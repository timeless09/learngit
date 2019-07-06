package dao;

import entity.RepairCard;
import java.util.ArrayList;

/**
 * 补卡表
 *
 * @author 10303
 */
public interface RepairCardDao extends BaseDao<RepairCard> {

    //根据条件获得补卡表的集合
    @Override
    public ArrayList<RepairCard> getAll(String s) throws Exception;

    //根据id获得补卡记录
    @Override
    public RepairCard get(int id) throws Exception;

    //更新补卡记录
    @Override
    public void update(RepairCard repairCard) throws Exception;

    //删除补卡记录
    @Override
    public void add(RepairCard repairCard) throws Exception;

    //根据id删除补卡记录
    @Override
    public void delete(int id) throws Exception;
}
