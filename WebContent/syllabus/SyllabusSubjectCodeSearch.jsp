<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>科目一覧</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    <style>
        body {
            background-color: #e0f7ff; /* 薄い青色 */
            background-image: linear-gradient(0deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%),
                              linear-gradient(90deg, transparent 24%, white 25%, white 26%, transparent 27%, transparent 74%, white 75%, white 76%, transparent 77%);
            background-size: 20px 20px; /* 方眼のサイズ調整 */
        }

        table {
            background-color: white;
        }

        .btn-select {
            background-color: red;
            color: white;
            border: none;
        }

        .btn-select:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2 class="mb-3">科目一覧</h2>

        <p>入学年度: <strong>${admission_year}</strong></p>

        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>選択</th>
                    <th>科目コード</th>
                    <th>科目名</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${subject}">
                    <tr>
                        <td>
                            <form action="ScoreRegistClassSearchScreenDisplayExecute.action" method="post">
                                <button type="submit" class="btn btn-select btn-sm" name="subject_cd">選択</button>
                                <input type="hidden" name="cd" value="${p.subjectCd}" />
                                <input type="hidden" name="name" value="${p.subjectName}" />
                                <input type="hidden" name="admission_year" value="${admission_year}" />
                            </form>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="cd" value="${p.subjectCd}" readonly />
                        </td>
                        <td>
                            <input type="text" class="form-control" name="name" value="${p.subjectName}" readonly />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
