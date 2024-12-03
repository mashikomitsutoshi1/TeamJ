package bean;

import java.sql.Date;

public class Teacher {
    // 教員ID
    private String id;
    
    // パスワード
    private String password;
    
    // 教員名
    private String name;
    
    // 管理者フラグ (管理者権限があるかどうか)
    private String adminFlg;
    
    // 保守期限 (システムのメンテナンス期限)
    private Date maintenanceDeadline;
    
    // 退職フラグ (退職済みかどうかのステータス)
    private String retireFlg;

    // 教員IDを取得する
    public String getId() {
        return id;
    }

    // 教員IDを設定する
    public void setId(String id) {
        this.id = id;
    }

    // パスワードを取得する
    public String getPassword() {
        return password;
    }

    // パスワードを設定する
    public void setPassword(String password) {
        this.password = password;
    }

    // 教員名を取得する
    public String getName() {
        return name;
    }

    // 教員名を設定する
    public void setName(String name) {
        this.name = name;
    }

    // 管理者フラグを取得する
    public String getAdminFlg() {
        return adminFlg;
    }

    // 管理者フラグを設定する
    public void setAdminFlg(String adminFlg) {
        this.adminFlg = adminFlg;
    }

    // 保守期限を取得する
    public Date getMaintenanceDeadline() {
        return maintenanceDeadline;
    }

    // 保守期限を設定する
    public void setMaintenanceDeadline(Date maintenanceDeadline) {
        this.maintenanceDeadline = maintenanceDeadline;
    }

    // 退職フラグを取得する
    public String getRetireFlg() {
        return retireFlg;
    }

    // 退職フラグを設定する
    public void setRetireFlg(String retireFlg) {
        this.retireFlg = retireFlg;
    }
}
