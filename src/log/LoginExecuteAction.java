package log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        // TeacherDaoを使用して認証を行う
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = null;
        List<String> errors = new ArrayList<>();

        try {
            teacher = teacherDao.login(id, password);
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("システムエラーが発生しました。もう一度お試しください。");
        }

        // 認証結果に応じた処理
        if (teacher != null) {
            HttpSession session = req.getSession();
            session.setAttribute("teacherInfo", teacher);
            session.setAttribute("userName", teacher.getName());
            session.setAttribute("adminFlg", teacher.getAdminFlg());

            // menu.jspにフォワード
            req.getRequestDispatcher("menu.jsp").forward(req, res);
        } else {
            Teacher checkTeacher = null;
            try {
                checkTeacher = teacherDao.getTeacherById(id);
            } catch (Exception e) {
                e.printStackTrace();
                errors.add("システムエラーが発生しました。もう一度お試しください。");
            }

            if (checkTeacher != null && "1".equals(checkTeacher.getRetireFlg())) {
                errors.add("そのIDは無効です");
            } else {
                errors.add("IDまたはパスワードが確認できませんでした");
            }
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);

            // ログイン画面に戻る
            req.getRequestDispatcher("log_in.jsp").forward(req, res);
        }
    }
}
