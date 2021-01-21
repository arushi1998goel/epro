package net.medavante.portal.pages.centralrating;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.selenium.core.BasePage;

public class CentralRatingAssessmentsListingPage extends BasePage {

	public CentralRatingAssessmentsListingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeBTN;

	@FindBy(xpath = "//input[@data-ma-filter-text-change='subjectNumber']")
	private WebElement searchSubjectINP;

	@FindBy(xpath = "//button[@class='btn btn-blue ng-binding']")
	private WebElement CreateSubjectBTN;

	@FindBy(xpath = "//div[contains(@id,'page-content') and @class='content ng-scope']/div[@class='row ng-scope grid-row']//div[@class='col-md-2 col-sm-2 col-xs-2'][1]//div[1]//label[contains(@class,'ng-binding')]")
	private List<WebElement> CrList;

	@FindBy(xpath = "//div[contains(@id,'page-content') and @class='content ng-scope']//div[3]//label[contains(@class,'ng-binding')]")
	private WebElement svidValue;

	@FindBy(xpath = "//div[contains(@class,'text-muted')]//span")
	private WebElement totalPages;

	@FindBy(xpath = "//div[@class = 'button-block']/a")
	private WebElement findSubjectBTN;

	@FindBy(xpath = "//button[contains(text(),'Create Subject')]")
	private WebElement findSubjectCreateSubjectBTN;

	@FindBy(xpath = "//label[text()='Find Subject']/parent::div//button[@data-ng-click='closePopup()']")
	private WebElement findSubjectCancelBTN;

	@FindBy(xpath = "//div[@class='popup-scroll find-list']/div/div/label")
	private List<WebElement> findSubjectExistingSubjectLIST;

	@FindBy(xpath = "(//div[@class = 'popupcontent']/div/button)[1]")
	private WebElement findSubjSelectStudyDRPDWN;

	@FindBy(xpath = "//div[@class = 'popupcontent']/div[@data-select-action='onSelectStudy']//div/ul//li")
	private List<WebElement> selectStudyFromStudyList;

	@FindBy(xpath = "//div[@class = 'popupcontent']/div[@data-select-action='onSelectSite']//div/ul//li")
	private List<WebElement> selectSiteFromSiteList;

	@FindBy(xpath = "(//div[@class = 'popupcontent']/div/button)[2]")
	private WebElement findSubjSelectSiteDRPDWN;

	@FindBy(xpath = "//div[@class = 'search-block']/div/input")
	private WebElement findSubjSubjectINP;

	@FindBy(xpath = "//div[@class = 'popup-scroll find-list']//div/label")
	private WebElement selectFilteredSubject;

	@FindBy(xpath = "//div[@id='page-title']/h1/span")
	private WebElement selectedSubjectNumbetrPageTitle;

	@FindBy(xpath = "//input[@data-ma-filter-text-change='studyName']")
	private WebElement studyNameToFilterINP;

	@FindBy(xpath="//*[contains(text(),'No results')]")
	private WebElement noresultsText;;

	/* temp */
	@FindBy(xpath = "//span//a[text()='New Subject page']")
	private WebElement addSubjectNewLINK;

	@FindBy(xpath = "//*[@data-ma-filter-drop-down-change='subjectVisitStatus']")
	private WebElement visitStatusDRPDWN;

	@FindBy(xpath = "//*[@data-ma-filter-drop-down-change='subjectVisitStatus']//ul/li/span")
	private List<WebElement> visitStatusList;

	@FindBy(xpath = "//div[@class='row ng-scope grid-row']")
	private WebElement rowVisit;

	@FindBy(xpath = "//div[@class='col-xs-14 col-sm-5 col-md-3 edit ng-isolate-scope']//a[@class='add-on icon-cancel']")
	private WebElement cancelDateTo;

	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-5 col-md-3 edit ng-isolate-scope']//a[@class='add-on icon-cancel']")
	private WebElement cancelDateFrom;

	@FindBy(xpath = "//div[contains(@class,' edit ng-isolate-scope') and @data-value='scheduledDateFrom']//a[contains(@class,'datepickerbutton')]")
	private WebElement schedulerFromDatePickerICN;

	@FindBy(xpath = "//div[contains(@class,' edit ng-isolate-scope') and @data-value='scheduledDateTo']//a[contains(@class,'datepickerbutton')]")
	private WebElement schedulerTODatePickerICN;

