<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  .inline {
    display: inline-block;
    margin-right: 20px; /* 余白を調整 */
  }
  .main {
    margin-bottom: 20px; /* メインセクションの余白 */
  }
</style>


<div class="main">
  <p>処理内容</p>
  <form id="student_number_regist" action="ScoreRegistStudentSearchScreenDisplay.action" method="post" class="inline">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" onchange="studentNumberRegist(this)">学籍番号
    </label>
  </form>
  <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post" class="inline">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" checked>クラス指定
    </label>
  </form>
  <label>
    成績保守期限:<span id="formattedDate" class="inline"></span>
  </label>
</div>

  <br>
<div class="search">

  <span id="class" class="inline">　クラス：<input type="text" name="class" value="${class_num }" size="5" form="search_class" pattern="^[0-9]+$" > </span>
  <span id="regist_year" class="inline">　処理年度：<input type="text" name="regist_year" value="${regist_year }" size="5" form="search_class" readonly> </span>
  <form id="subject_search" action="SubjectCodeSearch.action" method="post" class="inline">
  	<span id="subject">　科目：<input type="text" name="subject" value="${subject_cd }:${subject_name}" size="5" readonly> </span>
  	<button class="" id="search-button" name="subject" >科目コード検索</button>
  </form>
  <span id="enrollment_date" class="inline">　在籍者抽出日：<input type="date" name="enrollment_date" value="${enrollment_date}" size="5" form="search_class"> </span>
  <input type="hidden" id="subject_cd" name="subject_cd" value="${subject_cd }" form="search_class">
  <input type="hidden" id="subject_name" name="subject_name" value="${subject_name }" form="search_class">
  <form id="search_class" action="ScoreRegistClassRegistScreenDisplay.action" method="post">
  	<input type="submit" value="開始">
  </form>
</div>

<div class="regist">
		<form action="ScoreRegistClassRegistExecute.action" method="get">
			<c:choose>
				<c:when test="${list.size()>0 }">
				<table class="table table-hover">
						<tr>
							<th></th>
							<th><input type="text" id="input_month" name="month_all" value=""><input id="button_month" type="submit" value="月一括変更"></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th><input type="text" id="input_point" name="point_all" value=""><input id="button_point" type="submit" value="得点一括変更"></th>
							<th><input id="clear_point" type="submit" value="得点クリア"></th>
						</tr>

						<tr>
							<th>学年</th>
							<th>月</th>
							<th>学生番号</th>
							<th colspan="2">科目</th>
							<th>氏名</th>
							<th>得点</th>
							<th></th>
						</tr>
						<c:forEach var="test" items="${list }">
							<tr>
								<td>${test.studyYear }</td>
								<td><input type="text" id="month" name="month_${test.studentNo }" <c:if test="${test.month != 0}">value="${test.month }"</c:if> value=""  ></td>
								<td>${test.studentNo }</td>
								<td>${test.subjectCode }</td>
								<td>${test.subjectName }</td>
								<td>${test.studentName }</td>
								<td><input type="text" id="point" name="point_${test.studentNo }" <c:if test="${test.point != 0}">value="${test.point }"</c:if> value="" ></td>
								<td></td>
							</tr>
							<input type="hidden" name="regist" value="${test.studentNo }">
						</c:forEach>

					</table>
					<input class="excel-check" type="checkbox" name="excel" value="1">Excel出力
					<input type="hidden" name="subject_cd" value="${subject_cd }">
					<input type="hidden" name="enrollment_date" value="${enrollment_date}">
					<input type="hidden" name="regist_year" value="${regist_year}">
					<input type="hidden" name="class_num" value="${class_num}">
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
</div>



<script>
  // サンプルとして、ISO 8601形式の日付を受け取る
  var receivedDate = '2024-09-06';

  // 日付をJavaScriptのDateオブジェクトに変換する
  var dateObj = new Date(receivedDate);

  // 日本の慣習に従った形式で日付をフォーマットする関数
  function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1; // 月は0-indexedなので+1する
    var day = date.getDate();

    // フォーマットした日付を返す（例: YYYY年MM月DD日）
    return year + '年' + month + '月' + day + '日';
  }

  // フォーマットした日付をHTMLに表示する
  var formattedDate = formatDate(dateObj);
  document.getElementById('formattedDate').textContent = formattedDate;

  // クラス指定チェックを入れた瞬間実行
  function classRegist(checkbox) {
    var form = checkbox.form; // チェックボックスの親フォームを取得

    if (checkbox.checked) {
      form.submit(); // フォームを送信する
    }
  }

  // 学籍番号にチェックを入れた瞬間実行
  function studentNumberRegist(checkbox) {
    var form = checkbox.form; // チェックボックスの親フォームを取得

    if (checkbox.checked) {
      form.submit(); // フォームを送信する
    }
  }

//月一括変更
  const inputMonth = document.getElementById("input_month");
  const monthButton = document.getElementById("button_month");
  monthButton.addEventListener("click", function (event) {
    event.preventDefault(); // フォーム送信を防ぐ
    const months = document.querySelectorAll("input[id^='month']");
    months.forEach((month) => {
      month.value = inputMonth.value;
    });
  });

  // 得点一括変更
  const inputPoint = document.getElementById("input_point");
  const pointButton = document.getElementById("button_point");
  pointButton.addEventListener("click", function (event) {
    event.preventDefault(); // フォーム送信を防ぐ
    const points = document.querySelectorAll("input[id^='point']");
    points.forEach((point) => {
      point.value = inputPoint.value;
    });
  });

  //得点一括クリア
  const pointClear = document.getElementById("clear_point");
  pointClear.addEventListener("click", function (event) {
    event.preventDefault(); // フォーム送信を防ぐ
    const points = document.querySelectorAll("input[id^='point']");
    points.forEach((point) => {
      point.value = "";
    });
  });

</script>