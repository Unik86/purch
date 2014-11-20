package purch.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.json.GsonBuilderUtils;

import purch.model.client.dao.ClientDAO;
import purch.model.client.dao.ClientDAOImp;
import purch.model.storage.dao.StorageDAO;

import com.mysql.fabric.xmlrpc.base.Array;

@WebServlet("/getclients")
public class GetClientsServlet extends HttpServlet{
	ApplicationContext context;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");	
		resp.setCharacterEncoding("utf-8");
		
		List<String> listClients = new ArrayList<String>();
		List<String> listStorages = new ArrayList<String>();

		ApplicationContext context = new ClassPathXmlApplicationContext("purch\\resources\\spring\\Spring-Module.xml");
		 
        ClientDAO clientDAO = (ClientDAO) context.getBean("clientDAO");
        listClients = clientDAO.getAllClients();

        StorageDAO storageDAO = (StorageDAO) context.getBean("storageDAO");
        listStorages = storageDAO.getAllStorages();
        
		JSONArray jsonArrClients = new JSONArray(listClients);
		JSONArray jsonArrStorages = new JSONArray(listStorages);		
		
		String jsonObj = "{\"Clients\":" + jsonArrClients.toString() + ", \"Storages\":" + jsonArrStorages.toString() + "}";
		
		resp.getWriter().write(jsonObj);
	}
	
}
