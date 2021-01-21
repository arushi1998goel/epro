package net.medavante.portal.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesPreQualificationsPage extends BasePage {

	@FindBy(xpath = "//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//span/label[contains(text(),'Add Emails')]")
	private WebElement addmailsLabelSurveyTracker;

	@FindBy(xpath = "//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//span/label[contains(text(),'Send Survey')]")
	private WebElement sendSurveySurveyTracker;

	@FindBy(xpath = "//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//span/label[contains(text(),'Export to CSV')]")
	private WebElement ExportToCsvSurveyTracker;

	@FindBy(xpath = "//ul[@role='tablist']//li/a")
	private List<WebElement> activeFilter;

	public AdministrationStudiesPreQualificationsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesSurveyTrackerPage() {
		Assert.assertTrue(isElementPresent(addmailsLabelSurveyTracker)
				|| isElementPresent(sendSurveySurveyTracker) || isElementPresent(ExportToCsvSurveyTracker));
	}

	public int getFilterCount(String getCountOfFilter) {
		int totalCount = 0;
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ng-isolate-scope']"));
		for (WebElement filterName : activeFilter) {
			if (getText(filterName).contains(getCountOfFilter)) {
				totalCount = Integer.parseInt(getText(filterName.findElement(By.xpath(".//following-sibling::span"))));
				break; 
			}

		}
		reportInfo();
		return totalCount;
	}

}
