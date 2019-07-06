package dao;

import entity.Station;
import java.util.ArrayList;

/**
 * 岗位表
 *
 * @author cleju
 */
public interface StationDao extends BaseDao<Station> {

    //根据条件获得岗位表的集合
    @Override
    public ArrayList<Station> getAll(String s) throws Exception;

    //根据id获得岗位信息
    @Override
    public Station get(int id) throws Exception;

    //更新岗位信息
    @Override
    public void update(Station station) throws Exception;

    //添加岗位信息
    @Override
    public void add(Station station) throws Exception;

    //根据id删除岗位信息
    @Override
    public void delete(int id) throws Exception;
}
