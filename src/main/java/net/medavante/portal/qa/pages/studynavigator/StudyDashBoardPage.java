
package net.medavante.portal.qa.pages.studynavigator;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.StudyModuleConstants;

public class StudyDashBoardPage extends BasePage {

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-active']")
	private WebElement selectStudyDRPDWN;

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-active']//span[@id='selectedStudy']")
	private WebElement selectedStudyNameText;

	@FindBy(xpath = "//div[@id='NewStudyComponent']")
	private WebElement studyNavigatorGrid;

	@FindBy(xpath = "//div[@id='NewStudyComponent' and @class='new-study-component sidebar-opened']//ul[@class='aside-nav-list']//li//span[@class='f-text']")
	private List<WebElement> filterPanelIconMenuTitleList;

	@FindBy(xpath = "//div[@id='NewStudyComponent']//ul[@class='aside-nav-list']//li")
	private List<WebElement> filterList;

	@FindBy(xpath = "//div[@class='study-title']//strong")
	private WebElement selectedFilterGridTitleText;

	@FindBy(xpath = "//div[@class='study-filter-holder']//h3")
	private List<WebElement> filterByBoldText;

	@FindBy(xpath = "//div[@id='NewStudyComponent']//button[@class='nav-toggler']")
	private WebElement sidePanelTogglerButton;

	@FindBy(xpath = "//div[@id='NewStudyComponent']//div[@class='study-filter-holder']")
	private WebElement filterBlock;

	@FindBy(xpath = "//div[@id='NewStudyComponent']//div[@class='study-filter-holder']//li")
	private List<WebElement> studyFilterList;

	@FindBy(xpath = "//div[@id='NewStudyComponent']//div[@class='study-content-wrap']")
	private WebElement subjectContentGrid;

	@FindBy(xpath = "//div[@class='wrapper-dashboard-btn']//ul[@class='dropdown-menu']")
	private WebElement searchStudyDRPDWN;
	
	@FindBy(xpath = "//div[@class='dropdown-menu']//ul[@class='dropdown-menu']/li/span")
	private List<WebElement> studyLIST;

	@FindBy(xpath = "//div[@data-items='selectedStudy.sites']")
	private WebElement selectSiteDRPDWN;

	@FindBy(xpath = "//div[@data-items='selectedStudy.sites']//li/span")
	private List<WebElement> AvailableSiteList;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']//ul[@class='dropdown-menu']")
	private WebElement searchSiteDRPDWN;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']//ul[@class='dropdown-menu']//li")
	private List<WebElement> siteList;

	@FindBy(xpath = "//div[@class='dashboard-wrapper-col charts-view']")
	private WebElement studyChartViewDashBoard;

	@FindBy(xpath = "(//canvas[@class='chart chart-base ng-scope ng-isolate-scope'])[1]")
	private WebElement studySubjectGraph;

	@FindBy(xpath = "(//canvas[@class='chart chart-base ng-scope ng-isolate-scope'])[2]")
	private WebElement studyVisitGraph;

	@FindBy(xpath = "(//canvas[@class='chart chart-base ng-scope ng-isolate-scope'])[3]")
	private WebElement studyAssessmentGraph;

	@FindBy(xpath = "//div[@data-ng-if='isCardsViewActive()']")
	private WebElement studyCardViewDashBoard;

	@FindBy(xpath = "//button[@data-ng-class='currentViewModeCssClass']")
	private WebElement viewModeBTN;

	@FindBy(xpath = "//span[@class='f-text' and text()='Subjects']")
	private WebElement subjects;

	@FindBy(xpath = "//span[@class='f-text' and text()='Visits']")
	private WebElement visits;

	@FindBy(xpath = "//span[@class='f-text' and text()='Assessments']")
	private WebElement assessments;

	@FindBy(xpath = "//span[@class='f-text' and text()='Medications']")
	private WebElement medications;

	@FindBy(xpath = "//div[@id='sideNavArea']//span[contains(text(),'Messages')]")
	private WebElement messages;

	@FindBy(xpath = "//div[@class='chart-legend'][1]/a[1]")
	private WebElement subjectNewToolTip;

	@FindBy(xpath = "//div[@class='chart-legend'][1]/a[2]")
	private WebElement subjectScreenedToolTip;

	@FindBy(xpath = "//div[@class='chart-legend'][1]/a[3]")
	private WebElement subjectScreenedFailedToolTip;

	@FindBy(xpath = "//div[@class='row row-eq-height ng-scope']/div[1]//div[@class='chart-legend'][1]/a[4]")
	private WebElement subjectDisContinueToolTip;

	@FindBy(xpath = "//div[contains(@class,'col-xs-24 dashboard-columns')][1]//div[@class='row ng-scope'][1]/a//span[contains(@class,'dashboard-tile-descriptor')]")
	private WebElement subjectNewCardStatus;

	@FindBy(xpath = "//div[contains(@class,'col-xs-24 dashboard-columns')][1]//div[@class='row ng-scope'][2]/a//span[contains(@class,'dashboard-tile-descriptor')]")
	private WebElement subjectScreenedCardStatus;

	@FindBy(xpath = "//div[contains(@class,'col-xs-24 dashboard-columns')][1]//div[@class='row ng-scope'][3]/a//span[contains(@class,'dashboard-tile-descriptor')]")
	private WebElement subjectScreenedFailedStatus;

	@FindBy(xpath = "//div[contains(@class,'col-xs-24 dashboard-columns')][1]//div[@class='row ng-scope'][4]/a//span[contains(@class,'dashboard-tile-descriptor')]")
	private WebElement subjectDisContinueStatus;

	@FindBy(xpath = "//span[@class='icon-small icon-home']")
	private WebElement homeIcon;

	@FindBy(xpath = "//*[contains(@class,'filters-group')]/*[contains(text(),'Status')]/parent::div//span")
	private List<WebElement> subjectStatusList;

	@FindBy(xpath = "(//div[@class='chart-legend'])[1]/a//span[@class='scale-records-count pull-right ng-binding ng-scope']")
	private List<WebElement> subjectStatusCountList;

	@FindBy(xpath = "(//div[@class='chart-legend'])[1]/a")
	private List<WebElement> subjectStatusListLINK;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//div[@class='row grid-row ng-scope']")
	private WebElement subjectStatusCountOnListingPage;

	@FindBy(xpath = "//a[contains(@title,'any visits')]//span[contains(@data-ng-if,'valuesByScales')]")
	private WebElement statusForNew;

	@FindBy(css = "kendo-grid-group-panel[class='k-grouping-header k-grouping-header-flex']")
	private WebElement groupingArea;

	@FindBy(xpath = "//strong[contains(text(),'Assessments: All')]")
	private WebElement assessmentCountHeaderTitle;

	@FindBy(css = "td[class='ng-star-inserted'] a")
	private WebElement searchLinkinFstCell;

	/// Visits Locators//

	@FindBy(xpath = "//strong[contains(text(),'Visits: All')]")
	private WebElement visitCount;

	@FindBy(xpath = "//div[@class='k-grid-header-wrap']")
	private WebElement columnGridHeader;

	@FindBy(xpath = "//kendo-pager[contains(@class,'k-pager-wrap')]//kendo-pager-info")
	private WebElement pageTotalVisitsCount;

	@FindBy(xpath = "//*[text()=' No records available. ']")
	private WebElement recordPage;

	private ArrayList<String> subjectStatusTextLIST = new ArrayList<>(
			Arrays.asList("New", "Screened", "ScreenFailed", "Enrolled", "Completed", "Discontinued"));

	@FindBy(xpath = "//th[@class='k-header k-filterable']//a[contains(text(),'Subject')]//preceding-sibling::kendo-grid-filter-menu//span[@class='k-icon k-i-filter']")
	private WebElement filterICON_Sub;

	@FindBy(xpath = "//th[@class='k-header k-filterable']//a[contains(text(),'Visit')]//preceding-sibling::kendo-grid-filter-menu//span[@class='k-icon k-i-filter']")
	private WebElement filterICON_Visit;

