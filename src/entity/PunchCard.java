package entity;

/**
 * 打卡
 *
 * @author zzj
 */
public class PunchCard {

    private int PunchCard_ID;//id
    private String PunchCard_Person_Code;//打卡人编码
    private String PunchCard_Person_Name;//打卡人姓名
    private String PunchCard_Date;//打卡日期
    private String PunchCard_Note;//打卡备注

    /**
     * @return the PunchCard_ID
     */
    public int getPunchCard_ID() {
        return PunchCard_ID;
    }

    /**
     * @param PunchCard_ID the PunchCard_ID to set
     */
    public void setPunchCard_ID(int PunchCard_ID) {
        this.PunchCard_ID = PunchCard_ID;
    }

    public String getPunchCard_Person_Code() {
        return PunchCard_Person_Code;
    }

    public void setPunchCard_Person_Code(String PunchCard_Person_Code) {
        this.PunchCard_Person_Code = PunchCard_Person_Code;
    }

    public String getPunchCard_Person_Name() {
        return PunchCard_Person_Name;
    }

    public void setPunchCard_Person_Name(String PunchCard_Person_Name) {
        this.PunchCard_Person_Name = PunchCard_Person_Name;
    }

    /**
     * @return the Punch_Card_Date
     */
    public String getPunchCard_Date() {
        return PunchCard_Date;
    }

    /**
     * @param Punch_Card_Date the Punch_Card_Date to set
     */
    public void setPunchCard_Date(String Punch_Card_Date) {
        this.PunchCard_Date = Punch_Card_Date;
    }

    /**
     * @return the PunchCard_Note
     */
    public String getPunchCard_Note() {
        return PunchCard_Note;
    }

    /**
     * @param PunchCard_Note the PunchCard_Note to set
     */
    public void setPunchCard_Note(String PunchCard_Note) {
        this.PunchCard_Note = PunchCard_Note;
    }
}
