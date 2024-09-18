<%@page contentType="text/html; charset=UTF-8" %>
<div class="main">
  <p>処理内容</p>
  <form id="student_number_regist" action="ScoreRegistStudentSearchScreenDisplay.action" method="post">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" onchange="studentNumberRegist(this)">学籍番号
    </label>
  </form>
  <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" onchange="classRegist(this)">クラス指定
    </label>
  </form>
  <label>
    成績保守期限:<span id="formattedDate"></span>
  </label>
</div>
  <br>
  <span id="class">　クラス：<input type="text" name="class" value="" size="5"> </span>
  <span id="regist_year">　処理年度：<input type="text" name="class" value="" size="5"> </span>
  <form id="subject_search" action="SubjectCodeSearch.action" method="post">
  	<span id="subject">　科目：<input type="text" name="class" value="" size="5"> </span>
  	<button class="" id="search-button" name="subject">科目コード検索</button>
  </form>

  <span id="zaiseki_date">　在籍者抽出日：<input type="date" name="enrollment_date" value="" size="5"> </span>
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
</script>