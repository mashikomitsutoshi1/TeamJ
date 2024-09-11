<%@page contentType="text/html; charset=UTF-8" %>
<div class="main">
<p>処理内容</p>
<form autocomplete=off>

  <label>
    <input class="js-check" type="radio" name="rs" value="1" onclick="formSwitch()" >学籍番号
  </label>

  <label>
    <input class="js-check" checked="checked" type="radio" name="rs" value="1" onclick="formSwitch()">クラス指定
  </label>

  <label>
    成績保守期限:<span id="formattedDate"></span>
  </label>
<form>

<p>クラス</p>
</div>

<div>
  <span id="sample">　クラス：<input type="text" name="class" value="" size="5"> </span>
  <span id="sample">　処理年度：<input type="text" name="class" value="" size="5"> </span>
  <span id="sample">　科目：<input type="text" name="class" value="" size="5"> </span>
  <span id="sample">　在籍者抽出日：<input type="date" name="enrollment_date" value="" size="5"> </span>
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


var selecterBox = document.getElementById('sample');

function formSwitch() {
    check = document.getElementsByClassName('js-check')
    if (check[0].checked) {
        selecterBox.style.display = "none";

    } else if (check[1].checked) {
        selecterBox.style.display = "block";

    } else {
        selecterBox.style.display = "none";
    }
}
window.addEventListener('load', formSwitch());

function entryChange2(){
if(document.getElementById('changeSelect')){
id = document.getElementById('changeSelect').value;
}
}
</script>
