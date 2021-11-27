package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//@Listeners(NgListener.class)

public class LoginTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
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

    @Test(dataProvider = "loginModelDto", dataProviderClass = MyDataProvider.class)
    public void loginTest(User user) {

        //User user = new User().withEmail("rona666@mail.ru").withPassword("KoronA10!");

        logger.info("Test Registration Positive starts with user--->"+user.toString());
        app.getUser().openLoginRegistrationForm();
        //app.getUser().fillLoginRegistrationForm(email, password);
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);

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

    @Test(dataProvider = "loginDto", dataProviderClass = MyDataProvider.class)
    public void testLoginWithString(String email, String password){
       logger.info("Start with email--->" + email + "With password--->" + password);
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email, password);
        app.getUser().submitForm();

        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition () {
        app.getUser().clickOkButton();
    }
}
