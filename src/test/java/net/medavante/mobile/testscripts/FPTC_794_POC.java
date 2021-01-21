package net.medavante.mobile.testscripts;

import java.net.URL;
import java.util.Properties;

import org.jfree.util.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_794_POC extends BaseTest {

	private String subjectName = "AutomationEpro" + generateRandomString(5), questionnairesValue;

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_794_POC(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("EPROQuestionnairesStudy");
		visitName = properties.getProperty("EPROMandatoryVisit");

		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		System.out.println(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("Completion of first mandatory visit");
		subjectDetailPage.selectRaterForCalenderVisitDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,
				Constants.ATAssignedRater_10);

		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();	
		
		/* Subject Created Successfully */
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-794 Test Case Name: Questionnaires Navigation
	 * ====================================================================================================================
	 * 
	 * 
	 */

//	@Test(description = "FP-TC-794_QuestionnairesNavigation", groups = { "Mobile" })
//
//	public void FPTC_794_verifyQuestionnairesNavigation() throws Exception {
//
//		reportLog("1.1: As a Participant logged into the application");
//		mobileLoginPage = androidSetUp();
//
//		reportLog(
//				"1.2:MobileView Application launch and verify Register screen with instruction message,register the subject");
//		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
//		mobileLoginPage.configurationForRegisterTheSubject(registrationCode);
//
//		reportLog("1.5:MobileView Login with configured Pin");
//		mobileLoginPage.verifySignInScreenDisplayed();
//		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
//
//		reportLog("1.6:MobileView Check top menu configured with home,questionnaries,message,log an event");
//		dashborad.verifyUserLogin();
//		dashborad.verifyTopMenuOptions();
//
//		reportLog(
//				"2.1:MobileView Not Started Questionnaires Home Page Pending Questionnaires fields shall display the right value");
//		questionnairesValue = dashborad.getQuestionnairesValue();
//		dashborad.verifyHomeDashbaordValues("Pending", questionnairesValue);
//
//		reportLog("2.2:MobileView Questionnaires Tab Alert shall display the right value");
//		dashborad.verifyQuestionnairesTabAlertShowingCorrectValue(questionnairesValue);
//
//		reportLog("3.1:MobileView Tap to Questionnaires Tab");
//		questionnairesPage = dashborad.clickOnQuestionnairesTab();
//
//		reportLog(
//				"3.2:MobileView Questionnaires List shall be displayed for user listing all configured pending questionnaires.");
//		questionnairesPage.verifyTodayQuestionnairesListPresent();
//
//		reportLog("3.3:MobileView Verify the Percentage for Not Started Questionnaire");
//		questionnairesPage.verifyPercentageOfNotStartedQuestion(Constants.MForm, Constants.ZeroProgressPercentage);
//
//		reportLog("4.1:	MobileView Tap to Quest.1 and verify the following:");
//		questionnairesPage.selectQuestionForms(Constants.MForm);
//
//		reportLog("4.2:MobileView Starting Page with Starting Message (Questionnaire description) shown");
//		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);
//
//		reportLog("4.3:MobileView questions can be answered ");
//		questionnairesPage.clickOnStartQuestion();
//		questionnairesPage.verifyQuestionCanBeAnswered();
//
//		reportLog("4.4 :MobileView progress bar is displayed in Design 1 ");
//		questionnairesPage.verifyProgressBarShowing();
//		questionnairesPage.answerQuestionContaingTextBox(Constants.QuestionnairesAnswer);
//		questionnairesPage.tabOnProgressBarNextButton();
//
//		reportLog("4.5:MobileView Progress on questionnaire progress bar shall be visible for the user.");
//		questionnairesPage.verifyProgressBarShowing();
//
//		reportLog("4.6:MobileView Tap to Back Button and Verify the Percentage for questionnaire");
//		questionnairesPage.navigateBack();
//		questionnairesPage.clickOnYesOption();
//
//		reportLog("4.7:MobileView Percentage shall be displaying the right value. (NOT 0%)");
//		questionnairesPage = dashborad.clickOnQuestionnairesTab();
//		questionnairesPage.verifyPercentageOfNotStartedQuestion(Constants.MForm, Constants.Second_Percentage);
//
//		reportLog("4.8 :MobileView previous questions can be edited");
//		questionnairesPage.selectQuestionForms(Constants.MForm);
//		questionnairesPage.tabOnProgressBarBackwardButton();
//		questionnairesPage.verifyPreviousQuestionEditable();
//
//		reportLog(
//				"4.9:MobileView Next Page Button and Back Page Button forward and backward navigation works as suppose to Then tap to Back Button and confirm the confirmation message");
//		questionnairesPage.tabOnProgressBarNextButton();
//		questionnairesPage.tabOnProgressBarBackwardButton();
//		questionnairesPage.clickOnBackIcon();
//		questionnairesPage.verifyConfirmationDialogDisplayWithYesAndNoOptions();
//		questionnairesPage.clickOnYesOption();
//
//		reportLog("5.1:MobileView	Tap to Quest.2 and verify the following:");
//		questionnairesPage.selectQuestionForms(Constants.Mobile_MForm2);
//
//		reportLog("5.2:MobileView Starting Page without Starting Message shown");
//		questionnairesPage.verifyQuestionStartingPageWithDescriptionIsNotShowing();
//
//		reportLog("5.3:MobileView questions can be answered ");
//		questionnairesPage.verifyQuestionCanBeAnswered();
//
//		reportLog("5.4:MobileView progress bar is NOT displayed");
//		questionnairesPage.verifyProgressBarNotShowing();
//		questionnairesPage.filltheQuestionContainingCheckBox();
//		questionnairesPage.tabOnProgressBarNextButton();
//
//		reportLog("5.5 :MobileView previous questions can be viewed not edited");
//		questionnairesPage.tabOnProgressBarBackwardButton();
//		questionnairesPage.verifyPreviousQuestionNotEditable();
//
//		reportLog(
//				"5.6:MobileView forward and backward navigation works as suppose to Then tap to Back Button and confirm the confirmation message.");
//		questionnairesPage.tabOnProgressBarNextButton();
//		questionnairesPage.tabOnProgressBarBackwardButton();
//		questionnairesPage.clickOnBackIcon();
//		questionnairesPage.verifyConfirmationDialogDisplayWithYesAndNoOptions();
//		questionnairesPage.clickOnYesOption();
//
//		reportLog("6.1:MobileView	Tap to Quest.3 and verify the following:");
//		questionnairesPage.selectQuestionForms(Constants.Mat_Mob);
//
//		reportLog("6.2:MobileView Starting Page without Starting Message shown");
//		questionnairesPage.verifyQuestionStartingPageWithDescriptionIsNotShowing();
//
//		reportLog("6.3:MobileView questions can be answered ");
//		questionnairesPage.verifyQuestionCanBeAnswered();
//
//		reportLog("6.4 :MobileView progress bar is displayed in Design 3 - empty");
//		questionnairesPage.verifyProgressBarNotShowing();
//
//		reportLog("6.5:MobileView Backward Navigation (Back Page Button) Disabled  ");
//		questionnairesPage.verifyProgressBarBackwardNavigationIsDisabled();
//
//		reportLog("6.6:MobileView next questions can NOT be viewed (without providing an answer) ");
//		questionnairesPage.verifyNextQuestionNotViewedWithoutProvidingAnswer();
//
//		reportLog("6.7:MobileView previous questions can NOT be edited");
//		questionnairesPage.filltheQuestionContainingCheckBox();
//		questionnairesPage.tabOnProgressBarNextButton();
//		questionnairesPage.verifyProgressBarBackwardNavigationIsDisabled();
//
//		reportLog("6.8:MobileView previous questions can NOT be viewed and  previous questions can NOT be edited  ");
//		questionnairesPage.verifyPreviousQuestionCantViewedAndEdited();
//		questionnairesPage.clickOnBackIcon();
//		questionnairesPage.clickOnYesOption();
//
//		reportLog(
//				"7.0:MobileView	Log in, open the same Questionnaire and verify that Questionnaire state is saved properly");
//		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
//		sideMenu.clickOnExitApplication();
//		clickOnConnectAppIcon();
//		
//		reportLog("7.1: MobileView Launch the application again");
//		mobileLoginPage = androidSetUp();
//
//		reportLog("MobileView Enter the Mobile PIN");
//		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
//
//		reportLog("7.2:MobileView	Answer the currently displayed question and move to the next one.");
//		questionnairesPage = dashborad.clickOnQuestionnairesTab();
//		questionnairesPage.selectQuestionForms(Constants.Mat_Mob1);
//		questionnairesPage.filltheQuestionContainingCheckBox();
//		questionnairesPage.tabOnProgressBarNextButton();
//
//		reportLog("MobileView Exit Application");
//		questionnairesPage.clickOnBackIcon();
//		questionnairesPage.clickOnYesOption();
//		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
//		sideMenu.clickOnExitApplication();
//		sideMenu.verifyApplicationExit();
//		clickOnConnectAppIcon();
//	}
	
	@Test(description = "FP-TC-794_QuestionnairesNavigation", groups = { "Mobile" })
	public void testCal() throws Exception {
		WebDriver driver;

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "8.1.0"); 
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("platformName","Android");
	 
	   
	   capabilities.setCapability("appPackage", "com.android.calculator2");
	// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
	//Create RemoteWebDriver instance and connect to the Appium server
	 //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
	 //  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		  driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	   //locate the Text on the calculator by using By.name()
		Thread.sleep(5000);
		WebElement two= driver.findElement(MobileBy.id("com.android.calculator2:id/digit_7"));
	  // WebElement two=driver.findElement(MobileBy.xpath("//android.widget.LinearLayout[@content-desc=\"Numbers and basic operations\"]/android.view.ViewGroup[1]/android.widget.Button[3]"));
	   two.click();
		Thread.sleep(5000);

	   WebElement plus=driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='plus']"));
	   plus.click();
	   WebElement four=driver.findElement(MobileBy.xpath("//android.widget.LinearLayout[@content-desc=\"Numbers and basic operations\"]/android.view.ViewGroup[1]/android.widget.Button[4]"));
	   four.click();
		Thread.sleep(5000);

	   WebElement equalTo=driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='equals']"));
	   equalTo.click();
	   
	   //locate the edit box of the calculator by using By.tagName()
	   WebElement results=driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView"));
		//Check the calculated value on the edit box
	   
	   if(results.getText().equals("11"))
       {
           System.out.println("Test Passed...");
       }
       else
       {
           System.out.println("Test Failed...");
       }

	}


	@AfterMethod
	public void deactivateUser(ITestResult result) throws InterruptedException {

		if (ITestResult.SUCCESS == result.getStatus()) {

			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
		} else {
			Log.error("Deactivation Not needed");
		}
	}
}
