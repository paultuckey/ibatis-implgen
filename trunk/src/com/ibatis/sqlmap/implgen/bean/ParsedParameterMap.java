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

import java.util.ArrayList;
import java.util.List;


public class ParsedParameterMap {
    private String id;
    private String extendsMap;
    private String javaClass;
    private List<ParsedParameter> parameters = new ArrayList<ParsedParameter>();

    public void addParameter(ParsedParameter parameter) {
        parameters.add(parameter);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtendsMap() {
        return extendsMap;
    }

    public void setExtendsMap(String extendsMap) {
        this.extendsMap = extendsMap;
    }

    public List<ParsedParameter> getParameters() {
        return parameters;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public boolean isAnyExtendsMap() {
        return extendsMap != null && extendsMap.length() > 0;
    }

    public String toString() {
        return "ParsedResultMap{" +
                "id='" + id + '\'' +
                ", extendsMap='" + extendsMap + '\'' +
                ", javaClass='" + javaClass + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}