package syllabus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SyllabusDetail;
import dao.SyllabusDetailDAO;
import tool.Action;

public class SyllabusDetailMaintenanceAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("開始：SyllabusDetailMaintenanceAction.execute");

		// 入学年度の初期化
		int admission_year = 0;

		try{
			admission_year = Integer.parseInt(req.getParameter("admission_year"));
			System.out.println(admission_year);

			// DAOを宣言
			SyllabusDetailDAO dao = new SyllabusDetailDAO();

			// 入学年度を指定してシラバス詳細を取得
			List<SyllabusDetail> list = dao.search(admission_year);

			// リクエスト内に検索結果を格納
			req.setAttribute("list", list);
			req.setAttribute("prelist", list);

			// セッションスコープにセット（ファイル出力用）
	        HttpSession session = req.getSession();
	        session.setAttribute("slist", list);
	        session.setAttribute("slist2", list);

		}catch(Exception e){
				// 例外時の処理
				e.printStackTrace();

		}finally{
			req.setAttribute("admission_year", admission_year);

			System.out.println("終了：SyllabusDetailMaintenanceAction.execute");
			req.getRequestDispatcher("SyllabusDetailMaintenance.jsp").forward(req, res);
		}
	}
}
