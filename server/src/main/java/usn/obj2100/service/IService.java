package usn.obj2100.service;

import java.util.List;

/**
 * Interface for services.
 * <p/>
 * <b>Pattern:</b>
 * <br/>
 * <b>Role:</b> Interface.
 * <br/>
 * <b>Responsibility:</b> To provide a common interface for services.
 *
 * @version 0.1
 * @created 2024-02-13
 * @param <T> The type of object to be stored in, or gotten from the database.
 */
public interface IService<T>
{
	T get(int id);
	List<T> getAll();
	void create(T t);
	void update(T t);
	void delete(T t);
}
