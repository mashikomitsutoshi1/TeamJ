package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

    public List<Student> selectAll() throws Exception {

        // 学生のリストを作成
        List<Student> list = new ArrayList<>();

        // データベースへの接続を取得
        Connection con = getConnection();

        // SQL文を準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT");

        // SQL文を実行し、結果セットを取得
        ResultSet rs = st.executeQuery();

        // 結果セットをループし、Studentオブジェクトにデータを設定
        while (rs.next()) {
            Student stu = new Student();
            stu.setAdmissionYear(rs.getInt("admission_year"));	 			//入学年度
            stu.setStudentNo(rs.getString("student_no")); 					//学籍番号
            stu.setStudentName(rs.getString("student_name")); 				//学生氏名
            stu.setStudentNameKana(rs.getString("student_name_kana")); 		//学生氏名（カナ）
            stu.setClassNo(rs.getString("class_no"));  						//クラス
            stu.setDispositionStatus(rs.getString("disposition_status"));	//処分ステータス
            stu.setTotalAbsences(rs.getFloat("total_absences"));  			//前月末欠席累計数
            list.add(stu); // リストに追加
        }

        // リソースをクローズ
        st.close();
        con.close();

        // 学生のリストを返す
        return list;
    }

    public List<Student> selectClass(String admission_year,String class_no) throws Exception {

        // 学生のリストを作成
        List<Student> list = new ArrayList<>();

        // データベースへの接続を取得
        Connection con = getConnection();

        int admission_year_int = Integer.parseInt(admission_year);
        // SQL文を準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE admission_year = ? AND class_no = ?");
		st.setInt(1, admission_year_int);
		st.setString(2, class_no);

        // SQL文を実行し、結果セットを取得
        ResultSet rs = st.executeQuery();

        // 結果セットをループし、Studentオブジェクトにデータを設定
        while (rs.next()) {
            Student stu = new Student();
            stu.setAdmissionYear(rs.getInt("admission_year"));	 			//入学年度
            stu.setStudentNo(rs.getString("student_no")); 					//学籍番号
            stu.setStudentName(rs.getString("student_name")); 				//学生氏名
            stu.setStudentNameKana(rs.getString("student_name_kana")); 		//学生氏名（カナ）
            stu.setClassNo(rs.getString("class_no"));  						//クラス
            stu.setDispositionStatus(rs.getString("disposition_status"));	//処分ステータス
            stu.setTotalAbsences(rs.getFloat("total_absences"));  			//前月末欠席累計数
            list.add(stu); // リストに追加
        }

        // リソースをクローズ
        st.close();
        con.close();

        // 学生のリストを返す
        return list;
    }

    public Student get(int admissionYear,String studentNo) throws Exception {

        // 学生のリストを作成
        Student student = new Student();

        // データベースへの接続を取得
        Connection con = getConnection();

        // SQL文を準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE admission_year = ? and student_no = ?");
        st.setInt(1, admissionYear);
        st.setString(2, studentNo);

        // SQL文を実行し、結果セットを取得
        ResultSet rs = st.executeQuery();

        // 結果セットをループし、Studentオブジェクトにデータを設定
        while (rs.next()) {
            student.setAdmissionYear(rs.getInt("admission_year"));	 			//入学年度
            student.setStudentNo(rs.getString("student_no")); 					//学籍番号
            student.setStudentName(rs.getString("student_name")); 				//学生氏名
            student.setStudentNameKana(rs.getString("student_name_kana")); 		//学生氏名（カナ）
            student.setClassNo(rs.getString("class_no"));  						//クラス
            student.setDispositionStatus(rs.getString("disposition_status"));	//処分ステータス
            student.setTotalAbsences(rs.getFloat("total_absences"));  			//前月末欠席累計数
        }

        // リソースをクローズ
        st.close();
        con.close();

        // 学生のリストを返す
        return student;
    }

}
