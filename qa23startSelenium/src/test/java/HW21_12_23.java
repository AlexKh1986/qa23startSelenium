import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class HW21_12_23 {
    WebDriver wd;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexkhenkin/Tools/chromedriver/chromedriver");
        wd = new ChromeDriver();
        wd.get("https://telranedu.web.app/login");
    }

    @Test
    public void findAllTagsAndIds() {
        //do hw using class index like a sample and use all

        List<WebElement> allElements = wd.findElements(By.xpath("//*"));
        System.out.println("Finding elements: " + allElements.size());

        HashSet<String> uniqueClasses = new HashSet<>();

        for (WebElement element : allElements) {
            String tagName = element.getTagName();
            String id = element.getAttribute("id");
            String className = element.getAttribute("class");

            // Вывод информации о каждом элементе
            System.out.println("Tag: " + tagName + ", ID: " + (id == null ? "non exists" : id) + ", Class: " + className);

            // Добавляем имя класса в HashSet, если оно не пустое
            if (className != null && !className.isEmpty()) {
                // Элементы могут иметь несколько классов, разделенных пробелами
                String[] classes = className.split("\\s+");
                Collections.addAll(uniqueClasses, classes);
            }
        }
        System.out.println("Unique classes found on the page:");
        for (String cls : uniqueClasses) {
            System.out.println(cls);
        }

        if (wd != null) {
            wd.quit();
        }
    }

}
