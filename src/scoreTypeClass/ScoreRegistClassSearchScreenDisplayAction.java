package scoreTypeClass;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ScoreRegistClassSearchScreenDisplayAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//ローカル変数の宣言 1
		int regist_year = 0;
		//リクエストパラメータ―の取得 2
		//なし
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
        req.setAttribute("regist_year",regist_year);
		//JSPへフォワード 7
		req.getRequestDispatcher("scoreRegistTypeClassSearch.jsp").forward(req, res);
	}

}
