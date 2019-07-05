package entity;

/**
 * 考勤
 *
 * @author 10303
 */
public class CheckReport {

    private int CheckReport_ID; //考勤id
    private String CheckReport_Employee_Code;//考勤员工编码
    private String CheckReport_Employee_Name;//考勤员工姓名
    private String CheckReport_Start_Time;//开始时间
    private String CheckReport_End_Time;//结束时间
    private String CheckReport_Attendance_Cndition;//考勤状态

    public int getCheckReport_ID() {
        return CheckReport_ID;
    }

    public void setCheckReport_ID(int CheckReport_ID) {
        this.CheckReport_ID = CheckReport_ID;
    }

    public String getCheckReport_Employee_Code() {
        return CheckReport_Employee_Code;
    }

    public void setCheckReport_Employee_Code(String CheckReport_Employee_Code) {
        this.CheckReport_Employee_Code = CheckReport_Employee_Code;
    }

    public String getCheckReport_Employee_Name() {
        return CheckReport_Employee_Name;
    }

    public void setCheckReport_Employee_Name(String CheckReport_Employee_Name) {
        this.CheckReport_Employee_Name = CheckReport_Employee_Name;
    }

    public String getCheckReport_Start_Time() {
        return CheckReport_Start_Time;
    }

    public void setCheckReport_Start_Time(String CheckReport_Start_Time) {
        this.CheckReport_Start_Time = CheckReport_Start_Time;
    }

    public String getCheckReport_End_Time() {
        return CheckReport_End_Time;
    }

    public void setCheckReport_End_Time(String CheckReport_End_Time) {
        this.CheckReport_End_Time = CheckReport_End_Time;
    }

    public String getCheckReport_Attendance_Cndition() {
        return CheckReport_Attendance_Cndition;
    }

    public void setCheckReport_Attendance_Cndition(String CheckReport_Attendance_Cndition) {
        this.CheckReport_Attendance_Cndition = CheckReport_Attendance_Cndition;
    }
}
