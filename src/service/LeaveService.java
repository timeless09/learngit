package service;

import Until.DBConnection;
import dao.LeaveDao;
import dao.LeaveDaoImpl;
import entity.Leave;

import java.util.ArrayList;

/**
 * 请假记录
 *
 * @author cleju
 */
public class LeaveService implements LeaveDao {

    private DBConnection dbconn = null;
    private LeaveDaoImpl dao = null;

    public LeaveService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new LeaveDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索请假记录
    @Override
    public ArrayList<Leave> getAll(String s) throws Exception {
        ArrayList<Leave> leaves = null;
        try {
            leaves = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return leaves;
    }

    //根据id获得一个请假记录
    @Override
    public Leave get(int id) throws Exception {
        Leave leave = null;
        try {
            leave = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return leave;
    }

    //更新请假记录
    @Override
    public void update(Leave leave) throws Exception {
        try {
            this.dao.update(leave);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加请假记录
    @Override
    public void add(Leave leave) throws Exception {
        try {
            if (leave.getLeave_ID() >= 0) {
                this.dao.update(leave);
            } else {
                this.dao.add(leave);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除请假记录
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
