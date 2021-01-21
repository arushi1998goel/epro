package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	@FindBy(xpath = "//div[@data-value='clinician.roleId']//ul[@class='dropdown-menu']//span")
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
	
	
	
	public void clickOnSaveButton() {
		waitForElement(saveBTN);
		clickOn(saveBTN);
	}
	
	
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
	}
}
