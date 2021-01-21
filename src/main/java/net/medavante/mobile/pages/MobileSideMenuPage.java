package net.medavante.mobile.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.ElementOption;
import net.medavante.mobile.appium.core.MobileCoreFunctions;
import net.medavante.portal.utilities.Constants;

public class MobileSideMenuPage extends MobileCoreFunctions {

	public MobileSideMenuPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='OK']")
	private MobileElement humBurgerMenuIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private MobileElement menuBackIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Participant Version']")
	private MobileElement participantVersionText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Account']")
	private MobileElement myAccount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medications']")
	private MobileElement medications;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Schedule']")
	private MobileElement mySchedule;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Study Information']")
	private MobileElement studyInformation;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	private MobileElement contacts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	private MobileElement settings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Help & Tutorials']")
	private MobileElement helpTutorial;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About Us']")
	private MobileElement aboutUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Exit Application']")
	private MobileElement exitApplicationIcon;

	@AndroidFindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab'][1]")
	private MobileElement homeTab;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @index='0']")
	private MobileElement addMedicationIcon;

	@AndroidFindBy(xpath = "(//*[@class='android.view.View' and @index='0'])[2]")
	private MobileElement addSecondMedicationIcon;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @content-desc='CreateMedicationMenuItem']")
	private MobileElement createMedicationMenuItem;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	private MobileElement nameEditBox;

	@AndroidFindBy(xpath = "//android.support.v7.widget.LinearLayoutCompat")
	private MobileElement deleteIcon;

	@AndroidFindBy(xpath = "//*[@class='android.widget.LinearLayout']")
	private List<MobileElement> messageList;

	@AndroidFindBy(id = "android:id/content")
	private MobileElement confirmationDialog;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	private MobileElement okOption;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button2']")
	private MobileElement cancelOption;

	@AndroidFindBy(xpath = "//android.widget.EditText//following-sibling::android.widget.Button")
	private MobileElement elementLast;

	@AndroidFindBy(xpath = "//android.widget.EditText//preceding-sibling::android.widget.Button")
	private MobileElement elementFirst;

	@AndroidFindBy(xpath = "//android.widget.EditText//following-sibling::android.widget.Button")
	private List<MobileElement> lastElement;

	@AndroidFindBy(xpath = "//*[@class='android.view.ViewGroup']")
	private List<MobileElement> medicationList;

	@AndroidFindBy(xpath = "//*[@class='android.widget.ImageView']")
	private MobileElement qrCodeImage;

	@AndroidFindBy(xpath = "//android.widget.NumberPicker")
	private List<MobileElement> numberPicker;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Pick a')]")
	private MobileElement pickAdateBox;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private MobileElement okNumberPickerButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[2]//android.widget.TextView[2]")
	private MobileElement deviceIDValue;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[2]//android.widget.TextView[10]")
	private MobileElement appVersion;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[2]//android.widget.TextView[12]")
	private MobileElement deviceModel;

	public void verifyHomePageDisplay() {
		waitForElementToBecomeVisible(homeTab, DEFAULT_WAIT_2_ELEMENT);
		Assert.assertTrue(isElementPresent(homeTab));

	}

	public void clickOnAddIcon() {
      _normalWait(DEFAULT_WAIT_4_ELEMENT);
		waitForElementToBecomeVisible(addMedicationIcon, 30);
		click(addMedicationIcon);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void clickOnBackIcon() {
		 _normalWait(DEFAULT_WAIT_4_ELEMENT);
		click(menuBackIcon);
		_normalWait(DEFAULT_WAIT_ELEMENT);
		verifyPageIsDisplayAndCaptureTheScreenShot();

	}

	public void verifyPageTitle(String pageTitle) {
		WebElement questionText = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", pageTitle)));
		Assert.assertTrue(isElementPresent(questionText));
		verifyPageIsDisplayAndCaptureTheScreenShot();

	}

	public void verifySideMenuOptions() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		Assert.assertTrue( myAccount.isEnabled() && medications.isEnabled() && mySchedule.isEnabled()
				&& studyInformation.isEnabled() && contacts.isEnabled() && settings.isEnabled()
				&& helpTutorial.isEnabled() && aboutUs.isEnabled());
	}

	public void clickOnAboutUs() {
		click(aboutUs);
		Assert.assertTrue(aboutUs.isEnabled());
	}

	public void clickOnMyAccount() {
		click(myAccount);
		Assert.assertTrue(myAccount.isEnabled());
	}

	public void clickOnStudyInformation() {
		click(studyInformation);
		Assert.assertTrue(studyInformation.isEnabled());
	}

	public void clickOnSettings() {
		click(settings);
		Assert.assertTrue(settings.isEnabled());
	}

	public void clickOnMedications() {
		click(medications);
		Assert.assertTrue(medications.isEnabled());
		verifyPageIsDisplayAndCaptureTheScreenShot();
		waitForElementToBecomeVisible(addMedicationIcon, globalWaitTime);

	}

	public void clickOnExitApplication() throws Exception {
		waitForElementToBecomeVisible(exitApplicationIcon, DEFAULT_WAIT_ELEMENT);
		click(exitApplicationIcon);
		verifyPageIsDisplayAndCaptureTheScreenShot();
				
	}

	/* Verify user exit with application */
	public void verifyApplicationExit() {
		_normalWait(DEFAULT_WAIT_ELEMENT);
		verifyPageIsDisplayAndCaptureTheScreenShot();
				
	}

	public void enterNameAndClickOnSave(String name) {
		setText(nameEditBox, name);
		click(createMedicationMenuItem);
		 _normalWait(DEFAULT_WAIT_4_ELEMENT);
	}

	public void clickOnSaveMedicationIcon() {
		click(createMedicationMenuItem);
		 _normalWait(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyCreateMedicationIconDisplay() {
		Assert.assertTrue(createMedicationMenuItem.isDisplayed());
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void enterMedicationName(String string) {
		enterNameAndClickOnSave(string);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void verifyTextOnScreen(String text) {
		WebElement messageText = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", text)));
		Assert.assertTrue(messageText.isDisplayed());
		verifyPageIsDisplayAndCaptureTheScreenShot();

	}

	public void verifyAddMedicationButtonDisplay() {
		Assert.assertTrue(addMedicationIcon.isDisplayed());
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void createMedication(String string) {
		clickOnAddIcon();
		enterNameAndClickOnSave(string);
		_normalWait(DEFAULT_WAIT_2_ELEMENT);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void createSecondMedication(String string) {
		waitForElementToBecomeVisible(addSecondMedicationIcon, 30);
		click(addSecondMedicationIcon);
		enterNameAndClickOnSave(string);
		_normalWait(DEFAULT_WAIT_2_ELEMENT);
		verifyPageIsDisplayAndCaptureTheScreenShot();

	}

	public void exitApplication() {
		click(humBurgerMenuIcon);
		waitForElementToBecomeVisible(exitApplicationIcon, DEFAULT_WAIT_ELEMENT);
		click(exitApplicationIcon);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void selectMedicationListItem(String name) {
		WebElement medicationName = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", name)));
		click(medicationName);
		verifyPageIsDisplayAndCaptureTheScreenShot();

	}

	public void clickOnDeleteIcon() {
		click(deleteIcon);
		_normalWait(30);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void selectConfirmationOption(String option) {
		WebElement deleteOption = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.Button[@text='%s']", option)));
		click(deleteOption);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void verifyPresenceOfMedicationsInMedicatonList() {
		boolean flag = false;
		if (messageList.size() > 0) {
			Assert.assertTrue(flag, "Medication list is Present");
		}
	}

	public void selectMedicationFromList(String medication) {

		WebElement medicationName = mobileDriver.findElement(
				By.xpath(String.format("//*[@class='android.widget.LinearLayout']//*[@text='%s']", medication)));
		Assert.assertTrue(medicationName.isDisplayed());
		medicationName.click();
		waitForElementToBecomeVisible(deleteIcon, globalWaitTime);
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}

	public void verifyConfirmationDialogDisplayWithOkAndCancelOptions() {
		_normalWait(30);
		Assert.assertTrue(
				isElementPresent(confirmationDialog) && isElementPresent(cancelOption) && isElementPresent(okOption));

	}

	public void clickOnOKOption() {
		click(okOption);
		_normalWait(60);
	}

	public void clickOnCancelOption() {
		click(cancelOption);
		_normalWait(60);
	}

	/* Fill Medications Data */
	public void FillTextAndDropDownPopUpValues(String fieldIndex, String Value) {
		MobileElement medictaionDetails = getApiumDriver().findElement(
				By.xpath("//android.view.ViewGroup[@index='" + fieldIndex + "']//android.widget.EditText"));

		MobileElement element = getApiumDriver().findElement(By.xpath("//android.widget.EditText"));
		medictaionDetails.click();
		if (numberPicker.size() > 0) {
			while (getApiumDriver().findElements(By.xpath("//android.widget.EditText[@text='" + Value + "']"))
					.size() == 0) {
				if (lastElement.size() > 0) {
					TouchAction action = new TouchAction(mobileDriver);
					action.longPress(ElementOption.element(elementLast))
							.moveTo(ElementOption.element(element)).release().perform();
					_normalWait(3000);
				}

			}
			click(okOption);
		}

		else {
			medictaionDetails.sendKeys(Value);
		}
	}

	/* Fill All Details */
	public void fillAllDetailsForMedication() {

		setText(nameEditBox, Constants.WeekelyMedicationName);
		FillTextAndDropDownPopUpValues("4", "150");
		verifyPageIsDisplayAndCaptureTheScreenShot();
		FillTextAndDropDownPopUpValues("6", "mg");
		verifyPageIsDisplayAndCaptureTheScreenShot();
		FillTextAndDropDownPopUpValues("8", "orally");
		verifyPageIsDisplayAndCaptureTheScreenShot();
		FillTextAndDropDownPopUpValues("11", "frequency");
		verifyPageIsDisplayAndCaptureTheScreenShot();
		FillTextAndDropDownPopUpValues("12", "1");
		verifyPageIsDisplayAndCaptureTheScreenShot();
		String current = currentOnlyDate();
		pickAdateFromCalender(current);
		FillTextAndDropDownPopUpValues("18", "end date");
		String dateToselect = getAfterSevenDayDateFromCurrentDate();
		pickAdateFromCalender(dateToselect);
		verifyPageIsDisplayAndCaptureTheScreenShot();
		click(createMedicationMenuItem);
		verifyPageIsDisplayAndCaptureTheScreenShot();
		clickOnBackIcon();

	}

	public void scrollTillTheElement(List<MobileElement> desiredElement) {
		while (desiredElement.size() == 0) {
			TouchAction action = new TouchAction(mobileDriver);
			action.longPress(ElementOption.element(elementLast)).release().perform();
		}
	}

	/* Pick a date from calender */
	public void pickAdateFromCalender(String dateTobeSelected) {
		pickAdateBox.click();
		getApiumDriver().findElement(By.xpath("//android.view.View[@text='" + dateTobeSelected + "']")).click();
		click(okOption);

	}

	/* Get date after seven days to curent day */
	public String getAfterSevenDayDateFromCurrentDate() {
		String currentDate = currentOnlyDate();
		int dateAfterSevenDay = Integer.parseInt(currentDate) + 7;
		String date = Integer.toString(dateAfterSevenDay);
		return date;
	}

	public void verifyQRCodeOnScreen() {
		Assert.assertTrue(qrCodeImage.isDisplayed());
		
	}
	
	/* Return Device Values From My Account screen */

	public String getDeviceIDValue()

	{
		_normalWait(3000);
		String value = getText(deviceIDValue);
		return value;
	}
	
	
	/* Return App Vetsion From My Account screen */

	public String getAppVersion()

	{
		_normalWait(3000);
		String value = getText(appVersion);
		return value;
	}
	
	/* Return device model From My Account screen */

	public String getDeviceModel()

	{
		_normalWait(3000);
		String value = getText(deviceModel);
		return value;
	}
	
	/**
	 * Verify Avatar And Version Label display
	 */
	public void verifyAvatarAndVersionLabelIsDisplay() {
		Assert.assertTrue(participantVersionText.isDisplayed());
		verifyPageIsDisplayAndCaptureTheScreenShot();
	}
}