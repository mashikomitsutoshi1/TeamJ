<!-- pageディレクティブ -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="bean.Student, java.util.List" %>

<!-- 下記の@SuppressWarningsはEclipseの警告を消すための記述です。 -->
<% @SuppressWarnings("unchecked") List<Student> slist=(List<Student>)request.getAttribute("slist"); %>

<!-- taglibディレクティブ カスタムタグ-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- includeディレクティブ -->
<%@include file="/header.jsp" %>

<div id="wrapper">
	<h1>クラス名簿表</h1>
	<!--メイン-->
	<div id="main">

		<!-- FrontControllerサーブレットへ -->
		<form action="ClassListSelect.action" method="post">
			<table>
				<tr>
					<th>入学年度<span class="must">※</span></th>
					<th>クラス<span class="must">※</span></th>
				</tr>
				<tr>
					<td><input type="text" name="admission_year"></td>
					<td><input type="text" name="class_no"></td>
				</tr>
			</table>
			<p id="submit_button_cover">
				<input type="submit" id="submit_button" value="検索">
			</p>
		</form>
	</div>

	<div id="sub">
		<table>
			<tr>
				<th>入学年度</th><th>学籍番号</th><th>氏名</th><th>氏名カナ</th><th>在席状況</th>
			</tr>
			<!-- JSTL/EL式 -->
			<!-- リクエスト属性に保存されたリストを処理 -->
			<!-- 学生情報Beanのプロパティ取得 -->
			<c:forEach var="t" items="${slist}">
				<tr>
					<td>${t.getAdmissionYear()}</td>
 					<td>${t.getStudentNo()}</td>
					<td>${t.getStudentName()}</td>
					<td>${t.getStudentNameKana()}</td>
					<td>在学中</td>
<!--
	 					<td>${t.getClassNo()}</td>
						<td>${t.getDispositionStatus()}</td>
						<td>${t.getTotalAbsences()}</td>
 -->
				</tr>
			</c:forEach>
		</table>
		<form action="ClassListWriteToCsv.action" method="post">
			<p id="submit_button_cover">
				<input type="submit" id="submit_button" value="ファイル出力">
			</p>
		</form>

	</div>
</div>

<%@include file="/footer.jsp" %>
