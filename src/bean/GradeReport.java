package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeReport {

    private int admissionYear;		// 入学年度 学生テーブル
    private String studentNo;		// 学籍番号 学生テーブル
    private String studentName;		// 学生氏名

	//辞書（科目名、単位、評価）を要素として持つリスト
    private List<Map<String, Object>> gradeReport = new ArrayList<>();

    // ゲッターとセッター

    // admissionYear
    public int getAdmissionYear() {
        return admissionYear;
    }
    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    // studentNo
    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // studentName
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // セッター (科目名、単位、評価を設定)
    public void addSubject(String subjectName, int credit, String grading) {
        Map<String, Object> subject = new HashMap<>();
        subject.put("subject", subjectName);
        subject.put("credit", credit);
        subject.put("grading", grading);

        gradeReport.add(subject);
    }

    // ゲッター (科目名、単位、評価を取得)
    public String getSubjectName(int index) {
        return (String) gradeReport.get(index).get("subject");
    }

    public int getCredits(int index) {
        return (int) gradeReport.get(index).get("credit");
    }

    public String getGrading(int index) {
        return (String) gradeReport.get(index).get("grading");
    }

    // 科目情報を辞書形式で取得
    public Map<String, Object> getSubjectInfo(int index) {
        return gradeReport.get(index);
    }

    public List<Map<String, Object>> getGradeReport() {
        return gradeReport;
    }
}
