package net.medavante.portal.qa.pages.analytics;

import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AnalyticsDashBoardPage extends BasePage {

	@FindBy(xpath = "//*[@data-select-action='studySelected']//button[contains(@class,'btn dropdown-toggle')]")
	private WebElement selectStudyDRPDWN;
	
	@FindBy(xpath = "//*[@data-select-action='siteSelected']//button[contains(@class,'btn dropdown-toggle')]")
	private WebElement selectSiteDRPDWN;

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']")
	private WebElement studyListDRPDWN;

	@FindBy(xpath = "//li[@ng-repeat='dashboard in dashboardList']//span[@class='text-frame ng-binding']")
	private List<WebElement> configuredDashboard;

	@FindBy(xpath = "//div[@class='row dashboard']//h2[@class='breadcrumb-title ng-binding']")
	private WebElement selectedDashBoardTXT;

	
	@FindBy(xpath = "//div[@class='MicrosoftMap']")
	private WebElement navofDetailsDashBoard;
	

	public AnalyticsDashBoardPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 */
	public void selectStudy(String studyName) {
		waitAndClick(selectStudyDRPDWN);
		studyListDRPDWN.findElement(By.xpath("//span[contains(text(),'" + studyName + "')]")).click();
		_normalWait(15000);
		spinnerBecomeInvisible();
	}

	public void verifyAnalyticsDashBoardPage() {
		_normalWait(5000);
		waitForPageLoaded();
		if (isElementPresent(By.xpath("//iframe[@allowfullscreen='true']"))) {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@allowfullscreen='true']")));
			Assert.assertTrue((isElementPresent(navofDetailsDashBoard)));
			driver.switchTo().defaultContent();
		}
		else{
			Log.info("No DashBoard Added In List");
		}
	}

	/** Select dashboard from left side dashboards options */
	public void selectDashBoard(String dashBoardToBeSelected) {
		for (WebElement dashBoardName : configuredDashboard) {
			if (getText(dashBoardName).equalsIgnoreCase(dashBoardToBeSelected)) {
				clickOn(dashBoardName.findElement(By.xpath("./parent::span/parent::li")));
				spinnerBecomeInvisible();
				break;
			}
		}
	}

	/** Verify dashboard is selected */
	public void verifydashBoardIsSelected(String selectedDashBoard) {
		Assert.assertEquals(getText(selectedDashBoardTXT), selectedDashBoard);
		reportInfo();
	}

}
