<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績入力</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
        <div class="main card p-3 mb-4">
            <h5 class="card-title mb-3">処理内容</h5>
            <div class="d-flex align-items-center">
                <form id="student_number_regist" action="scoreRegistStudentSearch.jsp" method="post" class="me-3">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="rs" value="1" id="studentCheckbox" onchange="studentNumberRegist(this)">
                        <label class="form-check-label" for="studentCheckbox">学籍番号</label>
                    </div>
                </form>
                <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="rs" value="1" id="classCheckbox" onchange="classRegist(this)">
                        <label class="form-check-label" for="classCheckbox">クラス指定</label>
                    </div>
                </form>
                <div class="ms-4">
                    <span>成績保守期限: </span>
                    <span id="formattedDate" class="badge bg-info text-dark"></span>
                </div>
            </div>
        </div>
    </div>

    <script>
        var receivedDate = '2024-09-06';
        var dateObj = new Date(receivedDate);

        function formatDate(date) {
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            return year + '年' + month + '月' + day + '日';
        }

        var formattedDate = formatDate(dateObj);
        document.getElementById('formattedDate').textContent = formattedDate;

        function classRegist(checkbox) {
            if (checkbox.checked) {
                checkbox.form.submit();
            }
        }

        function studentNumberRegist(checkbox) {
            if (checkbox.checked) {
                checkbox.form.submit();
            }
        }
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
