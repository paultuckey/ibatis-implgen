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
package com.ibatis.sqlmap.implgen;


import com.ibatis.sqlmap.implgen.bean.ParsedClass;
import com.ibatis.sqlmap.implgen.bean.ParsedMethod;
import com.ibatis.sqlmap.implgen.bean.ParsedProc;
import com.ibatis.sqlmap.implgen.bean.ParsedParam;
import com.ibatis.sqlmap.implgen.template.generated.GeneratedSqlMapInterfaceTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Walk through all files/folders in the srcRoot and for each package containing sql files add an interface
 */
public class IbatisInterfaceProcGenerator extends ProcGenerator {

    Util.Log log = Util.getLog();

    protected boolean strict = true;

    public void processDirectory(ParsedProc[] procs, File directory) throws IOException {
        log.debug(directory.toString());

        String rawName = directory.getName();
        String name = makeClassName(rawName);

        File generatedInterfaceFile = new File(directory, name + ".java.txt");
        if (!generatedInterfaceFile.exists()) generatedInterfaceFile.createNewFile();
        if (!generatedInterfaceFile.canWrite()) {
            throw new IOException("cannot write to " + generatedInterfaceFile);
        }

        ParsedClass parsedClass = new ParsedClass();
        parsedClass.setName(name);
        List<Object> allItems = new ArrayList<Object>();
        for (ParsedProc proc : procs) {
            ParsedMethod method = new ParsedMethod();
            method.setType(ParsedMethod.Type.PROCEDURE);
            method.setName(proc.getName());
            method.getParams().addAll(Arrays.asList(proc.getParams()));
            String params = "";
            for (ParsedParam param : proc.getParams()) {
                params += (params.length() == 0 ? "":", ") +
                        "#" + param.getName() + ":" + param.getJdbcType() + "#";
            }
            method.setSql("{call " + proc.getName() + "(" + params + ") }");
            allItems.add(method);
        }

        GeneratedSqlMapInterfaceTemplate template = new GeneratedSqlMapInterfaceTemplate();
        template.allItems = allItems;
        template.parsedClass = parsedClass;
        template.writeToFile(generatedInterfaceFile);
    }

    private String makeClassName(String rawName) {
        String name = "";
        boolean upperCaseNextChar = true;
        for (char c : rawName.toCharArray()) {
            if ( (c + "").matches("\\w")) {
                // a word char ok to copy onto str
                name += upperCaseNextChar ? (c+"").toUpperCase() : c;
                upperCaseNextChar = false;
            }   else {
                upperCaseNextChar = true;
            }
        }
        if ( name.length() == 0 ) name = "SqlMapInterface";
        return name;
    }



}

