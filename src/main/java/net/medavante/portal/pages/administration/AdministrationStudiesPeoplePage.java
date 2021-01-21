package net.medavante.portal.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesPeoplePage extends BasePage {

	@FindBy(xpath = "//div[@id='dropdownContainer']//a[@class='btn circle-button btn-white']")
	private WebElement addPersonBTN;

	@FindBy(xpath = "//div[contains(@class,'dropdown')]//div[@data-ng-click='onAddStudyPeople(item)']//label")
	private List<WebElement> peopleLIST;
	
	@FindBy(xpath="//div[@class='btn-group ng-isolate-scope open dropup']/div[@class='dropdown-menu']/ul/li")
	private WebElement SystemRoleList;

	@FindBy(xpath = "//div[@data-value='person.roleId']")
	private WebElement peopleSystemRoleDRPDOWN;

	@FindBy(xpath = "//div[@data-value='person.roleId']//ul[@class='dropdown-menu']//span")
	private List<WebElement> peopleSystemRoleLIST;

	@FindBy(xpath = "//div[@data-ng-form='personEditor']//a[@title='Save']")
	private WebElement saveBTN;

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

	@FindBy(xpath = "//div[@class='col-xs-2 btn-arrows text-center']/a")
	private WebElement ExpandArrow;

	@FindBy(xpath = "//div[@class='col-xs-24 editor-collapse-row']//span[@class='icon-small icon-minus']")
	private WebElement RemovePersonLk;
	
	@FindBy(xpath="//div[@class='col-xs-2 btn-arrows text-center']//a")
	private WebElement peopleDropdown;
	
	@FindBy(xpath="//label[text()='Add Person']/..//a[@disabled='disabled']")
	private WebElement addPersonDisableIcon;
	
	@FindBy(xpath="//div[@class='pull-right col-xs-9 search-block']")
	private WebElement searchField;

	public AdministrationStudiesPeoplePage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesPeoplePage() {
		Assert.assertTrue(isElementPresent(addPersonBTN));
	}

	public <T> T removePerson(Class<T> className, String peopleToBeSelected, String confrmtn) {
		waitAndClick(ExpandArrow);
		_normalWait(100);
		WebElement person = driver.findElement(ByLocator("//a[contains(text(),'" + peopleToBeSelected + "')]"));
		waitAndClick(person);
		_normalWait(100);
		waitAndClick(RemovePersonLk);
		WebElement Confirmation = driver.findElement(ByLocator(
				"//div[@id='queryConfirmation']//div[@class='btn btn-active'][contains(text(),'" + confrmtn + "')]"));
		waitAndClick(Confirmation);
		waitUntillSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, className);

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
			}else {
				
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

	/* Add Multiple People */
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
		_normalWait(2000);
		moveToElement(driver.findElement(By.xpath("//div[@class='btn-group ng-isolate-scope open dropup']/div[@class='dropdown-menu']/ul/li/span[text()='"+systemRoleToBeSelected+"']")));
		javascriptButtonClick(driver.findElement(By.xpath("//div[@class='btn-group ng-isolate-scope open dropup']/div[@class='dropdown-menu']/ul/li/span[text()='"+systemRoleToBeSelected+"']")));
		
//		for (WebElement systemRole : SystemRoleList) {
//			System.out.println(systemRole.getText());
//			System.out.println(systemRoleToBeSelected);
//			if (getText(systemRole).trim().equalsIgnoreCase(systemRoleToBeSelected)) {
//				moveToElement(systemRole);
//				javascriptButtonClick(systemRole);
//				break;
//			}
//		}
	}

	public void clickOnSaveButton() {
		waitForElement(saveBTN);
		clickOn(saveBTN);
	}

	/***
	 * Click the dropdown on the person row
	 */
	
	public void clickTheDropDownOnThePersonRow() {

		waitAndClick(peopleDropdown);
		
	}

	/***
	 * Verify people tab displayed in view mode
	 */
	
	public void peopleTabDisplayedInViewMode() {

		moveToElement(addPersonDisableIcon);
		
		String value=addPersonDisableIcon.getAttribute("disabled");
		if(value.contains("disabled")){
			Assert.assertTrue(true);
		}
	}

	/*** 
	 * Verify search field
	 */
	
	
	public void searchFieldIsAvailable() {

		moveToElement(searchField);
		if(searchField.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}
	
	/***
	 * Verify person details row displayed
	 */

	@FindBy(xpath="//div[@id='ed86d62e-ccee-40b5-84b2-54b148674f00']")
	private WebElement personDetailsRow;
	public void personDetailsRowDispalyed() {
		moveToElement(personDetailsRow);
		if(personDetailsRow.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}

}
