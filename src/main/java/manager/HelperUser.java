package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebDriverWait wait = new WebDriverWait(wd,10);
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

    public void login(User user ) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
        clickLoggedIn();
        pause(5000);
    }
}
