package usn.obj2100.controller;

import java.util.List;

public interface IController<T>
{
	List<T> getAll();
	T getById(int id);
	boolean create(T t);
	void read(T t);
	boolean update(T t);
	boolean delete(T t);
}
