<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新完了</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
	<style>
        /* 背景デザイン */
        body {
            background-color: #e0f7ff; /* 薄い青色 */
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px; /* 方眼のサイズ調整 */
        }
    </style>

    <script type="text/javascript">
        // 終了ボタンを押した際の確認
        function confirmClose() {
            var logoutConfirmation = confirm("終了しますか？");
            if (logoutConfirmation) {
                // ログアウト処理のURLに遷移
                window.location.href = "<%= request.getContextPath() %>/log/logout.jsp";
            }
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-success">
            <p>退職者の更新が完了しました。</p>
        </div>

        <div class="d-flex justify-content-between">
            <a href="systemcontrol.jsp" class="btn btn-primary btn-lg">戻る</a>
            <!-- 終了確認 -->
            <input type="button" value="終了" class="btn btn-danger btn-lg" onclick="confirmClose();">
        </div>
        <br><br>

        <!-- menu.jspへのリンクボタン -->
        <div class="d-flex justify-content-center">
            <form action="../log/menu.jsp">
                <input type="submit" class="btn btn-info" value="トップ画面に戻る">
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
