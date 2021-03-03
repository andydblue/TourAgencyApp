package dao.interfaces;

public interface DaoCrud<T, K> {
    T create(T obj);
    T read(Integer id);
    void update(T obj, K id);
    void delete(K id);
}