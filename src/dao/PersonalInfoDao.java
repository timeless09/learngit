package dao;

import entity.Employee;

/**
 * 个人信息
 *
 * @author cleju
 */
public interface PersonalInfoDao {

    //根据用户名来得到该用户的详细信息
    public Employee get(String username) throws Exception;
}
