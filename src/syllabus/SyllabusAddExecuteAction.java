package syllabus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.SyllabusDetail;
import dao.SubjectDao;
import dao.SyllabusDetailDAO;
import tool.Action;

public class SyllabusAddExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("開始：SyllabusAddAction");

		int admission_year = 0;
		admission_year = Integer.parseInt(req.getParameter("admission_year"));
		req.setAttribute("admission_year", admission_year);

		if(req.getParameter("search") != null){
			System.out.println("新規科目追加画面へ");
			System.out.println(req.getParameter("admission_year"));

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
			req.getRequestDispatcher("SyllabusSubjectCodeSearch.jsp").forward(req, res);
		}

		if(req.getParameter("exec") != null){
			System.out.println("新規科目をインサートして元画面に戻る");

			System.out.println(req.getParameter("admission_year"));

			//ローカル変数の宣言 1
			SyllabusDetailDAO dao = new SyllabusDetailDAO(); // 科目Dao
			//リクエストパラメータ―の取得 2
			//なし
			//DBからデータ取得 3
			dao.ins(req.getParameter("admission_year"),req);
			//ビジネスロジック 4
			//なし
			//DBへデータ保存 5
			//なし
			//レスポンス値をセット 6
//			req.setAttribute("subject",list);


			try{
				// シラバステーブルにアクセスするDAOを宣言
				SyllabusDetailDAO sdao = new SyllabusDetailDAO();
				// 年度と氏名を指定して学生を検索
				List<SyllabusDetail> list = sdao.search(admission_year);
				// リクエスト内に検索結果を格納
				req.setAttribute("list", list);

				// セッションスコープにセット（ファイル出力用）
		        HttpSession session = req.getSession();
		        session.setAttribute("slist", list);


			}catch(Exception e){
					// 例外時の処理
					e.printStackTrace();

			}finally{
				System.out.println("finally");
				req.getRequestDispatcher("SyllabusDetailMaintenance.jsp").forward(req, res);
			}
		}

		if(req.getParameter("cancel") != null){
			System.out.println("元の画面へ");
			System.out.println(req.getParameter("admission_year"));

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
}
