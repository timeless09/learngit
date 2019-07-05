package entity;

/**
 *部门
 * @author 10303
 */
public class Department {
    private int Department_ID;//部门id
    private String Department_ID_Code;//部门编码
    private String Department_Name;//部门名称
    private String Department_Leader;//部门责任人
    private String Department_Duty;//部门职责
    private int Department_Superior_ID;//上级部门id
    private String Department_Superior_Name;//上级部门名称

    public String getDepartment_Superior_Name() {
        return Department_Superior_Name;
    }

    public void setDepartment_Superior_Name(String Department_Superior_Name) {
        this.Department_Superior_Name = Department_Superior_Name;
    }

    public int getDepartment_ID() {
        return Department_ID;
    }

    public void setDepartment_ID(int Department_ID) {
        this.Department_ID = Department_ID;
    }

    public String getDepartment_ID_Code() {
        return Department_ID_Code;
    }

    public void setDepartment_ID_Code(String Department_ID_Code) {
        this.Department_ID_Code = Department_ID_Code;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public void setDepartment_Name(String Department_Name) {
        this.Department_Name = Department_Name;
    }

    public String getDepartment_Leader() {
        return Department_Leader;
    }

    public void setDepartment_Leader(String Department_Leader) {
        this.Department_Leader = Department_Leader;
    }

    public String getDepartment_Duty() {
        return Department_Duty;
    }

    public void setDepartment_Duty(String Department_Duty) {
        this.Department_Duty = Department_Duty;
    }

    public int getDepartment_Superior_ID() {
        return Department_Superior_ID;
    }

    public void setDepartment_Superior_ID(int Department_Superior_ID) {
        this.Department_Superior_ID = Department_Superior_ID;
    }
    
}
