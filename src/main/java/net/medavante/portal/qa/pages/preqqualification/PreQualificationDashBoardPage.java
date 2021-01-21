package net.medavante.portal.qa.pages.preqqualification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.selenium.core.BasePage;

public class PreQualificationDashBoardPage extends BasePage {

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-active']")
	private WebElement studyDRPDWN;

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-default']")
	private WebElement siteDRPDWN;

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']")
	private WebElement studyListDRPDWN;

	@FindBy(xpath = "//div[@data-ng-if='IsSummaryViewSelected']//div[@class='summary-page']")
	private WebElement preQualificationSummaryChartVIEW;

	@FindBy(xpath = "//div[@id='PreQualificationDetailsGrid']")
	private WebElement preQualificationRaterScaleListVIEW;

	@FindBy(xpath = "//div[text()='No Pre-qualification Data.']")
	private WebElement summaryWithoutpreQualificationVIEW;

	@FindBy(xpath = "//a[@class='flat-view-button']")
	private WebElement viewModeBTN;

	@FindBy(xpath = "//span[@data-ng-show='RaterGridTotalRecords > 0']")
	private WebElement totalPreQualificationRaterScaleCOUNT;

	@FindBy(xpath = "//table//tbody/tr")
	private List<WebElement> totalRaterScaleRecords;

	@FindBy(xpath = "//h1[text()='Pre-qualification Rater Scale List ']")
	private WebElement prequalificationScaleListText;

	@FindBy(xpath = "//th/a[text()='Rater']")
	private WebElement raterLabel;

	@FindBy(xpath = "//th/a[text()='Site']")
	private WebElement siteLabel;

	@FindBy(xpath = "//th[text()='ADAS-Cog']")
	private WebElement scaleLabel;

	@FindBy(xpath = "//div[contains(@class,'k-grid-content')]//table//tr/td[1]/span")
	private List<WebElement> raterNameList;

	@FindBy(xpath = "//div[contains(@class,'k-grid-content')]//table//tr/td[2]/span")
	private List<WebElement> siteNameList;

	@FindBy(xpath = "//table[@class='k-selectable']//tr")
	private List<WebElement> scaleDescriptionRow;

	@FindBy(xpath = "//tr[@class='k-filter-row']//span[@data-field='rater']//input")
	private WebElement raterFilterINP;

	@FindBy(xpath = "//tr[@class='k-filter-row']//span[@data-field='site']//input")
	private WebElement siteFilterINP;

	@FindBy(xpath = "//div[@class='row summary-row ng-scope']/div[3]")
	private List<WebElement> surveySentCount;

	@FindBy(xpath = "//div[@class='row summary-row ng-scope']/div[4]")
	private List<WebElement> surveyReceivedCount;

	@FindBy(xpath = "//span[@class='k-widget k-dropdown k-header']")
	private WebElement scaleDRPDWN;

	@FindBy(xpath = "//div[@class='k-animation-container'][1]//ul/li//span")
	private List<WebElement> raterQualificationScaleList;

	@FindBy(xpath = "//div[@class='k-animation-container'][1]/div/div/ul/li//input")
	private List<WebElement> prequalificationCHKBX;

	@FindBy(xpath = "//tr[1]/th[3]")
	private WebElement parentScaleFirst;

	@FindBy(xpath = "//tr[1]/th[4]")
	private WebElement parentScaleSecond;

	@FindBy(xpath = "//table//tr[1]/th")
	private List<WebElement> ScaleArrangementList;

	@FindBy(xpath = "//div[@id='page-title']//a[@class='view-reset view-reset-shadow']")
	private WebElement resetButton;

	@FindBy(xpath = "//div[@id='page-title']//a[@title='Refresh']")
	private WebElement refreshtButton;

	@FindBy(xpath = "//table[@class='k-selectable']//tr/td[3]")
	private List<WebElement> ratercolumnScaleResultValues;

	List<String> listOfRaterQualification = new ArrayList<>(Arrays.asList("Not Pre-qualified", "Pre-qualified",
			"For Review", "Recommended", "Not Recommended", "Follow-up"));

	public PreQualificationDashBoardPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 */
	public void selectStudy(String studyName) {
		clickOn(studyDRPDWN);
		studyListDRPDWN.findElement(By.xpath("//span[contains(text(),'" + studyName + "')]")).click();
	}

