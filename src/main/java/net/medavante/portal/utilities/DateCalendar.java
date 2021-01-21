package net.medavante.portal.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.selenium.core.BaseTest;

public class DateCalendar {

	/*private WebElement hourLINK = driver
			.findElement(By.xpath("//div[contains(@class,'picker-open')]//span[@class='timepicker-hour']")),
			minutesLINK = driver
					.findElement(By.xpath("//div[contains(@class,'picker-open')]//span[@class='timepicker-minute']")),timeAMarker=driver.findElement(By.xpath("//button[@data-action='togglePeriod']")),selectedDurationTimeTXT=driver.findElement(By.xpath("//div[@data-ng-required='isDurationRequired()']//label[@data-ng-if='isDurationEdited()']")),startedTimePickerBTN=driver.findElement(By.xpath("//div[@data-ng-required='isStartedRequired()']//div[@id='timepicker']")),durationTimePickerBTN=driver.findElement(By.xpath("//div[@data-ng-required='isDurationRequired()']//div[@id='timepicker']"));

	private List<WebElement> hourLIST = driver
			.findElements(By.xpath("//div[contains(@class,'picker-open')]//div[@data-action='selectHour']//tr//td")),
			minutesLIST = driver.findElements(
					By.xpath("//div[contains(@class,'picker-open')]//div[@data-action='selectMinute']//tr//td"));

	*//**
	 * Set Time
	 * 
	 * @param hourToBeSelect
	 *            (Range Select from 01 To 12)
	 * @param minutesToBeSelected
	 *            (Range Select from 00 To 55 with the difference of 5 Minute
	 *            interval)
	 * @param timeMarker
	 *            (Select AM or PM)
	 *//*
	public void setStartedTime(String hourToBeSelect, String minutesToBeSelected, String timeMarker) {
		clickOn(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(hourToBeSelect)) {
				clickOn(hourTXT);
				break;
			}
		}
		waitAndClick(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(minutesToBeSelected)) {
				clickOn(minuteTXT);
				break;
			}
		}
		if (getText(timeAMarker).equalsIgnoreCase(timeMarker) == false) {
			clickOn(timeAMarker);
		}
		waitAndClick(startedTimePickerBTN);

	}

	*//**
	 * Set Time
	 * 
	 * @param hourToBeSelect
	 *            (Range Select from 01 To 24)
	 * @param minutesToBeSelected
	 *            (Range Select from 00 To 55 with the difference of 5 Minute
	 *            interval)
	 * @param hourToBeSelect
	 * @param minutesToBeSelected
	 *//*
	public void setDurationTime(String hourToBeSelect, String minutesToBeSelected) {
		clickOn(durationTimePickerBTN);
		clickOn(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(hourToBeSelect)) {
				clickOn(hourTXT);
				break;
			}
		}
		clickOn(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(minutesToBeSelected)) {
				clickOn(minuteTXT);
				break;
			}
		}

		clickOn(durationTimePickerBTN);
	}*/

	public String getFutureDate() {
	    Calendar calendar = Calendar.getInstance();
	    Date today = calendar.getTime();
	    // add one day to the date/calendar
	    calendar.add(Calendar.DAY_OF_YEAR, -1);
	    // now get "tomorrow"
	    Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("d");  
        String strDate = dateFormat.format(tomorrow);  
	    return strDate;

	}

	public static String dateTimeFileName(){
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
		return currentTime.format(formatter).replace("-", "").replace(":", "").replace(".", "").replaceAll(" ", "");
	}

