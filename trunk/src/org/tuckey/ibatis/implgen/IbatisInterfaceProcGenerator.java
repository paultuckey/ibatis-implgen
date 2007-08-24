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
package org.tuckey.ibatis.implgen;


import org.tuckey.ibatis.implgen.bean.ParsedClass;
import org.tuckey.ibatis.implgen.bean.ParsedMethod;
import org.tuckey.ibatis.implgen.bean.ParsedProc;
import org.tuckey.ibatis.implgen.bean.ParsedParam;
import org.tuckey.ibatis.implgen.template.generated.GeneratedSqlMapInterfaceTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

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

