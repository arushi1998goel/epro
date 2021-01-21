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

public class FPTC_1238_ReportedOutcomesDetailsMobilePROPossibilityToSelectAnObserver_SIP extends BaseTest {

	protected String observerAlias, observerName, observerNameToBeInserted = generateRandomString(2);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1238_ReportedOutcomesDetailsMobilePROPossibilityToSelectAnObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		
		observerName = properties.getProperty("Auto_Observer_Relation1");
		observerAlias = properties.getProperty("observer77Alias");
		
		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.configureObsreverForMobile(observerName, SuperAdminUN, SuperAdminPW);
		subjectDetailPage.configureObsreverForMobile(observerNameToBeInserted, SuperAdminUN, SuperAdminPW);
		subjectDetailPage.	clickOnReportedOutComeButton();
		subjectDetailPage.selectObserverRelationAndClickOnDeleteBTN(observerNameToBeInserted);
		subjectDetailPage.clickOnPopUpYesBtn();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();	
		loginPage.logoutApplication();
		/* Subject Created Successfully */
		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1238 Test Case Name: ReportedOutcomes Details
	 * MobilePRO Possibility To Select An Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1238_Reported Outcomes Details: Mobile PRO - Possibility to select an Observer ", groups = { "" })

	public void FPTC_1238_verifyReportedOutcomesDetailsMobilePROPossibilityToSelectAnObserverr() {

		reportLog("1.1:Log in to the Portal as User in Pr#3");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:User is logged in");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:	Navigate to the Subject Details page of Pr#2");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);


		reportLog("1.5: Verify Subject Listing Page is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("1.6:Select Subject Pr#3");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("1.7:Subject Details page is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.1:Select an action to open Reported Outcomes Details screen");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("3.2:Reported Outcomes Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("3.3:Mobile PRO section is available with Enabled Subject and Observer");
		subjectDetailPage.verifyMobileProSubjectDropDownEnabled();
		subjectDetailPage.verifyMobileProObserverDropDownEnabled();

		reportLog("3.4:Drop-down to select the Observer is available in Mobile PRO section");
		subjectDetailPage.verifyMobileProObserverDropdownToSelectIsAvailable();

		reportLog("4.1:Select the drop-down to choose the Observer");
		subjectDetailPage.selectMobileProObserverInnerDropDown();

		reportLog("4.2:Drop-down list is opened ");
		subjectDetailPage.verifyMobileProObserverSelectionDropDownListOpen();

		reportLog("4.3:Observer from Pr#2.1 is available for choose ");
		subjectDetailPage.verifyObserverPresentInObserverToSelectList(observerName);

		reportLog("4.4:Observer from PR#2.2 is not displayed in the drop-down");
		subjectDetailPage.verifyObserverNotPresentInObserverToSelectList(observerNameToBeInserted);

		reportLog("5.1:Select the Observer from Pr#2.1 ");
		subjectDetailPage.selectMobileProObserver(observerName);

		reportLog("5.2:Select an action to Save changes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("5.3:Changes are saved ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("5.4: Selected Observer(Pr#2.1) is displayed in Reported Outcome section on Subject Details page");
		subjectDetailPage.verifySelectedObserverPresentInDetailPage(observerName);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
	}
}
