<!-- pageディレクティブ -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="bean.StudentGradeReport" %>
<%@page import="java.util.List" %>

<!-- 下記の@SuppressWarningsはEclipseの警告を消すための記述です。 -->
<% @SuppressWarnings("unchecked") List<StudentGradeReport> sglist=(List<StudentGradeReport>)request.getAttribute("sglist"); %>

<!-- taglibディレクティブ カスタムタグ-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- includeディレクティブ -->
<%@include file="/header.jsp" %>

<div id="wrapper">
	<h1>学生成績表</h1>
	<!--メイン-->
	<div id="main">

		<!-- FrontControllerサーブレットへ -->
		<form action="StudentGradeReportSelect.action" method="post">
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
	            <th>入学年度</th><th>学籍番号</th><th>学科</th><th>コース</th><th>氏名</th>
<!--
	            <th>生年月日</th><th>入学</th><th>卒業</th>
-->
	            <th>科目名</th><th>単位</th><th>評価</th>
	        </tr>

			<!-- JSTL/EL式 -->
			<!-- リクエスト属性に保存されたリストを処理 -->
			<!-- 学生成績Beanのプロパティ取得 -->
			<c:forEach var="t" items="${sglist}">
			        <!-- 入学年度から評価までの行 -->
			        <tr>
			            <td>${t.admissionYear}</td>
			            <td>${t.studentNo}</td>
			            <td>${t.departmentName}</td>
			            <td>${t.courseName}</td>
			            <td>${t.studentName}</td>
	<!--
			            <td></td>
			            <td></td>
			            <td></td>
	-->
			            <!-- 最初の科目情報を表示 -->
			            <c:if test="${not empty t.gradeReport}">
			                <td>${t.gradeReport[0].subject}</td>
			                <td>${t.gradeReport[0].credit}</td>
			                <td>${t.gradeReport[0].grading}</td>
			            </c:if>
			        </tr>

			        <!-- 残りの科目情報を追加表示 -->
			        <c:forEach var="subject" items="${t.gradeReport}" varStatus="status">
			            <c:if test="${status.index != 0}">
			                <tr>
			                    <td></td>
			                    <td></td>
			                    <td></td>
			                    <td></td>
			                    <td></td>
	<!--
			                    <td></td>
			                    <td></td>
			                    <td></td>
	-->
			                    <td>${subject.subject}</td>
			                    <td>${subject.credit}</td>
			                    <td>${subject.grading}</td>
			                </tr>
			            </c:if>
			        </c:forEach>
			    </c:forEach>
		</table>

		<form action="StudentGradeReportWriteToCsv.action" method="post">
			<p id="submit_button_cover">
				<input type="submit" id="submit_button" value="ファイル出力">
			</p>
		</form>

	</div>
</div>

<%@include file="/footer.jsp" %>
