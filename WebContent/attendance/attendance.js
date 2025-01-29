document.addEventListener("DOMContentLoaded", function () {
    const codeToLabelMap = {
    	"1": "欠", // 欠席
        "2": "遅", // 遅刻
        "3": "早", // 早退
        "4": "他", // その他欠席
        "9": "-"  // 休日
    };

    // 出席入力フィールドを取得
    const inputFields = document.querySelectorAll(".attendance-input");

    inputFields.forEach(inputField => {
        // **リアルタイム変換**
        inputField.addEventListener("input", function () {
            const inputValue = this.value.trim();
            if (codeToLabelMap.hasOwnProperty(inputValue)) {
                this.value = codeToLabelMap[inputValue]; // 変換処理
            }
            updateTotals(this.closest("tr"));
        });

        // **Enterキーで次の行へ移動**
        inputField.addEventListener("keypress", function (e) {
            if (e.key === "Enter") {
                e.preventDefault();
                const inputValue = this.value.trim();
                if (codeToLabelMap.hasOwnProperty(inputValue)) {
                    this.value = codeToLabelMap[inputValue]; // 変換処理
                }
                updateTotals(this.closest("tr"));
                moveToNextRow(this);
            }
        });
    });

    function initializePage() {
        document.querySelectorAll("tr").forEach(row => updateTotals(row));

        document.querySelectorAll(".previous-total").forEach(cell => {
            const value = parseFloat(cell.getAttribute("data-value")) || 0;
            cell.textContent = formatFraction(value);
        });
    }

    function updateTotals(row) {
        let totalAbsence = 0, lateCount = 0, earlyLeaveCount = 0, otherAbsenceCount = 0;

        row.querySelectorAll(".attendance-input").forEach(input => {
            const value = input.value.trim();
            if (value === "欠") totalAbsence++;
            if (value === "遅") lateCount++;
            if (value === "早") earlyLeaveCount++;
            if (value === "他") otherAbsenceCount++;
        });

        const previousTotal = parseFloat(row.querySelector(".previous-total")?.getAttribute("data-value")) || 0;
        const monthlyTotal = totalAbsence + (lateCount / 3) + (earlyLeaveCount / 3);
        const overallTotal = previousTotal + monthlyTotal;

        setCellText(row, ".absence-total", totalAbsence);
        setCellText(row, ".late-total", lateCount);
        setCellText(row, ".early-leave-total", earlyLeaveCount);
        setCellText(row, ".other-absence-total", otherAbsenceCount);
        setCellText(row, ".monthly-total", formatFraction(monthlyTotal));
        setCellText(row, ".overall-total", formatFraction(overallTotal));

        updateRemarks(row, overallTotal);
    }

    function setCellText(row, selector, value) {
        const cell = row.querySelector(selector);
        if (cell) cell.textContent = value;
    }

    function formatFraction(value) {
        const whole = Math.floor(value);
        const fraction = value - whole;
        if (fraction === 0) return `${whole}`;
        if (fraction < 0.34) return `${whole} 1/3`;
        if (fraction < 0.67) return `${whole} 2/3`;
        return `${whole + 1}`;
    }

    function moveToNextRow(inputField) {
        const row = inputField.closest("tr");
        const cellIndex = [...row.cells].indexOf(inputField.closest("td"));
        let nextRow = row.nextElementSibling || document.querySelectorAll("tr")[1];

        if (nextRow) {
            const nextCell = nextRow.cells[cellIndex];
            if (nextCell) nextCell.querySelector("input")?.focus();
        }
    }

    function updateRemarks(row, overallTotal) {
        const remarksCell = row.querySelector("td:nth-child(1)");
        if (remarksCell) {
            if (overallTotal >= 100) remarksCell.textContent = "退学";
            else if (overallTotal >= 80) remarksCell.textContent = "退勧";
            else if (overallTotal >= 60) remarksCell.textContent = "再戒告";
            else if (overallTotal >= 40) remarksCell.textContent = "戒告";
            else if (overallTotal >= 20) remarksCell.textContent = "訓告";
            else remarksCell.textContent = "";
        }
    }

    // **送信処理**
    document.querySelector("button#execute").addEventListener("click", function (e) {
        e.preventDefault();

        const form = document.querySelector("form");

        // **データをJSONとして送信**
        const attendanceData = Array.from(document.querySelectorAll(".attendance-input")).map(input => ({
            studentNo: input.closest("tr").dataset.studentNo, // 学籍番号を取得
            date: input.dataset.date, // 日付（カラムごとのデータ）
            status: input.value.trim() // 出席情報
        }));

        fetch(form.action, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ attendance: attendanceData }) // JSON形式で送信
        })
        .then(() => {
            alert("登録できました");
            location.reload();
        })
        .catch(error => console.error("エラー:", error));
    });

    initializePage();
});
