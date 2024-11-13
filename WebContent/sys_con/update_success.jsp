<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新完了</title>
    <script type="text/javascript">
        function closeWindow() {
            window.close(); // ウィンドウを閉じる
        }
    </script>
</head>
<body>
    <h1>更新しました</h1>
    <p>退職者の更新が完了しました。</p>
    <a href="systemcontrol.jsp">ログインID保守画面に戻る</a>
    <br><br>
    <input type="button" value="終了" onclick="closeWindow();">
    <br><br>
    <!-- admin.jspへのリンクボタン -->
    <form action="../log/admin.jsp">
        <input type="submit" value="トップ画面に戻る">
    </form>
    
</body>
</html>
