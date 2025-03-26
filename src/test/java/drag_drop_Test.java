import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class drag_drop_Test {

    AndroidDriver driver;
    public AndroidTouchAction actions;


    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "16.0");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:app", "C:/Users/Neto/Desktop/Appium-Project/apps/ApiDemos-debug.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);

        actions = new AndroidTouchAction(driver);
    }

    private void DragAndDrop() {
        WebElement drag = driver.findElement(By.id("drag_dot_1"));
        WebElement drop = driver.findElement(By.id("drag_dot_2"));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        int startX = drag.getLocation().getX() + (drag.getSize().getWidth() / 2);
        int startY = drag.getLocation().getY() + (drag.getSize().getHeight() / 2);
        int endX = drop.getLocation().getX() + (drop.getSize().getWidth() / 2);
        int endY = drop.getLocation().getY() + (drop.getSize().getHeight() / 2);

        Sequence dragAndDrop = new Sequence(finger, 0);
        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(dragAndDrop));
    }

    @Test
    public void testDeveArrastarParoLado() {
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        WebElement DragAndDrop = driver.findElement(AppiumBy.accessibilityId("Drag and Drop"));
        DragAndDrop.click();
        DragAndDrop();
    }

    @AfterTest
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}