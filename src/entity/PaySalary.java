package entity;

/**
 * 派薪
 *
 * @author 10303
 */
public class PaySalary {

    private int Salary_ID;//id
    private String Salary_Person_Code;//领薪人编码
    private String Salary_Person_Name;//领薪人姓名
    private double Salary;//薪水
    private String Salary_Start_Time;//计算开始日期
    private String Salary_End_Time;//计算结束日期
    private int Salary_Time;//派薪总时间

    public int getSalary_Time() {
        return Salary_Time;
    }

    public void setSalary_Time(int Salary_Time) {
        this.Salary_Time = Salary_Time;
    }

    public int getSalary_ID() {
        return Salary_ID;
    }

    public void setSalary_ID(int Salary_ID) {
        this.Salary_ID = Salary_ID;
    }

    public String getSalary_Person_Code() {
        return Salary_Person_Code;
    }

    public void setSalary_Person_Code(String Salary_Person_Code) {
        this.Salary_Person_Code = Salary_Person_Code;
    }

    public String getSalary_Person_Name() {
        return Salary_Person_Name;
    }

    public void setSalary_Person_Name(String Salary_Person_Name) {
        this.Salary_Person_Name = Salary_Person_Name;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getSalary_Start_Time() {
        return Salary_Start_Time;
    }

    public void setSalary_Start_Time(String Salary_Start_Time) {
        this.Salary_Start_Time = Salary_Start_Time;
    }

    public String getSalary_End_Time() {
        return Salary_End_Time;
    }

    public void setSalary_End_Time(String Salary_End_Time) {
        this.Salary_End_Time = Salary_End_Time;
    }

}
