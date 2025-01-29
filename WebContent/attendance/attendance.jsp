	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html>

	<head>
	    <title>出欠席処理結果</title>
	    <script src="attendance.js"></script> <!-- JS ファイルを読み込む -->
	</head>

	<body>
	    <pre>
	        入学年度: ${admissionYear}&#009;&#009;クラス: ${processingClass}&#009;&#009;処理年月: ${processingYear}
	    </pre>

	    <form action="AttendanceEntryInsert.action" method="post">
	        <div style="overflow-x: scroll;">
	            <c:set var="codeToLabelMap" value="${{
	                '1':'欠',
	                '2':'遅',
	                '3':'早',
	                '4':'他',
	                '9':'-'
	            }}" />
	            <table border="1" style="white-space: nowrap;">
	                <tr>
	                    <th>備考</th>
	                    <th>学籍<br>番号</th>
	                    <th>氏名</th>
	                    <th>前月累計</th>
	                    <c:forEach var="day" begin="1" end="${lastDayOfMonth}">
	                        <th>${day}<br>日</th>
	                    </c:forEach>
	                    <th>欠席</th>
	                    <th>遅刻</th>
	                    <th>早退</th>
	                    <th>他決</th>
	                    <th>今月累計</th>
	                    <th>総累計</th>
	                </tr>
	                <c:forEach var="student" items="${studentList}">
	                    <c:set var="totalAbsence" value="0" />
	                    <c:set var="lateCount" value="0" />
	                    <c:set var="earlyLeaveCount" value="0" />
	                    <c:set var="otherAbsenceCount" value="0" />

	                    <tr>
	                        <td class="remarks" style="color:red;"></td>
	                        <td>${student.studentNo}</td>
	                        <td>${student.studentName}</td>
	                        <td class="previous-total" data-value="${student.totalAbsences}">${student.totalAbsences}</td> <!-- **前月累計（数値のまま）** -->

	                        <c:forEach var="day" begin="1" end="${lastDayOfMonth}">
<c:set var="formattedDay" value="${processingYear}-${String.format('%02d', day)}" />
	                            <td>
<c:set var="attendanceFound" value="false" />
<c:forEach var="attendance" items="${attendanceList}">
    <c:if test="${attendance.studentNo == student.studentNo && attendance.processingDate == formattedDay}">
        <c:set var="attendanceFound" value="true" />
        <input type="text"
            value="${codeToLabelMap[attendance.attendanceStatus]}"
            data-original="${attendance.attendanceStatus}"
            class="attendance-input" style="width:2em;" name="attendance_${student.studentNo}_${formattedDay}">
    </c:if>
</c:forEach>

<c:if test="${!attendanceFound}">
    <input type="text" value="" class="attendance-input" style="width:2em; name="attendance_${student.studentNo}_${formattedDay}"">
</c:if>
	                            </td>
	                        </c:forEach>

	                        <td class="absence-total">${totalAbsence}</td>
	                        <td class="late-total">${lateCount}</td>
	                        <td class="early-leave-total">${earlyLeaveCount}</td>
	                        <td class="other-absence-total">${otherAbsenceCount}</td>
	                        <td class="monthly-total" data-value="${totalAbsence + (lateCount / 3) + (earlyLeaveCount / 3)}">${totalAbsence + (lateCount / 3) + (earlyLeaveCount / 3)}</td>
	                        <td class="overall-total" data-value="${student.totalAbsences + totalAbsence + (lateCount / 3) + (earlyLeaveCount / 3)}">${student.totalAbsences + totalAbsence + (lateCount / 3) + (earlyLeaveCount / 3)}</td>
	                    </tr>

	                </c:forEach>
	            </table>
	        </div>
	        <div style="text-align:right;">
	            <button type="button" id="execute" onclick="submit();">実行</button>
<button type="button" onclick="location.href='../log/menu.jsp';">終了</button>

	        </div>
	    </form>
	</body>
	</html>
