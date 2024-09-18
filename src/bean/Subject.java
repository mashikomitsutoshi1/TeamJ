package bean;

public class Subject {
	private String subjectCd; //科目コード
	private String subjectName; //科目名

	//科目コードのGetter
	public String getSubjectCd() {
		return subjectCd;
	}

	//科目コードのSetter
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	//科目名のGetter
	public String getSubjectName() {
		return subjectName;
	}

	//科目名のSetter
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
