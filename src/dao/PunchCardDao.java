package dao;

import entity.PunchCard;
import java.util.ArrayList;

/**
 * 打卡表
 *
 * @author zzj
 */
public interface PunchCardDao extends BaseDao<PunchCard> {

    //根据条件获得打卡表的集合
    @Override
    public ArrayList<PunchCard> getAll(String s) throws Exception;

    //根据id获得打卡记录
    @Override
    public PunchCard get(int id) throws Exception;

    //添加打卡记录
    @Override
    public void add(PunchCard t) throws Exception;

}
