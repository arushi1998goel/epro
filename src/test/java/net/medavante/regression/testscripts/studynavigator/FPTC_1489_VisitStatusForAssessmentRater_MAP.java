package net.medavante.regression.testscripts.studynavigator;

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

public class FPTC_1489_VisitStatusForAssessmentRater_MAP extends BaseTest {
	private String visitName, visitNotAssignedName,
			subjectName = "AutoSubject2214" + generateRandomAlphanumericString(5), subjectNameNotassigned="SubjectRater"+generateRandomAlphanumericString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1489_VisitStatusForAssessmentRater_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitClinRoSubmitted");
		visitNotAssignedName = properties.getProperty("visitForRenameStatus");

		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		/*Create Subject Without Assign rater*
		 * 
		 */
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(
				subjectNameNotassigned);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitNotAssignedName);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1489 Test Case Name: Mark and unmark as 'Not Administered' Assessment for Not Assigned rater changes the status of assessment 
	 * 
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1489_VisitStatusForMarkAsNotAdministeredForAssignedAndNotAssignedRater", groups = {})
	public void FPTC_1489_VerifyVisitStatusForAssessmentRater()
			throws InterruptedException {

		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("1.2:Login is successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("2.1:Navigate to the Study Dashboard > Assessments Listing screen and open the Assessment from PR#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);
		
		reportLog("2.2:Assessment Details screen is opened successfully");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("3.1:Set checkbox to Mark as Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		
		reportLog("3.2:Checkbox is set ");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		
		reportLog("3.3:Confirmation control appears");
		assessmentDetailPage.conifrmButtonAppears();
		
		reportLog("4.1:Select the confirmation control");
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		
		reportLog("4.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("5.1:Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("5.2:Assessment goes to 'Complete' status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		
		reportLog("5.3:Not Administered checkbox is set");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
		reportLog("6.1:Navigate to the Study Dashboard > Assessments Listing screen and open the Assessment from PR#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		//studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitNotAssignedName, subjectNameNotassigned);
		
		reportLog("6.2:Assessment Details screen is opened successfully");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("7.1:Set checkbox to Mark as 'Not Administered'");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		
		reportLog("7.2:Checkbox is set ");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		
		reportLog("7.3:Confirmation control appears");
		assessmentDetailPage.conifrmButtonAppears();
		
		reportLog("8.1:Select the confirmation control");
		assessmentDetailPage.clickOnConfirmButton();
		
		reportLog("8.2:	Confirmation message window appears.");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();
		
		reportLog("9.1:	Select the confirmation control on the Confirmation message window");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		
		reportLog("9.2:	Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("10.1:Select the reason for change -Enter username and password - Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("10.2: Assessment receives Complete status ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		
		reportLog("10.3:Not Administered checkbox is set");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		
		reportLog("11.1:Unset checkbox 'Not Administered'");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		
		reportLog("11.2:Confirm control appears");
		assessmentDetailPage.conifrmButtonAppears();
		
		reportLog("11.3:Checkbox in unset");
		assessmentDetailPage.verifyNotAdministeredCheckBoxFlagNotSet();
		
		reportLog("12.1:Select the confirmation control");
		assessmentDetailPage.clickOnConfirmButton();
		
		reportLog("12.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("13.1:Select the reason for change -Enter username and password - Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("13.2:Checkbox is unset ");
		assessmentDetailPage.verifyCheckBoxMarkAsNotAdministeredNotSet();
		
		reportLog("13.3:Reason for Change window is closed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
	}
}
