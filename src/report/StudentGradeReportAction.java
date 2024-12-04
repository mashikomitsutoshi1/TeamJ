package report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentGradeReport;
import dao.StudentGradeReportDao;
import tool.Action;

public class StudentGradeReportAction extends Action {

	/**
	 * このメソッドはHTTPリクエストを処理し、学生とそのコース名の情報を取得してリクエスト属性に設定します。
	 *
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @return 処理結果としてのJSPページの名前
	 * @throws Exception 処理中に発生する可能性のある例外
	 */
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

		try {
			// StudentGradeReportDaoのインスタンスを作成
			StudentGradeReportDao dao = new StudentGradeReportDao();

			// データベースから学生成績情報を全て取得
			List<StudentGradeReport> sglist = dao.selectAll();

			// 取得した学生情報リストをリクエスト属性に設定
			request.setAttribute("sglist", sglist);

			// セッションスコープにセット（ファイル出力用）
	        HttpSession session = request.getSession();
	        session.setAttribute("sglist", sglist);
		}
		catch(Exception e) {
			// 例外が発生した場合はスタックトレースを出力
			e.printStackTrace();
		}

		// 処理が完了したら"list.jsp"ページを返す
		return "StudentGradeReport.jsp";
	}
}