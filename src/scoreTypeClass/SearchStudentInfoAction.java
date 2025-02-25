package scoreTypeClass;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Score;
import bean.Student;
import dao.ScoreDao;
import dao.StudentDao;
import tool.Action;

public class SearchStudentInfoAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentCode = req.getParameter("student_id"); // 7桁の学生コード（admission_year + student_no）
        System.out.println("student_id:"+studentCode);

        // 学生コード（7桁）から入学年度（2桁）と学生番号（5桁）を分割
        String admissionYear = studentCode.substring(0, 2); // 入学年度（最初の2桁）
        String studentNo = studentCode.substring(2); // 学生番号（後ろの5桁）

        // StudentDao と ScoreDao のインスタンス化
        StudentDao studentDao = new StudentDao();
        ScoreDao scoreDao = new ScoreDao();

        // DBから学生情報を取得
        Student student = studentDao.get(Integer.parseInt(admissionYear),studentNo);

        // 成績情報を格納するリスト
        List<Score> scores = new ArrayList<>();

        // 入学年度と学生番号が指定されている場合、成績情報をDBから取得
        if (admissionYear != null && studentNo != null) {
            try (Connection conn = getConnection()) {
                // SQLクエリ：admission_year と student_no に基づいて subject_code, month, point を取得
                String query = "SELECT subject_code, month, point, extraction_date FROM score WHERE admission_year = ? AND student_no = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    // パラメータを設定
                    stmt.setInt(1, Integer.parseInt(admissionYear));  // 入学年度
                    stmt.setString(2, studentNo);  // 学生番号

                    try (ResultSet rs = stmt.executeQuery()) {
                        // 結果を取得して処理
                        while (rs.next()) {
                        	Score score = new Score();
                            String subjectCode = rs.getString("subject_code");
                            int month = rs.getInt("month");
                            int point = rs.getInt("point");
                            String extractionDate = rs.getString("extraction_date");

                            // 取得したデータをScoreオブジェクトに格納
                            score.setSubjectCode(subjectCode);
                            score.setMonth(month);
                            score.setPoint(point);
                            score.setExtractionDate(Date.valueOf(extractionDate));
                            scores.add(score);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();  // エラーハンドリング
            }
        }

        System.out.println(scores.get(0).getPoint());

        // 成績情報と学生情報をリクエストにセット
        req.setAttribute("scores", scores);
        req.setAttribute("student", student); // 学生情報も渡す（必要に応じて）

        // JSPへフォワード
        req.getRequestDispatcher("searchStudentInfo.jsp").forward(req, res);
    }

	static DataSource ds;

    // データベース接続メソッド (仮定)
    public Connection getConnection() throws Exception {
		// データソースがnullの場合
		if (ds == null) {
			// InitialContextを初期化
			InitialContext ic = new InitialContext();
			// データベースへ接続
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/teamj_db");
		}
		// データベースへのコネクションを返却
		return ds.getConnection();
	}
}