	/*
	 * protected WebElement element; public final String DROPDOWNXPATH =
	 * ".//button[@class='btn dropdown-toggle btn-default']"; public final
	 * String INPUTXPATH = "./input[@type='text']"; public final String
	 * TEXTAREAXPATH = ".//textarea"; public final String CALENDARXPATH =
	 * ".//div[@class='value']";
	 * 
	 * /* The header controls
	 * 
	 * private By prev = By.xpath(
	 * "//div[contains(@class,'dropdown-menu picker')]//th[@class='prev']" );
	 * private By next = By.xpath(
	 * "//div[contains(@class,'dropdown-menu picker')]//th[@class='prev']/following-sibling::th[@class='next']"
	 * ); private By month_header = By.xpath(
	 * "//div[contains(@class,'dropdown-menu picker')]//th[@class='prev']/following-sibling::th[@class='picker-switch']"
	 * ); private String datesXPATHlocatorFirstPart =
	 * ".//td[@class='day'][text()='", datesXPATHlocatorSecondPart =
	 * "']|.//td[@class='day active'][text()='", datesXPATHlocatorThirdPart =
	 * "']";
	 * 
	 *//**
		 * Sets the year using the header's previous and next buttons
		 * 
		 * @param year
		 *            String, must be of the format YYYY
		 * @return boolean, false if anything goes wrong, true otherwise
		 */
	/*
	 * public boolean setYear(String year) {
	 * waitForElement(driver.findElement(By.xpath(
	 * "//div[contains(@class,'dropdown-menu picker')]//th[@class='prev']/following-sibling::th[@class='picker-switch']"
	 * ))); String widgetYear =
	 * DateCalendar.splitSpaces(driver.findElement(month_header).getText())[1];
	 * if (!year.matches("\\d{4}")) throw new SkipException(
	 * "The format of the year parameter was not correct, expected format YYYY but was ["
	 * + year + "]"); if (year.equals(widgetYear)) return true; try { int
	 * yearAsNumber = Integer.parseInt(year); int widgetYearAsNumber =
	 * Integer.parseInt(widgetYear); while (yearAsNumber > widgetYearAsNumber) {
	 * driver.findElement(next).click(); _normalWait(100); widgetYear =
	 * DateCalendar.splitSpaces(driver.findElement(month_header).getText())[1];
	 * widgetYearAsNumber = Integer.parseInt(widgetYear); } while (yearAsNumber
	 * < widgetYearAsNumber) { driver.findElement(prev).click();
	 * _normalWait(100); widgetYear =
	 * DateCalendar.splitSpaces(driver.findElement(month_header).getText())[1];
	 * widgetYearAsNumber = Integer.parseInt(widgetYear); } return true; } catch
	 * (Exception e) { // logger.logWarning( // "There was an error while trying
	 * to set the Year from the Date Widget's Year // : " + e.getMessage());
	 * return false; } }
	 * 
	 *//**
		 * Sets the Month using the header's previous and next buttons
		 * 
		 * @param month
		 *            String, must be of the format and range JAN to DEC
		 * @return false if anything goes wrong, true otherwise
		 */
	/*
	 * public boolean setMonth(String month) {
	 * waitForElement(element.findElement(month_header)); String widgetMonth =
	 * DateCalendar.splitSpaces(element.findElement(month_header).getText())[0];
	 * if (month.equals(widgetMonth)) return true; try { int monthAsNumber =
	 * getMonthNumberFromName(month); if (monthAsNumber == 0) throw new
	 * SkipException("The format of the month parameter was not correct [" +
	 * month + "]"); int widgetMonthAsNumber =
	 * getMonthNumberFromName(widgetMonth); while (monthAsNumber >
	 * widgetMonthAsNumber) { element.findElement(next).click();
	 * _normalWait(100); widgetMonth =
	 * DateCalendar.splitSpaces(element.findElement(month_header).getText())[0];
	 * widgetMonthAsNumber = getMonthNumberFromName(widgetMonth); } while
	 * (monthAsNumber < widgetMonthAsNumber) {
	 * element.findElement(prev).click(); _normalWait(100); widgetMonth =
	 * DateCalendar.splitSpaces(element.findElement(month_header).getText())[0];
	 * widgetMonthAsNumber = getMonthNumberFromName(widgetMonth); } return true;
	 * } catch (Exception e) { // logger.logWarning( // "There was an error
	 * while trying to set the Year from the Date Widget's Month // : " +
	 * e.getMessage()); return false; }
	 * 
	 * }
	 * 
	 * public static String[] splitSpaces(String text) { String[] parts =
	 * text.split("\\s+"); return parts; }
	 * 
	 *//**
		 * Sets the date from the Date Picker by directly clicking on the
		 * desired number
		 * 
		 * @param date
		 *            String of the format and range 01 to 31, depending on the
		 *            month
		 * @return boolean false if anything goes wrong, true otherwise
		 */
	/*
	 * public boolean setDate(String date) { try { if (date.startsWith("0"))
	 * date = date.replaceFirst("0", ""); By datesXpath =
	 * By.xpath(datesXPATHlocatorFirstPart + date + datesXPATHlocatorSecondPart
	 * + date + datesXPATHlocatorThirdPart); WebElement dateElement =
	 * element.findElement(datesXpath); dateElement.click(); return true; }
	 * catch (NoSuchElementException ne) { // logger.logWarning("The Date : " +
	 * date + " was not found for the Date // Widget."); return false; } }
	 * 
	 *//**
		 * Helper function, returns the given month's corresponding number
		 * 
		 * @param monthName
		 * @return
		 *//*
		 * private int getMonthNumberFromName(String monthName) { switch
		 * (monthName) { case "JAN": case "January": return 1; case "FEB": case
		 * "February": return 2; case "MAR": case "March": return 3; case "APR":
		 * case "April": return 4; case "MAY": case "May": return 5; case "JUN":
		 * case "June": return 6; case "JUL": case "July": return 7; case "AUG":
		 * case "August": return 8; case "SEP": case "September": return 9; case
		 * "OCT": case "October": return 10; case "NOV": case "November": return
		 * 11; case "DEC": case "December": return 12; default: return 0; } }
		 */

}
