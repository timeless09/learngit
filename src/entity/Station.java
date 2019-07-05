package entity;

/**
 * 岗位
 *
 * @author cleju
 */
public class Station {

    private int Station_ID;//岗位ID
    private String Station_Code;//岗位编码
    private String Station_Name;//岗位名称
    private String Station_Department;//所在部门
    private String Station_Superior;//直接上级
    private String Station_Category;//岗位类别
    private String Station_Describe;//岗位职责描述

    public int getStation_ID() {
        return Station_ID;
    }

    public void setStation_ID(int Station_ID) {
        this.Station_ID = Station_ID;
    }

    public String getStation_Code() {
        return Station_Code;
    }

    public void setStation_Code(String Station_Code) {
        this.Station_Code = Station_Code;
    }

    public String getStation_Name() {
        return Station_Name;
    }

    public void setStation_Name(String Station_Name) {
        this.Station_Name = Station_Name;
    }

    public String getStation_Department() {
        return Station_Department;
    }

    public void setStation_Department(String Station_Department) {
        this.Station_Department = Station_Department;
    }

    public String getStation_Superior() {
        return Station_Superior;
    }

    public void setStation_Superior(String Station_Superior) {
        this.Station_Superior = Station_Superior;
    }

    public String getStation_Category() {
        return Station_Category;
    }

    public void setStation_Category(String Station_Category) {
        this.Station_Category = Station_Category;
    }

    public String getStation_Describe() {
        return Station_Describe;
    }

    public void setStation_Describe(String Station_Describe) {
        this.Station_Describe = Station_Describe;
    }

}