	/**
	 * Verify summary chart is displayed for selected study
	 */
	public void verifySummaryChartIsOpened() {
		Assert.assertTrue(isElementPresent(preQualificationSummaryChartVIEW));
		reportInfo();
	}

	/**
	 * Verify Surveys Sent Summary Count
	 */
	public void verifySummarySurveysSentCount(int surveyCount) {
		int totalSentSiteCountValue = 0;
		for (WebElement siteSurveySentCount : surveySentCount) {
			int totalSentCount = Integer.parseInt(getText(siteSurveySentCount));
			totalSentSiteCountValue = totalSentSiteCountValue + totalSentCount;
		}
		Assert.assertEquals(surveyCount, totalSentSiteCountValue);
		reportInfo();
	}

	/**
	 * Verify Surveys Received Summary Count
	 */
	public void verifySummarySurveysReceivedCount(int surveyReceived) {
		int totalReceivedSiteCountValue = 0;
		for (WebElement siteSurveyReceivedCount : surveyReceivedCount) {
			int totalSentCount = Integer.parseInt(getText(siteSurveyReceivedCount));
			totalReceivedSiteCountValue = totalReceivedSiteCountValue + totalSentCount;
		}
		Assert.assertEquals(totalReceivedSiteCountValue, surveyReceived);
		reportInfo();
	}

	/**
	 * Verify Pre Qualification Rater Scale List displayed for selected study
	 */

	public void verifyPreQualificationRaterScaleListIsDisplayed() {
		waitForElement(preQualificationRaterScaleListVIEW);
		Assert.assertTrue(isElementPresent(preQualificationRaterScaleListVIEW));
		reportInfo();
	}

	/** To change the mode of the application */
	public void changeViewMode() {

		clickOn(viewModeBTN);
	}

	/**
	 * Verify total rater scale count Displayed at the top and in the list
	 * should be same
	 */
	public void verifyTotalPreQualificationRaterScaleIsDisplayed() {
		_normalWait(2000);
		if (totalRaterScaleRecords.size() > 0) {
			String[] splitString = getText(totalPreQualificationRaterScaleCOUNT).split("\\(");
			Assert.assertEquals(String.valueOf(totalRaterScaleRecords.size()), splitString[1].replaceAll("\\)", ""));
			reportInfo();
		} else {
			Log.info("Rater not Present");
			reportInfo();
		}

	}

	/**
	 * Click on site drop down to select the site
	 * 
	 * @param siteName
	 */
	public void selectSite(String siteName) {
		clickOn(siteDRPDWN);
		siteDRPDWN.findElement(By.xpath("//following-sibling::div/ul/li/span[contains(text(),'" + siteName + "')]"))
				.click();
		waitSpinnerToBecomeInvisible();
	}

	/** Verify All Label Present In The Page */
	public void verifyPreQualificationRaterLabelPresent() {
		Assert.assertTrue(isElementPresent(prequalificationScaleListText) && isElementPresent(raterLabel)
				&& isElementPresent(siteLabel) && isElementPresent(scaleLabel));
	}

	/** Verify Rater Column Values **/
	public void verifyPreQualificationRaterScaleListRaterColumnValues(String raterName) {
		_waitForJStoLoad();
		boolean flag = false;
		for (WebElement raterValues : raterNameList) {
			if (raterName.equalsIgnoreCase(getText(raterValues))) {
				flag = true;
				break;
			} else {
				continue;
			}

		}
		Assert.assertTrue(flag, raterName + " Present In the list");
	}

	/** Verify Rater Column Values **/
	public void verifyPreQualificationRaterScaleColumnValues(String columnName) {
		_waitForJStoLoad();
		WebElement scaleColumnValue = driver.findElement(
				By.xpath("//*[@id='PreQualificationDetailsGrid']//table//span[text()='" + columnName + "']"));
		Assert.assertTrue(isElementPresent(scaleColumnValue), "Scale Coloum value is displaying");

	}

	/** Verify Site Column Values **/
	public void verifyPreQualificationRaterScaleListSiteColumnValues(String siteName) {
		_waitForJStoLoad();
		boolean flag = false;
		for (WebElement siteValue : siteNameList) {
			waitForElement(siteValue);
			if ((getText(siteValue)).contains(siteName)) {
				flag = true;
				break;
			} else {
				continue;
			}

		}
		Assert.assertTrue(flag, siteName + " Present In the list");
	}

	/** Verify Filters */

	public void verifyRaterFiltersPresent() {
		Assert.assertTrue(isElementPresent(raterFilterINP) && isElementPresent(siteFilterINP));
	}

