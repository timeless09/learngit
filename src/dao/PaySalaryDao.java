package dao;

import entity.PaySalary;
import java.util.ArrayList;

/**
 * 派薪表
 *
 * @author 10303
 */
public interface PaySalaryDao extends BaseDao<PaySalary> {

    //根据条件获得派薪表的集合
    @Override
    public ArrayList<PaySalary> getAll(String s) throws Exception;

    //根据id获得派薪记录
    @Override
    public PaySalary get(int id) throws Exception;

    //更新派薪记录
    @Override
    public void update(PaySalary paySalary) throws Exception;

    //添加派薪记录
    @Override
    public void add(PaySalary paySalary) throws Exception;

    //根据id删除派薪记录
    @Override
    public void delete(int id) throws Exception;
}
