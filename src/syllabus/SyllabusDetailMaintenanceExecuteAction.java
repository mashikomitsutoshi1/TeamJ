package syllabus;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Syllabus;
import bean.SyllabusDetail;
import dao.SyllabusDAO;
import dao.SyllabusDetailDAO;
import tool.Action;

public class SyllabusDetailMaintenanceExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {


		if(req.getParameter("copy") != null){
			System.out.println("copyが押下されました");

			try{
				// シラバステーブルにアクセスするDAOを宣言
				SyllabusDAO dao = new SyllabusDAO();
				// シラバスを検索
				List<Syllabus> list = dao.search();
				// リクエスト内に検索結果を格納
				req.setAttribute("list", list);

			}catch(Exception e){
					// 例外時の処理
					e.printStackTrace();

			}finally{
				int saki_admission_year = Integer.parseInt(req.getParameter("admission_year"));
				req.setAttribute("saki_admission_year", saki_admission_year);

				req.getRequestDispatcher("SyllabusCopy.jsp").forward(req, res);
			}

		}
		if(req.getParameter("exec") != null){
			System.out.println("execが押下されました");
			int admission_year = Integer.parseInt(req.getParameter("admission_year"));
			System.out.println(admission_year);

			SyllabusDetailDAO dao = new SyllabusDetailDAO();

			dao.delIn(admission_year , req);


			// CSVファイル処理
	        // ユーザーのホームディレクトリを取得
	        String userHome = System.getProperty("user.home");

	        // Windowsの「ドキュメント」フォルダのパスを構築
	        String filePath = Paths.get(userHome, "Documents", "SyllabusDetail.csv").toString();

	        // Shift-JISでCSVファイルを作成、または上書き
	        try (BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))) {

	            HttpSession session = req.getSession();
	            List<SyllabusDetail> slist = (List<SyllabusDetail>) session.getAttribute("slist2");

	            if (slist == null) {
	                // slist が存在しない場合の処理
	                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "シラバスが存在しません");
	            } else {
	            	System.out.println("シラバスあった");
	            }

	            // ヘッダーを書き込み
	          writer.write("シラバス一覧");
	          writer.newLine(); // 改行
	          writer.write("入学年度,科目コード,必須フラグ,選択フラグ,履修時間,単位数,学年,SVKAMOKU");
	          writer.newLine(); // 改行

	            // 各学生の情報をCSV形式で書き込み
	            for (SyllabusDetail syllabus : slist) {
	                writer.write(syllabus.getAdmission_year() + "," +
	                		syllabus.getSubject_code() + "," +
	                		syllabus.getRequired_flg() + "," +
	                		syllabus.getSelect_flg() + "," +
	                		syllabus.getCourse_time() + "," +
	                		syllabus.getCredit() + "," +
	                		syllabus.getGrade() + "," +
	                		syllabus.getSvkamoku() + "," +
	                             "");
	                writer.newLine(); // 改行
	            }
	          writer.close();
	        }
	        SyllabusMaintenanceAction sma = new SyllabusMaintenanceAction();
	        sma.execute(req, res);
		}

		//　新規科目追加の場合
		if(req.getParameter("add") != null){
			System.out.println("addが押下されました");
			int admission_year = Integer.parseInt(req.getParameter("admission_year"));
			System.out.println("add押された後のadmission_year:" + admission_year);
			req.setAttribute("admission_year", admission_year);
			req.getRequestDispatcher("SyllabusAdd.jsp").forward(req, res);
		}

		if(req.getParameter("delAll") != null){
			System.out.println("delAllが押下されました");

			int admission_year = Integer.parseInt(req.getParameter("admission_year"));
			System.out.println(admission_year);

			SyllabusDetailDAO dao = new SyllabusDetailDAO();
			dao.del(admission_year , req);
	        SyllabusMaintenanceAction sma = new SyllabusMaintenanceAction();
	        sma.execute(req, res);
		}

		if(req.getParameter("del") != null){
			System.out.println("delが押下されました");

			int admission_year = Integer.parseInt(req.getParameter("admission_year"));
			String subject_code   = req.getParameter("subject_code");

			String action = req.getParameter("del");
			int actionint = Integer.parseInt(action);
			System.out.println(action);

			for(int i=0;i<100;i++){
				if(i == actionint){
					String sc = "subject_code_"+i;
					System.out.println(sc);
					subject_code = req.getParameter(sc);
					System.out.println(subject_code);
				}
			}

			System.out.println(admission_year);
			System.out.println(subject_code);

			SyllabusDetailDAO dao = new SyllabusDetailDAO();
			dao.delete(admission_year , subject_code, req);

//	        SyllabusMaintenanceAction sma = new SyllabusMaintenanceAction();
//	        sma.execute(req, res);

			try{
				admission_year = Integer.parseInt(req.getParameter("admission_year"));
				System.out.println(admission_year);

				// DAOを宣言
				SyllabusDetailDAO dao2 = new SyllabusDetailDAO();

				// 入学年度を指定してシラバス詳細を取得
				List<SyllabusDetail> list2 = dao2.search(admission_year);

				// リクエスト内に検索結果を格納
				req.setAttribute("list", list2);
//				req.setAttribute("prelist", list);

				// セッションスコープにセット（ファイル出力用）
		        HttpSession session = req.getSession();
		        session.setAttribute("slist2", list2);

			}catch(Exception e){
					// 例外時の処理
					e.printStackTrace();

			}finally{
				req.setAttribute("admission_year", admission_year);

				System.out.println("終了：SyllabusDetailMaintenanceAction.execute");
				req.getRequestDispatcher("SyllabusDetailMaintenance.jsp").forward(req, res);
			}




		}


		if(req.getParameter("allSyllbus") != null){
			System.out.println("allSyllbusが押下されました");

			// CSVファイル処理
	        // ユーザーのホームディレクトリを取得
	        String userHome = System.getProperty("user.home");

	        // Windowsの「ドキュメント」フォルダのパスを構築
	        String filePath = Paths.get(userHome, "Documents", "preSyllabusDetail.csv").toString();

	        // Shift-JISでCSVファイルを作成、または上書き
	        try (BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))) {

	            HttpSession session = req.getSession();
	            List<SyllabusDetail> slist = (List<SyllabusDetail>) session.getAttribute("slist");

	            if (slist == null) {
	                // slist が存在しない場合の処理
	                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "シラバスが存在しません");
	            } else {
	            	System.out.println("シラバスあった");
	            }

	            // ヘッダーを書き込み
	          writer.write("シラバス一覧");
	          writer.newLine(); // 改行
	          writer.write("入学年度,科目コード,必須フラグ,選択フラグ,履修時間,単位数,学年,SVKAMOKU");
	          writer.newLine(); // 改行

	            // 各学生の情報をCSV形式で書き込み
	            for (SyllabusDetail syllabus : slist) {
	                writer.write(syllabus.getAdmission_year() + "," +
	                		syllabus.getSubject_code() + "," +
	                		syllabus.getRequired_flg() + "," +
	                		syllabus.getSelect_flg() + "," +
	                		syllabus.getCourse_time() + "," +
	                		syllabus.getCredit() + "," +
	                		syllabus.getGrade() + "," +
	                		syllabus.getSvkamoku() + "," +
	                             "");
	                writer.newLine(); // 改行
	            }
	          writer.close();
	        }
	        SyllabusMaintenanceAction sma = new SyllabusMaintenanceAction();
	        sma.execute(req, res);
		}
	}
}
