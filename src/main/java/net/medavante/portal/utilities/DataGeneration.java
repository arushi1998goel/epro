package net.medavante.portal.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.medavante.portal.pages.administration.AdministrationOrganizationAddressesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCountriesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.selenium.core.Configuration;

public class DataGeneration {

	private static WebDriver driver;
	private static String machineForRun, userName, passWord, setEnvironment, applicationUrl, orgNameForDataCreation,
			abbrevationOrg, abbrevationStudy, orgType, studyPhase, studyLanguage, siteCount, studyCountry;

	/* Run On Test/Staging/QA Environment */

	public static void runOnEnvironement() throws Exception {
		machineForRun = Configuration.readApplicationFile("RunOnLocalMachine");
		setEnvironment = Configuration.readApplicationFile("SetEnvironment");
		if (machineForRun.equals("true")) {
			if (setEnvironment.equals("stg")) {
				applicationUrl = Configuration.readApplicationFile("siteStgURL");

			} else if (setEnvironment.equals("test")) {
				applicationUrl = Configuration.readApplicationFile("siteTestURL");
			}

			else if (setEnvironment.equals("qa")) {
				applicationUrl = Configuration.readApplicationFile("siteQaURL");
			}
		} else {
			applicationUrl = System.getProperty("environmentUrl");
			applicationUrl = Configuration.readApplicationFile(applicationUrl);
		}
	}

	/* Login Application */

	public static void logInApplication() throws Exception {
		/* Reading data from test data File */
		Properties properties = Configuration.readTestData("Study");
		userName = properties.getProperty("SuperUser");
		passWord = properties.getProperty("SuperPw");
		driver.findElement(By.id("WebLogin_UserName")).sendKeys(userName);
		driver.findElement(By.id("Password")).sendKeys(passWord);
		driver.findElement(By.id("WebLogin_Login")).click();
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
	}

