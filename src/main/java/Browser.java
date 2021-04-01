import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class Browser {
    
    static WebDriver driver;
    
    public static DesiredCapabilities setCap(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "MAC");
        log.info("Sets default capabilities for future optional browser's driver");
        return caps;
    }

    public static void setProp(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Sets general properties for starting the browser");
    }
    
     
}