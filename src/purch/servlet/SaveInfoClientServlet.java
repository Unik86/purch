package purch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;

import purch.model.client.Client;
import purch.model.client.dao.ClientDAO;
import purch.model.storage.Storage;
import purch.model.storage.dao.StorageDAO;

@WebServlet("/saveinfoclient")
public class SaveInfoClientServlet extends HttpServlet {
	ApplicationContext context;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String telSave = req.getParameter("telSave");
		String infoSave = req.getParameter("infoSave");
		String newPostSave = req.getParameter("newPostSave");
		String ukrPostSave = req.getParameter("ukrPostSave");
		String name = req.getParameter("name");
		String oldname = req.getParameter("oldname");
		boolean who = Boolean.parseBoolean(req.getParameter("who"));
		boolean create = Boolean.parseBoolean(req.getParameter("create"));
		
		ApplicationContext context = new ClassPathXmlApplicationContext("purch\\resources\\spring\\Spring-Module.xml");
		JSONObject jsonObj = null;
				
		if(who){
			if(create){
		        ClientDAO clientDAO = (ClientDAO) context.getBean("clientDAO");
		        Client client = new Client(name, telSave, newPostSave, ukrPostSave, infoSave);
		        clientDAO.insert(client);
		        jsonObj = new JSONObject(client);
			}else{
		        ClientDAO clientDAO = (ClientDAO) context.getBean("clientDAO");
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
				StorageDAO storageDAO = (StorageDAO) context.getBean("storageDAO");
				Storage storage = new Storage(name, telSave, newPostSave, ukrPostSave, infoSave);
				storageDAO.insert(storage);
				jsonObj = new JSONObject(storage);
			}else{
				StorageDAO storageDAO = (StorageDAO) context.getBean("storageDAO");
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
		
		resp.setContentType("text/plain");	
		resp.setCharacterEncoding("utf-8");
		
		resp.getWriter().write(jsonObj.toString());
	}
}
