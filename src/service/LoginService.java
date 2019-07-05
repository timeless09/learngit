package service;

import Until.DBConnection;
import dao.LoginDao;
import dao.LoginDaoImpl;

/**
 * 登录
 *
 * @author cleju
 */
public class LoginService implements LoginDao {

    private DBConnection dbconn = null;
    private LoginDaoImpl dao = null;

    public LoginService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new LoginDaoImpl(this.dbconn.getConnection());
    }

    @Override
    public boolean Login(String username, String password) throws Exception {
        boolean flag = this.dao.Login(username, password);
        return flag;
    }

}
