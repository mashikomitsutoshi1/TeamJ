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
    public Teacher login(String id, String password) {
        Teacher teacher = null;
        String sql = "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // retire_flgが1の場合は無効なユーザーとしてnullを返す
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
    // ログイン用メソッド2：退職者であるかをチェックする
    public Teacher getTeacherById(String id) {
        Teacher teacher = null;

        String sql = "SELECT * FROM TEACHER WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setAdminFlg(rs.getString("ADMIN_FLG"));
                    teacher.setRetireFlg(rs.getString("RETIRE_FLG"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }


    // 全教員のリストを取得するメソッド
    public List<Teacher> getTeacherList() {
        List<Teacher> teacherList = new ArrayList<>();
        //ID, NAME, RETIRE_FLG, ADMIN_FLG, MAINTENANCE_DEADLINEを得るためのSQL
        String sql = "SELECT ID, NAME, RETIRE_FLG, ADMIN_FLG, MAINTENANCE_DEADLINE FROM TEACHER ORDER BY NAME";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // 取得した教員情報をリストに追加
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString("ID"));
                teacher.setName(rs.getString("NAME"));
                teacher.setRetireFlg(rs.getString("RETIRE_FLG"));//退職者
                teacher.setAdminFlg(rs.getString("ADMIN_FLG"));//管理者権限
                teacher.setMaintenanceDeadline(rs.getDate("MAINTENANCE_DEADLINE")); // 保守期限
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherList;
    }

    // 全教員の退職フラグをリセットするメソッド
    public void resetAllRetireFlg() {
        String sql = "UPDATE TEACHER SET RETIRE_FLG = '0'";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int affectedRows = pstmt.executeUpdate();
            // リセットされた件数出力
            System.out.println("All retireFlg reset to 0 for " + affectedRows + " teachers.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 退職フラグを更新するメソッド
    public void updateRetireFlg(String teacherId, String retireFlg) {
        String sql = "UPDATE TEACHER SET RETIRE_FLG = ? WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, retireFlg);
            pstmt.setString(2, teacherId);
            int affectedRows = pstmt.executeUpdate();

            // 更新件数に応じたメッセージ出力
            if (affectedRows > 0) {
                System.out.println("Updated teacher with ID: " + teacherId + " to retireFlg: " + retireFlg);
            } else {
                System.out.println("No teacher found with ID: " + teacherId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 全教員の管理者フラグをリセットするメソッド
    public void resetAllAdminFlg() {
        String sql = "UPDATE TEACHER SET ADMIN_FLG = '0'";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int affectedRows = pstmt.executeUpdate();
            System.out.println("All adminFlg reset to 0 for " + affectedRows + " teachers.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 管理者フラグを更新するメソッド
    public void updateAdminFlg(String teacherId, String adminFlg) {
        String sql = "UPDATE TEACHER SET ADMIN_FLG = ? WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, adminFlg);
            pstmt.setString(2, teacherId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Updated teacher with ID: " + teacherId + " to adminFlg: " + adminFlg);
            } else {
                System.out.println("No teacher found with ID: " + teacherId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 保守期限を更新するメソッド
    public void updateMaintenanceDeadline(String teacherId, java.sql.Date maintenanceDeadline) {
        String sql = "UPDATE TEACHER SET MAINTENANCE_DEADLINE = ? WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, maintenanceDeadline);
            pstmt.setString(2, teacherId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
