package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (!app.getUser().isLoginPresent()) {
            app.getUser().logout();
        }
    }

    @Test(dataProvider = "registrationModelDto", dataProviderClass = MyDataProvider.class)
    public void registrationTestPositive(User user) {

        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
//        User user = new User()
//                .withName("Renata")
//                .withLastName("Finkel")
//                .withEmail("rona" + i + "@gmail.com")
//                .withPassword("RrRrRr12$");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().checkPolicy();
        app.getUser().submitForm();

        Assert.assertTrue(app.getUser().isRegistered());
    }

    @Test
    public void registrationTestNegative() {

        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .withName("Renata")
                .withLastName("Finkel")
                .withEmail("rona" + i + "@gmail.com")
                .withPassword("gjf");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().checkPolicy();

        app.getUser().submitForm();
        app.getUser().pause(100000);

        //Assert.assertTrue(app.getUser().isRegistered());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getUser().clickOkButton();
    }
}
