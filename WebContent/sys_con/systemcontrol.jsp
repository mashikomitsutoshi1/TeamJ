<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.TeacherDao" %>
<%@ page import="bean.Teacher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退職者管理</title>
    <script type="text/javascript">
        function confirmUpdate() {
            // 「更新してよろしいですか？」という確認ダイアログを表示
            return confirm("更新してよろしいですか？");
        }
    </script>
</head>
<body>
    <h2>退職者管理</h2>

    <%
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teacherList = teacherDao.getTeacherList();
    %>

    <form action="UpdateRetireStatus.action" method="POST" onsubmit="return confirmUpdate();">
        <table border="1">
            <tr>
                <th>氏名</th>
                <th>退職者</th>
            </tr>
            <% for (Teacher teacher : teacherList) { %>
    			<tr>
        			<td><%= teacher.getName() %></td>
        			<td>
            			<input type="checkbox" name="retireFlg_<%= teacher.getId() %>" value="1" <%= "1".equals(teacher.getRetireFlg()) ? "checked" : "" %> >
        			</td>
    			</tr>
			<% } %>
		</table>
        <br>
        <input type="submit" value="更新">
        <input type="button" value="終了" onclick="window.close();">
    </form>
</body>
</html>

