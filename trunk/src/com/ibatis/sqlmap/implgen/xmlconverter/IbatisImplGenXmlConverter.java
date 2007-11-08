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
package com.ibatis.sqlmap.implgen.xmlconverter;

import com.ibatis.sqlmap.implgen.Util;
import com.ibatis.sqlmap.implgen.template.generated.GeneratedSqlMapInterfaceTemplate;
import com.ibatis.sqlmap.implgen.bean.ParsedCacheModel;
import com.ibatis.sqlmap.implgen.bean.ParsedClass;
import com.ibatis.sqlmap.implgen.bean.ParsedMethod;
import com.ibatis.sqlmap.implgen.bean.ParsedResult;
import com.ibatis.sqlmap.implgen.bean.ParsedResultMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IbatisImplGenXmlConverter {

    Util.Log log = Util.getLog();


    public void convertXmlFile(File xmlFile, File saveAsJavaFile) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
        log.debug("converting " + xmlFile);
        log.debug("outputing to " + saveAsJavaFile);

        // parse sql map config

        // for each config file output java file


        InputSource inputSource = new InputSource(new FileInputStream(xmlFile));

        XPath xpath = XPathFactory.newInstance().newXPath();

        Node baseNode = (Node) xpath.evaluate("/sqlMap", inputSource, XPathConstants.NODE);

        List<Object> allItems = new ArrayList<Object>();

        ParsedClass parsedClass = new ParsedClass();
        parsedClass.setPackageStr("aaa");

        NodeList allBaseNodes = (NodeList) xpath.evaluate("*", baseNode, XPathConstants.NODESET);
        if (allBaseNodes != null) {
            for (int i = 0; i < allBaseNodes.getLength(); i++) {
                Node node = allBaseNodes.item(i);
                String name = node.getNodeName();
                if ("cacheModel".equals(name)) {
                    ParsedCacheModel cacheModel = new ParsedCacheModel();
                    cacheModel.setId(xpath.evaluate("@id", node));
                    cacheModel.setType(xpath.evaluate("@type", node));
                    cacheModel.setFlushIntervalHours(xpath.evaluate("flushInterval/@hours", node));
                    log.debug("parsedModel " + cacheModel);
                    allItems.add(cacheModel);
                }
                if ("resultMap".equals(name)) {
                    ParsedResultMap resultMap = new ParsedResultMap();
                    resultMap.setId(xpath.evaluate("@id", node));
                    resultMap.setJavaClass(xpath.evaluate("@class", node));
                    resultMap.setExtendsMap(xpath.evaluate("@extends", node));
                    log.debug("resultMap " + resultMap);

                    NodeList resultNodes = (NodeList) xpath.evaluate("result", node, XPathConstants.NODESET);
                    if (resultNodes != null) {
                        for (int j = 0; j < resultNodes.getLength(); j++) {
                            Node resultNode = resultNodes.item(j);
                            ParsedResult result = new ParsedResult();
                            result.setColumn(xpath.evaluate("@column", resultNode));
                            result.setJavaType(xpath.evaluate("@javaType", resultNode));
                            result.setJdbcType(xpath.evaluate("@jdbcType", resultNode));
                            result.setNullValue(xpath.evaluate("@nullValue", resultNode));
                            result.setProperty(xpath.evaluate("@property", resultNode));
                            log.debug("result " + result);
                            resultMap.addResult(result);
                        }
                    }
                    allItems.add(resultMap);
                }
                if ("procedure".equals(name) ||
                        "select".equals(name) ||
                        "delete".equals(name) ||
                        "update".equals(name) ||
                        "statement".equals(name) ||
                        "update".equals(name)) {
                    ParsedMethod method = new ParsedMethod();
                    method.setName(xpath.evaluate("@id", node));
                    method.setCacheModel(xpath.evaluate("@cacheModel", node));
                    method.setReturns(xpath.evaluate("@resultClass", node));
                    method.setSql(xpath.evaluate("text()", node));
                    log.debug("method " + method);
                    allItems.add(method);
                }
            }
        }

        if (allItems.size() > 0) {
            log.debug("got " + allItems.size() + " items, outputting");
            GeneratedSqlMapInterfaceTemplate sqlMapInterface = new GeneratedSqlMapInterfaceTemplate();
            sqlMapInterface.parsedClass = parsedClass;
            sqlMapInterface.allItems = allItems;
            sqlMapInterface.writeToFile(saveAsJavaFile);
        }

    }

    /**
     * test method
     */
    public static void main(String[] args) throws Exception {
        IbatisImplGenXmlConverter converter = new IbatisImplGenXmlConverter();

        try {

            converter.template(
                    new File("src/org/tuckey/ibatis/implgen/xmlconverter/example/existing-sqlmap-example-one.xml"),
                    new File("docs/ExampleOne.java")
            );

        } catch (Exception e) {
            converter.log.error(e);
            converter.log.error(e.getMessage());
            converter.log.error(e.getCause());
        }

    }

    private void template(File xml, File java) throws TransformerException, FileNotFoundException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(
                new StreamSource("src/org/tuckey/ibatis/implgen/xmlconverter/convert.xsl"));
        transformer.transform(new StreamSource(xml), new StreamResult(new FileOutputStream(java)));
    }

}
