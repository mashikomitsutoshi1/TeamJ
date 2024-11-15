<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <title>Login Screen</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- styles CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">

    <script>
        function togglePasswordVisibility() {
            const passwordField = document.getElementById("password");
            passwordField.type = passwordField.type === "password" ? "text" : "password";
        }
    </script>
</head>
<body class="bg-light">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4 mt-5">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h2 class="card-title text-center mb-4">ログイン</h2>

                        <%-- エラーメッセージがあれば表示 --%>
                        <%
                            List<String> errors = (List<String>) request.getAttribute("errors");
                            if (errors != null && !errors.isEmpty()) {
                        %>
                            <div class="alert alert-danger">
                                <% for (String error : errors) { %>
                                    <p class="mb-1"><%= error %></p>
                                <% } %>
                            </div>
                        <% } %>

                        <%-- ログインフォーム --%>
                        <form action="LoginExecute.action" method="post">
                            <div class="mb-3">
                                <label for="id" class="form-label">IDを入力してください。</label>
                                <input type="text" id="id" name="id" class="form-control" placeholder="Enter your ID" required>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">PWを入力してください。</label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required>
                            </div>

                            <%-- パスワード表示チェックボックス --%>
                            <div class="form-check mb-3">
                                <input class="form-check-input" type="checkbox" onclick="togglePasswordVisibility()" id="showPassword">
                                <label class="form-check-label" for="showPassword">パスワードを表示</label>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

