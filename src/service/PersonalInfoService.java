package service;

import Until.DBConnection;
import dao.PersonalInfoDao;
import dao.PersonalInfoDaoImpl;
import entity.Employee;

/**
 * 个人信息
 *
 * @author cleju
 */
public class PersonalInfoService implements PersonalInfoDao {

    private DBConnection dbconn = null;
    private PersonalInfoDaoImpl dao = null;

    public PersonalInfoService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new PersonalInfoDaoImpl(this.dbconn.getConnection());
    }

    //根据用户名获得一个员工个人信息
    @Override
    public Employee get(String username) throws Exception {
        Employee station = null;
        try {
            station = this.dao.get(username);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return station;
    }

}
