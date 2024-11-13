<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.TeacherDao" %>
<%@ page import="bean.Teacher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退職者管理</title>

    <!-- 日付用カレンダーUIの読み込み -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            // 各保守期限フィールドカレンダー設定
            $("input[name^='maintenanceDeadline_']").datepicker({
                dateFormat: "yy-mm-dd" // 年-月-日形式
            });

            // 一括入力用カレンダー設定
            $("#bulkMaintenanceDeadline").datepicker({
                dateFormat: "yy-mm-dd"
            });
        });

        function applyBulkDate() {
            // 一括入力で保守期限フィールドに設定
            var selectedDate = $("#bulkMaintenanceDeadline").val();
            if (selectedDate) {
                $("input[name^='maintenanceDeadline_']").val(selectedDate);
            } else {
                alert("一括入力の日付を選択してください。");
            }
        }

        function confirmUpdate() {
            // 保守期限の入力チェック
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

                // 月の範囲チェック
                if (month < 1 || month > 12) {
                    alert("保守期限が正しい日付ではありません");
                    return false;
                }

                // 月ごとの日数確認
                var daysInMonth = new Date(year, month, 0).getDate(); // 該当月の日数
                if (day < 1 || day > daysInMonth) {
                    alert("保守期限が正しい日付ではありません");
                    return false;
                }
            }
            // 確認ダイアログ
            return confirm("更新してよろしいですか？");
        }
        </script>
</head>
<body>
    <h2>退職者管理</h2>

    <!-- 一括入力フィールド -->
    <div style="text-align: right; margin-bottom: 10px;">
        <label for="bulkMaintenanceDeadline">保守期限一括入力:</label>
        <input type="text" id="bulkMaintenanceDeadline" placeholder="保守期限を一括設定">
        <button type="button" onclick="applyBulkDate()">設定</button>
    </div>

    <%
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teacherList = teacherDao.getTeacherList();
    %>

    <form action="UpdateRetireStatus.action" method="POST" onsubmit="return confirmUpdate();">
        <table border="1">
            <tr>
                <th>氏名</th>
                <th>管理者</th>
                <th>保守期限</th>
                <th>退職者</th>
            </tr>
            <% for (Teacher teacher : teacherList) { %>
                <tr>
                    <td><%= teacher.getName() %></td>
                    <td>
                        <!-- 管理者フラグのチェックボックス -->
                        <input type="checkbox" name="adminFlg_<%= teacher.getId() %>" value="1" <%= "1".equals(teacher.getAdminFlg()) ? "checked" : "" %> >
                    </td>
                    <td>
                        <!-- 保守期限の入力フィールド -->
                        <input type="text" name="maintenanceDeadline_<%= teacher.getId() %>" value="<%= teacher.getMaintenanceDeadline() != null ? teacher.getMaintenanceDeadline().toString() : "" %>">
                    </td>
                    <td>
                        <!-- 退職者フラグのチェックボックス -->
                        <input type="checkbox" name="retireFlg_<%= teacher.getId() %>" value="1" <%= "1".equals(teacher.getRetireFlg()) ? "checked" : "" %> >
                    </td>
                </tr>
            <% } %>
        </table>
        <br>
        <input type="submit" value="更新">
        <input type="button" value="終了" onclick="window.close();">
    </form>

    <!-- admin.jspへのリンクボタン -->
    <form action="../log/admin.jsp">
        <input type="submit" value="トップ画面に戻る">
    </form>
</body>
</html>
