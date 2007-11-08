<%@ page import="com.ibatis.sqlmap.implgen.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedCacheModel" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedMethod" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedResultMap" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedClass" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedResult" %>
<%@ page import="com.ibatis.sqlmap.implgen.bean.ParsedParam" %>
<%! public ParsedClass parsedClass; %>
<%! public List allItems; %>

package <%= parsedClass.getPackageStr() %>;

import java.sql.SQLException;
import com.ibatis.sqlmap.implgen.annotations.Procedure; <%-- todo: more types --%>

public interface <%= parsedClass.getName() %> {

<%
    for ( Object obj : allItems ) {

     if ( obj instanceof ParsedCacheModel ) { %>
        <% ParsedCacheModel model = (ParsedCacheModel) obj; %>
        @CacheModel(id = "<%= model.getId() %>", type="<%= model.getType() %>", flushIntervalHours = "<%= model.getFlushIntervalHours() %>")
    <%
        }
       if ( obj instanceof ParsedResultMap ) { %><%--
        --%><% ParsedResultMap resultMap = (ParsedResultMap) obj; %><%--
        --%>@ResultMap(id = "<%= resultMap.getId() %>", results = {<%--
            --%><% for ( ParsedResult result : resultMap.getResults() ) { %><%--
                --%>@Result(property = "<%= result.getProperty()%>", column = "<%= result.getColumn()%>", javaType = "<%= result.getJavaType()%>", jdbcType = "<%= result.getJdbcType()%>", nullValue = "<%= result.getNullValue()%>"),
            <% } %>
            })
       <%
      }
      if ( obj instanceof ParsedMethod ) { %><%--
        --%><% ParsedMethod method = (ParsedMethod) obj; %>
        <% if ( method.getType() == ParsedMethod.Type.DELETE ) { %>
            @Delete
            public void <%= method.getName() %>() throws SQLException /*sql{

                <%= method.getSql() %>

            }*/;
        <% }
           if ( method.getType() == ParsedMethod.Type.PROCEDURE ) { %>
            @Procedure
            public void <%= method.getName() %>(<%--
                --%><%
                    boolean first = true;
                    for (ParsedParam param : method.getParams()) {
                    %><%--
                    --%><%= first ? "": "," %> <%= param.getJavaTypeShort() %> <%= param.getName() %><%--
                --%><% first = false; %><%--
                --%><%
                    }
                %> ) throws SQLException /*sql{

                <%= method.getSql() %>

            }*/;
        <% }
        }
    } %>


}