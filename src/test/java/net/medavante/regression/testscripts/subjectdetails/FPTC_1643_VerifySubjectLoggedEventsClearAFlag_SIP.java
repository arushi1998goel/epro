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

public class FPTC_1643_VerifySubjectLoggedEventsClearAFlag_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1643_VerifySubjectLoggedEventsClearAFlag_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1643 Test Case Name:Subject LoggedEvents Clear A
	 * Flag
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1643_SubjectLoggedEventsClearAFlag", groups = {})
	public void FPTC_1643_VerifySubjectLoggedEventsClearAFlag() {

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
		subjectDetailPage.verifyEventListDisplayed();

		reportLog("2.3:Today' filter is displayed");
		subjectDetailPage.verifySubTabFiltersUnderSubjectCategoryIsPresent(Constants.CategoryFilter_Today);

		reportLog("2.4:Select Today events control");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_Today);

		reportLog("2.5:The newest flagged record is shown in the top of the list");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("2.6:An Event section presented and reflects content of the event");
		subjectDetailPage.verifyLoggedEventDetailBlock();

		reportLog("3.1:In the Event section for a Logged Event (that is marked as flagged) click control Clear Flag");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.clickOnEventRowWithFlagSet();
		subjectDetailPage.clickOnEventAndQuestionnaireLoggedFlag();

		reportLog("3.2:Control to Clear Flag is changed to Set Flag control");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithFlagSetIcon();

		reportLog("3.3:Selected event in the List of Logged Events is not marked by the corresponding icon");
		subjectDetailPage.verifySelectedEventNotDisplayedWithFlagIconOnEventList();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
