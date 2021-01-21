package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesDataLockPage;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3357_DataLock_MAP extends BaseTest {

	private String study,SubjectName = "SUBJ_" + generateRandomString(5);
	
	@Factory(dataProvider="Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3357_DataLock_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception
	{
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("Study2831");
		
		reportLog("1.1: Creating pre-requisites");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog(" Creating a subject");
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(study, Constants.ATAssignedRater_10, SubjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("At least one Open Query for an Assessment");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.addNewQuery(SubjectName);
		subjectDetailPage.clickOnQueriesCollpaseIcon();
		
		
		
		reportLog(" Creating At least one Completed Visit" );
		assessmentDetailPage=subjectDetailPage.completeVisitFromInitiateToComplete(Constants.Auto_Visit, AssessmentsDetailsPage.class);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);

		subjectDetailPage.moveBackToSubject(SubjectName);
		
		reportLog(" At least one Paper Transcription assessment without source exists");
		
		assessmentDetailPage=subjectDetailPage.completeVisitFromInitiateToComplete(Constants.PaperTranscription_Visit, AssessmentsDetailsPage.class);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnNotAdministeredChkBoxToUnselectProceedToPaperTranscription(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnPaperTrnscriptionCheckBox();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.moveBackToSubject(SubjectName);
		
		reportLog("At least one Editing Visit (2 assessments in the visit. The first was already edited and completed. The second assessment is in editing state)");
		assessmentDetailPage=subjectDetailPage.completeVisitFromInitiateToComplete(Constants.Auto_Visit_4, AssessmentsDetailsPage.class);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
	    loginPage.logoutApplication();
	    

	}
	
	
	/**
	 * 
     *   Show that a User with specific claims can apply a data lock
     *   Show that Warnings list is displayed for the Study by lock date
     *    Show that the User can generate a file with Warnings list
     *    Show that Details list is updated by a new entry when Data is locked
	 * 
	 * 
	 */
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3357 Test Case Name: Data Lock-
	 * Subjects List
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */
	
	@Test(description="R3-FP-TC-3357----Data Lock- Show that a User with specific claims can apply a data lock")
	public void R3_FPTC_3357_verifyDataLock()
	{
		
		reportLog("2.0:  Log in to the Portal as a User Pr.#1" );
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			
		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0:  Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.0.1:  Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.0.2:  Search " + study + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		
		reportLog("3.0.3: Study configuration is displayed" );
		adminstrationStudiesPage.verifyStudyConfigurationElementsAreDisplayed();
		
		reportLog("3.0.4: Navigate to Data Lock Tab");
		admindataLockTabPage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabDataLock,AdministrationStudiesDataLockPage.class );
				
		reportLog("3.1: Data Lock screen is displayed with such controls:");
		admindataLockTabPage.verifyAdministrationStudiesDataLockPage();
		
		reportLog("3.2: -- Lock Date field (required)");
		admindataLockTabPage.verifyDataLockTabLockDateFielddisplayed();
		
		reportLog("3.3: -- Analyze control");
		admindataLockTabPage.verifyAnalyzeControlIsDisplayed();
		
		reportLog("3.4: -- Lock control");
		admindataLockTabPage.verifyLockControlIsDisplayed();
		
		reportLog("3.5: -- Details list");
		admindataLockTabPage.verifyDetailsListIsDisplayed();
		
		reportLog("4.0: Select Lock Date field");
		admindataLockTabPage.clickToSelectDateButton();
		
		reportLog("4.1: Select future date");
		admindataLockTabPage.selectFutureDate();
		
		reportLog("4.2: User is unable to select future date");
		admindataLockTabPage.verifyselectFutureDateIsDisabled();
		
		reportLog("5.0: Select the date in the past");
		admindataLockTabPage.selectPastDate();
		
		reportLog("5.1: The Date is selected");
		admindataLockTabPage.verifySelectPastDateIsEnabled();
		
		reportLog("6.0: Select the current date");
		admindataLockTabPage.clickToSelectDateButton();
		admindataLockTabPage.selectCurrentDate(); 
		
		reportLog("Current Date is selected");
		admindataLockTabPage.verifyCurrentDateSelectionIsenabled();
		
		reportLog("7.0: Select Analyze control");
		admindataLockTabPage.clickOnAnalyzeControlButton();
		
		reportLog("7.1: A modal window is displayed");
		admindataLockTabPage.verifyModalWindowISDisplayed();
		
		reportLog("7.2: Warnings list of Assessments is displayed for the Study by lock date");
		admindataLockTabPage.verifywarningListDataISDisplayed();
		
		reportLog("7.3:  Warnings for Pr#2.2 - Pr#2.5 are displayed in the list");
		admindataLockTabPage.verifyRequisiteWarningDataIsDisplayed(Constants.VisitStatus_progress);
		admindataLockTabPage.verifyRequisiteWarningDataIsDisplayed(Constants.VisitStatus_Papertranscription);
		
		reportLog("7.4: Export to CSV control Displayed");
		admindataLockTabPage.verifyExportToCSVControlIsDisplayed();
		
		reportLog("8.0: Select an action to export to CSV");
		reportLog("File is automatically downloaded");
		admindataLockTabPage.clickOnExportToCSVButton();
		
		reportLog("9.0:  Close a modal window");
		admindataLockTabPage.closeModalWindow();
		
		reportLog("9.1: Select Lock control on Data Lock tab");
		admindataLockTabPage.clickToSelectDateButton();
		admindataLockTabPage.selectCurrentDate(); 
		admindataLockTabPage.clickOnLockcontrolButton();
		
		reportLog("Confirm the action");
		admindataLockTabPage.clickOnConfirmAction(Constants.Actionaccept);
		
		reportLog("9.2: A new entry is added to the Details list");
		admindataLockTabPage.verifyNewLockEntryStatus(Constants.initiated_Status);
		
		reportLog("10.0: Refresh the page");
		admindataLockTabPage.refreshPage();
		admindataLockTabPage.refreshPage();
		admindataLockTabPage.refreshPage();
		
		reportLog("10.1:  Data is locked");
		reportLog(" Status is changed to Completed");
		admindataLockTabPage.verifyNewLockEntryStatus(Constants.Completed_Status);
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
		
	}
}
