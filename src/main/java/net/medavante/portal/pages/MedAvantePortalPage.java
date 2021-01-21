package net.medavante.portal.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
	
	@FindBy(xpath="//*[@title='Virgil']")
	private WebElement virgilLogo;

	@FindBy(xpath = "//div[@class='user-column-holder']//span[text()='Qualifications:']")
	private WebElement qualificationTitle;

	@FindBy(xpath = "//div[@class='user-column-holder']//span[text()='Certifications:']")
	private WebElement certificationsTitle;

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
	
	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'General')]")
	private WebElement generaltab;
	
	@FindBy(xpath = "//header/ul[@class='nav nav-pills']")
	private WebElement topLevelNavigator;
	
	@FindBy(xpath="//span[@class='dashboard-tile-name']")
	private List<WebElement> tileNames;
	
	@FindBy(xpath="//header[@id='header']//a[text()='HOME']")
	private WebElement ActiveHome;
	
	@FindBy(xpath="//i[@class='burger-menu']")
	private WebElement burgerButton;
	
	@FindBy(xpath="//thead[@role='presentation']")
	private WebElement systemStatus;
	
  public MedAvantePortalPage verifyMedavantePortalPage() {
		waitForWebElementEnable(virgilLogo, 20);
		Assert.assertTrue(isElementPresent(virgilLogo));
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
		return this;
	}
  
  /**
   * Verify the text of tile passed as parameter
   * @author siddharth
   * @date 16/09/2019
   * @version 1.0
   * @param tileName
   */
  public void verifyTheTileIsPresent(String tileName) {
	  for(WebElement tile:tileNames) {
		 if(tile.getText().equalsIgnoreCase(tileName)) {
			 Assert.assertTrue(true);
		 }
	  }
	  		Assert.assertFalse(true);
  }

  /**
   * Verify the tab tile name passed as parameter
   * @author siddharth
   * @date 16/09/2019
   * @version 1.0
   * @param page
   */
	public void verifyPageTitle(String page) {
		WebElement title=driver.findElement(By.xpath("//a[contains(text(),'"+page+"')]"));
		moveToElement(title);
		Assert.assertTrue(isElementPresent(title),"The ["+page+"] is not present]");
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
		waitForElement(generaltab);
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
	
	/**
	 * Click on configure dropdown button
	 */
	public MedAvantePortalPage clickOnDropdown(String dropdownName) {
		String dropdownLocator="//li[@role='presentation']/a/span[contains(text(),'"+dropdownName+"')]";
		waitForPageLoaded();
		clickOn(dropdownLocator);
		return new MedAvantePortalPage(driver);
	}
	
	/**
	 * This method will Navigate user to the page passed in parameter.
	 * @author siddharth
	 * @created on:17/19/2019
	 * @version 1.0
	 * @param tabName
	 * @param classname
	 * @return {@link Generic}
	 */
	public <T> T navigateToPagePresentOnUpperTab(String tabName,Class<T> classname) {
		String tabLocator="//ul[@class='nav nav-pills'][1]//li/a[contains(text(),'"+tabName+"')]";
		waitForPageLoaded();
		clickOn(tabLocator);
		return (T) PageFactory.initElements(driver, classname);
	}
    
	/**
	 * Home screen is Displayed
	 */
	
	
	
	public void verifySelectedPageScreenIsDisplayed(String tagName, String text) {
		WebElement pagetitle = findElement(By.xpath("//" + tagName + "[contains(text(),'" + text + "')]"));
        moveToElement(pagetitle);
       Assert.assertTrue(isElementPresent(pagetitle));
	}
	
	/**
	 * top level navigation is Displayed
	 */

	public void verifyTopLevelNavigationIsDisplayed() {
		moveToElement(topLevelNavigator);
		Assert.assertTrue(isElementPresent(topLevelNavigator));
		reportInfo();		
	}
	
	/**
	 * verify home item is selected by Default
	 */

	public void verifyHomeItemIsSelectedByDefault() {
		moveToElement(ActiveHome);
		Assert.assertTrue(isElementPresent(ActiveHome));
		reportInfo();		
	}
	/**
	 * verify top level navigator item is displayed	
	 * @param tagName
	 * @param text
	 */
	public void verifyTopLevelNavigatorItemIsDisplayed(String tagName, String text) {
		WebElement locator = findElement(By.xpath("//" + tagName + "[contains(text(),'" + text + "')]"));
        moveToElement(locator);	
        Assert.assertTrue(isElementPresent(locator));
	}	
	
	/**
	 * click on top level navigator item
	 * @param tagName
	 * @param text
	 */
	public void clickOnTopLevelNavigatorItem(String tagName, String text) {
		WebElement tabLocator = findElement(By.xpath("//" + tagName + "[contains(text(),'" + text + "')]"));
		waitAndClick(tabLocator);
         reportInfo();

	}
	
	/**
	 * verify quick navigator is displayed
	 */
		
	public void verifyQuickNavigatorIsDisplayed() {
		moveToElement(burgerButton);
		Assert.assertTrue(isElementPresent(burgerButton));
		reportInfo();
		}
	
	/**
	 * select quick navigator
	 */

	public void selectQuickNavigator() {
		waitAndClick(burgerButton);
		}
	
	/**
	 * verify Navigate tape drop down list Items
	 * @param navItems
	 */
	public void verifyNavigateDropDownItems(String navItems) {
		WebElement locator = findElement(By.xpath("//span[text()='NAVIGATE']/..//following-sibling::ul/li/a[contains(text(),'"+navItems+"')]"));
        moveToElement(locator);	
        Assert.assertTrue(isElementPresent(locator));
        reportInfo();
	}
	
	/**
	 * click on naviagte tab study drop down item
	 * @param navItems
	 * @return
	 */
	
	public StudyDashBoardPage clickOnNavigateDropDownItems(String navItems) {
		WebElement navLocator = findElement(By.xpath("//span[text()='NAVIGATE']/..//following-sibling::ul/li/a[contains(text(),'"+navItems+"')]"));
        waitAndClick(navLocator);
		return PageFactory.initElements(driver, StudyDashBoardPage.class);

}
	/**
	 * verify system status tab is displayed
	 */

    public void verifySystemStatusTabIsDisplayed() {
    	Assert.assertTrue(systemStatus.isDisplayed());
    }
    
    @FindBy(xpath="//button[text()='Launch Training']")
    private WebElement LaunchTrainingbtn;
    
    @FindBy(xpath="//button[text()=' Cancel']")
    private WebElement cancelButton;
    
    
    /**
     *  verify training Is displayed
     * @param trainingName
     */
    public void verifyRespectiveTrainingIsDisplayed(String trainingName)
    {
    	waitUntillFinishProcessSpinnerDisable();
    	WebElement elementToBeVerified = driver.findElement(ByLocator("//strong[contains(text(),'"+trainingName+"')]"));
    	moveToElement(elementToBeVerified);
    	Assert.assertTrue(isElementDisplayed(elementToBeVerified));
    	reportInfo();
    }
    /**
     *  select Action to Start Training
     * @param trainingName
     */
    public void selectActionToStartTraining(String trainingName)
    {
		 WebElement optionToBeSelected= driver.findElement(ByLocator("//strong[contains(text(),'"+trainingName+"')]/..//button"));
		 clickOn(optionToBeSelected);
		 reportInfo();
    }
    /**
     *  verify credit Label is displayed
     * @param assetName
     */
    public void verifyCreditLabelIsDisplayedNextToAssetName(String assetName)
    {
    	WebElement fieldToBeVerified = 
    			driver.findElement(ByLocator("//span[text()='"+assetName+"']/ancestor::div//span[text()='Credit']"));
    	moveToElement(fieldToBeVerified);
    	Assert.assertTrue(isElementDisplayed(fieldToBeVerified));
    	reportInfo();
    }
   /**
     *  verify training Is started
     * @param trainingName
     */
    public void verifyTrainingIsStarted(String trainingName)
    {
    	waitUntillFinishProcessSpinnerDisable();
    	WebElement elementToBeVerified = 
    			driver.findElement(ByLocator("//div[@class='ma-dialog-header']/span[contains(text(),'"+trainingName+"')]"));
    	Assert.assertTrue(elementToBeVerified.isDisplayed());
    	reportInfo();
    }
    /**
     *  close asset
     */
    public void closeAsset()
    {
    	clickOn(cancelButton);
    	waitUntillFinishProcessSpinnerDisable();
    	reportInfo();
    }
    /*
     * launch Training & close asset
     */
    public void launchTrainingAndCloseAsset()
    {
    	try {
			if (LaunchTrainingbtn.isDisplayed()) {
				clickOn(LaunchTrainingbtn);
				waitUntillFinishProcessSpinnerDisable();
				Set<String> windows = driver.getWindowHandles();
				Iterator<String> iterator = windows.iterator();
				String parent = iterator.next();
				String child = iterator.next();
				getDriver().switchTo().window(child);
				driver.close();
				getDriver().switchTo().window(parent);
		    	reportInfo();
			}
		} catch (Exception e) {
		}
    	
    }
}
		
		

	



