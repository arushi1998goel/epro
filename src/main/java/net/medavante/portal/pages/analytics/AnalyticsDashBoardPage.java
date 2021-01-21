package net.medavante.portal.pages.analytics;

import java.util.List;
import java.util.NoSuchElementException;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AnalyticsDashBoardPage extends BasePage {

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']")
	private WebElement studyListDRPDWN;

	@FindBy(xpath = "//li[@ng-repeat='dashboard in dashboardList']//span[@class='text-frame ng-binding']")
	private List<WebElement> configuredDashboard;

	@FindBy(xpath = "//*[@class='breadcrumb-title ng-binding']")
	private WebElement selectedDashBoardTXT;

	@FindBy(xpath = "//div[@id='mapFocus']")
	private WebElement navofDetailsDashBoard;
	
	@FindBy(xpath = "//*[contains(@data-display-value,'selectedStudy')]//button[contains(@class,'btn dropdown-toggle')]")
	private WebElement selectStudyDRPDWN;
	
	@FindBy(xpath = "//*[@data-select-action='siteSelected']//button[contains(@class,'btn dropdown-toggle')]")
	private WebElement selectSiteDRPDWN;
	
	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']//ul[@class='dropdown-menu']")
	private WebElement searchSiteDRPDWN;
	
	@FindBy(xpath="//div[@class='dropdown-menu']//ul[contains(@class,'dropdown-menu')]/li/span")
	private List<WebElement> studyLIST;
	
	@FindBy(xpath = "//div[@class='error-container']//div[@class='close-button-white']")
	private WebElement errorContainerCloseIcon;
	
	@FindBy(xpath="//button[@title='Reports']/following-sibling::ul[@class='aside-nav-list in']/li/span[@class='f-text ng-binding']")
    private List<WebElement> reportsList;
	
	@FindBy(xpath="//button[@title='Dashboards']/following::li/span[@class='f-text ng-binding']")
	private List<WebElement> dashboardList;
	
	@FindBy(xpath = "//li[@ng-repeat='dashboard in vm.dashboards']//span[@class='f-text ng-binding']")
	private List<WebElement> Dashboardlist;


	public AnalyticsDashBoardPage(WebDriver driver) {
		super(driver);
	}

	
	
	public void selectDashboardFromList(String dashboardName)
	{
		boolean flag=true;
		for(WebElement web:Dashboardlist)
		{
			if(web.getText().trim().equalsIgnoreCase(dashboardName))
			{   clickOn(web);
				flag=true;
				break;		}
		}
		Assert.assertTrue(flag);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	
	
	
	
	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 */
	public void selectStudy(String studyName) {
		boolean flag=false;
		waitAndClick(selectStudyDRPDWN);
		_normalWait(3000);
		for (WebElement studyNametoselect : studyLIST) {
			if (studyNametoselect.getText().trim().contains(studyName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", studyNametoselect);
				waitAndClick(studyNametoselect);
				_normalWait(2000);
				flag = true;
				break;
			}
		}
		spinnerBecomeInvisible();
		Assert.assertTrue(flag);
	}

	

	public void verifyAnalyticsDashBoardPage() {
		boolean flag=false;
		_normalWait(5000);
		waitSpinnerToBecomeInvisible();
		if (isElementPresent(By.xpath("//iframe[@allowfullscreen='true']"))) {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@allowfullscreen='true']")));
			waitForWebElementEnable(navofDetailsDashBoard, 40);
			Assert.assertTrue((isElementPresent(navofDetailsDashBoard)));
			flag = true;
			driver.switchTo().defaultContent();
		}
		else{
			Assert.assertFalse(flag, "No DashBoard Added In List");
		}
		
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Select dashboard from left side dashboards options */
	public void selectDashBoard(String dashBoardToBeSelected) {
		for (WebElement dashBoardName : configuredDashboard) {
			if (getText(dashBoardName).equalsIgnoreCase(dashBoardToBeSelected)) {
				waitAndClick(dashBoardName.findElement(By.xpath("./parent::span/parent::li")));
				waitSpinnerToBecomeInvisible();
				break;
			}
			
		}
	}

	/** Verify dashboard is selected */
	public void verifydashBoardIsSelected(String selectedDashBoard) {
		Assert.assertEquals(getText(selectedDashBoardTXT), selectedDashBoard);
		new WebDriverWait(driver, 60)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner ng-hide']")));
		reportInfo();
	}
	
	/**
	 * Click on site drop down to select site
	 * 
	 */
	public void selectSite(String siteName) {
		clickOn(selectSiteDRPDWN);
		searchSiteDRPDWN.findElement(By.xpath("//li//span[contains(text(),'" + siteName + "')]")).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		_normalWait(800);
		reportInfo();
	}
	/**
	 * verify analytics page is displayed
	 */
	public void verifyAnalyticsDashBoardPageIsDisplayed() {
		Assert.assertTrue(selectStudyDRPDWN.isDisplayed());
	}

	/**
	 *  verify respective Reports displayed
	 * @param count
	 */
	public void verifyRespectiveReportsDisplayed(int count)
	{
		waitUntillFinishProcessSpinnerDisable();
		int reportsCounts = reportsList.size();
		boolean flag=true;
		if (reportsCounts==count) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * verify respective dashboards displayed
	 * @param count
	 */
	public void verifyRespectiveDashboardsDisplayed(int count)
	{
		waitUntillFinishProcessSpinnerDisable();
		int dashboardsCounts = dashboardList.size();
		boolean flag=true;
		if (dashboardsCounts==count) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify report is selected
	 * @param reportName
	 */
	public void verifyReportIsSelected(String reportName)
	{
		WebElement reportToBeVerified = driver.findElement(ByLocator("//span[text()='"+reportName+"']/ancestor::li"));
		boolean flag=true;
		if (reportToBeVerified.isSelected()) {
			moveToElement(reportToBeVerified);
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify Respective report used Template displayed
	 * @param reportName
	 */
	public void verifyRespectiveUsedTemplateIsDisplayed(String reportName)
	{
		WebElement templateToBeVerified = driver.findElement(ByLocator("//h2[contains(text(),'"+reportName+"')]/.."));
		moveToElement(templateToBeVerified);
		Assert.assertTrue(isElementDisplayed(templateToBeVerified));
		reportInfo();
	}
	/**
	 *  verify corresponding report Data reflects
	 */
	public void verifyCorrespondingReportDataReflects()
	{
		List<WebElement> reportDataList =getDriver().findElements(ByLocator("//table[@id='ParametersGridreportViewer_ctl09']//td[@style='padding-right:0px;']//td[1]/input"));
		int dataCount = reportDataList.size();
		for (int i = 1; i <=dataCount; i++) {
		 WebElement elementToBeVerified = driver.findElement(ByLocator
				 ("(//table[@id='ParametersGridreportViewer_ctl09']//td[@style='padding-right:0px;']//td[1])[" + i + "]"));
		moveToElement(elementToBeVerified);
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		}
	}
	/**
	 *  select Report
	 * @param reportName
	 */
	public void selectReport(String reportName)
	{
		WebElement reportToBeSelected = driver.findElement(ByLocator("//span[text()='"+reportName+"']/ancestor::li"));
		clickOn(reportToBeSelected);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
}
