<%-- ログアウトJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // セッションの無効化
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログアウト</title>
</head>
<body>
    <h2>ログアウトしました</h2>
    <p>再度ログインするには、以下のリンクをクリックしてください。</p>
    <a href="log_in.jsp">ログインページに戻る</a>
</body>
</html>
