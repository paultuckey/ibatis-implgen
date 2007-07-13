<%@ page import="org.tuckey.ibatis.implgen.*" %>
<%! public ParsedClass parsedClass; %>

package <%= parsedClass.getPackageStr() %>;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.common.resources.Resources;

import java.util.HashMap;
import java.sql.SQLException;
import java.io.Reader;
import java.io.IOException;


/**
 * Generated implementation of <%= parsedClass.getFullyQualifiedName() %>.
 *
 * DO NOT EDIT
 *
 * @see <%= parsedClass.getFullyQualifiedName() %>
 */
public class <%= parsedClass.getGeneratedJavaClassName() %> <%= parsedClass.getImplementsOrExtends() %> <%= parsedClass.getName() %> {

  private static SqlMapClient sqlMapper;

  static {
    try {
      Reader reader = Resources.getResourceAsReader("<%= parsedClass.getGeneratedXmlFilePath() %>");
      sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
      reader.close();
    } catch (IOException e) {
      // Fail fast.
      throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
    }
  }


<% for (ParsedMethod method : parsedClass.getMethods()) { %>
    public <%= method.getReturns() %> <%= method.getName() %>(<%
            int i = 0;
            for (ParsedParam param : method.getParams()) { %><%-- 
                --%><%= (i > 0 ? ", " : "") %><%= param.getTypeForJavaClass() %> <%= param.getName()%><%
                i++;
            } %>) throws SQLException {

        <% if ( method.isAnyParameters() ) { %>
            <% if ( method.isMultipleParameters() ) { %>
        HashMap<String, Object> params = new HashMap<String, Object>();
                <% for (ParsedParam param : method.getParams()) { %>
        params.put("<%= param.getName()%>", <%= param.getName()%>);<%--
                --%><% } %>
            <% } %>
        <% } %>

        //noinspection unchecked,UnnecessaryLocalVariable
        <% if ( !method.isReturnsVoid() ) { %>
        <%= method.getReturns() %> ret = (<%= method.getReturns() %>)
        <% } %>
        <% if ( method.isReturnsList() ) { %>
        sqlMapper.queryForList("<%= parsedClass.getFullyQualifiedName() %>.<%= method.getName() %>", <%= method.getParamsVarName() %>);
        <% } else { %>
        sqlMapper.queryForObject("<%= parsedClass.getFullyQualifiedName() %>.<%= method.getName() %>", <%= method.getParamsVarName() %>);
        <% } %>
        <% if ( !method.isReturnsVoid() ) { %>
        return ret;
        <% } %>
    }

<% } %>

}