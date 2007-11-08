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
package com.ibatis.sqlmap.implgen.bean;

import com.ibatis.sqlmap.implgen.bean.ParsedParam;

import java.io.File;

/**
 * Models a database stored procedure.
 *
 * @see ParsedParam
 * @see com.ibatis.sqlmap.implgen.proc.ProcParser
 */
public class ParsedProc {

    private String name;
    private File originalFile;
    private String description;
    private String author;
    private String version;
    private ParsedParam[] params;
    private String returnDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(File originalFile) {
        this.originalFile = originalFile;
    }

    public boolean isAnyParams() {
        return params != null && params.length > 0;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setParams(ParsedParam[] params) {
        this.params = params;
    }

    public ParsedParam[] getParams() {
        return params;
    }

    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isVersion() {
        return version != null && version.length() > 0;
    }
    public boolean isAuthor() {
        return author != null && author.length() > 0;
    }
    public boolean isReturnDesc() {
        return returnDesc != null && returnDesc.length() > 0;
    }

    public void setReturnDesc(String s) {
        returnDesc = s;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public boolean isValid() {
        return name != null;
    }
}
