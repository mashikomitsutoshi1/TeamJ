<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ユーザーページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 14px;
            margin: 0;
            padding: 20px;
            background-color: #f2f2f2;
            color: #333333;
        }
        h1, h2, h3 {
            font-size: 18px;
            margin: 10px 0;
            color: #333333;
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
            cursor: pointer;
        }
        .button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
        .button:hover:enabled {
            background-color: #0056b3;
        }
        label, input {
            font-size: 14px;
            margin: 5px 0;
            color: #333333;
        }
        .section {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container {
            display: flex;
            justify-content: space-between;
            gap: 20px;
            flex-wrap: wrap;
        }
        form {
            width: 45%;
            display: flex;
            flex-direction: column;
            gap: 8px;
            margin-bottom: 10px;
            max-width: 300px;
        }
        input[type="text"], input[type="number"], input[type="month"], input[type="date"] {
            width: 100%;
            padding: 8px;
            background-color: #f9f9f9;
            color: #333333;
            border: 1px solid #cccccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <h1><%= request.getAttribute("userName") %> 様</h1>

    <!-- 管理教員機能 -->
    <div class="section">
        <h2>管理教員機能</h2>

        <button class="button" disabled>成績評価保守期限管理</button>
        <button class="button" disabled>シラバス保守権限管理</button>
        <button class="button" disabled>ログインID保守</button>
    </div>

    <!-- 一般教員機能 -->
    <div class="section">
        <h2>一般教員機能</h2>

        <a href="〇〇.jsp" class="button">シラバス保守</a>
        <a href="〇〇.jsp" class="button">成績評価表</a>
        <a href="〇〇.jsp" class="button">成績評価保守</a>
        <a href="〇〇.jsp" class="button">学生成績表</a>
        <a href="〇〇.jsp" class="button">出席率算出</a>
    </div>

    <div class="form-container">
        <form action="〇〇.jsp" method="post">
            <h3>出欠席入力</h3>
            <label for="processingYear">処理年月:</label>
            <input type="month" id="processingYear" name="processingYear" required>

            <label for="processingClass">処理クラス:</label>
            <input type="text" id="processingClass" name="processingClass" required>

            <label for="admissionYear">処理入学年度:</label>
            <input type="number" id="admissionYear" name="admissionYear" required>

            <button type="submit" class="button">出欠席入力開始</button>
        </form>

        <form action="〇〇.jsp" method="post">
            <h3>出力基準日</h3>
            <label for="outputDate">出力基準日:</label>
            <input type="date" id="outputDate" name="outputDate" required>

            <label for="outputClass">出力クラス:</label>
            <input type="text" id="outputClass" name="outputClass" required>

            <label>処理選択:</label>
            <label><input type="radio" name="processChoice" value="checklist" required>出欠席チェックリスト</label>
            <label><input type="radio" name="processChoice" value="download" required>名簿データのダウンロード</label>

            <button type="submit" class="button">処理開始</button>
        </form>
    </div>

    <form action="logout.jsp" method="post">
        <button type="submit" class="button">終了</button>
    </form>
</body>
</html>
