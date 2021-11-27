package tests;

import models.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (app.getUser().isLoginPresent()) {
            app.getUser().login(new User().withEmail(app.email()).withPassword(app.password()));
        }
    }

    @Test(groups = {"aaa"})
    public void addNewCarTestPositive() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .address("Tel Aviv")
                .make("RangeRover")
                .model("Sport")
                .year("2020")
                .engine("3.6")
                .fuel("Petrol")
                .gear("MT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .carClass("C")
                .fuelConsumption("13")
                .carRegNumber("100-55" + i)
                .price("500")
                .distanceIncluded("5000")
                .typeFeature("type")
                .about("Very nice")
                .build();
        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);
        app.getCar().attachedPhoto("E:\\QA30\\QA30_ILCarro\\2021_land-rover_range-rover-sport_4dr-suv_p400-hst_fq_oem_10_815.jpg");
        app.getUser().submitForm();

        Assert.assertTrue(app.getCar().isPopUpCarAdded());
        Assert.assertTrue(app.getCar().thisCarIsAdded(car.getMake(), car.getModel()));

    }

    @Test
    public void addNewCarTestPositive2() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .address("Haifa")
                .make("BMW")
                .model("X6")
                .year("2017")
                .engine("4.2")
                .fuel("Diesel")
                .gear("AT")
                .wD("AWD")
                .doors("3")
                .seats("2")
                .carClass("B")
                .fuelConsumption("16")
                .carRegNumber("100-50" + i)
                .price("700")
                .distanceIncluded("1000")
                .typeFeature("type")
                .about("Car car car")
                .build();
        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);
        app.getCar().attachedPhoto("E:\\QA30\\QA30_ILCarro\\auto.jpeg");
        app.getUser().submitForm();

        app.getUser().pause(3000);
        Assert.assertTrue(app.getCar().isPopUpCarAdded());
        Assert.assertTrue(app.getCar().thisCarIsAdded(car.getMake(), car.getModel()));
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getCar().clickButton();
        app.getUser().logout();
        app.getUser().pause(5000);
    }
}
