package service;

import Until.DBConnection;
import dao.StationDao;
import dao.StationDaoImpl;
import entity.Station;

import java.util.ArrayList;

/**
 * 岗位
 *
 * @author cleju
 */
public class StationService implements StationDao {

    private DBConnection dbconn = null;
    private StationDaoImpl dao = null;

    public StationService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new StationDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索岗位信息
    @Override
    public ArrayList<Station> getAll(String s) throws Exception {
        ArrayList<Station> stations = null;
        try {
            stations = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return stations;
    }

    //根据id获得一个岗位信息
    @Override
    public Station get(int id) throws Exception {
        Station station = null;
        try {
            station = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return station;
    }

    //更新岗位
    @Override
    public void update(Station station) throws Exception {
        try {
            this.dao.update(station);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加岗位
    @Override
    public void add(Station station) throws Exception {
        try {
            if (station.getStation_ID() >= 0) {
                this.dao.update(station);
            } else {
                this.dao.add(station);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除岗位
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
