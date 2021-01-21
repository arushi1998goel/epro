package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3232_ApplicationsTab_PatientAppSubTab_GeneralFlow_SIP extends BaseTest{
	String opt, optSideMenu;
	@Factory(dataProvider = "Browsers",dataProviderClass = DataProviders.class)
	public R3_FPTC_3232_ApplicationsTab_PatientAppSubTab_GeneralFlow_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getData() throws Exception{
		System.setProperty("className",getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName=properties.getProperty("AutomationStudyName");
		opt ="Pending Questionnaire";
		optSideMenu = "My Account";
	}
	
	@Test(description = "R3_FPTC_3232: Applications tab - Patient App sub tab - General flow", groups = {"R3"})
	public void R3_FPTC_3232_ApplicationsTab_PatientAppSubTab_GeneralFlow() {
		reportLog("2: Log in to Portal as User PR#2");
		dashBoardPage =loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3: Navigate to Administration tab -> Studies");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
	
		reportLog("3.1: Navigate to Studies ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		
	    reportLog("3.2: Search and select the study");
	    adminstrationStudiesPage.searchAndClickOnStudy(studyName);
	    
	    reportLog("3.3: Go to Applications Tab");
	    adminStudiesApplicationPage = adminstrationStudiesPage.navigateToStudyApplicationTab();
	    
	    reportLog("3.4: Verify application Tab page.");
	    adminStudiesApplicationPage.verifyAdministrationStudiesApplicationPage();
	    
	    reportLog("3.5: Verify Registration, Top Menu, Side menu,Each menu can be expanded/collapsed and Each menu can be edited items");
	    adminStudiesApplicationPage.verifyRegistrationTabWithCollapsedAndEditIcon();
	    adminStudiesApplicationPage.verifyTopMenuTabWithCollapsedAndEditIcon();
	    adminStudiesApplicationPage.verifySideMenuTabWithCollapsedAndEditIcon();
	    
	    reportLog("4.1: Click on collapsed icon on All tabs.");
	    adminStudiesApplicationPage.clickOnRegistrationTopMenuAndSideMenuCollapsedIcon();
	    
	    reportLog("4.2: verify All menu are collapsed.");
	    adminStudiesApplicationPage.verifyAllTabsCollapsed();
	    
	    reportLog("5 Verify All menu are expand after refresh the page.");
	    adminStudiesApplicationPage.verifyAllTabsExpandedAfterRefresh();
	    
	    reportLog("6.1: Click on Edit icon on Registration menu.");
	    adminStudiesApplicationPage.clickOnEditIconOnRegistrationMenu();
	    
	    reportLog("6.2: Verify Registration model window opens.");
	    adminStudiesApplicationPage.verifyEditRegistrationSettingsPopUp();
	    
	    reportLog("6.3: Verify Show option for the Terms and Conditions (unselected by default).");
	    adminStudiesApplicationPage.verifyTermsAndConditionCheckboxUnchecked();
	    
	    reportLog("6.4: Verify Version with a default option Default preselected");
	    adminStudiesApplicationPage.verifyDefaultVersionSelectedInDropDown();
	    
	    reportLog("6.5: Verify Scrollable Agreement Text field (if available for the selected version)");
	    adminStudiesApplicationPage.verifyRegAggrementTxtField();
	    
	    reportLog("6.6: Verify Disabled control to save and active control to cancel changes are displayed");
	    adminStudiesApplicationPage.verifyrRegSaveButtonDisabled();
	    adminStudiesApplicationPage.verifyRegCancelButton();
	    
	    reportLog("7.1: Select Terms and conditions check box and click on save button.");
	    adminStudiesApplicationPage.clickOnTermsAndConditionCheckbox();
	    adminStudiesApplicationPage.clickOnSaveButtonOnRegistrationPopUp();
	    
	    reportLog("7.2: Verify edit model window closed.");
	    adminStudiesApplicationPage.verifyEditRegSettingModelWindowClosed();
	    
	    reportLog("7.3: Verify Registration settings are displayed with the updates.");
	    adminStudiesApplicationPage.verifyRegistrationSettingUpdated();
	    adminStudiesApplicationPage.uncheckedTermAndConditionCheckbox();
	    
	    reportLog("8.1: Click on edit icon on top menu tab.");
	    adminStudiesApplicationPage.clickOnEditIconOnTopMenuTab();
	    
	    reportLog("8.2: Verify Top menu model window opens.");
	    adminStudiesApplicationPage.verifyTopMenuPopUp();
	    
	    reportLog("8.3: Verify Subject and Observer options are displayed.");
	    adminStudiesApplicationPage.verifySubjectSettingOptions();
	    adminStudiesApplicationPage.verifyObserverSettingOptions();
	    
	    reportLog("8.5: Verify Cancel and next button are displayed.");
	    adminStudiesApplicationPage.verifyNextButtonOnTopMenuPopUp();
	    adminStudiesApplicationPage.verifyCancelButtonOnTopMenuPopUp();
	    
	    reportLog("9.1: Select All options under Subject setting.");
	    adminStudiesApplicationPage.selectSubjectSettingOptions();
	    
	    reportLog("9.2: Select All options under Observer setting.");
	    adminStudiesApplicationPage.selectObserverSettingOptions();
	    
	    reportLog("9.3: Click on Next button.");
	    adminStudiesApplicationPage.clickOnNextButtonUnderTopMenu();
	    
	    reportLog("9.4: Verify all options are displayed under Show in Home Section");
	    adminStudiesApplicationPage.verifyShowInHomeSectionOptions();
	    
	    reportLog("9.5: Verify Back, Cancel and next button are displayed.");
	    adminStudiesApplicationPage.verifyNextButtonOnTopMenuPopUp();
	    adminStudiesApplicationPage.verifyCancelButtonOnTopMenuPopUp();
	    adminStudiesApplicationPage.verifyBackButtonOnTopMenuPopUp();
	    
	    reportLog("10.1: Select any option from Show in Home Section");
	    adminStudiesApplicationPage.selectPendingQuestionnaireOption(opt);
	    
	    reportLog("10.2: Click on Next button.");
	    adminStudiesApplicationPage.clickOnNextButtonUnderTopMenu();
	    
	    reportLog("10.3: Verify Show Questionnaires For with the options displayed");
	    adminStudiesApplicationPage.verifyShowQuestionnairesForOptions();
	    
	    reportLog("10.4: Verify Show Completed and Expired Questionnaires with the options displayed");
	    adminStudiesApplicationPage.verifyShowCompletedAndExpiredQuestionnairesOptions();
	    
	    reportLog("11.1: Click on Next button.");
	    adminStudiesApplicationPage.clickOnNextButtonUnderTopMenu();
	    
	    reportLog("11.2: Verify Show in Messages section and Site Messages option displayed.");
	    adminStudiesApplicationPage.verifySiteMessagesAndShowInMessagesSection();
	    
	    reportLog("11.3: Verify Controls to save and cancel changes are displayed");
	    adminStudiesApplicationPage.verifyCancelButtonOnTopMenuPopUp();
	    adminStudiesApplicationPage.verifySaveButtonOnTopMenuPopUp();
	    
	    reportLog("12.1: Click on Save button.");
	    adminStudiesApplicationPage.clickOnSaveButtonUnderTopMenu();
	    
	    reportLog("12.2: Verify Edit mode is closed.");
	    adminStudiesApplicationPage.verifyTopMenuPopUpClosed();
	    
	    reportLog("12.3: Verify Top Menu settings are displayed with the updates");
	    adminStudiesApplicationPage.verifyTopMenuSettingsDisplayed(opt);
	    
	    reportLog("13.1: Click on edit icon on side menu tab.");
	    adminStudiesApplicationPage.clickOnEditIconOnSideMenuTab();
	    
	    reportLog("13.2: Verify Subject and Observer options under Side Menu pop up.");
	    adminStudiesApplicationPage.verifySubjectOptionUnderSideMenuPopUp();
	    adminStudiesApplicationPage.verifyObserverOptionUnderSideMenuPopUp();
	    
	    reportLog("13.3: Select Subject and Observer options under Side Menu pop up.");
	    adminStudiesApplicationPage.verifySaveAndCancelButtonUnderSideMenuPopUp();
	    
	    reportLog("14.1: Select Subject and Observer option from Side Menu pop up.");
	    adminStudiesApplicationPage.selectSubjectOptionUnderSideMenuPopUp(optSideMenu);
	    adminStudiesApplicationPage.selectObserverOptionUnderSideMenuPopUp(optSideMenu);
	    
	    reportLog("14.2: Click on save button.");
	    adminStudiesApplicationPage.clickOnSaveButtonOnSideMenuPopUp();
	    
	    reportLog("14.3: Verify Side Menu pop up closed.");
	    adminStudiesApplicationPage.verifySideMenuPopUpClosed();
	    
	    reportLog("14.4: Verify Side Menu settings are displayed with the updates");
	    adminStudiesApplicationPage.verifySideMenuSettingsUpdated(optSideMenu);
	    
	    reportLog("15.1: Logout from the application");
		loginPage.logoutApplication();

		reportLog("15.2: Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("15.3: Log in to Portal as User PR#3");
		dashBoardPage =loginPage.loginInApplication(AT_PRODAdminViewOnly,AT_Password);

		reportLog("15.4: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("15.5: Navigate to Administration tab -> Studies");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
	
		reportLog("15.6: Navigate to Studies ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		
	    reportLog("15.7: Search and select the study");
	    adminstrationStudiesPage.searchAndClickOnStudy(studyName);
	    
	    reportLog("15.8: Go to Applications Tab");
	    adminStudiesApplicationPage = adminstrationStudiesPage.navigateToStudyApplicationTab();
	    
	    reportLog("15.9: Verify Applications tab with the Patient sub-tab is displayed in a view mode only");
	    adminStudiesApplicationPage.verifyPatientTabDisplayedInViewMode();
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	    
	}
}
