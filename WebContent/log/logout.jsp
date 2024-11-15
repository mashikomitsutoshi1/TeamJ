<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // セッションの無効化
    session.invalidate();
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta charset="UTF-8">
    <title>ログアウト</title>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-body text-center">
                <h2 class="mb-3">ログアウトしました</h2>
                <p>再度ログインするには、以下のリンクをクリックしてください。</p>
                <a href="log_in.jsp" class="btn btn-primary mt-3">ログインページに戻る</a>
            </div>
        </div>
    </div>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
