package testCases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import utils.Methods;
import base.BaseTest;
import pages.CabBookingPage;

public class CabBookingPageTest extends BaseTest{
	
	@DataProvider(name = "testData")
	public String[][] loginData(){
		String[][] arr = {{"Cabs", "Outstation One-Way", "Delhi", "Manali, Himachal Pradesh", "Dec 09 2025", "12:40 PM", "SUV"}};
		return arr;
	}
	
	@Test(dataProvider = "testData")
	public void oneWayCabSearch(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        WebElement cab = Methods.findLowestCharges();
        Methods.takeElementScreenshot(cab, "cab.png");
	}

	@Test(dataProvider = "testData")
	public void verifyFromLocationInResults(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String expectedFromLocation = fromLocation;
        String actualFromLocation = driver.findElement(By.id("from_location")).getAttribute("value");
    	System.out.println(expectedFromLocation +" - "+actualFromLocation +" - "+ expectedFromLocation == actualFromLocation);
    	Assert.assertTrue(actualFromLocation.contains(expectedFromLocation));
	}

	@Test(dataProvider = "testData")
	public void verifyToLocationInResults(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String expectedToLocation = toLocation;
        String actualToLocation = driver.findElement(By.id("to_location")).getAttribute("value");
    	System.out.println(expectedToLocation +" - "+actualToLocation +" - "+ expectedToLocation == actualToLocation);
    	Assert.assertTrue(actualToLocation.contains(actualToLocation));
	}

	@Test(dataProvider = "testData")
	public void verifyPickUpDateInResults(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String actualDateString = driver.findElement(By.id("pickup_date")).getAttribute("value");
        String expectedDateString = date;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

        LocalDate date1 = LocalDate.parse(actualDateString, formatter1);
        LocalDate date2 = LocalDate.parse(expectedDateString, formatter2);

        
    	System.out.println(expectedDateString +" - "+actualDateString +" - "+ expectedDateString == actualDateString);
    	Assert.assertTrue(date1.equals(date2));
	}

	@Test(dataProvider = "testData")
	public void verifyPickUpTimeInResults(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String expectedPickUpTime = time;
        String actualPickUpTime = driver.findElement(By.id("pickup_time")).getAttribute("value");
    	System.out.println(expectedPickUpTime +" - "+actualPickUpTime +" - "+ expectedPickUpTime == actualPickUpTime);
    	Assert.assertEquals(expectedPickUpTime, actualPickUpTime);
	}

	@Test(dataProvider = "testData")
	public void verifyCabTypeInResult(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        
        // To verify the Car Type with help of number of seats (SUV has 6 seats)
        List<WebElement> cabs = driver.findElements(By.xpath("//div[contains(@class, 'cabDetailsCard_utilitiesInfo')]"));
        for(WebElement cab : cabs) {
        	String expectedNumberOfSeats = "6 Seats";
        	String actualNumberOfSeats = cab.findElement(By.tagName("span")).getText();
        	System.out.println(expectedNumberOfSeats +" - "+actualNumberOfSeats +" - "+ expectedNumberOfSeats == actualNumberOfSeats);
        	Assert.assertEquals(expectedNumberOfSeats, actualNumberOfSeats);
        }
	}

	@Test(dataProvider = "testData")
	public void verifyClearAllFilterFunctionality(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        cabBookingPage.clearAll();
        
        try {
        	driver.findElement(By.xpath("//div[contains(@class='checkbox_checked')]"));
        	Assert.assertTrue(true);
        } catch(Exception e) {
        	System.out.println("Cleared all filters");
        }   
	}
	
	@Test(dataProvider = "testData")
	public void y(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String expectedFromLocation = fromLocation;
        String actualFromLocation = driver.findElement(By.id("from_location")).getAttribute("value");
    	System.out.println(expectedFromLocation +" - "+actualFromLocation +" - "+ expectedFromLocation == actualFromLocation);
    	Assert.assertEquals(expectedFromLocation, actualFromLocation);
	}

	@Test(dataProvider = "testData")
	public void z(String section, String tripType, String fromLocation, String toLocation, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(fromLocation);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(toLocation);
		cabBookingPage.selectDestinationFromSuggestion(toLocation);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        
        String expectedFromLocation = fromLocation;
        String actualFromLocation = driver.findElement(By.id("from_location")).getAttribute("value");
    	System.out.println(expectedFromLocation +" - "+actualFromLocation +" - "+ expectedFromLocation == actualFromLocation);
    	Assert.assertEquals(expectedFromLocation, actualFromLocation);
	}

}
