<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Syllabus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	</head>
	<body>
		<% List<Syllabus> list = (List<Syllabus>)request.getAttribute("list"); %>

		<a>コピー元シラバス</a>

		<%if(list == null){ %>
	ぬるぬる
<%}else{%>
	<%-- 学生件数を表示 --%>
	検索結果は<%= list.size() %>件です。
<%}%>

		<table border="1">
			<tr>
				<th></th>
				<th>年度</th>
				<th>科目数</th>
				<th>課程区分</th>
				<th>課程名</th>
				<th>科名</th>
				<th>コース名</th>
				<th>コース名内訳名</th>
				<th>修業年月</th>
			</tr>
<%int i = 0; %>
		<c:forEach var="p" items="${list}">
		<form action="SyllabusCopyExecute.action" method="post">
			<tr>
				<td><%-- ボタン部 --%>
				${p.admission_year}
				${saki_admission_year}
					<button type="submit" name="select" value="<%= i %>">選択</button>
			        <input type="hidden" name="saki_admission_year" value="${saki_admission_year}">
					<input type="hidden" name="admission_year_<%= i %>" value="${p.admission_year}">
				</td>
				<td><%-- 入学年度 --%>
					${p.admission_year}
				</td>
				<td><%-- 科目数 --%>
					${p.subject_count}
				</td>
				<td><%-- 課程区分 --%>
					${p.curriculum_category}
				</td>
				<td><%-- 課程名 --%>
					${p.curriculum_name}
				</td>
				<td><%-- 科名 --%>
					${p.department_name}
				</td>
				<td><%-- コース名 --%>
					${p.course_name}
				</td>
				<td><%-- コース名内訳名 --%>
					${p.course_name_breakdown}
				</td>
				<td><%-- 就業年数 --%>
					${p.study_year}
				</td>
			</tr>
<% i++; %>
		</c:forEach>
		</table>

	</body>
</html>