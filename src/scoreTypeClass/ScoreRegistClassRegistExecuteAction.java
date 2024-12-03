package scoreTypeClass;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDao;
import tool.Action;

public class ScoreRegistClassRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//ローカル変数の宣言 1
		String subjectCd = ""; // 科目コード
		Date enrollmentDate = null; // 抽出日
		int point = 0; // 点数
		ScoreDao scoreDao = new ScoreDao(); // 成績Dao
		int count = 0;
		boolean excelSuccess = false;

		List<Score> list = new ArrayList<>();
		//リクエストパラメータ―の取得 2

		String[] student_no = req.getParameterValues("regist");
		subjectCd = req.getParameter("subject_cd");
		enrollmentDate = Date.valueOf(req.getParameter("enrollment_date"));
		String excel = req.getParameter("excel");
		String classNum = req.getParameter("class_num");
		String registYear = req.getParameter("regist_year");
		//DBからデータ取得 3

		//ビジネスロジック 4
		for(String no : student_no){
			Score score = new Score();
			point = Integer.parseInt(req.getParameter("point_" + no));
			if (point >= 0 && point <= 100) { // 得点が0～100の場合
				// scoreに情報をセット
				score.setStudentNo(no.substring(2));
				score.setAdmissionYear(Integer.parseInt(no.substring(0,2)));
				score.setSubjectCode(subjectCd);
				score.setMonth(Integer.parseInt(req.getParameter("month_" + no)));
				score.setPoint(point);
				score.setExtractionDate(enrollmentDate);
				// リストに格納
				list.add(score);
			}

		}

		//DBへデータ保存 5
		boolean isSuccess = scoreDao.save(list);

		if(isSuccess){
			count += 1;
		}
		//レスポンス値をセット 6
		req.setAttribute("count", count);

		//Excel出力処理
		if(excel.equals("1")){
			excelSuccess = writeToCsv(subjectCd,classNum,registYear);
		}

		if(excelSuccess = true){
			req.setAttribute("message", "Excel出力完了しました");
		}

		//JSPへフォワード 7
		req.getRequestDispatcher("scoreRegistTypeClassRegist.jsp").forward(req, res);
	}

	private boolean writeToCsv(String subjectCd,String className,String admissionYear){

		//結果返却用
		boolean result = false;
		// ユーザーのホームディレクトリを取得
        String userHome = System.getProperty("user.home");

        // Windowsの「ドキュメント」フォルダのパスを構築
        String filePath = Paths.get(userHome, "Documents", "成績評価チェックリスト.csv").toString();


		//syori
		try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))){

			//成績データ取得
			ScoreDao scoreDao = new ScoreDao(); // 成績Dao
			List<Score> list = scoreDao.getClassScoreToCsv(Integer.parseInt(admissionYear), className, subjectCd);

			if (list == null) {
                // list が存在しない場合の処理
                return result;
            }

			// ヘッダーを書き込み
	        writer.write("成績評価チェックリスト");
	        writer.newLine(); // 改行
	        writer.write("学籍番号,氏名,学年,月,科目コード,科目名,点数,クラス,ｸﾗｽ在籍者抽出日");
	        writer.newLine(); // 改行

	     // 各学生の情報をCSV形式で書き込み
            for (Score score : list) {
                writer.write(score.getStudentNo() + "," +
                			 score.getStudentName() + "," +
                			 score.getStudyYear() + "," +
                			 score.getMonth() + "," +
                			 score.getSubjectCode() + "," +
                			 score.getSubjectName() + "," +
                			 score.getPoint() + "," +
                			 score.getClassName() + "," +
                			 score.getExtractionDate() );
                writer.newLine(); // 改行
            }

            System.out.println("CSVファイルが " + filePath + " に書き込まれました。");


			result = true;
			return result;
		}catch (Exception e ){
			System.out.println(e);
			return result;

		}



	}

}
