package purch.model.entity;

public class Client {
	private int idClient;
	private String fioClient;
	private String telClient;
	private String newPost;
	private String ukrPost;
	private String infoClient;
	
	public Client(){
	}
	
	public Client(
			String fioClientA,
			String telClientA,
			String newPostA,
			String ukrPostA,
			String infoClientA
			){
		fioClient = fioClientA;
		telClient = telClientA;
		newPost = newPostA;
		ukrPost = ukrPostA;
		infoClient = infoClientA;
	}
	
	public void setIdClient(int idClientA){
		idClient = idClientA;
	}
	public int getIdClient(){
		return idClient;
	}
	public void setFioClient(String fioClientA){
		fioClient = fioClientA;
	}
	public String getFioClient(){
		return fioClient;
	}
	public void setTelClient(String telClientA){
		telClient = telClientA;
	}
	public String getTelClient(){
		return telClient;
	}
	public void setNewPost(String newPostA){
		newPost = newPostA;
	}
	public String getNewPost(){
		return newPost;
	}
	public void setUkrPost(String ukrPostA){
		ukrPost = ukrPostA;
	}
	public String getUkrPost(){
		return ukrPost;
	}
	public void setInfoClient(String infoClientA){
		infoClient = infoClientA;
	}
	public String getInfoClient(){
		return infoClient;
	}
	@Override
	public String toString() {
		return idClient +" "+ fioClient +" "+ telClient +" "+ newPost +" "+ ukrPost +" "+ infoClient;
	}
}
