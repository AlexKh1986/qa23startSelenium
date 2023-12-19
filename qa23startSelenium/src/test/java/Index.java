import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class Index {
    WebDriver wd;
    @BeforeClass
    public void setUp(){
        wd = new ChromeDriver();
        wd.get("file:///Users/alexkhenkin/Desktop/studyhard/21.index.html");
    }
    @Test
    public void cssLocator(){
        //tag name
       WebElement element =  wd.findElement(By.tagName("button"));
        List list  = (List) wd.findElement(By.tagName("a"));
    }
    @Test
    public  void stop(){
        wd.quit();
    }
}
