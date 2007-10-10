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

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ParsedClass {

    private static final String GENERATED_FILE_SUFFIX = "GeneratedSqlMap";
    private String name = "";
    private String packageStr = "";
    private List<ParsedMethod> methods = new ArrayList<ParsedMethod>();
    private List<ParsedCacheModel> cacheModels = new ArrayList<ParsedCacheModel>();
    private List<ParsedResultMap> resultMaps = new ArrayList<ParsedResultMap>();
    private File classFile;
    private boolean classIsInterface;

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
        return getFullyQualifiedName().replace('.', '/') + '/' + getGeneratedXmlFileName();
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

    public String getImplementsOrExtends() {
        return classIsInterface ? "implements" : "extends";
    }

    public void setClassIsInterface(boolean classIsInterface) {
        this.classIsInterface = classIsInterface;
    }

    public boolean isAnySQLMethods() {
        for (ParsedMethod method : methods) {
            if ( method.isSqlMethod() ) return true;
        }
        return false;
    }
}
