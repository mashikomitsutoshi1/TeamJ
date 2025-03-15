package syllabus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SyllabusDetailMaintenanceDelInAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

        // フォームデータを配列として取得
        String[] subject_code = req.getParameterValues("subject_code");

        for (int i = 0; i < subject_code.length; i++) {
            String code = subject_code[i];

            System.out.println(code);
        }

        req.getRequestDispatcher("SyllabusDetailMaintenance.jsp").forward(req, res);

	}


}
