<!-- pageディレクティブ -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- includeディレクティブ -->
<%@include file="/header.jsp" %>

<h1>WARNING</h1>
<div id="wrapper">
<%--
	<h2>${request.requestScope("error1")}</h2>
	<h2>${request.requestScope("error2")}</h2>
	<h2>${request.requestScope("error3")}</h2>
 --%>
	<h2><%= request.getAttribute("error1")%></h2>
	<h2><%= request.getAttribute("error2")%></h2>
	<h2><%= request.getAttribute("error3")%></h2>
</div>
<%@include file="footer.jsp" %>
