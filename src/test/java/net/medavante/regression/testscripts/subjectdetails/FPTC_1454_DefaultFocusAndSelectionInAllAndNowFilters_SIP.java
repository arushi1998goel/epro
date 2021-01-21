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

public class FPTC_1454_DefaultFocusAndSelectionInAllAndNowFilters_SIP extends BaseTest {

	private String subjectNameFirst = "Auto2665" + generateRandomAlphanumericString(3),
			subjectNameSecond = "Auto2665Second" + generateRandomAlphanumericString(3), visitScheduleToday,
			visitForFirst;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1454_DefaultFocusAndSelectionInAllAndNowFilters_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		visitScheduleToday = properties.getProperty("VisitNotAssigned");
		visitForFirst = properties.getProperty("VisitSecond2645");

		reportLog("Creating a subject first for no visits scheduled for today based on visit window");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectNameFirst);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("Creating a subject Second for  visits scheduled for today based on visit window");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectNameSecond);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitScheduleToday);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.setCurrentDate();
		loginPage.logoutApplication();

	}

	/**
	 * =======================================================================================================
	 * Test Case Id: FP-TC-1454 Test Case Name:Default focus and selection in
	 * All and Now filters
	 * ========================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1454_Verify Default focus and selection in All and Now filters", groups = { "" })

	public void FPTC_1454_verifyDefaultFocusAndSelectionInAllAndNowFilters() {

		reportLog("1.1:	Log in to the Portal as User of Pr#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:	 Navigate to Subject Details page of PR#2.1 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNameFirst);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectNameFirst);

		reportLog("2.2:	Select the All filter in the Visits list");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.3:	The first Visit is selected by default in the list");
		subjectDetailPage.verifyVisitSelectedByDefault(visitForFirst);

		reportLog("3.1:	Navigate to Subject Details page of PR#2.2");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNameSecond);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectNameSecond);

		reportLog("3.2:Now fillter is selected by default ");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.VisitCategoryFilter_Now);

		reportLog("3.3:The Visit of Pr#2.2.2 is selected by default");
		subjectDetailPage.verifyVisitSelectedByDefault(visitScheduleToday);

		reportLog("4.1:	Select the All filter in the Visits list");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("4.2:The Visit of Pr#2.2.2 is selected by default");
		subjectDetailPage.verifyVisitSelectedByDefault(visitScheduleToday);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
