package attendance;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;
import tool.Action;

public class AttendanceWriteToCsvAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // パラメータ取得
        String outputClass = request.getParameter("outputClass");
        String outputDateStr = request.getParameter("outputDate");

        if (outputClass == null || outputClass.isEmpty() || outputDateStr == null || outputDateStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "クラスまたは出力日が指定されていません");
            return;
        }

        // 出力基準日を解析
        LocalDate outputDate = LocalDate.parse(outputDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        YearMonth targetMonth = YearMonth.from(outputDate);
        int lastDayOfMonth = targetMonth.lengthOfMonth();

        // DAOを利用してDBからデータ取得
        AttendanceDao attendanceDao = new AttendanceDao();
        List<Student> studentList = attendanceDao.search(outputClass);
        List<Attendance> attendanceList = attendanceDao.search2();

        if (studentList == null || studentList.isEmpty()) {
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, "指定クラスの学生データがありません");
        	//レスポンス値をセット 6
            request.setAttribute("error1","指定クラスの学生データがありません");
            throw new Exception();
            //return;
        }

        // 出欠データをマッピング（studentNo -> (日付 -> ステータス)）
        Map<String, Map<Integer, String>> attendanceMap = new HashMap<>();
        for (Attendance att : attendanceList) {
            LocalDate processingDate;

            if (att.getProcessingDate() instanceof java.sql.Date) {
                processingDate = ((java.sql.Date) att.getProcessingDate()).toLocalDate();
            } else {
                processingDate = new java.sql.Date(att.getProcessingDate().getTime()).toLocalDate();
            }

            int dayOfMonth = processingDate.getDayOfMonth();

            attendanceMap
                .computeIfAbsent(att.getStudentNo(), k -> new HashMap<>())
                .put(dayOfMonth, att.getAttendanceStatus());
        }

     // CSVのレスポンスヘッダー設定
        response.setContentType("text/csv; charset=Shift_JIS");
        response.setHeader("Content-Disposition", "attachment; filename=attendance.csv");

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(response.getOutputStream(), "Shift_JIS")))) {

            // **ヘッダー行**
            writer.print("備考, 学籍番号, 氏名, 前月累計, ");
            for (int day = 1; day <= lastDayOfMonth; day++) {
                writer.printf("%d日,", day);
            }
            writer.println("欠席, 遅刻, 早退, 他決, 今月累計, 総累計"); // **改行**

            // **データ行**
            for (Student student : studentList) {
                System.out.println("出力中の学生: " + student.getStudentName());
                String studentNo = student.getStudentNo();
                Map<Integer, String> dailyAttendance = attendanceMap.getOrDefault(studentNo, new HashMap<>());

                int totalAbsences = 0, lateCount = 0, earlyLeaveCount = 0, otherAbsenceCount = 0;

                // **各学生の出力**
                writer.printf(",%s,%s,%.1f,", studentNo, student.getStudentName(), student.getTotalAbsences());

                // **日ごとの出席データ**
                for (int day = 1; day <= lastDayOfMonth; day++) {
                    String status = dailyAttendance.getOrDefault(day, "-");
                    writer.print(status + ",");

                    switch (status) {
                        case "1": totalAbsences++; break;
                        case "2": lateCount++; break;
                        case "3": earlyLeaveCount++; break;
                        case "4": otherAbsenceCount++; break;
                    }
                }

                int monthlyTotal = totalAbsences + (lateCount / 3) + (earlyLeaveCount / 3);
                double overallTotal = student.getTotalAbsences() + monthlyTotal;

                writer.println(String.format(",%s,%s,%s,%s,%s,%s", totalAbsences, lateCount, earlyLeaveCount,otherAbsenceCount,monthlyTotal,overallTotal));
}
        }
    }
}
