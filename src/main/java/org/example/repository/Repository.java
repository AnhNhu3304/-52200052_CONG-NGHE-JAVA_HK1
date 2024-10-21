package org.example.repository;

import java.util.List;

public interface Repository<T> {
    boolean add(T entity);
    T get(int id);
    List<T> getAll();
    boolean remove(int id);
    boolean remove(T entity);
    boolean update(T entity);
}
