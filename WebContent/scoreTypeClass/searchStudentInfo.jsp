<%@ page import="java.util.List" %>
<%@ page import="bean.Score" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>学生情報</title>
</head>
<body>
    <h1>学生情報</h1>
    <p>学生氏名: ${student.studentName}</p>
    <p>入学年度: ${student.admissionYear}</p>

    <h2>成績情報</h2>
    <form action="RegistStudentInfo.action" method="get">
    	<c:choose>
			<c:when test="${scores.size()>0 }">
				<table border="1">
					<thead>
						<tr>
							<th>科目コード</th>
							<th>科目名</th>
							<th>月</th>
							<th>点数</th>
							<th>抽出日</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="score" items="${scores}">
							<tr>
								<td>${score.subjectCode}</td>
								<td>${score.subjectName}</td>
								<td><input type="text" id="month" name="month_${score.subjectCode }" <c:if test="${score.month != 0}">value="${score.month }"</c:if> value=""  ></td>
								<td><input type="text" id="point" name="point_${score.subjectCode }" <c:if test="${score.month != 0}">value="${score.point }"</c:if> value=""  ></td>
								<td>${score.extractionDate}</td>
							</tr>
							<input type="hidden" name="extractionDate_${score.subjectCode}" value="${score.extractionDate}">
							<input type="hidden" name="regist" value="${score.subjectCode}">
					</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="admission_year" value="${student.admissionYear}">
				<input type="hidden" name="student_no" value="${student.studentNo}">
				<input type="hidden" name="student_name" value="${student.studentName}">
				<button class="btn btn-secondary" id="filter-button">登録</button>
			</c:when>
			<c:when test="${count == 1 }">
				<div>登録が完了しました</div>
			</c:when>
			<c:otherwise>
				<div>学生情報が存在しませんでした</div>
			</c:otherwise>
			</c:choose>
    </form>
</body>
</html>
