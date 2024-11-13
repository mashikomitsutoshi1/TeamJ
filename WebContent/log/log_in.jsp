<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <title>Login Screen</title>
    <style>
        /* スタイルの調整 */
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
            width: 400px;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px;
            color: #333;
        }

        .error-message {
            color: red;
            margin-bottom: 20px;
            font-size: 14px;
            text-align: center;
        }

        label {
            display: block;
            font-size: 16px;
            margin-bottom: 8px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 18px;
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
        }

        .checkbox-container {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .checkbox-container input {
            margin-right: 8px;
        }

        /* ボタンのリンク */
        a.button {
            background-color: #007BFF;
        }

        a.button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function togglePasswordVisibility() {
            const passwordField = document.getElementById("password");
            passwordField.type = passwordField.type === "password" ? "text" : "password";
        }
    </script>
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

            <%-- パスワード表示チェックボックス --%>
            <div class="checkbox-container">
                <input type="checkbox" onclick="togglePasswordVisibility()"> <label>パスワードを表示</label>
            </div>

            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
