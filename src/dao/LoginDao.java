package dao;

/**
 * 登录
 *
 * @author cleju
 */
public interface LoginDao {

    //判断输入的用户名或者密码是否正确
    public boolean Login(String username, String password) throws Exception;
}
