package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1245_verifySubjectDetailsConsentToRecord_SIP extends BaseTest {

	protected String subjectName__1312 = "SUBJ_" + generateRandomString(5);

	protected String Screening_Number = "SUBJ_" + generateRandomString(5);

    private String Visit_Widoutforms, Study12, OptionYesUnderConsentRecord, OptionNoUnderConsentRecord;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1245_verifySubjectDetailsConsentToRecord_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		Visit_Widoutforms = properties.getProperty("Visit_Widoutforms");
		Study12 = properties.getProperty("Study12");
		OptionYesUnderConsentRecord = properties.getProperty("OptionYesUnderConsentRecord");
		OptionNoUnderConsentRecord = properties.getProperty("OptionNoUnderConsentRecord");

		
		reportLog("Creating a one subject exists in this study without visit forms that configured to be recorded ");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName__1312);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(Visit_Widoutforms);
		loginPage.logoutApplication();

	}
	
	/**
	 * =====================================================================================
	 * Test Case Id: FP-TC-1245 Test Case Name: Portal - Subject Details - Consent To Record 
	 * =====================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1245_Portal - Subject Details - Consent To Record ", groups = { "" })
	public void FPTC_1245_verifySubjectDetailsConsentToRecord() {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1Navigate to Subject list of the Study without visit forms available for records (Pr #1). ");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.2  Verify New subject entry screen is displayed  ");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("1.3  Click on add subject");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("1.4 Required fields are highlighted​ ");
		studyNavigatorDashBoardPage.requiredFieldsOnAddSubjectPopUpIshighLighted(Constants.BackgroundColorCode_Red);

		reportLog("1.5Verify 'Consent To Record' is disabled");
		studyNavigatorDashBoardPage.verifyConsentRecordDisabled();

		reportLog("2.1Cancel creating of subject.");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("2.2Select any of existed subjects (Pr #1.1)");
		studyNavigatorDashBoardPage.searchSubject(subjectName__1312);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__1312);

		reportLog("2.3Click to edit selected subject");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.4Fields that can be changed become editable. ");
		studyNavigatorDashBoardPage.verifyFieldsAreEditable();

		reportLog("2.5Verify 'Consent To Record' is disabled");
		studyNavigatorDashBoardPage.verifyConsentRecordDisabled();
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("3.1  Navigate to Subject list of the Study with visit forms available for records (Pr #2). ");
		studyNavigatorDashBoardPage.navigateToHomePage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
	    studyNavigatorDashBoardPage.selectStudy(Study12,Constants.ATAssignedRater_10);

		reportLog("3.2 New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("3.3Click to add new subject. ");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("3.4Required fields are highlighted​ ");
		studyNavigatorDashBoardPage.requiredFieldsOnAddSubjectPopUpIshighLighted(Constants.BackgroundColorCode_Red);

		reportLog("3.5Verify 'Consent To Record' is enabled and has values 'Yes' or 'No'");
		studyNavigatorDashBoardPage.verifyConsentRecordEnabled();
		studyNavigatorDashBoardPage.veifyYesOptionIsPresent(OptionYesUnderConsentRecord);
		studyNavigatorDashBoardPage.veifyNoOptionIsPresent(OptionNoUnderConsentRecord);

		reportLog("4.1Fill all required fields, select any option for \"Consent to Record' field and save changes");
		studyNavigatorDashBoardPage.inputScreeningNum(Screening_Number);
		studyNavigatorDashBoardPage.clickOnConsentToRecord();
		studyNavigatorDashBoardPage.selectOptionFromConsentTorecord(OptionYesUnderConsentRecord);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("4.2New subject record is created ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4.3Verify List of available visits is displayed ");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("4.4 Option to edit subject details is available");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("5.1 Click to Edit subject details");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("5.2Fields that can be changed become editable. ");
		subjectDetailPage.verifyFieldsAreEditable();

		reportLog("5.3  'Consent To Record' is available for changes.");
		subjectDetailPage.verifyConsentRecordEnabled();

		reportLog("6.1 Change value for 'Consent to Record' field and save changes");
		subjectDetailPage.clickOnConsentToRecord();
		subjectDetailPage.selectOptionFromConsentTorecord(OptionYesUnderConsentRecord);
		subjectDetailPage.saveSubject();

		reportLog("6.2 Fill all details for Reason of change");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.verifyConsentToRecordAsReadOnlyLabel(OptionYesUnderConsentRecord);

		reportLog("Logout Application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
