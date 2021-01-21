package net.medavante.portal.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class RaterProfilePage extends BasePage {

	@FindBy(xpath="//strong[contains(text(),'2Events: Course (2Assets - en ru, ge it), Course (')]")
	private WebElement trainingWith2Assets;
	
	@FindBy(xpath="//strong[@class='group-heading pull-left link']")
	private WebElement trainingLink;
	
	@FindBy(xpath="//button[text()='Print Certificate']")
	private WebElement printCertificateBtn;
	
	@FindBy(xpath="//button[@class='btn btn-default ng-star-inserted'and text()=' Close']")
	private WebElement closeTrainingCertificateBtn;
	
	@FindBy(xpath="//button[@class='btn btn-new-active ng-star-inserted'and text()=' Close']")
	private WebElement closeCertificationcardBtn;
	
	@FindBy(xpath="//div[@class='subsection']//span[text()='Certifications']")
	private WebElement certificationCardSection;
		
	@FindBy(xpath="//button[text()='Edit profile']")
	private WebElement editProfileBtn;
	
	@FindBy(xpath="//button[text()=' Continue ']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-small btn-block ng-star-inserted' and text()=' Start ']")
	private WebElement startBtn;
	
	@FindBy(xpath="//strong[@class='label-title']")
	private WebElement trainingDescription;
	
	@FindBy(xpath="//div[contains(text(),'Please select training material to view:')]")
	private WebElement msgHeader;
	
	@FindBy(xpath="//span[text()='Name']")
	private WebElement nameLabel;
	
	@FindBy(xpath="(//label[@class='completed-date'])[1]")
	private WebElement completedDate;
	
	@FindBy(xpath="(//label[@class='result is-passed-result'])[1]")
	private WebElement trainingResult;
	
	@FindBy(xpath="(//div[@class='data-table-items'])[1]/div")
	private List<WebElement> assetlist;
	
	@FindBy(xpath="//button[@class='btn btn-default ng-star-inserted'and text()=' Cancel']")
	private WebElement cancelTrainingBtn;
	
	@FindBy(xpath="//div[@class='k-popup']/..")
	private WebElement creditLabelToolTip;
	
	@FindBy(xpath="//div[@class='w1']")
	private WebElement borderLayout;
	
	
	/**======================================================
	 * Constructor
	 * ======================================================
	 * @param driver
	 */
	
	
	public RaterProfilePage(WebDriver driver) {
		super(driver);
	}
	
	
	
	/**=============================================================
	 * Methods
	 *=============================================================
	 */
	
	/**
	 * close complete training Modal window
	 */
	public void closeCompletedTrainingModalWindow()
	{
		clickOn(cancelTrainingBtn);
		reportInfo();
	}
	
	/**
	 * close Training certificate
	 */
	public void closeTrainnigCertificate()
	{
		clickOn(closeTrainingCertificateBtn);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 * close certification card 
	 */
	public void closeCertificationCard()
	{
		clickOn(closeCertificationcardBtn);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 * verify site Rater Profile Is Displayed
	 */
	public void verifyRaterProfileIsDisplayed()
	{
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		Assert.assertTrue(isElementDisplayed(editProfileBtn));
		String expected="Profile - MedAvante Portal Application";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		reportInfo();
	}
	/**
	 * verify eLearning Training Displayed
	 * @param TrainingName
	 */
	public void verifyELearningTrainingDisplayed(String TrainingName)
	{
		WebElement trainingToBeVerified = driver.findElement(ByLocator("//strong[text()='"+TrainingName+"']/.."));
		Assert.assertTrue(isElementDisplayed(trainingToBeVerified));
		reportInfo();
	}
    /**
     *  proceed to start training 
     */
	public void proceedToStartTraining()
	{
		if (continueBtn.isDisplayed()) {
			clickOn(continueBtn);
		}
		else if(startBtn.isDisplayed()) {
			clickOn(startBtn);
		}
	}
	/**
	 *  click on completed traininig 
	 */
	public void clickToOpenOptionsForCompletedTraining()
	{
		waitUntillFinishProcessSpinnerDisable();
		clickOn(trainingLink);
		reportInfo();
	}
	/**
	 *  Print Certificate option Is Displayed & active
	 */
	public void verifyOptionToPrintCertificateDisplayedAndActive()
	{
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(printCertificateBtn.isDisplayed() && printCertificateBtn.isEnabled());
		reportInfo();
	}
	/**
	 *  Print Certificate option Is Not Displayed 
	 */
	public void verifyoptionToPrintCertificateNotDisplayed()
	{
		boolean flag=true;
		try {
			if (printCertificateBtn.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify Certification Card Is Displayed
	 */
	public void verifyCertificationCardDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementDisplayed(certificationCardSection));
		reportInfo();
	}
    /**
     *  
     *  Select training with 2 Assets 
     */
	public void selectTrainingWithTwoAssets()
	{
		clickOn(trainingWith2Assets);
		reportInfo();
	}
	/**
	 * Select Option To Print Screen 
	 */
	public void selectrOptiontoPrintCertificate()
	{
        clickOn(printCertificateBtn);
		reportInfo();
	}
	/**
	 *  Cancel Print Preview Screen by using Robot Class
	 * @throws AWTException
	 */
	public void cancelPrintOperation() throws AWTException
	{
       waitForPageLoaded();
		_normalWait(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	/**
	 *  verify frame across pages is displayed
	 */
	public void verifyFrameAcrossPagesDisplayed()
	{
        waitForPageLoaded();
        waitForElementPresent(borderLayout, 40);
		Assert.assertTrue(isElementDisplayed(borderLayout));
		reportInfo();
	}
	/***
	 *  verify Raters Details displayed
	 * @param ratername
	 * @param TrainingName
	 * @param completionDate
	 */
	public void verifyRaterDetailsDisplayed(String ratername,String TrainingName,String completionDate)
	{
	
		WebElement raterName = driver.findElement(ByLocator("//strong[text()='"+ratername+"']"));
		moveToElement(raterName);
		WebElement trainingToBeVerify = driver.findElement(ByLocator("//strong[contains(text(),'"+TrainingName+"')]"));
		moveToElement(trainingToBeVerify);
		List<WebElement> eventToVerify = driver.findElements(ByLocator("//div[@class='items-list-holder']//li"));
		boolean flag=true;
		if (eventToVerify.size()==0) {
			flag=false;	}
		Assert.assertTrue(flag);
		WebElement dateToBeVerified = driver.findElement(ByLocator("//time[@class='date' and text()='"+completionDate+"']"));
		moveToElement(dateToBeVerified);
		Assert.assertTrue(dateToBeVerified.isDisplayed()&&trainingToBeVerify.isDisplayed()&&raterName.isDisplayed());
	}
	/**
	 *  verify training is displayed as hyperLink
	 * @param TrainingName
	 */
	public void verifyTrainingIsDisplayedAsHyperlink(String TrainingName)
	{
		waitUntillFinishProcessSpinnerDisable();
		WebElement elementToBeVerified = 
		driver.findElement(ByLocator("//strong[@class='group-heading pull-left link' and contains(text(),'"+TrainingName+"')]/.."));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  verify training is not displayed as hyperLink
	 * @param TrainingName
	 */
	public void verifyTrainingIsDisplayed(String TrainingName)
	{
		waitUntillFinishProcessSpinnerDisable();
		WebElement elementToBeVerified = driver.findElement(ByLocator("//strong[contains(text(),'"+TrainingName+"')]/.."));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
		
	}
	/**
	 *  select any Training 
	 * @param TrainingName
	 */
	public void selectTraining(String TrainingName)
	{
		WebElement trainingToBeSelected = driver.findElement(ByLocator("//strong[contains(text(),'"+TrainingName+"')]"));
		clickOn(trainingToBeSelected);
		reportInfo();
	}
	/**
	 * verify training Details Modal window is Displayed
	 * @param TrainingName
	 */
	public void completedTrainingModalWindowIsDisplayed(String TrainingName)
	{
		waitUntillFinishProcessSpinnerDisable();
		WebElement elementToBeVerified = driver.findElement(ByLocator("//span[contains(text(),'"+TrainingName+"')]"));
		moveToElement(elementToBeVerified);
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  verify training name description field displayed
	 * @param fieldName
	 */
	public void verifyRespectiveFieldIsDisplayed(String fieldName,String expectedvalue)
	{
		WebElement elementToBeVerified= driver.findElement(ByLocator("//span[text()='"+fieldName+"']/following-sibling::div"));
		String actualValue=elementToBeVerified.getText();
		Assert.assertEquals(actualValue, expectedvalue);
		reportInfo();
	}
	/**
	 *  verify asset as hyperlink displayed
	 * @param assetName
	 */
	public void verifyAssetAsHyperLinkDisplayed(String assetName)
	{
		WebElement elementToBeVerified=driver.findElement(ByLocator("//span[text()='"+assetName+"'and @class='link-mode']"));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  hover over asset  name
	 * @param assetName
	 */
	public void hoverOverAssetToolTip(String assetName)
	{
		nameLabel.click();
		WebElement elementToBeVerified=driver.findElement(ByLocator("//span[text()='"+assetName+"'and @class='link-mode']"));
		moveToElement(elementToBeVerified);
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 * click on the asset hyperlink
	 * @param assetName
	 */
	public void clickOntheAssetName(String assetName)
	{
		nameLabel.click();
		WebElement elementToBeClicked=driver.findElement(ByLocator("//span[text()='"+assetName+"'and @class='link-mode']"));
        clickOn(elementToBeClicked);
        waitUntillFinishProcessSpinnerDisable();
        reportInfo();
	}
	/**
	 *   verify list of completed asset displayed
	 */
	public void verifyListOfCompletedAssetIsDisplayed()
	{
		boolean flag=true;
		if (assetlist.size()<2) {
			flag=false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * verify training Result is displayed
	 */
	public void verifyTrainingResultIsDisplayed()
	{
		moveToElement(trainingResult);
		Assert.assertTrue(isElementDisplayed(trainingResult));
		reportInfo();
	}
	/**
	 * verify training complete date is displayed
	 */
	public void verifyCompletedDateIsDisplayed()
	{
		moveToElement(completedDate);
		Assert.assertTrue(isElementDisplayed(completedDate));
		reportInfo();
	}
	/**
	 *  verify msg is displayed
	 */
	public void verifyMessageDisplayed()
	{
		moveToElement(msgHeader);
		Assert.assertTrue(isElementDisplayed(msgHeader));
		reportInfo();
	}
	/**
	 *  verify training name is displayed
	 * @param trainingName
	 */
	public void verifyTrainingNameIsDisplayed(String trainingName)
	{
		WebElement elementToBeVerified=
				driver.findElement(ByLocator("(//strong[@class='name' and contains(text(),'"+trainingName+"')])[1]"));
		moveToElement(elementToBeVerified);
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  verify training event no is displayed
	 */
	public void verifyTrainingEventNoIsDisplayed()
	{
		List<WebElement> elementsToBeVerified=driver.findElements(ByLocator("//span[@class='index']"));
		int count = elementsToBeVerified.size();
		for (int i = 1; i<=count; i++) {
			WebElement elementToBeVerified = driver.findElement(ByLocator("(//span[@class='index'])["+i+"]"));
			moveToElement(elementToBeVerified);
			Assert.assertTrue(isElementDisplayed(elementToBeVerified));
			reportInfo();
		
		   }
	}
	/**
	 *  verify asset with credit label Displayed
	 * @param assetName
	 */
	public void verifyAssetWithCreditLabel(String assetName)
	{
		WebElement assetToBeVerified = 
				driver.findElement(ByLocator("//span[text()='"+assetName+"']/../following::div[2]/span[text()='Credit']"));
		moveToElement(assetToBeVerified);
		Assert.assertTrue(isElementDisplayed(assetToBeVerified));
		reportInfo();
	}
	/**
	 *  hover mouse on icon next to credit 
	 * @param assetName
	 */
	public void hoverCursorOverRespectiveToolTip(String assetName)
	{
		WebElement assetToBeVerified = driver.findElement(
				ByLocator("//span[text()='"+assetName+"']/../following::div[2]//span[@class='icon-small icon-info']"));
		moveToElement(assetToBeVerified);
		Assert.assertTrue(isElementDisplayed(assetToBeVerified));
		reportInfo();
	}
	/**
	 *  icon next to credit label not displayed
	 * @param assetName
	 */
	public void verifyToolTipIconIsNoTDisplayedNearCreditLabel(String assetName)
	{
		boolean flag=true;
		try {
			WebElement assetToBeVerified = driver.findElement(
					ByLocator("//span[text()='"+assetName+"']/../following::div[2]//span[@class='icon-small icon-info']"));
			if (assetToBeVerified.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify option to view training tool tip displayed
	 * @param trainingName
	 */
	public void verifyOptionToViewTrainingToolTipIsDisplayed(String trainingName)
	{
		WebElement elementToBeVerified= driver.findElement(ByLocator(
			"//strong[@class='name'][ contains(text(),'"+trainingName+"')]/../following::span[@class='details-popup']"));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  click on the Training tool tip
	 * @param trainingName
	 */
	public void clickOnTheTrainingToolTipOption(String trainingName)
	{
		WebElement elementToBeVerified= 
			driver.findElement(ByLocator("(//strong[@class='name'][contains(text(),'"+trainingName+"')]/..)[1]/span/span")); 
		clickOn(elementToBeVerified);
		reportInfo();
	}
	/**
	 *  verify  respective options on training tool tip
	 * @param elementName
	*/
	public void verifyRespectiveOptionOnTrainingToolTip(String elementName)
	{
		WebElement elementToBeVerified=driver.findElement(ByLocator("//div[@class='k-popup']//strong[text()='"+elementName+"']"));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  verify  respective options on asset tool tip
	 * @param elementName
	*/
	public void verifyRespectiveOptionOnAssetToolTip(String elementName)
	{
		WebElement elementToBeVerified=
			driver.findElement(ByLocator("//div[contains(@class,'popup-box')]//strong[text()='"+elementName+"']"));
		Assert.assertTrue(isElementDisplayed(elementToBeVerified));
		reportInfo();
	}
	/**
	 *  verify training result
	 * @param assetName
	 * @param expectedTxt
	 */
	public void verifyTrainingResult(String assetName,String expectedTxt)
	{
		WebElement elementToBeVerified = 
		driver.findElement(ByLocator("//span[text()='"+assetName+"']/ancestor::div[contains(@class,'data-row ng')]//div[@class='note-column']/span"));
		String actualtxt = elementToBeVerified.getText().toUpperCase();
		String expectedText = expectedTxt.toUpperCase();
		Assert.assertEquals(actualtxt, expectedText);
	}
	/**
	 *  verify near credit label tool tip not displayed
	 */
	public void verifyToolTipNearCreditLabelNotDisplayed()
	{
		Assert.assertFalse(isElementDisplayed(creditLabelToolTip));
		reportInfo();
	}
}
