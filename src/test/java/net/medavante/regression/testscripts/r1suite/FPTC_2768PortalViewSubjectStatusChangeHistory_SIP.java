package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2768PortalViewSubjectStatusChangeHistory_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2768PortalViewSubjectStatusChangeHistory_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("tabletFormSettingStudy");
		subjectNameNew = prop.getProperty("SubjectNew");
		subjectNameScreened = prop.getProperty("SubjectScreened");
		subjectNameEnrolled = prop.getProperty("SubjectEnrolled");
		subjectNameCompleted = prop.getProperty("SubjectCompleted");
		subjectNameDiscontinued = prop.getProperty("SubjectDisContinued");
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * ====================================================================================================================


	 * Test Case Id: FP-TC-2768  Test Case Name: Portal: View Subject Status
	 * Change History
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FPTC_2768PortalViewSubjectStatusChangeHistory", groups = { "R1" })

	public void FPTC_2768_verifyUsersCanViewSubjectStatusHistoryListOnPortal() {

		reportLog("1. Log in to the Portal as User Pr#2.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2: Navigate to the Subject Details page from Pr#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.2:Verify Subject's Listing Page is opened with status as New");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("2.3 Select the subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectNameNew);

		reportLog("2.4: Validate the status before making any change");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_New);

		reportLog("3: Select the control to open the Subject Status history");
		subjectDetailPage.clickOnShowHistory();

		reportLog("3.1: Subject Status History modal window is opened. ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("3.2: All records are sorted by date, the latest record on the top.");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();

		reportLog("4. Close modal window by 'close' ('X') button");
		subjectDetailPage.clickOnCloseHistory();

		reportLog("5.1: Check status for Screened subject");
		viewSubjcetStatusChangeHistoryForExistingStatutes(subjectNameScreened, Constants.Status_Screened);
		reportLog("5.2: Check status for Enrolled subject");
		viewSubjcetStatusChangeHistoryForExistingStatutes(subjectNameEnrolled, Constants.Status_Enrolled);
		reportLog("5.3: Check status for Completed subject");
		viewSubjcetStatusChangeHistoryForExistingStatutes(subjectNameCompleted, Constants.Status_Completed);
		reportLog("5.4:  Check status for Discontinued subject");
		viewSubjcetStatusChangeHistoryForExistingStatutes(subjectNameDiscontinued, Constants.Status_Discontinued);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

	public void viewSubjcetStatusChangeHistoryForExistingStatutes(String subject, String status) {

		reportLog("Navigate to Home");
		studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		reportLog("Click on Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		reportLog("Navigate to Subject Listing page");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		reportLog("Select subject from listing page");
		studyNavigatorDashBoardPage.clickOnSubject(subject);
		reportLog("Verify selected subject status");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, status);
		reportLog("Click on Show History icon");
		subjectDetailPage.clickOnShowHistory();
		reportLog("Verify history pop up");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		reportLog("Verify History Information");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		reportLog("Close the history pop up");
		subjectDetailPage.clickOnCloseHistory();

	}
}
