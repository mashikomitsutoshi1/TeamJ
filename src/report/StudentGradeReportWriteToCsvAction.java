package report;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentGradeReport;
import tool.Action;

public class StudentGradeReportWriteToCsvAction extends Action {

	/**
	 * このメソッドはHTTPリクエストを処理し、学生とそのコース名の情報を取得してリクエスト属性に設定します。
	 *
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @return 処理結果としてのJSPページの名前
	 * @throws Exception 処理中に発生する可能性のある例外
	 */
	public void execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

        // ユーザーのホームディレクトリを取得
        String userHome = System.getProperty("user.home");

        // Windowsの「ドキュメント」フォルダのパスを構築
        String filePath = Paths.get(userHome, "Documents", "studentgradereport.csv").toString();

        // Shift-JISでCSVファイルを作成、または上書き
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))) {

            HttpSession session = request.getSession();
            List<StudentGradeReport> sglist = (List<StudentGradeReport>) session.getAttribute("sglist");

            if (sglist == null) {
                // slist が存在しない場合の処理
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "学生成績表が存在しません");
            }

            // ヘッダーを書き込み
            writer.write("学生成績表");
            writer.newLine(); // 改行
            writer.write("入学年度,学籍番号,学科,コース,氏名,生年月日,入学,卒業,科目名,単位,評価");
            writer.newLine(); // 改行

            // 各学生のデータをCSVに書き込み
            for (StudentGradeReport report : sglist) {
                int admissionYear = report.getAdmissionYear();
                String studentNo = report.getStudentNo();
                String departmentName = report.getDepartmentName();
                String courseName = report.getCourseName();
                String studentName = report.getStudentName();
                int birthdayYear = report.getBirthdayYear();
                int birthdayMonth = report.getBirthdayMonth();
                int birthdayDay = report.getBirthdayDay();
                int graduationYear = report.getGraduationYear();

                // 各科目情報もループで書き込み
                for ( Map<String, Object> subject : report.getGradeReport()) {
                    String subjectName = (String) subject.get("subject");
                    int credit = (int) subject.get("credit");
                    String grading = (String) subject.get("grading");

                    // CSV行を作成して書き込み
                    writer.write(String.format("%d,%s,%s,%s,%s,%d/%d/%d,%d,%d,%s,%d,%s%n",
                            admissionYear, studentNo, departmentName, courseName, studentName,
                            birthdayYear, birthdayMonth, birthdayDay,
                            admissionYear, graduationYear, subjectName, credit, grading));
                }
            }
        }
        System.out.println("CSVファイルが " + filePath + " に書き込まれました。");

		// 処理が完了したら"StudentGradeReport.jsp"ページを返す
		// return "StudentGradeReport.jsp";
   		request.getRequestDispatcher("StudentGradeReport.jsp").forward(request, response);

	}
}
