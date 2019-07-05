package dao;

import entity.Station;
import java.util.ArrayList;

/**
 *
 * @author cleju
 */
public interface StationDao extends BaseDao<Station>{
    @Override
    public ArrayList<Station> getAll(String s) throws Exception;
    @Override
    public Station get(int id) throws Exception;
    @Override
    public void update(Station station) throws Exception;
    @Override
    public void add(Station station) throws Exception;
}
