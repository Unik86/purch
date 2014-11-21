package purch.model.entity;

public class Storage {
	private int idStorage;
	private String nameStorage;
	private String telStorage;
	private String siteStorage;
	private String bankStorage;
	private String infoStorage;
	
	public Storage(){
	}
	
	public Storage(
			String nameStorageA,
			String telStorageA,
			String siteStorageA,
			String bankStorageA,
			String infoStorageA
			){
		nameStorage = nameStorageA;
		telStorage = telStorageA;
		siteStorage = siteStorageA;
		bankStorage = bankStorageA;
		infoStorage = infoStorageA;
	}
	
	public int getIdStorage() {
		return idStorage;
	}
	public void setIdStorage(int idStorage) {
		this.idStorage = idStorage;
	}
	public String getNameStorage() {
		return nameStorage;
	}
	public void setNameStorage(String nameStorage) {
		this.nameStorage = nameStorage;
	}
	public String getTelStorage() {
		return telStorage;
	}
	public void setTelStorage(String telStorage) {
		this.telStorage = telStorage;
	}
	public String getSiteStorage() {
		return siteStorage;
	}
	public void setSiteStorage(String siteStorage) {
		this.siteStorage = siteStorage;
	}
	public String getBankStorage() {
		return bankStorage;
	}
	public void setBankStorage(String bankStorage) {
		this.bankStorage = bankStorage;
	}
	public String getInfoStorage() {
		return infoStorage;
	}
	public void setInfoStorage(String infoStorage) {
		this.infoStorage = infoStorage;
	}
	@Override
	public String toString() {
		return idStorage +" "+ nameStorage +" "+ telStorage +" "+ siteStorage +" "+ bankStorage +" "+ infoStorage;
	}
}
