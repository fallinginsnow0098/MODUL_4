package service;

import java.util.ArrayList;

public interface IService<T>{
    ArrayList<T> findAll();
    T save(T t);
    T delete(int id);
    T findById(int id);
}
