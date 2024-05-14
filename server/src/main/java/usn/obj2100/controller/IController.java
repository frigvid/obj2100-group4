package usn.obj2100.controller;

import java.util.List;

/**
 * Interface for the controller classes.
 *
 * @param <T> The type of object the controller is for.
 */
public interface IController<T>
{
	List<T> getAll();
	T getById(int id);
	boolean create(T t);
	T read(T t);
	boolean update(T t);
	boolean delete(T t);
}
