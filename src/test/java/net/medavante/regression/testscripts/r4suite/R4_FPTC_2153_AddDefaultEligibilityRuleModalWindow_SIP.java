/**
 *@author 
* @date 10/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2153   Test Case Name: Add Default Eligibility Rule modal window- V26
*  pre-qualification : 1. At least one Study with at least one Form Group (Scale) exists for the test
                       2. At least one Qualification Group exists in the Qualification Library. Default Eligibility Rule
                        is not configured for the Qualification Group
                       3. At least one User with the claim to manage Qualification Library exists

* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.qualificationlibrary.QualificationLibraryPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_2153_AddDefaultEligibilityRuleModalWindow_SIP extends BaseTest {

	private String control_AddGroup="Add Group",control_AddCondition="Add Condition",control_Add="Add",control_Cancel=" Cancel",
			InvalidDescription=generateRandomString(501),attribute_1="variables",attribute_2="operator";
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2153_AddDefaultEligibilityRuleModalWindow_SIP(String browser) {
		super(browser);
	}

    @BeforeMethod
   	public void getTestData() throws Exception {
   		System.setProperty("className", getClass().getSimpleName());
   	}
    
    @Test(description="FP-TC-2153--Add Default Eligibility Rule modal window- V26")
    public void R4_FPTC_2153_AddDefaultEligibilityRuleModalWindow()
    {
    	
    	reportLog("2.0: Log in to the Portal as User Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, AT_Password);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("3.0: Navigate to the Qualification Library");
		qualificationlibrary = dashBoardPage.selectHorizontalUpperNavMenuItem(QualificationLibraryPage.class,
				Constants.ConfigureNavText, Constants.QualificationLibraryText);
		
		reportLog("3.1: The list of Qualification groups is displayed");
		qualificationlibrary.verifyQualificationLibraryPage();
		
		reportLog("3.2: The Qualification Group Pr#2 is displayed in the list");
		qualificationlibrary.applyFilterUnderLibraryName(Constants.Qualification_Pre);
		qualificationlibrary.verifyQualificationGroupIsDisplayed(Constants.Qualification_Pre);
		
		reportLog("4.0: Select an action to open Qualification Group Pr#2");
		qualificationlibrary.selectQualificationConfiguration(Constants.Qualification_Pre);
		
		reportLog("4.1: Select an action to configure Default Eligibility rules");
		qualificationlibrary.selectConfigureOptionAccordinglyRules(Constants.Eligibility_rule);

		reportLog("4.2: Add Eligibility Rule modal window is displayed with following fields and controls:");
		qualificationlibrary.verifyAddEligibilityRulePopUPWindowDisplayed();

		reportLog("Description (required)");
		qualificationlibrary.verifyFieldIsDisplayedOnAddRulePopUp(Constants.Field_Description);
		
		reportLog("Expression");
		qualificationlibrary.verifyFieldIsDisplayedOnAddRulePopUp(Constants.Field_Expression);

		reportLog("Add Group control");
		qualificationlibrary.verifyRespectiveControlDisplayed(control_AddGroup);
		
		reportLog("Add Condition control");
		qualificationlibrary.verifyRespectiveControlDisplayed(control_AddCondition);

		reportLog("Clear all control");
		qualificationlibrary.verifyClearAllControlIsDisplayed();
		
		reportLog("Add control (disabled)");
		qualificationlibrary.verifyAddControlIsDisabled();
		qualificationlibrary.verifyControlIsdisplayed(control_Add);
		
		reportLog("Cancel control");
		qualificationlibrary.verifyControlIsdisplayed(control_Cancel);
		
		reportLog("5.0: Enter more than 500 symbols in the Description field");
		qualificationlibrary.enterDescriptionInAddRulePopup(InvalidDescription);

		reportLog("5.1: Entered text is displayed in the field");
		qualificationlibrary.verifyEnteredValueInDescriptionFieldInAddRulePopUp(InvalidDescription);

		reportLog("5.2: Description field is highlighted as invalid");
		qualificationlibrary.verifyFieldIsHighlightedAsInvalid(Constants.Field_Description);
		
		reportLog("5.3: Validation icon with message is displayed");
		qualificationlibrary.verifyDisplayedValidationMessage(Constants.Field_Description);

		reportLog("6.0: Enter less than 500 symbols in the Description field");
		qualificationlibrary.enterDescriptionInAddRulePopup(Constants.uniqueDescription);

		reportLog("6.1:  Entered text is displayed in the Description field");
		qualificationlibrary.verifyEnteredValueInDescriptionFieldInAddRulePopUp(Constants.uniqueDescription);

		reportLog("6.2: Description field is not highlighted as required" );
		qualificationlibrary.verifyFieldValueIsValid(Constants.Field_Description);

		reportLog("7.0: Select an action to add a condition");
		qualificationlibrary.selectAddConditionControl();
		
		reportLog("7.1: The Condition is added with following controls:");
		qualificationlibrary.verifyConditionIsDisplayed();

		reportLog("Drop-down list to select a Value or Sum. Value is selected by default");
		qualificationlibrary.verifyDropDownIsDisplayed();
		qualificationlibrary.verifyDropDownDefaultSelectedValue(Constants.Option_Value);
		qualificationlibrary.selectListBoxDropDown();
		qualificationlibrary.verifyDropDownOption(Constants.Option_Sum);
		qualificationlibrary.selectListBoxDropDown();
		qualificationlibrary.verifyDropDownOption(Constants.Option_Value);

		reportLog("Drop-down list to select a Question:"); 
		reportLog("Education level"); 
		reportLog("Field of study"); 
		reportLog("Indication areas experience"); 
		reportLog("Language");
		qualificationlibrary.verifyRespectiveDropDownIsDisplayed(attribute_1);
		qualificationlibrary.verifyOptionsInQuestionDropdown(Constants.role_educationDegree);
		qualificationlibrary.verifyOptionsInQuestionDropdown(Constants.role_IndicationAreaExperience);
		qualificationlibrary.verifyOptionsInQuestionDropdown(Constants.role_language);
		qualificationlibrary.verifyOptionsInQuestionDropdown(Constants.role_fieldofStudy);

		reportLog("7.2: Drop-down list to select operator:");
		reportLog("Equals to");
		reportLog("Not equals to");
		qualificationlibrary.verifyRespectiveDropDownIsDisplayed(attribute_2);
		qualificationlibrary.verifyDefaultSelectedDropDownFieldValue(attribute_2, Constants.Option_Equals);
		qualificationlibrary.selectDropDownOption(attribute_2);
		qualificationlibrary.verifyDropDownOption(Constants.Option_Equals);
		qualificationlibrary.selectDropDownOption(attribute_2);
		qualificationlibrary.verifyDropDownOption(Constants.Option_NotEquals);

		reportLog("7.2: Drop-down list to select an answer");
		qualificationlibrary.verifyDropDownToSelectAnAnswerIsDisplayed(attribute_1);
		
		reportLog("7.3: Control to delete the condition");
		qualificationlibrary.verifyDeleteConditionControlIsDisplayed();
		
		reportLog("7.4: Control to select AND/OR operands");
		qualificationlibrary.verifyAND_ORControlIsDisplayed();
		
		reportLog("8.0: As a value select any value from Indication areas");
		qualificationlibrary.selectValueFromDropDown(attribute_1, Constants.Role);
		
		reportLog("8.1: Selected value is displayed");
		qualificationlibrary.verifyDefaultSelectedDropDownFieldValue(attribute_1, Constants.Role);
		
		reportLog("9.0: As a value select 'Education degree'");
		qualificationlibrary.selectValueFromDropDown(attribute_1, Constants.role_educationDegree);
		
		reportLog("9.1: As a condition - one of the expression is displayed:"); 
		reportLog("Equals to"); 
		reportLog("Not equals to");
		qualificationlibrary.verifyDefaultSelectedDropDownFieldValue(attribute_2, Constants.Option_Equals);

		reportLog("9.2: As a meaning values following items are displayed:");
		reportLog("High School"); 
		reportLog("2 Year Degree or Associates"); 
		reportLog("3 Year Degree");
		reportLog("4 Year Degree"); 
		reportLog("Master’s or 2 Cycle Degree");
		reportLog("Doctoral"); 
		reportLog("Doctor or Bachelor of Medicine/Surgery");
		reportLog("Practical/Vocational Nurse"); 
		reportLog("Registered Nurse"); 
		reportLog("Advanced Nursing License");
		reportLog("Physician Assistan");
		reportLog("Nurse Practitioner");
        qualificationlibrary.verifyOptionsforAnswerDropdownDisplayed();
        
        reportLog("10.0: As a value select 'Field of study'");
		qualificationlibrary.selectValueFromDropDown(attribute_1, Constants.role_fieldofStudy);
        
        reportLog("10.1: As a condition - one of the expression is displayed:"); 
		reportLog("Equals to"); 
		reportLog("Not equals to");
		qualificationlibrary.verifyDefaultSelectedDropDownFieldValue(attribute_2, Constants.Option_Equals);

        reportLog("10.2: As a meaning values following items are displayed:"); 
        reportLog("Clinical Psychology"); 
        reportLog("Clinical Neuropsychology"); 
        reportLog("Clinical Research"); 
        reportLog("Educational or School Psychology"); 
        reportLog("Endocrinology"); 
        reportLog("Family medicine"); 
        reportLog("Geriatric Medicine"); 
        reportLog("Human Development"); 
        reportLog("Medical Genetics"); 
        reportLog("Medicine"); 
        reportLog("Neurology"); 
        reportLog("Nursing"); 
        reportLog("Occupational Therapy"); 
        reportLog("Pediatric Medicine"); 
        reportLog("Physician Assistant"); 
        reportLog("Physical Therapy"); 
        reportLog("Psychiatry"); 
        reportLog("Social Work"); 
        reportLog("Sociology / Social Sciences"); 
        reportLog("Speech and Language Therapy"); 
        reportLog("Surgery"); 
        reportLog("Other");
        qualificationlibrary.verifyOptionsforAnswerDropdownDisplayed();

        reportLog("11.0: Select Sum from the drop-down list");
		qualificationlibrary.selectValueFromListBoxDropDown(Constants.Option_Sum);

        reportLog("11.1: - Following controls are displayed for the condition:");
        reportLog("Drop-down list to select a Question is empty");
        qualificationlibrary.verifyDropDownListToSelectQuestionDisplayedWithValue(Constants.value_Empty);
		
        reportLog("11.2: Drop-down list to select operator:");
        reportLog("Equals to");
        reportLog("Not equals to");
        qualificationlibrary.selectDropDownOption(attribute_2);
		qualificationlibrary.verifyDropDownOption(Constants.Option_Equals);
		qualificationlibrary.selectDropDownOption(attribute_2);
		qualificationlibrary.verifyDropDownOption(Constants.Option_NotEquals);

        reportLog("11.3: Entry field to specify a numeric answer");
		qualificationlibrary.verifyDropDownToSelectAnAnswerIsDisplayed(attribute_1);

		reportLog("12.0: Select an action to delete condition");
		qualificationlibrary.selectDeleteControl();
		
		reportLog("12.1: The Condition is deleted");
		qualificationlibrary.verifyConditionIsNotDisplayed();
		
		reportLog("13.0: Select an action to add a Group");
		qualificationlibrary.addGroup();

		reportLog("13.1: The Group is added with following controls:");
        qualificationlibrary.verifyGroupIsAdded();

		reportLog("Control to select AND/OR operands");
		qualificationlibrary.verifyAND_ORControlIsDisplayed();
		
		reportLog("Add Group control Displayed"); 
		qualificationlibrary.verifyAddGroupButtonIsDisplayed();
		
		reportLog("Add Condition control Displayed");
		qualificationlibrary.verifyRespectiveControlDisplayed(control_AddCondition);
		
		reportLog("Control to delete the Group Displayed");
		qualificationlibrary.verifyDeleteGroupControlIsDisplayed();
		
		reportLog("14.0: Select an action to delete the Group");
		qualificationlibrary.selectActiontoDeleteGroup();
		
		reportLog("14.1: The Group is deleted");
		qualificationlibrary.verifyGroupIsdeleted();
		
		reportLog("15.0: Select an action to add a Group");
		qualificationlibrary.addGroup();
		
		reportLog("15.1: Select an action to add nested Group");
		qualificationlibrary.selectAnOptionToClickNestedControl(control_AddGroup);
		
		reportLog("15.2: Select an action to add a Condition");
		qualificationlibrary.selectAnOptionToClickNestedControl(control_AddCondition);

		reportLog("15.3: Select items in the drop-down lists");
		qualificationlibrary.addCondition(Constants.Role);
		
		reportLog("15.4: Expression field is filled by defined rule");
		qualificationlibrary.verifyCondition(Constants.Role);
		
		reportLog("16.0: Select an action to clear all");
		qualificationlibrary.selectClearAllControl();
		
		reportLog("16.1: Groups and condition are deleted");
		qualificationlibrary.verifyGroupIsdeleted();

		reportLog("17.0: Select an action to add a Group");
		qualificationlibrary.addGroup();

		reportLog("17.1: Select an action to add a Condition");
		qualificationlibrary.selectAddConditionControl();
		
		reportLog("17.2: Select items in the drop-downs");
		qualificationlibrary.addCondition(Constants.Role);

		reportLog("17.3: Select an action to cancel");
		qualificationlibrary.selectRespectiveControl(control_Cancel);
		
		reportLog("17.4: Add Eligibility Rule modal window is closed without saving");
		qualificationlibrary.verifyAddEligibilityRulePopUpWindowNotDisplayed();
		qualificationlibrary.verifyRecentlyProvidedDescriptionWithRuleIsNOTDisplayed(
				Constants.Qualification_rule,Constants.uniqueDescription);
		
		reportLog("18.0: Select an action to configure Default Eligibility rules");
		qualificationlibrary.selectConfigureOptionAccordinglyRules(Constants.Eligibility_rule);

		reportLog("18.1: Enter text in the Description field");
        qualificationlibrary.enterDescriptionInAddRulePopup(Constants.uniqueDescription_1);

		reportLog("18.2: Select an action to add a Condition");
		qualificationlibrary.selectAddConditionControl();

		reportLog("18.3: Select items in the drop-down lists");
		qualificationlibrary.addCondition(Constants.role_educationDegree);

		reportLog("18.4: Select an action to save (Add control)");
        qualificationlibrary.selectAddOptionOnQualificationRulePopUp();

		reportLog("18.5: Qualification Group modal window is displayed");
		qualificationlibrary.verifyQualificationLibraryPopupDisplayed();

		reportLog("18.6: Text from the description is displayed in Default Eligibility rules section");
		qualificationlibrary.verifyRecentlyProvidedDescriptionWithRuleIsDisplayed(
				Constants.Eligibility_rule,Constants.uniqueDescription_1);
		
		reportLog("Restoring Prerequisite");
		qualificationlibrary.selectCancelOption();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		

    }
}
