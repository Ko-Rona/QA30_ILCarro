package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CarHelper extends HelperBase {
    public CarHelper(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.id("1"));
    }

    public void fillCarForm(Car car) {
        if (isCarCreationFormPresent()) {
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
            type(By.cssSelector(".feature-input"), car.getTypeFeature());
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
        WebElement form = wd.findElement(By.id(locator));
        String os = System.getProperty("os.name");
        System.out.println(os);

        if (os.startsWith("Mac")) {
            form.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        } else {
            form.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        }
        form.sendKeys(dates);
        form.sendKeys(Keys.ENTER);
    }

    public void typeCity(String locator, String city) {
        type(By.id(locator), city);
        click(By.cssSelector("div.pac-item"));
    }


    public boolean isSearchResults() {
        return isElementPresent(By.cssSelector(".search-results"));
    }

    public void fillCarForm2() {
        typeCity("city", "Haifa");
        selectDates("dates", "29-30");
    }

    private void selectDates(String locator, String dates) {

        String[] data = dates.split("-");
        click(By.id(locator));

        String locator1 = String.format("//td[@aria-label='November %s, 2021']", data[0]);
        String locator2 = String.format("//td[@aria-label='November %s, 2021']", data[1]);

        WebElement el1 = wd.findElement(By.xpath(locator1));

        if (el1.isEnabled()) {
            (el1.findElement(By.xpath("*"))).click();
            pause(2000);
        } else {
            return;
        }

        WebElement el2 = wd.findElement(By.xpath(locator2));
        if (el2.isEnabled()) {
            (el2.findElement(By.xpath("*"))).click();
        } else {
            return;
        }
    }

    public void fillSearchForm(String city, String from, String to) {
        typeCity("city", city);
        selectPeriod(from, to);
    }

//    private void selectPeriod(String from, String to) {
//        String[] dataFrom = from.split("/");
//        String[] dataTo = to.split("/");
//        click(By.id("dates"));
//
//        int diffStart = 0;
//        if (LocalDate.now().getMonthValue() != Integer.parseInt(dataFrom[0])) {
//            diffStart = Integer.parseInt(dataFrom[0]) - LocalDate.now().getMonthValue();
//        }
//
//        int diff = 0;
//        if (Integer.parseInt(dataFrom[0]) != Integer.parseInt(dataTo[0])) {
//            diff = Integer.parseInt(dataTo[0]) - Integer.parseInt(dataFrom[0]);
//        }
//
//        for (int i = 0; i < diffStart; i++) {
//            click(By.xpath("//button[@aria-label='Next month']"));
//        }
//
//        String locator3 = String.format("//div[.=' %s ']", dataFrom[1]);
//        click(By.xpath(locator3));
//
//        for (int i = 0; i < diff; i++) {
//            click(By.xpath("//button[@aria-label='Next month']"));
//        }
//
//        String locator4 = String.format("//div[.=' %s ']", dataTo[1]);
//        click(By.xpath(locator4));
//    }

    private void selectPeriod(String from, String to) {

        String[] dataFrom = from.split("/");
        String[] dataTo = to.split("/");
        click(By.id("dates"));

        int diffStart = 0;
        if (LocalDate.now().getMonthValue() != Integer.parseInt(dataFrom[0])) {
            diffStart = Integer.parseInt(dataFrom[0]) - LocalDate.now().getMonthValue();
            if (diffStart < 0) {
                diffStart = diffStart + 12;
            }
        }

        int diff = 0;
        if (Integer.parseInt(dataFrom[0]) != Integer.parseInt(dataTo[0])) {
            diff = Integer.parseInt(dataTo[0]) - Integer.parseInt(dataFrom[0]);
            if (diff < 0) {
                diff = diff + 12;
            }
        }

        for (int i = 0; i < diffStart; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        String locator3 = String.format("//div[.=' %s ']", dataFrom[1]);
        click(By.xpath(locator3));

        for (int i = 0; i < diff; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        String locator4 = String.format("//div[.=' %s ']", dataTo[1]);
        click(By.xpath(locator4));
    }

    public void selectPeriodNew(String fromD, String toD) {
        LocalDate from = LocalDate.parse(fromD, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate to = LocalDate.parse(toD, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        click(By.id("dates"));

        selectData(from, now);
//        int monthDiff = from.getYear()-now.getYear()==0 ? from.getMonthValue()-now.getMonthValue()
//                : 12-now.getMonthValue()+from.getMonthValue();
//        for (int i = 0; i < monthDiff; i++) {
//            click(By.xpath("//button[@aria-label='Next month']"));
//        }
        click(By.xpath(String.format("//div[.=' %s ']", from.getDayOfMonth())));
        selectData(to, from);
//        monthDiff = to.getYear()-from.getYear() == 0? to.getMonthValue() - from.getMonthValue()
//                : 12 - from.getMonthValue()+to.getMonthValue();
//        for (int i = 0; i < monthDiff; i++) {
//            click(By.xpath("//button[@aria-label='Next month']"));
//        }
        click(By.xpath(String.format("//div[.=' %s ']", to.getDayOfMonth())));

    }

    private void selectData(LocalDate first, LocalDate second) {
        int monthDiff = first.getYear() - second.getYear() == 0 ? first.getMonthValue() - second.getMonthValue()
                : 12 - second.getMonthValue() + first.getMonthValue();
        for (int i = 0; i < monthDiff; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector(".cars-container"));
    }
}