	/** Verify Scale Dropdown Values */

	public void verifyRaterScaleDropdownValues() {
		clickOn(scaleDRPDWN);
		waitForElementPresent(By.xpath("//div[@class='k-list-scroller']//ul[@aria-hidden='false']"), 10);
		List<String> raterQualification = new ArrayList<>();
		for (WebElement prequalificationScaleList : raterQualificationScaleList) {
			waitForElement(prequalificationScaleList);
			raterQualification.add(getText(prequalificationScaleList));

		}
		Assert.assertEquals(raterQualification, listOfRaterQualification, "Rater Qualification Present in List");
	}

	/**
	 * Select Multiple DropDown Values
	 */
	public void selectRaterCheckBoxesCAndVerifyMultipleCheckBoxesIsSelected() {
		waitForElement(scaleDRPDWN);
		int sizeOfCheckBoxes = prequalificationCHKBX.size();
		boolean flag = false;
		for (int i = 0; i < sizeOfCheckBoxes - 3; i++) {
			prequalificationCHKBX.get(i).click();
			waitForElement(scaleDRPDWN);
			clickOn(scaleDRPDWN);
			_normalWait(1000);
		}
		int checkedCount = 0, uncheckedCount = 0;
		for (int i = 0; i < sizeOfCheckBoxes; i++) {
			if (prequalificationCHKBX.get(i).isSelected()) {
				checkedCount++;
			} else {
				uncheckedCount++;
			}
		}
		if (checkedCount > 1) {
			flag = true;
			Assert.assertTrue(flag, "Multiple values checked in dropdown");

		} else {
			Assert.assertTrue(flag, "Multiple values not checked in dropdown");
		}

	}

	/**
	 * Rearrange Parent Scales
	 * 
	 * 
	 */
	public void reArrangeRaterScales() {
		waitForElement(parentScaleFirst);
		waitForElement(parentScaleSecond);
		List<String> scaleBeforeArrangementList = new ArrayList<>();
		for (WebElement scaleArrange : ScaleArrangementList) {
			scaleBeforeArrangementList.add(getText(scaleArrange));
		}
		dragAndDrop(parentScaleFirst, parentScaleSecond);
		List<String> scaleAfterArrangementList = new ArrayList<>();
		for (WebElement scaleArrange : ScaleArrangementList) {
			scaleAfterArrangementList.add(getText(scaleArrange));
		}
		Assert.assertNotEquals(scaleBeforeArrangementList, scaleAfterArrangementList, "Scale Arranged Sucessfully");
	}

	/** Verify Reset Button Present */
	public void raterResetButtonIsPresent() {
		Assert.assertTrue(isElementPresent(resetButton));
	}

	/** Verify Refresh Button Present */
	public void raterRefreshtButtonIsPresent() {
		Assert.assertTrue(isElementPresent(refreshtButton));
	}

	/** Verify Different Scales Showing For Diiferent Raters */

	public void verifyraterScaleResultValues() {
		boolean flag = false;
		List<String> scaleRaterResultList = new ArrayList<>();
		for (WebElement result : ratercolumnScaleResultValues) {
			scaleRaterResultList.add(getText(result));
		}
		if (listOfRaterQualification.containsAll(scaleRaterResultList)) {
			flag = true;
			Assert.assertTrue(flag, "Different result scale showing for different Rater");
		} else {
			Assert.assertTrue(flag, "Different result scale Not showing for different Rater");
		}
	}

	/**
	 * Verify the Scale Descriptions
	 * 
	 * @param columnName
	 * @param rowValue
	 */
	public void verifyDetailsForScale(String scaleSelected, String rowValue) {
		boolean flag = false;
		for (int columnNameIndex = 0; columnNameIndex < ScaleArrangementList.size(); columnNameIndex++) {
			if (getText(ScaleArrangementList.get(columnNameIndex)).trim().equalsIgnoreCase(scaleSelected)) {
				int columnNameIndex1 = columnNameIndex + 1;
				for (int recordRow = 0; recordRow < scaleDescriptionRow.size(); recordRow++) {
					WebElement scaleQualification = scaleDescriptionRow.get(recordRow)
							.findElement(By.xpath(".//td[" + columnNameIndex1 + "]//span"));
					if (getText(scaleQualification).trim().equalsIgnoreCase(rowValue)) {
						moveToElement(scaleQualification);
						flag = true;
						break;
					}

				}
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

}
