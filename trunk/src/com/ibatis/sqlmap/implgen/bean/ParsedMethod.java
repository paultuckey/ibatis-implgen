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
    private String parameterMap;
    private Type type;
    private String alternativeThrowsClass;
    private ParsedClass belongsToClass;

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

    public boolean isAnyParameterMap() {
        return parameterMap != null && parameterMap.length() > 0;
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

    public boolean isAlternativeThrows() {
        return alternativeThrowsClass != null;
    }

    public String getAlternativeThrowsClass() {
        return alternativeThrowsClass;
    }

    public void setAlternativeThrowsClass(String alternativeThrowsClass) {
        this.alternativeThrowsClass = alternativeThrowsClass;
    }

    public boolean isOkToOutputInImplClass() {
        return belongsToClass.isClassAnInterface() || isSqlMethod();
    }

    public void setBelongsToClass(ParsedClass belongsToClass) {
        this.belongsToClass = belongsToClass;
    }

    public String getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(String parameterMap) {
        this.parameterMap = parameterMap;
    }
}
