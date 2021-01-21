package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1146_ObserverDetailsDeleteObserver_SIP extends BaseTest {

	private String studyName;
	private String subjectName = "Subj_" + generateRandomString(3);
	protected String Observer="Ob"+ generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1146_ObserverDetailsDeleteObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");
		
		reportLog("Creating a New subject from user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		subjectDetailPage.clickOnReportedOutComeButton();		
		subjectDetailPage.configureObserver(Observer, Observer);
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1146 Test Case Name: Show that Observer could be deleted in case of No devices registered for Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1146: Subject Details - Delete Subject", groups = { "" })

	public void FPTC_1146_verifyObserverCouldBeDeletedInCaseOfNoDevicesRegisteredForObserver() {

		reportLog("1: Log in to the Portal as a User from Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2: Navigate to Study Navigator Pr.#1 and proceed to Subjects Listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.1: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
				
		reportLog("2.2: Navigate to the Subject details page of the subject and click on visit");
		reportLog("2.3: Subjects Listing screen is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("3: Select Subject Pr#3.1");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("3.1: Subject Detail screen is displayed");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("4: Select an Edit control for Reported Outcomes section ");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("4.1: Select action to delete Observer Pr#3.1 and confirm action");
		subjectDetailPage.verifyObserverGridIsDisplayedWithListOfObserver();
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(Observer);		
		
		reportLog("4.2: Select action to delete Observer Pr#3.1 and confirm action");
		subjectDetailPage.selectObserverRelationAndClickOnDeleteBTN(Observer);

		reportLog("4.3: Confirmation message appears");
		subjectDetailPage.verifyConfirmPopUpMessageIsDisplayed("Are you sure you want to delete this Observer?");
		
		reportLog("4.4: In the confirmation message confirm delete");
		subjectDetailPage.clickOnPopUpYesBtn();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();	
		
		reportLog("5: Navigate to Subject Details Pr.#3.2");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("5.1: Subjects Listing screen is displayed and search the subject");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();		
		studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("5.2: Subject Detail screen is displayed");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("6.1: Select an Edit control for Reported Outcomes section ");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("6.2: Verify that no observer present in grid");
		subjectDetailPage.verifyObserverGridIsHidden();		
		subjectDetailPage.closeReportedOutComePopup();	
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

		
	}
}
