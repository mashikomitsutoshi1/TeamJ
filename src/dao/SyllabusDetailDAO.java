package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.SyllabusDetail;

public class SyllabusDetailDAO extends Dao{

	/**
	 * シラバス詳細検索処理
	 * @return list		検索結果（シラバス詳細のリスト）
	 * @throws Exception
	 */
	public List<SyllabusDetail> search(int admission_year) throws Exception {
		System.out.println("開始：SyllabusDetailDAO.search");

		// シラバス情報を保持するArrayListを宣言
		List<SyllabusDetail> list=new ArrayList<>();

		// データベースへの接続
		Connection con = getConnection();

		// SQLの生成
		PreparedStatement st;

		// 入学年度の指定有無に応じて処理を分岐
		st = con.prepareStatement(
					"SELECT * "
					+ "FROM SyllabusDetail "
					+ "JOIN SUBJECT "
					+ "ON   SyllabusDetail.SUBJECT_CODE = SUBJECT.SUBJECT_CODE "
					+ "WHERE admission_year=?"
		);

		st.setInt(1,admission_year);

		// ★デバッグ用★発行されるSQLをコンソールに表示
		System.out.println(st);

		// 結果を受け取るResultSetの宣言
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			//取得結果をArrayListに追加する
			SyllabusDetail p = new SyllabusDetail();
			p.setAdmission_year(rs.getInt("admission_year"));	// 入学年度
			p.setSubject_code(rs.getString("subject_code"));	// 科目コード
			p.setRequired_flg(rs.getString("required_flg"));	// 必須フラグ
			p.setSelect_flg(rs.getString("select_flg"));		// 選択フラグ
			p.setCourse_time(rs.getInt("course_time"));			// 履修時間
			p.setCredit(rs.getInt("credit"));					// 単位数
			p.setGrade(rs.getInt("grade"));						// 学年
			p.setSvkamoku(rs.getString("svkamoku"));			// SVKAMOKU

			p.setSubject_name(rs.getString("subject_name"));	// 科目名

			list.add(p);	//ArrayListに追加
		}

		// データベースの後処理
		st.close();
		con.close();

