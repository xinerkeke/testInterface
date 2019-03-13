package com.test.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestApi {
	    public Properties prop;
	    public String excelPath;
	    public String host; 
	    
	    /**
	     * 构造函数
	     * @throws IOException 
	     */
	    public TestApi() {
			try {
				prop = new Properties();
				
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
						"/src.main.resource/config.properties");
				
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			host = prop.getProperty("Host");
			excelPath = prop.getProperty("testData");
		}

}
