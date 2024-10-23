<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1">
    <thead>
        <tr>
            <th>選択</th>
            <th>科目コード</th>
            <th>科目名</th>
        </tr>
    </thead>
    <tbody>
		<c:forEach var="p" items="${subject}">
			<tr>
				<td>
				<form action="ScoreRegistClassSearchScreenDisplayExecute.action" method="post">
					<button class="" id="select-button" name="subject_cd">選択</button>
					<input type="hidden" name="cd" value="${p.subjectCd}" />
					<input type="hidden" name="name" value="${p.subjectName}" />
				</form>
				</td>
				<td>
					<input class="" type="text" id="cd" name="cd" value="${p.subjectCd}" readonly />
				</td>
				<td>
					<input class="" type="text" id="name" name="name" value="${p.subjectName}" readonly />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


