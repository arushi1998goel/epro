package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1449DeleteObserverTest_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String crSiteName, crStudyName;
	protected String ObsROAss;
	protected NewSubjectDetailPage subjectDetailPage; 
	protected String Observer1="Obs1"+ generateRandomString(5);
	protected String Observer2="Obs2"+ generateRandomString(5);
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1449DeleteObserverTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
	
		Properties prop = Configuration.readTestData("RegressionTestData");
		crSiteName = prop.getProperty("siteName");
		crStudyName = prop.getProperty("AutomationStudyName");
		System.setProperty("className", getClass().getSimpleName());
		ObsROAss = prop.getProperty("Auto_Paper_OBSRO_Visit2");		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1449 Test Case Name: To check possibility to delete Observer
	 * 
	 * pre-qualification : 1. At least one Subject with at least two Observers exists
	 * 2. At least one Observer is assigned to at least one assessment
	 * 3. At least one Observer is not assigned to any assessment
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FPTC_1449 --Delete Observer", groups = { "R2" })
	public void FPTC_1449_verifyPossibilityToDeleteObserver() throws Exception {

		reportLog("1. Navigate to Subject Details of the Subject identified in prerequisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.1: Select Study");
		studyNavigatorDashBoardPage.selectStudy(crStudyName,Constants.ATAssignedRater_10);
				
		reportLog("1.2: Add new Subject by control 'Add New Subject' ");		
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		//studyNavigatorDashBoardPage.clickOnAddSubjectBTN(crSiteName);
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);	
		
		reportLog("1.3: Fill all required fields");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		
		reportLog("1.4: Save the changes");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("1.5: Open Reported Outcome Details screen ");
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.verifyAddObserverBtnIsDisplayed();
		
		reportLog("1.6: Configure two observers");
		subjectDetailPage.configureObserver(Observer1, Observer1);
		subjectDetailPage.configureObserver(Observer2, Observer2);
				
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
	
		reportLog("1.7: Select one visit and configure one observer for it");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.clickOnAddVisitIcon();				
		subjectDetailPage.selectRaterFromDropDown(Observer2);
								
		reportLog("2.: Select observer identified T1 and Edit and Delete controls are enabled");
		subjectDetailPage.clickOnReportedOutComeButton();		
		subjectDetailPage.verifyObserverGridIsDisplayedWithListOfObserver();
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(Observer1);		
		subjectDetailPage.selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(Observer2);		
		
		reportLog("3: Select an action to delete the Observer");
		subjectDetailPage.selectObserverRelationAndClickOnDeleteBTN(Observer1);
		
		reportLog("3.1: Confirmation message appears");
		subjectDetailPage.verifyConfirmPopUpMessageIsDisplayed("Are you sure you want to delete this Observer?");
				
		reportLog("4: Select an action to decline changes");
		subjectDetailPage.clickOnPopUpNoBtn();
		
		reportLog("4.1: Observer is not deleted and displayed in the list");
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(Observer1);		
		
		reportLog("5: Select Observer identified in prerequisites #3 again and select an action to delete the Observer");
		subjectDetailPage.selectObserverRelationAndClickOnDeleteBTN(Observer1);
		
		reportLog("5.1: Confirmation message appears");
		subjectDetailPage.verifyConfirmPopUpMessageIsDisplayed("Are you sure you want to delete this Observer?");
		
		reportLog("6: In the confirmation message confirm delete");
		subjectDetailPage.clickOnPopUpYesBtn();
		
		reportLog("6.2: The list of observers is updated.");		
		subjectDetailPage.selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(Observer2);		
		
		reportLog("6.3: The deleted Observer is not displayed on Observers list");
		subjectDetailPage.verifyObserverNotDisplayed(Observer1);		
		
		reportLog("7: Select an action to save changes in Reported Outcome Details screen");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();		
				
		reportLog("Logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