	@FindBy(xpath = "//span[text()='Subject']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement FilterICON_Sub;

	@FindBy(xpath = "//span[text()='Visit']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement FilterICON_Visit;

	@FindBy(xpath = "//span[text()='Status']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement FilterICON_Status;

	@FindBy(xpath = "//span[text()='Assessment']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement FilterICON_Assessment;

	@FindBy(xpath = "//span[text()='Medication']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement FilterICON_Medication;

	@FindBy(xpath = "div[class='k-filter-menu-container']")
	private List<WebElement> statusList;

	@FindBy(css = "div[class='k-filter-menu-container'] kendo-grid-string-filter-menu>kendo-grid-string-filter-menu-input:first-child input")
	private WebElement filterSearchBox;

	@FindBy(xpath = "//button[contains(text(),'Filter')]")
	private WebElement filterSearchButton;

	// @FindBy(css = "td[ng-reflect-column-index='0'] a")
	// private WebElement searchLinkinFstCell;

	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[text()='Study Profile']")
	private WebElement studyProfileSliderPanelOpen;

	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[contains(text(),'Raters')]")
	private WebElement ratersProfileSliderPanelOpen;

	@FindBy(xpath = "//div[@class='queries-dialog-header-title']")
	private WebElement queriesSliderPanelOpen;

	@FindBy(xpath = "//div[@class='row header-slide-study']//a[@title='Close']")
	private WebElement studyProfileCollpaseBTN;

	@FindBy(xpath = "//div[@data-ng-controller='RatersListController']//a[@title='Close']")
	private WebElement raterCollpaseBTN;

	@FindBy(xpath = "//div[@class='queries-dialog-content']//a[@title='Close']")
	private WebElement queriesCollpaseBTN;

	@FindBy(xpath = "//a[@title='Study Profile']")
	private WebElement studyProfileLinkICN;

	@FindBy(xpath = "//a[@title='Raters']")
	private WebElement ratersLinkICN;

	@FindBy(xpath = "//a[@title='Show queries']")
	private WebElement queriesLinkICN;

	@FindBy(xpath = "//a[@title='Raters']/span[2]")
	private WebElement ratersCount;

	@FindBy(xpath = "//div[@data-ng-controller='RatersListController']//div[@class='slide-panel-header']/h1")
	private WebElement ratersCountOnSlidePanel;

	@FindBy(xpath = "//div[@class='ng-scope']/label[@class='ng-binding ng-scope']")
	private List<WebElement> ratersList;

	@FindBy(xpath = "//table[@class='k-grid-table']//tbody//tr")
	private List<WebElement> rowofGridTableList;

	@FindBy(xpath = "//div[@data-select-action='siteSelected']//span[@id='selectedStudy']")
	private WebElement siteSelectedText;

	@FindBy(xpath = "//div[@data-select-action='studySelected']//span[@id='selectedStudy']")
	private WebElement chooseStudyText;

	/*
	 * 
	 * Subject Listing locators and functions
	 * 
	 */

	@FindBy(xpath = "//a[@title='Add Subject']")
	private WebElement addSubjectBTN;

	@FindBy(css = "a[title='Refresh'][class='circle-button btn btn-white']")
	private WebElement refreshBTN;

	@FindBy(xpath = "//select[@title='Choose Site']")
	private WebElement addSubjectSiteLIST;

	@FindBy(xpath = "//select[@title='Choose Site']/option")
	private List<WebElement> subjectSiteList;

	@FindBy(xpath = "//div[contains(@class,'show in')]//div[@class='modal-footer']/button[@data-ng-click='close()']")
	private WebElement addSubPopup_CancelBTN;

	@FindBy(xpath = "//div[contains(@class,'show in')]//h4[@data-ng-hide='isEdit' and text()='Add Subject']")
	private WebElement addSubPopup_AddSubjectTITLE;

	@FindBy(xpath = "//div[contains(@class,'show in')]//form[@name='subjectForm' and contains(@class,'ng-invalid ng-invalid-required')]")
	private WebElement addSubPopup_RequiredScreeningINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//label[text()='Language']/parent::div/div/button[@class='btn btn-default dropdown-toggle ng-binding dropdown-invalid']")
	private WebElement addSubPopup_LanguageDROPDOWNFieldVAL;

	@FindBy(xpath = "//div[contains(@class,'show in')]//textarea[@id='notes-input']")
	private WebElement addSubPopup_NotesINP;

	@FindBy(xpath = "//div[contains(@id,'edit-subject-dialog')]//button[@data-ng-click='save()']")
	private WebElement addSubPopup_SaveBTN;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='screeningNumber']")
	private WebElement addSubPopup_ScreeningINP;

	@FindBy(xpath = "//ul[@class='dropdown-menu']//li[@ng-repeat='a in cultures']/a")
	private List<WebElement> configuredLanguageLIST;

	@FindBy(css = "th[role='columnheader'] span")
	private List<WebElement> headerColumn;

	@FindBy(css = "th[role='columnheader'] span[mattooltip]")
	private List<WebElement> headerColumnText;

	@FindBy(css = "thead[role='presentation'] tr[aria-rowindex='1'] th")
	private List<WebElement> columnHeaderList;

	@FindBy(css = "thead[role='presentation'] tr[aria-rowindex='2'] th")
	private List<WebElement> subColumnHeaderList;

	@FindBy(xpath = "//tr[@role='row']//td[1]/a")
	private List<WebElement> FirstColumnList;

	@FindBy(xpath = "//tr[@role='row'][1]/td[1]/p[@class='k-reset']")
	private WebElement firstColumnAfterLablesInCollapsedView;

	@FindBy(xpath = "//div[contains(@class,'show in')]//label[text()='Language']/parent::div//button")
	private WebElement addSubPopup_SelectedSubjectLanguage;

	@FindBy(xpath = "//div[@class='modal fade smart-modal modal-form-window']")
	private WebElement closedAddSubPopup;

	@FindBy(xpath = "//strong[contains(text(),'Subjects: All')]")
	private WebElement subjectCount;

	@FindBy(xpath = "//strong[contains(text(),'All')]")
	private WebElement allCountCounter;

	@FindBy(xpath = "//div[@class='k-grid-header-wrap']//span[text()='Subject']/parent::a/preceding-sibling::*//a/span[@class='k-icon k-i-filter']")
	private WebElement subjectListSubjectSearchFilterBTN;

	@FindBy(xpath = "//div[@class='k-grid-header-wrap']//a[text()='Visit']/preceding-sibling::*//a/span[@class='k-icon k-i-filter']")
	private WebElement subjectListVisitFilterBTN;

	@FindBy(xpath = "//table[@class='k-grid-table']/tbody/tr/td[1]//a")
	private WebElement firstColumnFilterOption;

	@FindBy(xpath = "(//input[contains(@class,'k-textbox')])[1]")
	private WebElement filterINPBOX;

	@FindBy(xpath = "//div[contains(@class,k-action-buttons)]//button[text()='Filter']")
	private WebElement inpSearchfilterBTN;

	@FindBy(xpath = "//div[contains(@class,k-action-buttons)]//button[text()='Clear']")
	private WebElement inpSearchClearBTN;

	@FindBy(xpath = "//table[@class='k-grid-table']/tbody/tr/td[1]//a")
	private List<WebElement> gridTableFirstColumnList;

	@FindBy(css = "table[class='k-grid-table'] tbody tr")
	private List<WebElement> gridTableRowList;

	@FindBy(xpath = "//table[@class='k-grid-table']/tbody//td")
	private WebElement gridTableWithNoRecords;

	@FindBy(xpath = "//kendo-grid-list[contains(@class,'k-grid-container')]//table/tbody/tr/td[4]")
	private List<WebElement> visitStatusList;

	@FindBy(xpath = "//kendo-grid-list[contains(@class,'k-grid-container')]//table/tbody/tr/td[3]")
	private List<WebElement> subjectStatusListinGridTable;

	@FindBy(xpath = "//div[@class='k-grid-content k-virtual-content']")
	private WebElement contentGrid;

	@FindBy(xpath = "//div[@class='temporary-id-generation ng-scope']/label/b")
	private WebElement addSubPopup_GenerateTemporaryIDTXT;

	@FindBy(xpath = "//div[@class='temporary-id-generation ng-scope']//div[@title='Generate TemporaryID']")
	private WebElement addSubPopup_GenerateTemporaryIDBTN;

	@FindBy(xpath = "//input[@name='temporaryId']")
	private WebElement addSubPopup_TempINP;

	@FindBy(xpath = "//h1[contains(text(),'Subject:')]/span[@class='ng-binding']")
	private WebElement subjcetNumberValue;

	@FindBy(xpath = "//label[contains(@class,'temporary-id')]/a")
	private WebElement subjcetNumberValueOnPage;

	@FindBy(xpath = "//div[@class='temporary-id-generation ng-scope']//div[@title='Remove Autogenerated TemporaryID']")
	private WebElement addSubPopup_AutoGenerateTempCancelBTN;

	@FindBy(xpath = "//div[contains(@class,'show in')]//form[@name='subjectForm' and contains(@class,'ng-valid ng-valid-required')]")
	private WebElement addSubPopup_NonRequiredScreeningINP;

	@FindBy(xpath = "//div[@class='error-container']/label")
	private WebElement headerErrorTXT;

	@FindBy(xpath = "//div[@class='error-container']//div[@class='close-button-white']")
	private WebElement headerErrorCloseBtN;

	@FindBy(xpath = "//div[@id='breadcrumbs']/a[@class='home']")
	private WebElement homeICN;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='randomizationNumber']")
	private WebElement addSubPopup_RandomizationINP;

	@FindBy(xpath = "//td[text()=' No records available. ']")
	private WebElement noRecordContentText;

	@FindBy(xpath = "//div[@class='modal-dialog']//h4[@class='modal-title']")
	private WebElement EmailModalWindow;

	@FindBy(xpath = "//div[@class='modal-dialog']//div[@class='modal-footer']//button[@data-ng-click='closeMessageDialog()']")
	private WebElement EmailModalWindow_close;

	// Medication Filter Locators
	@FindBy(xpath = "//*[contains(@class,'filters-group')]//ul//li//span")
	private List<WebElement> menuitemFilterList;

	private ArrayList<String> medicationAndQuestionnairesEventFilterLIST = new ArrayList<>(
			Arrays.asList("All", "Today", "PastWeek", "Flagged"));

	@FindBy(xpath = "//h2[text()='Filter By']")
	private WebElement FilterByTxtBlock;

	@FindBy(xpath = "//*[contains(@class,'filters-group')]//span[contains(text(),'All')]//preceding-sibling::input")
	private WebElement allFilterOptionSelected;

	@FindBy(xpath = "//div[@id='maMedicationInfoDialog']//strong[text()='Medication']")
	private WebElement medicationPopUp;

	@FindBy(xpath = "//div[@id='maMedicationInfoDialog']//button[@class='btn btn-default' and @data-ng-click='closeMedicationDialog()']")
	private WebElement medicationPopUpCloseButton;

	@FindBy(xpath = "//div[@id='maMedicationInfoDialog']//div[@class='pull-right']//following-sibling::span[@class='ng-binding']")
	private WebElement medicationLastActionStatus;

	@FindBy(xpath = "//div[@id='maQuestionnaireInfoDialog']//strong[text()='Questionnaire']")
	private WebElement questionnairesPopUp;

	@FindBy(xpath = "//div[@id='maQuestionnaireInfoDialog']//button[@class='btn btn-default' and @data-ng-click='closeQuestionnaireDialog()']")
	private WebElement questionnairesPopUpCloseButton;

	@FindBy(xpath = "//div[@id='maLogEventInfoDialog']//strong[text()='LogEvent']")
	private WebElement EventPopUp;

	@FindBy(xpath = "//div[@id='maLogEventInfoDialog']//button[@class='btn btn-default' and @data-ng-click='closeLogEventDialog()']")
	private WebElement EventPopUpCloseButton;

	@FindBy(xpath = "//*[contains(@class,'study-content-holder')]//strong[contains(text(),'Medications: Today')]")
	private WebElement medicationTodayListBlock;

	// Study-filter-holder Locators

	@FindBy(css = "div[class='study-filter-holder'] div")
	private List<WebElement> filterGroupList;

	@FindBy(css = "div[class='study-filter-holder'] span")
	private List<WebElement> filterItemList;

	// Page Item locators in footer

	@FindBy(css = "ul[class='k-pager-numbers k-reset'] a[ng-reflect-ng-class='[object Object]']")
	private List<WebElement> footerDisplayedPageNumers;

	@FindBy(css = "kendo-pager-prev-buttons span[aria-label='Go to the first page']")
	private WebElement footerGoToFirstPageArrowButton;

	@FindBy(css = "kendo-pager-prev-buttons span[aria-label='Go to the previous page']")
	private WebElement footerGoToPreviousPageArrowButton;

	@FindBy(css = "kendo-pager-prev-buttons span[aria-label='Go to the last page']")
	private WebElement footerGoToLastPageArrowButton;

	@FindBy(css = "kendo-pager-next-buttons span[aria-label='Go to the next page']")
	private WebElement footerGoToNextPageArrowButton;

	@FindBy(css = "kendo-pager-page-sizes[class='k-pager-sizes k-label'] select")
	private WebElement footerPageSizeDropDownList;

	@FindBy(css = "kendo-pager-info")
	private WebElement footerTotalAvailableItemContainsOnPage;

	@FindBy(xpath = "//div[@id='page-title']/h1")
	private WebElement newSubjectDetailPageHeader;

	// Locators for new added functionality on studyNavigatorDashboardPage

	@FindBy(xpath = "//div[@class='cdk-overlay-container']//input[@placeholder='Search']")
	private WebElement searchStudy;

	@FindBy(xpath = "//div[@class='cdk-overlay-container']//div[@class='item ng-star-inserted']")
	private WebElement selectStudy;

	@FindBy(xpath = "//div[@class='column form-container']//input[@formcontrolname='siteQuery']")
	private WebElement searchSite;

	@FindBy(xpath = "//div[@class='list-container ng-star-inserted']//div[@class='item ng-star-inserted']")
	private WebElement selectSite;

	@FindBy(xpath = "//div[@class='ma-dialog-actions']//button[text()='Select']")
	private WebElement selectButtonForStudyAndSite;

	@FindBy(xpath = "//div[@class='list-container ng-star-inserted']//div[@class='item ng-star-inserted']")
	private List<WebElement> studyList;

	@FindBy(xpath = "//div[@class='list-container ng-star-inserted']//div[@class='item ng-star-inserted']")
	private List<WebElement> siteLists;

	/* New Locators */
	@FindBy(xpath = "//*[@class='aside-nav-wrap']//ul//li//*[@class='f-text']")
	private List<WebElement> mainFilltersTabsList;

	/* Select Study Profile Icon On BreadCrumbs */

	public void clickOnStudyProfileIcon() {
		clickOn(studyProfileLinkICN);
	}

	/**
	 * Verify Option to view Study Profile is available
	 */

	public void verifyOptionToViewStudyProfileDisplayed() {
		Assert.assertTrue(isElementDisplayed(studyProfileLinkICN));
		moveToElement(studyProfileLinkICN);
		reportInfo();
	}

	/* Select Raters Icon */

	public void verifyRatersOptionDisplay() {

		Assert.assertTrue(isElementPresent(ratersLinkICN));
		moveToElement(ratersLinkICN);
	}

	public void clickOnRatersIcon() {
		clickOn(ratersLinkICN);
	}

	/* Select Raters Close Icon */

	public void clickOnRatersCollpaseIcon() {
		clickOn(raterCollpaseBTN);
	}

	/* Select Queries Icon */
	public void clickOnQueriesIcon() {
		clickOn(queriesLinkICN);
	}

	/* Select Queries Close Icon */

	public void clickOnQueriesCollpaseIcon() {
		clickOn(queriesCollpaseBTN);
	}

	/* Verify Raters Panel Displayed */
	public void verifyRatersDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(ratersProfileSliderPanelOpen), "Raters Details Slider panel is displayed");
		reportInfo();

	}

	/* Verify Queries Panel Displayed */
	public void verifyQueriesDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(queriesSliderPanelOpen), "Query Details Slider panel is displayed");
		reportInfo();

	}

	/**
	 * verify raters count value
	 */
	public String ratersCountValue() {
		waitForWebElementEnable(ratersCount, 10);
		return ratersCount.getAttribute("innerHTML");
	}

	/**
	 * verify raters count value
	 */

	public String ratersCountValueOnSlide() {
		waitForWebElementEnable(ratersCountOnSlidePanel, 10);
		return ratersCountOnSlidePanel.getText();
	}

	/**
	 * Verify raters count
	 ***/

	public void verifyRatersCountOnSlidePanel(String ratersCount) {
		Assert.assertEquals(ratersCount, ratersCountOnSlidePanel.getText());
		reportInfo();
	}

	public StudyDashBoardPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 * @return
	 */
	public StudySubjectListingPage selectStudy(String studyName) {
		boolean flag = false;
		waitAndClick(selectStudyDRPDWN);
		for (WebElement studyNametoselect : studyLIST) {
			if (studyNametoselect.getText().trim().contains(studyName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", studyNametoselect);
				clickOn(studyNametoselect);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		new WebDriverWait(driver, 15).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='sub-navigation-group pull-right ng-scope']")));
		return PageFactory.initElements(driver, StudySubjectListingPage.class);
	}

	/**
	 * verify Option To Select Study Is Displayed
	 */
	public void verifyOptionToSelectStudyIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(selectStudyDRPDWN));
		moveToElement(selectStudyDRPDWN);
		reportInfo();
	}

	/**
	 * Verify study displayed under select study drop down
	 * 
	 */
	public void verifyStudyDisplayedUnderSelectStudyDropDownList(String studyName) {
		boolean flag = false;
		waitAndClick(selectStudyDRPDWN);
		for (WebElement studyNametoselect : studyLIST) {
			if (studyNametoselect.getText().trim().contains(studyName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", studyNametoselect);
				moveToElement(studyNametoselect);
				flag = true;
				break;
			}
		}
		waitAndClick(selectStudyDRPDWN);
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify study is UnAvailable under select study drop down
	 * 
	 */
	public void studyIsUnavailableUnderSelectStudyDropDWN(String studyName) {
		boolean flag = true;
		waitAndClick(selectStudyDRPDWN);
		for (WebElement studyNametoselect : studyLIST) {
			if (studyNametoselect.getText().trim().contains(studyName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", studyNametoselect);
				moveToElement(studyNametoselect);
				flag = false;
				break;
			}
		}
		waitAndClick(selectStudyDRPDWN);
		Assert.assertTrue(flag);
		reportInfo();
	}
	/* Verify Study DashBoard Page is open */

	public void verifyStudyDashBoardPage() {
		Assert.assertTrue(isElementDisplayed(studyNavigatorGrid));
		reportInfo();
	}

	/**
	 * Verify study dashboard and Selected Study Name is displayed
	 * 
	 * @param studyNameToBeVerify
	 */

	public void verifySelectedStudyName(String studyNameToBeVerify) {
		Assert.assertTrue(isElementDisplayed(studyNavigatorGrid));
		Assert.assertTrue(selectedStudyNameText.getText().contains(studyNameToBeVerify));
		reportInfo();
	}

	/**
	 * Verify study navigator side panel options is displayed
	 */

	public void verifyExtendedSidePanelIsDisplayedWithMenuIconAndTiles() {
		List<String> listToBeVerify = new ArrayList<String>();
		verifyExtendedSidePanelIsDisplayed();
		List<WebElement> visibleOptions = studyNavigatorGrid
				.findElements(By.xpath(".//ul[@class='aside-nav-list']//li/span[2]"));
		for (WebElement we : visibleOptions) {
			listToBeVerify.add(we.getText().trim());
		}
		Assert.assertTrue(listToBeVerify.containsAll(StudyModuleConstants.sidePanelOptions));
		reportInfo();
	}

	/**
	 * Verify study navigator side panel options is displayed with menu icon and
	 * menu tile
	 */

	public void verifyExtendedSidePanelIsDisplayedWithMenuTitles() {
		int counter = 0;
		if (verifyExtendedSidePanelIsDisplayed() == true) {
			for (WebElement we : filterList) {
				Assert.assertTrue(isElementDisplayed(we.findElement(
						By.xpath(".//span[text()='" + StudyModuleConstants.sidePanelOptions.get(counter) + "']"))));
				counter++;
			}
		} else {
			Assert.fail("Side panel should be open to verify the menu tile");
		}
		reportInfo();
	}

	/**
	 * Verify study navigator side panel options is displayed with only menu
	 * icon and not displayed menu tile
	 */

	public void verifyExtendedSidePanelIsDisplayedWithOutMenuTitles() {
		if (filterPanelIconMenuTitleList.size() > 0) {
			Assert.fail("Left side panel is displayed in expanded mode and menu title is also displayed");
		} else {
			Assert.assertTrue(true);
		}
		reportInfo();
	}

	/**
	 * CLick on side panel filter and verify selected grid title text
	 */

	public void selectSidePanelFilterOneByOneAndVerifyFilterItemAndListBlock() {
		Map<String, List<String>> statusListToBeVerify = new HashMap<>();
		List<String> listToBeVerify = new ArrayList<>();
		for (String statusText : StudyModuleConstants.sidePanelOptions) {
			filterList = driver.findElements(
					By.xpath("//div[@id='NewStudyComponent']//ul[@class='aside-nav-list']//li//span[@class='f-text']"));
			statusFilterByText: for (WebElement statusFilterTile : filterList) {
				if (statusFilterTile.getText().contains(statusText)) {
					clickOn(statusFilterTile);
					_normalWait(1500);
					Assert.assertTrue(selectedFilterGridTitleText.getText().contains(statusText));
					for (WebElement filterByBoldText : filterByBoldText) {
						if (filterByBoldText.getText().length() > 0) {
							listToBeVerify.add(getText(filterByBoldText));
						} else {
							continue;
						}
					}
					statusListToBeVerify.put(statusText, listToBeVerify);
					for (Entry<String, List<String>> entry : statusListToBeVerify.entrySet()) {
						if (entry.getKey().equals(statusText)) {
							Assert.assertTrue(StudyModuleConstants.subjectsFilterByTitleText
									.containsAll(statusListToBeVerify.get(statusText)));
						}
					}
					reportInfo();
					listToBeVerify.clear();
					break statusFilterByText;
				}
			}
		}
	}

	/**
	 * Verify After page refresh Menu selection is saved and the last select
	 * menu item before refresh is displayed
	 * 
	 * @param selectedStudyToBeVerified
	 */
	public void verifyLastSelectedStudyAndFilterIsSelectedAfterRefreshThePage(String selectedStudyToBeVerified) {
		String selectedFilterNameBeforePageRefresh = getSelectedFilterName();
		driver.navigate().refresh();
		PageFactory.initElements(driver, this);
		verifySelectedStudyName(selectedStudyToBeVerified);
		verifySelectedSidePanelFilterName(selectedFilterNameBeforePageRefresh);
		reportInfo();
	}

	/**
	 * Verify study navigator side panel is displayed in extended view
	 */
	public boolean verifyExtendedSidePanelIsDisplayed() {
		boolean status = studyNavigatorGrid.getAttribute("class").contains("new-study-component sidebar-opened");
		Assert.assertTrue(status);
		reportInfo();
		return status;
	}

	/**
	 * Verify study navigator side panel is displayed in extended view
	 */
	public void verifyExtendedSidePanelIsNotDisplayed() {
		Assert.assertTrue(!(studyNavigatorGrid.getAttribute("class").contains("new-study-component sidebar-opened")));
		reportInfo();
	}

	/**
	 * Open or close the side panel
	 */
	public void expandAndCollapseSidePanel() {
		clickOn(sidePanelTogglerButton);
	}

	/**
	 * To verify the selected filter name
	 * 
	 * @param selectedFIlterToBeVerify
	 */

	public void verifySelectedSidePanelFilterName(String selectedFIlterToBeVerify) {
		boolean flag = false;
		for (WebElement filterListText : filterList) {
			if (filterListText.getAttribute("class").contains("selected") && filterListText
					.findElement(By.xpath(".//span[2]")).getText().equalsIgnoreCase(selectedFIlterToBeVerify)) {
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Get the text of side panel selected filter tile text
	 */

	public String getSelectedFilterName() {
		String selectedFilterName = null;
		for (WebElement filterListText : filterList) {
			if (filterListText.findElement(By.xpath("./parent::li")).getAttribute("class").contains("selected")) {
				selectedFilterName = filterListText.getText();
			}
		}
		return selectedFilterName;
	}

	/**
	 * Verify Filter and list blocks are displayed on the page
	 */

	public void verifyFilterAndListBlockIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(filterBlock) && isElementDisplayed(subjectContentGrid));
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
	 * Verify Site doesn't include under site list
	 * 
	 */
	public void verifySiteIsNotIncludeUnderSiteList(String siteName) {
		boolean flag = true;
		clickOn(selectSiteDRPDWN);
		for (WebElement AvailableSite : AvailableSiteList) {
			if (getText(AvailableSite).contains(siteName)) {
				flag = false;
			}
		}
		clickOn(selectSiteDRPDWN);
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify selected site
	 * 
	 */
	public void verifySelectedSite(String siteName) {
		waitForPageLoaded();
		waitForElementClickable(selectSiteDRPDWN, 30);
		Assert.assertTrue(
				getText(selectSiteDRPDWN.findElement(By.xpath(".//span[@id='selectedStudy']"))).contains(siteName));
		moveToElement(selectSiteDRPDWN.findElement(By.xpath(".//span[@id='selectedStudy']")));
		reportInfo();

	}

	/**
	 * Verify Total count of Subjects across 'All Sites' is equal to Sum of
	 * Subjects for Rest of the available sites
	 * 
	 */
	public void verifySubjectsCountAcrossAllSites_EqualsTO_SumOfSubjectsCountAcrossRestOfAvailableSites() {
		String name = null;
		int SubjectCount_SiteAll = 0, totalSubjectCountExcept_SiteAll = 0;
		waitForPageLoaded();
		waitForElementClickable(selectSiteDRPDWN, 30);
		clickOn(selectSiteDRPDWN);
		for (WebElement AvailableSite : AvailableSiteList) {
			name = getText(AvailableSite);
			if (name.contains(Constants.All_Sites)) {
				clickOn(AvailableSite);
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				_normalWait(800);
				SubjectCount_SiteAll = getTotalcountOfSubjects();
				clickOn(selectSiteDRPDWN);
				continue;
			} else {
				clickOn(AvailableSite);
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				_normalWait(800);
				totalSubjectCountExcept_SiteAll = totalSubjectCountExcept_SiteAll + getTotalcountOfSubjects();
				clickOn(selectSiteDRPDWN);
				continue;
			}
		}
		clickOn(selectSiteDRPDWN);
		Assert.assertEquals(SubjectCount_SiteAll, totalSubjectCountExcept_SiteAll);
		reportInfo();
	}

	/**
	 * Get Total count of Subjects Available
	 * 
	 */
	public int getTotalcountOfSubjects() {
		waitForElementPresent(selectedFilterGridTitleText, 15);
		Assert.assertTrue(isElementPresent(selectedFilterGridTitleText));
		String[] Title = getText(selectedFilterGridTitleText).split(" ");
		int TotalCount = Integer.parseInt(Title[2].substring(1, Title[2].length() - 1));

		moveToElement(selectedFilterGridTitleText);
		reportInfo();
		return TotalCount;
	}

	/*
	 * public StudySubjectListingPage navigateToSubjectsListing() {
	 * WebDriverWait wait = new WebDriverWait(driver, 5);
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By. xpath(
	 * "//div[@class='spinner ng-scope']"))); clickOn(subjects);
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By. xpath(
	 * "//div[@class='spinner ng-scope']"))); return
	 * PageFactory.initElements(driver, StudySubjectListingPage.class);
	 * 
	 * }
	 */

	/**
	 * Click on Subject
	 * 
	 */

	public void navigateToSubjectsListing() {
		waitForPageLoaded();
		waitAndClick(subjects);

	}

	/**
	 * Click on Visit
	 * 
	 */

	public void navigateToVisitsListing() {
		waitForPageLoaded();
		waitAndClick(visits);

	}

	/**
	 * Click on Medication
	 * 
	 */
	public void navigateToMedicationListing() {
		waitForPageLoaded();
		clickOn(medications);

	}

	/**
	 * Click on Assessment
	 * 
	 */
	public void navigateToAssessmentsListing() {
		waitForPageLoaded();
		clickOn(assessments);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		_normalWait(2000);
	}

	/**
	 * Click on Messages
	 * 
	 */
	public void navigateToMessagesListing() {
		waitForPageLoaded();
		clickOn(messages);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		_normalWait(800);
	}

	public void verifySelectedStudyDashBoardChartViewIsDisplayed() {
		Assert.assertTrue(isElementPresent(studySubjectGraph) && isElementNotVisible(studyVisitGraph)
				&& isElementNotVisible(studyVisitGraph));
		reportInfo();
	}

	public void changeStudyViewMode() {
		waitAndClick(viewModeBTN);
	}

	public void verifySelectedStudyDashBoardCardViewIsDisplayed() {
		Assert.assertTrue(isElementPresent(studyCardViewDashBoard));
	}

	/**
	 * Verify ToolTip Subject Status Description
	 * 
	 *
	 */

	public void verifyToolTipSubjectStatusDescription(String newStatus, String screenedStatus,
			String screenFailedStatus, String discontinueStatus) {

		waitForElement(subjectNewToolTip);
		Assert.assertEquals(newStatus, getToolTip(subjectNewToolTip));
		Assert.assertEquals(screenedStatus, getToolTip(subjectScreenedToolTip));
		Assert.assertEquals(screenFailedStatus, getToolTip(subjectScreenedFailedToolTip));
		Assert.assertEquals(discontinueStatus, getToolTip(subjectDisContinueToolTip));
	}

	/** Verify Card Subject Status Description */
	public void verifyCardSubjectStatusDescription(String newStatus, String screenedStatus, String screenFailedStatus,
			String discontinueStatus) {

		Assert.assertEquals(newStatus, subjectNewCardStatus.getText());
		Assert.assertEquals(screenedStatus, subjectScreenedCardStatus.getText());
		Assert.assertEquals(screenFailedStatus, subjectScreenedFailedStatus.getText());
		Assert.assertEquals(discontinueStatus, subjectDisContinueStatus.getText());
	}

	/** Click On Home Icon And Navigate To Home Page */

	public void navigateToHome() {
		clickOn(homeIcon);
	}

	/**
	 * Verify ToolTip Subject Status Empty
	 * 
	 *
	 */

	public void verifyToolTipSubjectStatusEmpty() {

		String newText = getToolTip(subjectNewToolTip);
		String screenedText = getToolTip(subjectScreenedToolTip);
		String screened_Failed = getToolTip(subjectScreenedFailedToolTip);
		String discontinueText = getToolTip(subjectDisContinueToolTip);
		Assert.assertEquals("New", newText);
		Assert.assertEquals("Screened", screenedText);
		Assert.assertEquals("Screen Failed", screened_Failed);
		Assert.assertEquals("Discontinued", discontinueText);
	}

	/** Verify Card Subject Status Empty **/
	public void verifyCardSubjectStatusEmpty() {

		Assert.assertEquals("", subjectNewCardStatus.getText());
		Assert.assertEquals("", subjectScreenedCardStatus.getText());
		Assert.assertEquals("", subjectScreenedFailedStatus.getText());
		Assert.assertEquals("", subjectDisContinueStatus.getText());
	}

	/** Verify ToolTip Default Status Description **/

	public void verifyToolTipDefaultStatusDescription(String defaultNewStatus, String defaultScreenedStatus,
			String defaultScreenFailedStatus, String defaultDiscontinueStatus) {
		String newText = getToolTip(subjectNewToolTip);
		String screenedText = getToolTip(subjectScreenedToolTip);
		String screened_Failed = getToolTip(subjectScreenedFailedToolTip);
		String discontinueText = getToolTip(subjectDisContinueToolTip);
		Assert.assertEquals(defaultNewStatus, newText);
		Assert.assertEquals(defaultScreenedStatus, screenedText);
		Assert.assertEquals(defaultScreenFailedStatus, screened_Failed);
		Assert.assertEquals(defaultDiscontinueStatus, discontinueText);
	}

	/** Verify Card Subject Default Status Description */

	public void verifyCardDefaultStatusDescription(String defaultNewStatus, String defaultScreenedStatus,
			String defaultScreenFailedStatus, String defaultDiscontinueStatus) {

		Assert.assertEquals(defaultNewStatus, subjectNewCardStatus.getText());
		Assert.assertEquals(defaultScreenedStatus, subjectScreenedCardStatus.getText());
		Assert.assertEquals(defaultScreenFailedStatus, subjectScreenedFailedStatus.getText());
		Assert.assertEquals(defaultDiscontinueStatus, subjectDisContinueStatus.getText());
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
		boolean flag = false;
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

	/* Verify Subject Status With correct Count Indication Present */

	public void verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(String statusToBeSelect, String count) {
		boolean flag = false;
		for (WebElement subjectWE : subjectStatusList) {
			String subjectStatusTextWithNumbers = subjectWE.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusTextWithOutNumber = subjectStatusText.split("(?<=\\D)(?=\\d)");
			String str = subjectStatusTextWithOutNumber[1];
			int counts = Integer.parseInt(str);
			if (subjectStatusTextWithOutNumber[0].equalsIgnoreCase(statusToBeSelect)) {

				if (counts >= 0) {
					flag = true;
					Assert.assertTrue(flag, "Subject Status Filter is present With Correct Subject Count Indication");
					break;
				} else {
					Assert.assertTrue(flag,
							"Subject Status Filter is Not  present With Correct Subject Count Indication");
				}
				break;
			}
			Log.info("Subject Status Filter Not Present In List");
		}

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
		waitAndClick(addSubjectBTN);
		if (isElementDisplayed(addSubjectSiteLIST)) {
			List<WebElement> siteList = getDropDownOptions(addSubjectSiteLIST);
			for (int i = 0; i <= siteList.size() - 1; i++) {
				if (siteList.get(i).getText().trim().contains(siteNameToSelect[0])) {
					selectDropDownByIndex(addSubjectSiteLIST, i);
					break;
				}
			}
		}
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(addSubPopup_CancelBTN));
	}

	/**
	 * Verify Add Subject Button Is Displayed
	 */
	public void verifyAddSubjectBtnIsDisplayed() {
		Assert.assertTrue(isElementPresent(addSubjectBTN));
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//thead//th//span[text()='Subject']")));
		reportInfo();
	}

	/**
	 * Verify Refresh Button Is Displayed
	 */
	public void verifyRefreshBtnIsDisplayed() {
		Assert.assertTrue(isElementPresent(refreshBTN));
		moveToElement(refreshBTN);
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
	 * Verify required Field are Highlighted in new add subject popup
	 */
	public void verifyRequiredFieldsAreHighlighted​() {
		Assert.assertTrue(
				addSubPopup_RequiredScreeningINP.isDisplayed() && addSubPopup_LanguageDROPDOWNFieldVAL.isDisplayed());
		reportInfo();
	}

	/**
	 * Enter text in notes input field in add subject popup
	 * 
	 * @param inputNoteToBeEntered
	 */
	public void inputNotes(String inputNoteToBeEntered) {
		inputText(addSubPopup_NotesINP, inputNoteToBeEntered);
		reportInfo();
	}

	/**
	 * Verify Notes Text has been added
	 * 
	 * @param inputTextToBeVerified
	 */
	public void verifyNotesTextIsEntered(String inputTextToBeVerified) {
		Assert.assertEquals(inputTextToBeVerified, addSubPopup_NotesINP.getAttribute("value"));
	}

	/**
	 * Verify Save button is disabled
	 */

	public void verifySaveButtonIsDisabled() {
		Assert.assertFalse(addSubPopup_SaveBTN.isEnabled());
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
	 * Verify Screening Text has been added
	 * 
	 * @param screenNumToBeVerified
	 */
	public void verifyScreeningNumIsEntered(String screenNumToBeVerified) {
		Assert.assertTrue(addSubPopup_ScreeningINP.getAttribute("value").trim().contains(screenNumToBeVerified));
		reportInfo();
	}

	/**
	 * Get Value entered in Screening no field
	 * 
	 * @return
	 */
	public String getValueEnteredInScreeningNum() {
		waitForElement(addSubPopup_ScreeningINP);
		return (addSubPopup_ScreeningINP.getAttribute("value").trim());
	}

	/**
	 * Clear screening number
	 */
	public void clearScreeningInp() {
		addSubPopup_ScreeningINP.clear();
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
	 * Verify Language has been selected successfully
	 * 
	 * @param selectedLanguage
	 */
	public void verifyLanguageIsSelected(String selectedLanguage) {
		Assert.assertEquals(getText(addSubPopup_SelectedSubjectLanguage).trim(), selectedLanguage);
		reportInfo();
	}

	/**
	 * Click on save button of new subject Popup
	 * 
	 * @return SubjectsDetailsPage
	 */
	public NewSubjectDetailPage clickOnSaveBTN() {
		waitAndClick(addSubPopup_SaveBTN);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/**
	 * click on cancel button to close the add new subject popup
	 */
	public void clickOnCancelBTN() {
		clickOn(addSubPopup_CancelBTN);
	}

	/**
	 * Verify new add subject popup is not displayed
	 */
	public void verifyAddSubjectPopUpIsNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(addSubPopup_AddSubjectTITLE));
		reportInfo();
	}

	/**
	 * Verify Subject Listing page title and subject count is displayed
	 */
	public void verifySubjectListIsOpened() {
		waitForWebElementPresent(addSubjectBTN);
		Assert.assertTrue(isElementPresent(subjectCount) && isElementPresent(addSubjectBTN));
		reportInfo();

	}

	/**
	 * Verify page displayed with its data in grid table
	 */

	public void verifyDataAvailableUnderTableList() {
		waitForPageLoaded();
		Assert.assertTrue(gridTableRowList.size() > 0);
		moveToElement(gridTableRowList.get(1).findElement(By.xpath("./parent::tbody")));
		reportInfo();
	}

	/**
	 * Verify Subject is not exist in Subject listing
	 * 
	 * @param subjectToBeVerified
	 */

	public void verifySubjectIsNotDisplayedInSubjectList(String subjectToBeVerified) {
		searchSubject(subjectToBeVerified);
		_normalWait(1500);
		boolean flag = true;
		if (gridTableFirstColumnList.size() > 0) {
			for (WebElement subjectNum : gridTableFirstColumnList) {
				if (getText(subjectNum).trim().equalsIgnoreCase(subjectToBeVerified))
					;
				flag = false;
				break;
			}
		} else {
			Assert.assertTrue(isElementDisplayed(noRecordContentText));
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Subject is exist in Subject listing
	 * 
	 * @param subjectToBeVerified
	 */

	public void verifySubjectIsDisplayedInSubjectList(String subjectToBeVerified) {
		searchSubject(subjectToBeVerified);
		_normalWait(1500);
		boolean flag = false;
		if (gridTableFirstColumnList.size() > 0) {
			for (WebElement subjectNum : gridTableFirstColumnList) {
				if (getText(subjectNum).trim().equalsIgnoreCase(subjectToBeVerified)) {
					flag = true;
					Assert.assertTrue(flag, "Subject Is Present In the List");
					break;
				} else {
					Assert.assertFalse(flag, "Subject Is Not  Present In the List");
				}
			}
		} else {
			Assert.assertTrue(isElementDisplayed(noRecordContentText));
		}
	}

	/**
	 * Verify Visit is exist in Visit listing
	 * 
	 * @param Visit
	 *            ToBeVerified
	 */

	public void verifyVisitIsDisplayedInVisitList(String SubjectName, String visitToBeVerified) {
		searchSubject(SubjectName);
		searchVisit(visitToBeVerified);
		_normalWait(1500);
		boolean flag = false;
		if (gridTableFirstColumnList.size() > 0) {
			if (firstColumnFilterOption.getText().trim().equalsIgnoreCase(visitToBeVerified)) {
				moveToElement(firstColumnFilterOption);
				flag = true;
			}
		}
		Assert.assertTrue(flag, "Visit is Prsent in list");
		reportInfo();
	}

	/**
	 * Search Subject by filter
	 * 
	 * @param subjectToBeSearch
	 */

	public void searchSubject(String subjectToBeSearch) {
		clickOn(FilterICON_Sub);
		waitForElementPresent(filterINPBOX, 10);
		inputText(filterINPBOX, subjectToBeSearch);
		javascriptButtonClick(inpSearchfilterBTN);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);

	}

	/**
	 * Search Visit by filter
	 * 
	 * @param visitToBeSearch
	 */

	public void searchVisit(String visitToBeSearch) {
		clickOn(FilterICON_Visit);
		waitForElementPresent(filterINPBOX, 5);
		inputText(filterINPBOX, visitToBeSearch);
		javascriptButtonClick(inpSearchfilterBTN);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);

	}

	/**
	 * Search Assessment by filter
	 * 
	 * @param assessmentToBeSearch
	 */

	public void searchAssessment(String assessmentToBeSearch) {
		clickOn(FilterICON_Assessment);
		waitForElementPresent(filterINPBOX, 5);
		inputText(filterINPBOX, assessmentToBeSearch);
		javascriptButtonClick(inpSearchfilterBTN);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);

	}

	/*
	 * Search Medication By Filter
	 * 
	 * 
	 */
	public void searchMedication(String medicationToBeSearch) {
		clickOn(FilterICON_Medication);
		waitForElementPresent(filterINPBOX, 5);
		inputText(filterINPBOX, medicationToBeSearch);
		javascriptButtonClick(inpSearchfilterBTN);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);

	}

	public AssessmentsDetailsPage clickOnAssessmentFilterLink() {
		waitAndClick(firstColumnFilterOption);
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	public void clickOnMessageLink() {
		waitAndClick(firstColumnFilterOption);
	}

	public VisitDetailsPage clickOnVisitFilterLink() {
		waitAndClick(firstColumnFilterOption);
		return PageFactory.initElements(driver, VisitDetailsPage.class);
	}

	public void verifyNoRecordPageIsDisplayed() {
		waitForElement(recordPage);
		Assert.assertTrue(recordPage.isDisplayed());
		reportInfo();
	}

	/**
	 * Click on subject link
	 * 
	 * @param subjectToBeClicked
	 */

	public NewSubjectDetailPage clickOnSubject(String subjectToBeClicked) {
		boolean flag = false;
		searchSubject(subjectToBeClicked);
		int count = 0;
		do {
			try {
				for (WebElement subjectName : gridTableFirstColumnList) {
					if (getText(subjectName).trim().equalsIgnoreCase(subjectToBeClicked)) {
						javascriptButtonClick(subjectName);

						new WebDriverWait(driver, 30).until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
						Assert.assertTrue(newSubjectDetailPageHeader.isDisplayed());
						flag = true;
						count = 1;
						break;
					}
				}
			} catch (Exception e) {
			}
		} while (count == 0);
		Assert.assertTrue(flag, subjectToBeClicked + " Should be exit in list to click.");
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/**
	 * Search a column name and select a value under the column
	 * 
	 * @return
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
	public <T> T selectValueUnderDesiredColumn(final Class className, String ColumnNameToBeVerified,
			String ValueToBeSelectUnderColumn_IfRequired) {
		boolean flag = false;
		int columnNameIndex = 1;
		for (WebElement colHeader : headerColumn) {
			if (colHeader.getAttribute("mattooltip") != null) {
				if (colHeader.getAttribute("mattooltip").trim().equalsIgnoreCase(ColumnNameToBeVerified)) {
					if (rowofGridTableList.size() > 1) {
						if (ValueToBeSelectUnderColumn_IfRequired.equalsIgnoreCase("")
								|| ValueToBeSelectUnderColumn_IfRequired.equalsIgnoreCase(null)) {
							waitAndClick(
									rowofGridTableList.get(0).findElement(By.xpath("./td[" + columnNameIndex + "]/a")));
							flag = true;
							break;
						} else {
							searchFilterValueByColumnNameAndValue(ColumnNameToBeVerified,
									ValueToBeSelectUnderColumn_IfRequired);
							waitAndClick(
									rowofGridTableList.get(0).findElement(By.xpath("./td[" + columnNameIndex + "]/a")));
							flag = true;
							break;
						}
					}
				}
				columnNameIndex++;
			}
		}
		Assert.assertTrue(flag);
		return (T) PageFactory.initElements(driver, className);
	}

	/**
	 * 
	 * Verify Click to Generate Temporary ID text is display with active icon
	 */

	public void verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon() {

		Assert.assertTrue(addSubPopup_GenerateTemporaryIDTXT.isDisplayed());
		Assert.assertTrue(
				addSubPopup_GenerateTemporaryIDTXT.getText().trim().contains("Click to Generate TemporaryID"));
		Assert.assertTrue(
				addSubPopup_GenerateTemporaryIDBTN.isDisplayed() && addSubPopup_GenerateTemporaryIDBTN.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Screening Number field is Required And also Editable
	 */
	public void verifyScreeningNumIsRequiredAndEditable() {
		Assert.assertTrue(
				addSubPopup_RequiredScreeningINP.isDisplayed() && addSubPopup_RequiredScreeningINP.isEnabled());
		reportInfo();
	}

	/**
	 * Click on auto generate button to generate auto generate temp ID
	 */
	public String generateAutoTemporaryID() {
		waitAndClick(addSubPopup_GenerateTemporaryIDBTN);
		waitForWebElementEnable(addSubPopup_TempINP, 10);
		return addSubPopup_TempINP.getAttribute("value");
	}

	/**
	 * Click on auto generate button to generate auto generate temp ID
	 */
	public String autoGenerateTemporaryIDValue() {
		waitForWebElementEnable(addSubPopup_TempINP, 10);
		return addSubPopup_TempINP.getAttribute("value");
	}

	/**
	 * Verify
	 * 
	 * @param tempIdTextToBeVerified
	 */
	public void verifyAutoTemporaryIDIsGenerated(String tempIdTextToBeVerified) {
		Assert.assertTrue(addSubPopup_TempINP.getAttribute("value").trim().contains(tempIdTextToBeVerified));
		reportInfo();
	}

	/**
	 * Click on auto generate button to generate auto generate temp ID
	 */
	public String autoGenerateSubjectNumber() {
		waitForWebElementEnable(subjcetNumberValue, 10);
		return subjcetNumberValue.getText();
	}

	/**
	 * Verify subject value on visit page
	 */
	public void verifySubjectNumberOnVisitPage(String generatedTempId) {
		Assert.assertEquals(generatedTempId, subjcetNumberValueOnPage.getText());
		reportInfo();
	}

	/**
	 * Verify subject value on Assessment page
	 */
	public void verifySubjectNumberOnAssessmentPage(String generatedTempId) {
		Assert.assertEquals(generatedTempId, subjcetNumberValueOnPage.getText());
		reportInfo();
	}

	/**
	 * Verify
	 * 
	 * @param autoGeneratedSubjectValue
	 */
	public void verifyAutoGeneratedSubjectNumber(String generatedTempId) {
		Assert.assertEquals(generatedTempId, subjcetNumberValue.getText());
		reportInfo();
	}

	/**
	 * Verify Auto Generate Temporary Cancel(Remove Icon) Button is displayed
	 */
	public void verifyAutoGenerateTemporaryCancelBTNIsDisplay() {
		Assert.assertTrue(addSubPopup_AutoGenerateTempCancelBTN.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Screening Number field is become Not Required And also Editable
	 */
	public void verifyScreeningNumNotRequiredAndEditable() {
		Assert.assertTrue(
				addSubPopup_NonRequiredScreeningINP.isDisplayed() && addSubPopup_NonRequiredScreeningINP.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Auto generated text should not be editable
	 */
	public void verifyAutoGeneratedTemporaryINPIsNotEditable() {
		Assert.assertFalse(addSubPopup_TempINP.isEnabled());
		reportInfo();
	}

	/**
	 * Click on auto Generate cancel button to remove the auto Generated Temp Id
	 */

	public void removeAutoGenerateTemporaryID() {
		clickOn(addSubPopup_AutoGenerateTempCancelBTN);
	}

	/**
	 * Verify Click to Generate Temporary ID text is display is not display
	 */
	public void verifyClickToGenerateTemporaryIDTXTIsNotDisplay() {
		Assert.assertTrue(addSubPopup_TempINP.isDisplayed());
		reportInfo();
	}

	/**
	 * Enter temp number
	 * 
	 * @param tempIdToBeEnter
	 */
	public void inputTemporaryID(String tempIdToBeEnter) {
		inputText(addSubPopup_TempINP, tempIdToBeEnter);
		reportInfo();
	}

	/**
	 * Verify Correct error header message is display
	 * 
	 * @param errorTextToBeVerified
	 */
	public void verifyHeaderErrorText(String errorTextToBeVerified) {
		Assert.assertTrue(headerErrorTXT.getText().trim().contains(errorTextToBeVerified));
		reportInfo();
	}

	/**
	 * Click on cancel button to close the error container.
	 */
	public void closeHeaderErrorMessage() {
		clickOn(headerErrorCloseBtN);
	}

	/** Return Back to Home Page */
	public MedAvantePortalPage navigateToHomePage() {
		clickOn(homeICN);
		return (PageFactory.initElements(driver, MedAvantePortalPage.class));
	}

	/**
	 * Enter text in random num input field in add subject popup
	 * 
	 * @param inputRandomNumToBeEntered
	 */
	public void inputRandomizationNum(String inputRandomNumToBeEntered) {
		inputText(addSubPopup_RandomizationINP, inputRandomNumToBeEntered);
		reportInfo();
	}

	/**
	 * Click on the first cell of the searched first row
	 */

	public void clickOnFirstCell() {

		boolean flag = false;
		do {
			try {
				waitForElementClickable(firstColumnFilterOption, 5);
				doubleClickOnElement(firstColumnFilterOption);
				new WebDriverWait(driver, 15).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				flag = true;
				break;
			} catch (Throwable e) {
				_normalWait(600);
			}
		} while (flag == false);
		Assert.assertTrue(flag, "Subject Should be present in list to click");
	}

	public AssessmentsDetailsPage selectByVisitAndSubjectName(String visitName, String subjectName) {

		/** Search through filter icons */

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visitName);

		// clickOnFirstCell();
		waitAndClick(firstColumnFilterOption);

		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	public <T> T selectByFirtsCell(final Class className) throws InterruptedException {
		waitForPageLoaded();
		_normalWait(2000);
		clickOnFirstCell();
		return (T) PageFactory.initElements(driver, className);
	}

	/**
	 * Verify Language on StudyDash Board Page
	 */

	public StudyDashBoardPage searchBySubjectNameAndVerifyLanguage(String subjectName, String Language) {

		WebElement LanguageValue = null;

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);

		int coulmnCount = getCountOfColumnByDisplayedColumnName(Constants.StudyDashBoard_columnName_Language);

		LanguageValue = driver.findElement(By.xpath(
				"//a[contains(text(),'" + subjectName + "')]/parent::td/following-sibling::td[" + coulmnCount + "]"));

		assertEquals(LanguageValue.getText(), Language);
		return PageFactory.initElements(driver, StudyDashBoardPage.class);

	}

	public AssessmentsDetailsPage verifySelectionOfAssessmentFromList() {
		for (WebElement assessmentHeaderList : headerColumn) {
			if (assessmentHeaderList.getText().contains("Status")) {
				clickOn(assessmentHeaderList);
				_normalWait(1000);
				break;
			}
		}
		for (int i = 0; i <= FirstColumnList.size() - 1; i++) {
			if (FirstColumnList.get(0).isEnabled()) {
				clickOn((FirstColumnList.get(0)));
				break;
			}
		}
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	public void searchFilterValueByColumnNameAndValue(String columnName, String ValueToBeSearch) {
		_normalWait(2000);
		boolean flag = false;
		for (WebElement columnHeader : headerColumn) {
			if (columnHeader.getAttribute("mattooltip") != null) {
				if (columnHeader.getAttribute("mattooltip").trim().equalsIgnoreCase(columnName)) {
					waitAndClick(columnHeader.findElement(By.xpath("./parent::a/parent::th//span[text()='" + columnName
							+ "']/parent::a/preceding-sibling::*//a/span[@class='k-icon k-i-filter']")));
					inputText(filterINPBOX, ValueToBeSearch);
					List<WebElement> filterCheckBoxLIST = filterINPBOX
							.findElements(By.xpath(".//parent::li//parent::ul//li"));
					if (filterCheckBoxLIST.size() > 0) {
						for (int insideListVar = 1; insideListVar < filterCheckBoxLIST.size(); insideListVar++) {
							WebElement element = filterCheckBoxLIST.get(insideListVar);
							WebElement ev1 = element.findElement(By.xpath(".//label"));
							moveToElement(ev1);
							if (getText(ev1).trim().equalsIgnoreCase(ValueToBeSearch)) {
								doubleClickOnElement(ev1.findElement(By.xpath(".//preceding-sibling::input")));
								break;
							}
						}
					}
					waitAndClick(inpSearchfilterBTN);
					waitSpinnerToBecomeInvisible();
					_normalWait(1000);
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag,
				columnName + " Should be present in grid to be click and search " + ValueToBeSearch + " value.");
	}

	// **** ============================Visits
	// Methods===========================================****//

	/** Clear Filter By ColumnName **/
	public void clearFilterValueByColumnName(String columnName) {
		boolean flag = false;
		for (WebElement columnHeader : headerColumn) {
			if (getText(columnHeader).trim().equalsIgnoreCase(columnName)) {
				waitAndClick(columnHeader.findElement(By.xpath("./parent::a/parent::th//span[text()='" + columnName
						+ "']/parent::a/preceding-sibling::*//a/span[@class='k-icon k-i-filter']")));
				waitAndClick(inpSearchClearBTN);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, columnName + " Should be present in grid to be clear the entered value.");

	}

	/** Verify Visit List Is Displayed **/
	public void verifyVisitListIsOpened() {
		Assert.assertTrue(isElementPresent(visitCount) && isElementPresent(columnGridHeader));
	}

	/** Verify Visits Count **/
	public void verifyTotalVisitCountIsDisplayed() {
		_normalWait(1500);
		waitForPageLoaded();
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//kendo-pager[contains(@class,'k-pager-wrap')]//kendo-pager-info")));
		String[] VisitCountInHeader = getText(visitCount).split("\\(");
		String[] totalCountOfVisitsInPagination = getText(pageTotalVisitsCount).split("\\s+");
		Assert.assertEquals(totalCountOfVisitsInPagination[4], VisitCountInHeader[1].replaceAll("\\)", ""));
		reportInfo();
	}

	/** Verify Visit Subject Status */
	public void verifySubjectStatus(String subjectName, String visitname, String statusToBeVerify) {
		boolean flag = false;

		/** Search through filter icons */

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visitname);

		for (WebElement visitStatus : visitStatusList) {
			if (getText(visitStatus).equalsIgnoreCase(statusToBeVerify)) {
				flag = true;
				Assert.assertTrue(flag, "Visit Status is Displaying Correctly");
			} else {
				Assert.assertFalse(flag, "Visit Status is Not Displaying Correctly");
			}
		}
		Assert.assertTrue(flag);
	}

	/** Verify Subject Status */
	public void verifySubjectStatusinSubjectListing(String subjectName, String statusToBeVerify) {
		boolean flag = false;

		/** Search through filter icons */

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);

		for (WebElement status : subjectStatusListinGridTable) {
			if (getText(status).trim().equalsIgnoreCase(statusToBeVerify)) {
				flag = true;
				Assert.assertTrue(flag, "subject Status is Displaying Correctly");
			} else {
				Assert.assertFalse(flag, "subject Status is Not Displaying Correctly");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Click On Visit By Visit And SubjectName
	 * 
	 * @throws InterruptedException
	 */
	public VisitDetailsPage clickOnVisitByVisitAndSubjectName(String visitName, String subjectName) {

		/** Search through filter icons */
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visitName);
		waitAndClick(firstColumnFilterOption);
		return PageFactory.initElements(driver, VisitDetailsPage.class);
	}

	/** Verify Existence of Subject no */
	public void verifyExistingOfSubjectNo(String subjectNo) {
		boolean flag = false;

		/** Search through filter icons */
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectNo);

		for (WebElement subjectName : gridTableFirstColumnList) {
			if (getText(subjectName).trim().equalsIgnoreCase(subjectNo)) {
				flag = true;
				Assert.assertTrue(flag, subjectNo + " exit in the list");
				break;
			} else {
				Assert.assertFalse(flag, subjectNo + " Doesn't exit in the list");
			}
		}

		clearFilterValueByColumnName(Constants.StudyDashBoard_columnName_Subject);

	}

	/** Verify Existence of Deleted id */
	public void verifyExistingOfDeletedID(String ColumnName, String ValueToBeSearch) {
		boolean flag = false;

		/** Search through filter icons */
		searchFilterValueByColumnNameAndValue(ColumnName, ValueToBeSearch);

		if (getText(gridTableWithNoRecords).trim().equalsIgnoreCase("No records available.")) {
			flag = true;
			Assert.assertTrue(flag, ValueToBeSearch + " Doesn't exit in the list");
		} else {
			Assert.assertFalse(flag, ValueToBeSearch + " exit in the list");
		}

		clearFilterValueByColumnName(ColumnName);
	}

	/**
	 * Click on clickOnSearchedSubject link
	 * 
	 * @param subjectToBeClicked
	 */

	public NewSubjectDetailPage clickOnSearchedSubject(String subjectToBeClicked) {
		_normalWait(1500);
		boolean flag = false;
		for (WebElement subjectName : gridTableFirstColumnList) {
			if (getText(subjectName).trim().contains(subjectToBeClicked)) {
				javascriptButtonClick(subjectName);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, subjectToBeClicked + " Should be exit in list to click.");
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/** Verify Subject Visit And Assessment Present In StudyDashBoard Page */
	public void verifySubjectVisitAndAssesmentTabsPresent() {
		moveToElement(subjects);
		moveToElement(visits);
		moveToElement(assessments);
		Assert.assertTrue(isElementPresent(subjects) || isElementPresent(visits) || isElementPresent(assessments));
		reportInfo();
	}

	/** Verify Statuses of the Subjects for visit on Study Navigator Page */

	private ArrayList<String> visitStatusLIST = new ArrayList<>(
			Arrays.asList("Pending", "In Progress", "Complete", "Editing"));

	public void verifyVisitStatusOptions() {
		boolean flag = false;
		ArrayList<String> listToBeVerified = new ArrayList<>();
		for (WebElement subjectWE : subjectStatusList) {
			String subjectStatusTextWithNumbers = subjectWE.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusText1 = subjectStatusText.split("(?<=\\D)(?=\\d)");
			listToBeVerified.add(subjectStatusText1[0]);
		}
		if (listToBeVerified.contains(visitStatusLIST)) {
			flag = true;
			Assert.assertTrue(flag, "Visit statuses are present");
		} else {
			flag = false;
			Assert.assertFalse(flag, "Visit statuses are not present");
		}
	}

	/**
	 * Select rater from the Rater list
	 * 
	 **/

	public RatersDetailsPage selectRater(String raterToBeSelect) {
		for (WebElement raterName : ratersList) {
			if (getText(raterName).trim().contains(raterToBeSelect)) {
				waitAndClick(raterName);
				break;
			}
		}
		return PageFactory.initElements(driver, RatersDetailsPage.class);
	}

	public void verifyRatersListCount(String raterCount) {
		int sizeOfRatersList = ratersList.size();
		String ratersCountActual = Integer.toString(sizeOfRatersList);
		Assert.assertTrue(raterCount.contains(ratersCountActual));
	}

	/* select Subject Status Filter */

	public void selectSubjectStatusFromFilter(String subjectStatusToBeSelect) {
		for (WebElement statusName : subjectStatusList) {
			waitForElement(statusName);
			String subjectStatusTextWithNumbers = statusName.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusTextWithOutNumber = subjectStatusText.split("(?<=\\D)(?=\\d)");
			if (subjectStatusTextWithOutNumber[0].trim().equalsIgnoreCase(subjectStatusToBeSelect)) {
				waitAndClick(statusName.findElement(By.xpath(".//preceding-sibling::input")));
				break;
			}
		}
	}

	/* Verify Status Selected From Filter Showing Correct Status */
	public void verifyListShowingStausAccordingToFilterSelection(String columnName, String VisitStatus) {
		_normalWait(500);
		boolean flag = false;
		for (int columnNameIndex = 0; columnNameIndex < headerColumn.size(); columnNameIndex++) {
			int columnNameIndex1 = columnNameIndex - 1;
			WebElement colHeader = headerColumn.get(columnNameIndex);
			if (colHeader.getAttribute("ng-reflect-message") != null) {
				if (colHeader.getAttribute("ng-reflect-message").trim().equalsIgnoreCase(columnName)) {
					for (int rowValue = 0; rowValue < rowofGridTableList.size(); rowValue++) {
						if (getText(rowofGridTableList.get(rowValue)
								.findElement(By.xpath(".//td[" + columnNameIndex1 + "]"))).trim()
										.equalsIgnoreCase(VisitStatus)) {
							flag = true;
							Assert.assertTrue(flag, "All status is Same As Filter Selection");
						} else {
							Assert.assertFalse(flag, "All status is Same Not  As Filter Selection");
						}
					}
					break;
				}
			}
		}
	}

	/* Verify More Than One Study Showing In DropDown */
	public void verifyMoreThanOneStudyShowingInStudyDropDown() {
		int count = 0;
		boolean flag = false;
		waitAndClick(selectStudyDRPDWN);
		for (int studyNameList = 0; studyNameList < studyLIST.size(); studyNameList++) {
			count++;
		}
		if (count > 0) {
			flag = true;
			Assert.assertTrue(flag, "More Than One Study Showing In Study DropDown");
		} else {
			Assert.assertFalse(flag, "Only One Study Showing In Study DropDown");
		}
		clickOn(selectStudyDRPDWN);
	}

	/* Verify Site DropDown By Default Showing All Sites Selection */
	public void verifySiteDropDownShowingByDefaultSiteSelected(String dataToBeShow) {
		moveToElement(siteSelectedText);
		Assert.assertTrue(siteSelectedText.getText().contains(dataToBeShow));
		reportInfo();

	}

	/* Click On Site DropDown */
	public void clickOnSiteDropDown() {
		clickOn(selectSiteDRPDWN);
	}

	/* Verify Active Sites Present In List */
	public void verifyActivesitePresentInList(String activeSiteName) {
		boolean flag = false;
		for (int sitenameToBeSelect = 0; sitenameToBeSelect < siteList.size(); sitenameToBeSelect++) {
			if (getText(siteList.get(sitenameToBeSelect).findElement(By.xpath(".//span"))).trim()
					.contains(activeSiteName)) {
				flag = true;
				Assert.assertTrue(flag, "Active Site is Present In The List");
				break;
			} else {
				Assert.assertFalse(flag, "Active Site is  Not Present In The List");
			}
		}
	}

	/* Verify Study DropDown By Default Showing Choose Study */
	public void verifyStudyDropDownShowingByDefaultChooseStudySelection() {
		Assert.assertTrue(chooseStudyText.getText().equalsIgnoreCase("Choose Study"));
		reportInfo();

	}

	/* click on status option for anyone subject or visit */

	public void clickOnRadioButtonForStatusOption(String status) {
		waitForSpinner(DEFAULT_WAIT_4_PAGE);
		WebElement radioButtonforDeseiredStatus = driver
				.findElement(By.xpath("//ul[@class='filters-list']//li//span[contains(text(),'" + status + "')]"));
		javascriptButtonClick(radioButtonforDeseiredStatus.findElement(By.xpath(".//preceding-sibling::input")));
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}

	/* Verify Subject Status Filter Is Selected */

	public void verifySubjectStatusFilterIsSelected(String subjectStatusToBeVerify) {
		boolean flag = false;
		for (WebElement statusName : subjectStatusList) {
			waitForElement(statusName);
			String subjectStatusTextWithNumbers = statusName.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusTextWithOutNumber = subjectStatusText.split("(?<=\\D)(?=\\d)");
			if (subjectStatusTextWithOutNumber[0].trim().equalsIgnoreCase(subjectStatusToBeVerify)) {
				if (statusName.findElement(By.xpath(".//preceding-sibling::input")).isSelected()) {
					flag = true;
					Assert.assertTrue(flag, "Filter is Selected By Default");
					break;
				} else {
					Assert.assertFalse(flag, "Filter is Not  Selected By Default");
				}
				break;
			}
		}
	}

	/* Verify Screening Number,Randomization Number and Temp Id Visible */

	public void verifyScreeningNumberRandomizationTempIdIsDisplayed() {
		Assert.assertTrue(isElementPresent(addSubPopup_TempINP.findElement(By.xpath("./..")))
				&& isElementPresent(addSubPopup_RandomizationINP) && isElementPresent(addSubPopup_ScreeningINP));
		moveToElement(addSubPopup_TempINP.findElement(By.xpath("./..")));
		moveToElement(addSubPopup_RandomizationINP);
		moveToElement(addSubPopup_ScreeningINP);
		reportInfo();
	}

	/* Verify Temp Id Visible */
	public void verifyTempIdIsDisplayed() {
		Assert.assertTrue(isElementPresent(addSubPopup_TempINP.findElement(By.xpath("./.."))));
		moveToElement(addSubPopup_TempINP.findElement(By.xpath("./..")));
		reportInfo();
	}

	/* Verify Screening Number Visible */
	public void verifyScreeningNumbeIsDisplayed() {
		Assert.assertTrue(isElementPresent(addSubPopup_ScreeningINP));
		moveToElement(addSubPopup_ScreeningINP);
		reportInfo();
	}

	/**
	 * Verify Medication is exist in Subject listing
	 * 
	 * @param subjectToBeVerified
	 */

	/* Verify masking format for Screening Field */
	public void verifyScreeningFieldIsInMaskFormat() {
		doubleClickOnElement(addSubPopup_ScreeningINP);
		_normalWait(500);
		Assert.assertTrue(addSubPopup_ScreeningINP.getAttribute("aria-describedby").trim().startsWith("popover"));
		reportInfo();
	}

	public void verifyMedicationIsDisplayedInMedicationList(String medicationtToBeVerified) {
		searchMedication(medicationtToBeVerified);
		_normalWait(1500);
		boolean flag = false;
		if (gridTableFirstColumnList.size() > 0) {
			for (WebElement medicationName : gridTableFirstColumnList) {
				if (getText(medicationName).trim().equalsIgnoreCase(medicationtToBeVerified)) {
					flag = true;
					Assert.assertTrue(flag, "Medication Is Present In the List");
					break;
				} else {
					Assert.assertFalse(flag, "Medication Is Not  Present In the List");
				}
			}
		} else {
			Assert.assertTrue(isElementDisplayed(noRecordContentText));
		}
	}

	/**
	 * Click On Medication By Medication And SubjectName
	 * 
	 */
	public void clickOnMedicationByMedicationAndSubjectName(String medicationName, String subjectName) {

		/** Search through filter icons */

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Medication, medicationName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		clickOnFirstCell();
	}

	/**
	 * Click On Questionnaires By QuestionnaireNames And SubjectName
	 * 
	 */
	public void clickOnQuestionnariesByQuestionnairesAndSubjectName(String subjectName, String questionnariesName,
			String questionnaireStatus) {
		/** Search through filter icons */
		searchQuestionnaire(subjectName, questionnariesName, questionnaireStatus);
		clickOnFirstCell();
	}

	/**
	 * Click On Event By EventName And SubjectName
	 * 
	 */
	public void clickOnEventByEventNameAndSubjectName(String subjectName, String EventName) {
		/** Search through filter icons */
		searchEvent(subjectName, EventName);
		clickOnFirstCell();
	}

	/** Verify Assessment List Is Displayed **/
	public void verifyAssessmentListIsOpened() {
		Assert.assertTrue(isElementPresent(assessmentCountHeaderTitle) && isElementPresent(columnGridHeader));
	}

	/*
	 * Click
	 * 
	 * /* Select Assessment By Assessment Name and Visit Name And Subject Name
	 */
	public AssessmentsDetailsPage selectByAssesmentNameAndVisitAndSubjectName(String assesmentName, String visitName,
			String subjectName) {

		/** Search through filter icons */

		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Assessment, assesmentName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visitName);
		waitAndClick(firstColumnFilterOption);
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	/* Verify Menu Item Displayed With Filter */

	public void verifySelectedMenuItemDisplayedWithFilter() {
		boolean flag = false;
		ArrayList<String> listToBeVerified = new ArrayList<>();
		for (WebElement ListVar : menuitemFilterList) {
			String TextWithNumbers = ListVar.getText();
			String Text = TextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] Text1 = Text.split("(?<=\\D)(?=\\d)");
			listToBeVerified.add(Text1[0].trim());

		}
		if (listToBeVerified.containsAll(medicationAndQuestionnairesEventFilterLIST)) {
			flag = true;

		}
		Assert.assertTrue(flag, "Menu Item Displayed With Filters");
		reportInfo();
	}

	/* Verify Filter By Block Displayed */

	public void verifyFilterByBlockDisplayed() {
		moveToElement(FilterByTxtBlock);
		Assert.assertTrue(isElementPresent(FilterByTxtBlock));
		reportInfo();
	}
	/* Verify Item Title */

	public void verifyItemTitle(String titleTobeVerify) {
		WebElement titleVerify = driver.findElement(
				By.xpath("//*[@class='study-filter-holder']//h3[contains(text(),'" + titleTobeVerify + "')]"));
		moveToElement(titleVerify);
		Assert.assertTrue(titleVerify.isDisplayed());
		reportInfo();
	}

	/* Verify Medication Filter Selected By Deafult */
	public void verifyMenuItemFilterSelected(String filterToBeVerify) {
		boolean flag = false;
		for (WebElement filterName : menuitemFilterList) {
			waitForElement(filterName);
			String TextWithNumbers = filterName.getText();
			String Text = TextWithNumbers.replaceAll("\\W", " ");
			String[] filterTextWithOutNumber = Text.split("(?<=\\D)(?=\\d)");
			if (filterTextWithOutNumber[0].trim().equalsIgnoreCase(filterToBeVerify)) {
				if (filterName.findElement(By.xpath(".//preceding-sibling::input")).isSelected()) {
					flag = true;
					break;
				} else {
					Assert.assertFalse(flag, "Filter is Not  Selected By Default");
				}
			}
		}
		Assert.assertTrue(flag, "Filter is Selected By Default");
		reportInfo();
	}

	/* Verify Medication Filter Present With Correct value */

	public void verifyMenuItemFiltersDisplayedWithValues(String filterToBeSelect, String value) {
		_normalWait(1500);
		boolean flag = false;
		for (WebElement filterVar : menuitemFilterList) {
			String TextWithNumbers = filterVar.getText();
			String Text = TextWithNumbers.replaceAll("\\W", " ");
			String[] TextWithOutNumber = Text.split("(?<=\\D)(?=\\d)");
			if (TextWithOutNumber[0].trim().equalsIgnoreCase(filterToBeSelect)) {
				if (TextWithOutNumber[1].trim().equalsIgnoreCase(value)) {
					flag = true;
					break;
				}
			}
			Log.info("Medication Filter Choosen Not Present In List");
		}
		Assert.assertTrue(flag, "Menu Item  Filter is present With Correct value");
		reportInfo();

	}

	/* Verify Medication Values DisplayedCorrectlly */

	public void verifyMedicationValuesDisplayedCorrectly(String medicationName, String valueToBeVerify,
			String valueIndex) {

		WebElement value = null;
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Medication, medicationName);
		value = driver.findElement(
				By.xpath("//a[text()='" + medicationName + "']/parent::td/following-sibling::td[" + valueIndex + "]"));

		javascripctHilightingElement(value);
		Assert.assertTrue(value.getText().trim().contains(valueToBeVerify));
		reportInfo();
	}

	/* Verify Medication Subject Link Is Clickable */

	public void verifyMedicationSubjectLinkIsClickable(String medicationName, String subjectName) {

		WebElement subjectLink = null;
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Medication, medicationName);
		subjectLink = driver
				.findElement(By.xpath("//a[text()='" + medicationName + "']/parent::td/following-sibling::td[2]//a"));
		javascripctHilightingElement(subjectLink);
		Assert.assertTrue(subjectLink.getText().trim().equalsIgnoreCase(subjectName));
		reportInfo();

	}

	/* Click On Medication Subject Link */

	public void clickOnMedicationSubjectLink(String medicationName, String subjectName) {
		WebElement subjectLink = null;
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Medication, medicationName);
		subjectLink = driver
				.findElement(By.xpath("//a[text()='" + medicationName + "']/parent::td/following-sibling::td[2]//a"));
		clickOn(subjectLink);
	}

	/* Verify Medication PopUp Displayed */
	public void verifyMedicationPopUpDisplayed() {
		Assert.assertTrue(isElementPresent(medicationPopUp));
		reportInfo();

	}

	/* Click On Medication Close PopUp Button */
	public void clickOnMedicationCloseButton() {
		clickOn(medicationPopUpCloseButton);
	}

	/**
	 * Verify Filter Block name and its filter options list
	 */
	public void verifyFilterBlockNameAndContainingAllItsFilterList(String filterGroupName,
			List<String> filterItemNamelist) {
		boolean flag = false;
		List<WebElement> filterItemNameList;
		List<String> filterItemList = new ArrayList<String>();
		for (WebElement filterGroup : filterGroupList) {
			if (getText(filterGroup.findElement(By.xpath("./h3"))).equalsIgnoreCase(filterGroupName)) {
				moveToElement(filterGroup);
				filterItemNameList = filterGroup.findElements(By.xpath("./ul//span"));
				for (WebElement filterItem : filterItemNameList) {
					filterItemList.add(getText(filterItem).split("\\(")[0].trim());
					moveToElement(filterItem);
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
		Assert.assertEquals(filterItemList, filterItemNamelist);
		reportInfo();
	}

	/**
	 * Verify Filter Block name and its filter option
	 */
	public void verifyFilterBlockNameAndItsFilterList(String filterGroupName, String filterItemName) {
		boolean flag = false;
		List<WebElement> filterItemNameList;
		outerLoop: for (WebElement filterGroup : filterGroupList) {
			if (getText(filterGroup.findElement(By.xpath("./h3"))).equalsIgnoreCase(filterGroupName)) {
				moveToElement(filterGroup);
				filterItemNameList = filterGroup.findElements(By.xpath("./ul//span"));
				for (WebElement filterItem : filterItemNameList) {
					if (getText(filterItem).startsWith(filterItemName)) {
						moveToElement(filterItem);
						flag = true;
						break outerLoop;
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Filter Block name and its filter option item is able to be
	 * selected
	 */
	public void verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(String filterGroupName,
			String filterItemName) {
		boolean flag = false;
		List<WebElement> filterItemNameList;
		outerLoop: for (WebElement filterGroup : filterGroupList) {
			if (getText(filterGroup.findElement(By.xpath("./h3"))).equalsIgnoreCase(filterGroupName)) {
				if (filterGroupName != null || filterGroupName != "") {
					moveToElement(filterGroup);
				}
				filterItemNameList = filterGroup.findElements(By.xpath("./ul//span"));
				for (WebElement filterItem : filterItemNameList) {
					if (getText(filterItem).startsWith(filterItemName)) {
						if (filterItem.findElement(By.xpath("./preceding-sibling::input")).isEnabled()) {
							moveToElement(filterItem.findElement(By.xpath("./parent::label/parent::li")));
							flag = true;
							break outerLoop;
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify The Total count of items that are displayed match the count in
	 * brackets near the selected filter option
	 */
	public void verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid() {
		boolean flag = false;
		String[] countAvailableInFilter = null;
		for (WebElement filterItem : filterItemList) {
			if (filterItem.findElement(By.xpath("./preceding-sibling::input")).isSelected()) {
				countAvailableInFilter = getText(filterItem).split(" ");
				moveToElement(filterItem);
				flag = true;
				break;
			}
		}
		moveToElement(footerTotalAvailableItemContainsOnPage);
		Assert.assertTrue(flag);
		Assert.assertEquals(
				Integer.parseInt(countAvailableInFilter[countAvailableInFilter.length - 1]
						.substring(1, (countAvailableInFilter[countAvailableInFilter.length - 1].length() - 1)).trim()),
				Integer.parseInt(getText(footerTotalAvailableItemContainsOnPage).split(" ")[4].trim()));
		reportInfo();
	}

	/**
	 * Verify Filter Block name and its selected filter option
	 */
	public void verifyFilterBlockNameAndItsSelectedFilterList(String filterGroupName, String filterItemName) {
		boolean flag = false;
		List<WebElement> filterItemNameList;
		outerLoop: for (WebElement filterGroup : filterGroupList) {
			if (getText(filterGroup.findElement(By.xpath("./h3"))).equalsIgnoreCase(filterGroupName)) {
				moveToElement(filterGroup);
				filterItemNameList = filterGroup.findElements(By.xpath("./ul//input"));
				for (WebElement filterItem : filterItemNameList) {
					if (filterItem.isSelected()) {
						if (getText(filterItem.findElement(By.xpath("./following-sibling::span")))
								.startsWith(filterItemName)) {
							flag = true;
							moveToElement(filterItem.findElement(By.xpath("./following-sibling::span")));
							break outerLoop;
						}

					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * select a filter from lists Block
	 */
	public void selectFilterFromListsBlock(String filterGroupName, String filterItemName) {
		boolean flag = false;
		List<WebElement> filterItemNameList;
		outerLoop: for (WebElement filterGroup : filterGroupList) {
			if (getText(filterGroup.findElement(By.xpath("./h3"))).equalsIgnoreCase(filterGroupName)) {
				filterItemNameList = filterGroup.findElements(By.xpath("./ul//span"));
				for (WebElement filterItem : filterItemNameList) {
					if (getText(filterItem).startsWith(filterItemName)) {
						waitAndClick(filterItem.findElement(By.xpath("./preceding-sibling::input")));
						flag = true;
						waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
						_normalWait(2000);
						break outerLoop;
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Medication ToDays List Block Displayed
	 */

	public void verifyBlockHeaderIsDisplayedAccordingToFilterSelected(String headerTextToBeVerify) {
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(@class,'study-content-holder')]//strong[contains(text(),'"
						+ headerTextToBeVerify + "')]")).isDisplayed());
		moveToElement(
				driver.findElement(By.xpath("//*[contains(@class,'study-content-holder')]//strong[contains(text(),'"
						+ headerTextToBeVerify + "')]")));
		reportInfo();
	}

	/**
	 * Verify Available List block is displayed according to the Selected Time
	 * span today || Past Week
	 */
	public void verifyAvailableListBlockDisplayedUnderSelectedTimeSpan(String TimeFilterToBeSelected) {
		int columnNameIndex = 0;
		List<String> TimeSpan = null;
		String TimeToday = null;
		if (TimeFilterToBeSelected.equalsIgnoreCase("Past Week")) {
			TimeSpan = pastWeekStartAndEndDates();
		} else if (TimeFilterToBeSelected.equalsIgnoreCase("Today")) {
			TimeToday = currentDate();
		}
		for (WebElement colHeader : headerColumn) {
			if (colHeader.getAttribute("ng-reflect-message") != null) {
				columnNameIndex++;
				if (colHeader.getAttribute("ng-reflect-message").trim().equalsIgnoreCase("Date/Time")) {
					if (rowofGridTableList.size() > 1) {
						String[] StartDateAndTime = getText(
								rowofGridTableList.get(0).findElement(By.xpath(".//td[" + columnNameIndex + "]")))
										.split(" ");
						moveToElement(
								rowofGridTableList.get(0).findElement(By.xpath(".//td[" + columnNameIndex + "]")));
						String WeekStartDate = StartDateAndTime[0];
						doubleClickOnElement(colHeader);
						waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
						_normalWait(2000);
						String[] WeekEndtDateAndTime = getText(
								rowofGridTableList.get(0).findElement(By.xpath(".//td[" + columnNameIndex + "]")))
										.split(" ");
						moveToElement(
								rowofGridTableList.get(0).findElement(By.xpath(".//td[" + columnNameIndex + "]")));
						String WeekEndDate = WeekEndtDateAndTime[0];
						if (TimeFilterToBeSelected.equalsIgnoreCase("Past Week")) {
							try {
								Date WeekStart = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(TimeSpan.get(0));
								Date ListDateStart = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(WeekStartDate);
								Date WeekEnd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(TimeSpan.get(1));
								Date ListDateEnd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(WeekEndDate);
								if (WeekStart.before(ListDateStart) || WeekStart.equals(ListDateStart)) {
									Assert.assertTrue(true, "List is displayed after Last Week Start Date");
								} else {
									Assert.assertTrue(false, "List is not displayed after Last Week Start Date");
								}
								if (WeekEnd.after(ListDateEnd) || WeekEnd.equals(ListDateEnd)) {
									Assert.assertTrue(true, "List is displayed before Last Week End Date");
								} else {
									Assert.assertTrue(false, "List is not displayed before Last Week End Date");
								}
							} catch (Exception e) {
								Assert.assertTrue(false, "Unexpected error");
							}
						} else if (TimeFilterToBeSelected.equalsIgnoreCase("Today")) {
							try {
								Date CurrentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(TimeToday);
								Date ListDateStart = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(WeekStartDate);

								Date ListDateEnd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
										.parse(WeekEndDate);
								if (CurrentDate.equals(ListDateStart)) {
									Assert.assertTrue(true, "Start List is displayed of the current Date");
								} else {
									Assert.assertTrue(false, "Start List is not displayed of the current Date");
								}
								if (CurrentDate.equals(ListDateEnd)) {
									Assert.assertTrue(true, "End List is displayed of the current Date");
								} else {
									Assert.assertTrue(false, "End List is not displayed of the current Date");
								}
							} catch (Exception e) {
								Assert.assertTrue(false, "Unexpected error");
							}
						}
					} else {
						Assert.assertTrue(true, "No list is available according to the selected time");
					}
				}
			}
		}
		reportInfo();
	}

	/**
	 * Verify values are displayed in columns
	 * 
	 * @return
	 */
	public void verifyColumnNameDisplayed(String columnNameToBeVerified) {
		boolean flag = false;
		for (WebElement assessmentHeaderList : headerColumn) {
			if (assessmentHeaderList.getText().contains(columnNameToBeVerified)) {
				flag = true;
				moveToElement(assessmentHeaderList);
				_normalWait(3000);
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Get Count Of Column By Displayed Column Name
	 * 
	 * @return
	 */
	public int getCountOfColumnByDisplayedColumnName(String columnNameToBeVerified) {
		boolean flag = false;
		int columnCount = 0;
		for (WebElement assessmentHeaderList : headerColumnText) {
			if (assessmentHeaderList.getText().contains(columnNameToBeVerified)) {
				flag = true;
				moveToElement(assessmentHeaderList);
				break;
			} else {
				columnCount++;
				continue;
			}
		}
		Assert.assertTrue(flag);
		return columnCount;
	}

	/**
	 * Verify columns are displayed with their containing SubColumns
	 */
	public void verifyColumnAndTheirContainingSubColumnsDisplayed(String columnNameToBeVerified,
			String SubColumnNameToBeVerified) {
		boolean flag = false;
		outerLoop: for (WebElement columnHeader : columnHeaderList) {
			if (columnHeader.getText().trim().equalsIgnoreCase(columnNameToBeVerified)) {
				moveToElement(columnHeader);
				_normalWait(5000);
				for (WebElement subColumnHeader : subColumnHeaderList) {
					if (getAttributeValueOfElement(subColumnHeader.findElement(By.xpath("./a/span")), "mattooltip")
							.trim().endsWith(columnNameToBeVerified)
							&& subColumnHeader.findElement(By.xpath("./a/span")).getText().trim()
									.equalsIgnoreCase(SubColumnNameToBeVerified)) {

						moveToElement(subColumnHeader);
						_normalWait(3000);
						flag = true;

						break outerLoop;
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify columns Values can be sorted in ascending/descending orders
	 */
	public void verifyColumnValuesCanBeSortedInAscAndDesOrders(String columnNameToBeShorted) {
		boolean flagAsc = false, flagDes = false;
		for (WebElement assessmentHeaderList : headerColumn) {
			if (assessmentHeaderList.getText().contains(columnNameToBeShorted)) {
				waitAndClick(assessmentHeaderList);
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				if (getAttributeValueOfElement(assessmentHeaderList.findElement(By.xpath("./following-sibling::span")),
						"class").trim().equalsIgnoreCase("k-icon k-i-sort-asc-sm")) {
					flagAsc = true;
					moveToElement(assessmentHeaderList.findElement(By.xpath("./following-sibling::span")));
				}
				waitAndClick(assessmentHeaderList);
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				if (getAttributeValueOfElement(assessmentHeaderList.findElement(By.xpath("./following-sibling::span")),
						"class").trim().equalsIgnoreCase("k-icon k-i-sort-desc-sm")) {
					flagDes = true;
					moveToElement(assessmentHeaderList.findElement(By.xpath("./following-sibling::span")));
				}
				break;
			}
		}
		Assert.assertTrue(flagAsc && flagDes);
	}

	/**
	 * Verify model window displayed
	 */
	public void verifyEmailModalWindow() {
		boolean flag = false;
		waitForElementPresent(EmailModalWindow, 2);
		if (EmailModalWindow.findElement(By.xpath("./ancestor::div[@id='maMessageInfoDialog']")).getAttribute("class")
				.trim().endsWith("modalshow in")) {
			flag = true;
			moveToElement(EmailModalWindow);
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Click on model window close button
	 */
	public void clickOnEmailModalWindowCloseButton() {

		waitAndClick(EmailModalWindow_close);

	}

	/**
	 * Drag column headers and drop them to grouping area
	 */
	public void dragColumnHeadersAndDropThemToGroupingArea(String columnNameToBeDraged) {
		boolean flag = false;
		for (WebElement assessmentHeaderList : headerColumn) {
			if (assessmentHeaderList.getText().contains(columnNameToBeDraged)) {
				dragAndDrop(assessmentHeaderList, groupingArea);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify Dragged Column Is Grouped In Grouping Area
	 */
	public void verifyDraggedColumnIsGroupedInGroupingArea(String columnNameToBeDraged) {
		boolean flag = false;
		for (WebElement columnHeaderList : groupingArea
				.findElements(By.xpath("./div//div[@class='k-group-indicator']"))) {
			if (columnHeaderList.findElement(By.xpath("./a")).getText().trim().equalsIgnoreCase(columnNameToBeDraged)) {
				moveToElement(columnHeaderList);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Remove Grouped Column from Grouping Area
	 */
	public void removeGroupedColumnFromGroupingArea(String columnNameToBeDraged) {
		boolean flag = false;
		for (WebElement columnHeaderList : groupingArea
				.findElements(By.xpath("./div//div[@class='k-group-indicator']"))) {
			if (columnHeaderList.findElement(By.xpath("./a")).getText().trim().equalsIgnoreCase(columnNameToBeDraged)) {
				waitAndClick(columnHeaderList.findElement(By.xpath("./a/span[@class='k-icon k-i-group-delete']")));
				flag = true;
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Subject list is displayed in the default view
	 */
	public void verifyValuesUnderColumnDisplayedInDefaultView() {
		Assert.assertFalse(isElementDisplayed(firstColumnAfterLablesInCollapsedView));
		reportInfo();
	}

	/* Click On Menu Item And Navigate To Listing */

	public void selectStudyMenuItemAndNavigateToListing(String menuItemToSelect) {
		boolean flag = false;
		for (WebElement menuItemToBeShown : filterPanelIconMenuTitleList) {
			if (getText(menuItemToBeShown).equalsIgnoreCase(menuItemToSelect)) {
				flag = true;
				clickOn(menuItemToBeShown);
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Menu Item Select And Displayed */

	public void verifyMenuItemSelectedDisplayed(String menuItemToVerify) {
		boolean flag = false;
		for (WebElement menuItemToBeShown : filterPanelIconMenuTitleList) {
			if (getText(menuItemToBeShown).equalsIgnoreCase(menuItemToVerify)) {
				WebElement menuItem = menuItemToBeShown.findElement(By.xpath(".//parent::li"));
				if (menuItem.getAttribute("class").contains("selected")) {
					Assert.assertTrue(menuItem.isDisplayed());
					moveToElement(menuItem);
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Questionnaire/Event Subject Link Is Clickable */
	public void verifySubjectLinkIsClickable(String nameToBeSelect) {
		WebElement subjectLink = null;
		subjectLink = driver
				.findElement(By.xpath("(//a[text()='" + nameToBeSelect + "']/parent::td/parent::tr)[1]//td[3]//a"));
		moveToElement(subjectLink);
		Assert.assertTrue(subjectLink.isDisplayed() && subjectLink.isEnabled());
		reportInfo();
	}

	/* Click On Subject Link */
	public NewSubjectDetailPage clickOnSubjectLink(String nameToBeSelect) {
		WebElement subjectLink = null;
		subjectLink = driver
				.findElement(By.xpath("(//a[text()='" + nameToBeSelect + "']/parent::td/parent::tr)[1]//td[3]//a"));
		clickOn(subjectLink);
		return (PageFactory.initElements(driver, NewSubjectDetailPage.class));
	}

	/* Verify Link Is Clickable */
	public void verifyNameLinkIsClickable(String linkToBeSelect) {
		WebElement link = null;
		link = driver
				.findElement(By.xpath("(//a[text()='" + linkToBeSelect + "']/parent::td/parent::tr)[1]//td[1]//a"));
		moveToElement(link);
		Assert.assertTrue(link.isDisplayed() && link.isEnabled());
		reportInfo();
	}
	/* Search Questionnaire */

	public void searchQuestionnaire(String subjectName, String questionnaireName, String questionnaireStatus) {
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_QuestionnaireFilter,
				questionnaireName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, questionnaireStatus);
	}

	/* Search Event */

	public void searchEvent(String subjectName, String EventName) {
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_EventFilter, EventName);
	}

	/* Verify Questionnaire & Event Values DisplayedCorrectlly */
	public void verifyQuestionnaireAndEventValuesDisplayedCorrectly(String nameToBeVerify, String valueToBeVerify,
			String valueIndex) {
		WebElement value = null;
		value = driver.findElement(
				By.xpath("(//a[text()='" + nameToBeVerify + "']/parent::td/parent::tr)[1]//td[" + valueIndex + "]"));

		moveToElement(value);
		Assert.assertTrue(value.getText().trim().contains(valueToBeVerify));
		reportInfo();
	}

	/* Verify Questionnaires PopUp Displayed */
	public void verifyQuestionnairesPopUpDisplayed() {
		moveToElement(questionnairesPopUp);
		Assert.assertTrue(isElementPresent(questionnairesPopUp));
		reportInfo();

	}

	/* Click On Questionnaires Close PopUp Button */
	public void clickOnQuestionnairesCloseButton() {
		moveToElement(questionnairesPopUpCloseButton);
		clickOn(questionnairesPopUpCloseButton);
	}

	/* Verify Event PopUp Displayed */
	public void verifyEventPopUpDisplayed() {
		moveToElement(EventPopUp);
		Assert.assertTrue(isElementPresent(EventPopUp));
		reportInfo();

	}

	/* Click On Questionnaires Close PopUp Button */
	public void clickOnEventCloseButton() {
		moveToElement(EventPopUpCloseButton);
		clickOn(EventPopUpCloseButton);
	}

	/**
	 * Verify page is displayed with pagination
	 */
	public void verifyPageDisplayedWithPagination() {
		boolean flag = true;
		int pageNum;
		List<Integer> listOfPageNumbersDisplayed = new ArrayList<Integer>();
		waitForPageLoaded();
		for (WebElement PageNumerDisplayed : footerDisplayedPageNumers) {
			pageNum = Integer.parseInt(getText(PageNumerDisplayed).trim());
			listOfPageNumbersDisplayed.add(pageNum);
			moveToElement(PageNumerDisplayed);
		}
		for (int i = 0; i < listOfPageNumbersDisplayed.size() - 1; i++) {
			if (listOfPageNumbersDisplayed.get(i) > listOfPageNumbersDisplayed.get(i + 1))
				flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify page is displayed with page count based on default count value
	 */
	public void verifyPageDisplayedWithPageCountValue(int pageNumberToBeVerified) {
		int pageNum = 0;
		waitForPageLoaded();
		for (WebElement PageNumerDisplayed : footerDisplayedPageNumers) {
			if (getAttributeValueOfElement(PageNumerDisplayed, "class").contains("selected")) {
				pageNum = Integer.parseInt(getText(PageNumerDisplayed).trim());
				moveToElement(PageNumerDisplayed);
				break;
			} else {
				continue;
			}
		}
		Assert.assertEquals(pageNum, pageNumberToBeVerified);
		reportInfo();
	}

	/**
	 * Get Selected Page number
	 * 
	 * @return
	 */
	public int getSelectedPageNumber() {
		int selectedPageNum = 0;
		boolean flag = false;
		waitForPageLoaded();
		for (WebElement PageNumerDisplayed : footerDisplayedPageNumers) {
			if (getAttributeValueOfElement(PageNumerDisplayed, "class").contains("selected")) {
				selectedPageNum = Integer.parseInt(getText(PageNumerDisplayed).trim());
				moveToElement(PageNumerDisplayed);
				flag = true;
				break;
			} else {
				continue;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
		return selectedPageNum;
	}

	/**
	 * Select a particular page number
	 */
	public void selectPageNumber(int numberToBeSelect) {
		boolean flag = false;
		waitForPageLoaded();
		for (WebElement PageNumerDisplayed : footerDisplayedPageNumers) {
			if (Integer.parseInt(getText(PageNumerDisplayed).trim()) == numberToBeSelect) {
				waitAndClick(PageNumerDisplayed);
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				_normalWait(2000);
				flag = true;
				break;
			} else {
				continue;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Click on single forward arrow (Next page) and verify Next page is
	 * displayed
	 */
	public void clickOnSingleForwardArrowAndVerifyNextPageDisplayed() {
		int currentPageNum, nextPageNum;
		currentPageNum = getSelectedPageNumber();
		waitAndClick(footerGoToNextPageArrowButton);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		nextPageNum = getSelectedPageNumber();
		Assert.assertTrue(nextPageNum == currentPageNum + 1, "Next page is displayed");
		reportInfo();
	}

	/**
	 * Click on single backward arrow (Previous page) and verify Previous page
	 * is displayed
	 */
	public void clickOnSingleBackwardArrowAndVerifyPreviousPageDisplayed() {
		int currentPageNum, nextPageNum;
		currentPageNum = getSelectedPageNumber();
		waitAndClick(footerGoToPreviousPageArrowButton);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		nextPageNum = getSelectedPageNumber();
		Assert.assertTrue(nextPageNum == currentPageNum - 1, "Previous page is displayed");
		reportInfo();
	}

	/**
	 * Click on page drop down and select an option with the number of subjects
	 * to display
	 */
	public void selectOptionWithNumberOfSubjectsToDisplay(String dropDownValueToBeSelect) {
		Assert.assertTrue(isElementDisplayed(footerPageSizeDropDownList));
		selectDropDownByValue(footerPageSizeDropDownList, dropDownValueToBeSelect);
		moveToElement(footerPageSizeDropDownList);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	/**
	 * Verify Subjects List page is displayed with selected number of subjects
	 * to display
	 */
	public void verifySubjectListDisplayedInGridEqualsToSelectedItemsPerPage() {
		waitForElementClickable(footerPageSizeDropDownList, 2);
		moveToElement(getSelectedDropDownOption(footerPageSizeDropDownList));
		Assert.assertTrue(gridTableRowList.size() == Integer
				.parseInt(getAttributeValueOfElement(getSelectedDropDownOption(footerPageSizeDropDownList), "value")));
		reportInfo();
	}

	/* Verify Subject Total Count */
	public void verifySubjectsTotalCount(int count) {
		_normalWait(1500);
		boolean flag = false;
		waitForElementPresent(selectedFilterGridTitleText, 15);
		Assert.assertTrue(isElementPresent(selectedFilterGridTitleText));
		String[] Title = getText(selectedFilterGridTitleText).split(" ");
		int TotalCount = Integer.parseInt(Title[2].substring(1, Title[2].length() - 1));
		if (TotalCount == count) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/* Verify Subject Status Has One Less Count */

	public void verifySubjectStatusHasOneLessCount(String statusToBeSelect, int previousCount) {
		boolean flag = false;
		int currentCount = getSubjectStatusFilterCountByStatusName(statusToBeSelect);
		if (currentCount == previousCount - 1) {
			flag = true;
		}

		Assert.assertTrue(flag, "Subject Status Has One Less Count ");
		reportInfo();
	}

	/* Verify Subject Status Has One More Count */

	public void verifySubjectStatusHasOneMoreCount(String statusToBeSelect, int previousCount) {

		boolean flag = false;
		int currentCount = getSubjectStatusFilterCountByStatusName(statusToBeSelect);
		if (currentCount == (previousCount + 1)) {
			flag = true;
		}

		Assert.assertTrue(flag, "Subject Status Has One More Count ");
		reportInfo();
	}

	/* Get Subject Status Count Of Filter Status */
	public int getSubjectStatusFilterCountByStatusName(String statusToBeSelect) {
		int statusCount = 0;
		boolean flag = false;
		for (WebElement subjectWE : subjectStatusList) {
			String subjectStatusTextWithNumbers = subjectWE.getText();
			String subjectStatusText = subjectStatusTextWithNumbers.replaceAll("\n", " ").replaceAll("\\W", "");
			String[] subjectStatusTextWithOutNumber = subjectStatusText.split("(?<=\\D)(?=\\d)");
			if (subjectStatusTextWithOutNumber[0].equalsIgnoreCase(statusToBeSelect)) {
				flag = true;
				statusCount = Integer.parseInt(subjectStatusTextWithOutNumber[1]);
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
		return statusCount;

	}

	/* Configuring Subject For Pre-Requisite */
	public void creatingSubjectForPreRequisite(String studyName, String siteName, String subjectName) {
		selectStudy(studyName);
		selectSite(siteName);
		verifyAddSubjectBtnIsDisplayed();
		clickOnAddSubjectBTN();
		verifyAddSubjectPopUpIsDisplayed();
		inputScreeningNum(subjectName);
		selectSubjectLanguage(Constants.subjectLanguage);
	}

	/**
	 * Verify read only Label is displayed under Desired Column
	 */
	public void verifyReadOnlyLabelIsDisplayedUnderDesiredColumn(String ColumnNameToBeVerified) {
		boolean flag = true;
		int columnNameIndex = 1;
		for (WebElement colHeader : headerColumn) {
			if (colHeader.getAttribute("ng-reflect-message") != null) {
				if (colHeader.getAttribute("ng-reflect-message").trim().equalsIgnoreCase(ColumnNameToBeVerified)) {
					if (rowofGridTableList.size() > 1) {
						try {
							if (rowofGridTableList.get(0).findElement(By.xpath("./td[" + columnNameIndex + "]/a"))
									.isDisplayed()) {
								flag = false;
							}
						} catch (Exception e) {
							moveToElement(
									rowofGridTableList.get(0).findElement(By.xpath("./td[" + columnNameIndex + "]")));
						}
						break;
					}
				}
				columnNameIndex++;
			}
		}
		Assert.assertTrue(flag);
	}

	/* Refresh the page Wait until the page is loaded */
	public void refreshThePageUntilThePageIsLoaded() {

		boolean found = false;
		do {
			refreshPage();
		} while (!(contentGrid.isDisplayed()));

		found = true;
		Assert.assertTrue(found, "Page Is Loaded");
	}

	/* Verify Counter Count Displayed */
	public void verifyCounterCount() {
		String[] counts = getText(allCountCounter).split("\\(");
		String allcount = counts[1].replaceAll("\\)", "");
		Assert.assertTrue(isElementPresent(allcount));
		reportInfo();
	}

	public void verifyMedicationLastActionStatus(String StatusToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(isElementPresent(medicationLastActionStatus), "Last Action is displayed");
		if (medicationLastActionStatus.getText().trim().contains(StatusToBeVerified)) {
			moveToElement(medicationLastActionStatus);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**********************************************************************************************************************/
	/**
	 * New Flow for StudyNavigatorDashboardPage
	 * 
	 * 
	 */

	/*
	 * @function: Method For searching study and site on Study Navigator
	 * Dashboard page.
	 */

	public StudySubjectListingPage selectStudy(String StudyName, String SiteName) {
		searchStudy.sendKeys(StudyName);
		for (WebElement study : studyList) {
			if (study.getText().contains(StudyName)) {
				waitAndClick(study);
				break;
			}

		}
		searchSite.sendKeys(SiteName);
		for (WebElement site : siteLists) {
			if (site.getText().contains(SiteName)) {
				waitAndClick(site);
				break;
			}
		}

		waitAndClick(selectButtonForStudyAndSite);
		return PageFactory.initElements(driver, StudySubjectListingPage.class);

	}

	/* Select Main Tabs filters present in StudyDashBoard Page */

	public void selectVerticleAlignFiltersTabs(String filterToBeSelect) {
		boolean flag = false;
		for (WebElement filter : mainFilltersTabsList) {
			if (getText(filter).trim().contains(filterToBeSelect)) {
				moveToElement(filter);
				clickOn(filter.findElement(By.xpath(".//parent::li")));
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag);
		reportInfo();
	}

}
