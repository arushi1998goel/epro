package net.medavante.portal.qa.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BasePage;

public class MedAvantePortalPage extends BasePage {

	public MedAvantePortalPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='page-title']/h1[text()='Welcome to MedAvante Portal']")
	private WebElement pageTitle;

	@FindBy(xpath = "//*[@data-ng-href='/admin']")
	private WebElement administrationTile;

	@FindBy(xpath = "//*[@data-ng-href='/study']")
	private WebElement studyNavigatorTile;

	@FindBy(xpath = "//*[@data-ng-href='/centralrating']")
	private WebElement centralRatingTile;

	@FindBy(xpath = "//*[@data-ng-href='/prequalification']")
	private WebElement preQualificationTile;

	@FindBy(xpath = "//*[@data-ng-href='/iq']")
	private WebElement analyticsTile;
	
	
	/*-------------------New Functionality Locators--------------------------------------*/

	@FindBy(xpath = "(//ul[contains(@class,'nav') and not(contains(@class,'drop'))])[1]/li/a")
	private List<WebElement> navMenuItemsList;

	@FindBy(xpath = "//*[@id='header']//ul[@class='dropdown-menu']/parent::li[@class='dropdown open']//following-sibling::ul/li/a")
	private List<WebElement> subDropDownOfNavMenu;
	
	
	
	
	

	public MedAvantePortalPage verifyMedavantePortalPage() {
		waitForWebElementEnable(pageTitle, 10);
		Assert.assertTrue(isElementPresent(pageTitle));
		reportInfo();
		return this;
	}

	public StudyDashBoardPage navigateToStudyNavigator() {
		waitForWebElementEnable(pageTitle, 10);
		waitAndClick(studyNavigatorTile);
		return PageFactory.initElements(driver, StudyDashBoardPage.class);
	}

	public CentralRatingAssessmentsListingPage navigateToCentralRatings() {
		waitForElementClickable(centralRatingTile, DEFAULT_WAIT_4_PAGE);
		waitAndClick(centralRatingTile);
		waitSpinnerToBecomeInvisible();
		waitForPageLoaded();
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-ng-if='isFirstPageLoaded']")));

		return PageFactory.initElements(driver, CentralRatingAssessmentsListingPage.class);
	}

	@SuppressWarnings("unchecked")
	public <T> T selectAdministrationPortal(final Class className) {
		waitForWebElementEnable(administrationTile, 10);
		waitAndClick(administrationTile);
		return (T) PageFactory.initElements(driver, className);
	}

	public AdministrationOrganizationPage navigateToAdministration() {
		waitAndClick(administrationTile);
		waitForSpinner(10);
		return PageFactory.initElements(driver, AdministrationOrganizationPage.class);
	}

	public PreQualificationDashBoardPage navigateToPreQualifications() {
		waitAndClick(preQualificationTile);
		return PageFactory.initElements(driver, PreQualificationDashBoardPage.class);
	}

	public AnalyticsDashBoardPage navigateToAnalytics() {
		waitAndClick(analyticsTile);
		_normalWait(3000);
		return PageFactory.initElements(driver, AnalyticsDashBoardPage.class);
	}

	public String getSystemTime() {
		// "hh" in pattern is for 12 hour time format and "aa" is for AM/PM
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy hh");
		// Setting the time zoneS
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		String timeZone = dateTimeInGMT.format(new Date());
		return timeZone;

	}

	/* Verify Study Navigator Tile Is Displayed */

	public void verifyStudyNavigatorTilePresent() {
		moveToElement(studyNavigatorTile);
		Assert.assertTrue(isElementPresent(studyNavigatorTile));
		reportInfo();
	}

	public StudyDashBoardPage navigateBackToStudyNavigatorPage() {
		navigateBack();
		return PageFactory.initElements(driver, StudyDashBoardPage.class);
	}

	
	/*-------------------New Functionality methods--------------------------------------*/
	
	/* Select Upper Nav Menu Items */

	public <T> T selectHorizontalUpperNavMenuItem(final Class className,String itemToBeSelect, String... subTabValue) {

		boolean flag = false;
		_normalWait(3000);
		for (WebElement menuItem : navMenuItemsList) {
			try {
				if (getText(menuItem).trim().equalsIgnoreCase(itemToBeSelect)) {

					clickOn(menuItem);
					flag = true;
				}
			} 
			catch (Exception e) {

				if (menuItem.findElement(By.xpath(".//spanet.medavante.portal.pagesn[@class='dropdown-text']")).getText()
						.equalsIgnoreCase(itemToBeSelect)) {
					clickOn(menuItem);
					flag = true;
				}

			}
			if (subDropDownOfNavMenu.size() > 0) {
				for (WebElement subTab : subDropDownOfNavMenu) {
					if (getText(subTab).equalsIgnoreCase(subTabValue[0])) {
						clickOn(subTab);
						flag = true;
						break;
					}

				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
		return (T) PageFactory.initElements(driver, className);
	}

}