		// 学生リストの返却
		return list;
	}

	/**
	 * 成績の更新（削除→挿入）
	 * @param entranceYear	入学年度
	 * @return
	 */
	public int delIn(int admission_year,HttpServletRequest req){

		System.out.println("delIn:" + admission_year);

		try {
			// 年度を指定してシラバス情報を検索
			List<SyllabusDetail> list = search(admission_year);

			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);

			//  ----- 削除 -----
			System.out.println("削除開始");
			// SQLの生成
			PreparedStatement stDel = con.prepareStatement("DELETE "
					+ "FROM "
					+ "SYLLABUSDETAIL "
					+ "WHERE ADMISSION_YEAR    = ? "
					);

			stDel.setInt(1, admission_year);
			stDel.executeUpdate();

			//  ----- 挿入 -----

			System.out.println("挿入開始");
			// SQLの生成
			PreparedStatement st = con.prepareStatement("INSERT INTO SYLLABUSDETAIL VALUES(?,?,?,?,?,?,?,?)");

		    // フォームデータを配列として取得
	        String[] subject_code = req.getParameterValues("subject_code");
	        String[] required_flg = req.getParameterValues("required_flg");
	        String[] select_flg = req.getParameterValues("select_flg");
	        String[] course_time = req.getParameterValues("course_time");
	        String[] credit = req.getParameterValues("credit");
	        String[] grade = req.getParameterValues("grade");
	        String[] svkamoku = req.getParameterValues("svkamoku");

	        for (int i = 0; i < subject_code.length; i++) {
	            String ins_subject_code = subject_code[i];
	            System.out.println("subject_code[" + i + "] -> " + subject_code[i] );
	        	String ins_required_flg = required_flg[i];
	            String ins_select_flg = select_flg[i];
	            String ins_course_time = course_time[i];
	            String ins_credit = credit[i];
	            String ins_grade = grade[i];
	            String ins_svkamoku = svkamoku[i];

				st.setInt(1,admission_year);
				st.setString(2,ins_subject_code);
				st.setString(3,ins_required_flg);
				st.setString(4,ins_select_flg);
				st.setInt(5,Integer.parseInt( ins_course_time));
				st.setInt(6,Integer.parseInt( ins_credit));
				st.setInt(7,Integer.parseInt( ins_grade));
				st.setString(8, ins_svkamoku);

				System.out.println(st);
				st.executeUpdate();

	        }

			// コミットの実行
			con.commit();
			// データベース後処理
			stDel.close();
			st.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}

	public int del(int admission_year,HttpServletRequest req){

		System.out.println("del:" + admission_year);

		try {

			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);

			//  ----- 削除 -----
			System.out.println("削除開始");
			// SQLの生成
			PreparedStatement stDel = con.prepareStatement("DELETE "
					+ "FROM "
					+ "SYLLABUSDETAIL "
					+ "WHERE ADMISSION_YEAR    = ? "
					);

			stDel.setInt(1, admission_year);
			stDel.executeUpdate();

			// コミットの実行
			con.commit();
			// データベース後処理
			stDel.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}

	public int delete(int admission_year, String subject_code, HttpServletRequest req){

		System.out.println("del:" + admission_year);

		try {

			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);

			//  ----- 削除 -----
			System.out.println("削除開始");

			PreparedStatement stDel = con.prepareStatement("DELETE "
					+ "FROM "
					+ "SYLLABUSDETAIL "
					+ "WHERE ADMISSION_YEAR    = ? "
					+ "  AND subject_code      = ? "
					);

			stDel.setInt(1,admission_year);
			stDel.setString(2,subject_code);

			System.out.println(stDel);
			stDel.executeUpdate();



			// コミットの実行
			con.commit();
			// データベース後処理
			stDel.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}

	public int deleteBK(int admission_year, int subject_code, HttpServletRequest req){

		System.out.println("del:" + admission_year);

		try {

//			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);
//
//			//  ----- 削除 -----
			System.out.println("削除開始");

			PreparedStatement stDel = con.prepareStatement("DELETE "
					+ "FROM "
					+ "SYLLABUSDETAIL "
					+ "WHERE ADMISSION_YEAR    = ? "
					+ "  AND subject_code      = ? "
					);

			String[] deleteRows = req.getParameterValues("deleteRows");

			for (int i = 0; i < deleteRows.length; i++) {

				stDel.setInt(1,admission_year);
				stDel.setString(2,req.getParameter("subject_code_" + i));

				System.out.println(stDel);
				stDel.executeUpdate();

			}

			// コミットの実行
			con.commit();
			// データベース後処理
			stDel.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}

	public int ins(String admission_year,HttpServletRequest req){

		System.out.println("ins:" + admission_year);

		try {
			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);

			//  ----- 挿入 -----

			System.out.println("挿入開始");
			// SQLの生成
			PreparedStatement st = con.prepareStatement("INSERT INTO SYLLABUSDETAIL VALUES(?,?,?,?,?,?,?,?)");

	        String subject_code = req.getParameter("subject_code");
	        String required_flg = req.getParameter("required_flg");
	        String select_flg = req.getParameter("select_flg");
//	        String course_time = req.getParameter("course_time");
//	        String credit = req.getParameter("credit");
//	        String grade = req.getParameter("grade");
	        String svkamoku = req.getParameter("svkamoku");


	        String course_time = (req.getParameter("course_time") != "") ? req.getParameter("course_time") : "0";
	        String credit = (req.getParameter("credit") != "") ? req.getParameter("credit") : "0";
	        String grade = (req.getParameter("grade") != "") ? req.getParameter("grade") : "0";

	        System.out.println("1:" + subject_code);
            System.out.println("2:" + required_flg);
            System.out.println("3:" + select_flg);
            System.out.println("4:" + course_time);
            System.out.println("5:" + credit);
            System.out.println("6:" + grade);
            System.out.println("7:" + svkamoku);

            System.out.println("a");
			st.setInt(1,Integer.parseInt(admission_year));
            System.out.println("b");
			st.setString(2,subject_code);
            System.out.println("c");
			st.setString(3,required_flg);
            System.out.println("d");
			st.setString(4,select_flg);
            System.out.println("e");
			st.setInt(5,Integer.parseInt( course_time));
            System.out.println("f");
			st.setInt(6,Integer.parseInt( credit));
            System.out.println("g");
			st.setInt(7,Integer.parseInt( grade));
            System.out.println("g");
			st.setString(8, svkamoku);
            System.out.println("i");

			System.out.println(st);
			st.executeUpdate();

			// コミットの実行
			con.commit();
			// データベース後処理

			st.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}

	public int syllabusCopyIns(String admission_year,String saki_admission_year,HttpServletRequest req){

		System.out.println("ins:" + admission_year);
		System.out.println("ins:" + saki_admission_year);

		try {
			// データベースへ接続
			Connection con = getConnection();
			// オートコミットを解除する（削除と挿入の両方が完了したらコミットする）
			con.setAutoCommit(false);

			//  ----- 挿入 -----

			System.out.println("挿入開始");
			// SQLの生成
			PreparedStatement st = con.prepareStatement("INSERT INTO SYLLABUSDETAIL "
					+ "SELECT ?,subject_code,required_flg,select_flg,course_time,credit,grade,svkamoku "
					+ "FROM SYLLABUSDETAIL WHERE ADMISSION_YEAR = ?");

            System.out.println("a");
			st.setInt(1,Integer.parseInt(saki_admission_year));
            System.out.println("b");
			st.setInt(2,Integer.parseInt(admission_year));
            System.out.println("c");

			System.out.println(st);
			st.executeUpdate();

			// コミットの実行
			con.commit();
			// データベース後処理

			st.close();
			con.setAutoCommit(true);
			con.close();


		} catch (Exception e) {
			// 例外発生時の処理
			e.printStackTrace();
		}

		return 0;
	}




}
