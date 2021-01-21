package net.medavante.mobile.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.medavante.mobile.appium.core.MobileCoreFunctions;

public class MobileDashBoardPage extends MobileCoreFunctions {

	public MobileDashBoardPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='OK']")
	private MobileElement humBurgerMenuIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private MobileElement menuBackIcon;

	@AndroidFindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab'][1]")
	private MobileElement homeTab;

	@AndroidFindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab'][2]")
	private MobileElement questionnairesTab;

	@AndroidFindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab'][3]")
	private MobileElement messageTab;

	@AndroidFindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab'][4]")
	private static MobileElement logAnEventTab;

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

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Help & Tutorial']")
	private MobileElement helpTutorial;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About Us']")
	private MobileElement aboutUs;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Exit Application']")
	private MobileElement exitApplicationIcon;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @index='4']")
	private MobileElement addMessageIconWithNoData;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @index='3']")
	private MobileElement addMessageIcon;

	@AndroidFindBy(xpath = "(//android.view.View)[1]")
	private MobileElement replyMessageIcon;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Body']")
	private MobileElement messageBodyBox;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Subject']")
	private MobileElement messageSubjectBox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Send']")
	private MobileElement sendMessageButton;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout")
	private MobileElement confirmationDialog;

	@AndroidFindBy(xpath = "//*[@class='android.widget.FrameLayout']//android.widget.Button[@resource-id='android:id/button1']")
	private MobileElement yesOption;

	@AndroidFindBy(xpath = "//*[@class='android.widget.FrameLayout']//android.widget.Button[@resource-id='android:id/button2']")
	private MobileElement noOption;

	@AndroidFindBy(xpath = "//android.support.v7.widget.LinearLayoutCompat")
	private MobileElement deleteMessage;

	@AndroidFindBy(xpath = "//*[@class='android.widget.LinearLayout']")
	private List<MobileElement> messageList;

	@AndroidFindBy(xpath = "//*[@class='android.view.ViewGroup' and @index='3']//*[@class='android.widget.LinearLayout']")
	private List<MobileElement> homeDashBoardInformationList;

	@AndroidFindBy(xpath = "//*[contains(@class,'ActionBar$Tab') and @index='1']//*[@class='android.widget.FrameLayout']//*[@class='android.widget.TextView']")
	private MobileElement questionnairesTabAlertValue;

	@AndroidFindBy(xpath = "//*[contains(@class,'ActionBar$Tab') and @index='2']//*[@class='android.widget.FrameLayout']//*[@class='android.widget.TextView']")
	private MobileElement messageTabAlertValue;

	@AndroidFindBy(xpath = "//*[contains(@class,'ActionBar$Tab')]/*[@class='android.widget.TextView']")
	private List<MobileElement> dashboardTabList;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[1]//android.widget.TextView[2]")
	private MobileElement pendingQuestionnairesValue;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[2]//android.widget.TextView[2]")
	private MobileElement nextQuestionnairesValue;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[3]//android.widget.TextView[2]")
	private MobileElement unreadMessagesValue;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[4]//android.widget.TextView[2]")
	private MobileElement recentMessagesValue;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[5]//android.widget.TextView[2]")
	private MobileElement nextVisitValue;
	
	
	

	public void verifyUserLogin() {
		waitForElementToBecomeVisible(homeTab, 60);
		click(homeTab);
		_normalWait(DEFAULT_WAIT_ELEMENT);
		Assert.assertTrue(isElementPresent(homeTab));
		Assert.assertTrue(homeTab.isSelected());
		 capturescreen("Screenshot");

	}

	public void verifyHomePageDisplay() {
		_normalWait(globalWaitTime);
		waitForElementToBecomeVisible(homeTab, DEFAULT_WAIT_ELEMENT);
		Assert.assertTrue(isElementPresent(homeTab));

		 capturescreen("Screenshot");

	}

	public MobileSideMenuPage clickOnHumBergerMenuAndOpenLeftPanel() {
		click(humBurgerMenuIcon);
		MobileSideMenuPage sideMenu = new MobileSideMenuPage(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), sideMenu);
		 capturescreen("Screenshot");
		return sideMenu;

	}

	public MBMessagesPage clickOnMessageTab() {
		waitForElementToBecomeVisible(messageTab, globalWaitTime);
		click(messageTab);
		Assert.assertTrue(messageTab.isSelected());
		 capturescreen("Screenshot");
		
		MBMessagesPage message = new MBMessagesPage(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), message);
		 capturescreen("Screenshot");
		return message;
	}

	public MBQuestionnaires clickOnQuestionnairesTab() {
		waitForElementToBecomeVisible(questionnairesTab, globalWaitTime);
		click(questionnairesTab);
		click(questionnairesTab);
		Assert.assertTrue(questionnairesTab.isSelected());
		 capturescreen("Screenshot");
		MBQuestionnaires questionnaires = new MBQuestionnaires(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), questionnaires);
		 capturescreen("Screenshot");
		return questionnaires;

	}

	public MBLogAnEventPage clickOnLogAnEventTab() {
		click(logAnEventTab);
		Assert.assertTrue(logAnEventTab.isSelected());
		MBLogAnEventPage logAnEventPage = new MBLogAnEventPage(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), logAnEventPage);
		capturescreen("Screenshot");
		return logAnEventPage;
	}

	public void verifySideMenuOptions() {

		Assert.assertTrue(isElementPresent(myAccount) && isElementPresent(medications) && isElementPresent(mySchedule)
				&& isElementPresent(studyInformation) && isElementPresent(contacts) && isElementPresent(settings)
				&& isElementPresent(helpTutorial) && isElementPresent(aboutUs));
	}

	public void verifyTopMenuOptions() {
		Assert.assertTrue(isElementPresent(homeTab) && isElementPresent(questionnairesTab)
				&& isElementPresent(messageTab) && isElementPresent(logAnEventTab));

	}

	public void verifyTopMenuOptionsQuestionnaireOnlyDiplay() {
		Assert.assertTrue(isElementPresent(questionnairesTab));

	}

	public void verifyOtherTabsAreNotDisplaying() {
		Assert.assertFalse(
				isElementPresent(homeTab) && isElementPresent(messageTab) && isElementPresent(logAnEventTab));

	}

	public void clickOnHomeTab() {
		waitForElementToBecomeVisible(homeTab, globalWaitTime);
		click(homeTab);
		Assert.assertTrue(homeTab.isSelected());

	}

	public void clickOnAboutUs() {
		click(aboutUs);
		Assert.assertTrue(aboutUs.isSelected());
	}

	public void clickOnMyAccount() {
		click(myAccount);
		Assert.assertTrue(myAccount.isSelected());
	}

	public void clickOnSettings() {
		click(settings);
		Assert.assertTrue(settings.isSelected());
	}

	public void clickOnMedications() {
		click(medications);
		Assert.assertTrue(medications.isSelected());
	}

	public void clickOnExitApplication() {
		_normalWait(globalWaitTime);
		waitForElementToBecomeVisible(exitApplicationIcon, DEFAULT_WAIT_4_ELEMENT);
		click(exitApplicationIcon);
		 capturescreen("Screenshot");
	//	super.clickToMinimizeConnect();
	}

	public void verifyPageTitle(String pageTitle) {
		_normalWait(globalWaitTime);
		WebElement titleText = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", pageTitle)));
		Assert.assertTrue(isElementPresent(titleText));

	}

	public void verifyTextOnScreen(String text) {
		WebElement messageText = mobileDriver
				.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", text)));
		Assert.assertTrue(messageText.isDisplayed());

	}

	public void verifyAddMessageButtonDisplay() {
		waitForElementToBecomeVisible(addMessageIcon, 30);
		Assert.assertTrue(addMessageIcon.isDisplayed());
		 capturescreen("Screenshot");
	}

	public void verifyAddMessageButtonWhileNoDataAddedDisplay() {
		waitForElementToBecomeVisible(addMessageIconWithNoData, 30);
		Assert.assertTrue(addMessageIconWithNoData.isDisplayed());
		 capturescreen("Screenshot");
	}

	public void clickOnAddMessageButtonWhenNoDataPresent() {
		click(addMessageIconWithNoData);
		_normalWait(globalWaitTime);
	}

	public void clickOnAddMessageButton() {

		// longPress(addMessageIcon);
		// click(addMessageIcon);

		click(addMessageIcon);
		waitForElementToBecomeVisible(messageBodyBox, globalWaitTime);

	}

	public void clickOnReplyMessageButton() {
		click(replyMessageIcon);

	}

	public void enterMessageSubjectText(String subject) {
		waitForElementToBecomeVisible(messageSubjectBox, 30);
		setText(messageSubjectBox, subject);

	}

	public void enterMessageBodyText(String message) {
		waitForElementToBecomeVisible(messageBodyBox, 30);
		setText(messageBodyBox, message);

	}

	public void clickOnSendMessageButton() {

		click(sendMessageButton);
		click(sendMessageButton);
		_normalWait(globalWaitTime);
	}

	public void clickOnBackIcon() {
		click(menuBackIcon);
		_normalWait(30);
		waitForElementToBecomeVisible(addMessageIcon, globalWaitTime);
	}

	/*
	 * public void clickOnBackIcon(){
	 * waitForElementToBecomeVisible(menuBackIcon, 30); click(menuBackIcon);
	 * 
	 * }
	 */

	public void verifyConfirmationDialogDisplayWithYesAndNoOptions(String title) {
		_normalWait(globalWaitTime);
		WebElement titletext = mobileDriver.findElement(By.xpath(
				String.format("/hierarchy/android.widget.FrameLayout//android.widget.TextView[@text='%s']", title)));
		Assert.assertTrue(isElementPresent(titletext) && isElementPresent(yesOption) && isElementPresent(noOption));

	}

	public void verifyWarningMessage(String title) {
		_normalWait(globalWaitTime);
		WebElement titletext = mobileDriver.findElement(By.xpath(
				String.format("/hierarchy/android.widget.FrameLayout//android.widget.TextView[@text='%s']", title)));
		Assert.assertTrue(isElementPresent(titletext));

	}

	public void clickOnNoOption() {
		click(noOption);
		_normalWait(30);
	}

	public void clickOnYesOption() {
		click(yesOption);
		_normalWait(30);
	}

	public void selectMessage(String message) {

	}

	public void verifyDeleteIconDisplay() {
		Assert.assertTrue(isElementPresent(deleteMessage));
		 capturescreen("Screenshot");
	}

	public void clickOnDeleteMessageButton() {
		click(deleteMessage);
	}

	public void verifyPresenceOfMessageInMessageList() {
		boolean flag = false;
		if (messageList.size() > 0) {
			Assert.assertTrue("Message list is Present", flag);
		}
	}

	public void selectMessageFromList(String message) {

		WebElement messageText = mobileDriver.findElement(
				By.xpath(String.format("//*[@class='android.widget.LinearLayout']//*[@text='%s']", message)));
		Assert.assertTrue(messageText.isDisplayed());
		messageText.click();
		waitForElementToBecomeVisible(deleteMessage, globalWaitTime);
	}

	public void verifyHomeDashbaordValues(String detailToBeVerify, String valueToBeVerify) {
		boolean flag = false;
		for (MobileElement element : homeDashBoardInformationList) {
			if (element.findElement(By.xpath(".//*[@class='android.widget.TextView'][1]")).getText().trim()
					.contains(detailToBeVerify)) {

				flag = element.findElement(By.xpath(".//*[@class='android.widget.TextView'][2]")).getText()
						.contains(valueToBeVerify);
				break;

			}
		}

		Assert.assertTrue(flag);
		 capturescreen("Screenshot");
	}

	/* Questionnaires Tab Alert Showing Correct Value */

	public void verifyQuestionnairesTabAlertShowingCorrectValue(String value) {
		Assert.assertTrue(getText(questionnairesTabAlertValue).trim().contains(value));
		 capturescreen("Screenshot");
	}

	/* Return Questionnaires Values From tab Alert */

	public String getQuestionnairesValue()

	{
		_normalWait(3000);
		String value = getText(questionnairesTabAlertValue).trim();
		return value;
	}

	/* Message Tab Alert Showing Correct Value */

	public void verifyMessageTabAlertShowingCorrectValue(String value) {
		Assert.assertTrue(getText(messageTabAlertValue).trim().contains(value));
		 capturescreen("Screenshot");
		
	}

	/* Return Message Values From tab Alert */

	public String getMessageValue()

	{
		_normalWait(3000);
		String value = getText(messageTabAlertValue).trim();
		return value;
	}

	/* Click on tabs on home dashboard */
	@SuppressWarnings("unchecked")
	public <T> T clickOnHomeDashBoardMenuTabs(final Class<?> className, String selectValue)
			throws ClassNotFoundException {
		_normalWait(3000);
		for (MobileElement tabValue : dashboardTabList) {
			if (getText(tabValue).contains(selectValue)) {
				click(tabValue);
				break;

			}
		}

		return (T) PageFactory.initElements(getApiumDriver(), Class.forName("net.medavante.mobile.pages." + className));
		// return (T) PageFactory.initElements(mobileDriver,
		// Class.forName(className.toString()).getClassLoader().getClass());

	}
	
	/* Return Questionnaires Values From Home screen */

	public String getPendingQuestionnairesValueFromHomeScreen()

	{
		_normalWait(3000);
		String value = getText(pendingQuestionnairesValue);
		return value;
	}
	
	/* Return Questionnaires Values From Home screen */

	public String getNextQuestionnairesValueFromHomeScreen()

	{
		_normalWait(3000);
		String value = getText(nextQuestionnairesValue);
		return value;
	}
	
	

	/* Return unread Messages Values From Home screen */

	public String getUnreadMessagesValueFromHomeScreen()

	{
		_normalWait(3000);
		String value = getText(unreadMessagesValue);
		return value;
	}
	

	/* Return recent Messages Values From Home screen */

	public String getRecentMessagesValueFromHomeScreen()

	{
		_normalWait(3000);
		String value = getText(recentMessagesValue);
		return value;
	}
	

	/* Return next Visit Values From Home screen */

	public String getNextVisitValueFromHomeScreen()

	{
		_normalWait(3000);
		String value = getText(nextVisitValue);
		return value;
	}
}
