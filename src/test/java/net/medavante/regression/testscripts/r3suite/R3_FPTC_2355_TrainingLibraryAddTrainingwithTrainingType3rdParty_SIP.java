/**
 *@author 
* @date 05/03/2020
* ============================================================================================================
*  Test Case Id: FP-TC-2355 || Test Case Name: Training Library - Add Training with Training Type 3rd-party
	 * pre-qualification :1. User with Claims to manage Training Library exists.
                          
* ============================================================================================================ 
*/package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2355_TrainingLibraryAddTrainingwithTrainingType3rdParty_SIP extends BaseTest {

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2355_TrainingLibraryAddTrainingwithTrainingType3rdParty_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		
	}
	
	@Test(description="FP-TC-2355 --Training Library - Add Training with Training Type 3rd-party")
	public void R3_FPTC_2355_TrainingLibraryAddTrainingwithTrainingType3rdParty()
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
		reportLog("- Library Description (optional)");
		reportLog("- Display Description (optional)");
		reportLog("- Training Type (required)");
		reportLog("- Events");
		trainingDetailsPage.verifyAddTrainingPopupDisplayed();
		trainingDetailsPage.verifyAddTrainingPopupFieldsDisplayed();
		
		reportLog("2.1: Add Training option (disabled by default)");
		trainingDetailsPage.verifyaddButtonDisplayedInAddTrainingPopUp();
		
		reportLog("2.2: Cancel option Displayed");
		trainingDetailsPage.verifyCancelButtonDisplayedInAddTrainingPopUp();
		
		reportLog("3.0:Provide valid Library and Display Name");
		trainingDetailsPage.EnterLibraryName(Constants.ValidLibraryName);
		trainingDetailsPage.EnterLibraryDisplayName(Constants.ValidDisplayName);
		
		reportLog("3.1: Provided names are displayed in the appropriate name fields");
		trainingDetailsPage.verifyLibraryNameFieldsValue(Constants.ValidLibraryName);
		trainingDetailsPage.verifyLibraryDisplayName(Constants.ValidDisplayName);
				
		reportLog("4.0: Select dropdown for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("- Choose 3rd-party Training Type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_3rdParty);
		
		reportLog("4.1:3rd-party type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_3rdParty);
		
		reportLog("4.2: Forms Groups field is displayed (required)");
		trainingDetailsPage.verifyFormsGroupFieldDisplayed();
		
		reportLog("4.3: Add Event action is available");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("5.0: Select dropdown for the Form Groups field and select multiple Form Groups from the list");
		trainingDetailsPage.selectformsField();
		trainingDetailsPage.selectFormsFromformList(Constants.Assessment_S_STS);
		
		reportLog("5.1: Form Groups are selected and displayed in the Form Groups field");
		trainingDetailsPage.verifyFormgroupfieldValue(Constants.Assessment_S_STS);
		
		reportLog("6.0: Select option to Add Event");
		trainingDetailsPage.clickonaddEventLink();
		
		reportLog("6.1: Add Event dialog is displayed");
		trainingDetailsPage.verifyAddEventPopUpDisplayed();
		
		reportLog("7.0: Provide valid Event Name, choose any Asset from the Asset dropdown");
		trainingDetailsPage.enterEventNameinAddEventPopup(Constants.Event);
		trainingDetailsPage.clickOnAssetOption();
		trainingDetailsPage.EnterEventAssetNameInEventPopUp(Constants.Asset);
		
		reportLog("7.1: Provided information is displayed in the appropriate fields");
		trainingDetailsPage.verifyEventNameFieldValue(Constants.Event);
		trainingDetailsPage.verifyAssetFielsValue(Constants.Asset);
		
		reportLog("8.0: Add the Event to the Training");
		trainingDetailsPage.clickOnaddButton();
		
		reportLog("8.1: Add Event dialog is closed");
		trainingDetailsPage.verifyAddEventpopUpNotDisplayed();
		
		reportLog("8.2:  User is navigated to the Add Training dialog .");
		trainingDetailsPage.verifyAddTrainingPopupDisplayed();
		
		reportLog("8.3: Recently created Event is displayed in the Events section");
		trainingDetailsPage.verifyaddedEventdisplayed(Constants.Event);
		
		reportLog("8.4: Option to Add Event is not available");
		trainingDetailsPage.verifyOptiontoAddEventNotdisplayed();
		
		reportLog("8.5: Option to Delete created Event is available");
		trainingDetailsPage.verifyOptionToDeleteEventDisplayed(Constants.Event);
				
		reportLog("9.0: Select dropdown for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("Choose Forms Training Type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_Form);
		
		reportLog("9.1: The type is selected");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_Form);
		
		reportLog("9.2: Forms Groups field is displayed");
		trainingDetailsPage.verifyFormsGroupFieldDisplayed();
		
		reportLog("9.3: Option to Add Event is available");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("10.0:Select option to Add Event, fill required fields with valid information and add the Event to the Training");
		trainingDetailsPage.clickonaddEventLink();
		trainingDetailsPage.enterEventNameinAddEventPopup(Constants.Event_two);
		trainingDetailsPage.enterEventAssetNameForFormTypeEvent(Constants.Type,Constants.Asset);
		trainingDetailsPage.clickOnaddButton();
		
		reportLog("10.1: Add Event dialog is closed");
		trainingDetailsPage.verifyAddEventpopUpNotDisplayed();
		
		reportLog("10.2:  User is navigated to the Add Training dialog .");
		trainingDetailsPage.verifyAddTrainingPopupDisplayed();
		
		reportLog("10.3:  Recently created Event is displayed in the Events section");
		trainingDetailsPage.verifyaddedEventdisplayed(Constants.Event_two);
		
		reportLog("10.4:Option to Add Event is available");
		trainingDetailsPage.verifyAddEventOptiondisplayed();
		
		reportLog("10.5: Option to Delete created Event is available");
		trainingDetailsPage.verifyOptionToDeleteEventDisplayed(Constants.Event_two);
		
		reportLog("11.0:Select dropdown for the Training Type field");
		trainingDetailsPage.selectTrainingTypeField();
		
		reportLog("Choose 3rd-party Training Type");
		trainingDetailsPage.SelectDropDownTrainingtypeSelectValue(Constants.TrainingType_3rdParty);
		
		reportLog("11.1: Provided type is displayed in the Training Type field and highlighted as invalid");
		trainingDetailsPage.verifyTrainingTypefieldValue(Constants.TrainingType_3rdParty);
		trainingDetailsPage.verifyTrainingTypeFieldHighlighted();
		
		reportLog("11.2: Events in the Events section highlighted as invalid");
		trainingDetailsPage.VerifyEventsFieldhighlighted(Constants.Event);
		
		reportLog("11.3: Validation message for Training Type is displayed: - 'This Training Type isn't compatible with configured Event(s)'");
		trainingDetailsPage.verifyWarningMsgDisplayedOnEvent(Constants.Event);
		
		reportLog("11.4: Validation messages for Events are displayed: 'This event isn`t compatible with chosen Training Type'");
		trainingDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("12.0: Delete one of the events");
		trainingDetailsPage.deleteEvent(Constants.Event_two);
		
		reportLog("12.1:  Confirm deleting in the confirmation dialog");
		trainingDetailsPage.clickonDeleteBTn();
		
		reportLog("12.2: The event is deleted");
		trainingDetailsPage.verifyEventNotdisplayed(Constants.Event_two);
		
		reportLog("12.3: Validation messages is not displayed");
		trainingDetailsPage.verifyValidationMsgNotDisplayed();
		
		reportLog("13.0: Select option to Add Training");
		trainingDetailsPage.clickOnAddButton();
		
		reportLog("13.1: Add Training dialog is closed");
		trainingDetailsPage.verifyAddTrainingPopupNotDisplayed();
		
		reportLog("13.2: User is navigated to the Training Library -> Training Grid");
		trainingDetailsPage.verifyTrainingDetailsPage();
		
		reportLog("13.3: The list of Training is refreshed with newly added training");
		trainingDetailsPage.verifyTrainingListUpdated(ListOfTraining);
		
		reportLog("13.4: Training is displayed with '3rd-party' type in Type column");
		trainingDetailsPage.applyfilterToVerifyTraining(Constants.ValidLibraryName);
		trainingDetailsPage.verifyAddedTrainingDisplayed(Constants.ValidLibraryName);
		
		reportLog("Delete Recently Added Training");
		trainingDetailsPage.deleteTraining(Constants.ValidLibraryName, SuperAdminUN, AT_Password);
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
		
		
	}

}
