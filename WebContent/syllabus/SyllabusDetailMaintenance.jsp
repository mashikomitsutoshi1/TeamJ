<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.SyllabusDetail" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	   <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>シラバス詳細画面</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	    <script type="text/javascript">
	        // 削除確認ダイアログの関数
	        function confirmDeletion() {
	            // ユーザーに確認ダイアログを表示
	            return confirm("本当に削除してもよろしいですか？");
	        }
	    </script>

	</head>

	<body>

<%-- 検索結果をリストに格納 --%>
<% List<SyllabusDetail> list = (List)request.getAttribute("list"); %>

<h1>シラバス詳細画面</h1>
    <p>${admission_year}年度</p>

<%if(list == null){ %>
	シラバスが存在しません
<%}else{%>
	<%-- 学生件数を表示 --%>
	デバッグ用：検索結果は<%= list.size() %>件です。
<%}%>

		<table border="1">
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th>必須</th>
				<th>選択</th>
				<th>履修時間</th>
				<th>単位</th>
				<th>学年</th>
				<th>削除</th>
				<th>svkamoku</th>
			</tr>

<%int i = 0; %>

			<form action="SyllabusDetailMaintenanceExecute.action" method="post">
			<c:forEach var="p" items="${list}">
				<tr>
					<td><%-- 科目コード --%>
						<input type="text" name="subject_code" value="${p.subject_code}">

						<%-- <input type="hidden" name="subject_code_<%= i %>" value="${p.subject_code}"> --%>
					</td>
					<td><%-- 科目数 --%>
						<input type="text" name="subject_name" value="${p.subject_name}" readonly>
					</td>
					<td><%-- 必須 --%>
						<input type="text" name="required_flg" value="${p.required_flg}">
						<%--<input type="checkbox" name="required_flg" <c:if test="${p.required_flg==1}">checked</c:if>>--%>
					</td>
					<td><%-- 選択 --%>
						<input type="text" name="select_flg" value="${p.select_flg}">
						<%--<input type="checkbox" name="select_flg" <c:if test="${p.select_flg==1}">checked</c:if>>--%>
					</td>
					<td><%-- 履修時間 --%>
						<input type="text" value="${p.course_time}" name="course_time">
					</td>
					<td><%-- 単位 --%>
						<input type="text" value="${p.credit}" name="credit">
					</td>
					<td><%-- 学年 --%>
						<input type="text" value="${p.grade}" name="grade">
					</td>
					<td><%-- 削除 --%>
						<input type="hidden" name="subject_code_<%= i %>" value="${p.subject_code}">
						<button type="submit" name="del" value="<%= i %>">削除</button>
						<input type="hidden" name="del_subject_code" value="${p.subject_code}">
					</td>
					<td><%-- svkamoku --%>
						<input type="text" value="${p.svkamoku}" name="svkamoku">
					</td>
				</tr>
<% i++; %>
			</c:forEach>
		</table>

		<div>
			<input type="submit" name="allSyllbus" value="表示中コースの保守前シラバス出力"/>
		</div>
<%if(list.size()==0){ %>
			<input type="submit" name="copy" value="コピー元シラバス指定"/>
<%}else{%>
			<input type="submit" name="copy" value="コピー元シラバス指定" disabled/>
<%}%>
			<input type="submit" name="exec" value="実行（excelあり）"/>
			<input type="hidden" name="admission_year" value=${admission_year}>
	        <input type="submit" name="add" value="新規科目追加">
	    </form>

	    <!-- すべて削除ボタン。confirmDeletion() がfalseの場合、削除処理が実行されません。 -->
	    <form action="SyllabusDetailMaintenanceExecute.action" method="post" onsubmit="return confirmDeletion();">
			<input type="hidden" name="admission_year" value=${admission_year}>
	        <input type="submit" name="delAll"value="全て削除">
	    </form>

	</body>
</html>