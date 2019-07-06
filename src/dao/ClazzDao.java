package dao;

import entity.Clazz;

import java.util.ArrayList;

/**
 * 班次表
 *
 * @author cleju
 */
public interface ClazzDao extends BaseDao<Clazz> {

    //根据班次的名称或者编码获取所需的班次
    @Override
    public ArrayList<Clazz> getAll(String s) throws Exception;

    //根据id获取班次信息
    @Override
    public Clazz get(int id) throws Exception;

    //更新班次
    @Override
    public void update(Clazz clazz) throws Exception;

    //添加班次
    @Override
    public void add(Clazz clazz) throws Exception;

    //根据id删除班次
    @Override
    public void delete(int id) throws Exception;
}
