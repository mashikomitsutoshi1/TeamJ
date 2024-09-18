package bean;

public class Teacher {
    private String ID;
    private String PASSWORD;
    private String NAME;
    private String ADMIN_FLG;
    private String MAINTENANCE_DEADLINE;
    private String RETIRE_FLG;

    // Getter and Setter methods

    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getName() {
        return NAME;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

    public String getAdminFlg() {
        return ADMIN_FLG;
    }

    public void setAdminFlg(String ADMIN_FLG) {
        this.ADMIN_FLG = ADMIN_FLG;
    }

    public String getMaintenanceDeadline() {
        return MAINTENANCE_DEADLINE;
    }

    public void setMaintenanceDeadline(String MAINTENANCE_DEADLINE) {
        this.MAINTENANCE_DEADLINE = MAINTENANCE_DEADLINE;
    }

    public String getRetireFlg() {
        return RETIRE_FLG;
    }

    public void setRetireFlg(String RETIRE_FLG) {
        this.RETIRE_FLG = RETIRE_FLG;
    }
}

