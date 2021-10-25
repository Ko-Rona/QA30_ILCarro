package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        click(By.xpath("//a[@href='/login?url=%2Fsearch']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public boolean isLogged() {

        return isElementPresent(By.xpath("//a[contains(text(),'Logout')]"));
    }

    public void logout() {

        click(By.xpath("//button[@href='/logout?url=%2Fsearch']"));
    }

    public void clickLoggedIn() {
        click(By.xpath("//button[text()='Ok']"));
    }
}
