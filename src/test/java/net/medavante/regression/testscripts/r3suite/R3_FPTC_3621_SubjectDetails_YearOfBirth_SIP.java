/**
 * @author
 * @date 02/01/2020 
 * =========================================================================
 * Test Case Id: FP-TC-3621 
 * Test Case Name: Subject Details - Year of Birth
 *  * pre-qualification :* At least one active Study with at least one Site exists with configured Date of Birth field
                         * At least 1 Subject exists for the Study site Pr#1 with Date Of Birth (Age) value
                         * User with a claim to manage Subjects and Studies exists and associated with the Study Pr.#1
 * ========================================================================= 
 */

package net.medavante.regression.testscripts.r3suite;

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

public class R3_FPTC_3621_SubjectDetails_YearOfBirth_SIP extends BaseTest {

	private String study, 
	Subject1 = "AutoSUBJ_" + generateRandomString(5),Subject="AutoSub002"+ generateRandomString(5),Age="1998 (22)", Year="1998", futureYear="2025",
	SubjectName="Sub_g123"+generateRandomString(5),
	reason="Subject Request",visitName="Visit_CR_FunctionalityTest";
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3621_SubjectDetails_YearOfBirth_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study3621");

		reportLog("1.1: Creating pre-requisites");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(" Navigating to Study conofiguration--Study--> Click Date of Birth CheckBox");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomPage();
		adminstrationStudiesCustomPage.verifyDateOfBirthCheckBoxDisplayed();
		adminstrationStudiesCustomPage.customPageInEditMode();
		adminstrationStudiesCustomPage.checkDateOfBirthCheckBox();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		

