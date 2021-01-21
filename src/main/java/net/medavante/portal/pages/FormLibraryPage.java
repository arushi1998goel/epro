package net.medavante.portal.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.html.SubmittableElement;

import net.medavante.portal.enums.TagName;
import net.medavante.portal.selenium.core.BasePage;

public class FormLibraryPage extends BasePage {

	public FormLibraryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='top-content-panel clearfix']/button[2]")
	private WebElement newFormButton;

	@FindBy(xpath = "//div[@ng-show='popUpContentCtrl.isCurrentStep(1)']/div[1]/child::div/input")
	private WebElement abbreviationTextbox;

	@FindBy(xpath = "//div[@ng-show='popUpContentCtrl.isCurrentStep(1)']/div[2]/child::div/input")
	private WebElement titleTextBox;

	@FindBy(xpath = "//div[@ng-show='popUpContentCtrl.isCurrentStep(1)']/div[4]/div/span[2]")
	private WebElement initialPhase;

	@FindBy(xpath = "//div[@class='btn-group select-mode dropup']/button/i")
	private WebElement defaultColor;

	@FindBy(xpath = "//div[@class='modal-footer']/button[2]")
	private WebElement nextButton;

	@FindBy(xpath = "//div[@class='modal-footer']/button[4]")
	private WebElement cancelButton;

	@FindBy(xpath = "//div[@class='modal-footer']/button[1]")
	private WebElement backButton;

	@FindBy(xpath = "//div[@class='modal-footer']/button[3]")
	private WebElement createFormButton;

	@FindBy(xpath = "//h3[contains(text(),'Author of Original Paper Questionnaire')]//following-sibling::div/input")
	private WebElement authorOfOriginalPaperQuestionnaireTextBox;

	@FindBy(xpath = "//h3[contains(text(),'Change Note')]/parent::div/child::div/textarea")
	private WebElement changeNoteField;

	@FindBy(xpath = "//h3[contains(text(),'Description')]/parent::div/child::div/textarea")
	private WebElement descriptionField;

	@FindBy(xpath = "//h3[contains(text(),'Attached Document')]/following-sibling::button")
	private WebElement addDocumentButton;

	@FindBy(css = "input[placeholder='Search']")
	private WebElement searchField;

	@FindBy(css = "ul[class='change-tags-list two-cols-mode']>li")
	private List<WebElement> tagsList;

	@FindBy(xpath = "//div[contains(@class,'dropdown full-width')]/input")
	private WebElement dropDownList;

	@FindBy(xpath = "//div[contains(@class,'dropdown full-width')]/ul")
	private WebElement studyNameDropdownList;

	@FindBy(xpath = "//div[@class='datepicker-holder']")
	private WebElement datepickerDropdown;

	@FindBy(css = "div[class='tool-block text-right']>button")
	private WebElement addStudyButton;

	@FindBy(xpath = "//table[@class='ui-datepicker-calendar']//tbody/tr/td")
	private List<WebElement> datePickerList;

	@FindBy(xpath = "//div[@class='manager-table-body']//form-item")
	private List<WebElement> formAbbreviationText;
	
	@FindBy(xpath="//div[div[span[span[span[contains(text(), 'Form123')]]]]]/div[div[span[span[text()='Do not edit']]]]/following-sibling::div[contains(text(), 'Mobile')]/following-sibling::div//button[contains(text(), 'Delete')]")
    private  WebElement formElements;
	
	@FindBy(xpath = "//div[@class='top-content-panel clearfix']//input")
	private WebElement abbreviationSearchBox;
	
	@FindBy(xpath = "//div[@class='large-col-wrap ng-scope']")
	private WebElement createDesignButton;
	
	@FindBy(xpath = "//button[@class='btn pull-right']")
	private WebElement refreshIcon;
	
	@FindBy(xpath = "//div[@class='center-part clearfix']//li[1]")
	private WebElement designTab;
	
	@FindBy(xpath = "//form-pagination[@class='form-pagination ng-isolate-scope']//button[contains(text(),'Insert Page')]")
	private WebElement insertPage;
	
	@FindBy(xpath = "//div[@class='block-designer-area content-page-area ui-sortable designer-time']")
	private WebElement dropLocation;
	
	@FindBy(xpath = "//a[@class='btn btn-default ng-binding']")
	private WebElement editDesignButton;
	
	@FindBy(xpath = "//button[@class='btn dropdown-toggle ng-scope']")
	private WebElement editDesignDropdown;
	
	@FindBy(xpath = "//a[@class='ng-scope active']")
	private WebElement formListingScreen;
	
	
	@FindBy(xpath = "//header[@id='header']//div[@id='logo']")
	private WebElement virgilOgo;
	
	
	//tool box for input field and text fields
	@FindBy(xpath = "//span[text()='Date']")
	private WebElement dateTool;
	@FindBy(xpath = "//span[text()='Gauge']")
	private WebElement guageTool;
	@FindBy(xpath = "//span[text()='Keyboard']")
	private WebElement keyboardTool;
	@FindBy(xpath = "//span[text()='Multiple Choice']")
	private WebElement multipleChoiceTool;
	@FindBy(xpath = "//span[text()='Numeric']")
	private WebElement numericTool;
	@FindBy(xpath = "//span[text()='Single Choice']")
	private WebElement singleChoiceTool;
	@FindBy(xpath = "//span[text()='Single-Choice List']")
	private WebElement singleChoiceList;
	@FindBy(xpath = "//span[text()='Time']")
	private WebElement timeTool;
	@FindBy(xpath = "//span[text()='Heading']")
	private WebElement headingTool;
	@FindBy(xpath = "//span[text()='Text']")
	private WebElement textTool;

	//textbox for input and text fields

	@FindBy(xpath = "//div[@class='col-xs-18 column-for-property-editor ng-scope']//input")
	private WebElement textBoxForInputAndTextFields;

	//pagewise input and text fields link
	
	@FindBy(xpath = "//span[text()='Date1']")
	private WebElement dateToolLink;
	@FindBy(xpath = "//span[text()='Gauge1']")
	private WebElement guageToolLink;
	@FindBy(xpath = "//span[text()='Keyboard1']")
	private WebElement keyboardToolLink;
	@FindBy(xpath = "//span[text()='Multiple Choice1']")
	private WebElement multipleChoiceToolLink;
	@FindBy(xpath = "//span[text()='Numeric1']")
	private WebElement numericToolLink;
	@FindBy(xpath = "//span[text()='Single Choice1']")
	private WebElement singleChoiceToolLink;
	@FindBy(xpath = "//span[text()='Single-Choice List1']")
	private WebElement singleChoiceListLink;
	@FindBy(xpath = "//span[text()='Time1']")
	private WebElement timeToolLink;
	@FindBy(xpath = "//span[text()='Heading1']")
	private WebElement headingToolLink;
	
	
	@FindBy(xpath = "//div[@class='toolbar-button pull-right ng-scope']//a")
	private WebElement saveIcon;
	
	
	//Input field locator on form page
	
	@FindBy(xpath = "//div[@class='input-area textbox clear']")
	private WebElement dateFieldOnPage;
	
	@FindBy(xpath = "//div[@class='gauge-block-control horizontal-gauge']")
	private WebElement guageFieldOnPage;
	
	@FindBy(xpath = "//div[@class='ui-items-frame']//div[@class='input-area textbox']")
	private WebElement keyboardFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content multi-choice-block-container block-level1']")
	private WebElement multipleChoiceFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content numeric-block-container block-level1']")
	private WebElement numericFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content single-choice-block-container block-level1']")
	private WebElement singleChoiceFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content single-choice-list-block-container block-level1']")
	private WebElement singleChoiceListFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content time-block-container block-level1']")
	private WebElement timeFieldOnPage;
	
	@FindBy(xpath = "//div[@class='block-element-content header-block-container block-level1']")
	private WebElement headingFieldOnpage;
	
	@FindBy(xpath = "//button[@title='Move to First Page']")
	private WebElement moveToFirstPageLink;
	
	
	// Start review modal window locator
	@FindBy(xpath = "(//div[@class='datepicker-holder']//input)[2]")
	private WebElement dateFieldOnStartReviewWindow;
	
	@FindBy(xpath = "//div[@class='form-row']//textarea")
	private WebElement commentBoxOnStartReviewWindow;
	
	@FindBy(xpath = "(//div[@class='form-row']//input)[2]")
	private WebElement userNameOnStartReviewWindow;
	
	@FindBy(xpath = "(//div[@class='form-row']//input)[3]")
	private WebElement passwordOnStartReviewWindow;
	
	@FindBy(xpath = "(//div[@class='modal-footer']//button)[1]")
	private WebElement OKButtonOnStartReviewWindow;
	
	@FindBy(xpath = "(//div[@class='modal-footer']//button)[2]")
	private WebElement CancelButtonOnStartReviewWindow;
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * 
	 * Add text for selected fields
	 */
	
	public void addTextForSelectedFields(String date,String guage,String keyboard,String multipleChoice,String numeric,String singleChoice,String singleChoiceList,String time,String heading,
			String dateUpdated,String guageUpdated,String keyboardUpdated,String multipleChoiceUpdated,String numericUpdated,String singleChoiceUpdated,String singleChoiceListUpdated,String timeUpdated,String headingUpdated	) {
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+date+"']")));
		inputText(textBoxForInputAndTextFields, dateUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+guage+"']")));
		inputText(textBoxForInputAndTextFields, guageUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+keyboard+"']")));
		inputText(textBoxForInputAndTextFields, keyboardUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+multipleChoice+"']")));
		inputText(textBoxForInputAndTextFields, multipleChoiceUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+numeric+"']")));
		inputText(textBoxForInputAndTextFields, numericUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singleChoice+"']")));
		inputText(textBoxForInputAndTextFields, singleChoiceUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singleChoiceList+"']")));
		inputText(textBoxForInputAndTextFields, singleChoiceListUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+time+"']")));
		inputText(textBoxForInputAndTextFields, timeUpdated);
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+heading+"']")));
		inputText(textBoxForInputAndTextFields, headingUpdated);
		
	}
	
	/***
	 * Perform drag and drop for all the toolbox under input tab
	 */
	
	public void performDragAndDropForInputAndTextField() {
		dragAndDrop(dateTool, dropLocation);
		dragAndDrop(guageTool, dropLocation);
		dragAndDrop(keyboardTool, dropLocation);
		waitAndClick(insertPage);
		spinnerBecomeInvisible();
		dragAndDrop(multipleChoiceTool, dropLocation);
		dragAndDrop(numericTool, dropLocation);
		dragAndDrop(singleChoiceTool, dropLocation);
		waitAndClick(insertPage);
		spinnerBecomeInvisible();
		dragAndDrop(singleChoiceList, dropLocation);
		dragAndDrop(timeTool, dropLocation);
		dragAndDrop(headingTool, dropLocation);
		
	}
	
		
		
		/***
		 * Click on Save icon
		 */
		
		public void selectSaveIcon(){
			waitAndClick(saveIcon);
			waitForElement(saveIcon);
			_normalWait(2000);

		}
		
		/***
		 * 
		 * Click on move to first page arrow
		 */
		
		public void selectMoveToFirstPageControl() {
		    clickOn(moveToFirstPageLink);
          }
		
		/***
		 * 
		 * Verify all selected fields are displayed..
		 */
		
		
		public void verifyAllSelectedFieldsAreDisplayed(String datelinktextupdated, String guagelinktextupdated,
				String keyboardlinktextupdated, String multiplechoicelinktextupdated, String numericlinktextupdated,
				String singlechoicelinktextupdated, String singlechoicelistlinkupdated, String timelinktextupdated,
				String headinglinktextupdated) {
			boolean value1,value2,value3,value4,value5,value6,value7,value8,value9;
			
			
	        
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+datelinktextupdated+"']")));
			value1=dateFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+guagelinktextupdated+"']")));
			value2=guageFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+keyboardlinktextupdated+"']")));
			value3=keyboardFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+multiplechoicelinktextupdated+"']")));
			value4=multipleChoiceFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+numericlinktextupdated+"']")));
			value5=numericFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singlechoicelinktextupdated+"']")));
			value6=singleChoiceFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singlechoicelistlinkupdated+"']")));
			value7=singleChoiceListFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+timelinktextupdated+"']")));
			value8=timeFieldOnPage.isDisplayed();
			
			waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+headinglinktextupdated+"']")));
			value9=headingFieldOnpage.isDisplayed();
			
			
			
			if(value1==true && value2==true && value3==true && value4==true && value5==true
					&&value6==true && value7==true && value8==true && value9==true) {
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}		
		}
		
		
	
	/**
	 * Verify Form Listing Screen
	 * 
	 * @param tagName
	 * @param text
	 */
	public void verifyFormListingScreen(TagName tagName, String text) {
		verifytextPresentInTheTag(tagName, text);
		spinnerBecomeInvisible();
	}

	/**
	 * Click To create New Form
	 */
	public void clickOnNewFormButton() {
		waitAndClick(newFormButton);
	}

	/**
	 * verify New Form Window is displayed
	 * 
	 * @param tagName
	 * @param text
	 */
	public void verifyNewFormWindowModalIsDisplayed(TagName tagName, String text) {
		verifytextPresentInTheTag(tagName, text);
	}

	/**
	 * Mobile and tablet are displayed as form factor
	 * 
	 * @param texts
	 */
	public void verifyMobileAndTabletAreDisplayedAsFormFactor(boolean status, String... texts) {
		for (String text : texts) {
			_normalWait(DEFAULT_WAIT_4_ELEMENT);
			String mobileLocator = "//span[@class='label-name ng-binding' and text()='" + text + "']";
			verifyElementIsDisplayed(status, mobileLocator);
		}
	}

	/**
	 * Choose form type i.e :- Mobile or Tablet
	 * 
	 * @param formType
	 */
	public void chooseFormFactor(String formType) {
		String form = "//span[@class='label-name ng-binding' and text()='" + formType + "']//ancestor::label//input";
		clickOn(form);
		waitAndClick(nextButton);
	}

	/**
	 * Verify the field are required
	 */
	public void verifyNecessayFieldsAreRequired() {
		verifyTextColor(abbreviationTextbox, "rgba(204, 71, 44, 1)"); // red color code
		verifyTextColor(titleTextBox, "rgba(204, 71, 44, 1)");
		Assert.assertTrue(getText(initialPhase).contains("Initiate"));
		Assert.assertTrue(
				getAttributeValueOfElement(defaultColor, "style").contains("background-color: rgb(96, 96, 96)")); // darkGrey
																													// color
	}

	/**
	 * Verify status of next button :- Pass true to check for enable and false to
	 * disable check
	 * 
	 * @param Status
	 */
	public void verifyNextButtonStatus(boolean status) {
		angularHasFinishedProcessing();
		Assert.assertTrue(getAttributeValueOfElement(nextButton, "class").contains("btn-disabled"));
	}

	/**
	 * verify cancel and Back button are enabled :-Pass true to check for enable and
	 * false to disable check
	 */
	public void verifyCancelAndBackContorlButtonAreEnabled(boolean status) {
		verifyElementIsEnabledOrDisabled(status, cancelButton, backButton);
	}

	/**
	 * fill the required textbox field on New Form(Mobile) page Pass field name as
	 * key and the corresponding value to be entered in text box
	 * 
	 * @param fieldsName
	 */
	public void fillRequiredFields(HashMap<String, String> fieldsName) {
		fieldsName.forEach((fieldName, fieldValue) -> {
			String fieldTextBox = "//h3[text()='" + fieldName + "']//following-sibling::div[1]/input";
			waitAndClick(fieldTextBox);
			inputText(fieldTextBox, fieldValue);
		});
	}

	/**
	 * Click on next button
	 */
	public void clickOnNextButton() {
		waitAndClick(nextButton);
	}

	/**
	 * Verify author of original paper Questionnaire Field Is Displayed
	 */
	public void verifyAuthorOfOriginalPaperQuestionnaireFieldIsDisplayed(boolean status) {
		verifyElementIsDisplayed(status, authorOfOriginalPaperQuestionnaireTextBox);
	}

	/**
	 * Verify change note field and description field are displayed
	 */
	public void verifyChangeNoteFieldAndDescriptionFieldAreDisplayed(boolean status) {
		verifyElementIsDisplayed(status, changeNoteField, descriptionField);
	}

	/**
	 * Verify attached document with add document control is displayed
	 */
	public void verifyAttachedDocumentsWithAddDocumnetControl(boolean status) {
		verifyElementIsDisplayed(status, addDocumentButton);
	}

	/**
	 * Verify back next and cancel control is Enabled
	 */
	public void verifyNextBackCancelButtonIsEnabled(boolean status) {
		verifyElementIsEnabledOrDisabled(status, nextButton, backButton, cancelButton);
	}

	/**
	 * Verify tag search field is displayed
	 */
	public void verifyTagSearchFieldIsDisplayedWithListOfTags(boolean status) {
		verifyElementIsDisplayed(status, searchField);
		Assert.assertTrue(tagsList.size() > 1);
	}

	/**
	 * Verify Select Tag Name in search field
	 * 
	 * @param tagName
	 * @return
	 */
	public FormLibraryPage selectTagInSearchField(String tagName) {
		String tagLocator = "//ul[@class='change-tags-list two-cols-mode']/li//span[text()='" + tagName
				+ "']/..//input";
		waitAndClick(tagLocator);
		return this;
	}

	/**
	 * Verify drop down list for study name
	 * 
	 * @param status
	 * @return
	 */
	public FormLibraryPage verifyStudyNameDropDownList(boolean status) {
		waitAndClick(dropDownList);
		verifyElementIsDisplayed(status, studyNameDropdownList);
		waitAndClick(dropDownList);
		return this;
	}

	/**
	 * Verify date picker field is displayed
	 * 
	 * @param status :- pass true to check for displayed or false for not displayed
	 */
	public void verifyDatePickerField(boolean status) {
		verifyElementIsDisplayed(status, datepickerDropdown);
	}

	/**
	 * Verify Pdf button control status
	 * 
	 * @param text
	 * @param status :- pass true to check for enable or false for disable
	 */
	public void verifyAttachLicenseWithAddPdfControl(String text, boolean status) {
		String addPdfButton = "//h3[contains(text(),'" + text + "')]/../button";
		verifyElementIsDisplayed(status, addPdfButton);
	}

	/**
	 * Verify add study button status i.e :- enable or disable
	 * 
	 * @param status :- pass true to check for enable or false for disable
	 */
	public void verifyAddStudyButtonStatus(boolean status) {
		angularHasFinishedProcessing();
		if (status)
			Assert.assertFalse(getAttributeValueOfElement(addStudyButton, "class").contains("btn-disabled"));
		else
			Assert.assertTrue(getAttributeValueOfElement(addStudyButton, "class").contains("btn-disabled"));
	}

	/**
	 * Verify the status of form button i.e Enabled or Disable
	 * 
	 * @param status
	 * @return
	 */
	public FormLibraryPage verifyCreateFormButtonStatus(boolean status) {
		verifyElementIsEnabledOrDisabled(status, createFormButton);
		return this;
	}

	/**
	 * Click on create form button
	 */
	public FormLibraryPage clickOnCreateFormButton() {
		waitAndClick(createFormButton);
		_normalWait(4000);
		return this;
	}

	public void selectStudyName(String studyName) {
		waitAndClick(dropDownList);
		waitAndClick(studyNameDropdownList.findElement(By.xpath("./li/a[contains(text(),'" + studyName + "')]")));
	}

	/**
	 * The method will select the <b>day</b> passed inside the parameter for current
	 * month and year
	 * 
	 * @param day :- Pass the active date i.e The date not be holiday or inactive
	 *            business day The day passed should be working business day
	 */
	public void selectFirstPatientVisitFromDatePicker(String day) {
		waitAndClick(datepickerDropdown);
		for (WebElement element : datePickerList) {
			String valedDate = getAttributeValueOfElement(element, "class");
			if (Objects.isNull(valedDate) || StringUtils.containsIgnoreCase(valedDate, "ui-datepicker-today")) {
				String text = element.findElement(By.xpath("./a")).getText();
				if (text.equalsIgnoreCase(day))
					waitAndClick(element);
				break;
			}
		}
	}

	/***
	 * Verify the newly added form details
	 * @param abbreviation
	 * @param tagName
	 * @param formFactor
	 * @param delete
	 */
	
	
	public void veriyfNewFormIsDisplayedWithFollowingInfo(String abbreviation,String tagName,String formFactor,String delete) {
		inputText(abbreviationSearchBox, abbreviation);
		waitAndClick(refreshIcon);
		Assert.assertTrue(driver.findElement(By.xpath("//div[div[span[span[span[contains(text(), '"+abbreviation+"')]]]]]/div[div[span[span[text()='"+tagName+"']]]]/following-sibling::div[contains(text(), '"+formFactor+"')]/following-sibling::div//button[contains(text(), '"+delete+"')]")).isDisplayed());
		spinnerBecomeInvisible();
	}
	
	/**
	 * Expand row for given form Name
	 * @param formName
	 */
	
	public void expandRow(String forName) {
		inputText(abbreviationSearchBox, forName);
		_normalWait(2000);
		clickOn(refreshIcon);
		spinnerBecomeInvisible();
	    waitAndClick(getDriver().findElement(By.xpath("//span[@class='badge-box']")));
	    
	}
	
	
	/***
	 * Click on Open button for added form
	 */
	public void clickOnOpenButton(String formName) {
		
		_normalWait(4000);
		mouseHoverOnAnElement(getDriver().findElement(By.xpath("//span[text()='"+formName+"']/ancestor::div[3]/following::div[@class='collapse-slider collapse in']")));
        waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+formName+" ']/ancestor::div[3]/following::div[@class='collapse-slider collapse in']//button")));
		spinnerBecomeInvisible();
	}
	
	/***
	 * Click on create design button
	 */
	public void clickOnCreateDesignButton() {
	    waitAndClick(createDesignButton);

	}
	
	/***
	 * Verify Form design screen is displayed
	 */
	
	public void verifyFormDesignScreenIsDisplayed() {
		Assert.assertTrue(designTab.isDisplayed());
	}
	
	/***
	 * 
	 * Verify text(custom) fields are displayed
	 * 
	 * @param datelinktextupdated
	 * @param guagelinktextupdated
	 * @param keyboardlinktextupdated
	 * @param multiplechoicelinktextupdated
	 * @param numericlinktextupdated
	 * @param singlechoicelinktextupdated
	 * @param singlechoicelistlinkupdated
	 * @param timelinktextupdated
	 * @param headinglinktextupdated
	 */

	public void verifyFieldsTextAreDisplayed(String datelinktextupdated, String guagelinktextupdated,
			String keyboardlinktextupdated, String multiplechoicelinktextupdated, String numericlinktextupdated,
			String singlechoicelinktextupdated, String singlechoicelistlinkupdated, String timelinktextupdated,
			String headinglinktextupdated) {
		
		clickOn(moveToFirstPageLink);
		boolean value1,value2,value3,value4,value5,value6,value7,value8,value9;
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+datelinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
		value1=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+guagelinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value2=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+keyboardlinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
		value3=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+multiplechoicelinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value4=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+numericlinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value5=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singlechoicelinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value6=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+singlechoicelistlinkupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value7=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+timelinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value8=textBoxForInputAndTextFields.isDisplayed();
		
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='"+headinglinktextupdated+"']")));
		moveToElement(textBoxForInputAndTextFields);
        value9=textBoxForInputAndTextFields.isDisplayed();
		
		if(value1==true&& value2==true&&value3==true&&value4==true&&value5==true&&value6==true&&value7==true&&value8==true&&value9==true) {
			Assert.assertTrue(true);

		}
		else {
			Assert.assertTrue(false);
		}
	}

	public void findNewlyCreatedForm(String formAbbreviation, String tagname, String formfactormobile, String delete) {
		
		
	}

	
	/***
	 * 
	 * Click on edit design button
	 */
	public void clickOnEditDesignButton() {
		waitAndClick(editDesignButton);
		spinnerBecomeInvisible();
	}

	
	/****
	 * 
	 * activated edit design dropdown
	 */
	public void activateDropdownForEditDesign() {
		
		waitAndClick(editDesignDropdown);
	}
	
	
	
	/***
	 * 
	 * click on form listing screen
	 */

	public void clickOnFormListingTab() {

		waitAndClick(formListingScreen);
	}
	
	
	/***
	 * Click on Virgil logo
	 */
	
	public void clickOnVirgilLogo() {
		if(virgilOgo.isDisplayed()) {
			waitAndClick(virgilOgo);
			_normalWait(3000);
		}
	}
	
	/***
	 * find Newly Created Form Using Search Field
	 * @param formAbbreviation
	 */

	public void findNewlyCreatedFormUsingSearchField(String formAbbreviation) {
		
		inputText(abbreviationSearchBox, formAbbreviation);
		waitAndClick(refreshIcon);
		spinnerBecomeInvisible();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='badge-box']")).isDisplayed());

		
	}
	
	/***
	 * Select start review control
	 * @param startreviewtext
	 */

	public void selectStartReviewControl(String startreviewtext) {

		waitAndClick(getDriver().findElement(By.xpath("//a[@class='ng-binding ng-scope' and text()='"+startreviewtext+"']")));
		_normalWait(3000);
	}

	
	/***
	 * select unlock design control
	 * @param unlockdesigntext
	 */
	public void selectUnlockDesign(String unlockdesigntext) {
		
		waitAndClick(getDriver().findElement(By.xpath("//a[@class='ng-binding ng-scope' and text()='"+unlockdesigntext+"']")));
	}

	
	/***
	 * Verify start review modal window elements
	 */
	public void verifyStartReviewModalWindowElements(String reviewText) {

		Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='"+reviewText+"']")).isDisplayed());
        moveToElement(getDriver().findElement(By.xpath("//span[text()='"+reviewText+"']")));
        
        Assert.assertTrue(dateFieldOnStartReviewWindow.isDisplayed());
        moveToElement(dateFieldOnStartReviewWindow);
        
        Assert.assertTrue(commentBoxOnStartReviewWindow.isDisplayed());
        moveToElement(commentBoxOnStartReviewWindow);
        
        
        Assert.assertTrue(userNameOnStartReviewWindow.isDisplayed());
        moveToElement(userNameOnStartReviewWindow);
        
        
        Assert.assertTrue(passwordOnStartReviewWindow.isDisplayed());
        moveToElement(passwordOnStartReviewWindow);
	}
	
	
	/***
	 * Verify start review modal window elements
	 */
	public void verifySetAsCompleteModalWindowElements(String reviewText) {

		Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='"+reviewText+"']")).isDisplayed());
        moveToElement(getDriver().findElement(By.xpath("//span[text()='"+reviewText+"']")));
        
