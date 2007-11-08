/**
 * Copyright 2007 Paul Tuckey
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ibatis.sqlmap.implgen.proc;


import com.ibatis.sqlmap.implgen.Util;
import com.ibatis.sqlmap.implgen.bean.ParsedParam;
import com.ibatis.sqlmap.implgen.bean.ParsedProc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProcParserSybase implements ProcParser {

    Util.Log log = Util.getLog();


    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final String SPACE = "(?> --  [^\\n] * | \\/\\*  .*? \\*\\/ | \\s )";
    private static final String SPACES = "(?>" + SPACE + "++)";
    private static final String OPT_SPACE = "(?>" + SPACE + "*+)";
    private static final String ITEM = "(?> (?> \" [^\"]*+  \" )+ | (?: ' [^']*+  ' )+ | \\w++ )";
    private static final String PARAM = // Use "(?i:" as special markers. See PARAM_PATTERN.
            OPT_SPACE + "@(?i: \\w+ )" + SPACES +
                    "(?i: \\w+ " +
                    "     (?>" + OPT_SPACE +
                    "            \\(" + OPT_SPACE + "\\d+" + OPT_SPACE + "(?: ," + OPT_SPACE + "\\d+" + OPT_SPACE + ")? \\)" +
                    "     )? " +
                    OPT_SPACE +
                    ")" +
                    "(?>    \\b not" + SPACES + "null \\b" + OPT_SPACE +
                    "     | = " + OPT_SPACE + ITEM + OPT_SPACE +
                    "     | " + OPT_SPACE + "\\b OUTPUT \\b" + OPT_SPACE +
                    ")*";

    /**
     * Pattern to match a Create Procedure ... AS ... and capture:
     * 1) The procedure name
     * 2) All parameters together (if any)
     * 3) Everything following "AS"
     * <p/>
     * Properly handles any imbedded comments and character strings.
     */
    private static final Pattern CREATE_PROC = Pattern.compile(
            "\\bcreate" + SPACES + "proc(?>edure)?" + SPACES + "(\\w+) \\b" + OPT_SPACE +
                    "(?>" +
                    "     (" +
                    "         \\(" +
                    "        (?:" + PARAM + "(?: ," + PARAM + ")*|" + OPT_SPACE + ")" +
                    "         \\)" + OPT_SPACE +
                    "         |" +
                    "        " + PARAM + "(?: ," + PARAM + ")*" +
                    "        " + OPT_SPACE +
                    "     )" +
                    ")?" +
                    "\\b AS \\b (.*)",
            Pattern.CASE_INSENSITIVE | Pattern.COMMENTS | Pattern.DOTALL);

    private static final Pattern CREATE_VIEW = Pattern.compile(
            "\\bcreate" + SPACES + "view" + SPACES + "(\\w+) \\b" + OPT_SPACE,
            Pattern.CASE_INSENSITIVE | Pattern.COMMENTS | Pattern.DOTALL);

    /**
     * Pattern to match the parameter name and datatype from a list of parameters.
     * Apply continuously to the output (2) of CREATE_PROC until no more are matched.
     * if (2) is not null then this pattern should match at least once.
     * Captures:
     * 1) The parameter name
     * 2) The parameter data type.
     */
    private static final Pattern PARAM_PATTERN = Pattern.compile(PARAM.replaceAll("\\(\\?i:", "("),
            Pattern.CASE_INSENSITIVE | Pattern.COMMENTS | Pattern.DOTALL);

    private final Pattern RETURN_JAVADOC_PATTERN = Pattern.compile("^\\s*--\\s*(?:@)?return(?:\\:)?\\s+(.*)$", Pattern.CASE_INSENSITIVE);
    private final Pattern AUTHOR_JAVADOC_PATTERN = Pattern.compile("^\\s*--\\s*(?:@)?author(?:\\:)?\\s+(.*)$", Pattern.CASE_INSENSITIVE);
    private final Pattern VERSION_JAVADOC_PATTERN = Pattern.compile("^\\s*--\\s*(?:@)?version(?:\\:)?\\s+(.*)$", Pattern.CASE_INSENSITIVE);
    private final Pattern PARAM_JAVADOC_PATTERN = Pattern.compile("^\\s*--\\s*@param\\s+(.*)$", Pattern.CASE_INSENSITIVE);


    public void process(File f, ParsedProc sp) throws IOException {
        int size = (int) f.length();
        FileReader fis = new FileReader(f);
        String procNameFromFileName = f.getName().substring(0, f.getName().length() - ".sql".length());
        char[] buffer = new char[size];
        fis.read(buffer);
        fis.close();
        Matcher procMatcher = CREATE_PROC.matcher(CharBuffer.wrap(buffer));
        if (procMatcher.find()) {
            String procName = procMatcher.group(1);
            String params = procMatcher.group(2);
            String remainder = procMatcher.group(3);

            if (!procName.equalsIgnoreCase(procNameFromFileName)) {
                log.error("name mismatch, name in proc: " + procName + ", file: " + procNameFromFileName + " ");
            }
            sp.setName(procName);

            parseParams(params, sp);

            parseDoc(remainder, sp);
        } else {
            // might be a view
            Matcher viewMatcher = CREATE_VIEW.matcher(CharBuffer.wrap(buffer));
            if (viewMatcher.find()) {
                log.error("is a view skipping");
            } else if (sp.getName() == null) {
                log.error("unable to parse " + f.getName());
            }
        }
    }

    HashMap<String, String> customSqlToJavaTypeConverters = new HashMap<String, String>();

    public void registerSqlToJavaConversion(String sqlType, String javaType) {
        customSqlToJavaTypeConverters.put(sqlType, javaType);
    }


    private void parseParams(String params, ParsedProc sp) {
        if (params == null) return;
        Matcher paramMatcher = PARAM_PATTERN.matcher(params);
        ArrayList<ParsedParam> paramsRaw = new ArrayList<ParsedParam>();
        while (paramMatcher.find()) {
            String param = paramMatcher.group(1);
            String sqlType = paramMatcher.group(2);
            //log.debug(", " + count + ") " + param + ": " + type.replaceAll("\\s+$", ""));
            ParsedParam pp = new ParsedParam(param, sqlType);
            pp.setJavaType(sqlTypeToJavaType(sqlType));
            pp.setJdbcType(sqlTypeToJdbcType(sqlType));
            paramsRaw.add(pp);
        }
        ParsedParam[] paramsArr = new ParsedParam[paramsRaw.size()];
        paramsRaw.toArray(paramsArr);
        sp.setParams(paramsArr);
    }


    private void parseDoc(String procBody, ParsedProc sp) {
        String[] bodyLines = procBody.split(NEW_LINE);
        boolean parsingVars = false;
        String description = "";
        for (String bodyLine : bodyLines) {
            if (bodyLine == null) continue;
            bodyLine = bodyLine.trim();
            if (bodyLine.length() == 0) continue;
            if (Pattern.compile("^begin").matcher(bodyLine).find()) continue;
            // get rid of empty lines
            if (!Pattern.compile("^\\s*--").matcher(bodyLine).find()) break;
            // get rid of empty tag lines  ie, "-- @sometag"
            if (bodyLine.matches("^\\s*--\\s*@\\w+\\s*$")) {
                log.error("ERROR empty tag: " + bodyLine);
                continue;
            }

            Matcher returnMatcher = RETURN_JAVADOC_PATTERN.matcher(bodyLine);
            if (returnMatcher.matches()) {
                sp.setReturnDesc(returnMatcher.group(1));
                continue;
            }
            Matcher authorMatcher = AUTHOR_JAVADOC_PATTERN.matcher(bodyLine);
            if (authorMatcher.matches()) {
                sp.setAuthor(authorMatcher.group(1));
                continue;
            }
            Matcher versionMatcher = VERSION_JAVADOC_PATTERN.matcher(bodyLine);
            if (versionMatcher.matches()) {
                sp.setVersion(versionMatcher.group(1));
                continue;
            }
            Matcher paramMatcher = PARAM_JAVADOC_PATTERN.matcher(bodyLine);
            if (paramMatcher.find()) parsingVars = true;
            if (parsingVars) {
                if (paramMatcher.matches()) {
                    String paramDescBody = paramMatcher.group(1);
                    Matcher paramDescMatcher = Pattern.compile("^(\\w+)\\s+(.*)$").matcher(paramDescBody);
                    if (paramDescMatcher.matches()) {
                        String paramDescName = paramDescMatcher.group(1);
                        String paramDesc = paramDescMatcher.group(2);
                        if (paramDesc.startsWith("-")) {
                            // back populate onto param
                            for (int j = 0; j < sp.getParams().length; j++) {
                                ParsedParam param = sp.getParams()[j];
                                if (param.getName().equalsIgnoreCase(paramDescName)) {
                                    param.setDescription(paramDesc);
                                }
                            }
                        }
                    }
                }
            } else {
                // all these lines are description
                Matcher descLineMatcher = Pattern.compile("^\\s*--(.*)$").matcher(bodyLine);
                descLineMatcher.matches();
                String descLine = descLineMatcher.group(1);
                descLine = descLine.trim();
                if (descLine.startsWith("Name:")) {
                    continue;
                }
                if (descLine.startsWith("$Id:")) {
                    sp.setVersion(descLine);
                    continue;
                }
                if (Pattern.compile("^-+$").matcher(descLine).matches()) continue;
                if (descLine.startsWith("Description:")) {
                    descLine = descLine.substring("Description:".length()).trim();
                }
                description += (description.length() > 0 ? "<br/>" : "") + descLine;
            }
        }
        sp.setDescription(description);
    }


    public String sqlTypeToJavaType(String sqlType) {
        String type = "";
        sqlType = Util.trimToNull(sqlType);
        String customType = customSqlToJavaTypeConverters.get(sqlType);
        if (Pattern.compile("^char.*$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.String";
        if (Pattern.compile("^varchar.*$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.String";
        if (Pattern.compile("^numeric.*$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.Long";
        if (Pattern.compile("^int.*$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.Integer";
        if (Pattern.compile("^smallint.*$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.Integer";
        if (Pattern.compile("^datetime$", Pattern.CASE_INSENSITIVE).matcher(sqlType).matches()) type = "java.lang.Date";
        if (customType!= null) type = customType;
        if (type.length() == 0) System.out.println("st:'" + sqlType + "' " + type);
        return type;
    }

    public String sqlTypeToJdbcType(String sqlType) {
        String type = "";
        if (sqlType.matches("^char.*$")) type = "VARCHAR";
        if (sqlType.matches("^varchar.*$")) type = "VARCHAR";
        if (sqlType.matches("^numeric.*$")) type = "INTEGER";
        return type;
    }

}