<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 共通CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
	<style>
	/* 背景デザイン */
    body {
    	background-color: #e0f7ff; /* 薄い青色 */
        background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                          linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
        background-size: 20px 20px; /* 方眼のサイズ調整 */
    }
    </style>

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


