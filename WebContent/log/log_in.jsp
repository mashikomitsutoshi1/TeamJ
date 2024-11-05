<%-- ログインJSP --%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Login Screen</title>
    <style>
        /* 基本スタイル */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            /* ログインフォームデザイン */
            background-color: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 400px;
        }
        .login-container h2 {
            /* タイトルスタイル */
            margin-bottom: 30px;
            font-size: 28px;
            color: #333;
        }
        .login-container label {
            /* ラベルスタイル */
            display: block;
            margin-bottom: 10px;
            color: #333;
            font-size: 16px;
        }
        .login-container input {
            /* 入力フィールドスタイル */
            width: 100%;
            padding: 15px;
            margin-bottom: 25px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }
        .login-container button {
            /* ログインボタン */
            width: 100%;
            padding: 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 18px;
        }
        .login-container button:hover {
            /* ボタンホバー時のスタイル */
            background-color: #45a049;
        }
        .error-message {
            /* エラーメッセージ */
            color: red;
            margin-bottom: 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>ログイン</h2>

        <%-- エラーメッセージがあれば表示 --%>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && !errors.isEmpty()) {
        %>
            <div class="error-message">
                <% for (String error : errors) { %>
                    <p><%= error %></p>
                <% } %>
            </div>
        <% } %>

        <%-- ログインフォーム --%>
        <form action="LoginExecute.action" method="post">
            <label for="id">IDを入力してください。</label>
            <input type="text" id="id" name="id" placeholder="Enter your ID" required>

            <label for="password">PWを入力してください。</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>

            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
