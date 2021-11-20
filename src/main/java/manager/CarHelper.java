package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class CarHelper extends HelperBase{
    public CarHelper(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.id("1"));
    }

    public void fillCarForm(Car car) {
        if(isCarCreationFormPresent()){
            typeLocation(car.getAddress());
            type(By.id("make"), car.getMake());
            type(By.id("model"), car.getModel());
            type(By.id("year"), car.getYear());
            type(By.id("engine"), car.getEngine());

            select(By.id("fuel"), car.getFuel());
            select(By.id("gear"), car.getGear());
            select(By.id("wheelsDrive"), car.getWD());

            type(By.id("doors"), car.getDoors());
            type(By.id("seats"), car.getSeats());
            type(By.id("class"), car.getCarClass());
            type(By.id("fuelConsumption"), car.getFuelConsumption());
            type(By.id("serialNumber"), car.getCarRegNumber());
            type(By.id("price"), car.getPrice());
            type(By.id("distance"), car.getDistanceIncluded());
            type(By.cssSelector(".feature-input"),car.getTypeFeature());
            type(By.id("about"), car.getAbout());

        }
    }

    public void select(By locator, String option) {
        new Select(wd.findElement(locator)).selectByValue(option);
//        new Select(wd.findElement(locator)).selectByVisibleText(" Petrol ");
//        new Select(wd.findElement(locator)).selectByIndex(1);
    }

    private void typeLocation(String address) {
        type(By.id("pickUpPlace"), address);
        click(By.cssSelector("div.pac-item"));
        pause(5000);
    }

    private boolean isCarCreationFormPresent() {
        Boolean isForm = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.textToBePresentInElement
                        (wd.findElement(By.cssSelector("h2")),
                                "Write some details about your car to rent it out"));
        return isForm;
    }

    public void attachedPhoto(String path) {
    wd.findElement(By.id("photos"))
            .sendKeys(path);
    }

    public boolean isPopUpCarAdded() {
        WebElement el = wd.findElement(By.cssSelector(".mat-dialog-container"));
        String message = el.getText();
        return message.contains("Car added");
    }

    public boolean thisCarIsAdded(String make, String model) {
        WebElement el = wd.findElement(By.className("message"));
        String message = el.getText();
        return message.contains(make + " " + model + " added successful");
    }

    public void clickButton() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void fillFindForm() {
        typeCity("city", "Haifa");
        typeDates("dates", "12/14/2021 - 12/21/2021");
    }

    private void typeDates(String locator, String dates) {
        type(By.id(locator), dates);
    }

    private void typeCity(String locator, String city) {
        type(By.id(locator), city);
        click(By.cssSelector("div.pac-item"));
    }


    public boolean isSearchResults() {
        return isElementPresent(By.cssSelector(".search-results"));
    }

    public void fillCarForm2() {
        typeCity("city", "Haifa");
        selectDates("dates", "25-28");
    }

    private void selectDates(String locator, String dates) {

        String [] data = dates.split("-");
        click(By.id(locator));

        String locator1 = String.format("//td[@aria-label='November %s, 2021']", data[0]);
        String locator2 = String.format("//td[@aria-label='November %s, 2021']", data[1]);

        WebElement el1 = wd.findElement(By.xpath(locator1));

        if(el1.isEnabled()){
            (el1.findElement(By.xpath("*"))).click();
            pause(2000);
        }else{
            return;
        }

        WebElement el2 = wd.findElement(By.xpath(locator2));
        if(el2.isEnabled()){
            (el2.findElement(By.xpath("*"))).click();
        }else{
            return;
        }
    }
}
