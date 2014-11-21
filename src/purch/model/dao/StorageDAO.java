package purch.model.dao;

import java.util.List;

import purch.model.entity.Storage;

public interface StorageDAO {
	public void insert(Storage storage);
	public void update(Storage storage);
	public void delete(String name);
	public Storage select(String name);
	public List<String> getAllStorages();
}
