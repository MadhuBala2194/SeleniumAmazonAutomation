package ConfigReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties properties;
	private String propertyFilePath = "Config//Config.properties";
	
	/**
	 * Method to read the values from property file 
	 */
	public ConfigReader() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	/**
	 * Method get the browser details from property file 
	 */
	public String getBrowser() {
		return properties.getProperty("browser");

	}
	/**
	 * Method choose driver path based on browser from property file 
	 */
	public String getDriverPathChrome() {
		return properties.getProperty("driver_path_chrome");
	}
	/**
	 * Method choose driver path based on browser from property file 
	 */
	public String getDriverPathFireFox() {
		return properties.getProperty("driver_path_firefox");
	}
	/**
	 * Method to get application url from property file 
	 */
	public String getApplicationUrl() {
		return properties.getProperty("url");

	}
}