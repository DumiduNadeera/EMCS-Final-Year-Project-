package it.emcs.config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import it.emcs.dao.DBConnection;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "it.emcs")
public class WebAppConfiguration {

	@Autowired
	DBConnection dbConnection;

	@PreDestroy
	public void cleanUp() throws Exception {
		System.out.println("closing up");
		dbConnection.closeMongoClient();
	}

}
