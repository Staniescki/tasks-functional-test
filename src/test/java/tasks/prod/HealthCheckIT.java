package tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {

	    ChromeOptions options = new ChromeOptions();

	    WebDriver driver = new RemoteWebDriver(
	            new URL("http://172.26.144.1:4444/wd/hub"),
	            options
	    );

	    try {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.navigate().to("http://host.docker.internal:9999/tasks");

	        String version = driver.findElement(By.id("version")).getText();

	        Assert.assertTrue(version.startsWith("build"));

	    } finally {
	        driver.quit();
	    }
	}

	

}
