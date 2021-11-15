package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    public MyListener() {
        super();
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        super.afterClickOn(element, driver);
        logger.info("Click on WebElement by locator-->" +element.toString());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("Start search element by locator-->" +by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("The element with locator-->" +by+ " was found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("Something went wrong!!!");
        logger.info(String.valueOf(throwable.fillInStackTrace()));
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String link = "src/test/screenshots/screenshot-"+i+".png";
        HelperBase helperBase = new HelperBase(driver);
        helperBase.takeScreenShot(link);
        logger.info("This link to screenshot with error --->" + link);
    }
}
