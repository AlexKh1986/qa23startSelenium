import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

public class Index {
    WebDriver wd;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexkhenkin/Tools/chromedriver/chromedriver");
        wd = new ChromeDriver();
        wd.get("file:///Users/alexkhenkin/Desktop/studyhard/21.index.html");
    }

    @Test
    public void cssLocator() {
        //tag name
        WebElement element = wd.findElement(By.tagName("button"));
        //   List<WebElements> list  =  wd.findElement(By.tagName("a"));
    }

    @Test
    public void findAnchorTags() {
        List<WebElement> anchorElements = wd.findElements(By.tagName("a"));
        for (WebElement anchor : anchorElements) {
            System.out.println("Anchor text: " + anchor.getText());
        }
    }

    @Test
    public void findDivTags() {
        List<WebElement> divElements = wd.findElements(By.tagName("div"));
        for (WebElement div : divElements) {
            System.out.println("Div text: " + div.getText());
        }
    }

    @Test
    public void findLiTags() {
        List<WebElement> liElements = wd.findElements(By.tagName("li"));
        for (WebElement li : liElements) {
            System.out.println("List item text: " + li.getText());
        }
    }

    @Test
    public void findMetaTags() {
        List<WebElement> metaElements = wd.findElements(By.tagName("meta"));
        for (WebElement meta : metaElements) {
            System.out.println("Meta content: " + meta.getAttribute("content"));
        }
    }

    @Test
    public void findThTags() {
        List<WebElement> thElements = wd.findElements(By.tagName("th"));
        for (WebElement th : thElements) {
            System.out.println("Table header text: " + th.getText());
        }
    }

    @Test
    public void findTrTags() {
        List<WebElement> trElements = wd.findElements(By.tagName("tr"));
        for (WebElement tr : trElements) {
            System.out.println("Table row text: " + tr.getText());
        }
    }

    @Test
    public void stop() {
        wd.quit();
    }
}
