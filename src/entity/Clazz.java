package entity;

/**
 * 班次
 *
 * @author cleju
 */
public class Clazz {

    private int Class_ID;//班次id
    private String Class_Code;//班次编码
    private String Class_Name;//班次名称
    private String Class_Start_Time;//早上上班时间
    private String Class_End_Time;//下午下班时间
    private String Class_Note;//班次备注

    public int getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(int Class_ID) {
        this.Class_ID = Class_ID;
    }

    public String getClass_Code() {
        return Class_Code;
    }

    public void setClass_Code(String Class_Code) {
        this.Class_Code = Class_Code;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String Class_Name) {
        this.Class_Name = Class_Name;
    }

    public String getClass_Start_Time() {
        return Class_Start_Time;
    }

    public void setClass_Start_Time(String Class_Start_Time) {
        this.Class_Start_Time = Class_Start_Time;
    }

    public String getClass_End_Time() {
        return Class_End_Time;
    }

    public void setClass_End_Time(String Class_End_Time) {
        this.Class_End_Time = Class_End_Time;
    }

    public String getClass_Note() {
        return Class_Note;
    }

    public void setClass_Note(String Class_Note) {
        this.Class_Note = Class_Note;
    }
}
