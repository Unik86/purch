package test.model;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import purch.model.dao.StorageDAO;
import purch.model.entity.Storage;

public class TestStorage {
	@Test
	public void tStorage(){
		ApplicationContext context = new ClassPathXmlApplicationContext("purch\\resources\\spring\\Spring-Module.xml");

		StorageDAO storageDAO = (StorageDAO) context.getBean("storageDAO");

        Storage storage = new Storage("Рибтовары", "(090)555-11-12", "fish2.ua", "1201201210120", "товары для рыбалки");
        //storageDAO.insert(storage);

        //Storage storage2 = storageDAO.select("Шапки");
        //System.out.println(storage2);
		
        //storage2.setTelStorage("777777");
        storageDAO.update(storage);
		
        //storageDAO.delete("Беби плюс");
        
//        List<String> list = storageDAO.getAllStorages();
//        for(String s: list)
//        	System.out.println(s);
	}
}
