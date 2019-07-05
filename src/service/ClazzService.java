package service;

import Until.DBConnection;
import dao.ClazzDao;
import dao.ClazzDaoImpl;
import entity.Clazz;

import java.util.ArrayList;

/**
 * 班次
 *
 * @author cleju
 */
public class ClazzService implements ClazzDao {

    private DBConnection dbconn = null;
    private ClazzDaoImpl dao = null;

    public ClazzService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new ClazzDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索班次信息
    @Override
    public ArrayList<Clazz> getAll(String s) throws Exception {
        ArrayList<Clazz> clazzs = null;
        try {
            clazzs = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return clazzs;
    }

    //根据id获得一个班次信息
    @Override
    public Clazz get(int id) throws Exception {
        Clazz clazz = null;
        try {
            clazz = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return clazz;
    }

    //更新班次
    @Override
    public void update(Clazz clazz) throws Exception {
        try {
            this.dao.update(clazz);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加班次
    @Override
    public void add(Clazz clazz) throws Exception {
        try {
            if (clazz.getClass_ID() >= 0) {
                this.dao.update(clazz);
            } else {
                this.dao.add(clazz);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除班次
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
