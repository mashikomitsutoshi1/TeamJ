package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.GradeReport;

public class GradeReportDao extends Dao {

    public List<GradeReport> selectAll() throws Exception {
        return getGradeReportList(null, null);
    }

    public List<GradeReport> selectClass(String admission_year, String class_no) throws Exception {
        return getGradeReportList(admission_year, class_no);
    }

    private List<GradeReport> getGradeReportList(String admission_year, String class_no) throws Exception {
        List<GradeReport> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT * FROM STUDENT " +
                     "INNER JOIN SYLLABUSDETAIL ON STUDENT.ADMISSION_YEAR = SYLLABUSDETAIL.ADMISSION_YEAR " +
                     "INNER JOIN SUBJECT ON SYLLABUSDETAIL.SUBJECT_CODE = SUBJECT.SUBJECT_CODE " +
                     "LEFT OUTER JOIN SCORE ON STUDENT.ADMISSION_YEAR = SCORE.ADMISSION_YEAR " +
                     "AND STUDENT.STUDENT_NO = SCORE.STUDENT_NO " +
                     "AND SUBJECT.SUBJECT_CODE = SCORE.SUBJECT_CODE ";

        if (admission_year != null && class_no != null) {
            sql += "WHERE STUDENT.ADMISSION_YEAR = ? AND STUDENT.CLASS_NO = ? ";
        }

        sql += "ORDER BY STUDENT.ADMISSION_YEAR, STUDENT.STUDENT_NO, SUBJECT.SUBJECT_CODE";

        PreparedStatement st = con.prepareStatement(sql);
        if (admission_year != null && class_no != null) {
            st.setInt(1, Integer.parseInt(admission_year));
            st.setString(2, class_no);
        }

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            int wk_admission_year = rs.getInt("admission_year");
            String wk_student_no = rs.getString("student_no");
            GradeReport report = initializeReport(rs);

            do {
                if (wk_admission_year == rs.getInt("admission_year") &&
                    wk_student_no.equals(rs.getString("student_no"))) {
                    report.addSubject(rs.getString("subject_name"), rs.getInt("credit"), getGrading(rs));
                } else {
                    list.add(report);
                    report = initializeReport(rs);
                    wk_admission_year = rs.getInt("admission_year");
                    wk_student_no = rs.getString("student_no");
                }
            } while (rs.next());

            list.add(report);
        }

        st.close();
        con.close();
        return list;
    }

    private GradeReport initializeReport(ResultSet rs) throws Exception {
        GradeReport report = new GradeReport();
        report.setAdmissionYear(rs.getInt("admission_year"));
        report.setStudentNo(rs.getString("student_no"));
        report.setStudentName(rs.getString("student_name"));
        report.addSubject(rs.getString("subject_name"), rs.getInt("credit"), getGrading(rs));
        return report;
    }

    private String getGrading(ResultSet rs) throws Exception {
        int point = rs.getInt("point");
        if (rs.wasNull()) return "未実施";
        if (point >= 90) return "秀";
        if (point >= 80) return "優";
        if (point >= 70) return "良";
        if (point >= 60) return "可";
        return "不可";
    }
}
