package syllabus;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ScoreRegistClassSearchScreenDisplayExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("start:ScoreRegistClassSearchScreenDisplayExecuteAction");

		//ローカル変数の宣言 1
		String subject_cd		= "";
		String subject_name	= "";
		int regist_year		= 0;
		int admission_year	= 0;

		//リクエストパラメータ―の取得 2
		subject_cd 		= req.getParameter("cd");
		subject_name 	= req.getParameter("name");
		admission_year 	= Integer.parseInt(req.getParameter("admission_year"));

		//DBからデータ取得 3
		//なし

		//ビジネスロジック 4
		//処理年度取得
        LocalDateTime nowDate = LocalDateTime.now();
        if(nowDate.getMonthValue() < 4){
        	regist_year = nowDate.getYear() + 1;
        }else{
        	regist_year = nowDate.getYear();
        }

        //DBへデータ保存 5
		//なし

        //レスポンス値をセット 6
		req.setAttribute("subject_cd",subject_cd);
		req.setAttribute("subject_name",subject_name);
		req.setAttribute("regist_year",regist_year);
		req.setAttribute("admission_year",admission_year);

		//JSPへフォワード 7
//		req.getRequestDispatcher("scoreRegistTypeClassSearch.jsp").forward(req, res);
		req.getRequestDispatcher("SyllabusAdd.jsp").forward(req, res);

	}

}
