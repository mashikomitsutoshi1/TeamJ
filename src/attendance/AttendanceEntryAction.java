package attendance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;
import tool.Action;

public class AttendanceEntryAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // リクエストパラメータの取得
	    String processingYear = request.getParameter("processingYear");
	    String processingClass = request.getParameter("processingClass");
	    String admissionYear = request.getParameter("admissionYear");
	    String studentNo = request.getParameter("studentNo");
	    String processingDateStr = request.getParameter("processingDate");

        // 処理年月を Date 型で保持
        String processingDateString = processingYear + "-01"; // 例: 2025-01-01
        java.sql.Date processingDate = java.sql.Date.valueOf(processingDateString);

        // 学生リストを取得
        AttendanceDao dao = new AttendanceDao();
        List<Student> studentList = dao.search(processingClass);
        List<Attendance> attendanceList = dao.search2();

        // java.sql.Date から YearMonth を取得
        LocalDate localDate = processingDate.toLocalDate(); // java.sql.Date -> LocalDate
        YearMonth yearMonth = YearMonth.from(localDate);    // LocalDate -> YearMonth
        int lastDayOfMonth = yearMonth.lengthOfMonth();     // その月の末日を取得
//        System.out.println("Last Day of Month: " + lastDayOfMonth);

        // リクエストスコープにデータを設定
        request.setAttribute("studentList", studentList);
        request.setAttribute("processingDate", processingDate);
        request.setAttribute("lastDayOfMonth", lastDayOfMonth);
        request.setAttribute("processingClass", processingClass);
        request.setAttribute("admissionYear", admissionYear);
        request.setAttribute("processingYear", processingYear);

        request.setAttribute("attendanceList", attendanceList);

        // 次のページへフォワード
        request.getRequestDispatcher("/attendance/attendance.jsp").forward(request, response);

	}
}
