package entity;

/**
 * 补卡
 *
 * @author 10303
 */
public class RepairCard {

    private int Repair_ID;//id
    private String Repair_Person_Code;//补卡人编码
    private String Repair_Person_Name;//补卡人姓名
    private String Repair_Date;//补卡日期
    private String Repair_Reason;//补卡原因

    public int getRepair_ID() {
        return Repair_ID;
    }

    public void setRepair_ID(int Repair_ID) {
        this.Repair_ID = Repair_ID;
    }

    public String getRepair_Person_Code() {
        return Repair_Person_Code;
    }

    public void setRepair_Person_Code(String Repair_Person_Code) {
        this.Repair_Person_Code = Repair_Person_Code;
    }

    public String getRepair_Person_Name() {
        return Repair_Person_Name;
    }

    public void setRepair_Person_Name(String Repair_Person_Name) {
        this.Repair_Person_Name = Repair_Person_Name;
    }

    public String getRepair_Date() {
        return Repair_Date;
    }

    public void setRepair_Date(String Repair_Date) {
        this.Repair_Date = Repair_Date;
    }

    public String getRepair_Reason() {
        return Repair_Reason;
    }

    public void setRepair_Reason(String Repair_Reason) {
        this.Repair_Reason = Repair_Reason;
    }

}
