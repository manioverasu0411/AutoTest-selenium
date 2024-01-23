package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	
	private Properties prop;
	private ChromeOptions chromeoptions;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	
	public ChromeOptions getChromeOptions() {
		chromeoptions = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) chromeoptions.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) chromeoptions.addArguments("--incognito");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			chromeoptions.setCapability("browserName", "chrome");
			chromeoptions.setBrowserVersion(prop.getProperty("browserversion").trim());
			
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			chromeoptions.setCapability("selenoid:options", selenoidOptions);
		}
		
		return chromeoptions;
			
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			fo.setBrowserVersion(prop.getProperty("browserversion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			fo.setCapability("selenoid:options", selenoidOptions);
			
		}
		
		return fo;
			
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--incognito");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
		}
		
		return eo;
			
	}
	
}
