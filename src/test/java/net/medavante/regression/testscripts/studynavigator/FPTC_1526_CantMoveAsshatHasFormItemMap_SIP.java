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

public class FPTC_1526_CantMoveAsshatHasFormItemMap_SIP extends BaseTest {

	private String visitNameSecond;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1526_CantMoveAsshatHasFormItemMap_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitNameSecond = properties.getProperty("Mapped2232VisitName");

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitNameSecond);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subjcet in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameThirdType, visitNameSecond, screeningNum);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();

	}
	
	

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1526 Test Case Name:Cannot move Assessment that has
	 * form item mapping
	 * 
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FPTC_1526_Cannot move Assessment that has form item mapping", groups = { " " })

	public void FPTC_1526_verifyCantMoveAsshatHasFormItemMap() {

		reportLog("1.1:Log in to Portal as a User from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Open the Study Dashboard -> Select a Study and a Site from PR#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.2:Study Dashboard is displayed");
		studyNavigatorDashBoardPage.verifyStudyDashBoardPage();

		reportLog("3.1:	Navigate to the Assessment listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameThirdType, visitNameSecond, screeningNum);

		reportLog("3.2:	Assessment Detail screen is displayed  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.1:	Check that actions for moving an Assessment isn't displayed");
		reportLog("4.2:	Action option isn't displayed for the Assessment with Mapped Items");
		assessmentDetailPage.verifyingActionButtonIsNotDisplayed();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
