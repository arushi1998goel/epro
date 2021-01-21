package net.medavante.portal.pages.studynavigator;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;

public class VisitDetailsPage extends BasePage {

	@FindBy(xpath = "//div[@id='page-title']/h1")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[@class='row administered-row']//div[contains(@class,'btn-group')]")
	private List<WebElement> assignDROPDOWN;

	@FindBy(xpath = "//div[contains(@class,'col-sm-offset-0 details-grid study-languages')]//label[text()='Status']//parent::div/following-sibling::div/label")
	private WebElement subjectStatusTxt;

	@FindBy(xpath = "//a[text()='HOME']")
	private WebElement homeIcon;

	@FindBy(xpath = "//div[contains(@class,'details-grid study-languages')][1]//div[@class='row']")
	private List<WebElement> visitDetailsInfoList;

	@FindBy(xpath = "//div[contains(@class,'details-grid study-languages')][2]//div[@class='row']")
	private List<WebElement> visitDetailsInfoListUnderSubSection;

	@FindBy(xpath = "//div[@id='virgilForms']//label[contains(text(),'Submitted by')]/following-sibling::label/a[@href]")
	private WebElement raterLinkNexttoFormThumbnail;

	@FindBy(xpath = "//div[@id='virgilForms']//label[contains(text(),'Submitted by')]/following-sibling::label")
	private WebElement submittedByRaterNameNexttoFormThumbnail;

	@FindBy(xpath = "//*[@title='Refresh']")
	private WebElement refreshIcon;

	@FindBy(xpath = "//h2[contains(text(),'Detail')]")
	private WebElement detailTitle;

	@FindBy(xpath = "//a[@role='tab' and contains(text(),'Assessments')]")
	private WebElement assessmentsTAB;

	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div")
	private WebElement actionButton;

	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div")
	private List<WebElement> actionButtonsLists;
	
	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div/button")
	private WebElement actionButtonList;

	@FindBy(xpath="//a[text()='Change Visit']")
	private WebElement changecisittlist;
	
	@FindBy(xpath = "//span[text()='Actions']//parent::a/following-sibling::button")
	private WebElement actionToggleDropDWNBTN;

	@FindBy(xpath = "//div[@ng-if='showActionDropDown()']//ul//li//a[text()='Change Visit']")
	private WebElement changeVisitLink;

	@FindBy(xpath = "//div[@class='modal-dialog move-visit-assesment-dialog']//div[@class='modal-content']")
	private WebElement moveVisitPopUp;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Subject']//parent::button")
	private WebElement changeToSubjectDRPDWN;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Subject']//parent::button//following-sibling::div//ul//li//span")
	private List<WebElement> changeToSelectSubjectList;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Visit']//parent::button")
	private WebElement changeToVisitDRPDWN;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Visit']//parent::button//following-sibling::div//ul//li//span")
	private List<WebElement> changeToVisitList;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Observer']//parent::button")
	private WebElement selectObserverDRPDWN;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Observer']//parent::button//following-sibling::div//ul//li//span")
	private List<WebElement> selectObserverList;

	@FindBy(xpath = "//div[@class='btn btn-active disabled']/following-sibling::div")
	private WebElement closeICN;

	@FindBy(xpath = "//div[@data-select-action='selectVisit']//span[@id='selectedStudy']")
	private WebElement visitChangeToSelectedTXT;

	@FindBy(xpath = "//div[@class='modal-content']//div[@class='btn btn-danger ng-scope']")
	private WebElement confirmButtonForMoveToVisit;
	
	@FindBy(xpath = "//div[@class='modal-footer']//div[text()='Save']")
	private WebElement saveButtonForMoveToVisit;

	@FindBy(xpath = "//div[contains(@class,'success-info-container')]//div[@class='close-button-white']")
	private WebElement closeMessage;

	@FindBy(xpath = "//div[@class='modal-footer']//div[@ng-click='noClick()']")
	private WebElement cancelButtonForMoveToVisit;

