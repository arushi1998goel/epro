package net.medavante.portal.pages.administration;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSchedulePage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Calendar')]")
	private WebElement calendarSchedule;

	@FindBy(xpath = "//button[contains(text(),'Visit Templates')]")
	private WebElement visitTemplateSchedule;

	@FindBy(xpath = "//button[contains(text(),'Questionnaire Templates')]")
	private WebElement questionnaireTemplatesSchedule;

	@FindBy(xpath = "//span[@class='icon-edit']")
	private WebElement editIconSchedule;

	@FindBy(xpath = "//div[@id='questionnaire-templates-tab']//button[contains(@title,'Create Questionnaire')]")
	private WebElement createQuestionnaireTempleteIcon;

	@FindBy(xpath = "//div[@class='modal-dialog dialog-md']//*[contains(text(),'Create Questionnaire Template')]")
	private WebElement createQuestionnaireTempletePopUp;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(@class,'btn-active')]")
	private WebElement saveButton;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(@class,'btn-default btn-without-icon')]")
	private WebElement cancelButton;

	@FindBy(xpath = "//div[@class='modal-footer']/button[contains(@class,'btn-default')]")
	private WebElement createAnotherButton;

	@FindBy(xpath = "//input[@id='questionnaire-template-name-input']")
	private WebElement nameField;

	@FindBy(xpath = "//*[@id='questionnaire-template-comment-input']")
	private WebElement commentField;

	@FindBy(xpath = "(//div[@class='modal-header has-action']/button[@class='close'])[1]")
	private WebElement closeIcon;

	@FindBy(xpath = "//*[contains(@class,'modal-title bold') and contains(text(),'Create Event')]")
	private WebElement createEventPopup;

	@FindBy(xpath = "//input[@id='questionnaire-template-event-name-input']")
	private WebElement nameFieldOnCreateEventPopUp;

	@FindBy(xpath = "//div[contains(@id,'event-dialog')]//button[@class='close']")
	private WebElement closeOnEventPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-event-dialog']//button[contains(@class,'btn btn-active')]")
	private WebElement saveEventButton;

	@FindBy(xpath = "//div[@id='create-or-edit-event-dialog']//button[contains(@class,'btn btn-default ')]")
	private WebElement cancelEventButton;

	@FindBy(xpath = "//div[@id='create-or-edit-event-dialog']//span[contains(text(),'Create Another')]")
	private WebElement createAnotherEventButton;

	@FindBy(xpath = "//div[@class='scales-list']//span[@class='fake-choice']/input")
	private List<WebElement> scaleCheckBoxes;

	@FindBy(xpath = "(//div[@class='scales-list']//span[@class='fake-choice'])/input")
	private WebElement mobile_EI_Checkbox;

	@FindBy(xpath = "(//div[@class='scales-list']//span[@class='fake-choice'])[2]/input")
	private WebElement q_mobile_VP_Checkbox;

	@FindBy(xpath = "//div[@data-ng-switch-when='ByEvent']//input[contains(@class,'number-input') and @type='number']")
	private WebElement scheduleTime;

	@FindBy(xpath = "(//div[@data-ng-switch-when='ByEvent']//span[contains(@class,'k-state-default')])[2]")
	private WebElement afterEventDropDown;

	@FindBy(xpath = "//div[@data-ng-switch-when='Count']//input[contains(@class,'number-input')]")
	private WebElement recurInput;

	@FindBy(xpath = "//button[@title='Create Visit Template']/span")
	private WebElement createVisitTemplateIcon;

	@FindBy(xpath = "//button[contains(@title,'Template with Timepoints')]/span")
	private WebElement createVisitTemplateWithTimepointsIcon;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//*[contains(text(),'Create Visit Template')]")
	private WebElement createVisitTemplatePopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//button[@class='close']")
	private WebElement closeIconOnCreateVisitTemplatePopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//input[@id='visit-template-name-input']")
	private WebElement visitTemplateNameField;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//*[@id='visit-template-comment-input']")
	private WebElement visitTemplateCommentField;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//div[@class='flex-row']/span[@role='listbox' and contains(@class,'invalid')]")
	private WebElement visitAnalyticalType;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//input[@id='can-add-attachments-checkbox']")
	private WebElement canAddAttachmentsCheckbox;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//input[@id='can-be-unscheduled-checkbox']")
	private WebElement canbeUnscheduledCheckbox;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//input[@id='is-forms-ordered-enforced-checkbox']")
	private WebElement enforceOrderFormsCompletionCheckbox;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//div[@class='flex-row']/span/span[contains(@class,'k-state-disabled')]")
	private WebElement SubjectStatusUnscheduledDropDown;

	@FindBy(xpath = "(//div[@id='create-or-edit-visit-template-dialog']//div[contains(@class,'name-column')])[1]/label")
	private WebElement nameColumn;

	@FindBy(xpath = "(//div[@id='create-or-edit-visit-template-dialog']//div[@class='reporter-column'])[1]/span[1]")
	private WebElement reportedOutcome;

	@FindBy(xpath = "(//div[@id='create-or-edit-visit-template-dialog']//div[contains(@class,'recording-type')])[1]/span")
	private WebElement recordingType;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//button[@class='btn btn-default']")
	private WebElement CreateAnothervisitTemplateButton;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//button[contains(@class,'save-button')]")
	private WebElement visitTemplateSaveButton;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-dialog']//button[contains(@class,'default btn-without-icon')]")
	private WebElement visitTemplateCancelButton;

	@FindBy(xpath = "//ul[@id='analytical-type-drop-down_listbox']/li[text()='Baseline']")
	private WebElement analyticalTypeDropDown;

	@FindBy(xpath = "(//div[contains(@id,'visit-template-dialog')]//div[contains(@class,'name')]/input)[1]")
	private WebElement scaleCheckbox;

	@FindBy(xpath = "//div[@class='k-animation-container']/div[contains(@class,'k-state-border-up')]//ul/li[text()='Site Rater']")
	private WebElement selectReportedOutcomeOption;

	@FindBy(xpath = "//div[@class='k-animation-container']/div[contains(@class,'k-state-border-')]//ul/li[text()='None']")
	private WebElement recordingTypeOption;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-with-timepoints-dialog']//*[contains(text(),'Template with Timepoints')]")
	private WebElement createVisittemplateWithTimepointsPopUp;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//button[@class='close']")
	private WebElement closeButtonOnCreateVisittemplateWithTimepointsPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-with-timepoints-dialog']//input[contains(@id,'name')]")
	private WebElement nameFieldOnVisittemplateWithTimepointsPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-visit-template-with-timepoints-dialog']//*[contains(@id,'comment-input')]")
	private WebElement commentFieldOnVisittemplateWithTimepointsPopUp;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//input[contains(@id,'can-add-attachments')]")
	private WebElement addAttachmentsCheckbox;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//input[contains(@id,'can-be-unscheduled')]")
	private WebElement canbeUnscheduled;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//span[contains(@class,'k-dropdown-wrap')]")
	private WebElement subjectStatusWhenUnscheduled;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//button[@class='btn btn-default']")
	private WebElement createAnotherOnVisitTemplateWithTimepoints;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//button[contains(@class,'save-button')]")
	private WebElement saveButtonOnVisitTemplateWithTimepoints;

	@FindBy(xpath = "//div[contains(@id,'template-with-timepoints-dialog')]//button[contains(@class,'default btn-without-icon')]")
	private WebElement cancelButtonOnVisitTemplateWithTimepoints;

	@FindBy(xpath = "//div[contains(@id,'edit-timepoint-dialog')]//*[text()='Create Timepoint']")
	private WebElement createTimepointPopUp;

	@FindBy(xpath = "//div[contains(@id,'edit-timepoint-dialog')]//button[@class='close']")
	private WebElement closeIconOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[contains(@id,'edit-timepoint-dialog')]//input[contains(@id,'name-input')]")
	private WebElement nameFieldOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[contains(@id,'edit-timepoint-dialog')]//*[contains(@id,'comment-input')]")
	private WebElement commentFieldOnCreateTimepointPopUp;

	@FindBy(xpath = "//*[contains(text(),'Event Scheduling')]//following-sibling::div//input[@value='whenInitiated']")
	private WebElement whenInitiatedCheckbox;

	@FindBy(xpath = "//*[contains(text(),'Event Scheduling')]//following-sibling::div//input[@value='byEvent']")
	private WebElement byEventCheckbox;

	@FindBy(xpath = "//*[contains(text(),'Event Scheduling')]//following-sibling::div//input[@value='byTime']")
	private WebElement byTimeCheckbox;

	@FindBy(xpath = "//*[contains(text(),'Recurrence Pattern')]//following-sibling::div//input[@value='count']")
	private WebElement countCheckbox;

	@FindBy(xpath = "//*[contains(text(),'Recurrence Pattern')]//following-sibling::div//input[@value='minuteByMinute']")
	private WebElement minuteByMinuteCheckbox;

	@FindBy(xpath = "//*[contains(text(),'Recurrence Pattern')]//following-sibling::div//input[@value='hourly']")
	private WebElement hourlyCheckbox;

	@FindBy(xpath = "//*[contains(@id,'edit-timepoint-dialog')]//*[contains(@id,'ordered-enforced-checkbox')]")
	private WebElement enforceOrderFormCompletionsCheckboxOnCreateTimepointPopUp;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[contains(@class,'name-column')])[1]/label")
	private WebElement nameColumnOnCreateTimepointPopUp;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[contains(@class,'name-column')]/input)[1]")
	private WebElement scaleCheckboxOnCreateTimepointPopUp;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[contains(@class,'report')])[1]/span")
	private WebElement reportedOutcomeDropdownOnCreateTimepointPopUp;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[contains(@class,'recording')])[1]/span")
	private WebElement recordingTypeCheckboxOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//button[@class='btn btn-default']")
	private WebElement createAnotherOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//button[contains(@class,'save-button')]")
	private WebElement saveButtonOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//button[contains(@class,'btn-default btn')]")
	private WebElement cancelButtonOnCreateTimepointPopUp;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//input[contains(@class,'number-input')]")
	private WebElement entryFieldUnderByEvent;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//span[contains(@class,'time-units')]/span/span[1]")
	private WebElement timeDropdownUnderByEvent;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//span[contains(@class,'event-select')]/span/span)[1]")
	private WebElement eventDropdownUnderByEvent;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//span[contains(@class,'time-units-select')])[1]//span[@class='k-input ng-scope']")
	private WebElement timeDropdownUnderByTime;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//div[@class='ng-scope']//input[contains(@class,'number-input')]")
	private WebElement entryFieldUnderByTime;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[@class='ng-scope']//span[contains(@class,'k-input')])[2]")
	private WebElement timepointDropdownUnderByTime;

	@FindBy(xpath = "(//div[@id='create-or-edit-timepoint-dialog']//div[@class='control-group']//input[contains(@class,'ng-valid-pattern')])[2]")
	private WebElement occurrenceEntryFieldUnderCount;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//div[@class='control-group']//span[@class='light-text']")
	private WebElement patternSelectionDropdownUnderCount;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//input[contains(@class,'short-text-input')]")
	private WebElement patternEntryFieldUnderCount;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//label[text()='Every']//following-sibling::input")
	private WebElement timeEntryFieldUnderMinuteByMinute;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//label[text()='End After']//following-sibling::input")
	private WebElement occurrenceEntryFieldUnderMinuteByMinute;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//label[text()='Start Number']//following-sibling::span//span[@class='light-text']")
	private WebElement patternSelectionDropdownUnderMinuteByMinute;

	@FindBy(xpath = "//div[@id='create-or-edit-timepoint-dialog']//label[text()='Start Number']//following-sibling::input")
	private WebElement patternEntryFieldUnderMinuteByMinute;

	@FindBy(xpath = "//div[@class='k-animation-container']/div[contains(@class,'k-state-border-up')]//ul/li")
	private List<WebElement> timeList;

	@FindBy(xpath = "//div[@class='admin-tab-container']//button[contains(text(),'Calendar')]")
	private WebElement calendarTab;

	@FindBy(xpath = "//span[text()='Add Visit Template']//preceding::button[@title='Add Visit Template']/span")
	private WebElement addVisitTemplateIcon;

	@FindBy(xpath = "//span[text()='Add Questionnaire Template']//preceding::button[@title='Add Questionnaire Template']/span")
	private WebElement addQuestionnaireTemplateIcon;

	@FindBy(xpath = "//span[text()='Create Study Event']//preceding::button[@title='Create Study Event']/span")
	private WebElement createStudyEventIcon;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//*[contains(@class,'modal-title')]")
	private WebElement addCalenderVisitTemplate;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//label[contains(text(),'Name')]//following-sibling::input")
	private WebElement calenderVisitNameField;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//label[contains(text(),'Template')]//following-sibling::span/span")
	private WebElement templateDropdown;

	@FindBy(xpath = "//div[@class='k-animation-container']//ul[@id='template-dropdownlist_listbox']/li")
	private List<WebElement> templateDropdownList;

	@FindBy(xpath = "(//div[@id='create-or-edit-calendar-visit-template-dialog']//div[@class='form-row'])[1]/input")
	private WebElement dayEntryField;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//label[contains(text(),'Plus')]//following-sibling::input")
	private WebElement plusMinusEntryField;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//label[contains(text(),'After')]//following-sibling::span/span")
	private WebElement afterEventDropdownUnderByPreviousEvent;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//label[contains(text(),'End After')]//following-sibling::input")
	private WebElement occurrenceEntryField;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//button[contains(@class,'btn-active')]")
	private WebElement saveCalenderVisitTemplateButton;

	@FindBy(xpath = "//div[@id='create-or-edit-calendar-visit-template-dialog']//button[contains(@class,'default btn-without-icon')]")
	private WebElement cancelButtonOnCalenderVisit;

	@FindBy(xpath = "//div[@class='k-animation-container']/div[contains(@class,'k-state-border-up')]//ul[not(contains(@id,'templ'))]/li[1]")
	private WebElement afterEventDropDownOption;

	@FindBy(xpath = "//div[@id='calendar-grid']//div[contains(@class,'calendar-row')]//span[contains(@class,'label-with-tooltip')]")
	private List<WebElement> editIconOnCalenderVisit;

	@FindBy(xpath = "//div[@class='templates-container']//div[@class='template-header-main']//span[@class='text ng-binding']")
	private List<WebElement> addedVisitTemplate;

	@FindBy(xpath = "//div[@id='queryConfirmation']//*[@id='queryConfirmationLabel']")
	private WebElement confirmationPopUpTitle;

	@FindBy(xpath = "//div[@id='queryConfirmation']//label[contains(@class,'ng-binding ng')]")
	private WebElement confirmationPopUpContent;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[text()='Yes']")
	private WebElement yesButtonOnDeleteVisitPopUp;

	@FindBy(xpath = "//div[@id='system-message-container']//label[@class='error-message ng-binding']")
	private WebElement deleteVisitErrorMessage;

	@FindBy(xpath = "(//div[@id='system-message-container']//div[contains(@class,'close-button-')])[1]")
	private WebElement closeErrorMessageButton;

	@FindBy(xpath = "//div[@class='visit-template-buttons-container']//span[@class='action-btn']//button[@disabled='disabled']")
	private WebElement disabledButtonForAddVisitTemplate;

	@FindBy(xpath = "//div[@id='calendar-grid']")
	private WebElement calendargrid;

	@FindBy(xpath = "//div[@class='visit-template-buttons-container']")
	private WebElement addvisitTemplateControl;

	@FindBy(xpath = "//div[@class='templates-container']//div[@class='template-header']/div")
	private List<WebElement> visitTemplate;

	@FindBy(xpath="//h4[text()='Questionnaire Settings']")
    private WebElement questionnairepopupTitle;
    
    @FindBy(id="strictProtocolAdherence")
    private WebElement strictProtocoladgerencechkBx;
    
    @FindBy(xpath="//span[@class='k-widget k-dropdown k-header']")
    private WebElement folllowDropdown;
    
    @FindBy(xpath="//div[@id='edit-study-visit-calendar-settings-dialog']//button/span[text()='Save']")
    private WebElement settingpopupSaveBtn;
    
    @FindBy(xpath="//div[@id='edit-study-visit-calendar-settings-dialog']//button/span[text()='Cancel']")
    private WebElement settingpopupCancelBtn;
    
	public AdministrationStudiesSchedulePage(WebDriver driver) {
		super(driver);
	}

	/*
	 * @function: Verification of Schedule Page
	 *
	 */
	public void verifyAdministrationStudiesSchedulePage() {
		Assert.assertTrue(isElementPresent(calendarSchedule) && isElementPresent(visitTemplateSchedule));
		Assert.assertTrue(isElementPresent(questionnaireTemplatesSchedule) && isElementPresent(editIconSchedule));
	}

	/**
	 * =============================================================================================
	 * In this method, verify Questionnaire Template screen and assert assert create
	 * Template icon.
	 * =============================================================================================
	 */
	public void verifyQuestionaireTemplatesScreen() {
		waitAndClick(questionnaireTemplatesSchedule);
		waitForSpinnerBecomeInvisible(40);
		Assert.assertTrue(isElementPresent(questionnaireTemplatesSchedule));
		moveToElement(questionnaireTemplatesSchedule);
	}

	/**
	 * ==============================================================================================
	 * In this method, click on Create Questionnaire Template Link and assert Create
	 * Questionnaire Template pop up.
	 * ===============================================================================================
	 */
	public void clickonCreateQuestionnaireTemplateAndAssertPopUp() {
		waitAndClick(createQuestionnaireTempleteIcon);
		Assert.assertTrue(isElementPresent(createQuestionnaireTempletePopUp));
		moveToElement(createQuestionnaireTempletePopUp);
	}

	/**
	 * =================================================================================================
	 * In This method, verify save button is displayed as disabled on Create
	 * Questionnaire Template pop up.
	 * =================================================================================================
	 */
	public void verifySaveButtonDisplayedDisabled() {
		String saveButtonAttr = saveButton.getAttribute("disabled");
		Assert.assertTrue(saveButtonAttr.contains("true"));
		moveToElement(saveButton);
	}

	/**
	 * ===================================================================================================
	 * In this method, verify name field is displayed highlighted as required on
	 * Create Questionnaire Template pop up.
	 * ===================================================================================================
	 */
	public void verifyNameFieldDisplayedAsRequired() {
		Assert.assertTrue(nameField.getAttribute("required").contains("true"));
		moveToElement(nameField);
	}

	/**
	 * ====================================================================================================
	 * In this method, click on close button on pop up and assert Questionnaire
	 * template is not added.
	 * 
	 * @param temp ====================================================================================================
	 */
	public void clickOnCloseIconOnPopUpAndAssertTemplateNotAdded(String temp) {
		inputText(nameField, temp);
		clickOn(closeIcon);
		Assert.assertFalse(
				isElementPresent(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp + "')]")));
	}

	/**
	 * ========================================================================================================
	 * In this method, Fill name field and click on save button. Assert
	 * Questionnaire template added in the list.
	 * 
	 * @param temp ========================================================================================================
	 */
	public void fillNameFieldAndClickOnSaveButton(String temp) {
		inputText(nameField, temp);
		clickOn(saveButton);
		_normalWait(8000);
		spinnerBecomeInvisible();
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp + "')]"))
						.isDisplayed());
	}

	/**
	 * ==========================================================================================================
	 * In this method, Fill name field and click on Create Another button on Create
	 * Questionnaire Template pop up.
	 * 
	 * @param temp ==========================================================================================================
	 */
	public void fillNameFieldAndClickOnCreateAnotherButton(String temp) {
		inputText(nameField, temp);
		clickOn(createAnotherButton);
		this.verifyNameFieldDisplayedAsRequired();
		_normalWait(2000);
		this.verifySaveButtonDisplayedDisabled();
		clickOn(closeIcon);
		spinnerBecomeInvisible();
		_normalWait(3000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp + "')]"))
						.isDisplayed());

	}

	/**
	 * =============================================================================================
	 * In this method, Click on Create Event icon on added Questionnaire Template
	 * 
	 * @param temp =============================================================================================
	 */
	public void clickOnCreateNewEvent(String temp) {
		String eventButton = "//span[contains(@class,'text') and contains(text(),'" + temp
				+ "')]//parent::div//button[@title='Create Event']";
		Assert.assertTrue(isElementPresent(eventButton));
		clickOn(eventButton);
		waitForSpinnerBecomeInvisible(120);
	}

	public void removeQuestionnaireTemplate(String temp) {
		WebElement remove = driver.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp
				+ "')]//preceding::div[@class='template-header']//button[contains(@title,'Remove')]"));
		mouseHoverOnAnElement(remove);
		// javascriptButtonClick(remove);
		// waitAndClick(remove);
		spinnerBecomeInvisible();
	}

	/**
	 * ===================================================================================================
	 * In this method, verify Create Event pop up.
	 * ===================================================================================================
	 */
	public void verifyCreateEventPopUp() {
		waitForSpinnerBecomeInvisible(120);
		Assert.assertTrue(isElementPresent(createEventPopup));
		moveToElement(createEventPopup);
	}

	/**
	 * In this method, verify save button displayed disabled on create event pop up.
	 */
	public void verifySaveButtonDisabledOnCreateEventPopUp() {
		waitForSpinnerBecomeInvisible(100);
		String saveButtonAttr = saveEventButton.getAttribute("disabled");
		Assert.assertTrue(saveButtonAttr.contains("true"));
		moveToElement(saveEventButton);
	}

	/**
	 * In this method, click on close (x) icon on create event pop up.
	 */
	public void clickOnCloseIconOnCreateEventPopUp() {
		moveToElement(closeOnEventPopUp);
		clickOn(closeOnEventPopUp);
		_normalWait(10000);
	}

	/**
	 * In this method , verify name field and scale check box displayed required.
	 */
	public void verifyNameFieldAndScaleCheckboxesDisplayedHighlighted() {
		moveToElement(nameFieldOnCreateEventPopUp);
		Assert.assertTrue(nameFieldOnCreateEventPopUp.getAttribute("required").contains("true"));
		// this.verifyMobileEICheckBoxHighlighted();
	}

	/**
	 * In this method, verify mobile VP check-box is displayed highlighted.
	 */
	public void verifyMobileEICheckBoxHighlighted() {
		moveToElement(mobile_EI_Checkbox);
		Assert.assertTrue(mobile_EI_Checkbox.getAttribute("required").contains("true"));
	}

	/**
	 * In this method, verify q_mobile VP check-box is displayed highlighted.
	 */
	public void verifyQMobileVPCheckBoxHighlighted() {
		moveToElement(q_mobile_VP_Checkbox);
		Assert.assertTrue(q_mobile_VP_Checkbox.getAttribute("required").contains("true"));
	}

	/**
	 * In this method, verify new event not added on questionnaire Template.
	 * 
	 * @param temp
	 */
	public void verifyEventNotAddedInList(String temp) {
		WebElement template = driver
				.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp + "')]"));
		WebElement arrowIcon = driver.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp
				+ "')]//preceding::div[@class='collapse-column'][1]//span"));
		moveToElement(template);
		Assert.assertTrue(isElementNotVisible(arrowIcon));
	}

	/**
	 * Click on cancel button on create event pop up.
	 */
	public void clickOnCancelButtonOnCreateEventPopUp() {
		waitSpinnerToBecomeInvisible();
		waitAndClick(cancelEventButton);
	}

	/**
	 * In this method, fill name field on create event pop up.
	 * 
	 * @param even
	 */
	public void fillEventNameField(String even) {
		moveToElement(nameFieldOnCreateEventPopUp);
		inputText(nameFieldOnCreateEventPopUp, even);
	}

	/**
	 * In this method, select mobile VP check box on create event pop up.
	 */
	public void clickOnMobileEICheckBoxOnCreateEventPopUp() {
		this.verifyMobileEICheckBoxHighlighted();
		clickOn(mobile_EI_Checkbox);
		_normalWait(2000);
	}

	/**
	 * In this method, select q_mobile VP check box on create event pop up.
	 */
	public void clickOnQMobileVPCheckBoxOnCreateEventPopUp() {
		this.verifyQMobileVPCheckBoxHighlighted();
		clickOn(q_mobile_VP_Checkbox);
		_normalWait(2000);
	}

	/**
	 * In this method, click on save button on create event pop up.
	 */
	public void clickOnSaveButtonOnCreateEventPopUp() {
		// moveToElement(saveEventButton);
		waitAndClick(saveEventButton);
		_normalWait(10000);
	}

	/**
	 * In this method, select check boxes and click on save on create event pop up.
	 */
	public void selectCheckBoxAndClickOnSaveButtonOnCreateEventPopUp() {
		this.clickOnMobileEICheckBoxOnCreateEventPopUp();
		this.clickOnSaveButtonOnCreateEventPopUp();
		_normalWait(12000);
	}

	/**
	 * In this method click on expand icon on added questionnaire template list.
	 * 
	 * @param temp
	 */
	public void clickOnExpandIconOnQuestionnaireTemplate(String temp) {
		_normalWait(3000);
		WebElement arrowIcon = driver.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + temp
				+ "')]//preceding::div[@class='collapse-column'][1]//span"));
		// moveToElement(arrowIcon);
		Assert.assertTrue(arrowIcon.isDisplayed());
		waitAndClick(arrowIcon);
	}

	/**
	 * In this method, verify added event.
	 * 
	 * @param even
	 */
	public void verifyAddedEvent(String even) {
		_normalWait(2000);
		WebElement event = driver
				.findElement(By.xpath("//span[contains(@class,'text') and contains(text(),'" + even + "')]"));
		moveToElement(event);
	}

	/**
	 * In this method, click on create another button on create event pop up.
	 */
	public void clickOnCreateAnotherButton() {
		_normalWait(2000);
		waitAndClick(createAnotherEventButton);
		// moveToElement(createAnotherEventButton);
	}

	/**
	 * In this method, fill required fields under recurrence and schedule event
	 * section on create event pop up.
	 * 
	 * @param even
	 */
	public void fillRecurrenceAndScheduleFields(String even) {
		WebElement event = driver
				.findElement(By.xpath("//div[@class='k-list-scroller']//li[contains(text(),'" + even + "')]"));
		_normalWait(3000);
		moveToElement(scheduleTime);
		Assert.assertTrue(scheduleTime.getAttribute("required").contains("true"));
		moveToElement(recurInput);
		Assert.assertTrue(recurInput.getAttribute("required").contains("true"));
		inputText(scheduleTime, "30");
		// moveToElement(afterEventDropDown);
		waitAndClick(afterEventDropDown);
		moveToElement(event);
		clickOn(event);
		_normalWait(2000);
		inputText(recurInput, "1");
	}

	/**
	 * In this method, fill all required fields and click on create Another button
	 * on create event pop up.
	 * 
	 * @param even
	 */
	public void selectcheckboxAndClickOnCreateAnotherButton(String even) {
		this.clickOnMobileEICheckBoxOnCreateEventPopUp();
		this.fillRecurrenceAndScheduleFields(even);
		this.clickOnCreateAnotherButton();
		_normalWait(3000);
		this.verifyNameFieldAndScaleCheckboxesDisplayedHighlighted();
	}

	/**
	 * In this method, click on Visit Template tab and assert Create visit template
	 * and create visit template with time points icons.
	 */
	public void verifyVisitTemplateTab() {
		waitAndClick(visitTemplateSchedule);
		new WebDriverWait(driver, 180).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']//div[@class='spinner']")));
		this.verifyCreateVisitTemplateIcon();
		this.verifyCreateVisitTemplateWithTimepointsIcon();
	}

	/**
	 * In this method, verify create Visit Template icon.
	 */
	public void verifyCreateVisitTemplateIcon() {
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		Assert.assertTrue(createVisitTemplateIcon.isDisplayed());
		moveToElement(createVisitTemplateIcon);
	}

	/**
	 * In this method, verify create Visit Template with time points icons.
	 */
	public void verifyCreateVisitTemplateWithTimepointsIcon() {
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		Assert.assertTrue(createVisitTemplateWithTimepointsIcon.isDisplayed());
		moveToElement(createVisitTemplateWithTimepointsIcon);
	}

	/**
	 * In this method, verify added visit template with edit and delete icons.
	 * 
	 * @param visitTemp
	 */
	public void verifyAddedVisitTemplateInList(String visitTemp) {
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		WebElement visitTemplate = driver.findElement(
				By.xpath("//div[@class='templates-container']/div//span[contains(text(),'" + visitTemp + "')][2]"));
		Assert.assertTrue(visitTemplate.isDisplayed());
		moveToElement(visitTemplate);
		// mouseHoverOnAnElement(visitTemplate);
		moveToElement(visitTemplate
				.findElement(By.xpath(".//parent::div//parent::div//button[@title='Edit Visit Template']")));
		Assert.assertTrue(
				visitTemplate.findElement(By.xpath(".//parent::div//parent::div//button[@title='Edit Visit Template']"))
						.isDisplayed());

		moveToElement(visitTemplate
				.findElement(By.xpath(".//parent::div//parent::div//button[@title='Remove Visit Template']")));
		Assert.assertTrue(visitTemplate
				.findElement(By.xpath(".//parent::div//parent::div//button[@title='Remove Visit Template']"))
				.isDisplayed());
	}

	/**
	 * In this method, Click on create visit template icon.
	 */
	public void clickOnCreateVisitTemplateIcon() {
		// moveToElement(createVisitTemplateIcon);
		waitAndClick(createVisitTemplateIcon);
	}

	/**
	 * In this method, assert save button on create visit template pop up.
	 */
	public void verifySaveButtonOncreateVisitTemplatePopUp(boolean status) {
		moveToElement(visitTemplateSaveButton);
		if (status) {
			Assert.assertTrue(visitTemplateSaveButton.getAttribute("disabled").contentEquals("true"));
		} else {
			Assert.assertTrue(visitTemplateSaveButton.isEnabled());
		}
	}

	/**
	 * In this method, verify create visit template pop up.
	 */
	public void verifyCreateVisitTemplateModelTitle() {
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		moveToElement(createVisitTemplatePopUp);
		Assert.assertTrue(createVisitTemplatePopUp.isDisplayed());
	}

	/**
	 * In this method, verify close icon on create visit template pop up.
	 */
	public void verifyCloseIconOnCreateVisitTemplatePopUp() {
		moveToElement(closeIconOnCreateVisitTemplatePopUp);
		Assert.assertTrue(closeIconOnCreateVisitTemplatePopUp.isDisplayed());
	}

	/**
	 * In this method, verify name field is displayed as required on create visit
	 * template pop up.
	 */
	public void verifyNameFieldOnCreateVisitTemplatePopUpDisplayedRequired() {
		moveToElement(visitTemplateNameField);
		Assert.assertTrue(visitTemplateNameField.getAttribute("required").contains("true"));
	}

	/**
	 * In this method, verify comment field on create visit template pop up.
	 */
	public void verifyCommentFieldOnCreateVisitTemplatePopUp() {
		moveToElement(visitTemplateNameField);
		Assert.assertTrue(visitTemplateCommentField.isDisplayed());
	}

	/**
	 * In this method, verify analytics types is displayed required on create visit
	 * template pop up.
	 */
	public void verifyAnalyticsTypeOnCreateVisitTemplatePopUpDisplayedRequired() {
		moveToElement(visitAnalyticalType);
		String color = visitAnalyticalType.findElement(By.xpath("./span")).getCssValue("background");
		Assert.assertTrue(color.contains("rgb(255, 217, 217)"));
	}

	/**
	 * In this method, verify Can add attachments check box on create visit template
	 * pop up.
	 */
	public void verifyCanAddAttachmentsCheckboxOnCreateVisitTemplatePopUp() {
		moveToElement(canAddAttachmentsCheckbox);
		Assert.assertTrue(canAddAttachmentsCheckbox.isEnabled());
	}

	/**
	 * In this method, verify Can be unscheduled check box on create visit template
	 * pop up.
	 */
	public void verifyCanbeUnscheduledCheckboxOnCreateVisitTemplatePopUp() {
		moveToElement(canbeUnscheduledCheckbox);
		Assert.assertTrue(canbeUnscheduledCheckbox.isEnabled());
	}

	/**
	 * In this method, verify enforce order forms completion check box on create
	 * visit template pop up.
	 */
	public void verifyEnforceOrderFormsCompletionCheckboxOnCreateVisitTemplatePopUp() {
		moveToElement(enforceOrderFormsCompletionCheckbox);
		Assert.assertFalse(enforceOrderFormsCompletionCheckbox.isEnabled());
	}

	/**
	 * In this method, verify subject status drop down on create visit template pop
	 * up.
	 */
	public void verifySubjectStatusDropDownOnCreateVisitTemplatePopUp() {
		moveToElement(SubjectStatusUnscheduledDropDown);
		Assert.assertTrue(SubjectStatusUnscheduledDropDown.isDisplayed());
	}

	/**
	 * In this method, verify scale name under scale table on create visit template
	 * pop up.
	 */
	public void verifyNameColumnOnCreateVisitTemplatePopUp() {
		moveToElement(nameColumn);
		Assert.assertTrue(nameColumn.isDisplayed());
	}

	/**
	 * In this method, verify reported outcome drop down on create visit template
	 * pop up.
	 */
	public void verifyReportedOutcomeDropDownOnCreateVisitTemplatePopUp() {
		moveToElement(reportedOutcome);
		Assert.assertTrue(reportedOutcome.isDisplayed());
	}

	/**
	 * In this method, verify recording type drop down on create visit template pop
	 * up.
	 */
	public void verifyRecordingTypeDropDownOnCreateVisitTemplatePopUp() {
		moveToElement(recordingType);
		Assert.assertTrue(recordingType.isDisplayed());
	}

	/**
	 * In this method, verify create another button is displayed as disabled on
	 * create visit template pop up.
	 */
	public void verifyCreateAnotherButtonOnCreateVisitTemplatePopUp() {
		moveToElement(CreateAnothervisitTemplateButton);
		Assert.assertTrue(CreateAnothervisitTemplateButton.getAttribute("disabled").contains("true"));
	}

	/**
	 * In this method, verify cancel button on create visit template pop up.
	 */
	public void verifyCancelButtonOnCreateVisitTemplatePopUp() {
		moveToElement(visitTemplateCancelButton);
		Assert.assertTrue(visitTemplateCancelButton.isEnabled());
	}

	/**
	 * verify all Fields, Check-box, Drop-down And Buttons on Create Visit Template
	 * PopUp.
	 * 
	 * @param status
	 */
	public void verifyAllFieldsCheckboxDropdownAndButtonOnCreateVisitTemplatePopUp(boolean status) {
		this.verifyCreateVisitTemplateModelTitle();
		this.verifyCloseIconOnCreateVisitTemplatePopUp();
		this.verifyNameFieldOnCreateVisitTemplatePopUpDisplayedRequired();
		this.verifyCommentFieldOnCreateVisitTemplatePopUp();
		this.verifyAnalyticsTypeOnCreateVisitTemplatePopUpDisplayedRequired();
		this.verifyCanAddAttachmentsCheckboxOnCreateVisitTemplatePopUp();
		this.verifyCanbeUnscheduledCheckboxOnCreateVisitTemplatePopUp();
		this.verifyEnforceOrderFormsCompletionCheckboxOnCreateVisitTemplatePopUp();
		this.verifySubjectStatusDropDownOnCreateVisitTemplatePopUp();
		this.verifyNameColumnOnCreateVisitTemplatePopUp();
		this.verifyReportedOutcomeDropDownOnCreateVisitTemplatePopUp();
		this.verifyRecordingTypeDropDownOnCreateVisitTemplatePopUp();
		this.verifyCreateAnotherButtonOnCreateVisitTemplatePopUp();
		this.verifySaveButtonOncreateVisitTemplatePopUp(status);
		this.verifyCancelButtonOnCreateVisitTemplatePopUp();
	}

	/**
	 * In this method, enter visit name on create visit template pop up.
	 * 
	 * @param tempName
	 */
	public void inputVisitTemplateName(String tempName) {
		inputText(visitTemplateNameField, tempName);
	}

	/**
	 * In this method select analytical type drop down on create visit template pop
	 * up.
	 */
	public void selectAnalyticalTypeFromDropDown() {
		clickOn(visitAnalyticalType);
		waitAndClick(analyticalTypeDropDown);
		// moveToElement(analyticalTypeDropDown);
	}

	/**
	 * In this method, select Can add attachments check box on create visit template
	 * pop up.
	 */
	public void selectCanAddAttachmentsCheckbox() {
		waitAndClick(canAddAttachmentsCheckbox);
		// moveToElement(canAddAttachmentsCheckbox);
	}

	/**
	 * In this method, select Can be unscheduled check box on create visit template
	 * pop up.
	 */
	public void selectCanbeUnscheduledCheckbox() {
		waitAndClick(canbeUnscheduledCheckbox);
		// moveToElement(canbeUnscheduledCheckbox);
	}

	/**
	 * In this method, select scale check box, select reported outcome and recording
	 * type drop-down on create visit template pop up.
	 */
	public void selectScaleUnderScaleTable() {
		waitAndClick(scaleCheckbox);
		// moveToElement(scaleCheckbox);

		waitAndClick(reportedOutcome);
		clickOn(selectReportedOutcomeOption);

		waitAndClick(recordingType);
		clickOn(recordingTypeOption);
		// moveToElement(recordingType);
	}

	public void fillAllFieldsWithValidDetailsOnCreateVisitTemplatePopUp(String tempName) {
		this.inputVisitTemplateName(tempName);
		this.selectAnalyticalTypeFromDropDown();
		this.selectCanAddAttachmentsCheckbox();
		this.selectCanbeUnscheduledCheckbox();
		this.selectScaleUnderScaleTable();
	}

	/**
	 * In this method, click on create another button on create visit template pop
	 * up.
	 */
	public void clickOnCreateAnotherButtonOnCreateVisitTemplatePopUp() {
		waitAndClick(CreateAnothervisitTemplateButton);
		// moveToElement(CreateAnothervisitTemplateButton);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * In this method, click on save button on create visit template pop up.
	 */
	public void clickOnSaveButtonOnCreateVisitTemplatePopUp() {
		moveToElement(visitTemplateSaveButton);
		clickOn(visitTemplateSaveButton);
		new WebDriverWait(driver, 180).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * In this method, verify added visit template in visit template table.
	 * 
	 * @param temp
	 */
	public void verifyAddedVisittemplatesUnderVisitTemplateTable(String temp) {
		WebElement el = driver.findElement(By.xpath(
				"//div[starts-with(@class,'template-row')]//span[not(contains(@class,'ng-hide')) and contains(text(),'"
						+ temp + "')]"));
		moveToElement(el);
		Assert.assertTrue(el.isDisplayed());
	}

	public void verifyVisitTemplateRemovedAfterDeleteFromList(String temp) {
//		WebElement el = driver.findElement(By.xpath(
//				"//div[starts-with(@class,'template-row')]//span[not(contains(@class,'ng-hide')) and contains(text(),'"
//						+ temp + "')]"));
//		Assert.assertFalse(isElementPresent(el));
		boolean flag = false;
		for (WebElement webElement : addedVisitTemplate) {
			if (webElement.getText().equalsIgnoreCase(temp)) {
				flag = false;
				break;
			} else {
				flag = true;
			}

		}
		Assert.assertTrue(flag);
	}

	/**
	 * In this method, click on create visit template with time points icon.
	 */
	public void clickOnCreateVisitTemplateWithTimepointsIcon() {
		moveToElement(createVisitTemplateWithTimepointsIcon);
		clickOn(createVisitTemplateWithTimepointsIcon);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * In this method, verify all fields, buttons and drop-down on create visit
	 * template with time points pop up
	 */
	public void verifyAllFieldsButtonsAndDropdownOnCreateVisitTemplateWithTimepointsPopUp() {
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		Assert.assertTrue(createVisittemplateWithTimepointsPopUp.isDisplayed());
		moveToElement(createVisittemplateWithTimepointsPopUp);
		Assert.assertTrue(closeButtonOnCreateVisittemplateWithTimepointsPopUp.isDisplayed());
		moveToElement(closeButtonOnCreateVisittemplateWithTimepointsPopUp);
		Assert.assertTrue(nameFieldOnVisittemplateWithTimepointsPopUp.getAttribute("required").contains("true"));
		moveToElement(nameFieldOnVisittemplateWithTimepointsPopUp);
		Assert.assertTrue(commentFieldOnVisittemplateWithTimepointsPopUp.isDisplayed());
		moveToElement(commentFieldOnVisittemplateWithTimepointsPopUp);
		Assert.assertTrue(addAttachmentsCheckbox.isDisplayed());
		moveToElement(addAttachmentsCheckbox);
		Assert.assertTrue(canbeUnscheduled.isDisplayed());
		moveToElement(canbeUnscheduled);
		Assert.assertTrue(subjectStatusWhenUnscheduled.isDisplayed());
		Assert.assertTrue(createAnotherOnVisitTemplateWithTimepoints.getAttribute("disabled").contains("true"));
		moveToElement(createAnotherOnVisitTemplateWithTimepoints);
		Assert.assertTrue(saveButtonOnVisitTemplateWithTimepoints.isDisplayed());
		moveToElement(saveButtonOnVisitTemplateWithTimepoints);
		Assert.assertTrue(cancelButtonOnVisitTemplateWithTimepoints.isDisplayed());
		moveToElement(cancelButtonOnVisitTemplateWithTimepoints);
	}

	/**
	 * In this method, input name in name field on create visit template with time
	 * points pop up.
	 * 
	 * @param name
	 */
	public void inputNameInCreateVisitTemplateWithTimepointsPopUp(String name) {
		inputText(nameFieldOnVisittemplateWithTimepointsPopUp, name);
	}

	/**
	 * Click on create another button on visit template with time points pop up.
	 */
	public void clickOnCreateAnotherButtonOnVisitTemplateWithTimepointsPopUp() {
		waitAndClick(createAnotherOnVisitTemplateWithTimepoints);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * click on save button on visit template with time points pop up.
	 */
	public void clickOnSaveButtonOnVisitTemplateWithTimepointsPopUp() {
		waitAndClick(saveButtonOnVisitTemplateWithTimepoints);
		new WebDriverWait(driver, 80).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * Click on Time point icon.
	 * 
	 * @param visitTemp
	 */
	public void clickOnCreateTimePointIcon(String visitTemp) {
		WebElement visitTemplate = driver
				.findElement(By.xpath("//div[@class='templates-container']/div//span[contains(text(),'" + visitTemp
						+ "')][2]//parent::div//parent::div//button/span[contains(@class,'icon-add')]"));
		// moveToElement(visitTemplate);
		waitAndClick(visitTemplate);
		// _normalWait(8000);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * In this method, verify all items displayed on create time points pop up.
	 */
	public void verifyAllItemsOnCreateTimepointsPopUp() {
		moveToElement(createTimepointPopUp);
		Assert.assertTrue(createTimepointPopUp.isDisplayed());
		Assert.assertTrue(closeIconOnCreateTimepointPopUp.isDisplayed());
		Assert.assertTrue(nameFieldOnCreateTimepointPopUp.getAttribute("required").contains("true"));
		Assert.assertTrue(commentFieldOnCreateTimepointPopUp.isDisplayed());
		this.verifyAllOptionsUnderEventSchedulingBlock();
		this.verifyAllOptionsUnderRecurrencePatternBlock();
		Assert.assertFalse(enforceOrderFormCompletionsCheckboxOnCreateTimepointPopUp.isEnabled());
		moveToElement(nameColumnOnCreateTimepointPopUp);
		Assert.assertTrue(nameColumnOnCreateTimepointPopUp.isDisplayed());
		moveToElement(reportedOutcomeDropdownOnCreateTimepointPopUp);
		Assert.assertTrue(reportedOutcomeDropdownOnCreateTimepointPopUp.isDisplayed());
		moveToElement(recordingTypeCheckboxOnCreateTimepointPopUp);
		Assert.assertTrue(recordingTypeCheckboxOnCreateTimepointPopUp.isDisplayed());
		moveToElement(createAnotherOnCreateTimepointPopUp);
		Assert.assertTrue(createAnotherOnCreateTimepointPopUp.getAttribute("disabled").contains("true"));
		moveToElement(saveButtonOnCreateTimepointPopUp);
		Assert.assertTrue(saveButtonOnCreateTimepointPopUp.getAttribute("disabled").contains("true"));
		moveToElement(cancelButtonOnCreateTimepointPopUp);
		Assert.assertTrue(cancelButtonOnCreateTimepointPopUp.isEnabled());
	}

	/**
	 * In this method, verify all options under event scheduling block on create
	 * time points pop up.
	 */
	public void verifyAllOptionsUnderEventSchedulingBlock() {
		moveToElement(whenInitiatedCheckbox);
		if (whenInitiatedCheckbox.isEnabled()) {
			Assert.assertTrue(byTimeCheckbox.isEnabled());
		} else {
			Assert.assertTrue(whenInitiatedCheckbox.getAttribute("disabled").contains("true"));
			Assert.assertTrue(byEventCheckbox.isEnabled());
			Assert.assertTrue(byTimeCheckbox.isEnabled());
		}
	}

	/**
	 * In this method, verify all options under Recurrence Pattern block on create
	 * time points pop up.
	 */
	public void verifyAllOptionsUnderRecurrencePatternBlock() {
		if (whenInitiatedCheckbox.isEnabled()) {
			Assert.assertTrue(countCheckbox.isEnabled());
		} else {
			Assert.assertTrue(countCheckbox.isEnabled());
			Assert.assertTrue(minuteByMinuteCheckbox.isEnabled());
			Assert.assertTrue(hourlyCheckbox.isEnabled());
		}
	}

	/**
	 * In this method, verify all configuration under by event option.
	 */
	public void verifyAllConfigurationsUnderByEventOption() {
		Assert.assertTrue(entryFieldUnderByEvent.getAttribute("required").contains("true"));
		Assert.assertTrue(getText(timeDropdownUnderByEvent).contains("Minute"));
		Assert.assertTrue(eventDropdownUnderByEvent.isDisplayed());
	}

	/**
	 * Click on by event check box at create time points pop up.
	 */
	public void clickOnByEventCheckbox() {
		if (byEventCheckbox.isEnabled()) {
			doubleClickOnElement(byEventCheckbox);
		}
	}

	/**
	 * Click on by time check box at create time points pop up.
	 */
	public void clickOnByTimeCheckbox() {
		doubleClickOnElement(byTimeCheckbox);
		// waitAndClick(byTimeCheckbox);
	}

	/**
	 * Verify time point drop down and entry field under by time option.
	 */
	public void verifyTimepointDropdownAndEntryFieldUnderByTimeOption() {
		Assert.assertTrue(entryFieldUnderByTime.getAttribute("required").contains("true"));
		Assert.assertTrue(getText(timepointDropdownUnderByTime).contains("Minute"));
	}

	/**
	 * Verify time difference in time point drop down under by time option.
	 * 
	 * @param list
	 */
	public void verifyAllTimeDifference(List<String> list) {

		try {
			List<String> al = new ArrayList<>();
			waitAndClick(timeDropdownUnderByTime);
			int len = timeList.size();
			// System.out.println("Li length" + len);
			_normalWait(2000);
			for (WebElement time : timeList) {
				String ti = getText(time).trim();
				al.add(ti);
			}
			Assert.assertEquals(al, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * In this method, verify all configuration under count option on create time
	 * points pop up.
	 */
	public void verifyAllConfigurationUnderCountOption() {
		moveToElement(occurrenceEntryFieldUnderCount);
		Assert.assertTrue(occurrenceEntryFieldUnderCount.getAttribute("required").contains("true"));
		moveToElement(patternSelectionDropdownUnderCount);
		Assert.assertTrue(patternSelectionDropdownUnderCount.isDisplayed());
		moveToElement(patternEntryFieldUnderCount);
		Assert.assertTrue(patternEntryFieldUnderCount.isDisplayed());
	}

	/**
	 * Click on minute by minute check box on create time points pop up.
	 */
	public void clickOnMinuteByMinuteCheckbox() {
		doubleClickOnElement(minuteByMinuteCheckbox);
	}

	/**
	 * Click on hourly check box on create time points pop up.
	 */
	public void clickOnHourlyCheckbox() {
		doubleClickOnElement(hourlyCheckbox);
	}

	/**
	 * Click on count check box on create time points pop up.
	 */
	public void clickOnCountCheckbox() {
		doubleClickOnElement(countCheckbox);
	}

	/**
	 * In this method, verify all configurations under minute by minute or hourly
	 * options
	 */
	public void verifyAllConfigurationUnderMinuteByMinuteOrHourlyOption() {
		moveToElement(timeEntryFieldUnderMinuteByMinute);
		Assert.assertTrue(timeEntryFieldUnderMinuteByMinute.getAttribute("required").contains("true"));
		moveToElement(occurrenceEntryFieldUnderMinuteByMinute);
		Assert.assertTrue(occurrenceEntryFieldUnderMinuteByMinute.getAttribute("required").contains("true"));
		moveToElement(patternSelectionDropdownUnderMinuteByMinute);
		Assert.assertTrue(patternSelectionDropdownUnderMinuteByMinute.isDisplayed());
		moveToElement(patternEntryFieldUnderMinuteByMinute);
		Assert.assertTrue(patternEntryFieldUnderMinuteByMinute.isDisplayed());
	}

	/**
	 * In this method, input time points name.
	 * 
	 * @param timepoint
	 */
	public void inputTimePointName(String timepoint) {
		moveToElement(nameFieldOnCreateTimepointPopUp);
		inputText(nameFieldOnCreateTimepointPopUp, timepoint);
	}

	/**
	 * Input text in entry field under by event option.
	 */
	public void inputTextInEntryFieldUnderByEvent() {
		moveToElement(entryFieldUnderByEvent);
		inputText(entryFieldUnderByEvent, "2");
	}

	/**
	 * In this method, input text in Occurrence Entry Field Under Count option.
	 */
	public void inputTextInOccurrenceEntryFieldUnderCount() {
		moveToElement(occurrenceEntryFieldUnderCount);

		inputText(occurrenceEntryFieldUnderCount, "1");
	}

	/**
	 * In this method, fill all fields with valid details on create time point pop
	 * up.
	 * 
	 * @param timepoint
	 */
	public void fillAllFieldWithValidDetailsInCreateTimePointPopUp(String timepoint) {
		this.clickOnByEventCheckbox();
		this.clickOnCountCheckbox();
		this.inputTimePointName(timepoint);
		if (byEventCheckbox.isEnabled() == true) {
			this.inputTextInEntryFieldUnderByEvent();
			this.inputTextInOccurrenceEntryFieldUnderCount();
		}
		this.selectScaleCheckbox();
		this.selectReportedOutcomeOptionOncreateTimePointPopUp();
		this.selectRecordingTypeDropDownOncreateTimePointPopUp();
	}

	public void enterValidDetailsInCreateTimePointPopUp(String timepoint) {
		this.clickOnByEventCheckbox();
		this.clickOnCountCheckbox();
		this.inputTimePointName(timepoint);
		this.inputTextInEntryFieldUnderByEvent();
		this.inputTextInOccurrenceEntryFieldUnderCount();
		this.selectScaleCheckbox();
		this.selectReportedOutcomeOptionOncreateTimePointPopUp();
		this.selectRecordingTypeDropDownOncreateTimePointPopUp();
	}

	/**
	 * In this method, select scale check box on create time point pop up.
	 */
	public void selectScaleCheckbox() {
		// moveToElement(scaleCheckboxOnCreateTimepointPopUp);
		waitAndClick(scaleCheckboxOnCreateTimepointPopUp);
	}

	/**
	 * In this method, select reported outcome drop down on create time point pop
	 * up.
	 */
	public void selectReportedOutcomeOptionOncreateTimePointPopUp() {
		// moveToElement(reportedOutcomeDropdownOnCreateTimepointPopUp);
		waitAndClick(reportedOutcomeDropdownOnCreateTimepointPopUp);
		clickOn(selectReportedOutcomeOption);
	}

	/**
	 * In this method, select recording type drop down on create time point pop up.
	 */
	public void selectRecordingTypeDropDownOncreateTimePointPopUp() {

		// moveToElement(recordingTypeCheckboxOnCreateTimepointPopUp);
		waitAndClick(recordingTypeCheckboxOnCreateTimepointPopUp);
		clickOn(recordingTypeOption);
	}

	/**
	 * In this method, click on create another button on create time point pop up.
	 */
	public void clickOnCreateAnotherTimePointButton() {
		// moveToElement(createAnotherOnCreateTimepointPopUp);
		waitAndClick(createAnotherOnCreateTimepointPopUp);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * In this method, click on save button on create time points pop up.
	 */
	public void saveButtonOnCreateTimePointPopUp() {
		waitAndClick(saveButtonOnCreateTimepointPopUp);
		new WebDriverWait(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));
	}

	/**
	 * verify all added time point template.
	 * 
	 * @param visitTemp, @param tPoint
	 */
	public void verifyAllAddedTimePointTemplate(String visitTemp, String tPoint) {
		WebElement visitTemplate = driver
				.findElement(By.xpath("//div[@class='templates-container']/div//span[contains(text(),'" + visitTemp
						+ "')][2]//parent::div//parent::div"));
		// moveToElement(visitTemplate.findElement(By.xpath(".//a/span")));
		waitAndClick(visitTemplate.findElement(By.xpath(".//a/span")));
		moveToElement(visitTemplate.findElement(By.xpath(
				".//parent::div//following-sibling::div/div[contains(@class,'timepoint-block ng-scope')]//span[contains(text(),'"
						+ tPoint + "')][2]")));
		Assert.assertTrue(visitTemplate.findElement(By.xpath(
				".//parent::div//following-sibling::div/div[contains(@class,'timepoint-block ng-scope')]//span[contains(text(),'"
						+ tPoint + "')][2]"))
				.isDisplayed());
		waitAndClick(visitTemplate.findElement(By.xpath(
				".//parent::div//parent::div//following-sibling::div/div[contains(@class,'timepoint-block ng-scope')]//span[contains(text(),'"+tPoint+"')][2]//parent::div//following-sibling::span//span[@class='icon-small icon-delete']")));
		this.verifyVisitDeleteConfirmationPopUp();
		this.clickOnYesButtonOnDeleteVisitConfirmationPopUp();
		waitForSpinnerBecomeInvisible(20);
	}

	public void verifyAllOptionsUnderCalendarTabInSchedule() {
		waitAndClick(calendarTab);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		Assert.assertTrue(addVisitTemplateIcon.isDisplayed());
		Assert.assertTrue(addQuestionnaireTemplateIcon.isDisplayed());
		Assert.assertTrue(createStudyEventIcon.isDisplayed());
	}

	public void clickOnAddVisitTemplateIcon() {
		// moveToElement(addVisitTemplateIcon);
		waitAndClick(addVisitTemplateIcon);
		new WebDriverWait(driver, 120).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	public void verifyAllFieldsDropdownAndButtonOnAddCalenderVisitTemplatePopUp() {
		moveToElement(addCalenderVisitTemplate);
		Assert.assertTrue(addCalenderVisitTemplate.isDisplayed());

		moveToElement(calenderVisitNameField);
		Assert.assertTrue(calenderVisitNameField.getAttribute("required").contains("true"));

		moveToElement(templateDropdown);
		Assert.assertTrue(templateDropdown.isDisplayed());

		moveToElement(dayEntryField);
		Assert.assertTrue(dayEntryField.getAttribute("required").contains("true"));

		moveToElement(plusMinusEntryField);
		Assert.assertTrue(plusMinusEntryField.getAttribute("required").contains("true"));

		moveToElement(afterEventDropdownUnderByPreviousEvent);
		Assert.assertTrue(afterEventDropdownUnderByPreviousEvent.isDisplayed());

		moveToElement(occurrenceEntryField);
		Assert.assertTrue(occurrenceEntryField.getAttribute("required").contains("true"));

		moveToElement(saveCalenderVisitTemplateButton);
		Assert.assertTrue(saveCalenderVisitTemplateButton.getAttribute("disabled").contains("true"));
	}

	public void fillAllFieldsAndDropdownOnCalenderVisitPopUp(String name, String visit) {
		this.inputCalanderVisitName(name);
		this.selectTemplateDropDown(visit);
		this.inputDayEntryField();
		this.inputPlusMinusEntryField();
		this.selectAfterEventDropDown();
		this.inputOccurrenceEntryField();
	}

	public void inputCalanderVisitName(String name) {
		inputText(calenderVisitNameField, name);
	}

	public void selectTemplateDropDown(String visit) {
		waitAndClick(templateDropdown);
		for (WebElement webElement : templateDropdownList) {
			if (webElement.getText().trim().equalsIgnoreCase(visit)) {
				moveToElement(webElement);
				clickOn(webElement);
				break;
			}
		}
	}

	public void inputDayEntryField() {
		inputText(dayEntryField, "1");
	}

	public void inputPlusMinusEntryField() {
		inputText(plusMinusEntryField, "1");
	}

	public void selectAfterEventDropDown() {
		waitAndClick(afterEventDropdownUnderByPreviousEvent);
		// moveToElement(afterEventDropDownOption);
		waitAndClick(afterEventDropDownOption);
	}

	public void inputOccurrenceEntryField() {
		inputText(occurrenceEntryField, "1");
	}

	public void clickOnSaveCalenderVisitTemplateButton() {
		waitAndClick(saveCalenderVisitTemplateButton);
		new WebDriverWait(driver, 80).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	public void clickOnEditButtonOnCalenderVisit(String visit) {
		for (WebElement webElement : editIconOnCalenderVisit) {
			if (webElement.getText().trim().equalsIgnoreCase(visit)) {
				moveToElement(webElement);
				waitAndClick(webElement.findElement(By.xpath(
						".//parent::div//parent::div[@class='row-cell calendar-event-cell']//following-sibling::div[@class='row-cell buttons-cell']//button[@title='Edit']")));
				break;
			}
		}
		new WebDriverWait(driver, 80).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
	}

	public void verifyEditCalenderVisitTemplatePopUp(String temp) {
		moveToElement(addCalenderVisitTemplate);
		Assert.assertTrue(getText(addCalenderVisitTemplate).contains("Edit Visit Template"));

		moveToElement(templateDropdown);
		Assert.assertTrue(
				getText(templateDropdown.findElement(By.xpath("./span[@class='k-input ng-scope']"))).contains(temp));

		waitAndClick(cancelButtonOnCalenderVisit);
	}

	public void clickOnRemoveVisitTemplate(String visit) {
		for (WebElement webElement : addedVisitTemplate) {
			if (webElement.getText().trim().equalsIgnoreCase(visit)) {
				moveToElement(webElement
						.findElement(By.xpath(".//parent::div//parent::div//button[@title='Remove Visit Template']")));
				waitAndClick(webElement
						.findElement(By.xpath(".//parent::div//parent::div//button[@title='Remove Visit Template']")));
				break;
			}
		}
	}

	public void verifyVisitDeleteConfirmationPopUp() {
		moveToElement(confirmationPopUpTitle);
		Assert.assertTrue(confirmationPopUpTitle.isDisplayed());

		moveToElement(confirmationPopUpContent);
//		Assert.assertTrue(
//				confirmationPopUpContent.getText().contains("Are you sure you want to delete this Visit Template"));
	}

	public void clickOnYesButtonOnDeleteVisitConfirmationPopUp() {
		waitForElementPresent(yesButtonOnDeleteVisitPopUp, 30);
		waitAndClick(yesButtonOnDeleteVisitPopUp);
		_normalWait(2000);
//		new WebDriverWait(driver, 50).until(ExpectedConditions.invisibilityOfElementLocated(
//				By.xpath("//*[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']//div[@class='spinner']")));
	}

	public void verifyDeleteVisitErrorMessageAndCloseMessage() {
		waitForElementPresent(deleteVisitErrorMessage, 30);
		Assert.assertTrue(
				deleteVisitErrorMessage.getText().contains("Cannot delete a visit template as it is being used"));

		waitAndClick(closeErrorMessageButton);
	}

	public void clickOnDeleteIconOnCalendarVisit(String visitTemp) {
		WebElement calvisitTemplate = driver.findElement(By.xpath(
				"(//div[@id='calendar-grid']//div[contains(@class,'calendar-event-cell')]//span[contains(@class,'ng-hide') and contains(text(),'"
						+ visitTemp
						+ "')])[2]//parent::div//following::div[@class='row-cell buttons-cell']//button[@title='Delete']/span"));
		// moveToElement(calvisitTemplate);
		waitAndClick(calvisitTemplate);
	}

	public void clickOnArrowButtonOnAddedCalendarTemplate(String calTemp) {
		WebElement arrowIcon = driver.findElement(By.xpath(
				"(//div[@id='calendar-grid']//div[contains(@class,'calendar-event-cell')]//span[contains(@class,'ng-hide') and contains(text(),'"
						+ calTemp
						+ "')]//parent::div//parent::div//parent::div)[1]/div[@class='row-cell collapse-btn-cell']//span"));
		// moveToElement(arrowIcon);
		waitAndClick(arrowIcon);
	}

	/***
	 * Verify schedule page is in view mode
	 */
	public void verifySchedulePageInViewMode() {

		String icon = disabledButtonForAddVisitTemplate.getAttribute("disabled");
		if (icon.contains("disabled")) {
			Assert.assertTrue(true);
		}
	}

	public void verifyCalendarScreenDisplayed() {
		spinnerBecomeInvisible();
		moveToElement(calendargrid);
		moveToElement(addvisitTemplateControl);
		Assert.assertTrue(isElementPresent(calendargrid) && isElementPresent(addvisitTemplateControl));
		reportInfo();
	}

	public void deleteAddedVisitTemplate(String temp) {
		WebElement el = driver.findElement(By.xpath(
				"//div[starts-with(@class,'template-row')]//span[not(contains(@class,'ng-hide')) and contains(text(),'"
						+ temp + "')]"));
			System.out.println(getText(el));
			moveToElement(el);
			waitAndClick(el.findElement(By.xpath(".//parent::div//following-sibling::span//button/span[@class='icon-small icon-delete']")));
				this.verifyVisitDeleteConfirmationPopUp();
				this.clickOnYesButtonOnDeleteVisitConfirmationPopUp();
				waitForSpinnerBecomeInvisible(20);
	}

	public void deleteVisit(String visit1, String visit2, String vWithTimePoint1, String vWithTimePoint2) {
		this.deleteAddedVisitTemplate(visit1);
		this.deleteAddedVisitTemplate(visit2);
		this.deleteAddedVisitTemplate(vWithTimePoint1);
		this.deleteAddedVisitTemplate(vWithTimePoint2);
	}
	
	/**
	 *  click to open configuration of any Particular event 
	 * @param eventName
	 * @param form
	 */
     public void clickToOpenSettingOfEvent(String eventName,String form)
    {
	     String eventTobeopen = "//span[contains(text(),'"+eventName+"')]/ancestor::div[2]/preceding-sibling::div//a";
	     WebElement clickCollapseEvent = driver.findElement(ByLocator(eventTobeopen));
	     clickOn(clickCollapseEvent);
	     String formTobeselect ="//span[contains(text(),'"+form+"')]/ancestor::div[2]/following::div[4]"
	     		+ "//button[@title='Questionnaire Settings']";
	     WebElement formstobeconfigured = driver.findElement(ByLocator(formTobeselect));
	     clickOn(formstobeconfigured);
	     waitForElementToBecomeInvisible(ByLocator("//div[@class='modal-dialog dialog-md tall-dialog']/div[@class='ng-isolate-scope']"
	     		+ "//div[@class='spinner']"));
		
	}
 
 
     public void selectEventDropArrow(String eventName)
     {
    	 String eventTobeopen = "//span[contains(text(),'"+eventName+"')]/ancestor::div[2]/preceding-sibling::div//a";
	     WebElement clickCollapseEvent = driver.findElement(ByLocator(eventTobeopen));
	     clickOn(clickCollapseEvent);
     }
     
    
      public void verifyAllOptionOnQuestionnaireSettingPopup()
      {
    	  Assert.assertTrue(isElementDisplayed(questionnairepopupTitle));
    	  boolean flag=true;
    	  try {
    		  if (strictProtocoladgerencechkBx.isSelected()) {
    			  flag=true;
    			}
		  } catch (Exception e) {
		  }
			Assert.assertTrue(flag);
			reportInfo();

      }
      /*
       * verify follow up dropdown Field value
       */
      public void verifyFollowupDropDownValue(String Dropdown)
      {
    	  String dropdownValue = folllowDropdown.getText();
    	  boolean flag=true;
    	  try {
			if (dropdownValue.trim().contains(Dropdown)) {
				flag=true;
			}
		} catch (Exception e) {
		}
    	  Assert.assertTrue(flag);
    	  reportInfo();
      }
      /**
       * verify follow up DropDown list item
       */
      public void verifyFollowupdropDownOption()
      {
    	  clickOn(folllowDropdown);
    	  boolean flag=true;
    	  try {
	 	  List<WebElement> timelistes = driver.findElements(ByLocator("(//div[@class='k-list-scroller']/ul[@class='k-list k-reset'])[6]/li"));
    	  if (timelistes.size()==0) {
			flag=false;
		}
    	  } catch (Exception e) {
  		}
    	  Assert.assertTrue(flag);
    	  reportInfo();
      }
      /*
       *  save button disabled on Questionnaire setting popup
       */
      public void verifySavebtnDisabledonsettingPopup()
      {
    	  boolean flag=true;
    	  try {
			if (settingpopupSaveBtn.isEnabled()==false) {
				flag=true;
			}
		} catch (Exception e) {
		}
    	  Assert.assertTrue(flag);
    	  reportInfo();
    	   
      }
      /*
       *  save button Enabled on Questionnaire setting popup
       */
      public void verifySaveButtonisEnablede()
      {
    	  Assert.assertTrue(settingpopupSaveBtn.isEnabled());
    	  reportInfo();
      }
      /*
       *  click on save button  on Questionnaire setting popup
       */
      public void clickOnSaveButtonQuestionnaireSettingPopUp()
      {
    	  clickOn(settingpopupSaveBtn);
    	  WebElement confrmtn = driver.findElement(ByLocator("//div[@class='modal-dialog']//div[text()='Yes']"));
    	  clickOn(confrmtn);
    	  reportInfo();
      }
      /*
       *  Cancel button Enabled on Questionnaire setting popup
       */
      public void verifyCancelbtnEnabledOnsettingPopup()
      {
    	  Assert.assertTrue(settingpopupCancelBtn.isEnabled());
    	  reportInfo();
    	  
      }
      /**
       * click on Cancel button On questionnaire Setting pop up
       */
	  public void clickOnCancelBtnOnQuestionnairePopup()
	  {
		  clickOn(settingpopupCancelBtn);
		  reportInfo();
	  }
	  /*
	   * Verify Questionnaire setting poop up not Displayed
	   */
	  public void verifyQuestionnaireSettingPopupNotDisplayed()
	  {
		  waitUntillFinishProcessSpinnerDisable();
		  boolean flag=true;
		  if (questionnairepopupTitle.isDisplayed()==false) {
			flag=true;
		}
		  Assert.assertTrue(flag);
		  reportInfo();
	  }
	
	  /**
	   * Protocol Adherence for Questionnaires shown as 'Strict'
	   */
       public void verifyProtocolAdherenceForQuestionnaire(String formName,String Adherence)
       {
    	   String locator = "//span[contains(text(),'"+formName+"')]/ancestor::div[2]/following::div[2]";
    	WebElement formtobeverified = driver.findElement(ByLocator(locator)); 
    	String expected = formtobeverified.getText();
    	Assert.assertEquals(Adherence, expected);
       }


       public void selectFollowUpoDropDownValueInQuestionnaireSettingpopup(String time)
       {
    	   clickOn(folllowDropdown);
    	   WebElement selecttime = driver.findElement(ByLocator("//li[text()='"+time+"']"));
    	   clickOn(selecttime);
    	   reportInfo();
    	   
    	   
       }

}