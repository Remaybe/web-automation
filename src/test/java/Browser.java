import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static DesiredCapabilities setCap(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "MAC");
        return caps;
    }

    public static void setProp(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static WebDriver createBrowser() { return (WebDriver) Optional.empty().get(); }
}