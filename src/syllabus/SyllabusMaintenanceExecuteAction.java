package syllabus;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Syllabus;
import tool.Action;

public class SyllabusMaintenanceExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("開始：SyllabusMaintenanceExecuteAction.execute");

        // ユーザーのホームディレクトリを取得
        String userHome = System.getProperty("user.home");

        // Windowsの「ドキュメント」フォルダのパスを構築
        String filePath = Paths.get(userHome, "Documents", "AllSyllabus.csv").toString();

        // Shift-JISでCSVファイルを作成、または上書き
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "Shift_JIS"))) {

            HttpSession session = req.getSession();
            List<Syllabus> slist = (List<Syllabus>) session.getAttribute("slist");

            if (slist == null) {
                // slist が存在しない場合の処理
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "シラバスが存在しません");
            } else {
            	System.out.println("シラバスあった");
            }

            // ヘッダーを書き込み
          writer.write("シラバス一覧");
          writer.newLine(); // 改行
          writer.write("年度,科目数,課程区分,課程名,科名,コース名,コース名内訳名,修業年月");
          writer.newLine(); // 改行

            // 各学生の情報をCSV形式で書き込み
            for (Syllabus syllabus : slist) {
                writer.write(syllabus.getAdmission_year() + "," +
                		syllabus.getSubject_count() + "," +
                		syllabus.getCurriculum_category() + "," +
                		syllabus.getCurriculum_name() + "," +
                		syllabus.getDepartment_name() + "," +
                		syllabus.getCourse_name() + "," +
                		syllabus.getCourse_name_breakdown() + "," +
                		syllabus.getStudy_year() + "," +
                             "");
                writer.newLine(); // 改行
            }

          writer.close();


        }

		System.out.println("終了：SyllabusMaintenanceExecuteAction.execute");

		SyllabusMaintenanceAction sm = new SyllabusMaintenanceAction();
		sm.execute(req, res);

	}
}
