package bean;

import java.util.Date;

public class Score {
	private String subjectCode; //科目コード
	private int admissionYear; // 入学年度
	private String studentNo; //学籍番号
	private int month; //月
	private int point; //点数
	private Date extractionDate; //抽出日
	/**
	 * @return subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}
	/**
	 * @param subjectCode セットする subjectCode
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	/**
	 * @return admissionYear
	 */
	public int getAdmissionYear() {
		return admissionYear;
	}
	/**
	 * @param admissionYear セットする admissionYear
	 */
	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}
	/**
	 * @return studentNo
	 */
	public String getStudentNo() {
		return studentNo;
	}
	/**
	 * @param studentNo セットする studentNo
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month セットする month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point セットする point
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * @return extractionDate
	 */
	public Date getExtractionDate() {
		return extractionDate;
	}
	/**
	 * @param extractionDate セットする extractionDate
	 */
	public void setExtractionDate(Date extractionDate) {
		this.extractionDate = extractionDate;
	}
}
