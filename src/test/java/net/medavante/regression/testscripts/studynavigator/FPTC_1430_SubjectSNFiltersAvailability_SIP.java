package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1430_SubjectSNFiltersAvailability_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1430_SubjectSNFiltersAvailability_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("tabletFormSettingStudy");
	}

	/**
	 * ===================================================================================================
	 * Test Case Id : FP-TC-1430 Test Case Name :Subject Status Navigator Filters Availability
	 * 
	 * ===================================================================================================
	 */
	@Test(description = "FP-TC-1430 Subject Status Navigator Filters Availability ", groups = { "" })
	public void FPTC_1430_verifySubjectSNFiltersAvailability() {

		reportLog("1.1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


		reportLog("1.3:Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
	

		reportLog(
				"1.4:Verify Navigator displays filters for every status With Correct Count Indication that is defined for the study  subjects having appropriate status");

		studyNavigatorDashBoardPage
				.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(Constants.SubjectStatus_New);
		studyNavigatorDashBoardPage
				.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(Constants.SubjectStatus_Screened);
		studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(
				Constants.SubjectStatus_ScreenFailed);
		studyNavigatorDashBoardPage
				.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(Constants.SubjectStatus_Enrolled);
		studyNavigatorDashBoardPage
				.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(Constants.SubjectStatus_Completed);
		studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber(
				Constants.SubjectStatus_Discontinued);

		reportLog("2.1:Click New Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_New);

		reportLog("2.2:	Displayed Subject list contains subjects with New status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_New);

		reportLog("3.1:Click Screened Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Screened);

		reportLog("3.2:Displayed Subject list contains subjects with gained Screened status");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Screened);

		reportLog("4.1:Click ScreenFailed Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.Status_ScreenFailed);
		reportLog("4.2:	Displayed Subject list contains subjects with gained Screen Failed status");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_ScreenFailed);

		reportLog("5.1:Click Enrolled Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Enrolled);

		reportLog("5.2:Displayed Subject list contains subjects with gained Enrolled status");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Enrolled);

		reportLog("6.1:Click Completed Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Completed);

		reportLog("6.2:Displayed Subject list contains subjects with Completed status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Completed);

		reportLog("7.1:Click Discontinued Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Discontinued);

		reportLog("7.2:	Displayed Subject list contains subjects with Discontinued status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Discontinued);

		reportLog("7.3:Logout from the application");
		loginPage.logoutApplication();

		reportLog("7.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
