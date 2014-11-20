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

import purch.model.client.Client;
import purch.model.client.dao.ClientDAO;
import purch.model.storage.Storage;
import purch.model.storage.dao.StorageDAO;

@WebServlet("/deleteclient")
public class DeleteClientServlet extends HttpServlet {
	ApplicationContext context;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean who = Boolean.parseBoolean(req.getParameter("who"));
		String name = req.getParameter("name");

		ApplicationContext context = new ClassPathXmlApplicationContext("purch\\resources\\spring\\Spring-Module.xml");
				
		if(who == true){
	        ClientDAO clientDAO = (ClientDAO) context.getBean("clientDAO");
	        clientDAO.delete(name);
		}else{
			StorageDAO storageDAO = (StorageDAO) context.getBean("storageDAO");
			storageDAO.delete(name);
		}
	}
}
