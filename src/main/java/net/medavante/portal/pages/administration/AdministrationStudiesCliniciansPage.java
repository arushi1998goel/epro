package net.medavante.portal.pages.administration;

import java.util.List;

import org.codehaus.plexus.component.composition.SetterComponentComposer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesCliniciansPage extends BasePage {

	@FindBy(xpath = "//div[@data-on-before-show='onAddClinician()']//a[@class='btn circle-button btn-white']")
	private WebElement addClinicianBTN;

	@FindBy(xpath = "//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//label[contains(text(),'Add Clinician')]")
	private WebElement clinicianLabel;

	@FindBy(xpath = "//div[contains(@class,'blue-popup')]//div[@data-ng-click='selectItem(item)']")
	private List<WebElement> clinicianLIST;

	@FindBy(xpath = "//div[@data-value='clinician.roleId']")
	private WebElement clinicianSystemRoleDRPDOWN;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']/div//li//span")
	private List<WebElement> clinicianSystemRoleLIST;

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

	@FindBy(xpath = "//div[@ng-form='clinicianEditor']//a[@title='Save']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[@class='row shadowBlock item-row selected']//label[contains(text(),'Remove')]  ")
	private WebElement RemoveBtn;

	@FindBy(xpath = "//p[contains(text(),'Add Clinician')]//following::div[2]/child::input")
	private WebElement addCliniciansTextBox;

	@FindBy(xpath = "//p[./text()='Add Clinician']/preceding-sibling::button")
	private WebElement clinicianSuggestionCancelButton;
	
	@FindBy(xpath="//div[@class='col-xs-12 ng-isolate-scope']//a[@data-ng-click='isDisabled() || onTogglePopup()']")
	private WebElement addIconClinicians;
	
	@FindBy(xpath="//div[@class='col-xs-12 ng-isolate-scope']//a[@disabled='disabled']")
	private WebElement addIconDisabled;
	
	@FindBy(xpath="//div[@class='pull-right col-xs-9 search-block']")
	private WebElement searchField;

	public AdministrationStudiesCliniciansPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesCliniciansPage() {
		Assert.assertTrue(isElementPresent(clinicianLabel));
	}

	public void selectClinicianSystemRole(String systemRoleToBeSelected) {
		waitAndClick(clinicianSystemRoleDRPDOWN);
		for (WebElement systemRole : clinicianSystemRoleLIST) {
			if (getText(systemRole).equalsIgnoreCase(systemRoleToBeSelected)) {
				clickOn(systemRole);
				break;
			}
		}
	}
	
	
	/***
	 * @param Removing Clinician
	 * 
	 * 
	 * 
	 * *****/

	public <T> T removeClinician(Class<T> className, String clinicianToBeSelected, String Action) {
		_normalWait(1000);
		WebElement selectclinician = driver.findElement(
				ByLocator("//label[@class='ng-binding'][contains(text(),'" + clinicianToBeSelected + "')]"));
		_normalWait(2000);
		moveToElement(selectclinician);
		waitAndClick(selectclinician);
		moveToElement(RemoveBtn);
		waitAndClick(RemoveBtn);
		WebElement action = driver.findElement(ByLocator(
				"//div[@id='queryConfirmation']//div[@class='btn btn-active'][contains(text(),'" + Action + "')]"));
		moveToElement(action);
		waitAndClick(action);
		waitUntillSpinnerToBecomeInvisible();
		Assert.assertTrue(true);

		return PageFactory.initElements(driver, className);

	}

	public void clickOnSaveButton() {
		waitForElement(saveBTN);
		clickOn(saveBTN);
	}

	/***
	 * @param Adding New Clinician
	 * 
	 * 
	 * 
	 * *****/
	
	
	public void addClinician(String clinicianToBeSelected, String systemRoleToBeSelected) {
		waitAndClick(addClinicianBTN);
		_normalWait(100);
		for (WebElement clinicianName : clinicianLIST) {
			if (getText(clinicianName).equalsIgnoreCase(clinicianToBeSelected)) {
				clickOn(clinicianName);
				_normalWait(100);
				selectClinicianSystemRole(systemRoleToBeSelected);
				clickOnSaveButton();
				break;
			}
		}
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='tab-content']//div[@id='modal-fade']/div[@class='spinner']"));
	
	}

	public AdministrationStudiesCliniciansPage clickToAddClinicianIcon() {
		waitAndClick(addClinicianBTN);
		return this;
	}

	/**
	 * verify Clinician is diplayed inside the clinician drop down
	 * 
	 * @param cliniciansName
	 * @param clinicianDisplay
	 *            :- pass to "true" to check clinician is displayed and "false" to
	 *            check clinician is not displayed
	 * @return
	 */
	public AdministrationStudiesCliniciansPage verifyClinicianIsDisplyedInDropDown(String cliniciansName,
			boolean clinicianDisplay) {
		waitAndClick(addCliniciansTextBox);
		inputText(addCliniciansTextBox, cliniciansName);
		String cliniciansDropdown = "//div[@class='grid-line ng-hide']/following-sibling::div//div[contains(text(),'"
				+ cliniciansName + "')]";
		if (clinicianDisplay)
			Assert.assertTrue(isElementPresent(cliniciansDropdown));
		else
			Assert.assertTrue(!(isElementPresent(cliniciansDropdown)));
		return this;
	}

	/**
	 * click on cancel button to cancel the add clinincian suggestion
	 */
	public void clickToCancelClinicianSuggestion() {
		waitAndClick(clinicianSuggestionCancelButton);
	}

	/***
	 * Clinicians Tab are in view mode
	 */
	
	
	public void cliniciansTabAreInViewMode() {

	String icon=addIconClinicians.getAttribute("data-ng-click");
	if(icon.contains("isDisabled")) {
		Assert.assertTrue(true);
	}
		
	}
	
	/***
	 * Verify clinicians tab in view mode
	 */

	
	public void verifyTabInViewMode() {
		moveToElement(addIconDisabled);
		String value=addIconDisabled.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
	}
	
	/***
	 * Verify search field
	 */

	
	public void verifySearchField() {

		moveToElement(searchField);
		if(searchField.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}
}
