<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		   <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Bootstrap demo</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

	</head>
	<body>
		<h1>シラバス追加</h1>

		<form action = "SyllabusAddExecute.action" method="post">
		<table border="1">
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th>必須</th>
				<th>選択</th>
				<th>履修時間</th>
				<th>単位</th>
				<th>学年</th>
				<th>svkamoku</th>
			</tr>
			<tr>
					<td><%-- 科目コード --%>
						<input type="text" name="subject_code" value="${subject_cd}">
					</td>
					<td><%-- 科目数 --%>
						<input type="text" name="subject_name" value="${subject_name}" readonly>
					</td>
					<td><%-- 必須 --%>
						<input type="text" name="required_flg">
					</td>
					<td><%-- 選択 --%>
						<input type="text" name="select_flg">
					</td>
					<td><%-- 履修時間 --%>
						<input type="text" name="course_time">
					</td>
					<td><%-- 単位 --%>
						<input type="text" name="credit">
					</td>
					<td><%-- 学年 --%>
						<input type="text" name="grade">
					</td>
					<td><%-- svkamoku --%>
						<input type="text" name="svkamoku">
					</td>
			</tr>
		</table>
${admission_year}
			<input type="hidden" name="admission_year" value=${admission_year}>
			<input type="submit" name="search" value="科目コード検索"/>
			<input type="submit" name="cancel" value="キャンセル"/>
			<input type="submit" name="exec" value="設定"/>
		</form>
	</body>
</html>