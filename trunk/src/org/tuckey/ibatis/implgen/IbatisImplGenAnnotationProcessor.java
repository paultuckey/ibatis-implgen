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

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.apt.AnnotationProcessors;
import com.sun.mirror.apt.Filer;
import com.sun.mirror.apt.Messager;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.util.SourcePosition;
import org.tuckey.ibatis.implgen.annotations.CacheModel;
import org.tuckey.ibatis.implgen.annotations.Delete;
import org.tuckey.ibatis.implgen.annotations.Insert;
import org.tuckey.ibatis.implgen.annotations.Procedure;
import org.tuckey.ibatis.implgen.annotations.ResultMap;
import org.tuckey.ibatis.implgen.annotations.Select;
import org.tuckey.ibatis.implgen.annotations.Statement;
import org.tuckey.ibatis.implgen.annotations.Update;
import org.tuckey.ibatis.implgen.annotations.Result;
import org.tuckey.ibatis.implgen.generated.GeneratedSqlMapImplClassTemplate;
import org.tuckey.ibatis.implgen.generated.GeneratedSqlmapXmlTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Annotation processor that implements annotated Interface's with SQL in comments as an IBatis SQLMap Class and XML
 * file.
 *
 * @see org.tuckey.ibatis.implgen.example.ExampleDaoOne
 */
public class IbatisImplGenAnnotationProcessor implements AnnotationProcessor, AnnotationProcessorFactory {

    private AnnotationProcessorEnvironment env;
    private Messager messager;
    private boolean showPositionsOfAnnotations = false;

    private Class[] annotationsToProcess = {
            Statement.class, Insert.class, Update.class, Select.class, Delete.class, Procedure.class,
            ResultMap.class, CacheModel.class, Result.class
    };

    private static final String SHOW_POSITIONS_OPTION = "-AshowPositions";
    private static final String START_SQL_INDICATOR = "/*sql{";

    public IbatisImplGenAnnotationProcessor() {
        // empty constructor for apt tool
    }

    public IbatisImplGenAnnotationProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
        messager = env.getMessager();
    }

    public void process() {
        Map<String, String> options = env.getOptions();
        Set<String> keys = options.keySet();
        for (String key : keys) {
            if (!key.startsWith(SHOW_POSITIONS_OPTION + "=")) continue;
            showPositionsOfAnnotations = "true".equals(key.substring(key.indexOf("=") + 1));
        }
        try {
            Map<String, ParsedClass> classesReferedTo = new HashMap<String, ParsedClass>();
            for (Class annotationToProces : annotationsToProcess) {
                processAnnotationsForAnnotation(annotationToProces, classesReferedTo);
            }
            for (ParsedClass parsedClass : classesReferedTo.values()) {
                processClass(parsedClass);
            }
        } catch (Throwable t) {
            messager.printError(t.getMessage());
            t.printStackTrace(System.err);
        }
    }

    /**
     * Outputs the implemented class file and xml file.
     */
    private void processClass(ParsedClass parsedClass) throws IOException {
        PrintWriter xmlFileWriter = env.getFiler().createTextFile(Filer.Location.SOURCE_TREE, parsedClass.getPackageStr(),
                new File(parsedClass.getGeneratedXmlFileName()), null);
        GeneratedSqlmapXmlTemplate mapXmlTemplate = new GeneratedSqlmapXmlTemplate();
        mapXmlTemplate.parsedClass = parsedClass;
        mapXmlTemplate.write(xmlFileWriter);

        PrintWriter mapClassWriter = env.getFiler().createSourceFile(parsedClass.getGeneratedJavaClassFullyQualifiedName());
        GeneratedSqlMapImplClassTemplate mapClassTemplate = new GeneratedSqlMapImplClassTemplate();
        mapClassTemplate.parsedClass = parsedClass;
        mapClassTemplate.write(mapClassWriter);
    }

    /**
     * Finds all annotations of the class specified and populates them onto the map.
     */
    private void processAnnotationsForAnnotation(Class annotationClass, Map<String, ParsedClass> classesReferedTo)
            throws IOException {
        AnnotationTypeDeclaration type = (AnnotationTypeDeclaration) env.getTypeDeclaration(annotationClass.getName());
        Collection<Declaration> declarations = env.getDeclarationsAnnotatedWith(type);
        for (Declaration declaration : declarations) {
            if (!(declaration instanceof MethodDeclaration)) continue;
            MethodDeclaration methodDeclaration = (MethodDeclaration) declaration;
            System.out.println(annotationClass + " got " + methodDeclaration);
            TypeDeclaration classDeclaration = methodDeclaration.getDeclaringType();
            ParsedClass parsedClass = classesReferedTo.get(classDeclaration.getQualifiedName());
            if (parsedClass == null) {
                parsedClass = new ParsedClass();
                parsedClass.setName(classDeclaration.getSimpleName());
                parsedClass.setPackageStr(classDeclaration.getPackage().getQualifiedName());
                parsedClass.setClassFile(methodDeclaration.getPosition().file());
                parsedClass.setClassIsInterface(classDeclaration instanceof InterfaceDeclaration);
                classesReferedTo.put(classDeclaration.getQualifiedName(), parsedClass);
            }

            if (ResultMap.class.equals(annotationClass)) {
                ResultMap model = declaration.getAnnotation(ResultMap.class);
                ParsedResultMap resultMap = new ParsedResultMap();
                resultMap.setId(model.id());
                Result[] results = model.results();
                for (Result result : results) {
                    ParsedResult parsedResult = new ParsedResult();
                    parsedResult.setProperty(result.property());
                    parsedResult.setColumn(result.column());
                    parsedResult.setJavaType(result.javaType());
                    parsedResult.setJdbcType(result.jdbcType());
                    parsedResult.setNullValue(result.nullValue());
                    resultMap.getResults().add(parsedResult);
                }
                parsedClass.getResultMaps().add(resultMap);

            } else if (Result.class.equals(annotationClass)) {
                messager.printError(methodDeclaration.getPosition(), "incorrectly placed @Result");
            } else if (CacheModel.class.equals(annotationClass)) {
                CacheModel model = declaration.getAnnotation(CacheModel.class);
                ParsedCacheModel cacheModel = new ParsedCacheModel();
                cacheModel.setId(model.id());
                cacheModel.setType(model.type());
                cacheModel.setFlushIntervalHours(model.flushIntervalHours());
                parsedClass.getCacheModels().add(cacheModel);

            } else {
                ParsedMethod method = processAnnotation((MethodDeclaration) declaration, annotationClass);
                parsedClass.getMethods().add(method);
            }
        }

        // back populate result class onto result map
        for (ParsedClass parsedClass : classesReferedTo.values()) {
            for (ParsedMethod method : parsedClass.getMethods()) {
                if (!method.isAnyResultMap()) continue;
                for (ParsedResultMap resultMap : parsedClass.getResultMaps()) {
                    if ( !method.getResultMap().equals(resultMap.getId())) continue;
                    resultMap.setJavaClass(method.getReturnsType());
                }
            }
        }

    }

    /**
     * Handles an annotated method.
     */
    private ParsedMethod processAnnotation(MethodDeclaration declaration, Class annotationClass)
            throws IOException {
        SourcePosition positionInCode = declaration.getPosition();
        StringBuffer sql = getSql(positionInCode);
        if (showPositionsOfAnnotations) {
            messager.printNotice(positionInCode, annotationClass.getSimpleName() + " annotation found");
            messager.printNotice("sql: " + sql);
        }
        List<ParsedParam> params = new ArrayList<ParsedParam>();
        Collection<ParameterDeclaration> formalParams = declaration.getParameters();
        for (ParameterDeclaration typeParam : formalParams) {
            ParsedParam param = new ParsedParam();
            param.setName(typeParam.getSimpleName());
            param.setType(typeParam.getType().toString());
            params.add(param);
        }
        String returns = declaration.getReturnType().toString();
        String returnsType = returns;
        if (returns.contains("<") && returns.contains(">")) {
            returnsType = returns.substring(returns.indexOf("<") + 1, returns.indexOf(">"));
        }
        ParsedMethod method = new ParsedMethod();
        method.setTypeClass(annotationClass);
        method.setName(declaration.getSimpleName());
        method.setSql(sql.toString());
        method.setParams(params);
        method.setReturns(returns);
        method.setReturnsType(returnsType);

        if (Select.class.equals(annotationClass)) {
            Select anno = declaration.getAnnotation(Select.class);
            method.setCacheModel(anno.cacheModel());
            method.setResultMap(anno.resultMap());

        } else if (Procedure.class.equals(annotationClass)) {
            Procedure anno = declaration.getAnnotation(Procedure.class);
            method.setCacheModel(anno.cacheModel());
            method.setResultMap(anno.resultMap());

        } else if (Statement.class.equals(annotationClass)) {
            Statement anno = declaration.getAnnotation(Statement.class);
            method.setCacheModel(anno.cacheModel());
            method.setResultMap(anno.resultMap());
        }
        return method;
    }

    /**
     * Reads SQL notated in the style "/*sql{  SQL GOES HERE }*\/" after the method declaration into a StringBuffer.
     */
    private StringBuffer getSql(SourcePosition positionInCode) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(positionInCode.file()));
        // move reader up to the position specified
        int currentLine = 0;
        while (currentLine + 1 < positionInCode.line()) {
            br.readLine();
            currentLine++;
        }
        br.skip(positionInCode.column());
        char lastChar = 0;
        StringBuffer sql = new StringBuffer();
        boolean appending = false;
        int startPosUpTo = -1;
        while (br.ready()) {
            char currChar = (char) br.read();
            if (appending) {
                sql.append(currChar);
            } else {
                int startPos = START_SQL_INDICATOR.indexOf(currChar);
                if (startPosUpTo + 1 == startPos) {
                    startPosUpTo++;
                    if (startPosUpTo == START_SQL_INDICATOR.length() - 1) appending = true;
                } else {
                    startPosUpTo = -1;
                }
            }
            if (sql.length() > 0 && '*' == lastChar && '/' == currChar) {
                sql.delete(sql.length() - 2, sql.length());
                break;
            }
            lastChar = currChar;
        }
        br.close();
        // trim off trailing } if it is there
        if (sql.lastIndexOf("}") == sql.length() - 1) sql.delete(sql.length() - 1, sql.length());
        return sql;
    }

    /**
     * Returns an annotation processor.
     *
     * @return An annotation processor for annotations if requested, otherwise, returns the NO_OP annotation processor.
     */
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> decls, AnnotationProcessorEnvironment env) {
        AnnotationProcessor result;
        if (decls.isEmpty()) {
            result = AnnotationProcessors.NO_OP;
        } else {
            result = new IbatisImplGenAnnotationProcessor(env);
        }
        return result;
    }

    /**
     * This factory only builds processors for all Ibatis Impl Gen annotations.
     *
     * @return a collection containing the annotation names.
     */
    public Collection<String> supportedAnnotationTypes() {
        Set<String> set = new HashSet<String>();
        for (Class annotationToProces : annotationsToProcess) {
            set.add(annotationToProces.getName());
        }
        return set;
    }

    public Collection<String> supportedOptions() {
        Set<String> set = new HashSet<String>();
        set.add(SHOW_POSITIONS_OPTION);
        return set;
    }

}
