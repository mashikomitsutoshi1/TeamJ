package attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AttendanceDao;
import tool.Action;

public class AttendanceRateCalculationAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String startYearMonth = request.getParameter("startMonth"); // yyyy-MM
        String endYearMonth = request.getParameter("endMonth");     // yyyy-MM
        String processingClass = request.getParameter("processingClass");

        AttendanceDao dao = new AttendanceDao();
        Connection con = dao.getConnection();

        String sql = "SELECT TO_CHAR(a.processing_date, 'YYYY-MM') AS month, " +
                     "COUNT(CASE WHEN a.attendance_status = '1' THEN 1 END) AS absence_count, " +
                     "COUNT(CASE WHEN a.attendance_status = '2' THEN 1 END) AS late_count, " +
                     "COUNT(CASE WHEN a.attendance_status = '3' THEN 1 END) AS early_leave_count, " +
                     "COUNT(*) AS total_days " +
                     "FROM attendance a " +
                     "JOIN student s ON a.student_no = s.student_no " +
                     "WHERE TO_CHAR(a.processing_date, 'YYYY-MM') BETWEEN ? AND ? " +
                     "AND s.class = ? " +
                     "GROUP BY month " +
                     "ORDER BY month";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, startYearMonth);
        ps.setString(2, endYearMonth);
        ps.setString(3, processingClass);
        ResultSet rs = ps.executeQuery();

        // リストにデータを格納
        List<Map<String, Object>> attendanceRates = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            int absenceCount = rs.getInt("absence_count");
            int lateCount = rs.getInt("late_count");
            int earlyLeaveCount = rs.getInt("early_leave_count");
            int totalDays = rs.getInt("total_days");

            // 遅刻・早退を欠席換算 (3回で1カウント)
            double adjustedAbsence = absenceCount + ((lateCount + earlyLeaveCount) / 3.0);

            // 出席率算出
            double attendanceRate = ((totalDays - adjustedAbsence) / (double) totalDays) * 100;

            record.put("month", rs.getString("month"));
            record.put("absence_count", absenceCount);
            record.put("late_count", lateCount);
            record.put("early_leave_count", earlyLeaveCount);
            record.put("adjusted_absence", adjustedAbsence);
            record.put("attendance_rate", attendanceRate);

            attendanceRates.add(record);
        }

        request.setAttribute("attendanceRates", attendanceRates);
        request.setAttribute("startMonth", startYearMonth);
        request.setAttribute("endMonth", endYearMonth);
        request.setAttribute("processingClass", processingClass);

        request.getRequestDispatcher("/attendance/attendancerate.jsp").forward(request, response);

        rs.close();
        ps.close();
        con.close();
    }
}
