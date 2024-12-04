<!-- pageディレクティブ -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="bean.GradeReport" %>
<%@page import="java.util.List" %>

<!-- 下記の@SuppressWarningsはEclipseの警告を消すための記述です。 -->
<% @SuppressWarnings("unchecked") List<GradeReport> sglist=(List<GradeReport>)request.getAttribute("glist"); %>

<!-- taglibディレクティブ カスタムタグ-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- includeディレクティブ -->
<%@include file="/header.jsp" %>

<div id="wrapper">
	<h1>成績評価表</h1>
	<!--メイン-->
	<div id="main">

		<!-- FrontControllerサーブレットへ -->
		<form action="GradeReportSelect.action" method="post">
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
				<th>学籍番号</th><th>氏名</th><th>科目名</th><th>単位</th><th>評価</th>
			</tr>

			<!-- JSTL/EL式 -->
			<!-- リクエスト属性に保存されたリストを処理 -->
			<!-- 成績評価Beanのプロパティ取得 -->
			<c:forEach var="t" items="${glist}">
			        <!-- 学籍番号と氏名の行 -->
			        <tr>
			            <td>${t.studentNo}</td>
			            <td>${t.studentName}</td>
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
			                    <td>${subject.subject}</td>
			                    <td>${subject.credit}</td>
			                    <td>${subject.grading}</td>
			                </tr>
			            </c:if>
			        </c:forEach>
			    </c:forEach>
		</table>

		<form action="GradeReportWriteToCsv.action" method="post">
			<p id="submit_button_cover">
				<input type="submit" id="submit_button" value="ファイル出力">
			</p>
		</form>

	</div>
</div>

<%@include file="/footer.jsp" %>
