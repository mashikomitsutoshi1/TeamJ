<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="p" items="${subject}">
	<form action="ScoreRegistClassSearchScreenDisplayExecute.action" method="post">
		<button class="" id="select-button" name="subject_cd">選択</button>
		<input class="" type="text" id="cd" name="cd" value="${p.subjectCd}" readonly />
		<input class="" type="text" id="name" name="name" value="${p.subjectName}" readonly />
	</form>

</c:forEach>

