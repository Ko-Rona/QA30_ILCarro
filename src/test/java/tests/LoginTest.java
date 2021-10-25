package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }

    @Test
    public void loginTest() {
        String email = "rona666@mail.ru";
        String password = "KoronA10!";

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email, password);
        app.getUser().submitLogin();
        app.getUser().pause(5000);
        app.getUser().clickLoggedIn();

        Assert.assertTrue(app.getUser().isLogged());


    }
}
