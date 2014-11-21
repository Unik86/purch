package purch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import purch.model.dao.ClientDAO;
import purch.model.dao.StorageDAO;
import purch.model.entity.Client;
import purch.model.entity.Storage;


@Controller
public class MainController implements BeanFactoryAware {
	
	public MainController(){
		
	}
	
	private ClientDAO clientDAO;
	private StorageDAO storageDAO;

	public void setBeanFactory(BeanFactory context) {
		clientDAO = (ClientDAO)context.getBean("clientDAO");
		storageDAO = (StorageDAO)context.getBean("storageDAO");
	}
	
	@RequestMapping(value="/getclients", method = RequestMethod.GET)
	public @ResponseBody String getClients(){

		List<String> listClients = new ArrayList<String>();
		List<String> listStorages = new ArrayList<String>();

        listClients = clientDAO.getAllClients();
        listStorages = storageDAO.getAllStorages();
        
		JSONArray jsonArrClients = new JSONArray(listClients);
		JSONArray jsonArrStorages = new JSONArray(listStorages);		
		
		String jsonObj = "{\"Clients\":" + jsonArrClients.toString() + ", \"Storages\":" + jsonArrStorages.toString() + "}";

		return jsonObj;
	}
	
	@RequestMapping(value="/getinfoclient", method = RequestMethod.POST)
	public @ResponseBody String getInfoClient(@RequestParam String name, @RequestParam boolean who){
		
		JSONObject jsonObj = null;
		
		if(who == true){
	        Client client = clientDAO.select(name);
	        jsonObj = new JSONObject(client);
		}else{
			Storage storage = storageDAO.select(name);
	        jsonObj = new JSONObject(storage);
		}

		return jsonObj.toString();
	}
	
	@RequestMapping(value="/deleteclient", method = RequestMethod.POST)
	public @ResponseBody void deleteClient(@RequestParam String name, @RequestParam boolean who){

		if(who == true){
	        clientDAO.delete(name);
		}else{
			storageDAO.delete(name);
		}
	}
	
	@RequestMapping(value="/saveinfoclient", method = RequestMethod.POST)
	public @ResponseBody String saveInfoClient(
				@RequestParam String telSave, 
				@RequestParam String infoSave, 
				@RequestParam String newPostSave, 
				@RequestParam String ukrPostSave, 
				@RequestParam String name, 
				@RequestParam String oldname,
				@RequestParam boolean who,
				@RequestParam boolean create){

		JSONObject jsonObj = null;
		
		if(who){
			if(create){
		        Client client = new Client(name, telSave, newPostSave, ukrPostSave, infoSave);
		        clientDAO.insert(client);
		        jsonObj = new JSONObject(client);
			}else{
		        Client client = clientDAO.select(oldname);
		        
		        client.setFioClient(name);
		        client.setTelClient(telSave);
		        client.setNewPost(newPostSave);
		        client.setUkrPost(ukrPostSave);
		        client.setInfoClient(infoSave);
		        
		        clientDAO.update(client);
		        jsonObj = new JSONObject(client);
			}
		}else{
			if(create){
				Storage storage = new Storage(name, telSave, newPostSave, ukrPostSave, infoSave);
				storageDAO.insert(storage);
				jsonObj = new JSONObject(storage);
			}else{
				Storage storage = storageDAO.select(oldname);
				
				storage.setNameStorage(name);
				storage.setTelStorage(telSave);
				storage.setSiteStorage(newPostSave);
				storage.setBankStorage(ukrPostSave);
				storage.setInfoStorage(infoSave);
				
				storageDAO.update(storage);
				jsonObj = new JSONObject(storage);
			}
		}
		return jsonObj.toString();
	}
}
