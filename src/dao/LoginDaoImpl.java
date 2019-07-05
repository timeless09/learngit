package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 登录
 * @author 10303
 */
public class LoginDaoImpl implements LoginDao{
    private Connection dbconn;
    private PreparedStatement pstmt=null;
    private Statement stmt =null;
    public LoginDaoImpl(Connection dbconn){
        this.dbconn=dbconn;
    }
    /**
     * 
     * @param username 登录的用户名
     * @param password 登录的密码
     * @return 用户名或者密码是否正确
     * @throws Exception 
     */
    @Override
    public boolean Login(String username, String password) throws Exception{
        boolean flag = false ;
        String sql="select * from tlogin where Login_Username=? and Login_Password=?";
        this.pstmt=this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, username);
        this.pstmt.setString(2, password);
        ResultSet rs=this.pstmt.executeQuery();
        if(rs.next()) flag=true;
        return flag;
    }
}
