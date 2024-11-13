<%-- ログアウトJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // セッションの無効化
    session.invalidate();
%>

<%
    // ページタイトルを設定
    request.setAttribute("pageTitle", "ログアウト");
%>

<div class="container">
    <h2>ログアウトしました</h2>
    <p>再度ログインするには、以下のリンクをクリックしてください。</p>
    <a href="log_in.jsp" class="button">ログインページに戻る</a>
</div>

</body>
</html>
