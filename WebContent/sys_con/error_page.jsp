<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
</head>
<body>
    <h2>エラーが発生しました</h2>

    <p style="color: red;">
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.print(errorMessage);
            } else {
                out.print("不明なエラーが発生しました。");
            }
        %>
    </p>

    <!-- 戻るボタン -->
    <form>
        <input type="button" value="戻る" onclick="history.back();">
    </form>
</body>
</html>
