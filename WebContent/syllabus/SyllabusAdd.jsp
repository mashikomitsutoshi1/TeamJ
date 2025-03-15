<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>シラバス追加</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        body {
            background-color: #e0f7ff;
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <!-- ヘッダー部分 -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>シラバス追加</h1>
        </div>

        <form action="SyllabusAddExecute.action" method="post">
            <div class="card">
                <div class="card-body">
                    <div class="row mb-3">
                        <!-- 科目コード -->
                        <div class="col-md-6">
                            <label for="subject_code" class="form-label">科目コード</label>
                            <input type="text" id="subject_code" name="subject_code" class="form-control" value="${subject_cd}">
                        </div>
                        <!-- 科目名 -->
                        <div class="col-md-6">
                            <label for="subject_name" class="form-label">科目名</label>
                            <input type="text" id="subject_name" name="subject_name" class="form-control" value="${subject_name}" readonly>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <!-- 必須フラグ -->
                        <div class="col-md-3">
                            <label for="required_flg" class="form-label">必須</label>
                            <input type="text" id="required_flg" name="required_flg" class="form-control">
                        </div>
                        <!-- 選択フラグ -->
                        <div class="col-md-3">
                            <label for="select_flg" class="form-label">選択</label>
                            <input type="text" id="select_flg" name="select_flg" class="form-control">
                        </div>
                        <!-- 履修時間 -->
                        <div class="col-md-3">
                            <label for="course_time" class="form-label">履修時間</label>
                            <input type="text" id="course_time" name="course_time" class="form-control">
                        </div>
                        <!-- 単位数 -->
                        <div class="col-md-3">
                            <label for="credit" class="form-label">単位</label>
                            <input type="text" id="credit" name="credit" class="form-control">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <!-- 学年 -->
                        <div class="col-md-6">
                            <label for="grade" class="form-label">学年</label>
                            <input type="text" id="grade" name="grade" class="form-control">
                        </div>
                        <!-- SVKAMOKU -->
                        <div class="col-md-6">
                            <label for="svkamoku" class="form-label">SVKAMOKU</label>
                            <input type="text" id="svkamoku" name="svkamoku" class="form-control">
                        </div>
                    </div>

                    <input type="hidden" name="admission_year" value="${admission_year}">
                    <div class="mb-3">入学年度: ${admission_year}</div>

                    <!-- ボタン -->
                    <div class="d-flex justify-content-between">
                        <button type="submit" name="search" class="btn btn-secondary">科目コード検索</button>
                        <button type="submit" name="cancel" class="btn btn-danger">キャンセル</button>
                        <button type="submit" name="exec" class="btn btn-primary">設定</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
