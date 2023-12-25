import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class HW21_12_23 {
    WebDriver wd;
    private JavascriptExecutor js;
    private static final Set<String> uniqueAttributes = new HashSet<>();
    private static final Random random = new Random();

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexkhenkin/Tools/chromedriver/chromedriver");
        wd = new ChromeDriver();
        js = (JavascriptExecutor) wd;
        wd.get("https://telranedu.web.app/login");
    }

    @Test
    public void testFindUniqueClasses() {
        List<WebElement> allElements = wd.findElements(By.xpath("//*"));
        HashSet<String> uniqueClasses = new HashSet<>();

        for (WebElement element : allElements) {
            String className = element.getAttribute("class");
            if (className != null && !className.isEmpty()) {
                String[] classes = className.split("\\s+");
                uniqueClasses.addAll(Arrays.asList(classes));
            }
        }

        System.out.println("Unique classes found on the page:");
        for (String cls : uniqueClasses) {
            System.out.println(cls);
        }
    }


    @Test
    public void testFindUniqueAttributes() {
        List<WebElement> allElements = wd.findElements(By.xpath("//*"));
        Set<String> uniqueAttributes = new HashSet<>();

        for (WebElement element : allElements) {
            Object attributeObj = js.executeScript(
                    "var items = {}; " +
                            "for (index = 0; index < arguments[0].attributes.length; ++index) { " +
                            "items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value; " +
                            "} " +
                            "return items;", element);

            if (attributeObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, String> attributes = (Map<String, String>) attributeObj;
                uniqueAttributes.addAll(attributes.keySet());
            }
        }

        System.out.println("Unique attributes found on the page:");
        for (String attr : uniqueAttributes) {
            System.out.println(attr);
        }
    }

    @Test
    public void findAllTagsAndIds() {
        List<WebElement> allElements = wd.findElements(By.xpath("//*"));
        System.out.println("Finding elements: " + allElements.size());
        HashSet<String> uniqueClasses = new HashSet<>();
        for (WebElement element : allElements) {
            String tagName = element.getTagName();
            String id = element.getAttribute("id");
            String className = element.getAttribute("class");
            System.out.println("Tag: " + tagName + ", ID: " + (id == null ? "non exists" : id) + ", Class: " + className);
            if (className != null && !className.isEmpty()) {
                String[] classes = className.split("\\s+");
                Collections.addAll(uniqueClasses, classes);
            }
        }
        System.out.println("Unique classes found on the page:");
        for (String cls : uniqueClasses) {
            System.out.println(cls);
        }


    }

    @Test(dependsOnMethods = "testFindUniqueAttributes")
    public void testFindAttributesByPart() {
        String startsWith = getRandomSubstring();
        String contains = getRandomSubstring();

        List<WebElement> allElements = wd.findElements(By.xpath("//*"));
        Set<String> attributesStartingWith = new HashSet<>();
        Set<String> attributesContaining = new HashSet<>();

        for (WebElement element : allElements) {
            Object attributeObj = js.executeScript(
                    "var items = {}; " +
                            "for (index = 0; index < arguments[0].attributes.length; ++index) { " +
                            "items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value; " +
                            "} " +
                            "return items;", element);

            if (attributeObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, String> attributes = (Map<String, String>) attributeObj;
                for (String attrName : attributes.keySet()) {
                    if (attrName.startsWith(startsWith)) {
                        attributesStartingWith.add(attrName);
                    }
                    if (attrName.contains(contains)) {
                        attributesContaining.add(attrName);
                    }
                }
            }
        }

        System.out.println("Attributes starting with '" + startsWith + "':");
        for (String attr : attributesStartingWith) {
            System.out.println(attr);
        }

        System.out.println("Attributes containing '" + contains + "':");
        for (String attr : attributesContaining) {
            System.out.println(attr);
        }
    }

    private String getRandomSubstring() {
        if (uniqueAttributes.isEmpty()) {
            return "";
        }
        String[] allAttributes = uniqueAttributes.toArray(new String[0]);
        String selectedAttribute = allAttributes[random.nextInt(allAttributes.length)];
        return selectedAttribute.substring(0, Math.min(3, selectedAttribute.length()));
    }

    @AfterClass
    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }
}

