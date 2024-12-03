package scoreTypeClass;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCodeSearchAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//ローカル変数の宣言 1
		SubjectDao subjectDao = new SubjectDao(); // 科目Dao
		//リクエストパラメータ―の取得 2
		//なし
		//DBからデータ取得 3
		List<Subject>list = subjectDao.getAll();
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("subject",list);

		//JSPへフォワード 7
		req.getRequestDispatcher("subjectCodeSearch.jsp").forward(req, res);
	}

}
