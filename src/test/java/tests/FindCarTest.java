package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindCarTest extends TestBase {

    @Test
    public void findCarTest1() {
        app.getCar().fillFindForm();
        app.getUser().submitForm();

        Assert.assertTrue(app.getCar().isSearchResults());
    }

    @Test
    public void findCarTest2() {
        app.getCar().fillCarForm2();
        app.getUser().submitForm();

        Assert.assertTrue(app.getCar().isSearchResults());
    }
}
