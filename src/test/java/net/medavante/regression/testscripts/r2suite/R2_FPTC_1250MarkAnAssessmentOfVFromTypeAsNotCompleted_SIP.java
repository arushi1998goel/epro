package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1250MarkAnAssessmentOfVFromTypeAsNotCompleted_SIP extends BaseTest {

	protected String subjectName = "Subj1783" + generateRandomString(3), visitName, userNameWithClaim;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1250MarkAnAssessmentOfVFromTypeAsNotCompleted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userNameWithClaim = userCredentials.getProperty("PRODSiteCoordinatorCRSelfSchedule");
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitName1783");

		/* Create Subject */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1250 Test Case Name: Mark an assessment of VFrom type
	 * as Not Completed
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1250--Mark an assessment of VFrom type as Not Completed", groups = { "R2" })

	public void FPTC_1250_verifyMarkAnAssessmentOfVFromTypeAsNotCompleted() throws InterruptedException {

		reportLog(
				"1.1  Login to the portal as the user exists having claim canManageAssessments i.e. with " + userName);
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.3: Navigate to Assesment lisiting");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.4: Select " + subjectName + " subject from Assesment list");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitName, subjectName);

		reportLog("2.5:Select an action (...) to view Assessment completion option");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.6 Verify Option to mark the assessments of Pr#4 as Not Completed is visible");
		assessmentDetailPage.verifyCheckBoxIsPresent();

		reportLog("3.1:Select the option to mark the Assessment as Not Complete and save");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("3.2:E-sign control appears with the list of reasons for change");
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeValuesList);
		assessmentDetailPage.setStartedDate();
		
		reportLog("4.1	Select a reason and submit with e-sign");
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("4.2:Assessment of Step#2 is completed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("5:Open the Assessment PDF");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("5.2:Logout the user");
		loginPage.logoutApplication();

		reportLog("5.3:Verify user Logout");
		loginPage.verifyUserLogout();

	}

}
