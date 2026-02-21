package tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {
	
	public WebDriver setup() {
		
	    try {
	    	ChromeOptions options = new ChromeOptions();
	    	return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
	    WebDriver driver = this.setup();
	    
	    try {
	    	
	    	driver.get("http://172.26.144.1:8001/tasks");
	    	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar a mensagem
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
	    	
	    	
	    } finally {
	    	driver.quit();
	    }
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		WebDriver driver = this.setup();
		
		try {
			
			driver.get("http://172.26.144.1:8001/tasks");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar a mensagem
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
			
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		WebDriver driver = this.setup();
		
		try {
			
			driver.get("http://172.26.144.1:8001/tasks");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
						
			//clicar em salvar			
			driver.findElement(By.id("saveButton")).click();
			
			//validar a mensagem
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
			
		} finally {
			driver.quit();
		}
	}
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
		WebDriver driver = this.setup();
		
		try {
			
			driver.get("http://172.26.144.1:8001/tasks");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
						
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar a mensagem
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally {
			driver.quit();
		}
	}

	    @Test
    	public void deveRemoverTarefaComSucesso() {

    	    WebDriver driver = this.setup();

    	    try {

    	    	driver.get("http://frontend:8080/tasks");

    	    	// inserir tarefa
    			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    			// clicar em add todo
    			driver.findElement(By.id("addTodo")).click();

    			// escrever descricao
    			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

    			// escrever a data
    			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

    			//clicar em salvar
    			driver.findElement(By.id("saveButton")).click();

    			//validar a mensagem
    			String message = driver.findElement(By.id("message")).getText();

    			Assert.assertEquals("Success!", message);

    			// remover tarefa
                driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();

                //validar a mensagem
                message = driver.findElement(By.id("message")).getText();

                Assert.assertEquals("Success!", message);

    	    } finally {
    	    	driver.quit();
    	    }
    	}

}
