package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1155VerifySystemProvidesAnOptionToCaptureTheReasonForAChange_SIP extends BaseTest{

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1155VerifySystemProvidesAnOptionToCaptureTheReasonForAChange_SIP(String browser) {
		super(browser);
	}
	
	private String studyName; 
	private String oldNoteText="AutoOldNote_"+ generateRandomString(5),newNoteText="AutoNewNote_"+ generateRandomString(5);
	private String reasonForChangeComment="AutoComment_"+ generateRandomString(5),randomNum2="Auto"+ generateRandomString(5); 
	private String randomizationNum2="Auto"+ generateRandomString(2);
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		
		reportLog("1.1: Login in to application with "+ SuperAdminUN);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.2: Verify User Login ");
		dashBoardPage.verifyMedavantePortalPage(); 

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		reportLog("1.4: Select "+studyName +" study");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Select an action to add Subject by selecting the site");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("1.6: Enter "+screeningNum+" Screening# num​");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		
		reportLog("1.7: Enter "+screeningNum+" Randomization# num​");
		studyNavigatorDashBoardPage.inputRandomizationNum(randomizationNum);

		reportLog("1.8: Select language from the configured language list");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		reportLog("1.9: Input note text");
		studyNavigatorDashBoardPage.inputNotes(oldNoteText);

		reportLog("1.10: select an action to save and verify new subject is created");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
				
	}
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1155
	 * Test Case Name:In portal, System provides an option to capture the reason for a change
	 * for a Study
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1155_In portal, System provides an option to capture the reason for a change", groups = { "R2" })

	public void FPTC_1155_VerifySystemProvidesAnOptionToCaptureTheReasonForAChange() {
		

		reportLog("1.11: Verify Control to edit the Subject info is available");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("1.12: Verify Subject Details page is visible");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("2.1: Select the option to edit the Subject");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("2.2: Verify Edit Subject modal window is displayed ");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("2.3: Verify Screening num fields is displayed and editable");
		subjectDetailPage.verifyScreeningNumIsDisplayedAndEditable();
		
		reportLog("2.4: Verify language fields is displayed and editable");
		subjectDetailPage.verifyEditSubjectLanguageInputIsDisplayedAndEditable();

		reportLog("2.5: Verify Save controls are displayed");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();
		
		reportLog("2.6: Verify cancel controls are displayed");
		subjectDetailPage.verifyEditSubjectCancelButtonIsDisplayed();
		
		reportLog("3.1: Update Randomization field and enter "+ randomNum2);
		subjectDetailPage.inputRandomNumber(randomNum2);
		
		reportLog("3.2: Save the change");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("3.3 Verify A pop-up window appears to capture the Reason for Change");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		
		reportLog("4.1: Verify Control to select a reason appears as highlighted and by-default nothing is selected");
		subjectDetailPage.verifyReasonForChangeInpIsHighlightedWithDefaultValue();
		
		reportLog("4.2: Verify Control to add a comment appears as enabled & not highlighted");
		subjectDetailPage.verifyReasonForChangeCommentFieldIsEnabledAndNotRequired();
		
		reportLog("4.3: Verify Controls to provide user credentials appear as empty and are highlighted");
		subjectDetailPage.userCredentialsFieldIsRequiredAndEmpty();
		
		reportLog("4.4: Verify Options are available to save and cancel the reason");
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		
		reportLog("4.5: Verify By default the option to save is disabled");
		subjectDetailPage.verifyReasonForChangeOkButtonIsDisabled();
		
		reportLog("5.1: Select Other option from the list of reasons");
		subjectDetailPage.selectReasonForChangeOption(Constants.Other_Reason_For_Change);
		
		reportLog("5.2: Verify Other is selected as a reason");
		subjectDetailPage.verifySelectedReasonForChangeValue(Constants.Other_Reason_For_Change);
		
		reportLog("5.3: Verify Comment field becomes highlighted");
		subjectDetailPage.verifyReasonForChangeCommentFieldIsEnabledAndRequired();
		
		reportLog("6.1: Provide the User credentials");
		subjectDetailPage.inputCredentialsForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("6.2: Verify The Ok option remains disabled");
		subjectDetailPage.verifyReasonForChangeOkButtonIsDisabled();
		
		reportLog("7.1: Enter comment");
		subjectDetailPage.inputReasonForChangeComment(generateRandomString(5));
		
		reportLog("7.2: Enter invalid credentials to save the reason");
		subjectDetailPage.eSignReasonForChangeAndSubmit(generateRandomString(5), generateRandomString(5)); 
		
		reportLog("7.3: Verify A message indicating invalid credentials is displayed");
		subjectDetailPage.verifyInvalidCredentialsTextIsDisplayedForReasonForChange(invalidCredentialsMessage);
		
		reportLog("8.1: Provide valid credentials and choose the option to save");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
		reportLog("8.2: Verify The Subject details is updated with the "+ randomNum2 + " random number");
		subjectDetailPage.verifyRandomizationNumberIsDisplayed(randomNum2);
		
		reportLog("9.1: Click on edit subject icon to Update the randomization number again");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("9.2: Enter Random num to Update the randomization number again");
		subjectDetailPage.inputRandomNumber(randomizationNum2);
		
		reportLog("9.3: select to save");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("9.4: Verify The pop-up window appears again to capture the Reason for change");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		
		reportLog("10.1: One by one select all the available reasons (except Other) and verify The comment field remains enabled & not highlighted for all selections");
		subjectDetailPage.commentFieldIsNotMandatoryForAllReasonOptionsExceptOtherOption();
		
		reportLog("11.1: Provide credentials and proceed to save");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
		reportLog("11.2: Verify The Subject details is updated with the randomization number of " + randomizationNum2);
		subjectDetailPage.verifyRandomizationNumberIsDisplayed(randomizationNum2);
		
		reportLog("12.1: Click on subject edit icon to Update the Notes field of the subject");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("12.2: Enter" + newNoteText+ " Notes Text in note field");
		subjectDetailPage.inputNotesText(newNoteText);
		
		reportLog("12.3: select to save the change");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("12.4: Verify The pop-up window appears again to capture the Reason for change");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		
		reportLog("13.1: Select Reason for change option");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("13.2: add Comment");
		subjectDetailPage.inputReasonForChangeComment(reasonForChangeComment);
		
		reportLog("13.3: Verify Comment is added");
		subjectDetailPage.verifyReasonForChangeCommentIsAdded(reasonForChangeComment);
		
		reportLog("14.1: Provide credentials");
		subjectDetailPage.inputCredentialsForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("14.2: select an action to cancel");
		subjectDetailPage.clickOnReasonForChangeCancelBTN();
		
		reportLog("14.3: Verify Reason For Change pop-up window disappears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsNotDisplayed();
		
		reportLog("14.4: Verify The Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("14.5: Verify note is displayed");
		subjectDetailPage.verifyNoteTextIsAddedInEditSubjectPopUp(newNoteText);
		
		reportLog("15.1: Choose the option to cancel on Eidt Subject modal window");
		subjectDetailPage.clickOnCancelBtn();
		
		reportLog("15.2: Verify The Subject Details modal window is closed"); 
		subjectDetailPage.verifySubjectEdtingPopUpIsNotDisplayed();
		
		reportLog("15.3: Verify The old note remain display on subject detail page");
		subjectDetailPage.verifyNoteTextIsSaved(oldNoteText);
		
		reportLog("15.4: Logout from application");
		loginPage.logoutApplication(); 
		
		reportLog("15.5: Verify User logout from the application");
		loginPage.verifyUserLogout();

	}
}
