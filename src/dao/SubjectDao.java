package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

	public List<Subject> getAll() throws Exception {

		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select subject_code ,subject_name from subject");
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

			// リザルトセットを全権走査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Subject subject = new Subject();
				subject.setSubjectCd(rSet.getString("subject_code"));
				subject.setSubjectName(rSet.getString("subject_name"));
				list.add(subject);
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


}
