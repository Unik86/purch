package purch.model.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import purch.model.storage.Storage;

public class StorageDAOImp implements StorageDAO{
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void insert(Storage storage){
 
		String sql = "INSERT INTO Storage (nameStorage, telStorage, siteStorage, bankStorage, infoStorage) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, storage.getNameStorage());
			ps.setString(2, storage.getTelStorage());
			ps.setString(3, storage.getSiteStorage());
			ps.setString(4, storage.getBankStorage());
			ps.setString(5, storage.getInfoStorage());
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
 
	public Storage select(String name){
 
		String sql = "SELECT * FROM Storage WHERE nameStorage = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			Storage storage = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				storage = new Storage();
				storage.setIdStorage(rs.getInt("idStorage"));
				storage.setNameStorage(rs.getString("nameStorage"));
				storage.setTelStorage(rs.getString("telStorage"));
				storage.setSiteStorage(rs.getString("siteStorage")); 
				storage.setBankStorage(rs.getString("bankStorage"));
				storage.setInfoStorage(rs.getString("infoStorage"));
			}
			rs.close();
			ps.close();
			return storage;
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
		 
		String sql = "DELETE FROM Storage WHERE nameStorage = ?";
 
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
	public void update(Storage storage){
		 
		String sql = "UPDATE Storage SET nameStorage = ?, telStorage = ?, siteStorage = ?, bankStorage = ?, infoStorage = ? WHERE idStorage = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, storage.getNameStorage());
			ps.setString(2, storage.getTelStorage());
			ps.setString(3, storage.getSiteStorage());
			ps.setString(4, storage.getBankStorage());
			ps.setString(5, storage.getInfoStorage());
			ps.setInt(6, storage.getIdStorage());
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
	public List<String> getAllStorages(){
		 
		List<String> list = new ArrayList<String>();
		String sql = "SELECT nameStorage FROM Storage";
		
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("nameStorage"));
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
