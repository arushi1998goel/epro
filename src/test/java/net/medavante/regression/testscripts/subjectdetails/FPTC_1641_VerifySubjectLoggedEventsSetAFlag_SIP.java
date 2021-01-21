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

public class FPTC_1641_VerifySubjectLoggedEventsSetAFlag_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1641_VerifySubjectLoggedEventsSetAFlag_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1641 Test Case Name:Subject LoggedEvents Set A Flag
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1641_SubjectLoggedEventsSetAFlag", groups = {})
	public void FPTC_1641_VerifySubjectLoggedEventsSetAFlag() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
	
		
		reportLog("1.3:Study Pr.#1 Subject Listing screen ");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("1.4:Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		
		reportLog("2.1:Select Logged Events from Subject categories drop-down");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_LoggedEvents);

		reportLog("2.2:List of Logged Events Pr.#4  displayed ");
		subjectDetailPage.verifyEventListDisplayed();

		reportLog("2.3: Newest records are shown in the top of the list ");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("2.4:An Event section presented and reflects content of the event ");
		subjectDetailPage.verifyLoggedEventDetailBlock();

		reportLog("3.1:Select any event in the list (that is not marked as flagged) ");
		subjectDetailPage.clickOnEventRowWithFlagNotSet();
	
		reportLog("3.2:In the Event section for a selected event activate control Set Flag ");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithFlagSetIcon();
		subjectDetailPage.clickOnEventAndQuestionnaireLoggedFlag();
		
		reportLog("3.3:Set Flag control is changed to Clear Flag control");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithClearIcon();

		reportLog("3.4:Selected event in the List of Logged Events marked with the corresponding icon");
		subjectDetailPage.clickOnEventRowWithFlagSet();
	
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
