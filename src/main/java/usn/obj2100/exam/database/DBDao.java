package usn.obj2100.exam.database;

import java.util.List;

/**
 * Interface for the database access object.
 * <p/>
 * <b>Pattern:</b> Data Access Object (DAO).
 * <b>Role:</b> Interface.
 * <b>Responsibility:</b> To provide a common interface for the database access object.
 *
 * @version 0.1
 * @created 2024-02-13
 * @param <T> The type of object to be stored in the database.
 */
public interface DBDao<T>
{
	T get(int id);
	List<T> getAll();
	void save(T t);
	void update(T t);
	void delete(T t);
}