	@FindBy(xpath = "//td[@class='day active today']")
	private WebElement schedulerFromCurrentDate;

	@FindBy(xpath = "(//td[@class='day active today'])[2]")
	private WebElement schedulerToCurrentDate;

	@FindBy(xpath = "//div[@class='content ng-scope']/div")
	private List<WebElement> visitListCOLL;

	@FindBy(xpath = "//div[@class='popup-scroll find-list']//div[@class='grid-line grid-row']/label")
	private List<WebElement> dropDownSubjectList;

	@FindBy(xpath = "//button[@data-ng-click='resetToDefault()']")
	private WebElement resetToDefault;

	@FindBy(xpath = "//div[@data-value='scheduledDateFrom']//label")
	private WebElement scheduledDateFrom;

	@FindBy(xpath = "//div[@data-value='scheduledDateTo']//label")
	private WebElement scheduledDateTo;

	@FindBy(xpath = "//a[@data-ng-click='refreshList()']//span")
	private WebElement refreshIcon;

	@FindBy(xpath = "//h1[@class='ng-binding']")
	private WebElement pageStatusText;

	@FindBy(xpath = "//span[contains(@class,'icon-home')]")
	private WebElement homeIcon;

	@FindBy(xpath = "//div[contains(@class,'blue-popup')]")
	private WebElement findSubjectPopUp;

	@FindBy(xpath = "//div[@class='datepicker-days']//tr//td")
	private List<WebElement> dayLIST;

	@FindBy(xpath = "//*[@id='portal-grid-page-content']//span[contains(@class,'ellipsis ng-binding')]")
	private WebElement scheduleColVal;

	@FindBy(xpath = "//input[@data-ma-filter-text-change='visitName']")
	private WebElement filterVisitStatusINP;
	
	@FindBy(xpath = "//input[@data-ma-filter-text-change='site']")
	private WebElement filterSiteStatusINP;

	@FindBy(xpath = "//input[@data-ma-filter-text-change='svid']")
	private WebElement filterSVID;

	@FindBy(xpath = "//input[@data-ma-filter-text-change='subjectNumber']")
	private WebElement filterSubjectNumINP;

	@FindBy(xpath = "//div[@class='row ng-scope grid-row']/div//label")
	private List<WebElement> columnOfCrList;

	@FindBy(xpath = "//div[@id='portal-grid-page-header']//div/label")
	private List<WebElement> columnNameLIST;

	@FindBy(xpath = "//div[@class='row ng-scope grid-row']")
	private List<WebElement> recordRowLIST;

	@FindBy(xpath = "//h1[contains(text(),'CR Ass')]")
	private WebElement crAssessmnetHeading;

	/* Add Subject Popup Locators */

