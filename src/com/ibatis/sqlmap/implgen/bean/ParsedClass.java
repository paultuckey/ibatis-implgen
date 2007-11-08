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

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ParsedClass {

    private ParsedMethod.Type overrideXmlType;
    private static final String GENERATED_FILE_SUFFIX = "GeneratedSqlMap";
    private String name = "";
    private String packageStr = "";
    private List<ParsedMethod> methods = new ArrayList<ParsedMethod>();
    private List<ParsedCacheModel> cacheModels = new ArrayList<ParsedCacheModel>();
    private List<ParsedResultMap> resultMaps = new ArrayList<ParsedResultMap>();
    private List<ParsedParameterMap> parameterMaps = new ArrayList<ParsedParameterMap>();
    private File classFile;
    private boolean classAnInterface;
    private String forceExtendClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParsedMethod> getMethods() {
        return methods;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public File getClassFile() {
        return classFile;
    }

    public void setClassFile(File classFile) {
        this.classFile = classFile;
    }

    public String getFullyQualifiedName() {
        return packageStr + "." + name;
    }

    public String getGeneratedXmlFileName() {
        return name + GENERATED_FILE_SUFFIX + ".xml";
    }

    public String getGeneratedXmlFilePath() {
        return getPackageStr().replace('.', '/') + '/' + getGeneratedXmlFileName();
    }

    public String getGeneratedJavaClassName() {
        return name + GENERATED_FILE_SUFFIX;
    }

    public String getGeneratedJavaClassFullyQualifiedName() {
        return getFullyQualifiedName() + GENERATED_FILE_SUFFIX;
    }

    public List<ParsedCacheModel> getCacheModels() {
        return cacheModels;
    }

    public List<ParsedResultMap> getResultMaps() {
        return resultMaps;
    }

    public void setClassAnInterface(boolean classAnInterface) {
        this.classAnInterface = classAnInterface;
    }

    public boolean isAnySQLMethods() {
        for (ParsedMethod method : methods) {
            if ( method.isSqlMethod() ) return true;
        }
        return false;
    }

    public ParsedMethod.Type getOverrideXmlType() {
        return overrideXmlType;
    }

    public void setOverrideXmlType(ParsedMethod.Type overrideXmlType) {
        this.overrideXmlType = overrideXmlType;
    }

    public boolean isClassAnInterface() {
        return classAnInterface;
    }

    public List<ParsedParameterMap> getParameterMaps() {
        return parameterMaps;
    }

    public void setForceExtendClass(String forceExtendClass) {
        this.forceExtendClass = forceExtendClass;
    }

    public String getForceExtendClass() {
        return forceExtendClass == null ? "SqlMapDaoTemplate" : forceExtendClass;
    }
}


