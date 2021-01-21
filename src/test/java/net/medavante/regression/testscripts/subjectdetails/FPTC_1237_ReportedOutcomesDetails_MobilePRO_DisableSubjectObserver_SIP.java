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

public class FPTC_1237_ReportedOutcomesDetails_MobilePRO_DisableSubjectObserver_SIP extends BaseTest {
	
private String ReasonForChange = "For testing purpose";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1237_ReportedOutcomesDetails_MobilePRO_DisableSubjectObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("newStudyName");

		reportLog("PR#: Creating new subject");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();
		subjectDetailPage.navigateToHomePage();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1237 Test Case Name: Reported Outcomes Details:
	 * Mobile PRO - Disable Subject/Observer
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1237 : Reported Outcomes - Availability of Mobile PRO settings  ", groups = {
			"" })

	public void FPTC_1237_ReportedOutcomesDetails_MobilePRO_DisableSubjectObserver() throws Exception {


		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


		reportLog("2.2: Search Subject-" + screeningNum);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);

		reportLog("2.3: Select Subject-" + screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);

		reportLog("2.4: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.5: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("2.6: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("2.7: Verify Drop-downs of Subject in Mobile PRO section is disabled");
		subjectDetailPage.verifyMobileProSubjectDropDownDisabled();

		reportLog("2.8: Verify Drop-downs of Observer in Mobile PRO section is disabled");
		subjectDetailPage.verifyMobileProObserverDropDownDisabled();

		reportLog("2.9: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("2.10: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("3.1: Logout application");
		loginPage.logoutApplication();

		reportLog("3.2: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("3.3: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("4.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("4.3: Search Subject-" + screeningNum);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);

		reportLog("4.4: Select Subject-" + screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);

		reportLog("4.5: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("4.6: click On Reported OutCome Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("4.7: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("4.8: Verify Drop-downs of Subject in Mobile PRO section is Enabled");
		subjectDetailPage.verifyMobileProSubjectDropDownEnabled();

		reportLog("4.9: Verify Drop-downs of Observer in Mobile PRO section is Enabled");
		subjectDetailPage.verifyMobileProObserverDropDownEnabled();

		reportLog("5.1: Select Disable in Subject drop-down");
		subjectDetailPage.selectMobileProSubjectOption(Constants.Option_Disabled);

		reportLog("5.2: Verify Subject Disable Reason text box is highlighted as required field");
		subjectDetailPage.verifyMobileProSubjectDisableReasonTextBoxIsHighlightedAsRequiredField();

		reportLog("6.1: Verify Save control is not active");
		subjectDetailPage.verifyReportedOutcomeSaveButtonIsDisabled();

		reportLog("7.1: Enter reason into Mobile Pro Observer Reason text box field");
		subjectDetailPage.enterReasonIntoMobileProSubjectReasonTextBoxField(ReasonForChange);

		reportLog("7.2: Select Save control");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("7.3: Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("8.1: Select an action to cancel in Reason for Change window");
		subjectDetailPage.clickOnReasonForChangeCancelBTN();

		reportLog("8.2: Verify Reported OutCome Popup Is Displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		//
		reportLog("8.3: Verify reason into Mobile Pro Observer Reason text box field is displayed.");
		subjectDetailPage.verifyReasonIntoMobileProObserverReasonTextBoxField(ReasonForChange);
		//

		reportLog("9.1: Select Disable in Observer drop-down");
		subjectDetailPage.selectMobileProObserverOption(Constants.Option_Disabled);

		reportLog("9.2: Verify Observer Disable Reason text box is highlighted as required field");
		subjectDetailPage.verifyMobileProObserverDisableReasonTextBoxIsHighlightedAsRequiredField();

		reportLog("9.3: Verify Save control is not active");
		subjectDetailPage.verifyReportedOutcomeSaveButtonIsDisabled();

		reportLog("10.1: Enter reason into Mobile Pro Observer Reason text box field");
		subjectDetailPage.enterReasonIntoMobileProObserverReasonTextBoxField(ReasonForChange);

		reportLog("10.2: Select Save control");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("10.3: Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("11.1: Select " + Constants.ReasonsForChange.get(0) + " Reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));

		reportLog("11.2: complete e-sign and save");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);

		reportLog("11.3: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("11.4: Verify Subject value is highlighted in red in Reported Outcomes section");
		subjectDetailPage.verifySubjectValueColor(Constants.ColorCode_Red);

		reportLog("11.5: Verify Observer value is highlighted in red in Reported Outcomes section");
		subjectDetailPage.verifySubjectValueColor(Constants.ColorCode_Red);

		reportLog("11.6: Verify Mobile Subject is displayed with disable icon");
		subjectDetailPage.verifyMobileSubjectDisplayedWithDisableIcon();

		reportLog("11.7: Verify Mobile Observer is displayed with disable icon");
		subjectDetailPage.verifyMobileObserverDisplayedWithDisableIcon();

		reportLog(
				"11.8: Hover Cusrsor To Mobile Based Participant Value And verify Deactivation date and reason in the tooltip");
		subjectDetailPage.hoverCursorToMobileBasedParticipantValueAndVerifyToolTipTextForParticipant(ReasonForChange);

		reportLog(
				"11.9: Hover Cusrsor To Mobile Based Observer Value And verify Deactivation date and reason in the tooltip");
		subjectDetailPage.hoverCursorToMobileBasedObserverValueAndVerifyToolTipTextForObserver(ReasonForChange);

		reportLog("11.10: Logout application");
		loginPage.logoutApplication();

		reportLog("11.11: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}