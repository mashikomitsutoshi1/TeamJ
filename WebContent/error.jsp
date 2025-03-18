<!-- pageディレクティブ -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- includeディレクティブ -->
<%@include file="/header.jsp" %>

<h1 style="text-align:center">WARNING</h1>
<div id="wrapper">
<%--
	<h2>${request.requestScope("error1")}</h2>
	<h2>${request.requestScope("error2")}</h2>
	<h2>${request.requestScope("error3")}</h2>
 --%>
	<% if (request.getAttribute("error1") != null) { %>
	<h2><%= request.getAttribute("error1")%></h2>
	<% } %>
	<% if (request.getAttribute("error2") != null) { %>
	<h2><%= request.getAttribute("error2")%></h2>
	<% } %>
	<% if (request.getAttribute("error3") != null) { %>
	<h2><%= request.getAttribute("error3")%></h2>
	<% } %>

	<div id="button_container">
		<form action="../log/menu.jsp" method="post">
			<p id="submit_button_cover">
				<input type="submit" id="submit_button" value="終了">
			</p>
		</form>
	</div>

</div>

<%@include file="footer.jsp" %>
