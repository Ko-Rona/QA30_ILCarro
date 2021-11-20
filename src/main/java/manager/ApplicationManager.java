package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
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

    public ApplicationManager(String browser) {
        this.browser = browser;
    }
    String browser;

    public void init() {
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests stars with Chrome");
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests starts with Firefox");
        }
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