	@FindBy(xpath = "//div[@class='success-info-container']//label")
	private WebElement successMessage;

	@FindBy(xpath = "//div[@class='success-info-container']//div[@class='close-button-white']")
	private WebElement successContainerCloseIcon;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(@data-ng-if,'RaterDetail')]/a")
	private WebElement submittedScaleRaterNameTXT;

	@FindBy(xpath = "//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(text(),'Submitted Date')]/parent::div/label[2]")
	private WebElement scaleSubmittedDateTXT;

	@FindBy(xpath = "//div[@id='virgilForms']//div[contains(@class,'row administered-row')]")
	private WebElement assignedRaterTextLabel;

	// =============Reason For Change PopupLocators================

	@FindBy(xpath = "//div[@data-display-value='changeReason.englishName']")
	private WebElement reasonForChangeReasonDRPDOWN;

	@FindBy(xpath = "//div[@data-display-value='changeReason.englishName']//li/span")
	private List<WebElement> reasonForChangeReasonOptionLIST;

	@FindBy(xpath = "//div[@class='modal-body']//div[contains(@class,'col-sm')]/br//parent::div")
	private WebElement reasonForChangeAuthenticationText;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@id='loginInput']")
	private WebElement reasonForChangeUserNameINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@id='passwordInput']")
	private WebElement reasonForChangePasswordINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='OK']")
	private WebElement reasonForChangeOkBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='Cancel']")
	private WebElement reasonForChangeCancelBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[@class='modal-content']//h4[text()='Reason for change']")
	private List<WebElement> reasonForChangePopUpDisplayed;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[@class='modal-content']//h4[contains(text(),'Reason of Change')]")
	private WebElement reasonForChangePopUp;
	
	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[@class='modal-content']//h4[text()='Reason of Change']")
	private WebElement reasonofChangePopUp;

	@FindBy(xpath = "//*[@data-ng-model='reasonComment']")
	private WebElement reasonArea;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']")
	private List<WebElement> configuredTemplatesList;

	// ==================Visit Queries======

	@FindBy(xpath = "//*[@title='Show queries']")
	private WebElement queriesLinkICN;

	@FindBy(xpath = "//a[@title='Add Query']")
	private WebElement addQueriesButton;

	@FindBy(xpath = "//div[@class='portal-grid query-list']//div[contains(@class,'row query-item-row grid-row')]")
	private List<WebElement> queriesList;
		
	@FindBy(xpath = "//div[@class='queries-list']//div[@data-is-visible='isShowNew']//textarea[contains(@class,'reply-text')]")
	private WebElement queriesTextArea;
	
	@FindBy(xpath = "//div[@class='queries-list']//div[@data-query-id='query.id']//textarea[contains(@class,'reply-text')]")
	private WebElement replyQueriesTextArea;

	@FindBy(xpath = "//ul[@class='queries-controls']//li//a[@title='Create']")
	private WebElement createQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//a[@title='Cancel']")
	private WebElement cancelQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Reply']")
	private WebElement replyQueryButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Close Query']")
	private WebElement closeQueryButton;

	@FindBy(xpath = "//div[@class='queries-dialog-content']//a[@title='Close']")
	private WebElement queriesCollpaseBTN;

	@FindBy(xpath = "//div[@class='queries-dialog-header-title']")
	private WebElement queriesSliderPanelOpen;

	@FindBy(xpath = "//div[@data-scores-model='scoresModel']//div[@title='Save']//a")
	private WebElement scoreTabSaveBTN;

	@FindBy(xpath = "//a[text()='Attachments']")
	private WebElement attachmentsTAB;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-ng-if='showContextSelector']")
	private WebElement contextSelector;

	@FindBy(xpath = "//div[@data-display-value='selectedContext.name']//li/span")
	private List<WebElement> contextMenuLIST;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-ng-if='showScoreSelector']")
	private WebElement scoreSelector;

	@FindBy(xpath = "//div[@data-display-value='selectedScore.name']//li/span")
	private List<WebElement> scoreNameLIST;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Edit']")
	private WebElement queryEditButton;

	@FindBy(xpath = "//img[contains(@data-ng-hide,'form.paperTranscription') and not(contains(@class,'ng-hide'))]")
	private WebElement thumbnailBeforeNotAdministeredIMG;

	@FindBy(xpath = "//img[contains(@data-ng-show,'form.notAdministered')]")
	private WebElement notAdministeredThumbnailIMG;

	@FindBy(css = "embed[id='plugin']")
	private WebElement pdfFormOpenInNewWindow;

	@FindBy(xpath = "//label[contains(@data-ng-if,'form.notAdministered') and text()='Not Administered']")
	private WebElement notAdministeredLabel;
	
	@FindBy(xpath = "//a[@class='expander-button circle-button arrowup']")
	private WebElement arrowUp;
	
	@FindBy(xpath = "//a[@class='expander-button circle-button arrowdown']")
	private WebElement arrowDown;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Delete Query']")
	private WebElement queryDeleteButton;
	
	@FindBy(xpath = "(//ul[contains(@class,'nav') and not(contains(@class,'drop'))])[1]/li/a")
	private List<WebElement> navMenuItemsList;

	@FindBy(xpath = "//*[@id='header']//ul[@class='dropdown-menu']/parent::li[@class='dropdown open']//following-sibling::ul/li/a")
	private List<WebElement> subDropDownOfNavMenu;

	public VisitDetailsPage(WebDriver driver) {
		super(driver);
	}

	/** Verify Visit Details Page is displayed **/
	public void verifyVisitPageIsDisplayed() {
//		_normalWait(1000);
		Assert.assertTrue(isElementPresent(pageTitle));
		reportInfo();
	}

	public void verifyRefreshIconIsDisplayed() {
		Assert.assertTrue(refreshIcon.isDisplayed());
		reportInfo();
	}

	public void verifyVisitPageTitle(String titleToBeVerified) {
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(pageTitle.getText().trim().contains(titleToBeVerified));
		reportInfo();
	}

	public void verifyVisitDetailIsDisplayed() {
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']/div[@class='spinner']")));
		Assert.assertTrue(isElementPresent(detailTitle));
		reportInfo();
	}

	public void verifyAssessmentsTabIsDisplayed() {
		Assert.assertTrue(isElementPresent(assessmentsTAB));
		reportInfo();
	}

	public void navigateBackToPreviousPage() {
		navigateBack();
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		reportInfo();
	}

	public void verifyActionButtonIsDisplayed() {
		Assert.assertTrue(actionButton.isDisplayed());
		reportInfo();
	}

	
	/*
	 * Verify Action Button Is Not displayed(Previous) functionality
	 */

	public void verifyActionButtonIsNotDisplayed() {
		boolean flag = true;
		if (actionButtonsLists.size() == 0) {
			flag = false;
		}
		Assert.assertFalse(flag, "The action Button Is Not  Present");
		reportInfo();
	}
	
	
	
	/*
	 * Verify Action Button Is Not displayed accordingly New functionality
	 */

	public void verifyingActionButtonIsNotDisplayed() {
		moveToElement(actionButtonList);
		waitAndClick(actionButtonList);
		boolean flag = true;
		try {
			if (changecisittlist.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertFalse(flag);
		reportInfo();
	}
	
	
	
	public void verifyRaterDropIsNotPresent() {
		boolean flag = false;
		if (assignDROPDOWN.size() == 0) {
			flag = true;
		}
		assertTrue(flag);
		reportInfo();
	}

	public void verifySubjectStatus(String subjectStatusToBeVerified) {
		Assert.assertTrue(subjectStatusTxt.getText().contains(subjectStatusToBeVerified));

	}

	public void navigateToHome() {
	  selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyVisitDetails(String detailLabel, String detailToBeVerified) {
		boolean flag = false;
		for (int i = 1; i < visitDetailsInfoList.size(); i++) {
			WebElement detailRow = visitDetailsInfoList.get(i);
			if (getText(detailRow.findElement(By.xpath(".//div[contains(@class,'caption')]//label"))).trim()
					.equalsIgnoreCase(detailLabel)) {
				try {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//a"))).trim()
							.equals(detailToBeVerified);
				} catch (Exception e) {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//label"))).trim()
							.contains(detailToBeVerified);
				}
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag,
				detailLabel + " Should be present in grid to be click and search " + detailToBeVerified + " value.");

	}

	public void verifyVisitDetailsUnderSubjectSection(String detailLabel, String detailToBeVerified) {
		boolean flag = false;
		for (int i = 1; i < visitDetailsInfoListUnderSubSection.size(); i++) {
			WebElement detailRow = visitDetailsInfoListUnderSubSection.get(i);
			if (getText(detailRow.findElement(By.xpath(".//div[contains(@class,'caption')]//label"))).trim()
					.equalsIgnoreCase(detailLabel)) {
				try {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//label"))).trim()
							.contains(detailToBeVerified);
						flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//label"))).trim().toLowerCase()
							.contains(detailToBeVerified.toLowerCase());
				} catch (Exception e) {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//a"))).trim()
							.contains(detailToBeVerified.toLowerCase());
				}
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag,
				detailLabel + " Should be present in grid to be click and search " + detailToBeVerified + " value.");
	}

	public void verifyRatersLinkDisplayed(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(raterLinkNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(raterLinkNexttoFormThumbnail).trim(), RaterName);
		reportInfo();
	}

	public void verifySubmittedByRatersNameNonHyprlinkDisplayed(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(submittedByRaterNameNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(submittedByRaterNameNexttoFormThumbnail).trim(), RaterName);
		reportInfo();
	}

	/* Verify Assigned Rater Name Next To Thumbnail Image */
	public void verifyAssignedRaterNameNextToThumbnail(String RaterName) {
		scrollToTopOfThePage();
		moveToElement(assignedRaterTextLabel);
		Assert.assertTrue(assignedRaterTextLabel.isDisplayed());
		Assert.assertEquals(getText(assignedRaterTextLabel).trim(), RaterName);
		reportInfo();
	}

	/* Verify Action Button Is Displayed */

	public void verifyActionOptionIsDisplayed() {
		Assert.assertTrue(actionButton.isDisplayed());
		reportInfo();
	}
	


	/* Select Action To Move Visit */

	public void selectActionToMoveVisit() {
		clickOn(actionToggleDropDWNBTN);
		clickOn(changeVisitLink);
		//_normalWait(120000);
		_normalWait(30000);
		waitSpinnerToBecomeInvisible();
		WebDriverWait wait=new WebDriverWait(driver, 60); 
		// Add wait time for slowness
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
		waitForElement(moveVisitPopUp);
		waitSpinnerToBecomeInvisible();

	}

	/* Verify Move Visit Pop Up Displayed */
	public void verifyMoveVisitPopUpIsDisplayed() {
		_normalWait(10000);
		Assert.assertTrue(isElementPresent(moveVisitPopUp));
		reportInfo();
	}

	public void verifyMoveVisitPopUpIsNotDisplayed() {
		Assert.assertTrue(isElementPresent(moveVisitPopUp));
		reportInfo();
	}

	/* Select Subject For Moving visit */

	public void clickOnChangeToSelectSubjectDropDown() {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		clickOn(changeToSubjectDRPDWN);
	}
	/* Select visit For Moving visit */

	public void clickOnChangeToVisitDropDown() {
		waitSpinnerToBecomeInvisible();
		moveToElement(changeToVisitDRPDWN);
		clickOn(changeToVisitDRPDWN);
	}

	/*
	 * Select Change To SubjectFrom DropDown
	 */
	public void selectChangeToSubject(String subjectToBeSelect) {
		for (WebElement subjectChangeTo : changeToSelectSubjectList) {
			if (subjectChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				//waitAndClick(subjectChangeTo);
				waitAndClick(subjectChangeTo.findElement(By.xpath(".//parent::li")));
			}
		}
	}

	/* Verify Visit Not Present In Change To Visit DropDown */
	public void verifyVisitIsNotPresentInChangeToVisitDropDown(String subjectToBeSelect) {
		boolean flag = true;
		for (WebElement visitChangeTo : changeToVisitList) {
			if (visitChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				flag = false;
				break;
			} 
		}
		Assert.assertTrue(flag,"Visit Not Present In List");
		reportInfo();
		moveToElement(changeToVisitDRPDWN);
		clickOn(changeToVisitDRPDWN);
	}

	/* Verify Visit Present In Change To Visit DropDown and select that visit */
	public void verifyVisitIsPresentInChangeToVisitDropDown(String subjectToBeSelect) {
		boolean flag = false;
		for (WebElement visitChangeTo : changeToVisitList) {
			if (visitChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				flag = true;
				break;

			}
		}
		Assert.assertTrue(flag, "Visit  Present In List");
		reportInfo();
		clickOn(changeToVisitDRPDWN);
	}

	/* Select Visit From Change To DropDown */

	public void selectChangeToVisit(String VisitToBeSelect) {
		clickOn(changeToVisitDRPDWN);
		for (WebElement visitChangeTo : changeToVisitList) {
			if (visitChangeTo.getText().trim().equalsIgnoreCase(VisitToBeSelect)) {
				waitAndClick(visitChangeTo.findElement(By.xpath(".//parent::li")));
				break;
			}
		}
	}

	/* Select Visit From Change To DropDown */

	public void selectObserver(String ObserverToBeSelect) {
		clickOn(selectObserverDRPDWN);
		for (WebElement observerChangeTo : selectObserverList) {
			if (observerChangeTo.getText().trim().equalsIgnoreCase(ObserverToBeSelect)) {
				waitAndClick(observerChangeTo.findElement(By.xpath(".//parent::li")));
				break;
			}
		}
	}

	/* Verify Subject Of Different Study Not Present In Subject DropDown */
	public void verifySubjectIsNotPresentInChangeToSubjectDropDown(String subjectToBeSelect) {
		boolean flag = true;
		for (WebElement subjectChangeTo : changeToSelectSubjectList) {
			if (subjectChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				flag = false;
			}
		}
		Assert.assertTrue(flag, "Subject Not Present");
		reportInfo();
	}

	/* Click On Close Icon */
	public void clickOnCloseIconOfMoveVisitPopUp() {
		clickOn(closeICN);
	}

	/* Verify Change To Visit Is Selected */

	public void verifyChangeToVisitSelected(String VisitSelected) {
		Assert.assertTrue(visitChangeToSelectedTXT.getText().trim().equalsIgnoreCase(VisitSelected));
		reportInfo();
	}

	/* Click On Save button on change assessment page */

	public void  clickOnSaveButtonOnChangeAssesment() {
		moveToElement(saveButtonForMoveToVisit);
		clickOn(saveButtonForMoveToVisit);

	}
	
	/* Click On Confirm button of change assessment  */

	public void  clickOnConfirmButtonOfChangeAssesment() {

		waitForSpinnerBecomeInvisible(3000);
		moveToElement(confirmButtonForMoveToVisit);
		waitAndClick(confirmButtonForMoveToVisit);
	}	

	/* Verify ReasonForChange PopUp Displayed */

	public void verifyReasonForChangeOptionPopUpIsDisplayed() {
		Assert.assertTrue(isElementPresent(reasonForChangeReasonDRPDOWN));
		reportInfo();
	}

	public void clickOnCancelButtonInMoveToVisitConfirmation() {
		waitAndClick(cancelButtonForMoveToVisit);
	}

	public void verifyReasonForChangePopUpDisplayed() {
		_normalWait(1500);
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementDisplayed(reasonForChangePopUp));
		reportInfo();
	}
	

	public void verifyReasonofChangePopUpDisplayed() {
		_normalWait(1500);
		Assert.assertTrue(isElementDisplayed(reasonofChangePopUp));
		reportInfo();
	}

	public void verifyReasonForChangePopUpIsNotDisplayed() {
		boolean flag = false;
		if (reasonForChangePopUpDisplayed.size() == 0) {
			flag = true;
		}
		Assert.assertTrue(flag, "Pop up Is Not  Present");
		reportInfo();
	}

	/**
	 * Select reason for change from the options
	 * 
	 * @param reasonToSelect
	 */
	public void selectReasonForChangeOption(String reasonToSelect) {
		waitAndClick(reasonForChangeReasonDRPDOWN);
		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(reasonToSelect)) {
				clickOn(reasonOption.findElement(By.xpath("./parent::li")));
				break;
			}
		}
	}

	/* Verify List of options present in Reason for change dropdown */
	public void verifyReasonForChangeOptions(String reasonToSelect) {
		boolean flag = false;
		waitAndClick(reasonForChangeReasonDRPDOWN);
		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(reasonToSelect)) {
				flag = true;
				break;

			}
		}
		Assert.assertTrue(flag, "Option Present In List");
		reportInfo();
		clickOn(reasonForChangeReasonDRPDOWN);
	}

	/* Input Credential For ReasonFor Change */
	public void inputCredentialsInReasonForChangePopUp(String userName, String password) {
		_normalWait(500);
		inputText(reasonForChangeUserNameINP, userName);
		inputText(reasonForChangePasswordINP, password);
		reportInfo();
	}

	/**
	 * Esign after selecting reason for change
	 * 
	 * @param userName
	 * @param password
	 */
	public void eSignReasonForChangeAndSubmit(String userName, String password) {
		inputCredentialsInReasonForChangePopUp(userName, password);
		clickOn(reasonForChangeOkBTN);
		try {
			if (driver.findElement(By.xpath("//div[@class='smart-spinner']")).isDisplayed() == true) {
				new WebDriverWait(driver, 25).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
			}
		} catch (Exception e) {
		}
	}

	/** Verify Success Message **/

	public void verifyMessage(String message) {
		Assert.assertEquals(getText(successMessage).trim(), message);
		reportInfo();
		clickOn(closeMessage);
	}

	public void eSignForReasonForChange(String userName, String password) {
		
		_normalWait(3000);
		inputText(reasonForChangeUserNameINP, userName);
		inputText(reasonForChangePasswordINP, password);
		clickOn(reasonForChangeOkBTN);
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	/**
	 * Enter comment for the reason
	 *
	 */
	public void inputReasonComment(String reasonComment) {
		inputText(reasonArea, reasonComment);
		reportInfo();
	}

	/** Verify success Message **/

	public void verifySuccessMessage(String message) {
		_normalWait(2000);
		Assert.assertEquals(getText(successMessage).trim(), message);
		reportInfo();
	}

	/**
	 * Close success Container by click on close button
	 */
	public void closeSuccessMessage() {
		clickOn(successContainerCloseIcon);
	}

	public void clickOnReasonForChangeOkBTN() {
		waitAndClick(reasonForChangeOkBTN);
	}

	public void clickOnReasonForChangeCancelBTN() {
		waitAndClick(reasonForChangeCancelBTN);
	}

	/**
	 * Click On Not Complete Link From multiple configured(Pro,Obsro) Form Type
	 */
	public void selectNotCompletedLinkForMultipleConfiguredFormType(String configuredFormType) {
		scrollToTopOfThePage();
		boolean flag = false;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			clickOn(driver.findElement(By.xpath("//div[@class='section ng-scope']/div[text()='" + configuredFormType
					+ "']//parent::div//a[contains(@data-ng-click,'markAsNotCompleted')]")));

			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Reason For Change All Option Present In DropDown */
	public void verifyReasonForChangeOptionsPresentInDropDown(List<String> reasonToSelect) {
		clickOn(reasonForChangeReasonDRPDOWN);
		List<String> reasonForChange = new ArrayList<>();
		for (WebElement reasonChangeValues : reasonForChangeReasonOptionLIST) {
			reasonForChange.add(getText(reasonChangeValues).trim());
		}
		Assert.assertEquals(reasonForChange, reasonToSelect, "Reason For Change Values Are Present");
		clickOn(reasonForChangeReasonDRPDOWN);
	}

	public void verifySubmittedVisitRaterName(String raterNameToBeVerified) {
		Assert.assertTrue(getText(submittedScaleRaterNameTXT).contains(raterNameToBeVerified));
		reportInfo();
	}

	public void verifySubmittedVisitDate(String dateToBeVerified) {
		Assert.assertTrue(scaleSubmittedDateTXT.getText().toLowerCase().contains(dateToBeVerified.toLowerCase()));
		reportInfo();
	}

	/* Select Queries Icon */
	public void clickOnQueriesIcon() {
		waitAndClick(queriesLinkICN);
	}

	/* Select Queries Close Icon */

	public void clickOnQueriesCollpaseIcon() {
		waitAndClick(queriesCollpaseBTN);
	}

	/* click on Add query button */

	public void clickOnAddQueriesButton() {
		waitAndClick(addQueriesButton);
	}

	public void clickOnCreateQueryButton() {
		waitForElementPresent(createQueriesButton, DEFAULT_WAIT_4_ELEMENT);
		clickOn(createQueriesButton);

	}

	public void clickOnCancelQueryButton() {
		waitAndClick(cancelQueriesButton);

	}

	public void clickOnReplyQueryButton() {
		waitAndClick(replyQueryButton);

	}

	public void clickOnCloseQueryButton() {
		waitAndClick(closeQueryButton);

	}

	/* Verify Queries Panel Displayed */
	public void verifyQueriesDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(queriesSliderPanelOpen), "Query Details Slider panel is displayed");
		reportInfo();

	}

	/**
	 * Verify Presence Of Query By Subject In Query List
	 * 
	 */
	public void verifyPresenceOfQueryBySubject(String Subject) {
		boolean flag = false;
		for (WebElement subjecttoselect : queriesList) {
			if (subjecttoselect.getText().trim().contains(Subject)) {
				moveToElement(subjecttoselect);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}


	/* verify add query list area display */

	public void addNewQuery(String text) {
		clickOnAddQueriesButton();
		waitForWebElementEnable(queriesTextArea, 10);
		inputText(queriesTextArea, text);		
		clickOnCreateQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}
	
	/**
	 * Select the Query
	 * 
	 * @param queryName
	 * @return
	 */
	public void selectQuery(String queryName) {
		boolean flag = false;
		for (WebElement querytoselect : queriesList) {
			if (querytoselect.findElement(By.xpath(".//div[@class='thread-message ng-binding']")).getText()
					.equalsIgnoreCase(queryName)) {
				flag = true;
				scrollIntoView(querytoselect);
				clickOn(querytoselect);
				waitForElement(findElement(By.xpath("//div[@data-ng-show='isExpand()' and @class='collapsed']")));
				waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
				_normalWait(4000);
				break;
			}
		}

		Assert.assertTrue(flag);
		reportInfo();
	}
		
	

	public void verifyBackgroundScreenIsRefreshed() {
		String locator = "//div[@data-ng-controller='AssessmentDetailsTitleController']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));

	}

	public void verifyBackgroundScreenIsNotRefreshed() {
		String locator = "//div[@class='ng-isolate-scope ng-hide']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));

	}

	public void verifyQueryPanelIsNotRefreshed() {
		String locator = "//div[@class='ng-isolate-scope ng-hide']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));

	}

	public void verifyQueryEditButtonDisplay() {
		Assert.assertTrue(queryEditButton.isDisplayed());
		reportInfo();
	}

	public void VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent() {
		moveToElement(thumbnailBeforeNotAdministeredIMG);
		Assert.assertTrue(thumbnailBeforeNotAdministeredIMG.isDisplayed());
	}

	/** Verify Rater Dropdown Selected Value for configured Form Type */
	public void verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(String configuredFormType,
			String valueSelected) {
		scrollToTopOfThePage();
		boolean flag = false;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("./div[@class='small ng-binding']")))
						.equalsIgnoreCase(configuredFormType)) {
					Assert.assertTrue(getText(scaleRow.findElement(By
							.xpath("//div[@class='row administered-row']//span[@class='text-ellipsis-dd ng-binding']")))
									.trim().contains(valueSelected));
				/*	Assert.assertTrue(getText(scaleRow.findElement(By
							.xpath("//div[@class='row administered-row']//label[@class='disabled ng-binding ng-scope']")))
									.trim().contains(valueSelected));*/
					flag = true;
					break;
				}
			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/* Click On Thumbnail Image */
	public void clickOnBeforeNotAdministeredThumbnailImage() {
		waitForElementPresent(thumbnailBeforeNotAdministeredIMG, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(thumbnailBeforeNotAdministeredIMG.isDisplayed());
		scrollToTopOfThePage();
		waitAndClick(thumbnailBeforeNotAdministeredIMG);
	}

	/*
	 * Click On Assigned Rater thumbnail Image*?
	 * 
	 */
	public AssessmentsDetailsPage clickOnAssignedRaterThumbnailImage() {
		clickOnBeforeNotAdministeredThumbnailImage();
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT).until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope ng-hide']")));
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	/* Click On After Completion Not Administered ThumbNAILFORM */
	public AssessmentsDetailsPage clickOnAfterNotAdministeredThumbnailImage() {
		waitForElement(notAdministeredThumbnailIMG);
		Assert.assertTrue(notAdministeredThumbnailIMG.isDisplayed()) ;
		clickOn(notAdministeredThumbnailIMG);
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	/**
	 * Select thumbnail IMG from multiple configured Form Type
	 * 
	 * @return
	 */
	public void verifyFormOpenInPDFtemplate() {
		Assert.assertTrue(getAttributeValueOfElement(pdfFormOpenInNewWindow, "type").trim().endsWith("pdf"));
		reportInfo();
	}

	public void selectCheckboxQueryOption(String checkboxQueryName) {
		WebElement checkBoxQuery = driver.findElement(By.xpath("//div[@class='checkbox-query']//span[text()='"
				+ checkboxQueryName + "']//../input[@type='checkbox']"));
		waitAndClick(checkBoxQuery);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyListOfAllQueriesDisplayed() {

		Assert.assertTrue(queriesList.size() > 0, "Queries List is displaying");
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();

	}

	/**
	 * Verify reply button displayed
	 */
	public void verifyReplyButtonDisplayed() {
		waitForElement(replyQueryButton);
		Assert.assertTrue(replyQueryButton.isDisplayed());
		moveToElement(replyQueryButton);
		reportInfo();
	}

	public void replyQuery(String text) {
		waitForElementPresent(queriesTextArea, 10);
		inputText(replyQueriesTextArea, text);
		clickOnReplyQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}

	/**
	 * Verify close query button displayed
	 */
	public void verifyCloseQueryButtonDisplayed() {
		waitForElementPresent(closeQueryButton, 20);
		Assert.assertTrue(closeQueryButton.isDisplayed());
		moveToElement(closeQueryButton);
		reportInfo();
	}

	/* Verify NotAdministred Thumbnail Form Present */
	public void verifyNotAdministeredFormThumbnailImageIsPresent() {
		waitForPageLoaded();
		moveToElement(notAdministeredThumbnailIMG);
		Assert.assertTrue(notAdministeredThumbnailIMG.isDisplayed());
		reportInfo();

	}

	/* Verify Not Administered Label Present */

	public void verifyNotAdministeredLabelPresent() {
		moveToElement(notAdministeredLabel);
		Assert.assertTrue(isElementPresent(notAdministeredLabel));
		reportInfo();

	}

	public void clickonArrowDownIcon() {
		waitAndClick(arrowDown);
		
	}
	
	public void clickonArrowUpIcon() {
		waitAndClick(arrowUp);
	}

	
	}