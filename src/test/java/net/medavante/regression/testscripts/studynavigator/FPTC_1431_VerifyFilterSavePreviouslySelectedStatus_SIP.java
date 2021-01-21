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
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1431_VerifyFilterSavePreviouslySelectedStatus_SIP extends BaseTest {

	private String subjectNew, subjectScreened, subjectScreenedFailed, subjectDiscontinue, subjectComplete,
			subjectEnrolled;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1431_VerifyFilterSavePreviouslySelectedStatus_SIP(String browser) {
		super(browser);
	}

	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		subjectNew = properties.getProperty("SubjectNew2485");
		subjectScreened = properties.getProperty("SubjectScreened2485");
		subjectScreenedFailed = properties.getProperty("SubjectScreenedFailed2485");
		subjectDiscontinue = properties.getProperty("SubjectDiscontinued2485");
		subjectComplete = properties.getProperty("SubjectCompleted2485");
		subjectEnrolled = properties.getProperty("SubjectEnrolled2485");
	}

	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1431 Test Case Name:Subject Status - Navigator
	 * Filters: save previously selected status
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1431_Subject Status - Navigator Filters: save previously selected status", groups = {
			" " })

	public void FPTC_1431_verifySubjectStatusNavigatorFilterSavePreviouslySelectedStatus() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
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

		reportLog("2.1:	Click New Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_New);

		reportLog("2.2:Displayed Subject list contains subjects with New status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_New);

		reportLog("2.3:Proceed to any Subject detail screen from filtered by New status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNew);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectNew);

		reportLog("2.4:Subject detail screen with New status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status, Constants.SubjectStatus_New);

		reportLog("3.1:	Return to Navigator");		
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		reportLog("3.2:	Previously selected filter 'New' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_New);

		reportLog("4.1:	Click Screened Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Screened);

		reportLog("4.2:Displayed Subject list contains subjects with Screened status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Screened);

		reportLog("4.3:Proceed to any Subject detail screen from filtered by Screened status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectScreened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreened);

		reportLog("4.4:Subject detail screen with Screened status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status, Constants.SubjectStatus_Screened);

		reportLog("5.1:	Return to Navigator");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("5.2:	Previously selected filter 'Screened' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_Screened);
		
		reportLog("6.1:Click Screen Failed Subject filter ");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.Status_ScreenFailed);

		reportLog("6.2:Displayed Subject list contains subjects with Screen Failed status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_ScreenFailed);

		reportLog("6.3:Proceed to any Subject detail screen from filtered by Screen Failed status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectScreenedFailed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreenedFailed);

		reportLog("6.4:Subject detail screen with Screen Failed status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("7.1:	Return to Navigator");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("7.2:	Previously selected filter 'Screen Failed' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_ScreenFailed);

		reportLog("8.1:Click Enrolled Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Enrolled);

		reportLog("8.2:Displayed Subject list contains subjects with Enrolled status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Enrolled);

		reportLog("8.3:Proceed to any Subject detail screen from filtered by Enrolled status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectEnrolled);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectEnrolled);

		reportLog("8.4:Subject detail screen with Enrolled status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status, Constants.SubjectStatus_Enrolled);

		reportLog("9.1:	Return to Navigator");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("9.2:	Previously selected filter 'Enrolled' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_Enrolled);
		

		reportLog("10.1:Click Completed Subject filter");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Completed);

		reportLog("10.2:Displayed Subject list contains subjects with Completed status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Completed);

		reportLog("10.3:Proceed to any Subject detail screen from filtered by Completed status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectComplete);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectComplete);

		reportLog("10.4:Subject detail screen with Completed status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status, Constants.SubjectStatus_Completed);

		reportLog("11.1:Return to Navigator");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("11.2:Previously selected filter 'Completed' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_Completed);

		reportLog("12.1:Click Discontinued Subject filter ");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.SubjectStatus_Discontinued);

		reportLog("12.2:Displayed Subject list contains subjects with Discontinued status only");
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(
				Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Discontinued);

		reportLog("12.3:Proceed to any Subject detail screen from filtered by Discontinued status");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectDiscontinue);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectDiscontinue);

		reportLog("12.4:Subject detail screen with Discontinued status is displayed");
		subjectDetailPage.verifyDetailStatus(SubjectsModuleConstants.subject_Status,
				Constants.SubjectStatus_Discontinued);

		reportLog("13.1:Return to Navigator");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("13.2:Previously selected filter 'Discontinued' is selected as default");
		studyNavigatorDashBoardPage.verifySubjectStatusFilterIsSelected(Constants.SubjectStatus_Discontinued);

		reportLog("13.3:Logout application");
		loginPage.logoutApplication();

		reportLog("13.4:Verify user is logout");
		loginPage.verifyUserLogout();
   
	}

}
