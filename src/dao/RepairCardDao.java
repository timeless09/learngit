package dao;

import entity.RepairCard;
import java.util.ArrayList;

/**
 *
 * @author 10303
 */
public interface RepairCardDao extends BaseDao<RepairCard> {

    @Override
    public ArrayList<RepairCard> getAll(String s) throws Exception;

    @Override
    public RepairCard get(int id) throws Exception;

    @Override
    public void update(RepairCard repairCard) throws Exception;

    @Override
    public void add(RepairCard repairCard) throws Exception;

}
