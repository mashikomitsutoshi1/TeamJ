package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Score;

public class ScoreDao extends Dao{
	public List<Score> getClassScore(int admissionYear,String classNum,String subjectCd) throws Exception {

		// リストを初期化
		List<Score> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select	"
					+" concat(a.admission_year,a.student_no) as student_no"
					+" ,a.student_name as student_name"
					+" ,substring(b.study_year,1,1) as study_year"
					+" ,b.curriculum_category as curriculum_category"
					+" ,d.subject_name as subject_name"
					+" ,c.month as month "
					+" ,c.point as point "
					+"from student as a "
					+"left join (select * from syllabus where curriculum_category = ?) as b on a.admission_year = b.admission_year "
					+"left join score as c on a.admission_year = c.admission_year AND a.student_no = c.student_no AND b.curriculum_category = c.subject_code "
					+"left join subject as d on b.curriculum_category = d.subject_code "
					+"where "
					+" a.admission_year = ? "
					+" and a.class_no = ? ");

			statement.setString(1, subjectCd);
			statement.setInt(2, admissionYear-2000);
			statement.setString(3, classNum);

			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

			// リザルトセットを全権走査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Score score = new Score();
				score.setStudentNo(rSet.getString("student_no"));
				score.setStudentName(rSet.getString("student_name"));
				score.setStudyYear(rSet.getString("study_year"));
				score.setSubjectCode(rSet.getString("curriculum_category"));
				score.setSubjectName(rSet.getString("subject_name"));
				score.setMonth(rSet.getInt("month"));
				score.setPoint(rSet.getInt("point"));
				list.add(score);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public List<Score> getClassScoreToCsv(int admissionYear,String classNum,String subjectCd) throws Exception {

		// リストを初期化
		List<Score> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT "
					+"concat(main.admission_year,'-',main.student_no) as student_no "
					+",main.student_name"
					+",substring(c.study_year,1,1) as study_year"
					+",a.month"
					+",a.subject_code"
					+",b.subject_name"
					+",a.point"
					+",main.class_no"
					+",a.extraction_date"
					+" FROM student as main"
					+" left join score as a on main.admission_year = a.admission_year and main.student_no = a.student_no"
					+" left join subject as b on a.subject_code = b.subject_code"
					+" inner join (select * from syllabus where curriculum_category = ?) as c on main.admission_year = c.admission_year"
					+" where main.admission_year = ? and main.class_no = ? ");

			statement.setString(1, subjectCd);
			statement.setInt(2, admissionYear-2000);
			statement.setString(3, classNum);

			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

			// リザルトセットを全権走査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Score score = new Score();
				score.setStudentNo(rSet.getString("student_no"));
				score.setStudentName(rSet.getString("student_name"));
				score.setStudyYear(rSet.getString("study_year"));
				score.setMonth(rSet.getInt("month"));
				score.setSubjectCode(rSet.getString("subject_code"));
				score.setSubjectName(rSet.getString("subject_name"));
				score.setPoint(rSet.getInt("point"));
				score.setClassName(rSet.getString("class_no"));
				score.setExtractionDate(rSet.getDate("extraction_date"));
				list.add(score);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public boolean save(List<Score> list) throws Exception {

		try {
			for (Score score : list) {
				// コネクションを確立
				Connection connection = getConnection();
				// saveメソッドで情報を保存
				save(score, connection);
			}
		} catch (Exception e) {
			throw e;
		}

		return true;
	}

	private boolean save(Score score, Connection connection) throws Exception {

		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		try {
			// データベースからテスト情報を取得
			Score old = getTest(score.getStudentNo(), score.getSubjectCode(),score.getAdmissionYear());
			if (old.getStudentNo() == null) {
				// 学生が存在しなかった場合

				// プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("INSERT INTO score(subject_code, admission_year, student_no, month, point, extraction_date)VALUES (?, ?, ?, ?, ?, ?)");
				// プリペアードステートメントに値をバインド
				statement.setString(1, score.getSubjectCode());
				statement.setInt(2, score.getAdmissionYear());
				statement.setString(3, score.getStudentNo());
				statement.setInt(4, score.getMonth());
				statement.setInt(5, score.getPoint());
				statement.setDate(6, score.getExtractionDate());
			} else {
				// テストが存在した場合
				// プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update score set month = ? , point = ? ,extraction_date = ? where subject_code = ? and admission_year = ? and student_no = ?");
				// プリペアードステートメントに値をバインド
				statement.setInt(1, score.getMonth());
				statement.setInt(2, score.getPoint());
				statement.setDate(3, score.getExtractionDate());
				statement.setString(4, score.getSubjectCode());
				statement.setInt(5, score.getAdmissionYear());
				statement.setString(6, score.getStudentNo());

			}

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}

	private Score getTest(String studentNo, String subjectCode, int admissionYear) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		// リストを初期化
		Score score = new Score();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from score where subject_code = ? and admission_year = ? and student_no = ?");

			statement.setString(1, subjectCode);
			statement.setInt(2, admissionYear);
			statement.setString(3, studentNo);

			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

			// リザルトセットを全権走査
			if (rSet.next()) {
				score.setStudentNo(rSet.getString("student_no"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return score;
	}

	public void deleteScore(String studentNo, String subjectCode, int admissionYear) throws Exception{
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("delete from score where subject_code = ? and admission_year = ? and student_no = ?");

			statement.setString(1, subjectCode);
			statement.setInt(2, admissionYear);
			statement.setString(3, studentNo);

			// プリペアードステートメントを実行
			int count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
	}

}
