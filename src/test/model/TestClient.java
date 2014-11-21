package test.model;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import purch.model.dao.ClientDAO;
import purch.model.entity.Client;

public class TestClient {
	@Test
	public void tClient(){

		ApplicationContext context = new ClassPathXmlApplicationContext("purch\\resources\\spring\\Spring-Module.xml");
		 
        ClientDAO clientDAO = (ClientDAO) context.getBean("clientDAO");
        
        //Client client = new Client("Кравцов Вадим", "(093) 512-55-45", "Умань, Отделение №2", "Умань, Ленина 50", "Красавчег");
        //clientDAO.insert(client);
 
        Client client2 = clientDAO.select("Петрова Надя");
        //System.out.println(client2);
        client2.setTelClient("55555555");
        clientDAO.update(client2);
        //clientDAO.delete("Кравцов Вадим");
        
//        List<String> list = clientDAO.getAllClients();
//        for(String s: list)
//        	System.out.println(s);
	}
}
