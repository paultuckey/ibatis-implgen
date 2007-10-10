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
package org.tuckey.ibatis.implgen.bean;

import org.tuckey.ibatis.implgen.Util;

import java.util.ArrayList;
import java.util.List;


public class ParsedMethod implements Comparable<ParsedMethod> {

    public enum Type {
        SELECT, STATEMENT, INSERT, UPDATE, DELETE, PROCEDURE
    }

    private String name;
    private String sql;
    private List<ParsedParam> params = new ArrayList<ParsedParam>();
    private String returns;
    private String returnsType;
    private String cacheModel;
    private String resultMap;
    private Type type;

    private int line;
    private int column;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public String getSqlEscaped() {
        return Util.escapeXML(sql);
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParsedParam> getParams() {
        return params;
    }

    public void setParams(List<ParsedParam> params) {
        this.params = params;
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isReturnsVoid() {
        return "void".equals(returns);
    }

    public boolean isReturnsList() {
        return returns.startsWith("java.util.List");
    }

    public String getReturnsType() {
        return returnsType;
    }

    public void setReturnsType(String returnsType) {
        this.returnsType = returnsType;
    }

    public boolean isAnyParameters() {
        return params.size() > 0;
    }

    public String getParameterClass() {
        return params.size() == 0 ? "null" : params.size() == 1 ? params.get(0).getJavaType() : "map";
    }

    public String getParamsVarName() {
        return params.size() == 0 ? "null" : params.size() > 1 ? "params" : params.get(0).getName();
    }

    public boolean isMultipleParameters() {
        return params.size() > 1;
    }

    public String getCacheModel() {
        return cacheModel;
    }

    public void setCacheModel(String cacheModel) {
        this.cacheModel = cacheModel;
    }

    public String getResultMap() {
        return resultMap;
    }

    public void setResultMap(String resultMap) {
        this.resultMap = resultMap;
    }

    public boolean isAnyCacheModel() {
        return cacheModel != null && cacheModel.length() > 0;
    }

    public boolean isAnyResultMap() {
        return resultMap != null && resultMap.length() > 0;
    }

    public boolean isAnyResultClass() {
        return !isAnyResultMap() && !isReturnsVoid();
    }

    public boolean isAnyParameterClass() {
        return params.size() > 0;
    }

    public String toString() {
        return "ParsedMethod{" +
                "name='" + name + '\'' +
                ", sql='" + sql + '\'' +
                ", params=" + params +
                ", returns='" + returns + '\'' +
                ", returnsType='" + returnsType + '\'' +
                ", cacheModel='" + cacheModel + '\'' +
                ", resultMap='" + resultMap + '\'' +
                ", type=" + type +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    public int compareTo(ParsedMethod other) {
        int result = 0;
        if (line > other.line) result = 1;
        else if (line < other.line) result = -1;
        else if (column > other.column) result = 1;
        else if (column < other.column) result = -1;
        return result;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isSqlMethod() {
        return sql != null && sql.length() > 0;
    }

}
