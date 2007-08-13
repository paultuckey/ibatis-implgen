<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedParam" %>
<%@ page import="org.tuckey.ibatis.implgen.bean.ParsedProc" %>
<%!
    public ParsedProc[] procs;
    public String name;
%>
<html>
<head>
    <title>Stored Procedures in <%= name %></title>
    <style type="text/css">
        .params td {
            width: 200px;
            border: 1px solid #eeeeee;
        }
    </style>
</head>
<body>

<h1>Stored Procedures in <%= name %></h1>

<% if (procs != null) {
    for (ParsedProc proc : procs) { %>
<a name="<%= proc.getName() %>"></a>

<h2><%= proc.getName() %>
</h2>
<% if (proc.isVersion()) { %>
<small>Version: <%= proc.getVersion() %>
</small>
<% } %>
<% if (proc.isAuthor()) { %>
<small>Author: " + proc.getAuthor() + "</small>
<% } %>
<p><%= proc.getDescription() %>
</p>

<% if (proc.isAnyParams()) { %>

<h3>Parameters</h3>
<table class="params">

    <% for (int j = 0; j < proc.getParams().length; j++) {
        ParsedParam param = proc.getParams()[j];
    %>
    <tr>
        <td><b><%= param.getName() %></b></td>
        <td><%= param.getSqlType() %></td>
        <td><%= param.getDescription() %></td>
    </tr>
    <% } %>
</table>
<% } %>

<% if (proc.isReturnDesc()) { %>
    <h3>Return</h3>
    <p><%= proc.getReturnDesc() %></p>
<% } %>
<hr/>

<%
        }
    }
%>

</body>
</html>
