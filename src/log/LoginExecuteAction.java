package log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = "";
        String password = "";
        // フォームから送信されたデータを取得
        id = req.getParameter("id");
        password = req.getParameter("password");

        // TeacherDaoを使用して認証を行う
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = teacherDao.login(id, password);

        // 認証結果に応じた処理
        if (teacher != null) {
            // 認証成功時に、teacherオブジェクトをリクエスト属性に設定
            req.setAttribute("teacherInfo", teacher);

            // ユーザー名をリクエスト属性に設定
            req.setAttribute("userName", teacher.getName()); // teacher.getName() を適切なメソッドに置き換えてください

            // 管理者フラグを確認して適切なページにフォワード
            if ("1".equals(teacher.getAdminFlg())) {
                req.getRequestDispatcher("admin.jsp").forward(req, res);
            } else {
                req.getRequestDispatcher("user.jsp").forward(req, res);
            }
        } else {
            // 認証失敗の場合
            List<String> errors = new ArrayList<>();
            errors.add("IDまたはパスワードが確認できませんでした");
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);

            // ログイン画面に戻る
            req.getRequestDispatcher("log_in.jsp").forward(req, res);
        }
    }
}


/*
public class LoginExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String id = "";
    	String password = "";
    	// フォームから送信されたデータを取得
        id = req.getParameter("id");
        password = req.getParameter("password");

        // TeacherDaoを使用して認証を行う
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = teacherDao.login(id, password);

        // 認証結果に応じた処理
        if (teacher != null) {
            // 認証成功時に、teacherオブジェクトをリクエスト属性に設定
            req.setAttribute("teacherInfo", teacher);

            // メニュー画面に遷移
            req.getRequestDispatcher("menu.jsp").forward(req, res);
        } else {
        	// 認証失敗の場合
        	// エラーメッセージをセット
        	List<String> errors = new ArrayList<>();
        	errors.add("IDまたはパスワードが確認できませんでした");
        	req.setAttribute("errors", errors);
        	// 入力された教員IDをセット
        	req.setAttribute("id", id);

        	//フォワード
        	req.getRequestDispatcher("log_in.jsp").forward(req, res);
//        	url = "/log/login.jsp";
//        	req.getRequestDispatcher(url).forward(req, res);
        	// 認証失敗時、ログイン画面に戻る
            //res.sendRedirect("log_in.jsp?error=true");
        }

    }
}
*/
