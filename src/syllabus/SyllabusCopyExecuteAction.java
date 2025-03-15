package syllabus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Syllabus;
import dao.SyllabusDAO;
import dao.SyllabusDetailDAO;
import tool.Action;

public class SyllabusCopyExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("★開始：SyllabusCopyExcuteAction");

		String admission_year = req.getParameter("admission_year");
		String saki_admission_year = req.getParameter("saki_admission_year");

		System.out.println("admission_year"+admission_year);
		System.out.println("saki_admission_year"+saki_admission_year);

		String action = req.getParameter("select");
		int actionint = Integer.parseInt(action);
		System.out.println(action);

		for(int i=0;i<100;i++){
			if(i == actionint){
				String sc = "admission_year_"+i;
				System.out.println(sc);
				admission_year = req.getParameter(sc);
				System.out.println(admission_year);
			}
		}

		try{
			// シラバス詳細テーブルにアクセスするDAOを宣言
			SyllabusDetailDAO dao = new SyllabusDetailDAO();
			dao.syllabusCopyIns(admission_year,saki_admission_year,req);

			SyllabusDAO dao2 = new SyllabusDAO();

			List<Syllabus> list = dao2.search();

			req.setAttribute("list", list);

		}catch(Exception e){
				// 例外時の処理
				e.printStackTrace();

		}finally{
			req.getRequestDispatcher("SyllabusMaintenance.jsp").forward(req, res);
		}
	}


}
