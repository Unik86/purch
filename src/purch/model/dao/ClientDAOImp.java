package purch.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import purch.model.entity.Client;

public class ClientDAOImp implements ClientDAO{
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void insert(Client client){
 
		String sql = "INSERT INTO Client (fioClient, telClient, newPost, ukrPost, infoClient) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, client.getFioClient());
			ps.setString(2, client.getTelClient());
			ps.setString(3, client.getNewPost());
			ps.setString(4, client.getUkrPost());
			ps.setString(5, client.getInfoClient());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
 
	public Client select(String name){
 
		String sql = "SELECT * FROM Client WHERE fioClient = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			Client client = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client = new Client();
				client.setIdClient(rs.getInt("idClient"));
				client.setFioClient(rs.getString("fioClient"));
				client.setTelClient(rs.getString("telClient"));
				client.setNewPost(rs.getString("newPost")); 
				client.setUkrPost(rs.getString("ukrPost"));
				client.setInfoClient(rs.getString("infoClient"));
			}
			rs.close();
			ps.close();
			return client;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	public void delete(String name){
		 
		String sql = "DELETE FROM Client WHERE fioClient = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	public void update(Client client){
		 
		String sql = "UPDATE Client SET fioClient = ?, telClient = ?, newPost = ?, ukrPost = ?, infoClient = ? WHERE idClient = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, client.getFioClient());
			ps.setString(2, client.getTelClient());
			ps.setString(3, client.getNewPost());
			ps.setString(4, client.getUkrPost());
			ps.setString(5, client.getInfoClient());
			ps.setInt(6, client.getIdClient());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	public List<String> getAllClients(){
		 
		List<String> list = new ArrayList<String>();
		String sql = "SELECT fioClient FROM client";
		
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("fioClient"));
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return list;
	}
}
