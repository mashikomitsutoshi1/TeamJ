package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentGradeReport {

    private String studentNo;      // 学籍番号 学生テーブル
    private String departmentName;  // 学科 シラバステーブル
    private String courseName;      // コース シラバステーブル
    private String studentName;     // 学生氏名
    private int birthdayYear;       // 生年月日 年
    private int birthdayMonth;      // 生年月日 月
    private int birthdayDay;        // 生年月日 日
    private int admissionYear;      // 入学年度 学生テーブル
    private int graduationYear;     // 卒業年度

	//辞書（科目名、単位、評価）を要素として持つリスト
    private List<Map<String, Object>> gradeReport = new ArrayList<>();

    // ゲッターとセッター

    // studentNo
    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // departmentName
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    // courseName
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // studentName
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // birthdayYear
    public int getBirthdayYear() {
        return birthdayYear;
    }
    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    // birthdayMonth
    public int getBirthdayMonth() {
        return birthdayMonth;
    }
    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    // birthdayDay
    public int getBirthdayDay() {
        return birthdayDay;
    }
    public void setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    // admissionYear
    public int getAdmissionYear() {
        return admissionYear;
    }
    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    // graduationYear
    public int getGraduationYear() {
        return graduationYear;
    }
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
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
    // StudentGradeReport.java
    public List<Map<String, Object>> getGradeReport() {
        return gradeReport;
    }
}
