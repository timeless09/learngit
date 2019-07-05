package dao;

import entity.Clazz;

import java.util.ArrayList;

/**
 * @author cleju
 */
public interface ClazzDao extends BaseDao<Clazz> {
    @Override
    public ArrayList<Clazz> getAll(String s) throws Exception;

    @Override
    public Clazz get(int id) throws Exception;

    @Override
    public void update(Clazz clazz) throws Exception;

    @Override
    public void add(Clazz clazz) throws Exception;

    @Override
    public void delete(int id) throws Exception;
}
