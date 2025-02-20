package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Syllabus;

public class SyllabusDAO extends Dao{

	/**
	 * シラバス一覧検索処理
	 * @return list		検索結果（シラバスのリスト）
	 * @throws Exception
	 */
	public List<Syllabus> search() throws Exception {

		System.out.println("SyllabusDAO.search開始");

		// シラバス情報を保持するArrayListを宣言
		List<Syllabus> list=new ArrayList<>();

		// データベースへの接続
		Connection con = getConnection();

		// SQLの生成
		PreparedStatement st;

		// 入学年度の指定有無に応じて処理を分岐
		st = con.prepareStatement(
					"SELECT * "
					+ "FROM SYLLABUS "
					+ "ORDER BY ADMISSION_YEAR"
		);

		// ★デバッグ用★発行されるSQLをコンソールに表示
		System.out.println(st);

		// 結果を受け取るResultSetの宣言
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			//取得結果をArrayListに追加する
			Syllabus p = new Syllabus();
			p.setAdmission_year(rs.getInt("admission_year"));
			p.setCurriculum_category(rs.getString("curriculum_category"));
			p.setCurriculum_name(rs.getString("curriculum_name"));
			p.setDepartment_name(rs.getString("department_name"));
			p.setCourse_name(rs.getString("course_name"));
			p.setCourse_name_breakdown(rs.getString("course_name_breakdown"));
			p.setStudy_year(rs.getString("study_year"));

			// 科目数を取得するための処理
			st = con.prepareStatement(
						"SELECT count(*) AS CNT "
						+ "FROM SYLLABUSDETAIL "
						+ "WHERE admission_year = ? "
						+ "GROUP BY admission_year"
			);
			st.setInt(1,rs.getInt("admission_year"));
			ResultSet rs2 = st.executeQuery();

			// ★デバッグ用★発行されるSQLをコンソールに表示
			System.out.println(st);

			while(rs2.next()){
				p.setSubject_count(rs2.getInt("CNT"));
			}

			list.add(p);	//ArrayListに追加
		}

		// データベースの後処理
		st.close();
		con.close();

		// シラバスリストの返却
		return list;
	}

	/**
	 * シラバス一覧検索処理（入学年度指定）
	 * @return list		検索結果（シラバスのリスト）
	 * @param admission_year 入学年度
	 * @throws Exception
	 */
	public List<Syllabus> search(int admission_year) throws Exception {

		System.out.println("search2開始");

		// シラバス情報を保持するArrayListを宣言
		List<Syllabus> list=new ArrayList<>();

		// データベースへの接続
		Connection con = getConnection();

		// SQLの生成
		PreparedStatement st;

		// 入学年度の指定有無に応じて処理を分岐
		st = con.prepareStatement(
					"SELECT * "
					+ "FROM SYLLABUS "
					+ "WHERE admission_year = ? "
		);

		st.setInt(1,admission_year);

		// ★デバッグ用★発行されるSQLをコンソールに表示
		System.out.println(st);

		// 結果を受け取るResultSetの宣言
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			//取得結果をArrayListに追加する
			Syllabus p = new Syllabus();
			p.setAdmission_year(rs.getInt("admission_year"));
			p.setCurriculum_category(rs.getString("curriculum_category"));
			p.setCurriculum_name(rs.getString("curriculum_name"));
			p.setDepartment_name(rs.getString("department_name"));
			p.setCourse_name(rs.getString("course_name"));
			p.setCourse_name_breakdown(rs.getString("course_name_breakdown"));
			p.setStudy_year(rs.getString("study_year"));

			// 科目数を取得するための処理
			st = con.prepareStatement(
						"SELECT count(*) AS CNT "
						+ "FROM SYLLABUSDETAIL "
						+ "WHERE admission_year = ? "
						+ "GROUP BY admission_year"
			);
			st.setInt(1,rs.getInt("admission_year"));

			ResultSet rs2 = st.executeQuery();

			// ★デバッグ用★発行されるSQLをコンソールに表示
			System.out.println(st);

			while(rs2.next()){
				p.setSubject_count(rs2.getInt("CNT"));
			}

			list.add(p);	//ArrayListに追加
		}

		// データベースの後処理
		st.close();
		con.close();

		// 学生リストの返却
		return list;
	}


}
