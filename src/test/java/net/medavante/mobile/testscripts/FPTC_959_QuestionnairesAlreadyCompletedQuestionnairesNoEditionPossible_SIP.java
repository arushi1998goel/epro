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

public class FPTC_959_QuestionnairesAlreadyCompletedQuestionnairesNoEditionPossible_SIP extends BaseTest {

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_959_QuestionnairesAlreadyCompletedQuestionnairesNoEditionPossible_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyQuestionnairesObserver");
		visitName = properties.getProperty("EPROMandatoryVisit");
		observerregistrationCode = properties.getProperty("ObserverRegistrationCode");
		subjectName = properties.getProperty("SubjectName959");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-959 Test Case Name: Questionnaires - Already
	 * Completed Questionnaires - No Edition possible
	 * 
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-959_Questionnaires - Already Completed Questionnaires - No Edition possible", groups = {
			"Mobile" })

	public void FPTC_959_verifyQuestionnairesAlreadyCompletedQuestionnairesNoEditionPossible() throws Exception {


		reportLog("1: MobileView Launch the application");
		mobileLoginPage = androidSetUp();
		dashborad = mobileLoginPage.scanCodeOrEnterPin(observerregistrationCode, Mobile_Pin);

		reportLog(
				"2:MobileView Application is successfully launched and Home Page is displayed as per study configuration.");
		dashborad.verifyUserLogin();

		reportLog("3.1:MobileView Tap on Questionnaires tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog("3.2:MobileView Application navigates to Questionnaire list. Completed questionnaire is present.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(
				"4:MobileView Tap to Completed questionnaire and verify that the questionnaire cannot be opened and edited.");
		questionnairesPage.verifyCompletedIconPresentWithQuestionsAndNotEditable(Constants.Mat_Mob);
	
		reportLog("5.MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();		
		clickOnConnectAppIcon();
		
		reportLog("6.1:Portal Steps to verify Completed Questionnaires Prsent under Subject Deatils Page");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("6.2: Select Questionnaires category in the drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("6.3:Select the all tab to show questionnaires");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		
		reportLog("6.4: 'Subject' filter is displayed by default");
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Participant_Filter);
		
		reportLog("6.5:	Verify questionniares displayed	");
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("6,6: Select 'Observer' filter");
		subjectDetailPage.selectUserBlockFilter(Constants.Observer_Filter);
		
		reportLog("6.7:	Verify questionniares displayed	");
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();
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
