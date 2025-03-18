<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Syllabus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ja">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>シラバス一覧</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="../common/style.css">
    <script type="text/javascript">
        function message() {
            return alert("表示されているシラバス一覧をエクセルで出力します");
        }
    </script>
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
        <h1 class="mb-4 text-primary">シラバス一覧</h1>

        <% List<Syllabus> list = (List)request.getAttribute("list"); %>
        <% if(list == null){ %>
            <div class="alert alert-info">シラバスのデータがありません。</div>
        <% } else { %>

            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th></th>
                        <th>年度</th>
                        <th>科目数</th>
                        <th>課程区分</th>
                        <th>課程名</th>
                        <th>科名</th>
                        <th>コース名</th>
                        <th>コース名内訳名</th>
                        <th>修業年月</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${list}">
                        <tr>
                            <td>
                                <form action="SyllabusDetailMaintenance.action" method="post">
                                    <button type="submit" name="select" class="btn btn-primary btn-sm">選択</button>
                                    <input type="hidden" name="admission_year" value="${p.admission_year}">
                                </form>
                            </td>
                            <td>${p.admission_year}</td>
                            <td>${p.subject_count}</td>
                            <td>${p.curriculum_category}</td>
                            <td>${p.curriculum_name}</td>
                            <td>${p.department_name}</td>
                            <td>${p.course_name}</td>
                            <td>${p.course_name_breakdown}</td>
                            <td>${p.study_year}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        <% } %>

        <form action="SyllabusMaintenanceExecute.action" method="post" onsubmit="return message();">
            <div class="mb-3">
                <button type="submit" name="allSyllbus" class="btn btn-primary">全コースのシラバス出力</button>
            </div>
        </form>

		<form action="../log/menu.jsp" method="post" onsubmit="return confirm('終了しますか？');">
		    <button type="submit" class="btn btn-danger btn-sm">終了</button>
		</form>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
