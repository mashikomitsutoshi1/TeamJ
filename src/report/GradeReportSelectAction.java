package report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GradeReport;
import dao.GradeReportDao;
import tool.Action;

// 学生成績表Action
public class GradeReportSelectAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// リクエストパラメータの取得 入学年度、クラス
		String s_admission_year = request.getParameter("admission_year");
		String s_class_no = request.getParameter("class_no");

		// 未入力項目がある場合はエラー
		// メッセージを設定してフォワード先を返す エラー画面
		if (s_admission_year.isEmpty() || s_class_no.isEmpty()) {

            String error1 = "成績評価表";
            String error2 = "入力項目が正しくありません。";
            String error3 = "入学年度、クラスを確認してください。";
            System.out.println(error1);
            System.out.println(error2);
            System.out.println(error3);

            request.setAttribute("error1", error1);
            request.setAttribute("error2", error2);
            request.setAttribute("error3", error3);

            // return "/error.jsp";
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		try {
			// GradeReportDaoのインスタンスを作成
			GradeReportDao dao = new GradeReportDao();

			// データベースから成績情報を全て取得
			List<GradeReport> glist = dao.selectClass(s_admission_year,s_class_no);

			// 取得した学生情報リストをリクエスト属性に設定
			// リクエストスコープにセット（JSPで表示用）
			request.setAttribute("glist", glist);

			// セッションスコープにセット（ファイル出力用）
	        HttpSession session = request.getSession();
	        session.setAttribute("glist", glist);
		}
		catch(Exception e) {
			// 例外が発生した場合はスタックトレースを出力
			e.printStackTrace();
		}

		// 処理が完了したら"list.jsp"ページを返す
		// return "StudentGradeReport.jsp";
   		request.getRequestDispatcher("GradeReport.jsp").forward(request, response);

	}
}
