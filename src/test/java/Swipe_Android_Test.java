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

public class Swipe_Android_Test {

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

    private void Swipe() {

        WebElement pic1 = driver.findElements(By.className("android.widget.ImageView")).get(0);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        int startX = pic1.getLocation().getX() + (pic1.getSize().getWidth() / 2);
        int startY = pic1.getLocation().getY() + (pic1.getSize().getHeight() / 2);
        int endX = startX - 400;
        int endY = startY;

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(swipe));
    }

    @Test
    public void testDeveArrastarParaLado() {
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        WebElement gallery = driver.findElement(AppiumBy.accessibilityId("Gallery"));
        gallery.click();
        WebElement photo = driver.findElement(AppiumBy.accessibilityId("1. Photos"));
        photo.click();
        Swipe();
    }

    @AfterTest
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}