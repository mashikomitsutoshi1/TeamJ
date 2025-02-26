<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">

    <!-- 日付用カレンダーUIの読み込み -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<style>
        /* 背景デザイン */
        body {
            background-color: #e0f7ff; /* 薄い青色 */
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px; /* 方眼のサイズ調整 */
        }
    </style>

    <script>
        $(document).ready(function() {
            // カレンダーUIの設定
            $("#processingYear, #outputDate").datepicker({ dateFormat: "yy-mm-dd" });
        });

        function confirmLogout() {
            return confirm("終了しますか？");
        }
    </script>
</head>
<body>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1><%= session.getAttribute("userName") %> 様</h1>
            <form action="logout.jsp" method="post" onsubmit="return confirmLogout();">
                <button type="submit" class="btn btn-danger btn-sm">終了</button>
            </form>
        </div>

        <%
            String adminFlg = (String) session.getAttribute("adminFlg");
        %>

        <!-- 管理教員機能と一般教員機能を横並びに配置 -->
        <div class="row mb-3">
            <!-- 管理教員機能 -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2 class="h5 mb-0">管理教員機能</h2>
                    </div>
                    <div class="card-body">
                        <% if ("1".equals(adminFlg)) { %>
                            <a href="〇〇.jsp" class="btn btn-primary btn-sm mb-1 w-100">成績評価保守期限管理</a>
                            <a href="〇〇.jsp" class="btn btn-primary btn-sm mb-1 w-100">シラバス保守権限管理</a>
                            <a href="../sys_con/systemcontrol.jsp" class="btn btn-primary btn-sm mb-1 w-100">ログインID保守</a>
                        <% } else { %>
                            <button class="btn btn-secondary btn-sm mb-1 w-100" disabled>成績評価保守期限管理</button>
                            <button class="btn btn-secondary btn-sm mb-1 w-100" disabled>シラバス保守権限管理</button>
                            <button class="btn btn-secondary btn-sm mb-1 w-100" disabled>ログインID保守</button>
                        <% } %>
                    </div>
                </div>
            </div>

            <!-- 一般教員機能 -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2 class="h5 mb-0">一般教員機能</h2>
                    </div>
                    <div class="card-body">
                        <a href="〇〇.jsp" class="btn btn-primary btn-sm mb-1 w-100">シラバス保守</a>
						<a class="btn btn-primary btn-sm mb-1 w-100" href="/TeamJ/report/GradeReport.action">成績評価表</a>
                        <a href="../scoreTypeClass/ScoreTypeSelect.action" class="btn btn-primary btn-sm mb-1 w-100">成績評価保守</a>
						<a class="btn btn-primary btn-sm mb-1 w-100" href="/TeamJ/report/StudentGradeReport.action">学生成績表</a>
                        <a href="〇〇.jsp" class="btn btn-primary btn-sm mb-1 w-100">出席率算出</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- 出欠席入力と出力基準日・処理選択を横並びに配置 -->
        <div class="row mb-3">
            <!-- 出欠席入力 -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="〇〇.jsp" method="post">
                            <h3 class="h6">出欠席入力</h3>
                            <div class="mb-2">
                                <label for="processingYear" class="form-label">処理年月:</label>
                                <input type="month" id="processingYear" name="processingYear" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label for="processingClass" class="form-label">処理クラス:</label>
                                <input type="text" id="processingClass" name="processingClass" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label for="admissionYear" class="form-label">処理入学年度:</label>
                                <input type="number" id="admissionYear" name="admissionYear" class="form-control form-control-sm" required>
                            </div>
                            <button type="submit" class="btn btn-success btn-sm w-100">出欠席入力開始</button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- 出力基準日と処理選択 -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="〇〇.jsp" method="post">
                            <h3 class="h6">出力基準日</h3>
                            <div class="mb-2">
                                <label for="outputDate" class="form-label">出力基準日:</label>
                                <input type="date" id="outputDate" name="outputDate" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label for="outputClass" class="form-label">出力クラス:</label>
                                <input type="text" id="outputClass" name="outputClass" class="form-control form-control-sm" required>
                            </div>

                            <h3 class="h6 mt-3">処理選択</h3>
                            <div class="d-flex mb-2">
                                <div class="form-check form-check-inline">
                                    <input type="radio" id="checklist" name="processChoice" value="checklist" class="form-check-input" required>
                                    <label for="checklist" class="form-check-label">出欠席チェックリスト</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" id="download" name="processChoice" value="download" class="form-check-input" required>
                                    <label for="download" class="form-check-label">名簿データのダウンロード</label>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-success btn-sm w-100">処理開始</button>
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
