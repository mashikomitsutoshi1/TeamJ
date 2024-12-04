package report;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import tool.Action;

public class ClassListWriteToCsvAction extends Action {

	/**
	 * このメソッドはHTTPリクエストを処理し、学生とそのコース名の情報を取得してリクエスト属性に設定します。
	 *
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @return 処理結果としてのJSPページの名前
	 * @throws Exception 処理中に発生する可能性のある例外
	 */
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

        // ユーザーのホームディレクトリを取得
        String userHome = System.getProperty("user.home");

        // Windowsの「ドキュメント」フォルダのパスを構築
        String filePath = Paths.get(userHome, "Documents", "students.csv").toString();

        // Shift-JISでCSVファイルを作成、または上書き
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))) {

            HttpSession session = request.getSession();
            List<Student> slist = (List<Student>) session.getAttribute("slist");

            if (slist == null) {
                // slist が存在しない場合の処理
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "学生リストが存在しません");
            }

            // ヘッダーを書き込み
          writer.write("名簿データ");
          writer.newLine(); // 改行
//        writer.write("AdmissionYear,StudentNo,StudentName,StudentNameKana,ClassNo,DispositionStatus,TotalAbsences");
          writer.write("入学年度,学籍番号,氏名,氏名カナ,在席状況");
          writer.newLine(); // 改行

            // 各学生の情報をCSV形式で書き込み
            for (Student student : slist) {
                writer.write(student.getAdmissionYear() + "," +
                             student.getStudentNo() + "," +
                             student.getStudentName() + "," +
                             student.getStudentNameKana() + "," +
//                             student.getClassNo() + "," +
//                             student.getDispositionStatus() + "," +
//                             student.getTotalAbsences());
                             "在学中");
                writer.newLine(); // 改行
            }
        }

        System.out.println("CSVファイルが " + filePath + " に書き込まれました。");

		// 処理が完了したら"list.jsp"ページを返す
		return "Studentlist.jsp";
	}
}