	public static void logOut() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0 )");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@title='Log out']")).click();
	}

	/* Navigate To Administration */

	public static void clickToAdministration() {
		driver.findElement(By.xpath("//*[@data-ng-href='/admin']")).click();
	}

	/* Navigate To Studies */

	public static void clickToStudies() {
		driver.findElement(By.xpath("//span[@class='ng-binding' and text()='Studies']")).click();
	}

	/** Click on WebElement by using java script */
	public static void javascriptButtonClick(WebElement webElement) {

		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("webElement")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
	}

	/* Navigate To Languages */
	public static void clickToLanguagesTab() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver
				.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'Languages')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Function: Navigate to countries tab of study.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public static void clickToStudyCountriesTab() throws InterruptedException {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
		WebElement element = driver
				.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'Countries')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Function: Navigate to sites tab of study.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public static void clickToStudySitesTab() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
		WebElement element = driver.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'Sites')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Function: Navigate to People tab of study.
	 * 
	 */
	public static void clickToStudyPeopleTab() {
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
		driver.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'People')]")).click();
	}

	/** Select country language **/
	public static void selectCountryLanguageForStudy(String languageToBeSelected) {
		for (WebElement countryLang : driver
				.findElements(By.xpath("//div[@class='property-value-edit checkbox-list']/div"))) {
			if (countryLang.findElement(By.xpath("//div[@class='property-value-edit checkbox-list']/div//span"))
					.getText().equalsIgnoreCase(languageToBeSelected)) {
				WebElement element = countryLang
						.findElement(By.xpath("//div[@class='property-value-edit checkbox-list']/div//input"));
				Actions actions = new Actions(driver).doubleClick(element);
				actions.build().perform();

			}
		}
	}

	/** Select country from the countries option **/
	public static void selectCountry(String countryName, String countryCode, String countryLanguage) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
		driver.findElement(By.xpath("//a[@data-ng-click='getAvailableCountries()']/span")).click();
		String country = "//label[@class='ng-binding' and text()='" + countryName + "']";
		driver.findElement(By.xpath(country)).click();
		driver.findElement(By.xpath("//div[@data-value='country.targetSites']/div/div/input")).sendKeys(countryCode);
		selectCountryLanguageForStudy(countryLanguage);
		driver.findElement(By.xpath("//span[@class='icon-small icon-save']")).click();
	}

	/* Select Languages */
	public static void selectLanguageForStudy(String languageName) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		String language = "//label[@class='ng-binding' and text()='" + languageName + "']/preceding-sibling::input";
		driver.findElement(By.xpath(language)).click();
		driver.findElement(By.xpath("//span[@class='icon-small icon-save']")).click();
	}

	/** Check Organization Is Present */
	public static boolean verifyOrganizationForDataGenreation(String orgNameToBeCreated) {

		boolean flag = true;
		List<String> orgList = new ArrayList<>();
		List<WebElement> SearchOrgList = driver.findElements(By.xpath(
				"//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']"));
		for (WebElement orgValues : SearchOrgList) {
			orgList.add(orgValues.getText().trim());
		}
		if (orgList.contains(orgNameToBeCreated)) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/* Create Organization */
	public static void createOrgnaizationForDataCreation(String orgNameToCreate, String studyAbbrevation,
			String orgType) throws InterruptedException {
		boolean value = verifyOrganizationForDataGenreation(orgNameToCreate);
		if (value == true) {
			new WebDriverWait(driver, 15).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='details-grid portal-grid row']")));
			driver.findElement(By.xpath("//a[@class='btn circle-button btn-white' and @title='Add']")).click();
			new WebDriverWait(driver, 15)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Save']")));
			driver.findElement(By.xpath("//div[@data-value='model.name']//div[contains(@class,'value')]//input"))
					.sendKeys(orgNameToCreate);
			driver.findElement(By.xpath("//div[@data-value='model.abbreviation']/div/div[2]/input"))
					.sendKeys(studyAbbrevation);
			driver.findElement(By.xpath("//div[@data-value='model.ouTypeId']/div/div[2]")).click();
			for (WebElement e : driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"))) {
				if (e.getText().equalsIgnoreCase(orgType)) {
					e.click();
					break;
				}
			}

			driver.findElement(By.xpath("//a[@title='Save']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'Addresses')]")).click();
			;
			new WebDriverWait(driver, 15).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
			driver.findElement(By.xpath("//a[@class='btn circle-button btn-white' and @title='Add Address']")).click();

			new WebDriverWait(driver, 15).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@class='col-xs-24 address-wrapper selected']")));

			driver.findElement(By.xpath("(//div[@data-value='geoAddress.address']//textarea)[1]"))
					.sendKeys(StudyGenerationData.Organization_Address);
			driver.findElement(By.xpath("(//*[@data-value='geoAddress.city']//input)[1]"))
					.sendKeys(StudyGenerationData.Organization_City);
			driver.findElement(By
					.xpath("(//*[@data-value='geoAddress.countryId']//button[contains(@class,'btn dropdown-toggle btn-default')])[1]"))
					.click();
			for (WebElement element : driver
					.findElements(By.xpath("//*[contains(@class,'ng-isolate-scope open')]//ul//li//span"))) {
				if (element.getText().trim().equalsIgnoreCase(StudyGenerationData.Site_CountryName)) {
					element.findElement(By.xpath(".//parent::li")).click();
					break;
				}
			}
			new WebDriverWait(driver, 15)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Timezone Look-up'])[1]")));
			if (driver.findElement(By.xpath("(//a[text()='Timezone Look-up'])[1]")).isEnabled()) {
				driver.findElement(By.xpath("(//a[text()='Timezone Look-up'])[1]")).click();
			}
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//*[@data-text-id='Models.contactInformation.primary']//input"))
					.isEnabled()) {
				driver.findElement(By.xpath("//*[@data-text-id='Models.contactInformation.primary']//input")).click();
			}
			driver.findElement(By.xpath("(//label[text()='Save'])[1]")).click();

			new WebDriverWait(driver, 15).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));

			driver.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[contains(text(),'People')]")).click();
			new WebDriverWait(driver, 15).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
			selectMutiplePeopleForOrganization();
		} else {
			Log.info("Organization Present");
		}

	}

	/* Configure Multiple People On Organization */

	public static void selectMutiplePeopleForOrganization() throws InterruptedException

	{
		Thread.sleep(3000);
		for (int peopleVar = 0; peopleVar < StudyGenerationData.Site_People_Name.size(); peopleVar++) {
			driver.findElement(By.xpath("//label[text()='Link Person']//preceding-sibling::a")).click();
			String element = StudyGenerationData.Site_People_Name.get(peopleVar);
			for (WebElement peopleListElement : driver
					.findElements(By.xpath("//*[@class='popup-scroll']//label[@class='ng-binding']"))) {
				if (peopleListElement.getText().trim().equalsIgnoreCase(element)) {
					peopleListElement.click();
					new WebDriverWait(driver, 15)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[text()='Save'])[1]")));
					driver.findElement(By.xpath("(//label[text()='Save'])[1]")).click();
					Thread.sleep(3000);
					break;
				}
			}
		}
	}

	/**
	 * Check Study Is Present For Data Creation
	 * 
	 * @throws InterruptedException
	 */

	public static void checkStudyIsPresentAndCreateStudyForDataGeneration(List<String> studyNameValues,
			String abbreviation, String studyPhase, String studySponsor) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(1500);
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='details-grid portal-grid row']//label[text()='Sponsor']")));
		List<String> studyList = new ArrayList<>();
		for (WebElement studyValues : driver.findElements(
				By.xpath("//*[contains(@data-ng-repeat,'item in')]//span[@class='item-name ng-binding']"))) {
			String[] studyNames = studyValues.getText().trim().split("\\s+", 2);
			String studyPresentInList = studyNames[1];
			studyList.add(studyPresentInList);
		}

		Iterator<String> iterator = studyNameValues.iterator();
		while (iterator.hasNext()) {
			String studyNeeded = iterator.next();
			if (studyList.contains(studyNeeded)) {
				flag = false;
			} else {
				flag = true;
				new WebDriverWait(driver, 15).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[@class='btn circle-button btn-white' and @title='Add']")));
				driver.findElement(By.xpath("//a[@class='btn circle-button btn-white' and @title='Add']")).click();
				driver.findElement(By.xpath("//div[@data-text-id='Models.studies.name']/div/div[2]/input"))
						.sendKeys(studyNeeded);
				driver.findElement(By.xpath("//div[@data-text-id='Models.studies.abbreviation']/div/div[2]/input"))
						.sendKeys(abbreviation);
				driver.findElement(By.xpath("//div[@data-text-id='Models.studies.phase']/div/div[2]/input"))
						.sendKeys(studyPhase);
				driver.findElement(
						By.xpath("//div[@data-text-id='Models.studies.sponsor']//button[@data-toggle='dropdown']"))
						.click();
				for (WebElement e : driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li/span"))) {
					if (e.getText().equalsIgnoreCase(studySponsor)) {
						e.click();
						break;
					}
				}

				driver.findElement(By.xpath("//input[contains(@ng-model,'SiteRating')]")).click();
				driver.findElement(By.xpath("//input[contains(@ng-model,'IndependentRating')]")).click();
				driver.findElement(By.xpath("//input[contains(@ng-model,'IndependentReview')]")).click();
				driver.findElement(By.xpath("//input[contains(@ng-model,'Analytics')]")).click();
				driver.findElement(By.xpath("//input[contains(@ng-model,'isMobileProSelected')]")).click();
				driver.findElement(By.xpath("//input[contains(@ng-model,'isObserverSelected')]")).click();
				driver.findElement(By.xpath("//label[text()='Save']")).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
				clickToLanguagesTab();
				selectLanguageForStudy(StudyGenerationData.Language);
				clickToStudyCountriesTab();
				selectCountry(StudyGenerationData.Site_CountryName, StudyGenerationData.Site_Count,
						StudyGenerationData.Language);
				clickToStudySitesTab();
				configureSitesData(StudyGenerationData.Site_Name_List.get(9), StudyGenerationData.Facilities_Name_List.get(0),
						StudyGenerationData.Language);
				clickToStudyPeopleTab();
				selectMutilplePeopleForStudy("Activate");

			}
		}

	}

	/* Data Generation of Organization and Study */

	public static void createOrganizationAndStudyDataGeneration() throws Exception {

		/* Reading data from test data File */
		Properties properties = Configuration.readTestData("Study");
		orgNameForDataCreation = properties.getProperty("sponsor");
		abbrevationOrg = properties.getProperty("AutomationAbbrevationOrg");
		abbrevationStudy = properties.getProperty("AutomationAbbrevationStudy");
		orgType = properties.getProperty("AutomationOrgType");
		studyPhase = properties.getProperty("AutomationStudyPhase");
		studyLanguage = properties.getProperty("AutomationStudyLanguage");
		siteCount = properties.getProperty("AutomationSiteCount");
		studyCountry = properties.getProperty("StudyCountry");

		runOnEnvironement();
		System.setProperty("webdriver.chrome.driver", ".//src//test//resources//webdriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);

		/**
		 * Maximize window
		 */
		driver.manage().window().maximize();

		/**
		 * Delete cookies and set timeout
		 */
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/**
		 * Open application URL
		 */
		driver.navigate().to(applicationUrl);
		logInApplication();
		clickToAdministration();
		createOrgnaizationForDataCreation(orgNameForDataCreation, abbrevationOrg, orgType);
		clickToStudies();
		checkStudyIsPresentAndCreateStudyForDataGeneration(StudyGenerationData.Study_List, abbrevationStudy, studyPhase,
				orgNameForDataCreation);
		logOut();
	}

	public static void main(String[] args) throws Exception {
		createOrganizationAndStudyDataGeneration();
	}

	/**
	 * Select Site from the drop down options
	 * 
	 * @param siteNameToBeSelected
	 **/
	public static void selectSite(String siteNameToBeSelected) {
		driver.findElement(By.xpath("//input[@data-ng-model='investigatorFilter' and @name='search']"))
				.sendKeys(siteNameToBeSelected);
		for (WebElement siteName : driver.findElements(By.xpath(
				"//div[contains(@class,'drop-popup')]//div[@class='popup-scroll']//div[@class='grid-line grid-row ng-scope']//label"))) {
			if (siteName.getText().equalsIgnoreCase(siteNameToBeSelected)) {
				siteName.findElement(By.xpath("./parent::div")).click();
				break;
			}
		}

	}

	/**
	 * click On Configured Site
	 * 
	 * @param siteToBeClicked
	 * @throws InterruptedException
	 **/
	public static void clickOnConfiguredSite(String siteToBeClicked) throws InterruptedException {
		Thread.sleep(1500);
		for (WebElement siteName : driver.findElements(By.xpath("//div[contains(@data-ng-show,'siteDetails')]"))) {
			if (siteName.findElement(By.xpath(".//label[@class='orange ng-binding']")).getText()
					.equalsIgnoreCase(siteToBeClicked)) {
				Thread.sleep(400);
				siteName.findElement(
						By.xpath(".//label[@class='orange ng-binding' and text()='" + siteToBeClicked + "']")).click();
				break;
			}
		}
	}

	/**
	 * Select facility from the drop down options
	 * 
	 * @param facilityNameToBeSelected
	 **/
	public static void selectSiteFacility(String facilityNameToBeSelected) {
		driver.findElement(By.xpath("//input[contains(@data-ng-model,'facilityFilter')]"))
				.sendKeys(facilityNameToBeSelected);
		for (WebElement facilityName : driver.findElements(By.xpath(
				"//div[@class='dropdown-menu drop-popup']//div[contains(@data-ng-repeat,'facilitiesItems')]//label[@class='ng-binding']"))) {
			if (facilityName.getText().equalsIgnoreCase(facilityNameToBeSelected)) {
				facilityName.findElement(By.xpath("./parent::label/parent::div")).click();
				break;
			}

		}
	}

	/**
	 * Select people from the drop down options
	 * 
	 * @param peopleNameToBeSelected
	 **/
	public static void selectSitePeople(String peopleNameToBeSelected) {
		driver.findElement(By.xpath("//input[contains(@data-ng-model,'peopleItemsFilter')]"))
				.sendKeys(peopleNameToBeSelected);
		for (WebElement peopleName : driver.findElements(By.xpath(
				"//div[@class='dropdown-menu drop-popup']//div[contains(@data-ng-repeat,'peopleItems')]//label"))) {
			if (peopleName.getText().equalsIgnoreCase(peopleNameToBeSelected)) {
				peopleName.findElement(By.xpath("./parent::div")).click();
				break;
			}

		}
	}

	/**
	 * Select people system role from the drop down options
	 * 
	 * @param systemRoleToBeSelected
	 * @throws InterruptedException
	 **/
	public static void selectSitePeopleRoleAccess(String systemRoleToBeSelected) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='row grid-row selected']//div[@data-value='person.roleId']")).click();
		for (WebElement systemRole : driver.findElements(
				By.xpath("//div[@class='row grid-row selected']//div[@data-values='roles']//ul/li//span"))) {
			Thread.sleep(200);
			if (systemRole.getText().trim().equalsIgnoreCase(systemRoleToBeSelected)) {
				systemRole.findElement(By.xpath("./parent::li")).click();
				break;
			}

		}
	}

	/**
	 * Click on drop down Icon to expand the configured site details
	 * 
	 * @param configuredSiteName
	 **/
	public static void clickOnSiteDropDownIcon(String configuredSiteName) {
		for (WebElement configuredSiteDropDownIcon : driver
				.findElements(By.xpath("//div[contains(@data-ng-show,'siteDetails')]"))) {
			if (configuredSiteDropDownIcon.findElement(By.xpath(".//label[@class='orange ng-binding']")).getText()
					.equalsIgnoreCase(configuredSiteName)) {
				configuredSiteDropDownIcon.findElement(By.xpath(".//label[@class='orange ng-binding' and text()='"
						+ configuredSiteName + "']/parent::div/parent::div/preceding-sibling::div/a")).click();
				;
				break;
			}
		}
	}

	/**
	 * Select site language from options
	 * 
	 * @param languageToBeSelected
	 * @throws InterruptedException
	 **/
	public static void selectSitelanguage(String siteName, String languageToBeSelected) throws InterruptedException {
		Thread.sleep(2000);
		clickOnSiteDropDownIcon(siteName);
		for (WebElement language : driver
				.findElements(By.xpath("//div[@data-model='language']/div[2]/div//a[@class='a-color ng-binding']"))) {
			if (language.getText().trim().equalsIgnoreCase(languageToBeSelected)) {
				language.click();
				driver.findElement(By.xpath("//div[@class='collapsed-block open-grid']//a[@title='Add Language']")).click();;
				break;
			}
		}
	}

	/*
	 * Configure Study Site
	 * 
	 */

	/**
	 * Configure Site for Study
	 * 
	 * @param siteName
	 * @param facilityName
	 * @param sitePeople
	 * @param systemRole
	 * @param siteLanguage
	 * @throws InterruptedException
	 */
	public static void configureSitesData(String siteName, String facilityName,
			String siteLanguage) throws InterruptedException {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
		driver.findElement(By.xpath("//div[@class='row grid-header']//span[@class='icon-small icon-add']")).click();

		selectSite(siteName);
		driver.findElement(By.xpath("//div[@class='row row-collapsed-dark']//div[@class='col-xs-2']//input"))
				.sendKeys("Site");
		driver.findElement(By.xpath("//span[@class='icon-small icon-save']")).click();
		clickOnConfiguredSite(siteName);
		driver.findElement(By.xpath("//label[@class='ng-binding'and text()='Add Facility']/preceding-sibling::a"))
				.click();
		selectSiteFacility(facilityName);
		new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'status-block')]//input")));
		driver.findElement(By.xpath("//div[contains(@class,'status-block')]//input")).click();
		driver.findElement(By.xpath("//span[@class='icon-small icon-save']")).click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-xs-2 btn-arrows text-center']//a[@class='collapsed']")));
		new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='btn-group sites-tabs']/button[2]")));
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
		driver.findElement(By.xpath("//div[@class='btn-group sites-tabs']/button[2]")).click();
		selectMultiplePeopleWithRoleForSites(siteName);
		driver.findElement(By.xpath("//div[@class='btn-group sites-tabs']/button[3]")).click();
		selectSitelanguage(siteName, siteLanguage);
	}

	/* Select multiple people for Sites */

	public static void selectMultiplePeopleWithRoleForSites(String siteName) throws InterruptedException {
		HashMap<String, String> StudyPeopleWithSystemRoleList = new HashMap<>();
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Study_Site_People_Name.get(0),
				StudyGenerationData.System_Role_SitePeople.get(0));
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Study_Site_People_Name.get(1),
				StudyGenerationData.System_Role_SitePeople.get(1));
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Study_Site_People_Name.get(2),
				StudyGenerationData.System_Role_SitePeople.get(2));
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Study_Site_People_Name.get(3),
				StudyGenerationData.System_Role_SitePeople.get(3));

		// using iterators
		Iterator<Map.Entry<String, String>> iterator = StudyPeopleWithSystemRoleList.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String keyValue = entry.getValue();
			clickOnConfiguredSite(siteName);
			driver.findElement(By.xpath("//label[@class='ng-binding'and text()='Add People']/preceding-sibling::a"))
					.click();
			driver.findElement(By.xpath("//input[contains(@data-ng-model,'peopleItemsFilter')]")).clear();
			driver.findElement(By.xpath("//input[contains(@data-ng-model,'peopleItemsFilter')]")).sendKeys(key);
			for (WebElement peopleName : driver.findElements(By.xpath(
					"//div[@class='dropdown-menu drop-popup']//div[contains(@data-ng-repeat,'peopleItems')]//label"))) {
				if (peopleName.getText().equalsIgnoreCase(key)) {
					peopleName.click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
					selectPeopleSystemRole(keyValue);
					driver.findElement(
							By.xpath("//div[@class='row grid-row selected']/preceding-sibling::div//a[@title='Save']"))
							.click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
					break;
				}
			}
		}
	}

	/* Get Current Date */
	public static String currentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}

	/* Select Multiple People For Study */

	public static void selectMutilplePeopleForStudy(String datePickerLabel) {
		HashMap<String, String> StudyPeopleWithSystemRoleList = new HashMap<>();
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Super_User, StudyGenerationData.People_SystemRole.get(0));
		StudyPeopleWithSystemRoleList.put(StudyGenerationData.Site_Name_List.get(1),
				StudyGenerationData.People_SystemRole.get(0));

		// using iterators
		Iterator<Map.Entry<String, String>> iterator = StudyPeopleWithSystemRoleList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String keyValue = entry.getValue();
			new WebDriverWait(driver, 15).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
			driver.findElement(By.xpath("//div[@id='dropdownContainer']//a[@class='btn circle-button btn-white']"))
					.click();
			for (WebElement peopleName : driver.findElements(By.xpath(
					"//div[contains(@class,'dropdown')]//div[@data-ng-click='onAddStudyPeople(item)']//label"))) {
				if (peopleName.getText() .equalsIgnoreCase(key)) {
					peopleName.click();
					selectPeopleSystemRole(keyValue);
					for (WebElement loc : driver.findElements(By.xpath("//body//div[@class='date-wrapper']"))) {
						if (loc.findElement(By
								.xpath("./parent::div/parent::div/parent::div//label[contains(@class,'small-title')]"))
								.getText().trim().equalsIgnoreCase(datePickerLabel)) {
							loc.findElement(By.xpath(".//a[@class='add-on icon-calendar datepickerbutton']")).click();
							driver.findElement(
									By.xpath("//*[contains(@class,'picker-open')]//td[contains(@class,'today')]"))
									.click();
							driver.findElement(By.xpath("//div[@data-ng-form='personEditor']//a[@title='Save']"))
									.click();
							new WebDriverWait(driver, 15).until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
							break;
						}
					}
					break;
				}
			}
		}
	}

	public static void selectPeopleSystemRole(String systemRoleToBeSelected) {
		driver.findElement(By
				.xpath("//div[contains(@class,'edit')]//*[contains(@class,'grid-row selected')]//div[@data-value='person.roleId']"))
				.click();
		for (WebElement systemRole : driver.findElements(By.xpath(
				"//div[contains(@class,'edit')]//*[contains(@class,'grid-row selected')]//div[@data-value='person.roleId']//ul[@class='dropdown-menu']//span"))) {
			if (systemRole.getText().trim().equalsIgnoreCase(systemRoleToBeSelected)) {
				systemRole.click();
				break;
			}
		}
	}

}
