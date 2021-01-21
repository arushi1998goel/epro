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

public class FPTC_1531_AsseNotDisplayedInMoveToFilter_SIP
		extends BaseTest {

	private String visitNameFirst, visitNameSecond, subjectNameSelect;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1531_AsseNotDisplayedInMoveToFilter_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectNameSelect=properties.getProperty("Subject2232");
		visitNameFirst=properties.getProperty("Assessment2232");
		visitNameSecond=properties.getProperty("Mapped2232VisitName");

	}

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1531 Test Case Name:Assessment is not displayed in
	 * Move To filter in case when Assessment has at least one form item mapping
	 * 
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1531_Assessment is not displayed in Move To filter in case when Assessment has at least one form item mapping", groups = {
			" " })

	public void FPTC_1531_verify_AsseNotDisplayedInMoveToFilter() {

		reportLog("1.1:Log in to Portal as a User from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User successfully logged in");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.1:Navigate to Assessment Listing screen and select Assessment from Pr.#2");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		reportLog("2.2:Assessment Detail screen is displayed");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameThirdType, visitNameFirst, subjectNameSelect);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("3.1:	'Action' option is displayed");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("3.2:	Select an action to move Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("4.1:	Check that Assessment Pr.#3 isn't displayed in ‘Move To’ Assessment drop down list");
		reportLog("4.2:Assessment from Pr.#3 isn't displayed in ‘Move To’ Assessment drop down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.verifySubjectIsNOtPresntInMoveToSubjectListDrpDwn(subjectNameSelect);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
