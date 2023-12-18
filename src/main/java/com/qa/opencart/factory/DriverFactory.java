package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight = null;
	

	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		//String browserName = System.getProperty("browser");
		
		System.out.println("The browser name is: " + browserName);
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {

		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;

		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("Please pass the valid browser... " + browserName);
			throw new FrameworkException("No Browser Found...");

		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	

	
	public Properties initProp() {
		
		// mvn clean install -Denv="qa"
		FileInputStream ip = null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("env name is: "+envName);
		
		try {
		if(envName == null) {
			ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;

			default:
				System.out.println("Please pass valid environment...."+envName);
				throw new FrameworkException("Wrong Environment: "+envName);

			}
		}
		
		}
		catch(FileNotFoundException e) {
			
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}


	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
				
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}

}
