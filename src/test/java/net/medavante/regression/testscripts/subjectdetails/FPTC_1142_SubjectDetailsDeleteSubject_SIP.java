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

public class FPTC_1142_SubjectDetailsDeleteSubject_SIP extends BaseTest {

	private String studyName;
	private String subjectName = "Subj_" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1142_SubjectDetailsDeleteSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");

		reportLog("Creating a New subject from user");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1142 Test Case Name: Show that Subject could be
	 * deleted in case of No devices registered for Subject
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1142: Subject Details - Delete Subject", groups = { "" })

	public void FPTC_1142_verifySubjectCouldBeDeletedInCaseOfNoDevicesRegisteredForSubject() {

		reportLog("1.1: Log in to the Portal as a User from Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2: Navigate to Study Navigator Pr.#1 and proceed to Subjects Listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.2: Navigate to the Subject details page of the subject and click on visit");
		reportLog("2.3: Subjects Listing screen is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("3: Select Subject Pr#3.1");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("3.1: Subject Detail screen is displayed");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("4:  Select action to delete Subject Pr#3.1 ");
		subjectDetailPage.verifySubjectDeleteBtnIsDisplayed();

		reportLog("4.1: Confirm deletion");
		subjectDetailPage.deleteSubject();

		reportLog("5: Navigate back to listing page and Select Subject Pr#3.2");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("6: Select action to delete Subject Pr#3.2");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);

		reportLog("6.1: Subject isn't available to deleting (isn't deleted)");
		studyNavigatorDashBoardPage.verifyNoRecordPageIsDisplayed();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
