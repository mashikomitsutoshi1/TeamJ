<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>出席率算出</title>
    <!-- カスタムスタイル -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    <style>
        body {
            background-color: #e0f7ff;/* 薄い青色 */
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px;/* 方眼のサイズ調整 */
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <form action="../attendance/AttendanceRateCalculation.action" method="post">
            <div class="btn-group">
                <label for="startMonth" class="form-label">開始年月:</label>
                <input type="month" id="startMonth" name="startMonth" class="form-control" required>

                <label for="endMonth" class="form-label">終了年月:</label>
                <input type="month" id="endMonth" name="endMonth" class="form-control" required>

                <label for="processingClass" class="form-label">クラス:</label>
                <input type="text" id="processingClass" name="processingClass" class="form-control" required value="113">
            <button type="submit" class="btn btn-primary">算出</button>
        </form>
            </div>

        <hr>

        <table class="table">
            <thead>
                <tr>
                    <th>年月</th>
                    <th>出席率</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Map<String, Object>> attendanceRates = (List<Map<String, Object>>) request.getAttribute("attendanceRates");
                    if (attendanceRates != null) {
                        for (Map<String, Object> record : attendanceRates) {
                            double attendanceRate = (double) record.get("attendance_rate");
                %>
                <tr>
                    <td><%= record.get("month") %></td>
                    <td><%= String.format("%.2f", attendanceRate) %> %</td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
