<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.TeacherDao" %>
<%@ page import="bean.Teacher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教員管理</title>

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
    <!-- 日付用カレンダーUIの読み込み -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

    <script type="text/javascript">
        // 変更フラグの初期化
        var isModified = false;

        $(document).ready(function() {
            // カレンダーUIの設定
            $("input[name^='maintenanceDeadline_']").datepicker({ dateFormat: "yy-mm-dd" });
            $("#bulkMaintenanceDeadline").datepicker({ dateFormat: "yy-mm-dd" });

            // 各入力フィールドで変更があった場合、フラグを true に設定
            $("input[type='checkbox'], input[type='text']").on("change", function() {
                isModified = true;
            });
        });

        // 一括入力ボタンの処理
        function applyBulkDate() {
            var selectedDate = $("#bulkMaintenanceDeadline").val();
            if (selectedDate) {
                $("input[name^='maintenanceDeadline_']").val(selectedDate);
                isModified = true;
            } else {
                alert("一括入力の日付を選択してください。");
            }
        }

        // 更新ボタンを押す前の日付チェック
        function confirmUpdate() {
            var datePattern = /^\d{4}-(\d{2})-(\d{2})$/;
            var inputs = document.querySelectorAll("input[name^='maintenanceDeadline_']");
            for (var i = 0; i < inputs.length; i++) {
                var dateValue = inputs[i].value;
                if (!dateValue) {
                    alert("保守期限は必須です");
                    return false;
                }
                var match = dateValue.match(datePattern);
                if (!match) {
                    alert("保守期限を正しく入力してください");
                    return false;
                }
                var year = parseInt(dateValue.substring(0, 4), 10);
                var month = parseInt(match[1], 10);
                var day = parseInt(match[2], 10);
                if (month < 1 || month > 12) {
                    alert("保守期限が正しい日付ではありません");
                    return false;
                }
                var daysInMonth = new Date(year, month, 0).getDate();
                if (day < 1 || day > daysInMonth) {
                    alert("保守期限が正しい日付ではありません");
                    return false;
                }
            }
            return confirm("更新してよろしいですか？");
        }

        // 終了ボタンの確認
        function confirmClose() {
            if (isModified) {
                if (confirm("更新ボタンが押されていません。終了しますか？")) {
                    window.close(); window.location.href = "<%= request.getContextPath() %>/log/logout.jsp";
                }
            } else {
                var logoutConfirmation = confirm("終了しますか？");
                if (logoutConfirmation) {
                    window.location.href = "<%= request.getContextPath() %>/log/logout.jsp";
                }
            }
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">退職者管理</h2>

    <!-- 一括入力フィールド -->
    <div class="d-flex justify-content-end mb-3">
        <label for="bulkMaintenanceDeadline" class="me-2">保守期限一括入力:</label>
        <input type="text"
               id="bulkMaintenanceDeadline"
               class="form-control me-2"
               style="width: 200px;"
               placeholder="保守期限を一括設定">
        <button type="button"
                class="btn btn-primary"
                onclick="applyBulkDate()">設定</button>
    </div>

    <%
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teacherList = teacherDao.getTeacherList();
    %>

    <form action="UpdateRetireStatus.action" method="POST" onsubmit="return confirmUpdate();">
        <table class="table table-bordered table-striped">
            <thead class="table-light">
                <tr>
                    <th>氏名</th>
                    <th>管理者</th>
                    <th>保守期限</th>
                    <th>退職者</th>
                </tr>
            </thead>
            <tbody>
                <% for (Teacher teacher : teacherList) { %>
                    <tr>
                        <td><%= teacher.getName() %></td>
                        <td>
                            <input type="checkbox"
                                   name="adminFlg_<%= teacher.getId() %>"
                                   value="1"
                                   <%= "1".equals(teacher.getAdminFlg()) ? "checked" : "" %> >
                        </td>
                        <td>
                            <input type="text"
                                   name="maintenanceDeadline_<%= teacher.getId() %>"
                                   class="form-control"
                                   value="<%= teacher.getMaintenanceDeadline() != null ? teacher.getMaintenanceDeadline().toString() : "" %>">
                        </td>
                        <td>
                            <input type="checkbox"
                                   name="retireFlg_<%= teacher.getId() %>"
                                   value="1"
                                   <%= "1".equals(teacher.getRetireFlg()) ? "checked" : "" %> >
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <div class="d-flex justify-content-end mt-3">
            <input type="submit" class="btn btn-success me-2" value="更新">
            <input type="button" class="btn btn-danger btn-sm" value="終了" onclick="confirmClose();">
        </div>
    </form>

    <!-- menu.jspへのリンクボタン -->
    <div class="d-flex justify-content-center mt-4">
        <form action="../log/menu.jsp">
            <input type="submit" class="btn btn-primary" value="トップ画面に戻る">
        </form>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
