package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSitesPage extends BasePage {

	@FindBy(xpath = "//div[@class='row grid-header']//span[@class='icon-small icon-add']")
	private WebElement addSiteBTN;

	@FindBy(xpath = "//div[contains(@class,'drop-popup')]//div[@class='popup-scroll']//div[@class='grid-line grid-row ng-scope']//label")
	private List<WebElement> sitesListTXT;

	@FindBy(xpath = "//input[@data-ng-model='investigatorFilter' and @name='search']")
	private WebElement siteSearchINP;

	@FindBy(xpath = "//input[contains(@data-ng-model,'facilityFilter')]")
	private WebElement facilitySearchINP;

	@FindBy(xpath = "//input[contains(@data-ng-model,'peopleItemsFilter')]")
	private WebElement peopleSearchINP;

	@FindBy(xpath = "//div[@class='dropdown-menu drop-popup']//div[contains(@data-ng-repeat,'facilitiesItems')]//label[@class='ng-binding']")
	private List<WebElement> sitesFacilitiesList;

	@FindBy(xpath = "//div[@class='dropdown-menu drop-popup']//div[contains(@data-ng-repeat,'peopleItems')]//label")
	private List<WebElement> sitePeopleList;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[contains(@data-ng-show,'siteDetails')]")
	private List<WebElement> selectedSiteLinkTXT;

	@FindBy(xpath = "//label[@class='ng-binding'and text()='Add Facility']/preceding-sibling::a")
	private WebElement addFacilitySitesSubMenuIcon;

	@FindBy(xpath = "//label[@class='ng-binding'and text()='Add People']/preceding-sibling::a")
	private WebElement addPeopleSitesSubMenuTab;

	@FindBy(xpath = "//div[contains(@class,'status-block')]//input")
	private WebElement activateSiteCHKBOX;

	@FindBy(xpath = "//div[@class='row row-collapsed-dark']//div[@class='col-xs-2']//input")
	private WebElement siteLocationINP;

	@FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[2]")
	private WebElement peopleSitesTab;

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
		clickOn(addSiteBTN);
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
			if (getText(siteName).equalsIgnoreCase(siteNameToBeSelected)) {
				clickOn(siteName.findElement(By.xpath("./parent::div")));
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
			if (getText(siteName.findElement(By.xpath(".//label[@class='orange ng-binding']")))
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
		inputText(peopleSearchINP, peopleNameToBeSelected);
		for (WebElement peopleName : sitePeopleList) {
			waitForElement(peopleName);
			if (getText(peopleName).equalsIgnoreCase(peopleNameToBeSelected)) {
				clickOn(peopleName.findElement(By.xpath("./parent::div")));
				break;
			}

		}
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
		for (WebElement configuredSiteDropDownIcon : selectedSiteLinkTXT) {
			waitForElement(configuredSiteDropDownIcon);
			if (getText(configuredSiteDropDownIcon.findElement(By.xpath(".//label[@class='orange ng-binding']")))
					.equalsIgnoreCase(configuredSiteName)) {
				waitAndClick(configuredSiteDropDownIcon
						.findElement(By.xpath(".//label[@class='orange ng-binding' and text()='" + configuredSiteName
								+ "']/parent::div/parent::div/preceding-sibling::div/a")));
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
}
