<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE sqlMap
    PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN"
    "http://ibatis.apache.org/dtd/sql-map-2.dtd">

<%@ page import="org.tuckey.ibatis.implgen.*" %>
<%@ page import="org.tuckey.ibatis.implgen.annotations.*" %>
<%! public ParsedClass parsedClass; %>

<!--

  Generated implementation of <%= parsedClass.getFullyQualifiedName() %>
 
  DO NOT EDIT

-->
<sqlMap namespace="<%= parsedClass.getFullyQualifiedName() %>">

    <% for (ParsedCacheModel cacheModel : parsedClass.getCacheModels()) { %>
    <cacheModel id="<%= cacheModel.getId() %>" type="<%= cacheModel.getType() %>">
        <% if ( cacheModel.isFlushInterval() ) { %><flushInterval hours="<%= cacheModel.getFlushIntervalHours() %>" /><% } %>
    </cacheModel>
    <% } %>

    <% for (ParsedResultMap resultMap : parsedClass.getResultMaps()) { %>
        <% if ( Util.empty(resultMap.getJavaClass())) { continue; } %>
    <resultMap id="<%= resultMap.getId() %>" class="<%= resultMap.getJavaClass() %>" <%--
            --%><% if ( resultMap.isAnyExtendsMap() ) { %> extends="<%= resultMap.getExtendsMap() %>" <% } %> ><%--
        --%><% for (ParsedResult result : resultMap.getResults()) { %>
        <result property="<%= result.getProperty() %>" <%--
                --%><% if ( !Util.empty(result.getJavaType()) ) { %> javaType="<%= result.getJavaType() %>" <% } %><%--
                --%> column="<%= result.getColumn() %>"<%--
                --%><% if ( !Util.empty(result.getJdbcType()) ) { %> jdbcType="<%= result.getJdbcType() %>" <% } %><%--
                --%><% if ( result.isAnyNullValue() ) { %> nullValue="<%= result.getNullValue() %>" <% } %> /><%--
        --%><% } %>
    </resultMap>
    <% } %>


    <% for (ParsedMethod method : parsedClass.getMethods()) { %>
        <% if ( Select.class.equals(method.getTypeClass()) ) { %>
    <select id="<%= method.getName() %>" <%--
            --%><% if ( method.isAnyParameterClass() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %><%--
            --%><% if ( method.isAnyCacheModel() ) { %> cacheModel="<%= method.getCacheModel() %>" <% } %><%--
            --%><% if ( method.isAnyResultMap() ) { %> resultMap="<%= method.getResultMap() %>" <% } %><%--
            --%><% if ( method.isAnyResultClass() ) { %> resultClass="<%= method.getReturnsType() %>" <% } %> >
        <%= method.getSqlEscaped() %>
    </select>

        <% } else if ( Statement.class.equals(method.getTypeClass()) ) { %>
    <statement id="<%= method.getName() %>" <%--
               --%><% if ( method.isAnyParameterClass() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %><%--
               --%><% if ( method.isAnyCacheModel() ) { %> cacheModel="<%= method.getCacheModel() %>" <% } %><%--
               --%><% if ( method.isAnyResultMap() ) { %> resultMap="<%= method.getResultMap() %>" <% } %><%--
               --%><% if ( method.isAnyResultClass() ) { %> resultClass="<%= method.getReturnsType() %>" <% } %>  >
        <%= method.getSqlEscaped() %>
    </statement>

        <% } else if ( Update.class.equals(method.getTypeClass()) ) { %>
    <update id="<%= method.getName() %>" <%--
                --%><% if ( ! method.isAnyParameters() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %>  >
        <%= method.getSqlEscaped() %>
    </update>

        <% } else if ( Delete.class.equals(method.getTypeClass()) ) { %>
    <delete id="<%= method.getName() %>" <%--
            --%><% if ( ! method.isAnyParameters() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %>   >
        <%= method.getSqlEscaped() %>
    </delete>

        <% } else if ( Insert.class.equals(method.getTypeClass()) ) { %>
    <insert id="<%= method.getName() %>" <%--
            --%><% if ( ! method.isAnyParameters() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %>  >
        <%= method.getSqlEscaped() %>
    </insert>

        <% } else if ( Procedure.class.equals(method.getTypeClass()) ) { %>
    <procedure id="<%= method.getName() %>" <%--
               --%><% if ( method.isAnyParameterClass() ) { %> parameterClass="<%= method.getParameterClass() %>" <% } %><%--
               --%><% if ( method.isAnyCacheModel() ) { %> cacheModel="<%= method.getCacheModel() %>" <% } %><%--
               --%><% if ( method.isAnyResultMap() ) { %> resultMap="<%= method.getResultMap() %>" <% } %><%--
               --%><% if ( method.isAnyResultClass() ) { %> resultClass="<%= method.getReturnsType() %>" <% } %>  >
        <%= method.getSqlEscaped() %>
    </procedure>

        <% } %>
    <% } %>

</sqlMap>

