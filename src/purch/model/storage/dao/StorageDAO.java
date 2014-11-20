package purch.model.storage.dao;

import java.util.List;

import purch.model.storage.Storage;

public interface StorageDAO {
	public void insert(Storage storage);
	public void update(Storage storage);
	public void delete(String name);
	public Storage select(String name);
	public List<String> getAllStorages();
}
