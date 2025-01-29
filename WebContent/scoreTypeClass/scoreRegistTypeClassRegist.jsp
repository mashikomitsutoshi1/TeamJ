<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 共通スタイル -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">

        <style>
    	/* 背景デザイン */
        body {
            background-color: #e0f7ff; /* 薄い青色 */
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px; /* 方眼のサイズ調整 */
        }
        .form-control-inline {
            display: inline-block;
            width: auto;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
    </style>

    <!-- 日付表示スクリプト -->
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const formattedDate = new Date().toLocaleDateString('ja-JP', {
                year: 'numeric', month: '2-digit', day: '2-digit'
            });
            document.getElementById('formattedDate').textContent = formattedDate;
        });
    </script>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">成績登録</h2>

    <!-- メインフォーム -->
    <div class="card p-3 mb-4">
        <form id="student_number_regist" action="ScoreRegistStudentSearchScreenDisplay.action" method="post" class="d-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="rs" value="1" onchange="studentNumberRegist(this)"> 学籍番号
            </label>
        </form>
        <form id="class_regist" action="ScoreRegistClassSearchScreenDisplay.action" method="post" class="d-inline">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="rs" value="1" checked> クラス指定
            </label>
        </form>
        <span class="ms-3">成績保守期限: <span id="formattedDate"></span></span>
    </div>

    <!-- 検索フォーム -->
    <div class="card p-3 mb-4">
        <form id="search_class" action="ScoreRegistClassRegistScreenDisplay.action" method="post">
            <div class="mb-3">
                <label for="class" class="form-label">クラス:</label>
                <input type="text" id="class" name="class" class="form-control-inline form-control" value="${class_num}" size="5" pattern="^[0-9]+$">
            </div>
            <div class="mb-3">
                <label for="regist_year" class="form-label">処理年度:</label>
                <input type="text" id="regist_year" name="regist_year" class="form-control-inline form-control" value="${regist_year}" size="5" readonly>
            </div>
            <div class="mb-3">
                <label for="subject" class="form-label">科目:</label>
                <input type="text" id="subject" name="subject" class="form-control-inline form-control" value="${subject_cd}:${subject_name}" size="5" readonly>
                <button type="submit" class="btn btn-primary">科目コード検索</button>
            </div>
            <div class="mb-3">
                <label for="enrollment_date" class="form-label">在籍者抽出日:</label>
                <input type="date" id="enrollment_date" name="enrollment_date" class="form-control-inline form-control" value="${enrollment_date}">
            </div>
            <button type="submit" class="btn btn-primary">開始</button>
        </form>
    </div>

    <!-- 登録フォーム -->
    <div class="card p-3">
        <form action="ScoreRegistClassRegistExecute.action" method="get">
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>学年</th>
                        <th>月</th>
                        <th>学生番号</th>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th>氏名</th>
                        <th>得点</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${list}">
                        <tr>
                            <td>${test.studyYear}</td>
                            <td><input type="text" class="form-control form-control-inline" name="month_${test.studentNo}" value="${test.month}"></td>
                            <td>${test.studentNo}</td>
                            <td>${test.subjectCode}</td>
                            <td>${test.subjectName}</td>
                            <td>${test.studentName}</td>
                            <td><input type="text" class="form-control form-control-inline" name="point_${test.studentNo}" value="${test.point}"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" name="excel" value="1">
                <label class="form-check-label">Excel出力</label>
            </div>
            <input type="hidden" name="subject_cd" value="${subject_cd}">
            <input type="hidden" name="enrollment_date" value="${enrollment_date}">
            <button type="submit" class="btn btn-primary">登録</button>
        </form>
    </div>
</div>
</body>
</html>
