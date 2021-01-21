package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1228SystemValidatesSubjectNumberWhileEditingASubject_SIP extends BaseTest {

	private String studyToBesearched,screeningNumber = "SN", randomizationNumber = "Rand-1561_1", mask = "-###",
			temporaryID = "15", tempMask = "##A",subjectName="Auto1917"+generateRandomAlphanumericString(6);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1228SystemValidatesSubjectNumberWhileEditingASubject_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyToBesearched = prop.getProperty("studyToBeSearched");

		System.setProperty("className", getClass().getSimpleName());
		
		/*Create Subject For Configuration*/
		
		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		/*Clear Custom Configuration*/
		reportLog("Move to custom screena and Clear Custom Configuration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyToBesearched);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.clearAllField();
		adminstrationStudiesCustomPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
		/*Create Subject*/
		reportLog("Create Subject");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyToBesearched, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
		
		
		

	}

	/**
	 * =========================================================================
	 * Test Case Id : FPTC_1228 Test Case Name :System Validates Subject
	 * Number While Editing A Subject
	 * =========================================================================
	 * @throws InterruptedException 
	 * 
	 */

	@Test(description = "FPTC_1228 System Validates Subject Number While Editing A Subject", groups = { "R1" })
	public void FPTC_1228_verifySystemValidatesSubjectNumberWhileEditingASubject() throws InterruptedException {

		reportLog("1:Login in to application to verify TC#1");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("2:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.1:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.2:Navigate To Studies Custom Tab");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.3:Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyToBesearched);

		reportLog("2.4:Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("2.5:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("2.6:Navigate To Studies Site Tab for getting site user detail");
		administrationStudiesSitePage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("3:Navigate back to Study Custome tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("3.1:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("4:Configuration of Screening,Randomization Number and Temporary ID for TC#1");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID(screeningNumber + mask,
				randomizationNumber + mask, temporaryID + tempMask);

		reportLog("4.2:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("4.3:Verification of configured of Screening,Randomization Number and Temporary ID for TC#1");
		adminstrationStudiesCustomPage.verifyFieldFormatsAreSameAsConfigured(screeningNumber + mask,
				randomizationNumber + mask, temporaryID + tempMask);

		reportLog("4.4: Logout application");
		loginPage.logoutApplication();

		reportLog("4.5: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		/*
		 * Login with user with userCanManageSubject claim and verify subject
		 * details in edit mode
		 */

		reportLog("5:Login with user having canManageSubjects claim");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("6:Click On Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("6.1:Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyToBesearched,Constants.ATAssignedRater_10);

		reportLog("6.2:Select the Subject From the subject list");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		
		reportLog("7:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("8:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectOpenedInEditMode();

		reportLog("9:Verify subject can't be saved with empty Screening Number with Mask");
		subjectDetailPage.verifySubjectCantSavedWIthEmptyValues();

		reportLog("9.1:Verify user cancel the edit box and logout from the application");
		subjectDetailPage.clickOnCancelBtn();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		/*
		 * Verify for TC 2 when Screening#(With Mask)
		 * Randomization#(NotRequired),TemporaryID With Mask(Required)
		 */

		reportLog("10:Login in to application to verify TC#2");
		loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("11:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("11.1:Navigate To Studies");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("11.2:Navigate To Studies Custom Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("11.3:Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyToBesearched);

		reportLog("11.4:Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("11.5:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("11.6:Navigate To Studies Site Tab for getting site user detail");
		administrationStudiesSitePage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("12:Navigate back to Study Custome tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("12.1:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("13:Configuration of Screening,Randomization Number and Temporary ID for TC#2");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID(screeningNumber + mask, "",
				temporaryID + tempMask);

		reportLog("13.1:Navigate back to Study Custome tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("13.2:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("13.3:Verification of configured of Screening,Randomization Number and Temporary ID for TC#2");
		adminstrationStudiesCustomPage.verifyFieldFormatsAreSameAsConfigured(screeningNumber + mask, "",
				temporaryID + tempMask);

		reportLog("13.4: Logout application");
		loginPage.logoutApplication();

		reportLog("13.5: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		/*
		 * Login with user with userCanManageSubject claim and verify subject
		 * details in edit mode
		 */

		reportLog("14:Login with user having canManageSubjects claim");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("15:Click On Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("15.1:Select the Subject From the subject list");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		
		reportLog("16:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("17:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectOpenedInEditMode();

		reportLog("18:Verify subject can't be saved with empty Screening Number with Mask");
		subjectDetailPage.verifySubjectCantSavedWIthEmptyValues();

		reportLog("18.1:Verify user cancel the edit box and logout from the application");
		subjectDetailPage.clickOnCancelBtn();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		/*
		 * Verify for TC 3 when Screening#(Without Mask)
		 * Randomization#(NotRequired),TemporaryID With Mask(Required)
		 */

		reportLog("19:Login in to application to verify TC#3");
		// dashBoardPage = loginPage.loginInApplication(userName, password);
		loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("20:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("20.1:Navigate To Studies");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("20.2:Navigate To Studies Custom Tab");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("20.3:Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyToBesearched);

		reportLog("20.4:Navigate To Studies Custom Tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("20.5:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("20.6:Navigate To Studies Site Tab for getting site user detail");
		adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("21:Navigate back to Study Custome tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("21.1:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("22:Configuration of Screening,Randomization Number and Temporary ID for TC#3");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID(screeningNumber, "",
				temporaryID + tempMask);

		reportLog("22.1:Navigate back to Study Custome tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("22.2:Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("22.3:Verification of configured of Screening,Randomization Number and Temporary ID for TC#3");
		adminstrationStudiesCustomPage.verifyFieldFormatsAreSameAsConfigured(screeningNumber, "",
				temporaryID + tempMask);

		reportLog("22.4: Logout application");
		loginPage.logoutApplication();

		reportLog("22.5: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		/*
		 * Login with user with userCanManageSubject claim and verify subject
		 * details in edit mode
		 */

		reportLog("23:Login with user having canManageSubjects claim");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("24:Click On Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("24.1:Select the Subject From the subject list");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		
		reportLog("25:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("26:Verify Subject detail page opened and edit option is present");
		subjectDetailPage.verifySubjectOpenedInEditMode();

		reportLog("27:Verify subject can't be saved with empty Screening Number with Mask");
		subjectDetailPage.verifySubjectCantSavedWIthEmptyValues();

		reportLog("27.1:Verify user cancel the edit box and logout from the application");
		subjectDetailPage.clickOnCancelBtn();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}
	
	@AfterMethod
	public void updateCustomValue() {
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);	
			
			/*Clear Custom Configuration*/
			adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
			adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
			adminstrationStudiesPage.searchAndClickOnStudy(studyToBesearched);
			adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
			adminstrationStudiesCustomPage.clearAllField();
			adminstrationStudiesCustomPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
	}

}
