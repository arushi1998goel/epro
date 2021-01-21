/**
 *@author 
* @date 09/03/2020
* ============================================================================================================
*  Test Case Id: FP-TC-2169 || Test Case Name: Training Library - Add Training Dialog
	 * pre-qualification :1. User with Claims to manage Training Library exists
                          2. User Pr.#1 is successfully logged in to the Portal and navigated to the Training Library -> Training Grid
                          
* ============================================================================================================ 
*/package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2169_TrainingLibraryAddTrainingDialog_SIP extends BaseTest {

	
	private String LibraryNameValid="Auto_Library"+generateRandomString(4),Description="Auto_description"+generateRandomAlphanumericString(600),DisplayName="Auto_Display"+generateRandomString(150);
	
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2169_TrainingLibraryAddTrainingDialog_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		
	}
	
	@Test(description="FP-TC-2169 --Training Library - Add Training Dialog")
	public void R3_FPTC_2169_TrainingLibraryAddTrainingDialog()
	{
		
		reportLog("1.0: Log in to the Portal as User Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.0.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.0.2: navigated to the Training Library -> Training Grid.");
		trainingDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				TrainingDetailsPage.class, Constants.ConfigureNavText, Constants.TrainingLibraryText);
		
		reportLog("1.0.3: Verify Training Grid page");
		trainingDetailsPage.verifyTrainingDetailsPage();
		String ListOfTraining = trainingDetailsPage.getTrainingCount();
		
		reportLog("2.0: Select option to Add new Training");
		trainingDetailsPage.clickOnaddTrainingButton();
		
		reportLog("2.1: Add Training dialog is displayed with following fields:");
		reportLog("- Library name (required)");
		reportLog("- Display name (required)");
		reportLog("- Training Type (required)");
		reportLog("- Library Description (optional)");
		reportLog("- Display Description (optional)");
		reportLog("- Events");
		trainingDetailsPage.verifyAddTrainingPopupDisplayed();
		trainingDetailsPage.verifyAddTrainingPopupFieldsDisplayed();
		
		reportLog("2.1: Add Training option (disabled by default)");
		trainingDetailsPage.verifyaddButtonDisplayedInAddTrainingPopUp();
		
		reportLog("2.2: Cancel option Displayed");
		trainingDetailsPage.verifyCancelButtonDisplayedInAddTrainingPopUp();
		
		reportLog("3.0: Provide more than 150 symbols for the Library name");
		trainingDetailsPage.EnterLibraryName(Constants.LibraryName);
		
		reportLog("3.1: - Provided name is displayed in the field");
		trainingDetailsPage.verifyLibraryNameFieldsValue(Constants.LibraryName);
		
		reportLog("3.2: - Name is highlighted as invalid");
		trainingDetailsPage.verifyLibraryNameFieldHighlightedOrNotWithError();
		
		reportLog("3.3:  Validation message 'Library Name is too long (max 150 characters)' is displayed on click on the information icon");
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("4.0: Provide unique Library name (length less than 150 symbols)");
		trainingDetailsPage.EnterLibraryName(LibraryNameValid);
		
		reportLog("4.1: Provided name is displayed in the field ");
		reportLog("Provided name is valid");
		trainingDetailsPage.verifyLibraryNameFieldsValue(LibraryNameValid);
		trainingDetailsPage.verifyLibraryNameFieldHighlightedOrNotWithError();
		trainingDetailsPage.verifyValidationMsgNotDisplayed();
		
		reportLog("5.0: Provide more, than 150 symbols for the Training Display name");
		trainingDetailsPage.EnterLibraryDisplayName(DisplayName);
		
		reportLog("5.1: Provided name is displayed in the field");
		trainingDetailsPage.verifyLibraryDisplayName(DisplayName);
		
		reportLog("5.2: Name is highlighted as invalid");
		trainingDetailsPage.verifyLibraryDisplayNameFieldHighlightedOrNot();
		
		reportLog("5.3: Validation message 'Display Name is too long (max 150 characters)' is displayed on click on the information icon");
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("6.0: Provide valid unique Training Display name (length less than 150 symbols)");
		trainingDetailsPage.EnterLibraryDisplayName(Constants.ValidDisplayName);
		
		reportLog("6.1:  Provided name is displayed in the Display name field");
		trainingDetailsPage.verifyLibraryDisplayName(Constants.ValidDisplayName);
		
		reportLog("6.2: Provided name is valid");
		trainingDetailsPage.verifyLibraryDisplayNameFieldHighlightedOrNot();
		trainingDetailsPage.verifyValidationMsgNotDisplayed();
		
		reportLog("7.0: Select dropdown for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("7.1: Available training types list is displayed:");
		reportLog("- General");
		reportLog("- Forms");
		reportLog("- 3rd-party");
		reportLog("- e-Learning Brief");
		trainingDetailsPage.verifyTrainingTypeListDisplayed();
		
		reportLog("8.0: Select General training type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_General);
		
		reportLog("8.1: General type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_General);
		
		reportLog("8.2:  Add Event action is available");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("8.3: Option to Add Training is active");
		trainingDetailsPage.verifyAddTrainingButtonIsEnabled();
		
		reportLog("9.0: Select Prerequisite Training training type");
		trainingDetailsPage.selectTrainingTypeField();
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Prerequisite_Training);
		
		reportLog("9.1: Prerequisite Training training type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_Prerequisite_Training);
		
		reportLog("9.2: Option to Add Training is active");
		trainingDetailsPage.verifyAddTrainingButtonIsEnabled();
		
		reportLog("10.0: Select dropdown for Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("10.1: Select Forms training type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Form);
		
		reportLog("10.2: Forms type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_Form);
		
		reportLog("10.3:  Forms Groups field is displayed (required)");
		trainingDetailsPage.verifyFormsGroupFieldDisplayed();
		
		reportLog("11.0: Select dropdown for Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("11.1: Select 3rd-party type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_3rdParty);
		
		reportLog("11.2: 3rd-party type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_3rdParty);
		
		reportLog("11.3:  Forms Groups field is displayed (required)");
		trainingDetailsPage.verifyFormsGroupFieldDisplayed();
		
		reportLog("12.0: Select dropdown for Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("12.1: Select Application type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Application);
		
		reportLog("12.2:  Add Event action is available");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("12.3: Option to Add Training is active");
		trainingDetailsPage.verifyAddTrainingButtonIsEnabled();
		
		reportLog("13.0: Select dropdown for the Form Groups field");
        trainingDetailsPage.selectTrainingTypeField();
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_3rdParty);
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_3rdParty);
		trainingDetailsPage.selectformsField();
		
		reportLog("13.1: Available Form Groups are displayed in the list");
		reportLog("Form groups are sorted by name");
		trainingDetailsPage.verifyFormGroupListDisplayed();
		
		reportLog("14.0: Select multiple Form Groups from the list");
		trainingDetailsPage.selectFormsFromformList(Constants.Assessment_MMSEFormName);
		trainingDetailsPage.selectformsField();
		trainingDetailsPage.selectFormsFromformList(Constants.Assessment_S_STS);
		
		reportLog("14.1: Form Groups are selected and displayed in the Form Groups field");
		trainingDetailsPage.verifySelectedFormDisplayed(Constants.Assessment_MMSEFormName);
		trainingDetailsPage.verifySelectedFormDisplayed(Constants.Assessment_S_STS);
		
		reportLog("14.2: Single deletion is available");
		trainingDetailsPage.verifyFormsDeleteOptionDisplayed(Constants.Assessment_MMSEFormName);
		
		reportLog("15.0: Delete one of Form Groups from the Form Groups field");
		trainingDetailsPage.deleteSelectedForm(Constants.Assessment_MMSEFormName);
		
		reportLog("15.1: Form group is deleted from the Form Groups field");
		reportLog(" Forms Groups are refreshed accordingly in the field");
		trainingDetailsPage.verifyDeletedFormNotDisplayed(Constants.Assessment_MMSEFormName);
		
		reportLog("16.0: Provide more than 500 symbols for the Library Description field");
		trainingDetailsPage.enterLibraryDescriptionData(Description);
		
		reportLog("16.1: Provided text is displayed in the field");
		trainingDetailsPage.verifyLibraryDescriptionValueDisplayed(Description);
		
		reportLog("16.2: Description is highlighted as invalid");
		trainingDetailsPage.verifyLibraryDescriptionFieldHighlightedOrNot();
		
		reportLog("16.3: Validation message 'Library Description is too long (max 500 characters)' is displayed on click on the information icon");
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("17.0: Provide less than 500 symbols for the Library Description field");
		trainingDetailsPage.enterLibraryDescriptionData(Constants.Description);
		
		reportLog("17.1: Provided text is displayed in the field as valid");
		trainingDetailsPage.verifyLibraryDescriptionValueDisplayed(Constants.Description);
		
		reportLog("18.0: Provide more than 500 symbols for the Display Description");
		trainingDetailsPage.enterDisplayDescriptionData(Description);
		
		reportLog("18.1: Provided text is displayed in the field");
		trainingDetailsPage.verifyDisplayDescriptionValueDisplayed(Description);
		
		reportLog("18.2: Description is highlighted as invalid");
		trainingDetailsPage.verifyDisplayDescriptionFieldHighlightedOrNot();
		
		reportLog("18.3: Validation message 'Display Description is too long (max 500 characters)' is displayed on click on the information icon");
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
	    reportLog("19.0: Provide less than 500 symbols for the Display Description field");
	    trainingDetailsPage.enterDisplayDescriptionData(Constants.Description);
	    
	    reportLog("19.1: Provided text is displayed in the field as valid");
	    trainingDetailsPage.verifyDisplayDescriptionValueDisplayed(Constants.Description);
	    
	    reportLog("20.0: Select Cancel option");
	    trainingDetailsPage.clickOnCancelButton();
	    
	    reportLog("20.1: Confirmation dialog with message 'Are you sure you want to leave this page? The changes you made will be lost.' is displayed");
	    trainingDetailsPage.verifyConfirmationDialogueOnCancelPopup();
	    
	    reportLog("21.0:Select the option to go back to form in the Confirmation dialog");
	    trainingDetailsPage.clickOnNoButton();
	    
	    reportLog("21.1: Confirmation dialog with message is closed");
	    trainingDetailsPage.VerifyConfirmationDialogueNotDisplayed();
	    
	    reportLog("22.0: Select option to Add Training");
	    trainingDetailsPage.clickOnAddButton();
	    
	    reportLog("22.1: Add Training dialog is closed");
	    trainingDetailsPage.verifyAddTrainingPopupNotDisplayed();
	    
	    reportLog("22.2: User is navigated to the Training Library -> Training Grid");
	    trainingDetailsPage.verifyTrainingDetailsPage();
	    
	    reportLog("22.3:The list of Training is refreshed with newly added training" );
	    trainingDetailsPage.verifyTrainingListUpdated(ListOfTraining);
	    
	    reportLog("23.0:  Select option to Add Training");
	    trainingDetailsPage.clickOnaddTrainingButton();
	    
	    reportLog("23.1: Add Training dialog is displayed");
	    trainingDetailsPage.verifyAddTrainingPopupDisplayed();
	    
	    reportLog("23.2: Provide non-unique Library Name" );
	    trainingDetailsPage.EnterLibraryName(LibraryNameValid);
	    
	    reportLog("23.3: Provided name is displayed in the Library name field" );
	    trainingDetailsPage.verifyLibraryNameFieldsValue(LibraryNameValid);
	    
	    reportLog("23.4: Provide unique Display Name");
	    trainingDetailsPage.EnterLibraryDisplayName("Auto_display");
	    
	    reportLog("23.5: Provided name is displayed in the Display name field");
	    trainingDetailsPage.verifyLibraryDisplayName("Auto_display");
	    
	    reportLog("23.6: Select General Training type");
	    trainingDetailsPage.selectTrainingTypeField();
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_General);
	    
	    reportLog("23.7: General Training type is selected");
	    trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_General);
	    
	    reportLog("23.8: Select option to Add Training");
	    trainingDetailsPage.clickOnAddButton();
	    
	    reportLog("23.9: Validation message 'Library Name must be unique' is displayed on click on the information icon");
	    trainingDetailsPage.verifyErrorWarningDisplayed();
	    
	    reportLog("24.0: Provide unique Library Name");
	    trainingDetailsPage.EnterLibraryName("Automation_Library");
	    
	    reportLog("24.1: Provided name is displayed in the Library name field");
	    trainingDetailsPage.verifyLibraryNameFieldsValue("Automation_Library");
	    
	    reportLog("24.2: Provide non-unique Display Name");
	    trainingDetailsPage.EnterLibraryDisplayName(Constants.ValidDisplayName);
	    
	    reportLog("24.3: Provided name is displayed in the Display name field");
	    trainingDetailsPage.verifyLibraryDisplayName(Constants.ValidDisplayName);
	    
	    reportLog("24.4: Select option to Add Training");
	    trainingDetailsPage.clickOnAddButton();
	    
	    reportLog("24.5: Validation message 'Display Name must be unique' is displayed on click on the information icon");
	    trainingDetailsPage.verifyErrorWarningDisplayed();
	    
	    reportLog("25.0: Select Cancel option");
	    trainingDetailsPage.clickOnCancelButton();
	    
	    reportLog("25.1: Confirmation dialog with message 'Are you sure you want to leave this page? The changes you made will be lost.' is displayed.");
	    trainingDetailsPage.verifyConfirmationDialogueOnCancelPopup();
	    
	    reportLog("25.2: Confirm cancellation in the confirmation dialog");
	    trainingDetailsPage.clickYesOnCancelPopup();
	    
	    reportLog("25.3: Add Training dialog is closed without saving");
	    trainingDetailsPage.verifyAddTrainingPopupNotDisplayed();
	    trainingDetailsPage.applyfilterToVerifyTraining("Automation_Library");
	    trainingDetailsPage.verifyAddedTrainingNotDisplayed("Automation_Library");
	    
	    reportLog("Delete Recently Added Training");
	    trainingDetailsPage.refreshPage();
	    trainingDetailsPage.applyfilterToVerifyTraining(LibraryNameValid);
		trainingDetailsPage.verifyAddedTrainingDisplayed(LibraryNameValid);
		trainingDetailsPage.deleteTraining(LibraryNameValid, SuperAdminUN, AT_Password);
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
	    	
	}

}
