package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1638_SubjectLoggedEventsFlaggedEvents_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1638_SubjectLoggedEventsFlaggedEvents_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		subjectName = properties.getProperty("SubjectEventName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1638 Test Case Name: Subject Logged Events - Flagged
	 * Events
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1638_Subject Logged Events - Flagged Events", groups = {})
	public void FPTC_1638_SubjectLoggedEventsFlaggedEvents() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3:Study Pr.#1 Subject Listing screen");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("1.4:Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		
		reportLog("1.5:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("2.1:Select Logged Events from Subject categories drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_LoggedEvents);
			
		reportLog("2.2:List of Logged Events Pr.#4 is displayed ");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.verifyEventListDisplayed();
		
		reportLog("2.3:Select any event and activate Flagged control for it");
		subjectDetailPage.clickOnEventRowWithFlagNotSet();
		subjectDetailPage.clickOnEventAndQuestionnaireLoggedFlag();

		reportLog("2.4:Flag is added to the event");
		subjectDetailPage.verifySelectedEventAndQuestionnaireDisplayedWithFlagIconOnList();
		
		reportLog("3.1:Select Flagged events sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_Flagged);
		
		reportLog("3.2:The newest flagged record is shown in the top of the list (if few of them are available)");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();
		
		reportLog("3.3:An Event section presented and reflects content of the event");
		subjectDetailPage.verifyLoggedEventDetailBlock();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
	}
}