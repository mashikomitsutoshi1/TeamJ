package bean;

public class Syllabus implements java.io.Serializable{

	private int		admission_year;			// 入学年度
	private String	curriculum_category;	// 課程区分
	private String	curriculum_name;		// 課程名
	private String	department_name;		// 科名
	private String	course_name;			// コース名
	private String	course_name_breakdown;	// コース名内訳名
	private String	study_year;				// 修業年月

	private int		subject_count;			// 科目数

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
	 * @return curriculum_category
	 */
	public String getCurriculum_category() {
		return curriculum_category;
	}
	/**
	 * @param curriculum_category セットする curriculum_category
	 */
	public void setCurriculum_category(String curriculum_category) {
		this.curriculum_category = curriculum_category;
	}
	/**
	 * @return curriculum_name
	 */
	public String getCurriculum_name() {
		return curriculum_name;
	}
	/**
	 * @param curriculum_name セットする curriculum_name
	 */
	public void setCurriculum_name(String curriculum_name) {
		this.curriculum_name = curriculum_name;
	}
	/**
	 * @return department_name
	 */
	public String getDepartment_name() {
		return department_name;
	}
	/**
	 * @param department_name セットする department_name
	 */
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	/**
	 * @return course_name
	 */
	public String getCourse_name() {
		return course_name;
	}
	/**
	 * @param course_name セットする course_name
	 */
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	/**
	 * @return course_name_breakdown
	 */
	public String getCourse_name_breakdown() {
		return course_name_breakdown;
	}
	/**
	 * @param course_name_breakdown セットする course_name_breakdown
	 */
	public void setCourse_name_breakdown(String course_name_breakdown) {
		this.course_name_breakdown = course_name_breakdown;
	}
	/**
	 * @return study_year
	 */
	public String getStudy_year() {
		return study_year;
	}
	/**
	 * @param study_year セットする study_year
	 */
	public void setStudy_year(String study_year) {
		this.study_year = study_year;
	}
	/**
	 * @return subject_count
	 */
	public int getSubject_count() {
		return subject_count;
	}
	/**
	 * @param subject_count セットする subject_count
	 */
	public void setSubject_count(int subject_count) {
		this.subject_count = subject_count;
	}
}
