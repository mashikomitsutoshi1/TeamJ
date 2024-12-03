package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;

public class TeacherDao extends Dao {

    // ログイン用メソッド：IDとパスワードで教員を検索
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if ("1".equals(rs.getString("RETIRE_FLG"))) {
                    return null;
                }
                teacher = new Teacher();
                teacher.setId(rs.getString("ID"));
                teacher.setPassword(rs.getString("PASSWORD"));
                teacher.setName(rs.getString("NAME"));
                teacher.setAdminFlg(rs.getString("ADMIN_FLG"));
                teacher.setMaintenanceDeadline(rs.getDate("MAINTENANCE_DEADLINE"));
                teacher.setRetireFlg(rs.getString("RETIRE_FLG"));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return teacher;
    }

    public Teacher getTeacherById(String id) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT * FROM TEACHER WHERE ID = ?";

        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getString("ID"));
                teacher.setName(rs.getString("NAME"));
                teacher.setAdminFlg(rs.getString("ADMIN_FLG"));
                teacher.setRetireFlg(rs.getString("RETIRE_FLG"));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return teacher;
    }

    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT ID, NAME, RETIRE_FLG, ADMIN_FLG, MAINTENANCE_DEADLINE FROM TEACHER ORDER BY NAME";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString("ID"));
                teacher.setName(rs.getString("NAME"));
                teacher.setRetireFlg(rs.getString("RETIRE_FLG"));
                teacher.setAdminFlg(rs.getString("ADMIN_FLG"));
                teacher.setMaintenanceDeadline(rs.getDate("MAINTENANCE_DEADLINE"));
                teacherList.add(teacher);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return teacherList;
    }

    public void resetAllRetireFlg() throws Exception {
        String sql = "UPDATE TEACHER SET RETIRE_FLG = '0'";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("All retireFlg reset to 0 for " + affectedRows + " teachers.");
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void updateRetireFlg(String teacherId, String retireFlg) throws Exception {
        String sql = "UPDATE TEACHER SET RETIRE_FLG = ? WHERE ID = ?";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, retireFlg);
            pstmt.setString(2, teacherId);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated teacher with ID: " + teacherId + " to retireFlg: " + retireFlg);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void resetAllAdminFlg() throws Exception {
        String sql = "UPDATE TEACHER SET ADMIN_FLG = '0'";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("All adminFlg reset to 0 for " + affectedRows + " teachers.");
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void updateAdminFlg(String teacherId, String adminFlg) throws Exception {
        String sql = "UPDATE TEACHER SET ADMIN_FLG = ? WHERE ID = ?";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminFlg);
            pstmt.setString(2, teacherId);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated teacher with ID: " + teacherId + " to adminFlg: " + adminFlg);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void updateMaintenanceDeadline(String teacherId, java.sql.Date maintenanceDeadline) throws Exception {
        String sql = "UPDATE TEACHER SET MAINTENANCE_DEADLINE = ? WHERE ID = ?";

        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, maintenanceDeadline);
            pstmt.setString(2, teacherId);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
