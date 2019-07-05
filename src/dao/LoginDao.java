package dao;

/**
 *
 * @author cleju
 */
public interface LoginDao {
    public boolean Login(String username, String password)throws Exception;
}
