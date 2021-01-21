package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.*;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2706VerifyMAAdminCanManageCRSubject_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2706VerifyMAAdminCanManageCRSubject_SIP(String browser) {
		super(browser);
	}

	private String studyName,subjectName2 = "Automation_" + generateRandomString(3);

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
	}

	/**
	 * =========================================================================
	 * Test Case Id:  FPTC_2706 Test Case Name: MA Admin - canManageCRSubject
	 * =========================================================================
	 * @throws Exception 
	 */

	@Test(description = "FPTC_2706_MA Admin - CanManageCRSubject", groups = { "R1" })
	public void FPTC_2706verifyMAAdminCanManageCRSubject() throws Exception {

		reportLog("1.1: Login in to application with MedAvante User type 4");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);

		reportLog("1.2: Navigate to central rating area");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.3: Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.4: Select Study and Site");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Click 'Create Subject' control");
		centralRatingAssesmentListingPage.clickOnCreateSubjectBtnForSelectedStudyAndSubject();
	    
	    reportLog("1.6: Verify add subject popup is Display");
	    centralRatingAssesmentListingPage.verifyAddSubjectPopUpIsDisplayed();
	    
	    reportLog("1.7: Enter "+screeningNum+" Screening Num");
	    centralRatingAssesmentListingPage.inputScreeningNum(screeningNum);
	    
	    reportLog("1.8: Select "+studyLanguage+" Language");
	    centralRatingAssesmentListingPage.selectSubjectLanguage(studyLanguage);
	    
	    reportLog("1.9: Click on save button");
	    subjectDetailPage=centralRatingAssesmentListingPage.clickOnSaveBTN();
	    
		reportLog("2.1: Verify User is redirected to Subject Details screen of initial created subject and verify "+screeningNum+" subject is created ");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.2: Verify Edit control for subject Details is available");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("3.1: Click on Edit control ");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("3.2: Change subject name to "+subjectName2);
		subjectDetailPage.inputScreeningNumber(subjectName2);

		reportLog("3.3: Click on Save nutton to save the changes");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("3.4: Select reason to update the screening reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);

		reportLog("3.5: Esign to submit the changes");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODITSupport, AT_Password);

		reportLog("3.6: Verify Changes applied "+subjectName2+" Subject number is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName2);

		reportLog("4.1: Click on Delete subject and click on Yes button.");
		studySubjectListing=subjectDetailPage.deleteSubject();
	     studySubjectListing.navigateBack();

		reportLog("4.2: Navigate to central rating page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("4.3: click on find subject Button");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("4.4: Select study and site from the drop down option");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("4.5: Verify Subject is removed");
		centralRatingAssesmentListingPage.verifySubjectNameNotDisplayedInFindSubjectList(subjectName2);

		reportLog("4.6: User Logout");
		loginPage.logoutApplication();

		reportLog("4.7: Verify User Logout");
		loginPage.verifyUserLogout();

		reportLog("5.1: Login in to application with MedAvante User type 2");
		dashBoardPage = loginPage.maLogin(AT_MAclinician, AT_Password);

		reportLog("5.2: Navigate to central rating area");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("5.3: Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("5.4: Select Study and Site");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("5.5: Verify Create Subject control is not available");
		centralRatingAssesmentListingPage.verifyCreateSubjectBtnIsNotDisplayed();

		reportLog("5.5: Click on cancel button to close find subject popup");
		centralRatingAssesmentListingPage.closeFindSubjectPopup();
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.1: Navigate Subject Details");
		subjectDetailPage = centralRatingAssesmentListingPage.clickOnSearchSubjects("Rand001");

		reportLog("6.2: Verify Edit subject control is not available on Subject Details");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed("Rand001");
		subjectDetailPage.verifySubjectEditButtonIsNotDisplayed();

		reportLog("6.3: User Logout");
		loginPage.logoutApplication();

		reportLog("6.4: Verify User Logout");
		loginPage.verifyUserLogout();

		reportLog("7.1: Login in to application with MedAvante User type 3");
		dashBoardPage = loginPage.maLogin(AT_MAclinicianSecond, AT_Password);

		reportLog("7.2: Navigate to central rating area");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("7.3: Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("7.4: Select Study and Site");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("7.5: Verify Create Subject control is not available");
		centralRatingAssesmentListingPage.verifyCreateSubjectBtnIsNotDisplayed();

		reportLog("7.5: Click on cancel button to close find subject popup");
		centralRatingAssesmentListingPage.closeFindSubjectPopup();

		reportLog("8.1: Search subject and navigate to subject detail page");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		subjectDetailPage = centralRatingAssesmentListingPage.clickOnSearchSubjects("Rand001");

		reportLog("8.2: Verify Edit subject control is not available on Subject Details");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed("Rand001");
		subjectDetailPage.verifySubjectEditButtonIsNotDisplayed();

		reportLog("8.3: User Logout");
		loginPage.logoutApplication();

		reportLog("8.4: Verify User Logout");
		loginPage.verifyUserLogout();
	}

}
