package dao;

import entity.PaySalary;

import java.util.ArrayList;

public interface PaySalaryDao extends BaseDao<PaySalary> {

    @Override
    public ArrayList<PaySalary> getAll(String s) throws Exception;

    @Override
    public PaySalary get(int id) throws Exception;

    @Override
    public void update(PaySalary paySalary) throws Exception;

    @Override
    public void add(PaySalary paySalary) throws Exception;
}
