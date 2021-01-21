package net.medavante.mobile.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.medavante.mobile.appium.core.MobileCoreFunctions;

public class MBQuestionnaires extends MobileCoreFunctions {

	public MBQuestionnaires(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ListView//android.widget.LinearLayout//android.view.ViewGroup[1]/android.view.View/following-sibling::android.widget.TextView[1]")
	private List<MobileElement> questionnairesTodayFormsList;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='4']")
	private List<MobileElement> questionnairesTomorrowFormsBlock;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='6']")
	private List<MobileElement> questionnairesLaterFormsList;

	@AndroidFindBy(xpath = "//*[contains(@class,'ViewGroup') and @index='1']//android.widget.TextView[@index='1']")
	private MobileElement questionStartingDescription;

	@AndroidFindBy(xpath = "//*[contains(@class,'android.widget.Button') and @text='Start']")
	private MobileElement startButtonForQuestion;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private MobileElement formTextEditBox;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @text='Mobile_Form']")
	private MobileElement formTitle;

	@AndroidFindBy(xpath = "//*[@class='android.widget.ProgressBar']")
	private MobileElement progressBar;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='3']")
	private MobileElement progressBarDesign2;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @index='5']")
	private MobileElement progressBarNextButton;

	@AndroidFindBy(xpath = "(//*[@class='android.view.ViewGroup' and @index='1'])[2]//following-sibling::android.view.View[@index='2']")
	private MobileElement progressBarPreviousButton;

	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private MobileElement menuBackIcon;

	@AndroidFindBy(id = "android:id/content")
	private MobileElement confirmationDialog;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	private MobileElement yesOption;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button2']")
	private MobileElement noOption;

	@AndroidFindBy(xpath = "(//*[@class='android.widget.CheckBox'])[1]")
	private MobileElement mFomr2CheckBox1;
	
	@AndroidFindBy(xpath = "(//android.widget.ListView//android.view.View)[1]//android.widget.RadioButton")
	private MobileElement radioButton;


	@AndroidFindBy(xpath = "(//*[@class='android.widget.TextView'])[2]")
	private MobileElement completionMessage;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
	private MobileElement continueBtn;

	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private MobileElement questionBackButtonICN;
	
	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@index='0']//following-sibling::android.view.ViewGroup)[2]")
	private MobileElement questionTextBoxBackButton;
	
	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[2]//android.view.ViewGroup[@class='android.view.ViewGroup'])[1]")
	private MobileElement firstQuesttabInList;

	/* Verify Today Question is Present */
	public void verifyTodayQuestionnairesListPresent() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			flag = true;
		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}
	
	/* Verify Tomorrow Questionnaires is Present */
	public void verifyTomorrowQuestionnairesListPresent() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		boolean flag = false;
		if (questionnairesTomorrowFormsBlock.size() > 0) {
			flag = true;
		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}

	

	/* Verify Later Questionnaires is Present */
	public void verifyLaterQuestionnairesListPresent() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		boolean flag = false;
		if (questionnairesLaterFormsList.size() > 0) {
			flag = true;
		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}

	/* Click On Questions */

	public void selectQuestionForms(String questionToBeSelect)

	{
		_normalWait(DEFAULT_WAIT_2_ELEMENT);
		new WebDriverWait(mobileDriver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.LinearLayout[@index='2']//android.view.ViewGroup[1]/android.view.View/following-sibling::android.widget.TextView[1]")));
		Assert.assertTrue(questionnairesTodayFormsList.size() > 0);
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			for (MobileElement question : questionnairesTodayFormsList) {
				if (getText(question).trim().equalsIgnoreCase(questionToBeSelect)) {
					flag = true;
					click(question);
					break;
				}

			}
		}
		Assert.assertTrue(flag);
		_normalWait(globalWaitTime);
		 capturescreen("Screenshot");
		

	}

	/* Verify question Starting page and Description */

	public void verifyQuestionStartingPageWithDescription(String descriptionToBeVerify) {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		waitForElementToBecomeVisible(questionStartingDescription, DEFAULT_WAIT_2_ELEMENT);
		Assert.assertTrue(getText(questionStartingDescription).trim().contains(descriptionToBeVerify));
		Assert.assertTrue(startButtonForQuestion.isDisplayed());
		 capturescreen("Screenshot");
	}

	/* Click On start Question */
	public void clickOnStartQuestion() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		waitForElementToBecomeVisible(startButtonForQuestion, DEFAULT_WAIT_4_ELEMENT);
		click(startButtonForQuestion);
		 capturescreen("Screenshot");
	}

	/* Verify Question Can Be Answered */
	public void verifyQuestionCanBeAnswered() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		waitForElementToBecomeVisible(formTitle, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(formTitle.isEnabled());
		 capturescreen("Screenshot");
	}

	/* Verify Progress bar showing */
	public void verifyProgressBarShowing() {
		Assert.assertTrue(progressBar.isDisplayed());
		 capturescreen("Screenshot");
	}

	/* Verify Progress bar showing */
	public void verifyProgressBarNotShowing() {
		Assert.assertEquals(getApiumDriver().findElementsByXPath("//*[@class='android.widget.ProgressBar']").size(), 0);
		 capturescreen("Screenshot");
	}

	/* Verify Previous Question Editable */
	public void verifyPreviousQuestionEditable() {
		Assert.assertTrue(formTitle.isEnabled());
		 capturescreen("Screenshot");
	}

	/* Verify Previous Question not Editable */
	public void verifyPreviousQuestionNotEditable() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		Assert.assertTrue(formTitle.isEnabled());
		// Assert.assertFalse(isClickable(mFomr2CheckBox1));
		 capturescreen("Screenshot");
	}

	/* Click on Progress Bar Next Button */

	public void tabOnProgressBarNextButton() {
		waitForElementToBecomeVisible(progressBarNextButton, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(progressBarNextButton.isDisplayed());
		//longPress(progressBarNextButton);
		progressBarNextButton.click();
		 capturescreen("Screenshot");
		
	}

	/* Click on Progress Bar Backward Button */

	public void tabOnProgressBarBackwardButton() {
		waitForElementToBecomeVisible(progressBarPreviousButton, DEFAULT_WAIT_4_ELEMENT);
		//longPress(progressBarPreviousButton);
		progressBarPreviousButton.click();
		 capturescreen("Screenshot");
	}

	/* Click on Progress Bar Back Icon */
	public void clickOnBackIcon() {
		click(menuBackIcon);
		_normalWait(DEFAULT_WAIT_2_ELEMENT);
		 capturescreen("Screenshot");
	}

	/* Verify exit Message */
	public void verifyConfirmationDialogDisplayWithYesAndNoOptions() {
		_normalWait(30);
		Assert.assertTrue(
				isElementPresent(confirmationDialog) && isElementPresent(yesOption) && isElementPresent(noOption));
		 capturescreen("Screenshot");

	}

	/* Click on No to exit */
	public void clickOnNoOption() {
		click(noOption);
		_normalWait(DEFAULT_WAIT_2_ELEMENT);
	}

	/* Click on Yes to exit */
	public void clickOnYesOption() {
		click(yesOption);
		_normalWait(DEFAULT_WAIT_ELEMENT);
	}

	/* Verify question Starting page and Description not showing */

	public void verifyQuestionStartingPageWithDescriptionIsNotShowing() {
		_normalWait(globalWaitTime);
		Assert.assertEquals(getApiumDriver()
				.findElementsByXPath("//*[contains(@class,'android.widget.Button') and @text='Start']").size(), 0);
		 capturescreen("Screenshot");
	}

	/*
	 * Fill the Mform2*
	 */
	public void filltheQuestionContainingCheckBox() {
		_normalWait(globalWaitTime);
		waitForElementToBecomeVisible(mFomr2CheckBox1, DEFAULT_WAIT_2_ELEMENT);
		boolean flag = false;
		if (mFomr2CheckBox1.isDisplayed()) {
			mFomr2CheckBox1.click();
			flag = true;
		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}

	/* Verify Forward Button ProgressBar Enabled */
	public void verifyProgressBarForwardNavigationIsEnabled() {
		Assert.assertTrue(progressBarNextButton.isEnabled());
		 capturescreen("Screenshot");
	}

	/* Verify Backward Button ProgressBar Disabled */
	public void verifyProgressBarBackwardNavigationIsDisabled() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		waitForElementToBecomeVisible(progressBarPreviousButton, globalWaitTime);
		Assert.assertFalse(progressBarPreviousButton.isEnabled());
		 capturescreen("Screenshot");
	}

	/* Verify Previous question can not be viewed and edited */
	public void verifyPreviousQuestionCantViewedAndEdited() {
		Assert.assertFalse(progressBarPreviousButton.isEnabled());
		 capturescreen("Screenshot");
	}

	/* Next Question Can't Viewed without providing answer */

	public void verifyNextQuestionNotViewedWithoutProvidingAnswer() {

		if (mFomr2CheckBox1.isSelected() == false) {
			_normalWait(DEFAULT_WAIT_ELEMENT);
			Assert.assertFalse(progressBarNextButton.isEnabled());
		}
	}

	/* Fill answer by filling TextBox */
	public void answerQuestionContaingTextBox(String answer) {
		waitForElementToBecomeVisible(formTextEditBox, DEFAULT_WAIT_4_ELEMENT);
		_normalWait(globalWaitTime);
		setText(formTextEditBox, answer);
		questionTextBoxBackButton.click();
		 capturescreen("Screenshot");

	}

	/* Back Button Click */

	public void navigateBack() {
		clickBackButton();
		 capturescreen("Screenshot");
	}

	/*
	 * Verify the percentage of not started Questionnaires (0%)
	 */

	public void verifyPercentageOfNotStartedQuestion(String formName, String percentageVerify) {
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			for (MobileElement question : questionnairesTodayFormsList) {
				if (getText(question).trim().contains(formName)) {
					MobileElement questionPercentage = getApiumDriver().findElement(By.xpath("//*[@text='" + formName
							+ "']//parent::android.view.ViewGroup//following-sibling::android.view.ViewGroup[@index='3']//*[@class='android.widget.TextView'][1]"));
					if (getText(questionPercentage).trim().contains(percentageVerify)) {
						flag = true;
						break;
					}
				}
			}

		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");

	}

	/* Verify Completion Message On last page */
	public void verifyCompletionSucessMessage(String compeltionMessageToBeVerify) {
		waitForElementToBecomeVisible(completionMessage, DEFAULT_WAIT_2_ELEMENT);
		_normalWait(globalWaitTime);
		Assert.assertTrue(getText(completionMessage).trim().contains(compeltionMessageToBeVerify));
		 capturescreen("Screenshot");
	}

	/*
	 * Click on Continue
	 * 
	 */

	public MobileDashBoardPage clickOnContinue() {
		waitForElementToBecomeVisible(continueBtn, globalWaitTime);
		click(continueBtn);
		 capturescreen("Screenshot");
		MobileDashBoardPage mobilePage= new MobileDashBoardPage(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), mobilePage);
		 capturescreen("Screenshot");
		return mobilePage;
	}

	/* Verify Continue Button Displayed */

	public void verifyContinueButtonDisplayed() {
		waitForElementToBecomeVisible(continueBtn, DEFAULT_WAIT_2_ELEMENT);
		Assert.assertTrue(continueBtn.isDisplayed());
		 capturescreen("Screenshot");
	}

	/* Fill All Answer */
	public void answerAllTextBoxField(String answer) {
		do {
			if(formTextEditBox.isDisplayed()){
			waitForElementToBecomeVisible(formTextEditBox, globalWaitTime);
		 	setText(formTextEditBox, answer);
		     _normalWait(globalWaitTime);
			  capturescreen("Screenshot");
			 waitForElementToBecomeVisible(progressBarNextButton, globalWaitTime);
		     click(progressBarNextButton);
			}

		} while (getApiumDriver().findElementsByXPath("//*[@class='android.view.View' and @index='5']").size() > 0);
	}

	/* Fill All checkbox Answer */
	public void answerAllCheckbox() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		do {
			if (mFomr2CheckBox1.isDisplayed()) {
				mFomr2CheckBox1.click();
				_normalWait(DEFAULT_WAIT_ELEMENT);
				 capturescreen("Screenshot");
				waitForElementToBecomeVisible(progressBarNextButton, DEFAULT_WAIT_ELEMENT);
				//longPress(progressBarNextButton);
				progressBarNextButton.click();
				
			}

		} while (getApiumDriver().findElementsByXPath("//*[@class='android.view.View' and @index='5']").size() > 0);
	}

	
	/*Fill All RadioButton*/
	
	public void answerAllRadioButton()
	{
		
		waitForElementToBecomeVisible(radioButton, globalWaitTime);
		_normalWait(DEFAULT_WAIT_ELEMENT);
		do{
			if(radioButton.isDisplayed())
			{
				radioButton.click();
				 capturescreen("Screenshot");
				waitForElementToBecomeVisible(progressBarNextButton, globalWaitTime);
				click(progressBarNextButton);
			}
		}while(getApiumDriver().findElementsByXPath("//*[@class='android.view.View' and @index='5']").size() > 0);
	}
	
	/* Verify Continue Button Not Displayed */

	public void verifyContinueButtonAndCompletionPageIsNotDisplayed() {
		Assert.assertEquals(getApiumDriver().findElementsByXPath("//android.widget.Button[@text='Continue']").size(),
				0);
		 capturescreen("Screenshot");
	}

	/*
	 * Verify Progress Bar Design 2*
	 */

	public void verifyProgressBarDesign2IsPresent() {
		Assert.assertTrue(progressBarDesign2.isDisplayed());
		 capturescreen("Screenshot");
	}

	/* Verify the question is Active or disactivated */
	public void verifyTheAppearenceOfForm() {
		_normalWait(globalWaitTime);
		 capturescreen("Screenshot");

	}

	/* Question with due time displayed */

	public void verifyDueTimeIsDisplayedForQuestionnaires(String questionName, String dueStatement) {

		_normalWait(DEFAULT_WAIT_ELEMENT);
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			for (MobileElement question : questionnairesTodayFormsList) {
				if (getText(question).trim().contains(questionName)) {
					MobileElement questionDue = getApiumDriver().findElement(By.xpath("(//*[@text='" + questionName
							+ "']//parent::android.view.ViewGroup//following-sibling::android.widget.TextView)[3]"));
					if (getText(questionDue).trim().contains(dueStatement)) {
						flag = true;
						break;
					}
				}
			}

		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}

	/* click on question page back button icon */

	public void clickOnBackButtonIconOnQuestionPage() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		waitForElementToBecomeVisible(questionBackButtonICN, globalWaitTime);
		click(questionBackButtonICN);
		 capturescreen("Screenshot");
	}
	
	/*Verify Completed Icon Present*/
	
	public void verifyCompletedIconPresentWithQuestionsAndNotEditable(String questionName)
	{
		
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			for (MobileElement question : questionnairesTodayFormsList) {
				if (getText(question).trim().contains(questionName)) {
					MobileElement completedIcon = getApiumDriver().findElement(By.xpath("//*[@text='" + questionName
							+ "']//parent::android.view.ViewGroup//following-sibling::android.view.View"));
					if (completedIcon.isDisplayed()) {
						flag = true;
						break;
					}
				}
			}

		}
		Assert.assertTrue(flag);
		_normalWait(DEFAULT_WAIT_ELEMENT);
		 capturescreen("Screenshot");
	}
	
/*Verify Completed Icon Present*/
	
	public void verifyPendingIconPresentWithQuestions(String questionName)
	{
		
		boolean flag = false;
		if (questionnairesTodayFormsList.size() > 0) {
			for (MobileElement question : questionnairesTodayFormsList) {
				if (getText(question).trim().contains(questionName)) {
					MobileElement pendingIcon = getApiumDriver().findElement(By.xpath("//*[@text='" + questionName
							+ "']//parent::android.view.ViewGroup//following-sibling::android.view.View"));
					if (pendingIcon.isDisplayed()) {
						flag = true;
						break;
					}
				}
			}

		}
		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}
	
	public void clickOnFirstQuestionnairesInTheList() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		boolean flag = false;
		if(firstQuesttabInList.isDisplayed()) {
			click(firstQuesttabInList);
			flag =true;
		}
		Assert.assertTrue(flag);
		_normalWait(DEFAULT_WAIT_ELEMENT);
		 capturescreen("Screenshot");
	}
}