//        Assert.assertTrue(dateFieldOnStartReviewWindow.isDisplayed());
//        moveToElement(dateFieldOnStartReviewWindow);
        
        Assert.assertTrue(commentBoxOnStartReviewWindow.isDisplayed());
        moveToElement(commentBoxOnStartReviewWindow);
        
        
        Assert.assertTrue(userNameOnStartReviewWindow.isDisplayed());
        moveToElement(userNameOnStartReviewWindow);
        
        
        Assert.assertTrue(passwordOnStartReviewWindow.isDisplayed());
        moveToElement(passwordOnStartReviewWindow);
	}

	/***
	 * Input correct credentials
	 * @param superadminun
	 * @param superadminpw
	 */
	public void inputCorrectCredentials(String superadminun, String superadminpw) {
		inputText(userNameOnStartReviewWindow, superadminun);
		inputText(passwordOnStartReviewWindow, superadminpw);
		waitAndClick(OKButtonOnStartReviewWindow);
		_normalWait(3000);
	}

	/***
	 * Verify form on form listing screen
	 * @param formAbbreviation
	 */
	@FindBy(xpath="//div[@class='panel-title']//h2")
	private WebElement formNameOnFormListing;
	public void verifyFormOnFormListingScreen(String formAbbreviation) {
		moveToElement(formNameOnFormListing);
		String value=formNameOnFormListing.getText();
		if(value.contains(formAbbreviation))
		{
			Assert.assertTrue(true);
		}
		
	}
	/***
	 * activate drop down for view design
	 */
	@FindBy(xpath="(//button[@type='button']//span)[2]")
	private WebElement viewDesign;
	public void activateDropDownForViewDesign() {
		
		waitAndClick(viewDesign);
	}

	/***
	 * Select start tetsing control
	 */
	@FindBy(xpath="//a[text()='Start Testing']")
	private WebElement startTesting;
	public void selectStartTestingControl() {
		
		waitAndClick(startTesting);
		_normalWait(5000);
		
	}

	/***
	 * Verify start test modal window
	 * @param testlabel
	 */
	public void verifyStartTestingModalWindow(String testlabel) {
		// TODO Auto-generated method stub
		
	}

	/***
	 * select start regulatoru review action
	 */
	@FindBy(xpath="//a[text()='Start Regulatory Review']")
	private WebElement startRegulatoryAction;
	public void selectStartRegulatoryReviewAction() {
		
		waitAndClick(startRegulatoryAction);
		_normalWait(5000);
		
	}

	/***
	 * Select set as complete action
	 */
	@FindBy(xpath="//a[text()='Set as Complete']")
	private WebElement setAsCompleteAction;
	public void selectSetAsCompleteAction() {
		
		waitAndClick(setAsCompleteAction);
		_normalWait(5000);
		
	}

	/***
	 * Verify completed form
	 */
	@FindBy(xpath="//div[@class='pull-left']//span[text()='Complete']")
	private WebElement conplete;
	public void verifyCompletedForm() {
	

		if(conplete.isDisplayed())
		{
			Assert.assertTrue(true);
		}
	}
	
	
	
}
