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

public class FPTC_1468_IRVisitCannotBeChanged_SIP extends BaseTest {

	private String subjectName1, subjectName2, completedIrVisit, completedNonIrVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1468_IRVisitCannotBeChanged_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		subjectName1 = properties.getProperty("subject2193");
		subjectName2 = properties.getProperty("subjectsecond2193");
		completedIrVisit = properties.getProperty("IRVisit");
		completedNonIrVisit = properties.getProperty("NonCRCompletedVisit2193");
		
		}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1468 Test Case Name: Change Visit - IR Visit cannot be changed 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1468 _Change Visit - IR Visit cannot be changed ", groups = {})
	public void FPTC_1468_VerifyIRVisitCannotBeChanged() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.2:Navigate to Visit listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedIrVisit, subjectName1);

		reportLog("2.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2.6:'Action' option isn't displayed for CR Visit");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();

		reportLog("3.1: Navigate to Visit listing screen and select Visit from Pr.#4");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonIrVisit, subjectName1);
		
		reportLog("3.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("3.5:'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("3.2: Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		
		reportLog("3.3:Check that Visit Pr.#2 isn't displayed in ‘Move To’ Visit drop down list");
		reportLog("3.6: Visit from Pr.#2 isn't displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName1);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsNotPresentInChangeToVisitDropDown(completedIrVisit);		
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();

		reportLog("4.1: Navigate to Visit listing screen and select Visit from Pr.#4");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonIrVisit, subjectName1);
		
		reportLog("4.4: Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("4.5:'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("4.2:Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();

		reportLog("4.3:Check that Visit Pr.#3 isn't displayed in ‘Move To’ Visit drop down list");
		reportLog("4.6: Visit from Pr.#3 isn't displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName2);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsNotPresentInChangeToVisitDropDown(completedIrVisit);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();
			

		reportLog("4.7: Logout from the application");
		loginPage.logoutApplication();

		reportLog("4.8: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
