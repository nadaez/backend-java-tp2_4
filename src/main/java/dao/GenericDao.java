package dao;

import java.io.Serializable;

public interface GenericDao<T> {
    T create(T t);
    T read(Long id);
    T update(T t);
    void delete(T t);
}
