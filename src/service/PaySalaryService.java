package service;

import Until.DBConnection;
import dao.PaySalaryDao;
import dao.PaySalaryDaoImpl;
import entity.PaySalary;

import java.util.ArrayList;

public class PaySalaryService implements PaySalaryDao {

    private DBConnection dbconn = null;
    private PaySalaryDaoImpl dao = null;

    public PaySalaryService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new PaySalaryDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索派薪记录
    @Override
    public ArrayList<PaySalary> getAll(String s) throws Exception {
        ArrayList<PaySalary> paySalarys = null;
        try {
            paySalarys = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return paySalarys;
    }

    //根据id获得一个派薪记录
    @Override
    public PaySalary get(int id) throws Exception {
        PaySalary paySalary = null;
        try {
            paySalary = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return paySalary;
    }

    //更新派薪记录
    @Override
    public void update(PaySalary paySalary) throws Exception {
        try {
            this.dao.update(paySalary);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加派薪记录
    @Override
    public void add(PaySalary paySalary) throws Exception {
        try {
            if (paySalary.getSalary_ID() >= 0) {
                this.dao.update(paySalary);
            } else {
                this.dao.add(paySalary);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除派薪记录
    @Override
    public void delete(int id) throws Exception {
        try {
            this.dao.delete(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }
}
