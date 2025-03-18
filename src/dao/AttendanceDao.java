package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;
import bean.Student;

public class AttendanceDao extends Dao {
	public List<Attendance> selectAll() throws Exception {

        // 学生のリストを作成
        List<Attendance> list = new ArrayList<>();

        // データベースへの接続を取得
        Connection con = getConnection();

        // SQL文を準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM ATTENDANCE");

        // SQL文を実行し、結果セットを取得
        ResultSet rs = st.executeQuery();

        // 結果セットをループし、Studentオブジェクトにデータを設定
        while (rs.next()) {
	        Attendance a = new Attendance();
	        a.setProcessingDate(rs.getDate("processing_date"));
	        a.setAdmissionYear(rs.getInt("admission_year"));
	        a.setStudentNo(rs.getString("student_no"));
	        a.setAttendanceStatus(rs.getString("attendance_status"));
	        list.add(a);
        }

        // リソースをクローズ
        st.close();
        con.close();

        // 学生のリストを返す
        return list;
    }
	// 指定されたクラスと入学年度に基づく学生情報を取得
	public List<Student> search(String processingClass) throws Exception {
	    List<Student> list = new ArrayList<>();

	    Connection con = getConnection();
//	    System.out.println("Connection established.");

	    PreparedStatement st = con.prepareStatement(
		        "SELECT * FROM student WHERE class_no = ? ORDER BY student_name_kana");
		    st.setString(1, processingClass);
		    ResultSet rs = st.executeQuery();
//		    System.out.println("Query executed: " + st);

	    while (rs.next()) {
	        Student s = new Student();
	        s.setAdmissionYear(rs.getInt("admission_year"));
	        s.setStudentNo(rs.getString("student_no"));
	        s.setStudentName(rs.getString("student_name"));
	        s.setStudentNameKana(rs.getString("student_name_kana"));
	        s.setClassNo(rs.getString("class_no"));
	        s.setDispositionStatus(rs.getString("disposition_status"));
	        s.setTotalAbsences(rs.getFloat("total_absences"));
//	        System.out.println("Student retrieved: " + s.getStudentNo() + ", " + s.getStudentName());
	        list.add(s);
	    }

	    st.close();
	    con.close();

	    System.out.println("Total students retrieved: " + list.size());
	    return list;
	}
	public List<Attendance> search2() throws Exception {
	    List<Attendance> list = new ArrayList<>();

	    Connection con = getConnection();
//	    System.out.println("Connection established.");

	    PreparedStatement st = con.prepareStatement(
		        "SELECT * FROM attendance");
//		    st.setString(1, processingClass);
		    ResultSet rs = st.executeQuery();
//		    System.out.println("Query executed: " + st);

	    while (rs.next()) {
	        Attendance a = new Attendance();
	        a.setProcessingDate(rs.getDate("processing_date"));
	        a.setAdmissionYear(rs.getInt("admission_year"));
	        a.setStudentNo(rs.getString("student_no"));
	        a.setAttendanceStatus(rs.getString("attendance_status"));
	        list.add(a);
	    }

	    st.close();
	    con.close();

	    return list;
	}
}