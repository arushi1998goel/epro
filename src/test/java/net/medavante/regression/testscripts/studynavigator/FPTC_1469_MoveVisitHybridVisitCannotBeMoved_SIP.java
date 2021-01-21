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

public class FPTC_1469_MoveVisitHybridVisitCannotBeMoved_SIP extends BaseTest {

	private String visitHybrid, completedNonIrVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1469_MoveVisitHybridVisitCannotBeMoved_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		subjectName = properties.getProperty("subject2193");
		visitHybrid = properties.getProperty("HybridVisit");
		completedNonIrVisit = properties.getProperty("visitForRenameStatus");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1469 Test Case Name: Change Visit - Hybrid (IR and Non-IR) Visit cannot be changed 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1469 _Move Visit Hybrid Visit Cannot BeM oved", groups = {})
	public void FPTC_1469_VerifyMoveVisitHybridVisitCannotBeMoved() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.2:Navigate to Visit listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitHybrid, subjectName);

		reportLog("2.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("2.5:'Action' option is Not displayed");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();

		reportLog("3.1:Navigate to Visit listing screen ");
		visitDetaiLPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("3.2:select Visit from Pr.#4");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonIrVisit, subjectName);

		reportLog("3.3:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("3.4:'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("4.1:Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();

		reportLog("4.2:Check that Visit Pr.#2 isn't displayed in ‘Move To’ Visit drop down list");
		reportLog("4.3:Visit from Pr.#2 isn't displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitHybrid);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();
		
		reportLog("4.4:Logout from the application");
		loginPage.logoutApplication();

		reportLog("4.5:Verify user is logout");
		loginPage.verifyUserLogout();
		
		

	}
}