		reportLog(" creating subject accordingly pre requisite");

		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(study, Constants.ATAssignedRater_10, SubjectName);
		studyNavigatorDashBoardPage.fillDateOfBirth(Constants.Date, Constants.Month, Constants.Year);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(SubjectName); 
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	@Test(description = "FP-TC-3621 || Subject Details - Year of Birth", groups = { "R3" })
	public void R3_FPTC_3621_subjectDetails_YearOfBirth_SIP() throws InterruptedException {

		reportLog("2.1: Login to Portal as a User Pr.#3.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.2: The user is successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1: Navigate to Configure -> Study Setup -> Studies -> Study Pr.#1-> Custom tab -> Edit mode");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomPage();
		adminstrationStudiesCustomPage.customPageInEditMode();
		
		reportLog("3.2:  Year Of Birth option is displayed in the Subject Field Visibility section");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXDisplayed();
		
		reportLog("3.3: Edit Custom tab is available");
		adminstrationStudiesCustomPage.customPageInEditMode();

		reportLog("4.1: Click on year Of Birth CHKBOX to check the checkbox");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();

		reportLog("4.2: Verify year Of Birth CHKBOX Selected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXSelected();

		reportLog("4.3: verify Date Of Birth Check Box Unselected");
		adminstrationStudiesCustomPage.verifyDateOfBirthCHKBOXUnselected();

		reportLog("4.4: Click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("5.1: Navigate to Study -> Study Pr.#1-> Subjects -> Subject Pr.#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(study, Constants.ATAssignedRater_10);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(SubjectName);

		reportLog("5.2: Subject Pr.#2 details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(SubjectName);
		
		reportLog("5.3: Verify date Of Birth is not displayed");
		subjectDetailPage.verifyDateOfBirthIsNotDisplayed();
		
		reportLog("5.4: Verify year Of Birth display empty");
		subjectDetailPage.verifyYearOfBirthDisplayEmpty();
		
		reportLog("6.1: Select to edit Subject details" );
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("6.2: Edit Subject dialog is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("6.3: Verify year Of Birth display empty");
		subjectDetailPage.verifyYearOfBirthDisplayEmpty();
		
		reportLog("6.4: Option to clean Year Of Birth field is displayed");
		subjectDetailPage.verifyoptionToCleanYearOfBirthdisplayed();

	    reportLog("7.0: Selecting Year Of Birth DropDown");
	    subjectDetailPage.selectYearOfbirthdropDown();
	    
	    reportLog("7.1: Values in [CCYY] format are available to select in Year Of Birth drop-down");
	    subjectDetailPage.verifyValuesInYearofBirthdropDown();
	    
	    reportLog("7.2: Select any year");
		subjectDetailPage.selectYearOfBirth(Year);
		
		reportLog("7.3: Year Of Birth value is displayed");
		subjectDetailPage.verifyYearOfBirthfieldvalue(Year);
		
		reportLog("8.1: click On Save Button");
		subjectDetailPage.editSubjectStatusChange("Save");
		
		reportLog("8.2:Entering Reason for change and  valid credentials  --Select Ok control" );
		subjectDetailPage.enterReasonUserCredentialsforChange(reason,SuperAdminUN, SuperAdminPW);
		
		reportLog("8.2: Subject Pr.#2 details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(SubjectName);
		
		reportLog("8.3: Year Of Birth value is displayed");
		subjectDetailPage.verifyyearOfBirthOnSubjectdetailsPage(Age);
		
		reportLog("8.4: Age value is displayed in brackets correctly");
		subjectDetailPage.verifyAgeFromYearOfBirth(Year);
		
		reportLog("9.1: Navigating Back to subject List");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("9.2: Select to Add Subject--");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		
		/*----------------------------------------------------------------------
		----------------------------------------------------------------------*/
		
		reportLog("9.3: Select Site Pr.#1 from Choose Site drop-down ");
		reportLog("This funtionality In Removed from application UI So Can;t Be automated");
		
		/*----------------------------------------------------------------------
		----------------------------------------------------------------------*/
		
		reportLog("9.4:  --Add Subject dialog is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		
		reportLog("9.5: Date of Birth field is Not displayed");
		subjectDetailPage.verifyDateOfBirthIsNotDisplayed();
		
		reportLog("9.6:  Year Of Birth field is displayed");
		subjectDetailPage.veriFyYearOfBirthDisplayedinsideEditPopup();
		
		reportLog("10.1: Fill in all required fields" );
		studyNavigatorDashBoardPage.fillRequiredfieldsForsubject(Subject1);
		
		reportLog("10.2: Leave Year Of Birth field empty");
		subjectDetailPage.selectYearOfBirth("");
		
		reportLog("10.3: click On Save Button");
		subjectDetailPage.editSubjectStatusChange(Constants.Action_Save);
		
		reportLog("10.4: New Subject is created --- Subject details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(Subject1);
		
		reportLog("10.5: Year Of Birth (Age) field is displayed empty");
		subjectDetailPage.verifyYearOfBirthDisplayEmpty();
		
		reportLog("11.1: Select to edit Subject details");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("11.2: Edit Subject dialog is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("11.3: Select Year Of Birth field");
		subjectDetailPage.selectYearOfbirthdropDown();
		
		reportLog("11.4: Values in [CCYY] format are available to select in Year Of Birth drop-down");
		subjectDetailPage.verifyValuesInYearofBirthdropDown();
		
		reportLog("12.1:  Try to select future year");
		subjectDetailPage.selectYearOfBirth(futureYear); 
		
		reportLog("12.2: Future year is not available");
		subjectDetailPage.verifyYearOfBirthDisplayEmpty();
		
		reportLog("12.3: Select current year");
		subjectDetailPage.selectYearOfBirth(Year);
		
		reportLog("12.4:  Current year is selected");
		subjectDetailPage.verifyYearOfBirthfieldvalue(Year);
		
		reportLog("13.1: click On Save Button");
		subjectDetailPage.editSubjectStatusChange(Constants.Action_Save);
		
		reportLog("13.2:Entering Reason for change and  valid credentials  --Select Ok control" );
		subjectDetailPage.enterReasonUserCredentialsforChange(reason,SuperAdminUN, SuperAdminPW);
		
		reportLog("13.3: Year Of Birth (Age) is displayed in the Subject Details section");
		subjectDetailPage.verifyyearOfBirthOnSubjectdetailsPage(Age);
		
		reportLog("13.4: Age value is displayed in brackets correctly [current year-YOB]");
		subjectDetailPage.verifyAgeFromYearOfBirth(Year);
		
		reportLog("14.1:Select to edit Subject details");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("14.2: Select to delete Year Of Birth field");
		subjectDetailPage.cancelYearOfBirth();
		
		reportLog("14.3: Year Of Birth field is empty");
		subjectDetailPage.verifyYearOfBirthfieldvalue("");
		
		reportLog("15.1: click On Save Button");
		subjectDetailPage.editSubjectStatusChange(Constants.Action_Save);
		
		reportLog("15.2:Entering Reason for change and  valid credentials  --Select Ok control" );
		subjectDetailPage.enterReasonUserCredentialsforChange(reason,SuperAdminUN, SuperAdminPW);
		
		reportLog("15.3: Year Of Birth(Age) is displayed empty in the Subject Details section");
		subjectDetailPage.verifyYearOfBirthDisplayEmpty();
		
		reportLog("16.1: Navigating Back to subject List");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("16.2: Select to Add Subject");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		
		/*----------------------------------------------------------------------
		----------------------------------------------------------------------*/
		
		reportLog("16.3: Select Site Pr.#1 from Choose Site drop-down ");
		reportLog("This funtionality In Removed from application UI So Can;t Be automated");
		
		/*----------------------------------------------------------------------
		----------------------------------------------------------------------*/
		
		reportLog("16.4: Fill in all required fields" );
		studyNavigatorDashBoardPage.fillRequiredfieldsForsubject(Subject);
		
		reportLog("16.5:Entering Year Of Birth" );
		subjectDetailPage.selectYearOfbirthdropDown();
		subjectDetailPage.selectYearOfBirth(Year);
		
		reportLog("16.6: click On Save Button");
		subjectDetailPage.editSubjectStatusChange(Constants.Action_Save);
		
		reportLog("16.7: New Subject is created --- Subject details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(Subject);
		
		reportLog("16.8: Year Of Birth value is displayed correctly");
		subjectDetailPage.verifyyearOfBirthOnSubjectdetailsPage(Age);
		
		reportLog("16.9: Age value is displayed in brackets correctly [current year-YOB]");
		subjectDetailPage.verifyAgeFromYearOfBirth(Year);
		
		reportLog("17.1: Initiate any visit for a Subject");
		subjectDetailPage.initiateVisitforsubject();
		
		reportLog("17.2: Navigate to Visit details page and Verify Year Of Birth value is displayed correctly");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		subjectDetailPage.navigateToVisitandVerifyAgeFromDateOfYear(Subject, visitName, Year); 
		
		reportLog("11.0: Unselecting Year Of birth CheckNOx Again");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.customPageInEditMode();
		adminstrationStudiesCustomPage.unselectYearOfBirthChkBx();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
			
		reportLog("18.1 Logout from the application");
		loginPage = loginPage.logoutApplication();

		reportLog("18.2 Verify user is successfully logout from the application");
		loginPage.verifyUserLogout();		
		
	}	
}