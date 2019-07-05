package entity;

/**
 * 登录
 *
 * @author 10958
 */
public class Admin {

    private int Login_ID; //用户id
    private String Login_Username;//用户名
    private String Login_Password;//用户密码

    /**
     * @return the Login_ID
     */
    public int getLogin_ID() {
        return Login_ID;
    }

    /**
     * @param Login_ID the Login_ID to set
     */
    public void setLogin_ID(int Login_ID) {
        this.Login_ID = Login_ID;
    }

    /**
     * @return the Login_Username
     */
    public String getLogin_Username() {
        return Login_Username;
    }

    /**
     * @param Login_Username the Login_Username to set
     */
    public void setLogin_Username(String Login_Username) {
        this.Login_Username = Login_Username;
    }

    /**
     * @return the Login_Password
     */
    public String getLogin_Password() {
        return Login_Password;
    }

    /**
     * @param Login_Password the Login_Password to set
     */
    public void setLogin_Password(String Login_Password) {
        this.Login_Password = Login_Password;
    }
}
