package entity;

/**
 * 请假
 *
 * @author cleju
 */
public class Leave {

    private int Leave_ID;//请假id
    private String Leave_Person_Code;//请假人编码
    private String Leave_Person_Name;//请假人姓名
    private String Leave_Start_Time;//请假开始时间
    private String Leave_End_Time;//请假结束时间
    private String Leave_Reason;//请假原因

    public int getLeave_ID() {
        return Leave_ID;
    }

    public void setLeave_ID(int Leave_ID) {
        this.Leave_ID = Leave_ID;
    }

    public String getLeave_Person_Code() {
        return Leave_Person_Code;
    }

    public void setLeave_Person_Code(String Leave_Person_Code) {
        this.Leave_Person_Code = Leave_Person_Code;
    }

    public String getLeave_Person_Name() {
        return Leave_Person_Name;
    }

    public void setLeave_Person_Name(String Leave_Person_Name) {
        this.Leave_Person_Name = Leave_Person_Name;
    }

    public String getLeave_Start_Time() {
        return Leave_Start_Time;
    }

    public void setLeave_Start_Time(String Leave_Start_Time) {
        this.Leave_Start_Time = Leave_Start_Time;
    }

    public String getLeave_End_Time() {
        return Leave_End_Time;
    }

    public void setLeave_End_Time(String Leave_End_Time) {
        this.Leave_End_Time = Leave_End_Time;
    }

    public String getLeave_Reason() {
        return Leave_Reason;
    }

    public void setLeave_Reason(String Leave_Reason) {
        this.Leave_Reason = Leave_Reason;
    }

}
