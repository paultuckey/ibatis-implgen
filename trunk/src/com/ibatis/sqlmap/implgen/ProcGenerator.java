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

import com.ibatis.sqlmap.implgen.bean.ParsedProc;
import com.ibatis.sqlmap.implgen.proc.ProcParser;
import com.ibatis.sqlmap.implgen.proc.ProcParserSybase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ProcGenerator {

    Util.Log log = Util.getLog();

    /**
     * Goes through directory and parses all .sql files looking for procs.
     *
     * @param dir directory to process
     * @return array of stored procs
     */
    public void processDir(File dir) throws IOException {
        log.debug("on dir " + dir.getName());
        File[] files = dir.listFiles();
        ArrayList<ParsedProc> procsInThisDirRaw = new ArrayList<ParsedProc>();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                processDir(file);
            } else if (file.getName().endsWith(".sql")) {
                log.info("parsing " + file.getName());
                ParsedProc sp = processProc(file);
                if (!sp.isValid()) {
                    // do nothing todo: error?
                } else {
                    procsInThisDirRaw.add(sp);
                }
            }
        }
        if ( procsInThisDirRaw.size() > 0 ) {
            ParsedProc[] procs = new ParsedProc[procsInThisDirRaw.size()];
            procsInThisDirRaw.toArray(procs);
            processDirectory(procs, dir);
        }
    }

    private ParsedProc processProc(File f) {
        ParsedProc sp = new ParsedProc();
        sp.setOriginalFile(f);
        try {
            parser.process(f, sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public void registerSqlToJavaConversion(String sqlType, String javaType) {
        parser.registerSqlToJavaConversion(sqlType, javaType);
    }


    ProcParser parser = new ProcParserSybase();


    public abstract void processDirectory(ParsedProc[] procs, File directory) throws IOException;

}
