import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
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
        List<WebElement> list = wd.findElements(By.tagName("a"));
        //find elements by class
        WebElement element1 = wd.findElement(By.className("container"));
        WebElement element2 = wd.findElement(By.cssSelector(".container"));
        List<WebElement> list1 = wd.findElements(By.className("nav-item"));
        List<WebElement> list2 = wd.findElements(By.cssSelector(".nav-item"));
        //by ID
        WebElement element3 = wd.findElement(By.id("nav"));
        WebElement element4 = wd.findElement(By.cssSelector("#nav"));
        //by attribute
        WebElement element5 = wd.findElement(By.cssSelector("[href='#item3']"));
        WebElement element6 = wd.findElement(By.cssSelector("[placeholder='Type your name']"));
        WebElement element7 = wd.findElement(By.name("name"));
        WebElement element8 = wd.findElement(By.cssSelector("[name = 'surename']"));
        //by linkText and partialTextLink
        WebElement element9 = wd.findElement(By.linkText("Item 1"));
        WebElement element10 = wd.findElement(By.partialLinkText("m 1"));
        //by xpath
        WebElement input = wd.findElement(By.cssSelector("[placeholder='Type your name']"));

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

    @AfterClass
    public void stop() {
        wd.quit();

    }


}
