import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HW21_12_23 {
    WebDriver wd;
    private JavascriptExecutor js;
    private static final Set<String> uniqueAttributes = new HashSet<>();
    private static final Random random = new Random();
    private final Set<String> uniqueClasses = new HashSet<>();
    private final List<WebElement> foundElements = new ArrayList<>();
    private List<WebElement> allElements;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexkhenkin/Tools/chromedriver/chromedriver");
        wd = new ChromeDriver();
        js = (JavascriptExecutor) wd;
        wd.get("https://telranedu.web.app/login");
       allElements = wd.findElements(By.xpath("//*"));

    }

    @Test
    public void testFindUniqueClasses() {
        uniqueClasses.clear();
        foundElements.clear();

        for (WebElement element : allElements) {
            String className = element.getAttribute("class");
            if (className != null && !className.isEmpty()) {
                String[] classes = className.split("\\s+");
                Collections.addAll(uniqueClasses, classes);
                foundElements.add(element);
            }
        }

        System.out.println("Unique classes found on the page:");
        for (String cls : uniqueClasses) {
            System.out.println(cls);
        }
    }

    @Test
    public void testFindUniqueAttributes() {
        uniqueAttributes.clear();
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

    private List<WebElement> findParents(WebElement element) {
        List<WebElement> parents = new ArrayList<>();
        try {
            WebElement parent = element.findElement(By.xpath(".."));
            while (parent != null && !(parent.getTagName().equals("html"))) {
                parents.add(parent);
                parent = parent.findElement(By.xpath(".."));
            }
        } catch (NoSuchElementException e) {
            // Обработка случая, когда родительский элемент не найден
        }
        return parents;
    }

    private List<WebElement> findDescendants(WebElement element) {
        return element.findElements(By.xpath(".//*"));
    }

    @Test
    public void testFindParentAndChildrenOfElement() {
        WebElement element = findElementByClass(uniqueClasses);
        if (element != null) {
            WebElement parentElement = element.findElement(By.xpath(".."));
            System.out.println("Parent Tag: " + (parentElement != null ? parentElement.getTagName() : "None"));
            List<WebElement> allDescendants = element.findElements(By.xpath(".//*"));
            System.out.println("Found " + allDescendants.size() + " descendants.");
            for (WebElement descendant : allDescendants) {
                System.out.println("Descendant Tag: " + descendant.getTagName());
            }
        } else {
            System.out.println("No element found with unique classes.");
        }
    }

    private WebElement findElementByClass(Set<String> uniqueClasses) {
        for (String cls : uniqueClasses) {
            WebElement element = wd.findElement(By.className(cls));
            if (element != null) {
                return element;
            }
        }
        return null;
    }

    @Test
    public void testFindParentsAndDescendants() {
        for (WebElement element : foundElements) {
            List<WebElement> parents = findParents(element);
            List<WebElement> descendants = findDescendants(element);

            System.out.println("Element: " + element.getTagName());
            System.out.println("Parents: " + parents.stream().map(WebElement::getTagName).collect(Collectors.joining(", ")));
            System.out.println("Descendants: " + descendants.stream().map(WebElement::getTagName).collect(Collectors.joining(", ")));
        }
    }

    @AfterClass
    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }
}

