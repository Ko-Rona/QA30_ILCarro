package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class FindCarTest extends TestBase {

    @Test
    public void findCarTest1() {
        app.getCar().fillFindForm();
        app.getUser().submitForm();

        Assert.assertTrue(app.getCar().isSearchResults());
    }

//    @Test
//    public void findCarTest2() {
//        app.getCar().fillCarForm2();
//        app.getUser().submitForm();
//
//        Assert.assertTrue(app.getCar().isSearchResults());
//    }

    @Test
    public void findCarTest3() {
        app.getCar().fillSearchForm("Haifa", "11/30/2021", "8/26/2022");
        app.getUser().submitForm();

        Assert.assertTrue(app.getCar().isListOfCarsAppeared());
    }

    @Test
    public void searchTestYear(){
        app.getCar().typeCity("city", "Haifa");
        app.getCar().selectPeriodNew("12/12/2021","06/06/2022");
        app.getUser().submitForm();
        Assert.assertTrue(app.getCar().isListOfCarsAppeared());
    }
    @BeforeMethod
    public void post() {
        app.getUser().returnToStart();
    }
}
