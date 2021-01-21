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

public class FPTC_1472_ChangeVisitNotAllowedSubjectsOfDiffStudyOrSite_SIP
		extends BaseTest {

	private String studyName2, visitFirstCompletedWithStudy1, visit2NotCompletedNotAssignedStudy2,
			visit3CompletedStudy1Site1, visit4NotCompletedNotAssignedStudy1Site2, subjectWithSite1, SubjectWithSite2,
			subjectStudy2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1472_ChangeVisitNotAllowedSubjectsOfDiffStudyOrSite_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		studyName2 = properties.getProperty("Study2224New");
		visitFirstCompletedWithStudy1 = properties.getProperty("visitClinRoSubmitted");
		visit2NotCompletedNotAssignedStudy2 = properties.getProperty("visitName");
		visit3CompletedStudy1Site1 = properties.getProperty("visitClinRoSubmitted");
		visit4NotCompletedNotAssignedStudy1Site2 = properties.getProperty("visitForRenameStatus");
		subjectWithSite1 = properties.getProperty("Subject2224Site1");
		SubjectWithSite2 = properties.getProperty("SubjectWithSite2");
		subjectStudy2 = properties.getProperty("SubjectWithStudy2");
	}

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1472 Test Case Name: Change Visit: option to change Visit is not allowed between subjects of different Study or Site 
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1472_Change Visit: option to change Visit is not allowed between subjects of different Study or Site", groups = {
			"" })
	public void FPTC_1472_VerifyChangeVisitNotAllowedSubjectsOfDiffStudyOrSite() {

		reportLog("1.1:Log in to Portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:	Navigate to Visit listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.2:select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitFirstCompletedWithStudy1,
				subjectWithSite1);

		reportLog("2.3:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("3.2:	'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("3.1:Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();

		reportLog("4.1:	Check that Visit Pr.#3 isn't displayed in ‘Move To’ Visit drop-down list");
		reportLog("4.2:Visit from Pr.#3 isn't displayed in ‘Move To’ Visit drop-down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.verifySubjectIsNotPresentInChangeToSubjectDropDown(subjectStudy2);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();

		reportLog("5.1:Navigate to Visit listing screen");
		visitDetaiLPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("5.2:select Visit from Pr.#4");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit3CompletedStudy1Site1,
				subjectWithSite1);

		reportLog("6.1:	'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("6.2:	Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();

		reportLog("7.1:	Check that Visit Pr.#5 isn't displayed in ‘Move To’ Visit drop-down list");
		reportLog("7.2:	Visit from Pr.#5 isn't displayed in ‘Move To’ Visit drop-down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.verifySubjectIsNotPresentInChangeToSubjectDropDown(SubjectWithSite2);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();

		reportLog("7.3:Logout application");
		loginPage.logoutApplication();

		reportLog("7.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
