package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void submitLogin() {
        WebElement submit = wd.findElement(By.xpath("//button[@type='submit']"));
        submit.submit();
    }

    public void submitForm() {
        WebElement submit = wd.findElement(By.xpath("//button[@type='submit']"));
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.elementToBeClickable(submit));
        submit.submit();
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[contains(text(),'Logout')]"));
    }

    public boolean isLoggedSuccess() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));
        return wd.findElement(By.cssSelector(".dialog-container h2")).getText().contains("success");
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public void clickLoggedIn() {
        click(By.xpath("//button[text()='Ok']"));
    }

    public void clickOkButton() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public boolean isLoginPresent() {
        return isElementPresent(By.xpath("//a[text()=' Log in ']"));
    }

    public boolean isErrorMessageItsNotEmail() {
        WebElement el = wd.findElement(By.xpath("//div[text()=\"It'snot look like email\"]"));
        String message = el.getText();
        return message.contains("It'snot look like email");
    }

    public boolean isOkButtonDisabled() {
        WebElement el = wd.findElement(By.xpath("//button[@type='submit']"));
        return el.isEnabled();
    }

    public boolean isPopUpAuthorizationError() {
        WebElement el = wd.findElement(By.xpath("//mat-dialog-container[@role='dialog']"));
        String message = el.getText();
        return message.contains("Authorization error");
    }

    public boolean isErrorMessageWrongEmail() {
        WebElement el = wd.findElement(By.xpath("//*[@aria-modal='true']"));
        String message = el.getText();
        return message.contains("Wrong email or password");
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
        clickLoggedIn();
        pause(5000);
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
        //click(By.xpath("//label[contains(text(),'I agree to the')]"));

        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click();");
        js.executeScript("document.querySelector('#terms-of-use').checked=true;");
    }

    public boolean isRegistered() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));
        return wd.findElement(By.cssSelector(".dialog-container h1")).getText().contains("Registered");
    }

    public void checkPolicy() {
        //click(By.xpath("//label[contains(text(),'I agree to the')]"));

//        JavascriptExecutor js = (JavascriptExecutor) wd;
//        js.executeScript("document.querySelector('#terms-of-use').click();");
//        js.executeScript("document.querySelector('#terms-of-use').checked=true;");
//        click(By.id("email"));

        Actions action = new Actions(wd);
        WebElement container = wd.findElement(By.cssSelector(".checkbox-container"));
        Rectangle rect = container.getRect();
        //int x=rect.getX() +rect.getHeight()/10;
        //int x=rect.getX() +2%;
        int x = rect.getX() + 5;
        int y = rect.getY() * (1 / 4 * rect.getHeight());
        action.moveByOffset(x, y).click().perform();
    }
}
