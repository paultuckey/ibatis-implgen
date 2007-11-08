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

import com.ibatis.sqlmap.implgen.Util;

public class ParsedParam {
    private String name;
    private String jdbcType;
    private String sqlType;
    private String javaType;
    private String description;

    public ParsedParam(String param, String sqlType) {
        this.name = param;
        setSqlType(sqlType);
    }

    public ParsedParam() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = Util.trimToNull(jdbcType);
         if ( this.jdbcType != null ) this.jdbcType = jdbcType.toLowerCase();
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = Util.trimToNull(sqlType);
         if ( this.sqlType != null ) this.sqlType = sqlType.toLowerCase();
    }

    public String getJavaType() {
        return javaType == null ? "Object" : javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = Util.trimToNull(javaType);
    }

    public String getJavaTypeShort() {
        if ( javaType == null ) return null;
        return javaType.startsWith("java.lang.") ? javaType.substring("java.lang.".length()): javaType;
    }
}
