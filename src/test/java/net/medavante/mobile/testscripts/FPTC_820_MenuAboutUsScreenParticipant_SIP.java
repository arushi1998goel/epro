package net.medavante.mobile.testscripts;
import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_820_MenuAboutUsScreenParticipant_SIP extends BaseTest{
	
	
	@BeforeClass
	public void executionOn(){
		exceutionOn="MobileAndWebExecution"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_820_MenuAboutUsScreenParticipant_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		
		reportLog("Go to Portal Side to Create Subject");
		//dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage = loginPage.loginInApplication(FormUserName, Form_Password);

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
//		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
//				screeningNum);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.All_SiteText,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog(
				"1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		mobileLoginPage.clickOnSubmitButton();
		
		mobileLoginPage.clickOnContinueButton();
		
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);
		mobileLoginPage.clickOnAcceptBtn();

		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);

		mobileLoginPage.clickOnNextButton();

		mobileLoginPage.verifyInstructionMessageText(createIdentityQuestionMessage);
		mobileLoginPage.verifyChooseAQuestionDisplay();

		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);
		mobileLoginPage.clickOnNextButton();
		mobileLoginPage.clickOnContinueButton();		

		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-820  Test Case Name: To verify that About Us screen shall display general information English language about the Application and MedAvante-ProPhase. 
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-820_To verify that About Us screen shall display general information English language about the Application and MedAvante-ProPhase.", groups = { "Mobile" })
	public void FPTC_820_MenuAboutUsScreenParticipant() throws Exception {
		
		reportLog("1: Login to the application");
		mobileLoginPage.verifySignInScreenDisplayed();		
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);						
		
		reportLog("1.1:MobileView Tap to Side Menu Icon");
		dashborad.verifyHomePageDisplay();
		
		reportLog("1.2:MobileView select About Us");
		sideMenu=dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnAboutUs();
		sideMenu.verifyPageTitle("About Us");
		
		reportLog("1.3:MobileView verify the following details in English language for About Us screen: Information about MedAvante-ProPhase displayed in scrollable read-only mode ");
		sideMenu.verifyTextOnScreen("VIRGIL PRO");
		
		reportLog("1.4:MobileView Information about ePRO displayed in read-only mode");
		sideMenu.verifyTextOnScreen(inforAboutMedAvante);
		
		reportLog("1.5:MobileView Information about Legal Notice displayed in scrollable read-only");
		sideMenu.verifyTextOnScreen(legalNotice);
		
		reportLog("1.6:MobileView Information about Copyright displayed in read-only mode as a footer and the 'end year' is the current year ");
		sideMenu.verifyTextOnScreen(copyRights);
		
		reportLog("1.7:MobileView Information about Privacy Policy with web address is displayed at the bottom of the About Us ");
		sideMenu.verifyTextOnScreen(privacyPolicy);
		
		reportLog("MobileView Click on exit icon and close the application");
		sideMenu.clickOnBackIcon();
		sideMenu.exitApplication();
		clickOnConnectAppIcon();
		
	}
	
	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, screeningNum);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
			
			
		}else {
			Log.error(subjectName + " subject is not added for " + studyName+ " study.");
		}
	}


	
}
