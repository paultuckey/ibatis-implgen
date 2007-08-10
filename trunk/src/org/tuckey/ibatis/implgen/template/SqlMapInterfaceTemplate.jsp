<%@ page import="org.tuckey.ibatis.implgen.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedCacheModel" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedMethod" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedResultMap" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedClass" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedResult" %>
<%! public ParsedClass parsedClass; %>
<%! public List allItems; %>

package <%= parsedClass.getPackageStr() %>;

public interface <%= parsedClass.getName() %> {

<%
    for ( Object obj : allItems ) {
%>

    <% if ( obj instanceof ParsedCacheModel ) { %>
        <% ParsedCacheModel model = (ParsedCacheModel) obj; %>
        @CacheModel(id = "<%= model.getId() %>", type="<%= model.getType() %>", flushIntervalHours = "<%= model.getFlushIntervalHours() %>")
    <% } %>

    <% if ( obj instanceof ParsedResultMap ) { %><%--
        --%><% ParsedResultMap resultMap = (ParsedResultMap) obj; %><%--
        --%>@ResultMap(id = "<%= resultMap.getId() %>", results = {<%--
            --%><% for ( ParsedResult result : resultMap.getResults() ) { %><%--
                --%>@Result(property = "<%= result.getProperty()%>", column = "<%= result.getColumn()%>", javaType = "<%= result.getJavaType()%>", jdbcType = "<%= result.getJdbcType()%>", nullValue = "<%= result.getNullValue()%>"),
            <% } %>
            })
    <% } %>

    <% if ( obj instanceof ParsedMethod ) { %><%--
        --%><% ParsedMethod method = (ParsedMethod) obj; %>
        <% if ( method.getType() == ParsedMethod.Type.DELETE ) { %>
            @Delete
            public void <%= method.getName() %>() throws SQLException /*sql{

                <%= method.getSql() %>

            }*/;
        <% } %>
        <% if ( method.getType() == ParsedMethod.Type.PROCEDURE ) { %>
            @Procedure
            public void <%= method.getName() %>() throws SQLException /*sql{

                <%= method.getSql() %>

            }*/;
        <% } %>
    <% } %>

<% } %>


}