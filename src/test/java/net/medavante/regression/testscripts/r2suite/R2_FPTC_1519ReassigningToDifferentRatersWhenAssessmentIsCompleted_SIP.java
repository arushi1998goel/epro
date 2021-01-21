package net.medavante.regression.testscripts.r2suite;

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

public class R2_FPTC_1519ReassigningToDifferentRatersWhenAssessmentIsCompleted_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1519ReassigningToDifferentRatersWhenAssessmentIsCompleted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userName = userCredentials.getProperty("PRODSiteCoordinator");
		studyName = prop.getProperty("AutomationStudyName");
		visitSubmitted = prop.getProperty("visitClinRoSubmitted");
		visitNotAssigned = prop.getProperty("visitClinRoNotAssigned");
		
		
		 /*------Creating Subject and assigning rater for Pre-Requisite-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitSubmitted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_11);
		subjectDetailPage.clickOnVisitRow(visitNotAssigned);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitSubmitted);
		assessmentDetailPage=subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBack();
		loginPage.logoutApplication();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1519 Test Case Name:Re-assigning to different raters
	 * when Assessment is Completed
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1519_ VerifyReassigningToDifferentRatersWhenAssessmentIsCompleted ", groups = { "R2" })

	public void FPTC_1519_VerifyReassigningToDifferentRatersWhenAssessmentIsCompleted() throws InterruptedException {

		reportLog("1:Login to The Application");
		dashBoardPage =loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select Study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.waitSpinnerToBecomeInvisible();

		reportLog("1.4: Open Subject PR#3 ");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);

		reportLog("1.5:Verify Subject DetailPage Opened");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.6:Verify Visit Status for One Assessment was submitted and another one is Not Assigned");
		subjectDetailPage.clickOnVisitRow(visitSubmitted);

		reportLog("1.7:Verify One Assessment was submitted");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_11);

		reportLog("1.8 Verify for Submitted Assesment Rater dropDown Not Displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();

		reportLog("1.9: Vetify One Assesment was Not Assigned");
		subjectDetailPage.clickOnVisitRow(visitNotAssigned);

		reportLog("1.10: Verify Assesment Not Assigned ");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);

		reportLog("2:Navigate to the Visits Listing screen from Study Navigator and open the Visit from PR#3");
		dashBoardPage=subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("2.1:Navigate To Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2.2:Select Study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.3:Navigate To Visit Listing Page");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.4: Search For  Submitted Visit");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitSubmitted,screeningNum);

		reportLog("2.5: Visit Detail Page Displayed For Submitted Assesment");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("2.6: Verify ReAssignment DropDown Not Displayed For Assesment");
		visitDetaiLPage.verifyRaterDropIsNotPresent();

		reportLog("2.8:Navigate Back To Visit Listing Page");
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("2.9: Search For  Not Assigned Visit");
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNotAssigned, screeningNum);

		reportLog("2.10: Visit Detail Page Displayed For Not Assigned Assesment");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
