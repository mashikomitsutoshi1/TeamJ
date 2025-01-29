<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Syllabus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>シラバス一覧</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	    <script type="text/javascript">
	        // 確認ダイアログの関数
	        function message() {
	            // ユーザーに確認ダイアログを表示
	            return alert("表示されているシラバス一覧をエクセルで出力します");
	        }
	    </script>
	</head>

	<body>

<%-- 検索結果をリストに格納 --%>
<% List<Syllabus> list = (List)request.getAttribute("list"); %>

		<h1>シラバス一覧</h1>

<%if(list == null){ %>
	ぬる
<%}else{%>
	<%-- 年度別シラバスの件数を表示 --%>
	★デバッグ用★検索結果は<%= list.size() %>件です。
<%}%>

		<table  class="table">
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
		<c:forEach var="p" items="${list}">
		<form action="SyllabusDetailMaintenance.action" method="post">
			<tr>
				<td><%-- ボタン部 --%>
					<button type="submit" name="select">選択</button>
					<input  type="hidden" name="admission_year"  value= "${p.admission_year}">
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
		</form>
		</c:forEach>
		</table>

		<form action="SyllabusMaintenanceExecute.action" method="post"  onsubmit="return message();">
			<div>
				<input type="submit" name="allSyllbus" value="全コースのシラバス出力"/>
			</div>
		</form>

		<form action = "../index.jsp" method="post">
			<input type="submit" name="finish" value="終了"/>
		</form>
	</body>
</html>