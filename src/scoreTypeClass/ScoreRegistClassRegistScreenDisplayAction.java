
package scoreTypeClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDao;
import tool.Action;

public class ScoreRegistClassRegistScreenDisplayAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//ローカル変数の宣言 1
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String classNum = "";
		String subjectCd = "" ;
		String subjectName = "" ;
		int registYear = 0;
		Date enrollmentDate;
		ScoreDao scoreDao = new ScoreDao(); // 成績Dao
		//リクエストパラメータ―の取得 2

		subjectCd = req.getParameter("subject_cd");
		subjectName = req.getParameter("subject_name");
		classNum = req.getParameter("class");
		registYear = Integer.parseInt(req.getParameter("regist_year"));
		enrollmentDate = dateFormat.parse(req.getParameter("enrollment_date"));
		//DBからデータ取得 3
		List<Score> list = scoreDao.getClassScore(registYear, classNum, subjectCd);

		//ビジネスロジック 4
		//処理年度取得

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		req.setAttribute("regist_year",registYear);
		req.setAttribute("enrollment_date",sdf.format(enrollmentDate));
		req.setAttribute("class_num",classNum);
		req.setAttribute("subject_cd",subjectCd);
		req.setAttribute("subject_name",subjectName);
		req.setAttribute("list", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("scoreRegistTypeClassRegist.jsp").forward(req, res);
	}

}
