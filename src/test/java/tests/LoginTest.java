package tests;

import manager.NgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(NgListener.class)

public class LoginTest extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }

//    @BeforeMethod
//    public void precondition() {
//        if (!app.getUser().isLoginPresent()) {
//            app.getUser().logout();
//        }
//    }

    @Test
    public void loginTest() {

        User user = new User().withEmail("rona666@mail.ru").withPassword("KoronA10!");

        logger.info("Test Registration Positive starts with email--->"+user.getEmail());
        logger.info("Test Registration Positive starts with password--->"+user.getPassword());
        app.getUser().openLoginRegistrationForm();
        //app.getUser().fillLoginRegistrationForm(email, password);
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(5000);

        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }


    @Test
    public void loginTestWrongEmail() {
        User user = new User().withEmail("rona666mail.ru").withPassword("KoronA10!");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);

        Assert.assertTrue(app.getUser().isErrorMessageItsNotEmail());
        Assert.assertFalse(app.getUser().isOkButtonDisabled());
    }

    @Test
    public void loginTestWrongPassword() {
        User user = new User().withEmail("rona@666mail.ru").withPassword("12345");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();

        Assert.assertTrue(app.getUser().isPopUpAuthorizationError());
        Assert.assertTrue(app.getUser().isErrorMessageWrongEmail());
    }

    @Test
    public void loginTestWrongUser() {
        User user = new User().withEmail("rona@666mail.ru").withPassword("GrrrrH10!!");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();

        Assert.assertTrue(app.getUser().isPopUpAuthorizationError());
        Assert.assertTrue(app.getUser().isErrorMessageWrongEmail());
    }


    @AfterMethod
    public void postCondition () {
        app.getUser().clickOkButton();
    }

}
