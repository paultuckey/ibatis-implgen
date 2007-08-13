/**
 * Copyright (c) 2005-2007, Paul Tuckey
 * All rights reserved.
 * ====================================================================
 * Licensed under the BSD License. Text as follows.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   - Neither the name tuckey.org nor the names of its contributors
 *     may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package org.tuckey.ibatis.implgen.proc;


import org.tuckey.ibatis.implgen.Util;
import org.tuckey.ibatis.implgen.bean.ParsedParam;
import org.tuckey.ibatis.implgen.bean.ParsedProc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
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
        if (sqlType.matches("^char.*$")) type = "java.lang.String";
        if (sqlType.matches("^varchar.*$")) type = "java.lang.String";
        if (sqlType.matches("^numeric.*$")) type = "java.lang.Long";
        return type;
    }

    public String sqlTypeToJdbcType(String sqlType) {
        String type = "";
        if (sqlType.matches("^char.*$")) type = "VARCHAR";
        if (sqlType.matches("^varchar.*$")) type = "VARCHAR";
        if (sqlType.matches("^numeric.*$")) type = "INTEGER";
        return type;
    }


    String[][] typeMap2 = {
            {"java.lang.Boolean", "BIT"},
            {"java.lang.Byte", "TINYINT"},
            {"java.lang.Short", "SMALLINT"},
            {"java.lang.Integer", "INTEGER"},
            {"java.lang.Long", "INTEGER"},
            {"java.lang.Double", "FLOAT"},
            {"java.lang.Double", "DOUBLE PRECISION"},
            {"java.lang.Float", "REAL"},
            {"java.math.BigDecimal", "NUMERIC"},
            {"java.math.BigDecimal", "DECIMAL"},
            {"java.lang.String", "CHAR"},
            {"java.lang.String", "VARCHAR"},
            {"java.lang.String", "TEXT"},
            {"java.sql.Date", "DATETIME"},
            {"java.sql.Time", "DATETIME"},
            {"java.sql.Timestamp", "TIMESTAMP"},
            {"byte[]", "BINARY"},
            {"byte[]", "VARBINARY"},
            {"java.lang.Object", "IMAGE"},
            {"java.io.InputStream", "IMAGE"},
            {"java.sql.Clob", "TEXT"}
    };

}


