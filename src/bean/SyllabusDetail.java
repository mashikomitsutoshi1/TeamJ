package bean;

public class SyllabusDetail implements java.io.Serializable{

	private int 	admission_year;		// 入学年度
	private String	subject_code;		// 科目コード
	private String	required_flg;		// 必須フラグ
	private String 	select_flg;			// 選択フラグ
	private int 	course_time;		// 履修時間
	private int 	credit;				// 単位数
	private int 	grade;				// 学年
	private String 	svkamoku;			// SVKAMOKU

	private String 	subject_name;		// 科目名

	/**
	 * @return admission_year
	 */
	public int getAdmission_year() {
		return admission_year;
	}
	/**
	 * @param admission_year セットする admission_year
	 */
	public void setAdmission_year(int admission_year) {
		this.admission_year = admission_year;
	}
	/**
	 * @return subject_code
	 */
	public String getSubject_code() {
		return subject_code;
	}
	/**
	 * @param subject_code セットする subject_code
	 */
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	/**
	 * @return required_flg
	 */
	public String getRequired_flg() {
		return required_flg;
	}
	/**
	 * @param required_flg セットする required_flg
	 */
	public void setRequired_flg(String required_flg) {
		this.required_flg = required_flg;
	}
	/**
	 * @return select_flg
	 */
	public String getSelect_flg() {
		return select_flg;
	}
	/**
	 * @param select_flg セットする select_flg
	 */
	public void setSelect_flg(String select_flg) {
		this.select_flg = select_flg;
	}
	/**
	 * @return course_time
	 */
	public int getCourse_time() {
		return course_time;
	}
	/**
	 * @param course_time セットする course_time
	 */
	public void setCourse_time(int course_time) {
		this.course_time = course_time;
	}
	/**
	 * @return credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit セットする credit
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * @return grade
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * @param grade セットする grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	/**
	 * @return svkamoku
	 */
	public String getSvkamoku() {
		return svkamoku;
	}
	/**
	 * @param svkamoku セットする svkamoku
	 */
	public void setSvkamoku(String svkamoku) {
		this.svkamoku = svkamoku;
	}
	/**
	 * @return subject_name
	 */
	public String getSubject_name() {
		return subject_name;
	}
	/**
	 * @param subject_name セットする subject_name
	 */
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
}
