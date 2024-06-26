package usn.obj2100.server.service;

import java.util.List;

/**
 * Interface for services.
 * <p/>
 * <b>Pattern:</b> Generic Interface.
 * <br/>
 * <b>Role:</b> Interface.
 * <br/>
 * <b>Responsibility:</b> To provide a common interface for services.
 *
 * @version 0.1
 * @created 2024-05-13
 * @param <T> The type of object to be stored in, or gotten from the database.
 */
public interface IService<T>
{
	T get(int id);
	List<T> getAll();
	T create(T t);
	boolean update(T t);
	boolean delete(T t);
}
