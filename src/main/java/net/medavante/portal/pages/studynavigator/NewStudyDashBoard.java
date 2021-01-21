package net.medavante.portal.pages.studynavigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class NewStudyDashBoard extends BasePage {

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-active']")
	private WebElement selectStudyDRPDWN;

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']")
	private WebElement searchStudyDRPDWN;

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']/li/span")
	private List<WebElement> studyLIST;

	@FindBy(xpath = "//div[@data-items='selectedStudy.sites']")
	private WebElement selectSiteDRPDWN;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']//ul[@class='dropdown-menu']")
	private WebElement searchSiteDRPDWN;

	@FindBy(xpath = "//div[@class='sidenav-area']//li[1]/span[2]")
	private WebElement subjects;

	@FindBy(xpath = "//div[@class='sidenav-area']//li[2]/span[2]")
	private WebElement visits;

	@FindBy(xpath = "//div[@class='sidenav-area']//li[3]/span[2]")
	private WebElement assessments;

	@FindBy(xpath = "//div[@class='aside-controls-holder']/div[@class='study-filter-holder']//span[contains(text(),'New')]")
	private WebElement subjectNewRadioBTN;

	@FindBy(xpath = "//span[@class='icon-small icon-home']")
	private WebElement homeIcon;

	@FindBy(xpath = "(//div[@class='chart-legend'])[1]/a/span[@class='scale-descriptor ng-binding']")
	private List<WebElement> subjectStatusList;

	@FindBy(xpath = "(//div[@class='chart-legend'])[1]/a//span[@class='scale-records-count pull-right ng-binding ng-scope']")
	private List<WebElement> subjectStatusCountList;

	@FindBy(xpath = "(//div[@class='chart-legend'])[1]/a")
	private List<WebElement> subjectStatusListLINK;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//div[@class='row grid-row ng-scope']")
	private WebElement subjectStatusCountOnListingPage;

	@FindBy(xpath = "//a[contains(@title,'any visits')]//span[contains(@data-ng-if,'valuesByScales')]")
	private WebElement statusForNew;
	
	/*
	 * 
	 * Subject Listing locators
	 * 
	 */

	@FindBy(xpath = "//a[@title='Add Subject']")
	private WebElement addSubjectBTN;

	@FindBy(xpath = "//select[@title='Choose Site']")
	private WebElement addSubjectSiteLIST;

	@FindBy(xpath = "//div[contains(@class,'show in')]//div[@class='modal-footer']/button[@data-ng-click='close()']")
	private WebElement addSubPopup_CancelBTN;

	private ArrayList<String> subjectStatusTextLIST = new ArrayList<>(
			Arrays.asList("New", "Screened", "ScreenFailed", "Enrolled", "Completed", "Discontinued"));

	public NewStudyDashBoard(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 */
	public void selectStudy(String studyName) {
		boolean flag = false;
		clickOn(selectStudyDRPDWN);
		for (WebElement studyNametoselect : studyLIST) {
			if (studyNametoselect.getText().trim().contains(studyName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", studyNametoselect);
				clickOn(studyNametoselect);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, studyName + " supposed to be in study list");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='sub-navigation-group pull-right ng-scope']")));
	}

	/**
	 * Click on subject link to view the subject listing
	 */
	public void navigateToSubjectsListing() {
		clickOn(subjects);
		waitForElement(subjectNewRadioBTN);
	}

	/**
	 * Click on visit link to view the visit listing
	 */
	public void navigateToVisitsListing() {
		clickOn(visits);
	}

	/**
	 * Click on Assessment link to view the visit listing
	 */
	public void navigateToAssessmentsListing() {
		clickOn(assessments);
	}

	/**
	 * Click On Home Icon And Navigate To Home Page
	 */

	public void navigateToHome() {
		clickOn(homeIcon);
	}

	/** Verify ToolTip Status Description With Max Length **/

	public void verifyToolTipStatusDescriptionWithMaxLength(String newStatus, String screenedStatus,
			String screenFailedStatus, String discontinueStatus) {

		Assert.assertEquals(
				checkSubjectStatusDescriptioLength(newStatus) && checkSubjectStatusDescriptioLength(screenedStatus)
						&& checkSubjectStatusDescriptioLength(screenedStatus)
						&& checkSubjectStatusDescriptioLength(screenFailedStatus)
						&& checkSubjectStatusDescriptioLength(screenFailedStatus),
				true);

	}

	/** Verify Card Subject Status Description With Max Length */

	public void verifyCardStatusDescriptionWithMaxLength(String newStatus, String screenedStatus,
			String screenFailedStatus, String discontinueStatus) {

		Assert.assertEquals(
				checkSubjectStatusDescriptioLength(newStatus) && checkSubjectStatusDescriptioLength(screenedStatus)
						&& checkSubjectStatusDescriptioLength(screenedStatus)
						&& checkSubjectStatusDescriptioLength(screenFailedStatus)
						&& checkSubjectStatusDescriptioLength(screenFailedStatus),
				true);

	}

	/** Check Max Length Of String **/
	public boolean checkSubjectStatusDescriptioLength(String _value) {
		boolean flag = (_value.length() == 99) ? true : false;
		return flag;

	}

	/** Verify Statuses of the Subjects on Study Navigator Page */
	public void verifySubjectsStatusesForSubjectsOnStudyNavigator() {
		boolean flag;
		ArrayList<String> weTextArrayList = new ArrayList<>();
		for (WebElement subjectWE : subjectStatusList) {
			String subjectStatusTextWithNumbers = subjectWE.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusText1 = subjectStatusText.split("(?<=\\D)(?=\\d)");
			weTextArrayList.add(subjectStatusText1[0]);
		}
		if (weTextArrayList.contains(subjectStatusTextLIST)) {
			flag = true;
			Assert.assertTrue(flag, "Subject statuses are present");
		} else {
			flag = false;
			Assert.assertFalse(flag, "Subject statuses are not present");

		}
	}

	/**
	 * Verify Total Number of Subject's Statuses on Study Navigator and
	 * Respective Pages
	 */
	public void verifyCountsAndStatusesOfSubjects(String subjectStatus, int statusIndex) {

		boolean flag = false;
		String subjectStatusTextCount = "";
		int subjectCount;
		ArrayList<Integer> weCountArrayList = new ArrayList<>();
		ArrayList<WebElement> subjectStatusCountOnListingPageArray = new ArrayList<>();

		for (WebElement subjectWE : subjectStatusCountList) {
			String subjectStatusTextWithNumbers = subjectWE.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\\D", " ");
			int spacePos = subjectStatusText.indexOf(" ");
			if (spacePos > 0)
				subjectStatusTextCount = subjectStatusText.substring(0, spacePos);
			subjectCount = Integer.parseInt(subjectStatusTextCount);
			weCountArrayList.add(subjectCount);
		}
		for (WebElement weSubjectStatusLink : subjectStatusListLINK) {
			String subjectStatusText = weSubjectStatusLink.getText();
			if (subjectStatusText.contains(subjectStatus)) {
				clickOn(weSubjectStatusLink);
				navigateToSubjectsListing();
				waitSpinnerToBecomeInvisible();
				while (subjectStatusCountOnListingPageArray.size() <= weCountArrayList.get(statusIndex)) {
					scrollDown("100");
					subjectStatusCountOnListingPageArray.add(subjectStatusCountOnListingPage);
					_normalWait(200);
				}
				if (subjectStatusCountOnListingPageArray.size() - 1 == weCountArrayList.get(statusIndex)) {

					flag = true;
					Assert.assertTrue(flag, "Total subject status is equal to the status on dashboard");
					//navigateBack();
					break;

				} else {

					Assert.assertFalse(flag, "Total subject status is not equal to the status on dashboard");
				}
			}
		}

		// verifySelectedStudyDashBoardChartViewIsDisplayed();

	}

	public String verifyStatusCount() {
		javascripctHilightingElement(statusForNew);
		return statusForNew.getText();

	}

	public void verifyDefaultCountForStatus(String count) {
		Assert.assertTrue(statusForNew.getText().trim().contains(count));
		reportInfo();
	}

	public void verifySubjectRenameStatus(String renameAliasSubjectToBeVerified) {
		for (WebElement renameStatusList : subjectStatusList) {
			String renameStatus = renameStatusList.getText();
			String[] subjectStatusText1 = renameStatus.split("-");

			Assert.assertTrue(subjectStatusText1[0].contains(renameAliasSubjectToBeVerified));
		}
	}



	/**
	 * click on add subject button to create new subject
	 */
	public void clickOnAddSubjectBTN(String... siteNameToSelect) {
		clickOn(addSubjectBTN);
		if (isElementDisplayed(addSubjectSiteLIST)) {
			List<WebElement> dropDownSiteValues = getDropDownOptions(addSubjectSiteLIST);
			for (WebElement siteName : dropDownSiteValues) {
				if (getText(siteName).trim().contains(siteNameToSelect[0])) {
					selectDropDownByValue(siteName, siteNameToSelect[0]);
					break;
				}
			}
		}
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(addSubPopup_CancelBTN));
	}
}
