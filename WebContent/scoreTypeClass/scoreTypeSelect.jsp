<%@page contentType="text/html; charset=UTF-8" %>
<div class="main">
<p>処理内容</p>
<form id="student_number_regist" action="" method="post">

  <label>
    <input class="js-check" type="checkbox" name="rs" value="1" onclick="studentNumberRegist()" >学籍番号
  </label>
</form>
<form id="class_regist" action="" method="post">
  <label>
    <input class="js-check" type="checkbox" name="rs" value="1" onclick="classRegist()">クラス指定
  </label>

<form>
  <label>
    成績保守期限:<span id="formattedDate"></span>
  </label>
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

//クラス指定チェックを入れた瞬間実行
function classRegist() {
    var form = document.getElementById('class_regist');

    if (form.agree.checked) {
      form.submit(); // フォームを送信する
    }
}

//学籍番号にチェックを入れた瞬間実行
function studentNumberRegist() {
    var form = document.getElementById('student_number_regist');

    if (form.agree.checked) {
      form.submit(); // フォームを送信する
    }
}
</script>
