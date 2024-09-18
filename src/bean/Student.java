package bean;

public class Student {
	private int admissionYear; //入学年度
	private String studentNo; //学籍番号
	private String studentName; //学生氏名
	private String studentNameKana; //学生氏名（カナ）
	private String classNo; //クラス
	private String dispositionStatus; //処分ステータス
	private float totalAbsences; //前月末欠席累計数
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
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return studentNameKana
	 */
	public String getStudentNameKana() {
		return studentNameKana;
	}
	/**
	 * @param studentNameKana セットする studentNameKana
	 */
	public void setStudentNameKana(String studentNameKana) {
		this.studentNameKana = studentNameKana;
	}
	/**
	 * @return classNo
	 */
	public String getClassNo() {
		return classNo;
	}
	/**
	 * @param classNo セットする classNo
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	/**
	 * @return dispositionStatus
	 */
	public String getDispositionStatus() {
		return dispositionStatus;
	}
	/**
	 * @param dispositionStatus セットする dispositionStatus
	 */
	public void setDispositionStatus(String dispositionStatus) {
		this.dispositionStatus = dispositionStatus;
	}
	/**
	 * @return totalAbsences
	 */
	public float getTotalAbsences() {
		return totalAbsences;
	}
	/**
	 * @param totalAbsences セットする totalAbsences
	 */
	public void setTotalAbsences(float totalAbsences) {
		this.totalAbsences = totalAbsences;
	}


}
