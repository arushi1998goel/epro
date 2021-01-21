package net.medavante.regression.testscripts.studynavigator;

import org.testng.annotations.Test;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1461_VerifyMoveVisitOptionToMoveVisitDidntAppear_SIP
		extends BaseTest {

	private String visitNameFirst, visitNameSecond, subjectNameSelect;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1461_VerifyMoveVisitOptionToMoveVisitDidntAppear_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		visitNameFirst = properties.getProperty("MoveVisit1");
		visitNameSecond = properties.getProperty("MoveVisit2");
		subjectName = properties.getProperty("SubjectName2144");
		subjectNameSelect = properties.getProperty("SubjectNameSelect2144");
	}

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1461 Test Case Name: Move Visit - option to move
	 * Visit didn't appear in case when at least one assessment for Visit is
	 * assigned
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1461_Move Visit - option to move Visit didn't appear in case when at least one assessment for Visit is assigned", groups = {
			" " })

	public void FPTC_1461_VerifyMoveVisitOptionToMoveVisitDidntAppearInCaseWhenAtLeastOneAssessmentForVisitIsAssigned() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.2:Navigate to Visit listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNameFirst, subjectName);

		reportLog("2.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3.1:	'Action' option is displayed");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("3.2:	Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		reportLog("4.1:Check that Visit Pr.#3 isn't displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectNameSelect);
		visitDetaiLPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitNameSecond);
		
		reportLog("4.2:Click On Close Icon Of Move Visit PopUp");
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();

		reportLog("4.2: Logout from the application");
		loginPage.logoutApplication();

		reportLog("4.3: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}

