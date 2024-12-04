package report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class ClassListAction extends Action {

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
			// StudentDAOのインスタンスを作成
			StudentDao dao = new StudentDao();

			// データベースから学生とそのコース名の情報を全て取得
			List<Student> slist = dao.selectAll();

			// 取得した学生情報リストをリクエスト属性に設定
			request.setAttribute("slist", slist);

			// セッションスコープにセット（ファイル出力用）
	        HttpSession session = request.getSession();
	        session.setAttribute("slist", slist);

		}
		catch(Exception e) {
			// 例外が発生した場合はスタックトレースを出力
			e.printStackTrace();
		}

		// 処理が完了したら"list.jsp"ページを返す
		return "Studentlist.jsp";
	}
}