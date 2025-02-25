package scoreTypeClass;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import bean.Student;
import dao.ScoreDao;
import tool.Action;

public class RegistStudentInfoAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		String[] subjectCodes = req.getParameterValues("regist");
		int admissionYear = Integer.parseInt(req.getParameter("admission_year"));
		String studentNo = req.getParameter("student_no");
		String studentName = req.getParameter("student_name");
		int point = 0; // 点数
		int count = 0;
		List<Score> list = new ArrayList<>();
		ScoreDao scoreDao = new ScoreDao(); // 成績Dao
		Student student = new Student();

		for(String code : subjectCodes){
			Score score = new Score();
			//得点が未入力の場合、削除処理
			if(req.getParameter("point_" + code).equals("")){
				scoreDao.deleteScore(studentNo, code, admissionYear);
				break;
			}
			point = Integer.parseInt(req.getParameter("point_" + code));
			if (point >= 0 && point <= 100) { // 得点が0～100の場合
				// scoreに情報をセット
				score.setStudentNo(studentNo);
				score.setAdmissionYear(admissionYear);
				score.setSubjectCode(code);
				score.setMonth(Integer.parseInt(req.getParameter("month_" + code)));
				score.setPoint(point);
				score.setExtractionDate(Date.valueOf(req.getParameter("extractionDate_" + code)));
				// リストに格納
				list.add(score);
			}
		}

		//DBへデータ保存 5
		boolean isSuccess = scoreDao.save(list);

		if(isSuccess){
			count += 1;
		}
		//レスポンス値をセット 6
		student.setAdmissionYear(admissionYear);
		student.setStudentName(studentName);
		req.setAttribute("count", count);
		req.setAttribute("student", student);


		// JSPへフォワード
        req.getRequestDispatcher("searchStudentInfo.jsp").forward(req, res);



	}

}
