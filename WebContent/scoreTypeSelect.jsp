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
    成績保守期限:
<input type="date" class="w120" value="<fmt:formatDate value="2024-03-31" pattern="yyyy年MM月dd日" />">
  </label>
<form>
</div>


  <span id="sample">　クラス：<input type="text" name="class" value="" size="5"> </span>

<script>
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
