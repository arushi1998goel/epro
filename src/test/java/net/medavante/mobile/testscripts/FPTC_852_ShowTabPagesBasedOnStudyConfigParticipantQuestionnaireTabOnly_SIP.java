
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
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_852_ShowTabPagesBasedOnStudyConfigParticipantQuestionnaireTabOnly_SIP extends BaseTest {
	private String subjectNameParticipant = "Subject_Part" + generateRandomString(3),
			subjectNameObserver = "Subject_Obs" + generateRandomString(3), participantStudyName, observerStudyName,
			registrationCodeObserver, registrationCodeParticipant;

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_852_ShowTabPagesBasedOnStudyConfigParticipantQuestionnaireTabOnly_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		participantStudyName = properties.getProperty("EPROQuestionnairesParticipantStudy");
		observerStudyName = properties.getProperty("EPROQuestionnairesObserverStudy");
		visitName = properties.getProperty("EPROMandatoryVisit");

		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(participantStudyName, Constants.ATAssignedRater_10,
				subjectNameParticipant);
		System.out.println(subjectNameParticipant);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		subjectDetailPage.clickOnInitiateVisitIcon();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("Get the Registration Code From The Subject");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNameParticipant);

		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCodeParticipant = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

		// observer

		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(observerStudyName, Constants.ATAssignedRater_10,
				subjectNameObserver);
		System.out.println(subjectNameObserver);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		subjectDetailPage.clickOnInitiateVisitIcon();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("Get the Registration Code From The Subject");
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNameObserver);

		studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

		loginPage.logoutApplication();
		/* Subject Created Successfully */

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-852 Test Case Name: To verify that device tabs are
	 * displayed based on study config_participant_questionnaire only and for
	 * Observer_Home_questionnaire_Messages tabs *
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-852_To verify that device tabs are displayed based on study config_participant_questionnaire only and for Observer_Home_questionnaire_Messages tabs", groups = {
			"Mobile" })

	public void FPTC_852_ShowTabPagesBasedOnStudyConfigParticipantQuestionnaireTabOnly() throws Exception {

		reportLog("1: MobileView Launch the application");
		mobileLoginPage = androidSetUp();

		reportLog("2: Enter valid pin in the sign in page.");
		dashborad = mobileLoginPage.scanCodeOrEnterPin(registrationCodeParticipant,Mobile_Pin);

		reportLog(
				"2:MobileView Application is successfully launched and Home Page is displayed as per study configuration.");
		dashborad.verifyUserLogin();

		reportLog("2: Questionnaire tab shall be displayed.");
		dashborad.verifyTopMenuOptionsQuestionnaireOnlyDiplay();

		reportLog(
				"3: check the top menu tabs, For the logged in participant, Only questionnaire tab shall be displayed ");
		dashborad.verifyTopMenuOptionsQuestionnaireOnlyDiplay();

		reportLog("3.1: following tabs shall not be displayed : Home, Messages and Log an Event.");
		dashborad.verifyOtherTabsAreNotDisplaying();

		reportLog("4.1: Log in to the portal and de-activate the participant device");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(participantStudyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectNameParticipant);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog(
				"4.2:  When Deactivating the subject (Participant) account the reason for change pop up on portal side, required field needs to be filled in and electronically signed.");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		subjectDetailPage.clickOnDeactivateDeviceButtonForRegisteredSubject();
		subjectDetailPage.selectReasonForChangeOption(Constants.reasonsForChangeDeactivateDevice.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

		reportLog("4.3: Then refresh the page.");
		subjectDetailPage.refreshPage();

		reportLog("4.4: Device history shall show deactivated status.");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("4.5: All records are sorted by date, the latest record on the top.");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();

		reportLog("4.6: Close modal window by 'close' ('X') button");
		subjectDetailPage.clickOnCloseHistory();

		reportLog(
				"5: User is still logged in the applicaiton, go to medications option and then try to add a medication.");
		androidSetUp();

		reportLog("5.1: Logged in participant shall be shown with registration page.");
		mobileLoginPage.verifyRegisterScreenIsDisplay();

		reportLog("6: Register the Observer reg code then click 'Continue' button and 'Accept' the Terms & Conditions");
		mobileLoginPage.scanCodeOrEnterPin(registrationCodeObserver,Mobile_Pin);

		reportLog("7: Create primary identity, secondary identity. Login to the application with valid pin.");
		mobileLoginPage.configurationForRegisterTheSubject(registrationCodeObserver);

		reportLog("7.1: Home , questionnaires, messages tabs shall be displayed to the logged in observer.");
		dashborad.verifyTopMenuOptions();

		reportLog("8: Click on side menu option-exit application.");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();

		reportLog("8.1: Logged in participant shall be exited from the application.");
		sideMenu.verifyApplicationExit();

	}

	@AfterMethod
	public void deactivateUser(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			reportLog("deactivate Observer");
			subjectDetailPage.deactivateObserverConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();

		} else {
			Log.error("Deactivation Not needed");
		}

	}
}
