/**
 *@author 
* @date 04/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-1108   Test Case Name: Qualification Library - Add Qualification Group wizard- V10
*  pre-qualification :1. At least one Study and multiple Form Groups (Scales) exist for test
                      2. Multiple Qualification Groups exist
                      3. User with the claim to manage Qualification Library exists
                      4. User Pr.#3 is successfully logged in to the portal and navigated to the Qualification Library
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.qualificationlibrary.QualificationLibraryPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_1108_QualificationLibraryAddQualificationGroupWizard_SIP extends BaseTest {

	private String InValidName=generateRandomAlphanumericString(151),InValidDescription=generateRandomString(501),
			nonUniqueDisplayName=Constants.uniqueDisplayName,nonUniqueLibraryName=Constants.uniqueLibraryName,
			uniqueName="Autolibrary_1";
	
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_1108_QualificationLibraryAddQualificationGroupWizard_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		
	}
	
	@Test(description="FP-TC-1108---Qualification Library - Add Qualification Group wizard- V10")
	public void R4_FPTC_1108_QualificationLibraryAddQualificationGroupWizard()
	{
		
		reportLog("1.0: Log in to the Portal as User Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, AT_Password);

		reportLog("1.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to the Qualification Library");
		qualificationlibrary = dashBoardPage.selectHorizontalUpperNavMenuItem(QualificationLibraryPage.class,
				Constants.ConfigureNavText, Constants.QualificationLibraryText);
		qualificationlibrary.verifyQualificationLibraryPage();
		String qualificationGroupCount=qualificationlibrary.getQualificationGroupCount();

		reportLog("2.1: Select option to Add Group");
		qualificationlibrary.selectOptionToAddGroup();
		
		reportLog("2.2: Qualification Library Grid is displayed with following columns by default:");
		qualificationlibrary.verifyQualificationLibraryPopupDisplayed();

		reportLog("Display name (required)");
		qualificationlibrary.verifyFieldIsDisplayedAndRequired(Constants.DisplayName);
		
		reportLog("Library name (required)");
		qualificationlibrary.verifyFieldIsDisplayedAndRequired(Constants.libraryName);

		reportLog("Form groups selection (required)");
		qualificationlibrary.verifyFieldIsDisplayedAndRequired(Constants.FormGroups);

		reportLog("Description (optional)");
		qualificationlibrary.verifyDescriptionFieldIsDisplayedAsOptional();
		
		reportLog("Qualification Question: How many times have you administered this scale over this course of your career?");
		qualificationlibrary.verifyQualificationQuestionIsDisplayed(Constants.Question);
		
		reportLog("2.3: Options to configure eligibility and qualification rules");
		qualificationlibrary.verifyConfigureOptionAccordinglyRules(Constants.Eligibility_rule);
		qualificationlibrary.verifyConfigureOptionAccordinglyRules(Constants.Qualification_rule);

		reportLog("2.4: Add option (disabled by default)");
		qualificationlibrary.verifyAddButtonIsDisplayed();
		qualificationlibrary.verifyAddButtonDisabled();
		
		reportLog("2.5: Cancel option");
		qualificationlibrary.verifyCancelButtonIsDisplayed();
		
		reportLog("3.0: Provide more than 150 symbols for Qualification Group Display name");
		qualificationlibrary.enterRequiredFieldValue(Constants.DisplayName, InValidName);
		
		reportLog("3.1: Provided name is displayed in the field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.DisplayName, InValidName);
		
		reportLog("3.2: Name is highlighted as invalid");
		qualificationlibrary.verifyFieldIsHighlightedAsInvalid(Constants.DisplayName);
		
		reportLog("3.3: Validation message: 'A Display name: must be no more than 150 characters' is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.DisplayName);
		
		reportLog("4.0: Provide unique Qualification Group Display name (length less than 150 symbols)");
		qualificationlibrary.enterRequiredFieldValue(Constants.DisplayName, Constants.uniqueDisplayName);

		reportLog("4.1: Provided name is displayed in the field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.DisplayName,Constants.uniqueDisplayName);

		reportLog("4.2:  Name is valid");
		qualificationlibrary.verifyFieldValueIsValid(Constants.DisplayName);
		
		reportLog("5.0: Provide more than 150 symbols for Qualification Group Library name");
		qualificationlibrary.enterRequiredFieldValue(Constants.libraryName, InValidName);
		
		reportLog("5.1: Provided name is displayed in the field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.libraryName, InValidName);

		reportLog("5.1: Name is highlighted as invalid");
		qualificationlibrary.verifyFieldIsHighlightedAsInvalid(Constants.libraryName);

		reportLog("5.3: Validation message: 'A Library name: must be no more than 150 characters' is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.libraryName);
		
		reportLog("6.0: Provide unique Qualification Group Library name (length less than 150 symbols)");
		qualificationlibrary.enterRequiredFieldValue(Constants.libraryName, Constants.uniqueLibraryName);

		reportLog("6.1: Provided name is displayed in the field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.libraryName,Constants.uniqueLibraryName);

		reportLog("6.2: Name is valid");
		qualificationlibrary.verifyFieldValueIsValid(Constants.libraryName);

		reportLog("7.0: Select multiple form groups from the list of predefined groups");
		qualificationlibrary.selectFormGroupFromFormGroupsList(Constants.Assessment_CAM);
		qualificationlibrary.selectFormGroupFromFormGroupsList(Constants.Assessment_MMSEFormName);

		reportLog("7.1: Form groups are selected and displayed in the field");
		qualificationlibrary.verifySelectedFormGroupDisplayed(Constants.Assessment_CAM);
		qualificationlibrary.verifySelectedFormGroupDisplayed(Constants.Assessment_MMSEFormName);

		reportLog("7.2: Form group field is not required");
		qualificationlibrary.verifyFieldIsNotDisplayedRequired(Constants.FormGroups);
		
		reportLog("8.0: Provide more than 500 symbols for Description field");
		qualificationlibrary.enterDataInDescriptionField(InValidDescription);
		
		reportLog("8.1: Provided data is displayed in the field");
		qualificationlibrary.verifyEnteredDataInDescriptionField(InValidDescription);
		
		reportLog("8.2: Data is highlighted as invalid");
		qualificationlibrary.verifyFieldIsHighlightedAsInvalid(Constants.description);
		
		reportLog("8.3: Validation message: 'A Description: must be no more than 500 characters' is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.description);
		
		reportLog("9.0: Provide 500 or less symbols for Description field");
		qualificationlibrary.enterDataInDescriptionField(Constants.uniqueDescription);

		reportLog("9.1: Provided data is displayed in the field");
		qualificationlibrary.verifyEnteredDataInDescriptionField(Constants.uniqueDescription);

		reportLog("9.2: Description is valid");
		qualificationlibrary.verifyFieldValueIsValid(Constants.description);
		
		reportLog("10.0: Select option to configure Default eligibility rules");
		qualificationlibrary.selectConfigureOptionAccordinglyRules(Constants.Eligibility_rule);
		
		reportLog("10.1: Add Eligibility Rule window is displayed");
		qualificationlibrary.verifyAddEligibilityRulePopUPWindowDisplayed();
		
		reportLog("11.0: Provide description");
		qualificationlibrary.enterDescriptionInAddRulePopup(Constants.Description);
		
		reportLog("11.1: Description is provided");
		qualificationlibrary.verifyEnteredValueInDescriptionFieldInAddRulePopUp(Constants.Description);
		
		reportLog("11.2: Define set of conditions or a group");
		qualificationlibrary.selectAddConditionControl();
		qualificationlibrary.addCondition(Constants.Role);
		qualificationlibrary.addGroup();
		
		reportLog("11.3: Rule is configured");
        qualificationlibrary.verifyCondition(Constants.Role);
        qualificationlibrary.verifyGroupIsAdded();
      
        reportLog("12.0: Select option to Add Rule");
        qualificationlibrary.selectAddOptionOnRulePopUp();
        
        reportLog("12.1: Eligibility rule dialog is closed");
		qualificationlibrary.verifyAddEligibilityRulePopUpWindowNotDisplayed();
		
		reportLog("12.2: User is navigated to the  Qualification Group dialog");
		qualificationlibrary.verifyQualificationLibraryPopupDisplayed();

		reportLog("12.3: Recently provided description for Default Eligibility rule is displayed");
		qualificationlibrary.verifyRecentlyProvidedDescriptionWithRuleIsDisplayed(Constants.Eligibility_rule, Constants.Description);
		
		reportLog("12.4: Option to configure eligibility rule is available");
		qualificationlibrary.verifyConfigureOptionAccordinglyRules(Constants.Eligibility_rule);
		
		reportLog("13.0: Select option to configure Default Qualification rules");
		qualificationlibrary.selectConfigureOptionAccordinglyRules(Constants.Qualification_rule);
		
		reportLog("13.1: Add Qualification Rule window is displayed");
        qualificationlibrary.verifyAddQualificationRuleWindowIsdisplayed();
        
        reportLog("14.0: Provide description");
        qualificationlibrary.enterDescriptionInAddRulePopup(Constants.uniqueDescription_1);
        
        reportLog("14.1: Description is provided");
        qualificationlibrary.verifyEnteredValueInDescriptionFieldInAddRulePopUp(Constants.uniqueDescription_1);
        
        reportLog("14.2: Define set of conditions or a group");
		qualificationlibrary.addGroup();
		
        reportLog("14.3: Rule is configured");
        qualificationlibrary.verifyGroupIsAdded();
        
        reportLog("15.0: Select option to Add Rule");
        qualificationlibrary.selectAddOptionOnQualificationRulePopUp();
        
        reportLog("15.1: Qualification rule dialog is closed");
        qualificationlibrary.verifyQualificationRuleWindowNotDisplayed();
        
        reportLog("15.2: User is navigated to the  Qualification Group dialog");
		qualificationlibrary.verifyQualificationLibraryPopupDisplayed();

		reportLog("15.3: Recently provided description for Default qualification rule is displayed");
		qualificationlibrary.verifyRecentlyProvidedDescriptionWithRuleIsDisplayed(Constants.Qualification_rule,Constants.uniqueDescription_1);

		reportLog("15.4: Option to configure qualification rule is available");
		qualificationlibrary.selectConfigureOptionAccordinglyRulesIsDisplayed(Constants.Qualification_rule);
		
		reportLog("15.3: Option to Add Qualification Group is active");
		qualificationlibrary.verifyAddButtonIsEnabled();
		
		reportLog("16.0: Select option to Add Qualification Group");
		qualificationlibrary.selectOptionToAddQualificationGroup();
		
		reportLog("16.1: Add Qualification Group modal window is closed");
		qualificationlibrary.verifyAddQualificationLibraryPopupNotDisplayed();
		
		reportLog("16.2: User is navigated to the Qualification Library screen");
		qualificationlibrary.verifyQualificationLibraryPage();

		reportLog("16.3:  The list of Qualification Groups is refreshed with newly added group");
		qualificationlibrary.verifyQualificationGroupListUpdated(qualificationGroupCount);
		String qualificationGroupCount_2=qualificationlibrary.getQualificationGroupCount();

		reportLog("17.0: Select option to Add new Group");
		qualificationlibrary.selectOptionToAddGroup();

		reportLog("17.1: Qualification Group modal window is displayed");
		qualificationlibrary.verifyQualificationLibraryPopupDisplayed();
		
		reportLog("17.2: Provide non-unique Qualification Group Display name");
		qualificationlibrary.enterRequiredFieldValue(Constants.DisplayName, nonUniqueDisplayName);

		reportLog("17.3: Provided name is displayed in the Display name field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.DisplayName, nonUniqueDisplayName);

		reportLog("17.4: Provide unique Qualification Group Library name");
		qualificationlibrary.enterRequiredFieldValue(Constants.libraryName,uniqueName);

		reportLog("17.5: Provided name is displayed in the Library name field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.libraryName,uniqueName);

		reportLog("17.6: Select Form groups");
		qualificationlibrary.selectFormGroupFromFormGroupsList(Constants.Assessment_CAM);
		qualificationlibrary.selectFormGroupFromFormGroupsList(Constants.Assessment_MMSEFormName);

		reportLog("17.7:  Form groups are selected");
		qualificationlibrary.verifySelectedFormGroupDisplayed(Constants.Assessment_CAM);
		qualificationlibrary.verifySelectedFormGroupDisplayed(Constants.Assessment_MMSEFormName);

		reportLog("17.8: Select option to Add Qualification group");
		qualificationlibrary.selectOptionToAddQualificationGroup();

		reportLog("17.9: Validation message that Display name is not unique is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.DisplayName);

		reportLog("18.0: Provide unique Qualification Group Display name");
		qualificationlibrary.enterRequiredFieldValue(Constants.DisplayName,uniqueName);

		reportLog("18.1: Provided name is displayed in the Display name field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.DisplayName,uniqueName);

		reportLog("18.2: Provide non-unique Qualification Group Library name");
		qualificationlibrary.enterRequiredFieldValue(Constants.libraryName,nonUniqueLibraryName);

		reportLog("18.3: Provided name is displayed in the Library name field");
		qualificationlibrary.verifyRequiredFieldEnteredValue(Constants.libraryName,nonUniqueLibraryName);

		reportLog("18.4: Select option to Add Qualification group");
		qualificationlibrary.selectOptionToAddQualificationGroup();

		reportLog("18.5: Validation message that Library name is not unique is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.libraryName);

		reportLog("19.0: Select Cancel option");
		qualificationlibrary.selectCancelOption();
		
		reportLog("19.1: Confirm cancellation in the confirmation dialog");
		qualificationlibrary.confirmCancellationInConfirmDialogue();
		String qualificationGroupCount_3=qualificationlibrary.getQualificationGroupCount();
        Assert.assertEquals(qualificationGroupCount_2, qualificationGroupCount_3);
		
		reportLog("19.2: Add Qualification Group dialog is closed without saving");
		qualificationlibrary.verifyAddQualificationLibraryPopupNotDisplayed();
		
		reportLog("Restoring PreRequisite");
		qualificationlibrary.deleteQualificationGroup(Constants.uniqueLibraryName,SuperAdminUN, SuperAdminPW);
		qualificationlibrary.verifyQualificationGroupDeleted(Constants.uniqueLibraryName);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
	}
}
