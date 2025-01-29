package bean;

import java.util.Date;

public class Attendance {
    private Date processingDate; // 日付
    private int admissionYear; // 入学年度
    private String studentNo; // 学籍番号
    private String attendanceStatus; // 出席状況
    private String processingClass; // クラス情報（追加）

    // Getter and Setter for processingDate
    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    // Getter and Setter for admissionYear
    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    // Getter and Setter for studentNo
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // Getter and Setter for attendanceStatus
    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    // Getter and Setter for processingClass (追加)
    public String getProcessingClass() {
        return processingClass;
    }

    public void setProcessingClass(String processingClass) {
        this.processingClass = processingClass;
    }
}
