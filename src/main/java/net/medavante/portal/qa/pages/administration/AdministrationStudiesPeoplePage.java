package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesPeoplePage extends BasePage {

	@FindBy(xpath = "//div[@id='dropdownContainer']//a[@class='btn circle-button btn-white']")
	private WebElement addPersonBTN;

	@FindBy(xpath = "//div[contains(@class,'dropdown')]//div[@data-ng-click='onAddStudyPeople(item)']//label")
	private List<WebElement> peopleLIST;

	@FindBy(xpath = "//div[@data-value='person.roleId']")
	private WebElement peopleSystemRoleDRPDOWN;

	@FindBy(xpath = "//div[@data-value='person.roleId']//ul[@class='dropdown-menu']//span")
	private List<WebElement> peopleSystemRoleLIST;

	@FindBy(xpath = "//div[@data-ng-form='personEditor']//a[@title='Save']")
	private WebElement saveBTN;

	public AdministrationStudiesPeoplePage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesPeoplePage() {
		Assert.assertTrue(isElementPresent(addPersonBTN));
	}

	public void addPeople(String peopleToBeSelected, String systemRoleToBeSelected) {
		waitAndClick(addPersonBTN);
		_normalWait(100);
		for (WebElement peoplName : peopleLIST) {
			if (getText(peoplName).equalsIgnoreCase(peopleToBeSelected)) {
				clickOn(peoplName);
				_normalWait(100);
				selectPeopleSystemRole(systemRoleToBeSelected);
				clickOnSaveButton();
				break;

			}
		}
		try {
			if (isElementPresent(By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"))) {
				waitForElementToBecomeInvisible(
						By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"));
			}
		} catch (Exception e) {
		}
	}

	@FindBy(xpath = "//body//div[@class='date-wrapper']")
	private List<WebElement> datepicker;
	
	@FindBy(xpath = "//div[contains(@class,'datetimepicker-widget dropdown-menu picker-open top')]//div[@class='datepicker-days']//th[@class='picker-switch']")
	private WebElement monthSelector;

	@FindBy(xpath = "//div[contains(@class,'datetimepicker-widget dropdown-menu picker-open top')]//div[@class='datepicker-months']//th[@class='picker-switch']")
	private WebElement yearSelector;

	@FindBy(xpath = "//div[contains(@class,'datetimepicker-widget dropdown-menu picker-open top')]//tbody//span[contains(@class,'month')]")
	private List<WebElement> monthLIST;

	@FindBy(xpath = "//div[contains(@class,'datetimepicker-widget dropdown-menu picker-open top')]//tbody//span[contains(@class,'year')]")
	private List<WebElement> yearList;

	@FindBy(xpath = "//div[contains(@class,'datetimepicker-widget dropdown-menu picker-open top')]//tbody//tr//td[@class='day' or contains(@class,'day active today')]")
	private List<WebElement> dayList;

	public void addMultiPeople(String peopleToBeSelected, String datePickerLabel) {

		String[] date = currentDate().split("-");

		String dateToSelect = date[0];
		String monthToSelect = date[1];
		String yearToSelect = date[2];

		waitAndClick(addPersonBTN);
		_normalWait(100);
		for (WebElement peoplName : peopleLIST) {
			if (getText(peoplName).equalsIgnoreCase(peopleToBeSelected)) {
				clickOn(peoplName);
				_normalWait(100);
				for (WebElement loc : datepicker) {
					if (loc.findElement(
							By.xpath("./parent::div/parent::div/parent::div//label[contains(@class,'small-title')]"))
							.getText().trim().equalsIgnoreCase(datePickerLabel)) {
						clickOn(loc.findElement(By.xpath(".//a[@class='add-on icon-calendar datepickerbutton']")));
						waitForElement(monthSelector);
						clickOn(monthSelector);
						waitForElement(yearSelector);
						clickOn(yearSelector);
						for (WebElement year : yearList) {
							if (year.getText().trim().equalsIgnoreCase(yearToSelect)) {
								clickOn(year);
								break;
							}
						}
						for (WebElement month : monthLIST) {
							if (month.getText().trim().equalsIgnoreCase(monthToSelect)) {
								waitAndClick(month);
								break;
							}
						}
						for (WebElement dateText : dayList) {
							if (dateText.getText().trim().equalsIgnoreCase(dateToSelect)) {
								clickOn(dateText);
								break;
							}

						}

						break;
					}
				}
				break;

			}
		}
	}

	public void selectPeopleSystemRole(String systemRoleToBeSelected) {
		waitAndClick(peopleSystemRoleDRPDOWN);
		for (WebElement systemRole : peopleSystemRoleLIST) {
			if (getText(systemRole).equalsIgnoreCase(systemRoleToBeSelected)) {
				clickOn(systemRole);
				break;
			}
		}
	}

	public void clickOnSaveButton() {
		waitForElement(saveBTN);
		clickOn(saveBTN);
	}

}
