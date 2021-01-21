package net.medavante.portal.pages.administration;

import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import net.medavante.portal.selenium.core.BasePage;

public class AdministrationOrganizationPage extends BasePage {

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeBTN;

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addOrganizationBTN;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//button[@class='btn dropdown-toggle btn-default']")
	private WebElement organizationTypeDRPDWN;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//input[@name='search']")
	private WebElement searchOrgINP;

	@FindBy(xpath = "//span[@class='icon-small icon-delete']")
	private WebElement deleteBTN;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'General')]")
	private WebElement generalTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Addresses')]")
	private WebElement addressesTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'People')]")
	private WebElement peopleTAB;

	@FindBy(xpath = "//a//span[text()='System']")
	private WebElement systemTAB;

	@FindBy(xpath = "(//span[@class='input-group-btn']/button)[1]")
	private WebElement searchIcon;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li")
	private List<WebElement> orgTypeDRPDWN;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> SearchOrgList;

	@FindBy(xpath = "//a//span[text()='Forms Library']")
	private WebElement formsLibraryLNK;

	@FindBy(xpath = "//span[@class='ng-binding' and text()='People']")
	private WebElement peoplePortal;

	@FindBy(xpath = "//span[@class='ng-binding' and text()='Studies']")
	private WebElement studyPortal;

	@FindBy(xpath = "//div[@class='details-grid portal-grid row']")
	private WebElement gridOrganization;

	@FindBy(xpath = "//div[@data-value='model.name']//div[contains(@class,'value')]//input")
	private WebElement orgNameTextBox;

	@FindBy(xpath = "//div[@data-value='model.abbreviation']/div/div[2]/input")
	private WebElement orgAbbreviationTextBox;

	@FindBy(xpath = "//div[@data-value='model.ouTypeId']/div/div[2]")
	private WebElement orgTypeDropdown;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li")
	private List<WebElement> orgDropdownOptions;

	@FindBy(xpath = "//div[@data-value='model.comments']/div/div[2]/textarea")
	private WebElement orgCommentsTextBox;

	@FindBy(xpath = "//a[@title='Save']")
	private WebElement saveIcon;

	@FindBy(xpath = "//div[@data-value='model.name']//div[2]//label")
	private WebElement selectedOrgName;
	

	public AdministrationOrganizationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Function: Click on General Tab
	 * 
	 * @param
	 * @return General Page Object
	 */

	public void createOrganization(String orgName, String studyAbbrevation, String orgType, String studyComments) {
		waitForElementClickable(gridOrganization, 20);
		waitAndClick(addOrganizationBTN);
		waitForElement(saveIcon);
		_normalWait(200);
		inputText(orgNameTextBox, orgName);
		inputText(orgAbbreviationTextBox, studyAbbrevation);
		_normalWait(500);
		waitAndClick(orgTypeDropdown);
		selectDropdownOption(orgDropdownOptions, orgType);
		inputText(orgCommentsTextBox, studyComments);
		waitAndClick(saveIcon);
		waitForSpinner(10);

	}

	public AdministrationOrganizationGeneralPage navigateToOrganizationGeneralTab() {
		waitForElementClickable(generalTAB, 10);
		waitAndClick(generalTAB);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		return PageFactory.initElements(driver, AdministrationOrganizationGeneralPage.class);

	}

	/**
	 * Function: Click on Addresses Tab
	 * 
	 * @param
	 * @return Addresses Page Object
	 */

	public AdministrationOrganizationAddressesPage navigateToOrganizationAddressesTab() {
		_normalWait(10000);
		waitAndClick(addressesTAB);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		return PageFactory.initElements(driver, AdministrationOrganizationAddressesPage.class);

	}

	/**
	 * Function: Click on People Tab
	 * 
	 * @param
	 * @return People Page Object
	 */

	public AdministrationOrganizationPeoplePage navigateToOrganizationPeopleTab() {
		waitForElementClickable(peopleTAB, 20);
		javascriptButtonClick(peopleTAB);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		return PageFactory.initElements(driver, AdministrationOrganizationPeoplePage.class);
	}

	/**
	 * Function: Search Organization
	 * 
	 * 
	 */

	public void organizationSearch(String orgName, String orgType) {
		_normalWait(3000);
		waitAndClick(organizationTypeDRPDWN);
		organizationTypeDRPDWN.findElement(By.xpath("//li//span[contains(text(),'" + orgType + "')]")).click();
		inputText(searchOrgINP, orgName);
		List<WebElement> newSearchOrgLIST = SearchOrgList;
		_normalWait(3000);
		for (WebElement displayOrg : newSearchOrgLIST) {
			waitAndClick(displayOrg);
			waitSpinnerToBecomeInvisible();
			break;
		}
	}

	public void verifySelectedOrgIsDisplayed(String orgName) {
		_normalWait(1000);
		Assert.assertEquals(orgName, selectedOrgName.getText());
	}

	/**
	 * Function: Verify General,address and people tabs are present
	 * 
	 * 
	 */
	public void verifyAllTabsPresent() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath("//*[contains(@data-ng-show,'isBusy') and @class='ng-isolate-scope']//div[@class='spinner']")));
		
		Assert.assertTrue(isElementPresent(generalTAB) && isElementPresent(addressesTAB) && isElementPresent(peopleTAB));
	}

	/**
	 * Function: Navigate to home page
	 * 
	 * 
	 */
	public void navigateBackToDashBoard() {
		waitAndClick(homeBTN);
	}

	public AdministrationPeoplePage navigateToPeople() {
		clickOn(peoplePortal);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		return PageFactory.initElements(driver, AdministrationPeoplePage.class);

	}

	public AdministrationStudiesPage navigateToStudies() {

		_normalWait(4000);
		waitForSpinnerBecomeInvisible(40);
		clickOn(studyPortal);
		waitSpinnerToBecomeInvisible();
		//waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		return PageFactory.initElements(driver, AdministrationStudiesPage.class);

	}

	public AdministrationFormsLibraryPage navigateFormsLibrary() {
		_normalWait(2000);
		waitForSpinnerBecomeInvisible(40);
		clickOn(formsLibraryLNK);
		waitForSpinnerBecomeInvisible(40);
		return PageFactory.initElements(driver, AdministrationFormsLibraryPage.class);
	}

	public AdministrationSystemPage navigateToSystem() {
		_normalWait(1000);
		clickOn(systemTAB);
		spinnerBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationSystemPage.class);

	}

	/** Check Organization Is Present */
	public boolean verifyOrganizationForDataGenreation(String orgNameToBeCreated) {

		boolean flag = true;
		List<String> orgList = new ArrayList<>();
		for (WebElement orgValues : SearchOrgList) {
			orgList.add(getText(orgValues).trim());
		}
		if (orgList.contains(orgNameToBeCreated)) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/* Create Organization */
	public void createOrgnaizationForDataCreation(String orgNameToCreate, String studyAbbrevation, String orgType) {
		boolean value = verifyOrganizationForDataGenreation(orgNameToCreate);
		if (value == true) {
			waitForElementClickable(gridOrganization, 20);
			waitAndClick(addOrganizationBTN);
			waitForElement(saveIcon);
			_normalWait(200);
			inputText(orgNameTextBox, orgNameToCreate);
			inputText(orgAbbreviationTextBox, studyAbbrevation);
			_normalWait(500);
			waitAndClick(orgTypeDropdown);
			selectDropdownOption(orgDropdownOptions, orgType);
			waitAndClick(saveIcon);
			waitForSpinner(10);
		} else {
			Log.info("Organization Present");
		}

	}

	/**
	 * Function: Verify Organization page with Add Organization Icon
	 * 
	 * 
	 */
	
	public void verifyOrganizationPageWithAddOrgButton(){
		
		Assert.assertTrue(isElementPresent(addOrganizationBTN) && isElementPresent(organizationTypeDRPDWN));
	}
	
	/**
     * This method is used to navigte to the tab passed in the parameter
     * @param tabName
     * @param classname
     * @author siddharth
     * @date 17/09/2019
     * @version 1.0
     * @return
     */
    public <T> T navigateToTab(String tabName,Class<T> classname) {
    	String tabLocator="//a[@class='ng-scope']/span[contains(text(),'"+tabName+"')]";
    	WaitForElementPresent(tabLocator, 10);
    	clickOn(tabLocator);
    	return PageFactory.initElements(driver, classname);
    }
	
	
	
}