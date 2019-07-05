package dao;

import entity.PunchCard;
import java.util.ArrayList;

/**
 *
 * @author zzj
 */
public interface PunchCardDao extends BaseDao<PunchCard> {

    @Override
    public ArrayList<PunchCard> getAll(String s) throws Exception;

    @Override
    public PunchCard get(int id) throws Exception;

    @Override
    public void update(PunchCard t) throws Exception;

    @Override
    public void add(PunchCard t) throws Exception;

    @Override
    public void delete(int id) throws Exception;

}
