import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Optional;

public class Browser {

    public static DesiredCapabilities setCap(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "MAC");
        return caps;
    }

    private static WebDriver createBrowser() { return (WebDriver) Optional.empty().get(); }
}