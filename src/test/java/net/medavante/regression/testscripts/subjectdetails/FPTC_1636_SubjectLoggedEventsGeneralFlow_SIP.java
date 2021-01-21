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

public class FPTC_1636_SubjectLoggedEventsGeneralFlow_SIP extends BaseTest {
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1636_SubjectLoggedEventsGeneralFlow_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1636 Test Case Name:Subject Logged Events General
	 * Flow
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1636_SubjectLoggedEventsGeneralFlow", groups = {})
	public void FPTC_1636_SubjectLoggedEventsGeneralFlow() {

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
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("1.5:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.1:Select Logged Events from Subject categories drop-down");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_LoggedEvents);

		reportLog("2.2:List of Logged Events Pr.#4  displayed ");
		subjectDetailPage.verifyEventListDisplayed();

		reportLog("2.3:'All' filter displayed by default ");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_All);
		
		reportLog("2.4: Newest records are shown in the top of the list ");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("2.5:An Event section presented and reflects content of the event ");
		subjectDetailPage.verifyLoggedEventDetailBlock();
		
		reportLog("Log out from the Portal ");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
	}

}
