<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録画面</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 共通CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
	<style>
	/* 背景デザイン */
    body {
    	background-color: #e0f7ff; /* 薄い青色 */
        background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                          linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
        background-size: 20px 20px; /* 方眼のサイズ調整 */
    }
    </style>
</head>
<body>
<div class="container">
    <!-- メインセクション -->
    <div class="card main">
        <p>処理内容</p>
        <form id="student_number_regist" action="ScoreRegistStudentSearchScreenDisplay.action" method="post" class="inline">
            <label>
                <input class="js-check form-check-input" type="checkbox" name="rs" value="1" onchange="studentNumberRegist(this)"> 学籍番号
            </label>
        </form>
        <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post" class="inline">
            <label>
                <input class="js-check form-check-input" type="checkbox" name="rs" value="1" checked onchange="classRegist(this)"> クラス指定
            </label>
        </form>
        <label>
            成績保守期限: <span id="formattedDate" class="inline"></span>
        </label>
    </div>

    <!-- 検索セクション -->
    <div class="card search">
        <form id="search_class" action="ScoreRegistClassRegistScreenDisplay.action" method="post">
            <span id="class" class="inline">クラス:
                <input type="text" name="class" value="" size="5" pattern="^[0-9]+$" form="search_class">
            </span>
            <span id="regist_year" class="inline">処理年度:
                <input type="text" name="regist_year" value="${regist_year}" size="5" readonly form="search_class">
            </span>
            <form id="subject_search" action="SubjectCodeSearch.action" method="post" class="inline">
                <span id="subject" class="inline">科目:
                    <input type="text" name="subject" value="${subject_cd }:${subject_name}" size="5" readonly>
                </span>
                <button class="btn btn-primary" id="search-button" name="subject">科目コード検索</button>
            </form>
            <span id="enrollment_date" class="inline">在籍者抽出日:
                <input type="date" name="enrollment_date" value="" size="5" form="search_class">
            </span>
            <input type="hidden" id="subject_cd" name="subject_cd" value="${subject_cd}" form="search_class">
            <input type="hidden" id="subject_name" name="subject_name" value="${subject_name}" form="search_class">
            <input type="submit" value="開始">
        </form>
    </div>
</div>

<script>
    // 日付を日本形式にフォーマット
    var receivedDate = '2024-09-06';
    var dateObj = new Date(receivedDate);

    function formatDate(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        return year + '年' + month + '月' + day + '日';
    }

    document.getElementById('formattedDate').textContent = formatDate(dateObj);

    // クラス指定チェック
    function classRegist(checkbox) {
        if (checkbox.checked) {
            checkbox.form.submit();
        }
    }

    // 学籍番号チェック
    function studentNumberRegist(checkbox) {
        if (checkbox.checked) {
            checkbox.form.submit();
        }
    }
</script>
</body>
</html>