	@FindBy(xpath = "//div[contains(@class,'show in')]//h4[@data-ng-hide='isEdit' and text()='Add Subject']")
	private WebElement addSubPopup_AddSubjectTITLE;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='screeningNumber']")
	private WebElement addSubPopup_ScreeningINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//label[text()='Language']/parent::div/div/button[@class='btn btn-default dropdown-toggle ng-binding dropdown-invalid']")
	private WebElement addSubPopup_LanguageDROPDOWNFieldVAL;

	@FindBy(xpath = "//ul[@class='dropdown-menu']//li[@ng-repeat='a in cultures']/a")
	private List<WebElement> configuredLanguageLIST;

	@FindBy(xpath = "//div[contains(@class,'show in')]//button[@data-ng-click='save()']")
	private WebElement addSubPopup_SaveBTN;

	@FindBy(xpath = "//*[@data-ma-filter-drop-down-change='subjectStatus']")
	private WebElement subjectStatusDropDown;

	@FindBy(xpath = "//*[@data-ma-filter-drop-down-change='subjectStatus']//ul/li/span")
	private List<WebElement> subjectStatusListFilter;
	
	@FindBy(xpath = "//div[@id='portal-grid-page-header']")
	private WebElement potalHeader;


	public NewSubjectDetailPage clickOnSearchSubjects(String subjectName) {
		inputText(searchSubjectINP, subjectName);
		_normalWait(2000);
		for (WebElement subjectNum : CrList) {
			_waitForJStoLoad();
			_normalWait(3000);
			if (getText(subjectNum).equalsIgnoreCase(subjectName)) {
				waitAndClick(subjectNum);				
				break;
			}
		}
		new WebDriverWait(driver, 50)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);

	}

	public void verifyExistanceOfSubjectUnderSubjectNumber(String subjectName) {
		inputText(searchSubjectINP, subjectName);
		boolean flag = false;
		_normalWait(2500);
		for (WebElement subjectNum : CrList) {
			_waitForJStoLoad();
			if (getText(subjectNum).equalsIgnoreCase(subjectName)) {
				flag = true;
				Assert.assertTrue(flag, "The Subject Is Present");
				break;
			} else {
				Assert.assertFalse(flag, "The Subject list Is  Not Present");
			}
		}
		reportInfo();
	}

	/**
	 * Verify new add subject popup is displayed
	 */
	public void verifyAddSubjectPopUpIsDisplayed() {
		Assert.assertTrue(isElementPresent(addSubPopup_AddSubjectTITLE));
		reportInfo();
	}

	/**
	 * Enter text in Screening input field in add subject popup
	 * 
	 * @param inputScreeningNumToBeEntered
	 */
	public void inputScreeningNum(String inputScreeningNumToBeEntered) {
		inputText(addSubPopup_ScreeningINP, inputScreeningNumToBeEntered);
		reportInfo();
	}

	/**
	 * Select language from the drop down options to create the subject in add
	 * subject popup
	 * 
	 * @param languageToBeSelect
	 */
	public void selectSubjectLanguage(String languageToBeSelect) {
		clickOn(addSubPopup_LanguageDROPDOWNFieldVAL);
		_normalWait(100);
		for (WebElement language : configuredLanguageLIST) {
			if (getText(language).trim().equalsIgnoreCase(languageToBeSelect)) {
				waitAndClick(language);
				break;
			}
		}
	}

	/**
	 * Click on save button of new subject Popup
	 * 
	 * @return SubjectsDetailsPage
	 */
	public NewSubjectDetailPage clickOnSaveBTN() {
		clickOn(addSubPopup_SaveBTN);
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	public void loadCompletePage() {
		loadCompleteList(totalPages);
	}

	public void clickOnFindSubjectIcon() {
		waitForElement(findSubjectBTN);
		clickOn(findSubjectBTN);

	}

	/** Select Study,Site And Subject From DropDown */
	public NewSubjectDetailPage selectingStudyDropDownSiteDropDownAndSubject(String studyName, String siteName,
			String subjectName) {

		clickOn(findSubjSelectStudyDRPDWN);
         _normalWait(3000);
		for (WebElement study : selectStudyFromStudyList) {
			_waitForJStoLoad();
			if (getText(study).contains(studyName)) {
				waitAndClick(study);
				break;
			}

		}

		clickOn(findSubjSelectSiteDRPDWN);
		for (WebElement site : selectSiteFromSiteList) {
			_waitForJStoLoad();
			if (getText(site).contains(siteName)) {
				clickOn(site);
				break;
			}
		}

		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'search-block']/div/input")));
		inputText(findSubjSubjectINP, subjectName);
		_waitForJStoLoad();
		clickOn(selectFilteredSubject);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);

	}

	/** Select Study,Site From DropDown */
	public void selectStudyAndSiteFromDropDown(String studyName, String siteName) {

		clickOn(findSubjSelectStudyDRPDWN);
	  _normalWait(3000);

		for (WebElement study : selectStudyFromStudyList) {
			_waitForJStoLoad();
			if (getText(study).contains(studyName)) {
				clickOn(study);
				break;
			}

		}

		clickOn(findSubjSelectSiteDRPDWN);
		for (WebElement site : selectSiteFromSiteList) {
			_waitForJStoLoad();
			if (getText(site).contains(siteName)) {
				clickOn(site);
				break;
			}
		}

	}

	/** Select Subject Name After Selecting The Study And site Form DropDown */
	public NewSubjectDetailPage selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(String subjectName) {

		inputText(findSubjSubjectINP, subjectName);
		_waitForJStoLoad();
		clickOn(selectFilteredSubject);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);

	}

	/**
	 * Click on create subject button to create new subject for selected study
	 * 
	 * @return Object of subject detail page
	 */
	public NewSubjectDetailPage clickOnCreateSubjectBtnForSelectedStudyAndSubject() {
		waitAndClick(findSubjectCreateSubjectBTN);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/**
	 * Veriy Subject name Not exist in find subject list for selected study and
	 * site
	 * 
	 * @param subjectToBeVerified
	 */
	public void verifySubjectNameNotDisplayedInFindSubjectList(String subjectToBeVerified) {
		inputText(findSubjSubjectINP, subjectToBeVerified);
		Assert.assertTrue(noresultsText.isDisplayed());
	}

	/**
	 * Verify Create Subject button is not displayed
	 */
	public void verifyCreateSubjectBtnIsNotDisplayed() {
		Assert.assertFalse(findSubjectCreateSubjectBTN.isDisplayed());
		reportInfo();
	}

	/**
	 * Close Find subject popup
	 */
	public void closeFindSubjectPopup() {
		clickOn(findSubjectCancelBTN);
	}

	/** Verify Subject List Is displayed after Selecting Study From DropDown */
	public void verifySubjectListDisplayedAfterSelectingStduyAndSiteFromDrpDown() {

		boolean flag = false;
		if (dropDownSubjectList.size() > 0) {

			flag = true;
			Assert.assertTrue(flag, "The Subject list Is Present");

		} else {
			Assert.assertFalse(flag, "The Subject list Is  Not Present");
		}
	}

	/** Verify Subject Without Schedule Cr Visit Present In List */
	public void verifySubjectWithoutScheduleCrVisitPresentInList(String subjectName) {

		_normalWait(1000);
		 inputText(findSubjSubjectINP,subjectName);
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[contains(@data-ng-repeat,'subject in model.subjects')])[1]")));
		for (WebElement subjectLabelName : dropDownSubjectList) {
			boolean flag = false;
			if (getText(subjectLabelName).contains(subjectName)) {
				flag = true;
				Assert.assertTrue(flag, "The Subject Is Present");
				break;

			}
		}
	}

	public void verifyChangeReflectionAfterSelectingStudyAndSubject(String subjectName) {
		boolean flag = false;
		_normalWait(2000);
		if (subjectName.equalsIgnoreCase(selectedSubjectNumbetrPageTitle.getText())) {
			flag = true;
			Assert.assertTrue(flag, "Subject deatil page opened for the " + subjectName + " with all details");
		}
	}

	/** Search Visit By Status */
	public void searchByVisitStatus(String visitStatus) {
		clickOn(visitStatusDRPDWN);
		for (WebElement status : visitStatusList) {
			if (status.getText().equalsIgnoreCase(visitStatus)) {
				waitAndClick(status.findElement(By.xpath("./parent::li")));
				break;
			}
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		}

	}

	public CentralRatingAppointmentPage clickOnVisitRow() {
		
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner ng-scope']"));
		for (WebElement vistEle : visitListCOLL) {
			waitAndClick(vistEle);
			break;
		}
		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);
	}

	public NewSubjectDetailPage navigateTonewSubjectPage() {
		clickOn(addSubjectNewLINK);
	//	_normalWait(1000);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/**
	 * Navigate to Central Rating Appointment Page
	 * 
	 * @return
	 */
	public CentralRatingAppointmentPage navigateToCentralRatingAppointmentPage() {

		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);

	}

	/** Cancel Schedular Date By Clicking On Cancel Icon */

	public void clickOnSchedularCancelIcon() {

		waitAndClick(cancelDateFrom);
		waitAndClick(cancelDateTo);
		new WebDriverWait(driver, 50)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		
	}

	/** Select Todays Date From Datepicker */

	public void selectScheduledDateFromToForToday() {
		waitAndClick(schedulerFromDatePickerICN);
		waitAndClick(schedulerFromCurrentDate);
		waitAndClick(schedulerTODatePickerICN);
		waitAndClick(schedulerToCurrentDate);
		waitSpinnerToBecomeInvisible();
	}

	/** Search Visit By Visit Input Filter */
	public void sortByVisitName(String visitNameToBeSearch) {
		inputText(filterVisitStatusINP, visitNameToBeSearch);
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		reportInfo();
	}

	public void sortBySubjectName(String subjectNameToBeSearch) {
		inputText(filterSubjectNumINP, subjectNameToBeSearch);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		reportInfo();
	}
	
	/** Search Visit By Visit Input Filter */
	public void sortBySiteName(String sitetNameToBeSearch) {
		inputText(filterSiteStatusINP, sitetNameToBeSearch);
		new WebDriverWait(driver, 40)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		reportInfo();
	}

	/** Sort CR list by study name */
	public void sortByStudyName(String studyNameToBeSearch) {

		inputText(studyNameToFilterINP, studyNameToBeSearch);
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		reportInfo();

	}

	/** Click On Row Of the Visit */
	public CentralRatingAppointmentPage clickOnRowOfVisitByName(String crListVisitName) {
		_waitForJStoLoad();
		outerloop: for (int crAssesmentRow = 0; crAssesmentRow < visitListCOLL.size(); crAssesmentRow++) {
			for (int crAssesementColumn = crAssesmentRow; crAssesementColumn < columnOfCrList
					.size(); crAssesementColumn++) {
				_normalWait(1000);
				WebElement visitNameCrList = columnOfCrList.get(crAssesementColumn);
				if (visitNameCrList.getText().equalsIgnoreCase(crListVisitName)) {
					waitAndClick(visitNameCrList.findElement(By.xpath("./parent::div")));
					break outerloop;
				}
			}
		}
		waitSpinnerToBecomeInvisible();
		return (PageFactory.initElements(driver, CentralRatingAppointmentPage.class));
	}

	/**
	 * Click on row on the basis of column name
	 * 
	 * @param columnName
	 * @param rowValue
	 */
	public NewSubjectDetailPage clickOnRowByColumnAndRowValue(String columnName, String rowValue) {
		for (int columnNameIndex = 0; columnNameIndex < columnNameLIST.size(); columnNameIndex++) {
			if (getText(columnNameLIST.get(columnNameIndex)).trim().equalsIgnoreCase(columnName)) {
				int columnNameIndex1 = columnNameIndex + 1;
				for (int recordRow = 0; recordRow < recordRowLIST.size(); recordRow++) {

					if (getText(recordRowLIST.get(recordRow)
							.findElement(By.xpath("./div[" + columnNameIndex1 + "]//label"))).trim()
									.equalsIgnoreCase(rowValue)) {
						clickOn(recordRowLIST.get(recordRow).findElement(By.xpath("./div[" + columnNameIndex1 + "]")));
						break;
					}

				}
				break;
			}
		}

		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/**
	 * verify row value on the basis of column name
	 * 
	 * @param columnName
	 * @param rowValue
	 */
	public void verifyRowValueAccordingToColumnValue(String columnName, String rowValue) {

		boolean flag = false;
		for (int columnNameIndex = 0; columnNameIndex < columnNameLIST.size(); columnNameIndex++) {
			if (getText(columnNameLIST.get(columnNameIndex)).trim().equalsIgnoreCase(columnName)) {
				int columnNameIndex1 = columnNameIndex + 1;
				for (int recordRow = 0; recordRow < recordRowLIST.size(); recordRow++) {
					if (getText(recordRowLIST.get(recordRow)
							.findElement(By.xpath("./div[" + columnNameIndex1 + "]//label"))).trim()
									.equalsIgnoreCase(rowValue)) {
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

	public MedAvantePortalPage navigateToMedavantedashBoard() {
		clickOn(homeBTN);
		return PageFactory.initElements(driver, MedAvantePortalPage.class);
	}

	/** Verify Page title */
	public void verifyCRAssessmentsPage() {
		waitForElementPresent(crAssessmnetHeading, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementPresent(crAssessmnetHeading));
		reportInfo();
	}

	/* Validate Scheduled Date From */

	public void verifyScheduleDateFrom() {

		String date = currentDate();
		String[] splitString = getText(scheduledDateFrom).split("\\s");
		Assert.assertTrue(splitString[0].equalsIgnoreCase(date));

	}

	/* validate Scheduled Date to */

	public void verifyScheduleDateTo() {

		String date = currentDate();
		String[] splitString = getText(scheduledDateTo).split("\\s");
		Assert.assertTrue(splitString[0].equalsIgnoreCase(date));

	}

	public void clickRefreshIcon() {
		waitAndClick(refreshIcon);
		waitSpinnerToBecomeInvisible();

	}

	public void clickHomeIcon() {
		waitAndClick(homeIcon);

	}

	/* validate Find Subject dialog is opened. */

	public void verifyFindSubjectDialog() {

		Assert.assertTrue(isElementPresent(findSubjectPopUp));

	}

	public void verifyResetToDefaultButton() {

		WebElement resetToDefault = driver.findElement(
				By.xpath("//button[contains(@data-ng-disabled,'Enabled()') and not(contains(@disabled,'disabled'))]"));
		Assert.assertTrue(isElementPresent(resetToDefault));
		if (resetToDefault.isEnabled()) {
			Assert.assertTrue(true, "Reset To Default Button Is Enabled");
		} else {

			Assert.assertFalse(false, "Reset To Default Button Is Disabled");

		}
	}

	public void clickResetToDefault() {

		waitAndClick(resetToDefault);

	}

	public void verifyScheduleDateData() {
		_normalWait(3000);
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='row ng-scope grid-row']"));
		int rows_count = rows.size();
		for (int i = 0; i < rows_count; i++) {
			String scheduledDateText = scheduleColVal.getText();
			if (scheduledDateText.trim().equalsIgnoreCase(currentDate())) {

			}
		}

	}

	// get SVID value

	/**
	 * Click on auto generate button to generate auto generate temp ID
	 */
	public String svidValue() {
		waitForWebElementEnable(svidValue, 10);
		return svidValue.getText();
	}

	/** Search Visit By Visit Input Filter */
	public void searchBySVID(String svid) {
		inputText(filterSVID, svid);
		waitSpinnerToBecomeInvisible();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
		reportInfo();
	}

	public void verifyPageTitle(String title) {
		WebElement pageTitle = driver.findElement(By.xpath("//*[@title='" + title + "']"));
		Assert.assertTrue(isElementNotVisible(pageTitle), "Page title is displaying");
	}

	public void verifyDataOnSubjcetGeneralSection(String value) {
		WebElement pageTitle = driver
				.findElement(By.xpath("//div[@class='subject-general-section']//span[text()='" + value + "']"));
		Assert.assertTrue(isElementDisplayed(pageTitle), "Correct value is displaying");

	}

	public void verifyValueOnSubjcetDetailPage(String value) {
		WebElement desiredValue = driver.findElement(By.xpath("//label[text()='" + value + "']"));
		Assert.assertTrue(isElementDisplayed(desiredValue), "Correct value is displaying");

	}

	public void verifySortedListDisplayedForSelectedVisitStatus(String studyName, String visitStatus) {

		clickOnSchedularCancelIcon();
		sortByStudyName(studyName);
		searchByVisitStatus(visitStatus);
		waitSpinnerToBecomeInvisible();
		//_normalWait(500);

	}

	public void debug(String Subjectname) throws InterruptedException {
		WebElement ele = null;
		int flag = 0;
		do {
			try {
				ele = driver.findElement(By.xpath(
						"//div[@id='portal-grid-page-content']/div/div[1]//label[text()='" + Subjectname + "']"));
				scrollDown("70");
				moveToElement(ele);
				clickOn(ele);
				flag = 1;
				break;
			} catch (Throwable e) {
				scrollDown("100");
				Thread.sleep(600);
			}
		} while ((flag == 0) && driver.findElements(By.xpath("//div[@class='row text-center text-muted']")).size() > 0);
		if (flag == 1) {
			System.out.println("Element has been found.!!");

		} else {
			System.out.println("Element has not been found.!!");
		}

	}

	public NewSubjectDetailPage navigateToNewSubjectDetailPage() {
		clickOn(addSubjectNewLINK);
		_normalWait(1000);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);

	}

	/** Verify subject Status Present in Subject Status Filter */
	public void verifySubjectStatusPresentInList(String subjectStatus) {
		boolean flag = false;
		for (WebElement status : subjectStatusListFilter) {
			if (status.getText().equalsIgnoreCase(subjectStatus)) {
				moveToElement(status);
				flag = true;
				break;
			}
			waitSpinnerToBecomeInvisible();
		}
		Assert.assertTrue(flag);
		clickOn(subjectStatusDropDown);
		reportInfo();

	}

	/* Click On Subject Status Filter DropDown */
	public void clickOnSubjectStatusFilterDropDown() {
		clickOn(subjectStatusDropDown);
	}
	
	/**
	 * verify rating page is displayed
	 */
	
	public void verifyRatingPagrIsDisplayed() {
		Assert.assertTrue(potalHeader.isDisplayed());
		reportInfo();

	}

}
