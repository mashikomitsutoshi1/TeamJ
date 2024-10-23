package bean;

import java.sql.Date;

public class Teacher {
    private String id;
    private String password;
    private String name;
    private String adminFlg;
    private Date maintenanceDeadline;
    private String retireFlg;

    // Getter and Setter methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminFlg() {
        return adminFlg;
    }

    public void setAdminFlg(String adminFlg) {
        this.adminFlg = adminFlg;
    }

    public Date getMaintenanceDeadline() {
        return maintenanceDeadline;
    }

    public void setMaintenanceDeadline(Date maintenanceDeadline) {
        this.maintenanceDeadline = maintenanceDeadline;
    }

    public String getRetireFlg() {
        return retireFlg;
    }

    public void setRetireFlg(String retireFlg) {
        this.retireFlg = retireFlg;
    }
}
