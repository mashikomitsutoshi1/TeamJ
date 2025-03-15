package syllabus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Syllabus;
import dao.SyllabusDAO;
import tool.Action;

public class SyllabusMaintenanceAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		System.out.println("開始：SyllabusMaintenanceAction.execute");

		if(req.getParameter("allSyllbus") != null){
			System.out.println("allSyllbusが押下されました");
		}

		try{
			// DAOを宣言
			SyllabusDAO dao = new SyllabusDAO();

			// 全シラバスを検索
			List<Syllabus> list = dao.search();

			// リクエスト内に検索結果を格納
			req.setAttribute("list", list);

			// セッションスコープにセット（ファイル出力用）
	        HttpSession session = req.getSession();
	        session.setAttribute("slist", list);

		}catch(Exception e){
				// 例外時の処理
				e.printStackTrace();

		}finally{
			System.out.println("終了：SyllabusMaintenanceAction.execute");
			req.getRequestDispatcher("SyllabusMaintenance.jsp").forward(req, res);
		}

	}

}
