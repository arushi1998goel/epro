package net.medavante.mobile.testscripts;

import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.enums.TagName;
import net.medavante.portal.pages.FormLibraryPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_4025_CreateCompleteMobileFormFromFormManagerAndAssignToStudy_MAP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_4025_CreateCompleteMobileFormFromFormManagerAndAssignToStudy_MAP(String browser) {
		super(browser);
	}

	@BeforeTest()
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("");
		formAbbreviation = "Form@" + GenerateRandomNumber(3);
		formTitle = "TestForm" + GenerateRandomNumber(4);
		required = new HashMap<String, String>();
		required.put(Constants.Abbreviation, formAbbreviation);
		required.put(Constants.Title, formTitle);
	}

	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_4025 Test Case Name: Show that user with appropriate claims is able to:

                                               - Create new Mobile Form in Form Manager;

                                               - Add created Form to the corresponding Study;

                                               - Create Calendar Event including new created Mobile Form;

                                               - Assign Event to the corresponding Subject.

                                    Show that Subject is able to compete assigned Calendar Event with included Mobile Form.

                                    Show that completed mobile Event displayed with correct information on Portal.
                                    
                                    
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 */
	
	
	@Test
	public void CreateCompleteMobileFormFromFormManagerAndAssignToStudy() throws Exception {
		reportLog("1.1 log in to MA Portal ");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		System.out.println("***************" + formName);

		reportLog("1.2 User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1 Navigate to Forms manager");
		formLibraryPage = dashBoardPage.selectHorizontalUpperNavMenuItem(FormLibraryPage.class,
				Constants.ConfigureNavText, Constants.FormsLibraryNavText);

		reportLog("2.2 Form Listing screen is displayed");
		formLibraryPage.verifyFormListingScreen(TagName.strong, "Form Listing");

		reportLog("3.1 Select action to create New Form");
		formLibraryPage.clickOnNewFormButton();

		reportLog("3.2  New Form creation modal window is displayed");
		formLibraryPage.verifyNewFormWindowModalIsDisplayed(TagName.h2, "New Form");

		reportLog("3.3 Following Form factors with description are displayed:- Tablet ,Mobile");
		formLibraryPage.verifyMobileAndTabletAreDisplayedAsFormFactor(Constants.isEnabled, Constants.FormFactorMobile, Constants.FormFactorTablet);

		reportLog("4.1 Choose Form Factor: 'Mobile' and proceed to the next step");
		formLibraryPage.chooseFormFactor(Constants.FormFactorMobile);

		reportLog("4.2 New Form (Mobile) modal window is displayed with the following items:");
		formLibraryPage.verifyNewFormWindowModalIsDisplayed(TagName.h2, "New Form (Mobile)");

		reportLog(
				"4.3 required field - Title: required field - Version - Initial Phase: 'Initiate' by default - Color: 'Dark Gray' by default ");
		formLibraryPage.verifyNecessayFieldsAreRequired();

		reportLog("4.4 'Next' control is disabled ");
		formLibraryPage.verifyNextButtonStatus(Constants.isDisabled);

		reportLog("4.5 'Back'/'Cancel' controls are enabled");
		formLibraryPage.verifyCancelAndBackContorlButtonAreEnabled(Constants.isEnabled);

		reportLog("5.1 Fill all required fields ");
		formLibraryPage.fillRequiredFields(required);

		reportLog("5.1.1 proceed to the next step");
		formLibraryPage.clickOnNextButton();

		reportLog("6.1 New Form (Mobile) modal window is displayed");
		formLibraryPage.verifyNewFormWindowModalIsDisplayed(TagName.h2, "New Form (Mobile)");

		reportLog("6.2 Verify Author of Original Paper Questionnaire field Is Displayed");
		formLibraryPage.verifyAuthorOfOriginalPaperQuestionnaireFieldIsDisplayed(Constants.isEnabled);

		reportLog("6.3 Change Note field and Description field are displayed");
		formLibraryPage.verifyChangeNoteFieldAndDescriptionFieldAreDisplayed(Constants.isEnabled);

		reportLog("6.4 Attached Documents with 'Add Document' control");
		formLibraryPage.verifyAttachedDocumentsWithAddDocumnetControl(Constants.isEnabled);

		reportLog("6.5 'Next'/'Back'/'Cancel' controls are enabled ");
		formLibraryPage.verifyNextBackCancelButtonIsEnabled(Constants.isEnabled);

		reportLog("7.1 Leave fields empty and proceed to the next step");
		formLibraryPage.clickOnNextButton();

		reportLog("7.2 New Form (Mobile) modal window is displayed ");
		formLibraryPage.verifyNewFormWindowModalIsDisplayed(TagName.h2, "New Form (Mobile)");

		reportLog("7.3 Verify Tags search field - List of Tags");
		formLibraryPage.verifyTagSearchFieldIsDisplayedWithListOfTags(Constants.isEnabled);

		reportLog("7.4 'Next'/'Back'/'Cancel' controls are enabled");
		formLibraryPage.verifyNextBackCancelButtonIsEnabled(Constants.isEnabled);

		reportLog("8.1 Using search field select any tag and proceed to the next step");

		formLibraryPage.selectTagInSearchField(Constants.TagName1).clickOnNextButton();

		reportLog("8.2 New Form (Mobile) modal window is displayed ");
		formLibraryPage.verifyNewFormWindowModalIsDisplayed(TagName.h2, "New Form (Mobile)");

		reportLog("8.3 verify 'Study Name' drop-down list and First Patient Visit' date picker field");
		formLibraryPage.verifyStudyNameDropDownList(Constants.isEnabled).verifyDatePickerField(Constants.isEnabled);

		reportLog("8.4 Attach License' with 'Add PDF' control ");
		formLibraryPage.verifyAttachLicenseWithAddPdfControl("Attach License", Constants.isEnabled);

		reportLog("8.5 'Add Study' control disabled until Study is not selected");
		formLibraryPage.verifyAddStudyButtonStatus(Constants.isDisabled);

		reportLog("8.6 'Create Form'/'Back'/'Cancel' controls are enabled");
		formLibraryPage.verifyCancelAndBackContorlButtonAreEnabled(Constants.isEnabled);

		reportLog("9.1 Select Study Name ");
		formLibraryPage.selectStudyName(Constants.StudyName1);

		reportLog("9.2 Select First Patient Visit' date from date picker");
		formLibraryPage.selectFirstPatientVisitFromDatePicker("30");

		reportLog("9.3 'Add Study' control became enabled");
		formLibraryPage.verifyAddStudyButtonStatus(Constants.isEnabled);
	
		reportLog("10.1 Select action to create new Form	- Form listing screen is displayed");
		formLibraryPage.clickOnCreateFormButton().verifyFormListingScreen(TagName.strong, "Form Listing");
		
		reportLog("10.2 New Form is created and displayed with the following information: Abbreviation, Tags selected in a Step#8 and  Form Factor: 'Mobile' and delete control is displayed"
				+ "Expand/Collapse control");
		formLibraryPage.veriyfNewFormIsDisplayedWithFollowingInfo(formAbbreviation,Constants.TagName1,Constants.FormFactorMobile,Constants.delete);
		
		reportLog("11.1 Expand row and click On Open button");
		formLibraryPage.expandRow(formAbbreviation);
		formLibraryPage.clickOnOpenButton(formAbbreviation);
				
		reportLog("11.2 Create Design'	Form Designer screen is displayed");
		formLibraryPage.clickOnCreateDesignButton();
		formLibraryPage.verifyFormDesignScreenIsDisplayed();
		
		reportLog("12.1 Using drag and drop action add all existing Input  and text fields from the toolbox (insert additional pages if needed)");
		formLibraryPage.performDragAndDropForInputAndTextField();
		
		reportLog("12.2: Add text (names) for selected fields ");
		formLibraryPage.addTextForSelectedFields(Constants.dateLinktext, Constants.guageLinktext, Constants.keyboardLinktext, Constants.multipleChoiceLinktext, Constants.numericLinktext, Constants.singleChoiceLinktext, Constants.singleChoiceListLink, Constants.timeLinktext, Constants.headingLinktext, Constants.dateLinktextUpdated, Constants.guageLinktextUpdated, Constants.keyboardLinktextUpdated, Constants.multipleChoiceLinktextUpdated, Constants.numericLinktextUpdated, Constants.singleChoiceLinktextUpdated, Constants.singleChoiceListLinkUpdated, Constants.timeLinktextUpdated, Constants.headingLinktextUpdated);
		
		reportLog("12.3: Save changes");
		formLibraryPage.selectSaveIcon();
		
		reportLog("12.4: All selected fields are displayed at the pages");
		formLibraryPage.verifyAllSelectedFieldsAreDisplayed(Constants.dateLinktextUpdated, Constants.guageLinktextUpdated,Constants.keyboardLinktextUpdated,Constants.multipleChoiceLinktextUpdated,Constants.numericLinktextUpdated,Constants.singleChoiceLinktextUpdated,Constants.singleChoiceListLinkUpdated,Constants.timeLinktextUpdated,Constants.headingLinktextUpdated);

        reportLog("12.5: Fields text (custom name) are displayed");
        formLibraryPage.verifyFieldsTextAreDisplayed(Constants.dateLinktextUpdated, Constants.guageLinktextUpdated,Constants.keyboardLinktextUpdated,Constants.multipleChoiceLinktextUpdated,Constants.numericLinktextUpdated,Constants.singleChoiceLinktextUpdated,Constants.singleChoiceListLinkUpdated,Constants.timeLinktextUpdated,Constants.headingLinktextUpdated);
		
		reportLog("12.6: Form with all existing Input and Text fields is created");
		formLibraryPage.selectMoveToFirstPageControl();
		formLibraryPage.verifyAllSelectedFieldsAreDisplayed(Constants.dateLinktextUpdated, Constants.guageLinktextUpdated,Constants.keyboardLinktextUpdated,Constants.multipleChoiceLinktextUpdated,Constants.numericLinktextUpdated,Constants.singleChoiceLinktextUpdated,Constants.singleChoiceListLinkUpdated,Constants.timeLinktextUpdated,Constants.headingLinktextUpdated);
		
		reportLog("13.1: Navigate back to the Form Listing screen ");
		formLibraryPage.getDriver().navigate().refresh();
		formLibraryPage.clickOnVirgilLogo();
	    formLibraryPage = dashBoardPage.selectHorizontalUpperNavMenuItem(FormLibraryPage.class,
				Constants.ConfigureNavText, Constants.FormsLibraryNavText);
		
		reportLog("13.2: Using search field find newly created Form");
        formLibraryPage.findNewlyCreatedFormUsingSearchField(formAbbreviation);
        
		reportLog("14.1: Select action to open Form");
		formLibraryPage.expandRow(formAbbreviation);
		formLibraryPage.clickOnOpenButton(formAbbreviation);
		formLibraryPage.clickOnEditDesignButton();
		
		reportLog("14.2 : Form is opened");
		formLibraryPage.verifyAllSelectedFieldsAreDisplayed(Constants.dateLinktextUpdated, Constants.guageLinktextUpdated,Constants.keyboardLinktextUpdated,Constants.multipleChoiceLinktextUpdated,Constants.numericLinktextUpdated,Constants.singleChoiceLinktextUpdated,Constants.singleChoiceListLinkUpdated,Constants.timeLinktextUpdated,Constants.headingLinktextUpdated);
		
		reportLog("15.1: Expand row");
		
		formLibraryPage.getDriver().navigate().refresh();
		formLibraryPage.clickOnVirgilLogo();
		formLibraryPage = dashBoardPage.selectHorizontalUpperNavMenuItem(FormLibraryPage.class,
				Constants.ConfigureNavText, Constants.FormsLibraryNavText);
		formLibraryPage.expandRow(formAbbreviation);
		
		reportLog("15.2: Activate drop-down for 'Edit Design'");
		formLibraryPage.clickOnOpenButton(formAbbreviation);
		formLibraryPage.activateDropdownForEditDesign();
		formLibraryPage.selectUnlockDesign(Constants.unlockDesignText);
		formLibraryPage.activateDropdownForEditDesign();
		
		reportLog("15.3: Select 'Start Review' action");
		formLibraryPage.selectStartReviewControl(Constants.startReviewText);
		
		reportLog("15.4: Start Review' modal window with following fields is displayed: Phase: 'Review ,Target Date: DD-MON-YY ,Comment field ,Username field and Password field");
		formLibraryPage.verifyStartReviewModalWindowElements(Constants.reviewTextStartReviewWindow);
		
		reportLog("16.1: Input correct credentials for user PR.#2");
		reportLog("16.2: Select 'Ok' control");
		formLibraryPage.inputCorrectCredentials(SuperAdminUN, SuperAdminPW);
		
		reportLog("16.3: Form is displayed on the Form Listing screen");
		formLibraryPage.verifyFormOnFormListingScreen(formAbbreviation);
		/***
		 * On UI View design is showing instead of edit design
		 */
		
		reportLog("17.1: Activate drop-down for 'Edit Design' ");
		formLibraryPage.activateDropDownForViewDesign();
		
		reportLog("17.2: Select 'Start Testing' action");
		formLibraryPage.selectStartTestingControl();
		reportLog("17.3: 'Start Testing' modal window with following fields is displayed:"); 
		reportLog("- Phase: 'Test'" );
		reportLog("- Target Date: DD-MON-YYYY");
		reportLog("- Comment field");
		reportLog("- Username field");
		reportLog("- Password field");
		formLibraryPage.verifyStartReviewModalWindowElements(Constants.testLabel);
		
		reportLog("18.1: Input correct credentials for user PR.#2");
		reportLog("18.2: Select 'Ok' control");
		formLibraryPage.inputCorrectCredentials(SuperAdminUN, SuperAdminPW);

		reportLog("18.3: Form is displayed on the Form Listing screen");
		formLibraryPage.verifyFormOnFormListingScreen(formAbbreviation);

		reportLog("19.1: Activate drop-down for 'Edit Design' ");
		formLibraryPage.activateDropDownForViewDesign();

		reportLog("19.2: Select 'Start Regulatory Review' action");
		formLibraryPage.selectStartRegulatoryReviewAction();
		
		reportLog("19.3: 'Start Regulatory Review' modal window with following fields is displayed:");
		
		reportLog("- Phase: 'Regulatory'");
		reportLog("- Target Date: DD-MON-YYYY");
		reportLog("- Comment field");
		reportLog("- Username field");
		reportLog("- Password field");
		formLibraryPage.verifyStartReviewModalWindowElements(Constants.regulatory);
		
		reportLog("20.1: Input correct credentials for user PR.#2");
		reportLog("20.2: Select 'Ok' control");
		formLibraryPage.inputCorrectCredentials(SuperAdminUN, SuperAdminPW);

		reportLog("20.3: Form is displayed on the Form Listing screen");
		formLibraryPage.verifyFormOnFormListingScreen(formAbbreviation);

		reportLog("21.1: Activate drop-down for 'Edit Design'");
		formLibraryPage.activateDropDownForViewDesign();
		

		reportLog("21.2: Select 'Set as Complete' action");
		formLibraryPage.selectSetAsCompleteAction();
		
		reportLog("21.3: 'Set as Complete' modal window with following fields is displayed:");
		reportLog("- Phase: 'Complete'");
		reportLog("- Target Date: DD-MON-YYYY");
		reportLog("- Comment field");
		reportLog("- Username field");
		reportLog("- Password field");
		
		formLibraryPage.verifyStartReviewModalWindowElements(Constants.complete);

		
		reportLog("22.1: Input correct credentials for user PR.#2 ");
		reportLog("22.2:  Select 'Ok' control");
		formLibraryPage.inputCorrectCredentials(SuperAdminUN, SuperAdminPW);

		reportLog("22.3: Form is displayed on the Form Listing screen with 'Complete' status");
		formLibraryPage.verifyCompletedForm();
		
		reportLog("23.1: Navigate to Study Setup -> Forms Library ");
		formLibraryPage.getDriver().navigate().refresh();
		formLibraryPage.clickOnVirgilLogo();
		adminstrationOrganizationPage=formLibraryPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationFormLibraryPage= adminstrationOrganizationPage.navigateFormsLibrary();
		 
		reportLog("23.2: Select action to Add Form ");
		formLibraryPage.clickOnNewFormButton();

		reportLog("23.3: Fill all required fields");
		
		reportLog("23.4: Select Form factor: Mobile");
		
		reportLog("23.5: Save new form");
		reportLog("23.6: Form is saved");
		
		reportLog("24.1: Add Form version:");
		reportLog("- Form Template Type: VForm");
		reportLog("- Add Language");
		reportLog("- Add Template:");
		reportLog("-- Using search field select name of newly created Form (Step#21) Save Version");
		reportLog("24.2: Forms Version is created");
		
		reportLog("25.1: Navigate to Studies -> Select Study Pr.#1");
		reportLog("25.2: Open Forms tab");
		reportLog("25.3: Select action to add scale ");
		reportLog("25.4: Using search field select name of newly created Form ");
		reportLog("25.5: Save changes");
		reportLog("25.6: New Form is added to the Scales list");
		
		reportLog("26.1: Select newly created Form and activate the Languages");
		reportLog("26.2: Select Language and save changes");
		reportLog("26.3: Language is activated");
		reportLog("27.1: Navigate to Schedule tab -> Create 'Questionnaire Template' with Event:");
		reportLog("27.2: Select newly created Form for Questionnaire");
		reportLog("27.3: Save");
		reportLog("27.4: Questionnaire Template is created");
		
		reportLog("28.1: Add Questionnaire Template created in Step#27 in Calendar");
		reportLog("28.2: Questionnaire Template is added to the Calendar");
		reportLog("29.1: Navigate to the Subject Pr.#3 detail screen");
		reportLog("29.2:  From the category list select 'Questionnaires'");
		reportLog("29.3: Questionnaire list is empty");
		
		
		
		
		
		
		
		
		
		
		
	}
}
