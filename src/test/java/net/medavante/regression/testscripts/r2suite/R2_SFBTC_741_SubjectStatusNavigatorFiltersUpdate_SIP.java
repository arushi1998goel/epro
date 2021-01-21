package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class R2_SFBTC_741_SubjectStatusNavigatorFiltersUpdate_SIP extends BaseTest {

	protected String renameAliasFiledText = "Auto_741Test";
	protected String studyName, newVisitName, screenedVisitName, enrolledVisitName, completedVisitName,
			discontinuedVisitName, subjectWithNewStatusName, subjectWithScreennedStatusName,
			subjectWithEnrolledStatusName, subjectCompletedStatusName, subjectDisconutinedStatusName;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_SFBTC_741_SubjectStatusNavigatorFiltersUpdate_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("Test741Study");
		newVisitName = prop.getProperty("visitForRenameNewStatus");
		screenedVisitName = prop.getProperty("visitForRenameScreenedStatus");
		enrolledVisitName = prop.getProperty("visitForRenameEnrolledStatus");
		completedVisitName = prop.getProperty("visitForRenameCompletedStatus");
		discontinuedVisitName = prop.getProperty("visitForRenamediscontinuedStatus");
		subjectWithNewStatusName = prop.getProperty("subjectNewName");
		subjectWithScreennedStatusName = prop.getProperty("subjectScreenedName");
		subjectWithEnrolledStatusName = prop.getProperty("subjectEnrolledName");
		subjectCompletedStatusName = prop.getProperty("subjectCompletedName");
		subjectDisconutinedStatusName = prop.getProperty("subjectDiscontinuedName");
	}

	/**
	 * =========================================================================
	 * Test Case Id: SFB-TC-741 Test Case Name: Visit assessments - Assignment
	 * drop-down
	 * =========================================================================
	 * @throws InterruptedException 
	 * 
	 */
	@Test(description = "SFB-TC-741_verifySubjectStatusNavigatorFiltersUpdate", groups = { "R2" })
	public void SFBTC_741_VerifySubjectStatusNavigatorFiltersUpdate() throws InterruptedException {

		reportLog("1.Login To Application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
	

		reportLog("1.1 Verify Portal Page");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: After Setting Up PreRequisite Click on Adminstration  tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.3:Click on Studies Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4:Select Study For Status Description Editing");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.5:Navigate To Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("1.6:Rename Subject Status Description and Verify");
		adminstrationStudiesCustomPage.editSubjectStatusAliasField(renameAliasFiledText);
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		adminstrationStudiesCustomPage.verifySubjectRenameStatus(renameAliasFiledText);
		
		String subjectNewRenameStatus = adminstrationStudiesCustomPage.getSubjectAliasStatusList(0);
		String subjectScreenedRenameAliasStatus = adminstrationStudiesCustomPage.getSubjectAliasStatusList(1);
		String subjectEnrolledRenameAliasStatus = adminstrationStudiesCustomPage.getSubjectAliasStatusList(3);
		String subjectCompletedRenameAliasStatus = adminstrationStudiesCustomPage.getSubjectAliasStatusList(4);
		String subjectDiscontinuedRenameAliasStatus = adminstrationStudiesCustomPage.getSubjectAliasStatusList(5);
		
		adminstrationStudiesCustomPage.navigateToHome();

		reportLog("2:Navigate To Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.RaterName_21);
	
		
		reportLog("2.2Verify Renamed Statuses are shown in Subject Status default filter");
		studyNavigatorDashBoardPage.verifySubjectRenameStatus(renameAliasFiledText);

		reportLog("3:Navigate To Visit Listing Page");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		

		/*reportLog("3.1:Verify New Renamed SubjectStatus are shown in the Visits list");
		studyNavigatorDashBoardPage.spinnerBecomeInvisible();
		studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithNewStatusName, newVisitName, subjectNewRenameStatus);*/

		reportLog("3.1:Verify New Renamed SubjectStatus are shown in the Visits list");
		studyNavigatorDashBoardPage.spinnerBecomeInvisible();		
		studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithNewStatusName, newVisitName, subjectNewRenameStatus);
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(newVisitName, subjectWithNewStatusName);


		reportLog("4.1: New Renamed SubjectStatus are shown in the Visits Detail Page");
		visitDetaiLPage.verifySubjectStatus(subjectNewRenameStatus);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("3.2:Verify and Navigate To Visit List of ScreenedSubject Status");
		/*studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithScreennedStatusName, screenedVisitName,
				subjectScreenedRenameAliasStatus);*/
	     studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(screenedVisitName,
				subjectWithScreennedStatusName);


		reportLog("4.2:Rename ScreenedSubject Status is shown on the Visit details page");
//		visitDetaiLPage.verifySubjectStatus(subjectScreenedRenameAliasStatus);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("3.3:Verify and Navigate To Visit List pf EnrolledSubject Status");
		/*studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithEnrolledStatusName, enrolledVisitName,
				subjectEnrolledRenameAliasStatus);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(enrolledVisitName, subjectWithEnrolledStatusName);


		reportLog("4.3:Renamed EnrolledSubjectStatus is shown on the Visit details page");
//		visitDetaiLPage.verifySubjectStatus(subjectEnrolledRenameAliasStatus);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("3.4:Verify and Navigate To Visit List Page of CompletedSubject Status Visit");
		/*studyNavigatorDashBoardPage.verifySubjectStatus(subjectCompletedStatusName, completedVisitName,
				subjectCompletedRenameAliasStatus);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedVisitName, subjectCompletedStatusName);


		reportLog("4.4:Renamed CompletedSubjectStatus is shown on the Visit details page");
//		visitDetaiLPage.verifySubjectStatus(subjectCompletedRenameAliasStatus);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("3.5:Verify and Navigate To Visit List Page of DiscontinuedSubject Status");
		/*studyNavigatorDashBoardPage.verifySubjectStatus(subjectDisconutinedStatusName, discontinuedVisitName,
				subjectDiscontinuedRenameAliasStatus);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(discontinuedVisitName,
				subjectDisconutinedStatusName);


		reportLog("4.5:Renamed Discontinued Status is shown on the Visit details page");
		visitDetaiLPage.verifySubjectStatus(subjectDiscontinuedRenameAliasStatus);
		visitDetaiLPage.navigateBackToPreviousPage();
		visitDetaiLPage.navigateToHome();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		
		reportLog("5.1:Navigate to Subjects list and Verify NewRename SubjectStatus");		
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithNewStatusName, subjectNewRenameStatus);

		reportLog("6.1:Navigate to New Subject details page");		
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectWithNewStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", subjectNewRenameStatus);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("5.2:Navigate to Subjects list and Verify ScreenedRename SubjectStatus");
	/*	studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithScreennedStatusName, subjectScreenedRenameAliasStatus);*/

		reportLog("6.2:Navigate to Subject details page and Verify ScreenedRename SubjectStatus");
		studyNavigatorDashBoardPage.clickOnSubject(subjectWithScreennedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", subjectScreenedRenameAliasStatus);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("5.3:Navigate to Subject List page of EnrolledSubjectStatus");
		
/*		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithEnrolledStatusName, subjectEnrolledRenameAliasStatus);
*/
		reportLog("6.3:Verify SubjectDetail Page and Verify Rename EnrolledSubjectStatus");
		studyNavigatorDashBoardPage.clickOnSubject(subjectWithEnrolledStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", subjectEnrolledRenameAliasStatus);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("5.4:Navigate to Subject list page of CompletedSubjectStatus ");
//		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectCompletedStatusName, subjectCompletedRenameAliasStatus);

		reportLog("6.4:Naviagte To SubjectDetail Page Verify Rename CompletedSubjectStatus");
		studyNavigatorDashBoardPage.clickOnSubject(subjectCompletedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", subjectCompletedRenameAliasStatus);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("5.5:Navigate to Subject list for DisContinuedSubjectStatus");
/*		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectDisconutinedStatusName, subjectDiscontinuedRenameAliasStatus);*/

		reportLog("6.5 Verify  And SubjectDetail Page Verify Rename DisContinuedSubjectStatus");
		studyNavigatorDashBoardPage.clickOnSubject(subjectDisconutinedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", subjectDiscontinuedRenameAliasStatus);

		reportLog("7: Navigate to Studies tab, select Study custom and activate Edit mode - Clear custom statuses labels and Save changes");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.clearCustomSubjectStatus();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		adminstrationStudiesCustomPage.navigateToHome();

		reportLog("8:Navigate to Study Navigator ");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("8.1:Verify Status filters are shown with default lables ");
		studyNavigatorDashBoardPage.verifySubjectsStatusesForSubjectsOnStudyNavigator();

		reportLog("9:Navigate to Study Visits list ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("9.1: Verify Default  NewSubjectStatus  shown in the Visits list ");
		/*studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithNewStatusName, newVisitName,
				SubjectsModuleConstants.subject_NEW_Status);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(newVisitName, subjectWithNewStatusName);


		reportLog("10.1:Verify Default NewSubjectStatus is shown in visit Detail page");
		visitDetaiLPage.verifySubjectStatus(SubjectsModuleConstants.subject_NEW_Status);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("9.2: Verify Default  ScreenedSubjectStatus is  shown in the Visits list ");
	/*	studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithScreennedStatusName,screenedVisitName,SubjectsModuleConstants.subject_Screned_Status);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(screenedVisitName, subjectWithScreennedStatusName);
		
		

		reportLog("10.2:Verify Default ScreenedSubjectStatus is shown in visit Detail page");
		visitDetaiLPage.verifySubjectStatus(SubjectsModuleConstants.subject_Screned_Status);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("9.3: Verify Default  Enrolled SubjectStatus are shown in the Visits list ");
	/*	studyNavigatorDashBoardPage.verifySubjectStatus(subjectWithEnrolledStatusName,enrolledVisitName,SubjectsModuleConstants.subject_Enrolled_Status);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(enrolledVisitName, subjectWithEnrolledStatusName);


		reportLog("10.3:Verify Default EnrolledSubjectStatus is shown in visit Detail page");
		visitDetaiLPage.verifySubjectStatus(SubjectsModuleConstants.subject_Enrolled_Status);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("9.4: Verify Default  CompletedSubjectStatus are shown in the Visits list ");
	/*	studyNavigatorDashBoardPage.verifySubjectStatus(subjectCompletedStatusName,completedVisitName,SubjectsModuleConstants.subject_Completed_Status);*/
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedVisitName, subjectCompletedStatusName);
	


		reportLog("10.4:Verify Default CompletedSubjectStatus is shown in visit Detail page");
//		visitDetaiLPage.verifySubjectStatus(SubjectsModuleConstants.subject_Completed_Status);
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("9.5: Verify Default  DisContinueSubjectStatus are shown in the Visits list ");
	  /*  studyNavigatorDashBoardPage.verifySubjectStatus(subjectDisconutinedStatusName,discontinuedVisitName,SubjectsModuleConstants.subject_Discontinued_Status);*/
	    studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(discontinuedVisitName, subjectDisconutinedStatusName);
	


		reportLog("10.5:Verify Default DiscontinueSubjectStatus is shown in visit Detail page");
//		visitDetaiLPage.verifySubjectStatus(SubjectsModuleConstants.subject_Discontinued_Status);
		visitDetaiLPage.navigateBackToPreviousPage();
		visitDetaiLPage.navigateToHome();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("11.1:Navigate to Subjects list and Verify NewRename SubjectStatus");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithNewStatusName, SubjectsModuleConstants.subject_NEW_Status);

		reportLog("12.1:Navigate to New Subject details page");
		studyNavigatorDashBoardPage.clickOnSubject(subjectWithNewStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", SubjectsModuleConstants.subject_NEW_Status);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("11.2:Navigate to Subject List and SubjectDetail Page Verify Rename ScreenedSubjectStatus");
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithScreennedStatusName, SubjectsModuleConstants.subject_Screned_Status);

		reportLog("12.2:Navigate to Screenned Subject details page");
		studyNavigatorDashBoardPage.clickOnSubject(subjectWithScreennedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", SubjectsModuleConstants.subject_Screned_Status);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("11.3:Navigate to Subject List page and SubjectDetail Page and Verify Rename EnrolledSubjectStatus");
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectWithEnrolledStatusName, SubjectsModuleConstants.subject_Enrolled_Status);

		reportLog("12.3:Navigate to Enrolled Subject details page");
		studyNavigatorDashBoardPage.clickOnSubject(subjectWithEnrolledStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", SubjectsModuleConstants.subject_Enrolled_Status);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("11.4:Navigate to Subject list And SubjectDetail Page Verify Rename CompletedSubjectStatus");
		/*studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectCompletedStatusName, SubjectsModuleConstants.subject_Completed_Status);*/

		reportLog("12.4:Navigate to Completed Subject details page");
		studyNavigatorDashBoardPage.clickOnSubject(subjectCompletedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", SubjectsModuleConstants.subject_Completed_Status);
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("11.5:Navigate to Subject list And SubjectDetail Page Verify Rename DisContinuedSubjectStatus ");
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectDisconutinedStatusName,
				SubjectsModuleConstants.subject_Discontinued_Status);

		reportLog("12.5:Navigate to DisContinued Subject details page");
		studyNavigatorDashBoardPage.clickOnSubject(subjectDisconutinedStatusName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus("Status", SubjectsModuleConstants.subject_Discontinued_Status);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
