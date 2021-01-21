package net.medavante.portal.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSitesPage extends BasePage {

	@FindBy(xpath = "//div[@id='dropdownContainer']//span[@class='icon-small icon-add']")
	private WebElement addSiteBTN;

	@FindBy(xpath = "//div[contains(@class,'drop-popup')]//div[@class='popup-scroll']//div[@class='grid-line grid-row ng-scope']//label")
	private List<WebElement> sitesListTXT;

	@FindBy(xpath = "//input[@data-ng-model='investigatorFilter' and @name='search']")
	private WebElement siteSearchINP;

	@FindBy(xpath = "//input[contains(@data-ng-model,'facilityFilter')]")
	private WebElement facilitySearchINP;

	@FindBy(xpath = "//input[contains(@data-ng-model,'peopleFilter')]")
	private WebElement peopleSearchINP;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'siteSearch')]//div[contains(@data-ng-if,'facilities')]")
	private List<WebElement> sitesFacilitiesList;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'siteSearch')]//div/div[@data-ng-show='!isEditing()']/div")
	private List<WebElement> sitePeopleList;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[contains(@data-ng-show,'siteDetails')]")
	private List<WebElement> selectedSiteLinkTXT;
	
	@FindBy(xpath="//div[contains(@data-ng-show,'siteDetails')]//label[@class='orange ng-binding']")
	private List<WebElement> siteLinkText;

	@FindBy(xpath = "//label[@class='ng-binding'and text()='Add Facility']/preceding-sibling::a")
	private WebElement addFacilitySitesSubMenuIcon;

	@FindBy(xpath = "//label[@class='ng-binding'and text()='Add Site']/preceding-sibling::a")
	private WebElement addPeopleSitesSubMenuTab;

	@FindBy(xpath = "//div[contains(@class,'status-block')]//input")
	private WebElement activateSiteCHKBOX;

	@FindBy(xpath = "//div[@class='row row-collapsed-dark']//div[@class='col-xs-2']//input")
	private WebElement siteLocationINP;

	@FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[2]")
	private WebElement peopleSitesTab;
	
	@FindBy(xpath="//div[@class='btn-group sites-tabs']/button[1]")
	private WebElement facilitiesSitesTab;

	@FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[4]")
	private WebElement closeOutSitesTab;

	@FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[3]")
	private WebElement languagesSitesTab;

	@FindBy(xpath = "//div[@class='collapsed-block open-grid']//a[@title='Add Language']")
	private WebElement addLanguageBTN;

	@FindBy(xpath = "//div[@data-model='language']/div[2]/div//a[@class='a-color ng-binding']")
	private List<WebElement> siteLanguageLST;

	@FindBy(xpath = "//div[@class='row grid-row selected']//div[@data-value='person.roleId']")
	private WebElement sitePeopleSystemRoleDRPDOWN;

	@FindBy(xpath = "//div[@class='row grid-row selected']//div[@data-values='roles']//ul/li//span")
	private List<WebElement> sitePeopleSystemRolesLST;

	@FindBy(xpath = "//div[@class='row grid-row selected']/preceding-sibling::div//a[@title='Save']")
	private WebElement peopleSaveBTN;

	@FindBy(xpath = "//div[@class='row row-collapsed-dark']//div/label[@class='orange ng-binding']")
	private WebElement siteUsedAsCredential;

	@FindBy(css = "div[class='row row-collapsed-dark']")
	private WebElement siteRow;

	@FindBy(css = "div[class='row row-collapsed-dark'] div[class='value'] input")
	private WebElement siteNumber;

	@FindBy(xpath = "//div[@class='row grid-row selected']/preceding-sibling::div//a[@title='Remove Person']")
	private WebElement removePersonBtn;

	@FindBy(xpath = "//*[contains(@class,'picker-open')]//td[contains(@class,'today')]")
	private WebElement todayDate;

	@FindBy(xpath = "//div[@class='col-xs-24 grid-row']//a[@class='a-color ng-binding']")
	private List<WebElement> sitePeopleLST;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[text()='Yes']")
	private WebElement confirmationYesBTN;

	@FindBy(xpath = "//label[text()='Deactivate']//parent::div//following-sibling::div//a[contains(@class,'datepickerbutton')]")
	private WebElement deactivateCalender;

	@FindBy(xpath = "//label[text()='Deactivate']//parent::div//following-sibling::div//a[@class='add-on icon-cancel']")
	private WebElement clearDeactivateDate;

	@FindBy(xpath="//div[@class='collapsed-block open-grid']//span[@class='icon-small icon-minus']")
	private WebElement removeBtn;
	

	@FindBy(xpath = "//div[@class='col-xs-24 no-padding']//a[@disabled='disabled']")
	private WebElement addIconSitesTab;
	

	@FindBy(xpath="//div[@class='col-xs-2 btn-arrows text-center']//a[@class='collapsed']")
	private WebElement collapseButton;
	
	@FindBy(xpath="//div[@class='row grid-row selected']//div[@data-label-align='right']//input")
	private WebElement conductAssessment;
	
	@FindBy(xpath="(//div[@class='col-xs-2 btn-arrows text-center'])[1]//a")
	private WebElement sitesDropdownArrow;
	
	@FindBy(xpath="//div[@class='col-xs-24 no-padding']//a[@disabled='disabled']")
	private WebElement addSiteDisableIcon;
	
	@FindBy(xpath="//div[@class='pull-right col-xs-9 search-block']")
	private WebElement searchField;
	
	@FindBy(xpath="//div[@class='row align-center']")
	private WebElement siteDetailsRow;
	
	@FindBy(xpath="//div[@class='btn-group sites-tabs']//button[2]")
	private WebElement peopleTab;
	
	@FindBy(xpath="(//div[@class='row row-collapsed-dark'])[1]//div[@class='col-xs-2 btn-arrows text-center']//a")
	private WebElement sitesDropdown;
	
	@FindBy(xpath="//div[@class='collapsed-block open-grid' and @data-ng-show='!isEditing()']")
	private WebElement sitesRowDisabled;
	
	@FindBy(xpath="//div[@class='row align-center']")
	private WebElement sitePeopleDetailsRow;
	
	@FindBy(xpath="//div[@class='btn-group sites-tabs']//button[3]")
	private WebElement languagesTab;
	
	@FindBy(xpath="//div[@class='collapsed-block open-grid' and @data-ng-show='!isEditing()']")
	private WebElement siteLanguageRowDisabled;
	
	@FindBy(xpath="//div[@class='row align-center']")
	private WebElement siteLanguageDetailsRow;
	
	@FindBy(xpath="//div[@class='btn-group sites-tabs']//button[4]")
	private WebElement closeOutSiteTab;
	
	@FindBy(xpath="//div[@class='col-xs-24 no-padding']//a[@disabled='disabled']")
	private WebElement siteCloseoutTabDisable;
	
	@FindBy(xpath="//div[@class='row grid-row item-row ng-scope selected']")
	private WebElement firstRow;
	
	@FindBy(xpath="//div[@class='tab-view']//ul//li[2]//a")
	private WebElement filesTab;
	
	@FindBy(xpath="//div[@id='warnings']")
	private WebElement warningTab;
	
	@FindBy(xpath="//div[@id='files']")
	private WebElement filesTabBlock;
	
	@FindBy(xpath="//div[@class='tab-view']//ul//li[3]//a")
	private WebElement workflowTab;
	
	public AdministrationStudiesSitesPage(WebDriver driver) {
		super(driver);
	}

	/** Verify Studies Sites Page is displayed **/
	public void verifyAdministrationStudiesSitesPage() {
		Assert.assertTrue(isElementPresent(addSiteBTN));
	}

	/**
	 * Configure Site for Study
	 * 
	 * @param siteName
	 * @param facilityName
	 * @param sitePeople
	 * @param systemRole
	 * @param siteLanguage
	 */
	public void enterSitesData(String siteName, String facilityName, String sitePeople, String systemRole,
			String siteLanguage) {
		_normalWait(100);
		waitAndClick(addSiteBTN);
		_normalWait(2000);
		selectSite(siteName);
		inputText(siteLocationINP, "TestLoc_" + generateRandomString(5));
		clickOn(saveBTN);
		spinnerBecomeInvisible();
		clickOnConfiguredSite(siteName);
		clickOn(addFacilitySitesSubMenuIcon);
		selectSiteFacility(facilityName);
		waitAndClick(activateSiteCHKBOX);
		waitAndClick(saveBTN);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-xs-2 btn-arrows text-center']//a[@class='collapsed']")));
		javascriptButtonClick(peopleSitesTab);
		clickOnConfiguredSite(siteName);

		waitAndClick(addPeopleSitesSubMenuTab);
		selectSitePeople(sitePeople);
		selectSitePeopleRoleAccess(systemRole);
		waitAndClick(peopleSaveBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"));
		waitAndClick(languagesSitesTab);
		selectSitelanguage(siteName, siteLanguage);
		waitAndClick(closeOutSitesTab);
	}

	/**
	 * Select Site from the drop down options
	 * 
	 * @param siteNameToBeSelected
	 **/
	public void selectSite(String siteNameToBeSelected) {
		inputText(siteSearchINP, siteNameToBeSelected);
		for (WebElement siteName : sitesListTXT) {
			waitForElement(siteName);
			if (getText(siteName).trim().equalsIgnoreCase(siteNameToBeSelected)) {
				waitAndClick(siteName);
				break;
			}
		}

	}

	/**
	 * click On Configured Site
	 * 
	 * @param siteToBeClicked
	 **/
	public void clickOnConfiguredSite(String siteToBeClicked) {
		_normalWait(1500);
		for (WebElement siteName : selectedSiteLinkTXT) {
			waitForElement(siteName);
			if (getText(siteName.findElement(By.xpath("(.//label[@class='orange ng-binding'])")))
					.equalsIgnoreCase(siteToBeClicked)) {
				_normalWait(400);
				waitAndClick(siteName.findElement(
						By.xpath(".//label[@class='orange ng-binding' and text()='" + siteToBeClicked + "']")));
				break;
			}
		}
	}

	/**
	 * Select facility from the drop down options
	 * 
	 * @param facilityNameToBeSelected
	 **/
	public void selectSiteFacility(String facilityNameToBeSelected) {
		inputText(facilitySearchINP, facilityNameToBeSelected);
		for (WebElement facilityName : sitesFacilitiesList) {
			waitForElement(facilityName);
			if (getText(facilityName).equalsIgnoreCase(facilityNameToBeSelected)) {
				clickOn(facilityName.findElement(By.xpath("./parent::label/parent::div")));

				break;
			}

		}
	}

	/**
	 * Select people from the drop down options
	 * 
	 * @param peopleNameToBeSelected
	 **/
	public void selectSitePeople(String peopleNameToBeSelected) {
		boolean flag=false;
		inputText(peopleSearchINP, peopleNameToBeSelected);
		clickOn(collapseButton);
		waitForSpinnerBecomeInvisible(10);
		for (WebElement peopleName : sitePeopleList) {			
			if (peopleName.getText().trim().contains(peopleNameToBeSelected)) {
				moveToElement(peopleName);
				scrollIntoView(peopleName.findElement(By.xpath(".//label/a")));
				javascriptButtonClick(peopleName.findElement(By.xpath(".//label/a")));
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select people system role from the drop down options
	 * 
	 * @param systemRoleToBeSelected
	 **/
	public void selectSitePeopleRoleAccess(String systemRoleToBeSelected) {
		
		waitAndClick(sitePeopleSystemRoleDRPDOWN);
		for (WebElement systemRole : sitePeopleSystemRolesLST) {
			_normalWait(200);
			waitForElement(systemRole);
			if (systemRole.getText().trim().equalsIgnoreCase(systemRoleToBeSelected)) {
				clickOn(systemRole.findElement(By.xpath("./parent::li")));
				break;
			}

		}
	}

	/**
	 * Click on drop down Icon to expand the configured site details
	 * 
	 * @param configuredSiteName
	 **/
	public void clickOnSiteDropDownIcon(String configuredSiteName) {
		for (WebElement configuredSiteDropDownIcon : siteLinkText) {
			waitForElement(configuredSiteDropDownIcon);
			String siteText=getText(configuredSiteDropDownIcon);
			if (siteText.equalsIgnoreCase(configuredSiteName)) {
				waitAndClick(configuredSiteDropDownIcon
						.findElement(By.xpath(".//ancestor::div[2]//preceding-sibling::div/a")));
				break;
			}
		}
	}

	/**
	 * Select site language from options
	 * 
	 * @param systemRoleToBeSelected
	 **/
	public void selectSitelanguage(String siteName, String languageToBeSelected) {
		clickOnSiteDropDownIcon(siteName);
		for (WebElement language : siteLanguageLST) {
			if (getText(language).trim().equalsIgnoreCase(languageToBeSelected)) {
				waitAndClick(language);
				waitAndClick(addLanguageBTN);
				spinnerBecomeInvisible();
				break;
			}
		}
	}

	/**
	 * @return
	 * @function: Returned the site user name
	 * 
	 */
	public String siteCredentialUsedToLoginForSubjectManagement() {

		return getText(siteUsedAsCredential);

	}

	public void changeSitesNumber(String siteName, String siteNumberToBeEntered) {
		waitAndClick(siteRow.findElement(By.xpath("./div//label[contains(text(),'" + siteName + "')]")));
		inputText(siteNumber, siteNumberToBeEntered);
		clickOn(saveBTN);
		spinnerBecomeInvisible();
	}

	/* Deactivate the rater */
	public void deactivateTheRater(String raterName, String siteName) {
		clickOnConfiguredSite(siteName);
		javascriptButtonClick(peopleSitesTab);
		clickOnSiteCollapsedButton(siteName);
		for (WebElement peopleName : sitePeopleLST) {
			waitForElement(peopleName);
			if (getText(peopleName).equalsIgnoreCase(raterName)) {
				_normalWait(400);
				scrollPageThroughWebElement(peopleName);
				clickOn(peopleName);
				clickOn(deactivateCalender);
				clickOn(todayDate);
				waitAndClick(peopleSaveBTN);
				break;
			}
		}

	}

	/* Add Rater Under Site */
	public void addPeopleRater(String siteName, String peopleToBeAdded) {

		for (WebElement peopleName : sitePeopleLST) {
			waitForElement(peopleName);
			if (getText(peopleName).equalsIgnoreCase(peopleToBeAdded)) {
				_normalWait(400);
				scrollPageThroughWebElement(peopleName);
				clickOn(peopleName);
				waitForElementClickable(clearDeactivateDate, DEFAULT_WAIT_4_PAGE);
				clickOn(clearDeactivateDate);
				waitAndClick(peopleSaveBTN);
				break;
			}
		}
	}

	/* Click On Site Collpased Icon */

	public void clickOnSiteCollapsedButton(String siteName) {
		WebElement collapsedElement = driver.findElement(By.xpath(
				"//div[contains(@class,'collapsed-dark')]//label[contains(@class,'ng-binding') and text()='" + siteName
						+ "']//parent::div//parent::div//preceding-sibling::div[contains(@class,'btn-arrows')]//a"));
		clickOn(collapsedElement);
	}

	public void switchTabs(String Tabname) {

		WebElement Tabnam = driver.findElement(
				ByLocator("//div[@class='btn-group sites-tabs']//button[contains(text(),'" + Tabname + "')]"));
		Tabnam.click();

	}
	
/**
 * @param adding New Site
 * @param siteName
 * @param facilityName
 * @param sitePeople
 * @param systemRole
 * @param siteLanguage
 */

	public void AddNewSite(String siteName, String facilityName, String sitePeople, String systemRole,
			String siteLanguage) {
		waitAndClick(addSiteBTN);
		_normalWait(2000);
		WebElement selSite = driver.findElement(ByLocator("//label[contains(text(),'"+siteName+"')]/.."));
		waitForElement(selSite);
		moveToElement(selSite);
		waitAndClick(selSite);
		inputText(siteLocationINP, "TestLoc_" + generateRandomString(5));
		waitAndClick(saveBTN);
		spinnerBecomeInvisible();
		_normalWait(3000);
		verifySiteDisplayed(siteName);
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='tab-content']//div[@class='spinner']"));
		selectesiteToconfigured(siteName);
		addFacilityPeople(siteName);
		WebElement selFacilty = driver.findElement(ByLocator("//label[contains(text(),'" + facilityName + "')]"));
		clickOn(selFacilty);
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-xs-2 btn-arrows text-center']//a[@class='collapsed']")));
		_normalWait(3000);
		waitAndClick(languagesSitesTab);
		_normalWait(3000);
		selectSitelanguage(siteName, siteLanguage);
        try {
        	if (closeOutSitesTab.isDisplayed()) {
        		waitAndClick(closeOutSitesTab);
			}
			
		} catch (Exception e) {
		}
        _normalWait(1000);
		javascriptButtonClick(peopleSitesTab);
		spinnerBecomeInvisible();
		_normalWait(3000);
		selectesiteToconfigured(siteName);
		addFacilityPeople(siteName);
		enterPeopleToBeSelected(siteName, sitePeople);
		selectSitePeopleRoleAccess(systemRole);
		waitAndClick(peopleSaveBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"));
		reportInfo();

		
	}

	public void verifySiteDisplayed(String siteName)
	{
		boolean flag=true;
		try {
		WebElement site = driver.findElement(ByLocator("//label[@class='orange ng-binding'][text()='"+siteName+"']"));
		if (site.isDisplayed()) {
			moveToElement(site);
			flag=true;
		}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	@FindBy(xpath="(//label[text()='Add People'])[2]/..//div[@class='grid-line grid-row ng-scope']")
	private List<WebElement> roleList;
	/**
	 * Select people from the drop down options
	 * 
	 * @param peopleNameToBeSelected
	 **/

	public void enterPeopleToBeSelected(String siteName, String peopleToBeSelected) {

		WebElement enter = driver.findElement(ByLocator(
				"//label[contains(text(),'" + siteName + "')]/../../../preceding-sibling::div[@class='row']//input"));
		inputText(enter, peopleToBeSelected);
		for (WebElement peopleName : roleList) {
			if (getText(peopleName).equalsIgnoreCase(peopleToBeSelected)) {
				moveToElement(peopleName);
				clickOn(peopleName.findElement(By.xpath("./parent::div")));
				break;
			}

		}

	}

	public void addFacilityPeople(String siteName) {

		WebElement ADD = driver.findElement(ByLocator("//label[contains(text(),'" + siteName
				+ "')]/../../../preceding-sibling::div[@class='row']//div[@class='inline ng-scope']//span[@class='icon-small icon-add']"));
		waitAndClick(ADD);

	}

	

	public void selectesiteToconfigured(String siteName) {

		List<WebElement> confstnameName = driver.findElements(
				ByLocator("//div[contains(@data-ng-show,'siteDetails')]//label[@class='orange ng-binding']"));
		boolean flag = false;
		for (WebElement we : confstnameName) {
			if (we.getText().equalsIgnoreCase(siteName)) {
				waitForElementClickable(we, 6);
				waitAndClick(we);
				flag = true;

			}
		}
     Assert.assertTrue(flag);
     reportInfo();
	}
	
	
	/****
	 * 
	 * 
	 * 
	 * @param Removing Site
	 * @param className
	 * @param siteName
	 * @param confirmation
	 * @param sitePeople
	 * @return
	 */
	public <T> T removeSite(Class<T> className,String siteName,String confirmation,String sitePeople)
	{
		waitAndClick(languagesSitesTab);
		clickOnSiteCollapsedButton(siteName);
		String selLanguag="//label[contains(text(),'"+siteName+"')]//ancestor::div[4]/following-sibling::div//div[@class='collapsed-block open-grid']//a";
		waitAndClick(selLanguag);
		waitAndClick(removeBtn);
		WebElement cnfrmtn = driver.findElement(ByLocator("//div[@id='queryConfirmation']//div[@class='btn btn-active'][contains(text(),'"+confirmation+"')]"));
		waitAndClick(cnfrmtn);
		spinnerBecomeInvisible();
		try {
        	if (closeOutSitesTab.isDisplayed()) {
        		waitAndClick(closeOutSitesTab);
			}
			
		} catch (Exception e) {
		}
        _normalWait(1000);
		waitForElementClickable(peopleSitesTab, 5);
		javascriptButtonClick(peopleSitesTab);
//		selectesiteToconfigured(siteName);
		WebElement selpersonarrow = driver.findElement(ByLocator("//label[contains(text(),'"+siteName+"')]//ancestor::div[@class='row row-collapsed-dark']/div/a"));
		waitForElementClickable(selpersonarrow, 5);
		waitAndClick(selpersonarrow);
		WebElement selPerson=driver.findElement(ByLocator("//div[@class='collapsed-block open-grid']//a[contains(text(),'"+sitePeople+"')]"));
		waitAndClick(selPerson);
		waitAndClick(selPerson.findElement(By.xpath(".//ancestor::div[3]/preceding-sibling::div//span[@class='icon-small icon-minus']")));
		waitAndClick(cnfrmtn);
		spinnerBecomeInvisible();
		waitAndClick(languagesSitesTab);
		waitAndClick(facilitiesSitesTab);
		selectesiteToconfigured(siteName);
		WebElement removesitebtn = driver.findElement(ByLocator("//label[contains(text(),'"+siteName+"')]//ancestor::div[3]/preceding-sibling::div//span[@class='icon-small icon-delete']"));
		waitForElementClickable(removesitebtn, 5);
		waitAndClick(removesitebtn);
		waitAndClick(cnfrmtn);
		spinnerBecomeInvisible();
		refreshPage();
		reportInfo();
		
		return PageFactory.initElements(driver, className);
		 
		
		
	}
	
	
	public void clickOnCollpasedIcon(){
		
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-xs-2 btn-arrows text-center']//a[@class='collapsed']")));
		waitForSpinnerBecomeInvisible(20);
		
	}
	
	public void clickOnPeopleSitesTab(){
		
		javascriptButtonClick(peopleSitesTab);
		spinnerBecomeInvisible();
		
		
	}
	
	/*Verify conduct assessment checkbox checked*/

	public void verifyConductAssessmentsOptionIsSelected() {
		
		moveToElement(conductAssessment);
		Assert.assertTrue(conductAssessment.isSelected(), "Check box selected");
		
	}

	/***
	 * Site Tab are in view mode
	 */
	
	
	public void siteTabAreInViewMode() {

		String icon=addIconSitesTab.getAttribute("data-ng-click");
		if(icon.contains("Disabled")) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Click on dropdown on the sites row
	 */
	
	public void clickOnDropdownOnTheSiteRow() {
		
		waitAndClick(sitesDropdownArrow);
	}

	/***
	 * Sites facilities tab displayed in view mode
	 */
	
	public void siteFacilitiesTavDisplayedInViewMode() {

		moveToElement(addSiteDisableIcon);
		String value=addSiteDisableIcon.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Verify Search field
	 */
	public void verifySearchField() {
		moveToElement(searchField);
		if(searchField.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}

	/***
	 *  verify Site facility details row displayed
	 */
	
	public void siteFacilitiesDetailsRowDisplayed() {

		moveToElement(siteDetailsRow);
		if(siteDetailsRow.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * open people sites tab
	 */
	
	public void openPeopleSitesTab() {
		waitAndClick(peopleTab);
	}

	/***
	 * Click the dropdown of the sites row tab of people tab
	 */
	
	public void clickTheDropdownOfTheSitesRow() {

		waitAndClick(sitesDropdown);
		waitForSpinnerBecomeInvisible(3000);
		
		
	}

	/***
	 * Sites people tab displayed in view mode
	 */
	
	public void sitesPeopleTabDisplayedInViewMode() {

		moveToElement(sitesRowDisabled);
		String value=sitesRowDisabled.getAttribute("data-ng-show");
		if(value.contains("!Editing")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Verify site people details row displayed
	 */
	
	public void sitePeopleDetailsRowDisplayed() {

		moveToElement(sitePeopleDetailsRow);
		if(sitePeopleDetailsRow.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}
	
	/***
	 * Open languages sites tab
	 */

	
	public void openLanguagesSitesTab() {
		waitAndClick(languagesSitesTab);
	}

	/***
	 * Site language tab displayed in view mode
	 */
	
	public void siteLanguagesTabDisplayedInAViewMode() {

		moveToElement(siteLanguageRowDisabled);
		String value=siteLanguageRowDisabled.getAttribute("data-ng-show");
		if(value.contains("!Editing")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 *  verify Site language details row displayed
	 */
	
	
	public void siteLanguageDetailsRowDisplayed() {

		moveToElement(siteLanguageDetailsRow);
		if(siteLanguageDetailsRow.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
		
	}

	/***
	 * Open close out sites tab
	 */
	
	public void openCloseOutSitesTab() {

		waitAndClick(closeOutSiteTab);
		
	}

	/***
	 * Verify site close out tab displayed in view mode
	 */
	
	
	public void siteCloseOutTabTabDisplayedInAViewMode() {

		moveToElement(siteCloseoutTabDisable);
		String value=siteCloseoutTabDisable.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * First row is grayed out
	 */
	
	public void firstRowIsGrayedOut() {

		String color=firstRow.getCssValue("background-color");
		Assert.assertTrue(true, color);
	}

	/***
	 * Verify warning tab displayed in the close out details block
	 */
	
	public void warningTabDisplayedInTheCloseoutDetailsBlock() {

		moveToElement(warningTab);
		if(warningTab.isDisplayed()) {
			Assert.assertTrue(true);
		}

		
	}

	/***
	 * Open files tab
	 */
	
	public void openFilesTab() {

		waitAndClick(filesTab);
	}

	/***
	 * Files tab displayed in close out tab
	 */
	
	public void filesTabDisplayedInTheCloseoutTab() {
		moveToElement(filesTabBlock);
		if(filesTabBlock.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Open workflow tab
	 */
	
	
	public void openWorkflowTab() {

		waitAndClick(workflowTab);
		
	}

	/***
	 * workflow tab is displayed close out block
	 */
	@FindBy(xpath="//div[@id='workflow']")
	private WebElement workflowTabBlock;
	public void workflowTabDisplayedInTheCloseoutBlock() {

		moveToElement(workflowTabBlock);
		if(workflowTabBlock.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}

	
}
