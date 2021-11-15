package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        if (text != null && !text.isEmpty()) {
            WebElement el = wd.findElement(locator);
            el.click();
            el.clear();
            el.sendKeys(text);
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot(String link) {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

        File screenshot = new File(link);

        try {
            Files.copy(tmp, screenshot);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
