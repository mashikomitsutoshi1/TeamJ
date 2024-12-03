package sys_con;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;
import tool.Action;

public class UpdateRetireStatusAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TeacherDao teacherDao = new TeacherDao();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // すべての教員の retireFlg と adminFlg をリセット
            teacherDao.resetAllRetireFlg();
            teacherDao.resetAllAdminFlg();

            Enumeration<String> parameterNames = req.getParameterNames();
            boolean hasError = false;

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();

                if (paramName.startsWith("retireFlg_")) {
                    String teacherId = paramName.substring("retireFlg_".length());
                    String retireFlg = req.getParameter(paramName) != null ? "1" : "0";
                    teacherDao.updateRetireFlg(teacherId, retireFlg);
                }
                if (paramName.startsWith("adminFlg_")) {
                    String teacherId = paramName.substring("adminFlg_".length());
                    String adminFlg = req.getParameter(paramName) != null ? "1" : "0";
                    teacherDao.updateAdminFlg(teacherId, adminFlg);
                }
                if (paramName.startsWith("maintenanceDeadline_")) {
                    String teacherId = paramName.substring("maintenanceDeadline_".length());
                    String maintenanceDeadline = req.getParameter(paramName);

                    if (maintenanceDeadline == null || maintenanceDeadline.isEmpty()) {
                        hasError = true;
                        req.setAttribute("errorMessage", "保守期限は必須です。");
                    } else {
                        try {
                            java.util.Date deadlineDate = dateFormat.parse(maintenanceDeadline);
                            teacherDao.updateMaintenanceDeadline(teacherId, new java.sql.Date(deadlineDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            hasError = true;
                            req.setAttribute("errorMessage", "保守期限の日付形式が正しくありません。");
                        }
                    }
                }
            }

            if (hasError) {
                req.getRequestDispatcher("error_page.jsp").forward(req, res);
            } else {
                req.getRequestDispatcher("update_success.jsp").forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "システムエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("error_page.jsp").forward(req, res);
        }
    }
}


/*
package sys_con;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;
import tool.Action;

public class UpdateRetireStatusAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TeacherDao teacherDao = new TeacherDao();

        // すべての教員の retireFlg を0にリセット
        teacherDao.resetAllRetireFlg();

       // すべての教員の retireFlg を0にリセット
        teacherDao.resetAllAdminFlg();

        // リクエストから全パラメータ名を取得
        Enumeration<String> parameterNames = req.getParameterNames();

     // 退職者フラグと管理者フラグの状態を更新
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if (paramName.startsWith("retireFlg_")) {
                // retireFlg の更新処理
                String teacherId = paramName.substring("retireFlg_".length());
                String retireFlg = req.getParameter(paramName) != null ? "1" : "0";
                teacherDao.updateRetireFlg(teacherId, retireFlg);
            }
            if (paramName.startsWith("adminFlg_")) {
                // adminFlg の更新処理
                String teacherId = paramName.substring("adminFlg_".length());
                String adminFlg = req.getParameter(paramName) != null ? "1" : "0";
                teacherDao.updateAdminFlg(teacherId, adminFlg);
            }
        }


        // 成功ページにリダイレクト
        req.getRequestDispatcher("update_success.jsp").forward(req, res);
    }
}

*/