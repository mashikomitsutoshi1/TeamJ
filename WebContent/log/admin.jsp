<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .button {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 15px;
            font-size: 16px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #0056b3;
        }
        label {
            display: block;
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <h1><%= request.getAttribute("userName") %> </h1>

    <h2>管理機能</h2>

    <a href="〇〇.jsp" class="button">成績評価保守期限管理</a>
    <a href="〇〇.jsp" class="button">シラバス保守権限管理</a>
    <a href="../sys_con/systemcontrol.jsp" class="button">ログインID保守</a>
    <a href="〇〇.jsp" class="button">シラバス保守</a>
    <a href="〇〇.jsp" class="button">成績評価表</a>
    <a href="〇〇.jsp" class="button">成績評価保守</a>
    <a href="〇〇.jsp" class="button">学生成績表</a>
    <a href="〇〇.jsp" class="button">出席率算出</a>

    <h3>出欠席入力</h3>
    <form action="〇〇.jsp" method="post">
        <label for="processingYear">処理年月:</label>
        <input type="month" id="processingYear" name="processingYear" required>

        <label for="processingClass">処理クラス:</label>
        <input type="text" id="processingClass" name="processingClass" required>

        <label for="admissionYear">処理入学年度:</label>
        <input type="number" id="admissionYear" name="admissionYear" required>

        <button type="submit" class="button">出欠席入力開始</button>
    </form>

    <h3>出力基準日</h3>
    <form action="〇〇.jsp" method="post">
        <label for="outputDate">出力基準日:</label>
        <input type="date" id="outputDate" name="outputDate" required>

        <label for="outputClass">出力クラス:</label>
        <input type="text" id="outputClass" name="outputClass" required>

        <label>処理選択:</label>
        <label><input type="radio" name="processChoice" value="checklist" required>出欠席チェックリスト</label>
        <label><input type="radio" name="processChoice" value="download" required>名簿データのダウンロード</label>

        <button type="submit" class="button">処理開始</button>
    </form>

    <form action="logout.jsp" method="post">
        <button type="submit" class="button">終了</button>
    </form>
</body>
</html>
