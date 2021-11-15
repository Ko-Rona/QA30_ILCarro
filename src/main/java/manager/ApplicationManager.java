package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    //WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    CarHelper car;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() {
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("Tests starts on Chrome Driver");
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.xyz/search");
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        user = new HelperUser(wd);
        car = new CarHelper(wd);
        wd.register(new MyListener());
    }

    public void stop() {
        wd.quit();
    }

    public HelperUser getUser() {
        return user;
    }

    public CarHelper getCar(){
        return car;
    }


}
