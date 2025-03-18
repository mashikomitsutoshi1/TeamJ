<%@page contentType="text/html; charset=UTF-8" %>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 共通CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">

<style>
  body {
    background-color: #e0f7ff;
    background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                      linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
    background-size: 20px 20px;
  }
  .main {
    margin-bottom: 20px;
    padding: 20px;
  }
  .button-container {
    text-align: right;
    margin-top: 10px;
  }
  .search-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 15px;
    padding: 15px;
  }
  .inline {
    min-width: 150px;
  }
  #search-button {
    margin-left: 10px;
  }
</style>

<div class="container">
    <div class="card main">
  <p>処理内容</p>
  <form id="student_number_regist" action="ScoreRegistStudentSearchScreenDisplay.action" method="post" class="inline">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" checked>学籍番号
    </label>
  </form>
  <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post" class="inline">
    <label>
      <input class="js-check" type="checkbox" name="rs" value="1" onchange="classRegist(this)">クラス指定
    </label>
  </form>
  <label>
    成績保守期限:<span id="formattedDate" class="inline"></span>
  </label>
</div>

<br>
<div class="search-container">

  <!-- 学籍番号の入力欄 -->


  <!-- 学生情報を表示するための場所 -->
  <div id="student_info" class="student-info">
    <!-- 学生情報がここに表示されます -->
  </div>

  <!-- 学籍番号に基づいて学生情報を検索するためのフォーム -->
  <form id="search_form" action="SearchStudentInfo.action" method="post" class="inline">
    <input type="submit" value="学生情報検索" id="search_button">
     <span id="student_id" class="inline">学籍番号：<input type="text" name="student_id" id="student_id_input" size="10" required> </span>
  </form>

</div>
</div>

<script>
  // クラス指定チェックを入れた瞬間実行
  function classRegist(checkbox) {
    var form = checkbox.form; // チェックボックスの親フォームを取得
    if (checkbox.checked) {
      // チェックされた場合、フォームを送信
      form.submit();
    }
  }

  // 学籍番号を基に学生情報を取得する関数
  function fetchStudentInfo(studentId) {
    // 学籍番号が空でないか確認
    if (!studentId) {
      alert("学籍番号を入力してください。");
      return;
    }

    // サーバーに学籍番号を送信して学生情報を取得する処理
    fetch(`/SearchStudentInfo.action?student_id=${studentId}`, {
      method: 'GET', // GETメソッドを使用
    })
    .then(response => response.json())  // レスポンスをJSONで受け取る
    .then(data => {
      // 学生情報が返ってきたら、その情報をHTMLに表示する
      if (data.success) {
        displayStudentInfo(data.student, data.subjects);  // 学生情報と科目情報を表示する関数を呼び出す
      } else {
        document.getElementById('student_info').innerHTML = '該当する学生情報は見つかりませんでした。';
      }
    });
  }

  // 学生情報をHTMLに表示する関数
  function displayStudentInfo(student, subjects) {
    const studentInfoDiv = document.getElementById('student_info');

    // 学生情報
    let studentHTML = `
      <h3>学生情報</h3>
      <p><strong>学籍番号:</strong> ${student.student_id}</p>
      <p><strong>氏名:</strong> ${student.name}</p>
      <p><strong>学年:</strong> ${student.academic_year}</p>
    `;

    // 科目情報の表示
    if (subjects && subjects.length > 0) {
      studentHTML += `<h4>科目情報</h4>`;
      studentHTML += `<ul>`;
      subjects.forEach(subject => {
        studentHTML += `
          <li>
            <strong>科目コード:</strong> ${subject.subject_code} <br>
            <strong>科目名:</strong> ${subject.subject_name} <br>
            <strong>点数:</strong> <input type="text" value="${subject.score}" id="score_${subject.subject_code}" size="5">
          </li>
        `;
      });
      studentHTML += `</ul>`;
    } else {
      studentHTML += `<p>科目情報がありません。</p>`;
    }

    studentInfoDiv.innerHTML = studentHTML;
  }

  // エンターキーで成績検索をサポート
  document.getElementById('student_id_input').addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
      event.preventDefault();  // Enterキーのデフォルト動作をキャンセル

      // 入学年度と学生番号を取得
      const admissionYear = document.getElementById('admission_year_input').value;
      const studentNo = document.getElementById('student_id_input').value;

      // 入学年度と学生番号が入力されていれば成績検索
      if (admissionYear && studentNo) {
        // URLにパラメータを追加してリダイレクト
        window.location.href = `/SearchStudentScore.action?admission_year=${admissionYear}&student_no=${studentNo}`;
      } else {
        alert("入学年度と学籍番号を入力してください。");
      }
    }
  });
</script>
