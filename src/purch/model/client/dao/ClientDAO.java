package purch.model.client.dao;

import java.util.List;

import purch.model.client.Client;

public interface ClientDAO {
	public void insert(Client client);
	public void update(Client client);
	public void delete(String name);
	public Client select(String name);
	public List<String> getAllClients();
}
