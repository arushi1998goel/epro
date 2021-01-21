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

public class FPTC_1159_verifyEditSubjectConfiguredWithTheMask_SIP extends BaseTest {

	int screeningNumberText = getRandomInteger(100, 999);
	private String updatedScreeningNumber = Integer.toString(screeningNumberText),ScreeningNum,
	textNote = "TestAuto20145" + generateRandomString(3), updatedScreeningNumberWithMask,cureentMaskingConfigured = "333",
	subjectName = GenerateRandomNumber(9);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1159_verifyEditSubjectConfiguredWithTheMask_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2045");
		//subjectName = properties.getProperty("SubjectName2045");
		
		reportLog("Creating a New subject from user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		ScreeningNum= subjectDetailPage.getSubjectNumberFromSubjectDetailPage();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1159 Test Case Name:Edit Subject Configured With The
	 * Mask
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1159_EditSubjectConfiguredWithTheMask ", groups = { "R1" })
	public void FPTC_1159_verifyEditSubjectConfiguredWithTheMask() {

		reportLog("1.1: Login to Site Portal with site user with the claim canManageSubject");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Verify User login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Navigate to the Subject details page of Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		
		reportLog("1.4: Select the subject pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				ScreeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(ScreeningNum);

		reportLog("1.5:Verify Subject Details Displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.6:Option to edit Subject is available");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("2.1:Select option to edit Subject");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.2:Fields that can be changed become editable on Edit screen");
		subjectDetailPage.verifyScreeningNumIsDisplayedAndEditable();
		subjectDetailPage.verifyEditSubjectLanguageInputIsDisplayedAndEditable();

		reportLog("2.3:Screening# is same as Subject name of step#1");
		subjectDetailPage.verifyScreeningNumIsEntered(ScreeningNum);

		reportLog("3.1:Clear Screening# field");
		subjectDetailPage.clearScreeningInp();

		reportLog("3.2:Required field is highlighted and saving is not allowed");
		subjectDetailPage.verifyScreeningNumIsRequired();

		 reportLog("3.3:Current masking for the Study is displayed in the Screening# field");
		 subjectDetailPage.inputScreeningNumber(updatedScreeningNumber);
		 subjectDetailPage.verifyCurrentConfiguredMasking(cureentMaskingConfigured);
       
		reportLog("3.4:Pop-up with the description of mask format is displayed");
		subjectDetailPage.verifyPopUpDescriptionWithMaskFormatIsDisplayed();

		reportLog("4.1:Fill Screening # according to the mask");
		subjectDetailPage.inputScreeningNumber(updatedScreeningNumber);

		reportLog("4.2:Edit any Subject data");
		subjectDetailPage.inputNotesText(textNote);

		reportLog("4.3:Options to save and cancel are available");
		subjectDetailPage.verifyEditSubjectCancelButtonIsDisplayed();
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("5.1:Click on Cancel option");
		subjectDetailPage.clickOnCancelBtn();

		reportLog("5.2:	Changes aren't saved.");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(ScreeningNum);

		reportLog("6.1:	Click on Edit Subject option again");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("6.2:	Fields that can be changed become editable on Edit screen");
		subjectDetailPage.verifyScreeningNumIsDisplayedAndEditable();
		subjectDetailPage.verifyEditSubjectLanguageInputIsDisplayedAndEditable();

		reportLog("7.1:Fill all required fields, change an existed data and save changes");
		subjectDetailPage.inputScreeningNumber(updatedScreeningNumber);
		subjectDetailPage.inputNotesText(textNote);
		updatedScreeningNumberWithMask = subjectDetailPage.getScreeningNumberFromEditSubjectPopUp();

		reportLog("7.2:Data are validated and saved");
		subjectDetailPage.verifyScreeningNumIsEntered(updatedScreeningNumberWithMask);
		subjectDetailPage.verifyLanguageIsSelected(studyLanguage);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);

		reportLog("7.3:Subject record is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(updatedScreeningNumberWithMask);
		
		reportLog("7.4: Deleting the subject");
		subjectDetailPage.deleteSubject();
		
		reportLog("7.5: Logout from application");
		loginPage.logoutApplication();

		reportLog("7.6: Verify User Logout from application");
		loginPage.verifyUserLogout();

	
	}

	}
