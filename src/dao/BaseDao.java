package dao;

import java.util.ArrayList;

public interface BaseDao<T> {

    //根据条件搜索，返回所需的泛型列表
    public ArrayList<T> getAll(String s) throws Exception;

    //根据id获得泛型对象
    public T get(int id) throws Exception;

    //更新
    public void update(T t) throws Exception;

    //添加
    public void add(T t) throws Exception;

    //根据id删除
    public void delete(int id) throws Exception;
}
