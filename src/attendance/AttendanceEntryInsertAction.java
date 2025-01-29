package attendance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AttendanceDao;
import tool.Action;

public class AttendanceEntryInsertAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");

        Map<String, String> labelToCodeMap = new HashMap<>();
        labelToCodeMap.put("欠", "1");
        labelToCodeMap.put("遅", "2");
        labelToCodeMap.put("早", "3");
        labelToCodeMap.put("他", "4");
        labelToCodeMap.put("-", "9");

        try (Connection con = new AttendanceDao().getConnection()) {
            con.setAutoCommit(false); // トランザクション開始

            String sql = "INSERT INTO attendance VALUES (?, ?, ?, ?) ON CONFLICT (student_no, processing_date) DO UPDATE SET attendance_status = COALESCE(EXCLUDED.attendance_status, attendance.attendance_status)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                Enumeration<String> params = request.getParameterNames();
                int insertCount = 0; // 追加・更新件数カウント

                while (params.hasMoreElements()) {
                    String paramName = params.nextElement();
                    System.out.println("Received param: " + paramName + " = " + request.getParameter(paramName)); // 🔍 デバッグ用
                    if (paramName.startsWith("attendance_")) {
                        String[] parts = paramName.split("_");
                        if (parts.length == 3) {
                            String dateStr = parts[2];
                            String studentNo = parts[1];
                            String status = request.getParameter(paramName).trim();

                            if (!status.isEmpty() && labelToCodeMap.containsKey(status)) {
                                String statusCode = labelToCodeMap.get(status);

                                if (statusCode == null) {
                                    System.out.println("NULL DETECTED for " + studentNo + " on " + dateStr);
                                    continue;
                                }

                                System.out.println("Inserting: " + dateStr + " | " + studentNo + " | " + statusCode);

                                ps.setDate(1, Date.valueOf(dateStr));
                                ps.setInt(2, 24); // 例: 入学年度
                                ps.setString(3, studentNo);
                                ps.setString(4, statusCode);
                                ps.addBatch();
                                insertCount++;
                            }
                        }
                    }
                }

                if (insertCount > 0) {
                    int[] results = ps.executeBatch();
                    con.commit(); // コミット
                    response.getWriter().write("出欠データを登録しました！ " + results.length + " 件登録");
                } else {
                    response.getWriter().write("登録するデータがありませんでした。");
                }
            } catch (Exception e) {
                con.rollback(); // エラー発生時はロールバック
                e.printStackTrace();
                response.getWriter().write("エラー: " + e.getMessage());
            }
        }
    }
}
