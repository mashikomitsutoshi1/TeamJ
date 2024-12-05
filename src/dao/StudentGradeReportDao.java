package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.StudentGradeReport;

public class StudentGradeReportDao extends Dao {

    // 成績評価を返すメソッド
    private String getGrading(int point) {
        if (point >= 90) return "秀";
        if (point >= 80) return "優";
        if (point >= 70) return "良";
        if (point >= 60) return "可";
        return "不可";
    }

    // 学生情報を設定するメソッド
    private StudentGradeReport createStudentGradeReport(ResultSet rs) throws Exception {
        StudentGradeReport report = new StudentGradeReport();
        report.setAdmissionYear(rs.getInt("admission_year"));
        report.setStudentNo(rs.getString("student_no"));
        report.setStudentName(rs.getString("student_name"));
        report.setDepartmentName(rs.getString("department_name"));
        report.setCourseName(rs.getString("course_name"));
        report.setBirthdayYear(1974);
        report.setBirthdayMonth(4);
        report.setBirthdayDay(13);  // 固定値
        report.setGraduationYear(rs.getInt("admission_year") + 2);  // 入学年度 + 2年

        return report;
    }

    // 成績情報を追加するメソッド
    private void addSubjectToReport(StudentGradeReport report, ResultSet rs) throws Exception {
        String grading = getGrading(rs.getInt("point"));
        report.addSubject(
                rs.getString("subject_name"),
                rs.getInt("credit"),
                grading
        );
    }

    public List<StudentGradeReport> selectAll() throws Exception {
        List<StudentGradeReport> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "SELECT * FROM SCORE " +
                "INNER JOIN SYLLABUSDETAIL " +
                "ON SCORE.ADMISSION_YEAR = SYLLABUSDETAIL.ADMISSION_YEAR " +
                "AND SCORE.SUBJECT_CODE = SYLLABUSDETAIL.SUBJECT_CODE " +
                "INNER JOIN STUDENT " +
                "ON SCORE.ADMISSION_YEAR = STUDENT.ADMISSION_YEAR " +
                "AND SCORE.STUDENT_NO = STUDENT.STUDENT_NO " +
                "INNER JOIN SYLLABUS " +
                "ON SCORE.ADMISSION_YEAR = SYLLABUS.ADMISSION_YEAR " +
                "INNER JOIN SUBJECT " +
                "ON SYLLABUSDETAIL.SUBJECT_CODE = SUBJECT.SUBJECT_CODE " +
                "ORDER BY STUDENT.ADMISSION_YEAR, STUDENT.STUDENT_NO, SUBJECT.SUBJECT_CODE"
        );

        ResultSet rs = st.executeQuery();
        if (!rs.next()) {
            st.close();
            con.close();
            return list; // 結果がない場合は空リストを返す
        }

        // 最初の学生情報を初期化
        int wk_admission_year = rs.getInt("admission_year");
        String wk_student_no = rs.getString("student_no");
        StudentGradeReport report = createStudentGradeReport(rs);
        addSubjectToReport(report, rs);

        // ループして残りのデータを処理
        while (rs.next()) {
            if (wk_admission_year == rs.getInt("admission_year") && wk_student_no.equals(rs.getString("student_no"))) {
                // 同じ学生の場合は科目情報を追加
                addSubjectToReport(report, rs);
            } else {
                // 別の学生の場合は現在のレポートをリストに追加して新しいレポートを作成
                list.add(report);
                report = createStudentGradeReport(rs);
                addSubjectToReport(report, rs);
                wk_admission_year = rs.getInt("admission_year");
                wk_student_no = rs.getString("student_no");
            }
        }

        // 最後の学生のレポートを追加
        list.add(report);

        st.close();
        con.close();
        return list;
    }

    public List<StudentGradeReport> selectClass(String admission_year, String class_no) throws Exception {
        List<StudentGradeReport> list = new ArrayList<>();
        Connection con = getConnection();
        int admission_year_int = Integer.parseInt(admission_year);

        PreparedStatement st = con.prepareStatement(
                "SELECT * FROM SCORE " +
                "INNER JOIN SYLLABUSDETAIL " +
                "ON SCORE.ADMISSION_YEAR = SYLLABUSDETAIL.ADMISSION_YEAR " +
                "AND SCORE.SUBJECT_CODE = SYLLABUSDETAIL.SUBJECT_CODE " +
                "INNER JOIN STUDENT " +
                "ON SCORE.ADMISSION_YEAR = STUDENT.ADMISSION_YEAR " +
                "AND SCORE.STUDENT_NO = STUDENT.STUDENT_NO " +
                "INNER JOIN SYLLABUS " +
                "ON SCORE.ADMISSION_YEAR = SYLLABUS.ADMISSION_YEAR " +
                "INNER JOIN SUBJECT " +
                "ON SYLLABUSDETAIL.SUBJECT_CODE = SUBJECT.SUBJECT_CODE " +
                "WHERE STUDENT.ADMISSION_YEAR = ? AND STUDENT.CLASS_NO = ? " +
                "ORDER BY STUDENT.ADMISSION_YEAR, STUDENT.STUDENT_NO, SUBJECT.SUBJECT_CODE"
        );

        st.setInt(1, admission_year_int);
        st.setString(2, class_no);

        ResultSet rs = st.executeQuery();
        if (!rs.next()) {
            st.close();
            con.close();
            return list; // 結果がない場合は空リストを返す
        }

        // 最初の学生情報を初期化
        int wk_admission_year = rs.getInt("admission_year");
        String wk_student_no = rs.getString("student_no");
        StudentGradeReport report = createStudentGradeReport(rs);
        addSubjectToReport(report, rs);

        // ループして残りのデータを処理
        while (rs.next()) {
            if (wk_admission_year == rs.getInt("admission_year") && wk_student_no.equals(rs.getString("student_no"))) {
                // 同じ学生の場合は科目情報を追加
                addSubjectToReport(report, rs);
            } else {
                // 別の学生の場合は現在のレポートをリストに追加して新しいレポートを作成
                list.add(report);
                report = createStudentGradeReport(rs);
                addSubjectToReport(report, rs);
                wk_admission_year = rs.getInt("admission_year");
                wk_student_no = rs.getString("student_no");
            }
        }

        // 最後の学生のレポートを追加
        list.add(report);

        st.close();
        con.close();
        return list;
    }
}
