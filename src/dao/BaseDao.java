package dao;

import java.util.ArrayList;


public interface BaseDao <T> {
    public ArrayList<T> getAll(String s) throws Exception;
    public T get(int id) throws Exception;
    public void update(T t) throws Exception;
    public void add(T t) throws Exception;
    public void delete(int id) throws Exception;
}
