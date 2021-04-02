package common.interfaces;
import java.util.List;

public interface DAO<E> {
    E get(String id);

    List<E> getAll();

    void create(E entity);

    E update(E entity);

    int delete(int id);
}