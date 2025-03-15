<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Syllabus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>コピー元シラバス</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
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
            <h1 class="mb-4">コピー元シラバス</h1>
            <div>
                <% List<Syllabus> list = (List<Syllabus>) request.getAttribute("list"); %>
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
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
                        <% int i = 0; %>
                        <c:forEach var="p" items="${list}">
                            <form action="SyllabusCopyExecute.action" method="post">
                                <tr>
                                    <td>
                                        <button type="submit" name="select" value="<%= i %>" class="btn btn-primary btn-sm">選択</button>
                                        <input type="hidden" name="saki_admission_year" value="${saki_admission_year}">
                                        <input type="hidden" name="admission_year_<%= i %>" value="${p.admission_year}">
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
                            </form>
                            <% i++; %>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
