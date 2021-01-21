package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SFBTC_1834_InPortal_TheUserWithClaimCanUnlockPINCanResetSubjectsPINifItIsSet_SIP extends BaseTest {

	private String study, subject;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public SFBTC_1834_InPortal_TheUserWithClaimCanUnlockPINCanResetSubjectsPINifItIsSet_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("StudyMobileEPROVirigilAPK");
		subject = properties.getProperty("SubjectWithSetPIN");

		// **********************************
		// Need mobile interaction to lock the pin by entering incorrect pin
		// with multiple attempts before every run.
		// **********************************

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-1834 Test Case Name: In Portal - the user with claim
	 * canUnlockPIN can reset a subject's PIN if it is set
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "SFB-TC-1834 : In Portal - the user with claim canUnlockPIN can reset a subject's PIN if it is set ", groups = {
			"" })

	public void SFBTC_1834_InPortal_TheUserWithClaimCanUnlockPINCanResetSubjectsPINifItIsSet() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(study);

		reportLog("2.3: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("2.4: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.5: Verify Subject Lock Icon Displayed under REPORTED OUTCOMES");
		subjectDetailPage.verifySubjectLockIconDisplayed();

		reportLog("2.6: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("2.7: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("2.8: Verify Option to Unlock subject's PIN is NOT visible");
		subjectDetailPage.verifySubjectUnLockButtonNotDisplayedOnReportedOutcomePopUp();

		reportLog("2.9: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("3.1: Logout application");
		loginPage.logoutApplication();

		reportLog("3.2: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("3.3: Login in to application");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);

		reportLog("4.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("4.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(study);

		reportLog("4.3: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("4.4: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("4.5: Verify Subject Lock Icon Displayed under REPORTED OUTCOMES");
		subjectDetailPage.verifySubjectLockIconDisplayed();

		reportLog("4.6: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("4.7: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("4.8: Verify Option to Unlock subject's PIN is displayed");
		subjectDetailPage.verifySubjectUnLockButtonDisplayedOnReportedOutcomePopUp();

		reportLog("5.1: click on Unlock subject's PIN.");
		subjectDetailPage.clickOnSubjectUnLockButtonDisplayedOnReportedOutcomePopUp();

		reportLog("5.2: Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("5.3: Select " + Constants.ReasonsForChangePaperTranscription.get(3) + " Reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChangePaperTranscription.get(3));

		reportLog("5.4: complete e-sign and save");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODProjectManager, AT_Password);

		reportLog("5.5: Verify Option to Unlock subject's PIN is NOT visible");
		subjectDetailPage.verifySubjectUnLockButtonNotDisplayedOnReportedOutcomePopUp();

		reportLog("5.6: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("6.1: Logout application");
		loginPage.logoutApplication();

		reportLog("6.2: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("6.3: Login in to application");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("7.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("7.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(study);

		reportLog("7.3: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("7.4: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("7.5: Verify Subject Lock Icon Not Displayed under REPORTED OUTCOMES");
		subjectDetailPage.verifySubjectLockIconNotDisplayed();

		reportLog("7.6: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("7.7: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("7.8: Verify Option to Unlock subject's PIN is NOT visible");
		subjectDetailPage.verifySubjectUnLockButtonNotDisplayedOnReportedOutcomePopUp();

		reportLog("7.9: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("7.10: Logout application");
		loginPage.logoutApplication();

		reportLog("7.11: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		// ***************************
		// TC #8 can not be automate as it needs tablet interaction
		// ***************************

	}
}
