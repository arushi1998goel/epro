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

public class FPTC_1161_AbilityToUnlockPINFromThePortalForSubject_MAP extends BaseTest {

	private String study, subject;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1161_AbilityToUnlockPINFromThePortalForSubject_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("StudyMobileEPROVirigilAPK");
		subject = properties.getProperty("SubjectWithSetPIN");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1161 Test Case Name: Ability to unlock PIN from the
	 * Portal for Subject
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1161 : Ability to unlock PIN from the Portal for Subject ", groups = { "" })

	public void FPTC_1161_AbilityToUnlockPINFromThePortalForSubject() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		//studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.1: Select study");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("2.2: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("2.3: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.4: Verify Subject Lock Icon Displayed under REPORTED OUTCOMES");
		subjectDetailPage.verifySubjectLockIconDisplayed();

		reportLog("3.1: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("3.2: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("4.1: Verify Option to Unlock subject's PIN is displayed");
		subjectDetailPage.verifySubjectUnLockButtonDisplayedOnReportedOutcomePopUp();

		reportLog("4.2: click on Unlock subject's PIN.");
		subjectDetailPage.clickOnSubjectUnLockButtonDisplayedOnReportedOutcomePopUp();

		reportLog("4.3: Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("5.1: Select " + Constants.ReasonsForChangePaperTranscription.get(3) + " Reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChangePaperTranscription.get(3));

		reportLog("5.2: complete e-sign and save");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODProjectManager, AT_Password);

		reportLog("5.3: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("5.4: Verify Option to Unlock subject's PIN is NOT visible");
		subjectDetailPage.verifySubjectUnLockButtonNotDisplayedOnReportedOutcomePopUp();

		reportLog("5.5: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("5.6: Verify subject is not highlighted and shown as unlocked on the screen");
		subjectDetailPage.verifySubjectLockIconNotDisplayed();

		reportLog("5.7: Logout application");
		loginPage.logoutApplication();

		reportLog("5.8: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
