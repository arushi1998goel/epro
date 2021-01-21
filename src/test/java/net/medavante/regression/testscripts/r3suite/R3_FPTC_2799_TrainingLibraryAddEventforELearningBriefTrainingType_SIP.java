/**
 *@author 
* @date 06/04/2020
* =========================================================================
*  Test Case Id: FP-TC-2799 || Test Case Name: Training Library - Add Event for e-Learning Brief Training type
* pre-qualification :
* 
1. User with VU MA Admin role with claims canAccessTrainingLibrary, canManageTrainingLibrary exists
2. At least one Training with type e-Learning Brief without configured Events (in the Attention phase) is present in the Training library
3. At least 2 Assets exist:
3.1 At least 1 Active Asset with Complete or Pass/Fail outcome type;
3.2 At least 1 Inactive Asset with Complete or Pass/Fail outcome type;.
3.3 At least 1 Active Asset with Requires Decision outcome type
4. At least 1 Course in Active status exists.
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2799_TrainingLibraryAddEventforELearningBriefTrainingType_SIP extends BaseTest {

	private String TrainingName="AT_2799",Asset_1="Automation_Asset_2799",AssetNew="AT_2799",Course="AT_Course_2799";
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2799_TrainingLibraryAddEventforELearningBriefTrainingType_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	
	}
	
	@Test(description="FP-TC-2799 --Training Library - Add Event for e-Learning Brief Training type")
	public void R3_FPTC_2799_TrainingLibraryAddEventforELearningBriefTrainingType()
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
		
		reportLog("2.0: Select Training Pr.#2 hyperlink");
		trainingDetailsPage.applyfilterToVerifyTraining(TrainingName);
		trainingDetailsPage.clickOnFilteredTrainingLibrary(TrainingName);
		
		reportLog("2.1: Edit Training dialog is opened.");
		trainingDetailsPage.verifyEditTrainngDialogueDisplayed();
		
		reportLog("2.2: All configured training details are displayed.");
		trainingDetailsPage.verifyAddTrainingPopupFieldsDisplayed();
		
		reportLog("2.3: Option to Add Event is available.");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("2.4: Save Training and Cancel options are available.");
		trainingDetailsPage.verifySave_CancelButtonDisplayed();
		
		reportLog("3.0: Select an option to Add Event");
		trainingDetailsPage.clickonaddEventLink();
		
		reportLog("3.1: Add Event dialog is displayed with following fields:");
		reportLog("- Name (required)");
		reportLog("- Description (optional)");
		reportLog("- Asset (required)");
		trainingDetailsPage.verifyAddEventPopUpDisplayed();
		trainingDetailsPage.verifyFieldsAtAddEventPopup();
		
		reportLog("3.2: Add option (disabled by default)");
		trainingDetailsPage.verifyAddButtonDisabled();
		
		reportLog("3.3: Cancel option");
		trainingDetailsPage.verifyCancelbuttonOnaddEventPopupDisplayed();
		
		reportLog("3.4: Event Scheduling section is not displayed");
		trainingDetailsPage.verifyEventSchedulingFieldNotDisplayed();
		
		reportLog("4.0: Provide valid Event Name");
		trainingDetailsPage.enterEventNameinAddEventPopup(Constants.Event);
		
		reportLog("4.1: Provided information is displayed in the appropriate field");
		trainingDetailsPage.verifyEventNameFieldValue(Constants.Event);
		
		reportLog("5.0: Select drop-down for Asset field");
		trainingDetailsPage.clickOnAssetOption();

		reportLog("5.1: List of Assets is displayed:");
		trainingDetailsPage.verifyAssetListDisplayed();
		
		reportLog("5.2: - Asset from Pr. #3.1 is displayed in the list");
		trainingDetailsPage.verifyAssetDisplayedInAssetList(Constants.Asset);
		
		reportLog("5.3: Assets from Pr. #3.2 and Pr. #3.3 are not displayed in the list.");
		trainingDetailsPage.verifyassetNotDisplayedInAssetList(Asset_1);
		trainingDetailsPage.verifyassetNotDisplayedInAssetList(AssetNew);
		
		reportLog("5.4: Course from Pr. #4 is not displayed in the list.");
		trainingDetailsPage.verifyassetNotDisplayedInAssetList(Course);

		reportLog("6.0: Select any Asset from the list and select to Add Event to the Training");
		trainingDetailsPage.EnterEventAssetNameInEventPopUp(Constants.Asset);
		trainingDetailsPage.clickOnaddButton();
		
		reportLog("6.1: Add Event dialog is closed");
		trainingDetailsPage.verifyAddEventpopUpNotDisplayed();
		
		reportLog("6.2:  User is navigated to the Edit Training dialog");
		trainingDetailsPage.verifyEditTrainngDialogueDisplayed();

		reportLog("6.3: Created Event is displayed in the Events section");
		trainingDetailsPage.verifyaddedEventdisplayed(Constants.Event);
		
		reportLog("6.4: Options 'Change certification' and 'Automatically applied' are not displayed");
		trainingDetailsPage.verifyChangesCertificationAutomaticallyAppliedNotDisplayed();
		
		reportLog("6.5:  Option to Add Event is not available");
		trainingDetailsPage.verifyOptiontoAddEventNotdisplayed();
	
		reportLog("6.6: Option to Delete created Event is available.");
		trainingDetailsPage.verifyOptionToDeleteEventDisplayed(Constants.Event);
		
		reportLog("6.7: Save Training and Cancel options are available.");
		trainingDetailsPage.verifySave_CancelButtonDisplayed();

		reportLog("7.0: Select drop-down for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("7.1: Choose not e-Learning Brief Training Type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Form);
		
		reportLog("7.2: The type is selected.");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_Form);
		
		reportLog("7.3: Form Groups field is displayed (if Type is Forms or 3rd-party).");
		trainingDetailsPage.verifyFormsGroupFieldDisplayed();
		
		reportLog("7.4: Selected Training type is highlighted. Validation message 'This Training Type isn't compatible with "
				+ "configured Event(s).' is displayed on click on the information icon.");
		trainingDetailsPage.verifyTrainingTypeFieldHighlighted();
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("7.5: Event is highlighted. Validation message 'This Event isn't compatible with chosen Training Type.' "
				+ "is displayed on click on the information icon.");
		trainingDetailsPage.VerifyEventsFieldhighlighted(Constants.Event);
		trainingDetailsPage.verifyWarningMsgDisplayedOnEvent(Constants.Event);
		
		reportLog("7.6: Option to delete Event is available.");
		trainingDetailsPage.verifyOptionToDeleteEventDisplayed(Constants.Event);
		
		reportLog("7.7: Option to Add Event is available (if Type is Forms or General).");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("7.8: Save training option is disabled.");
		trainingDetailsPage.verifySaveButtonDisabled();
		
		reportLog("7.9: Cancel option is available.");
		trainingDetailsPage.verifyCancelButtonDisplayedInAddTrainingPopUp();
		
		reportLog("8.0: Select drop-down for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("8.1: Choose Prerequisite Training Brief Type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Prerequisite_Training);
		
		reportLog("8.2: Prerequisite Training type is selected.");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_Prerequisite_Training);
		
		reportLog("8.3: Validation messages are not displayed.");
		trainingDetailsPage.verifyValidationMsgNotDisplayed();
		
		reportLog("8.4: Save Training and Cancel options are available.");
		trainingDetailsPage.verifySave_CancelButtonDisplayed();
		
		reportLog("9.0: Select to Delete Event");
		trainingDetailsPage.deleteEvent(Constants.Event);
		
		reportLog("9.1: Confirmation dialog is displayed with the confirmation message 'Event {event name} will be deleted and "
				+ "all information you have provided will be lost. Are you sure?'");
		trainingDetailsPage.verifyConfirmationMSgOnDeleteEvent();
		
		reportLog("9.2: Options to Delete and Cancel are available");
		trainingDetailsPage.verifyDeleteCancelButtonsDisplayedOnDeleteEventPopup();
		
		reportLog("10.0:Select an option to Cancel");
		trainingDetailsPage.clickonCancelondeletePoopup();
		
		reportLog("10.1: Confirmation dialog is closed.");
		trainingDetailsPage.VerifyConfirmationDialogueNotDisplayed();
		
		reportLog("10.2: Edit Training dialog is displayed.");
		trainingDetailsPage.verifyEditTrainngDialogueDisplayed();
		
		reportLog("10.3: Created Event is displayed in the Events section");
		trainingDetailsPage.verifyaddedEventdisplayed(Constants.Event);
		
		reportLog("10.4: Save and Cancel options are available");
		trainingDetailsPage.verifySave_CancelButtonDisplayed();
		
		reportLog(" LogOut Application");
		trainingDetailsPage.refreshPage();
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
		}
}
