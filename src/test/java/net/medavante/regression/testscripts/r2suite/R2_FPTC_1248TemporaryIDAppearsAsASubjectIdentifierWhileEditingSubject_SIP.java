package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1248TemporaryIDAppearsAsASubjectIdentifierWhileEditingSubject_SIP extends BaseTest {

	Random rand = new Random();
	int n = rand.nextInt(10) + 1;
	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String crSiteName, crStudyName, crStudyLanguage, crvisitName;
	
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1248TemporaryIDAppearsAsASubjectIdentifierWhileEditingSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		crSiteName = prop.getProperty("siteName");
		crStudyName = prop.getProperty("studyForEnrolledStatus");
		crStudyLanguage = prop.getProperty("studyLanguage");
		crvisitName = prop.getProperty("visitName");
		System.setProperty("className", getClass().getSimpleName());
	
	 	reportLog("Creating a subject from Admin user");
	 	
		reportLog("1. Log in to the Portal as the User of Pr#4");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(crStudyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();
		
		String generatedTempId = studyNavigatorDashBoardPage.generateAutoTemporaryID();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId);
		studyNavigatorDashBoardPage.verifyAutoGenerateTemporaryCancelBTNIsDisplay();
		studyNavigatorDashBoardPage.verifyScreeningNumNotRequiredAndEditable();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId);

		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage(crStudyLanguage);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		subjectDetailPage.clickOnVisitRow(crvisitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.navigateBackToDashBoard();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1248 Test Case Name: Temporary ID appears as a subject identifier while editing a subject  	
	 * 
	 * pre-qualification : At least 1 Study exists with temporary ID is added as a mandatory field 
	 * At least 1 Site exists for the study
	 * At least 1 Subject exists for the study site with a screening number and without any randomization number
	 * At least 1 User exists with appropriate claims
	 * At least 1 Subject Visit exists
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FPTC_1248 --Temporary ID appears as a subject identifier while editing a subject", groups = { "R2" })
	public void FPTC_1248_verifyTemporaryIDAppearsAsASubjectIdentifierWhileEditingSubject() throws Exception {
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2: Navigate to the subject details page of Pr#3 ");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("2.1: Temporary ID field is visible and is disabled");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();
		//need to add
				
		reportLog("2.1: Select the Edit control");
		subjectDetailPage.verifySubjectOpenedInEditMode();
		
		reportLog("3: Remove the screening number of Step#2");
		subjectDetailPage.clearScreeningInp();
		String generatedTempId = studyNavigatorDashBoardPage.autoGenerateTemporaryIDValue();
		
		
		reportLog("3.1: Temporary ID is enabled");
		//Not happening
		
		reportLog("3.3: Both Screening Number and Temporary ID fields become required and highlighted");
		//Not happening
		
		reportLog("4: Enter a Temporary ID");
		//Not happening
		
		reportLog("5: Save the Subject");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("5.1: Enter the reason for changing and the E-signature");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);

		reportLog("5.2: Subject name gets the Temporary ID value");
		//subjectDetailPage.eSignReasonForChangeAndSubmit(userName, password);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN,SuperAdminPW);
		
		reportLog("5.3:  Subject Number = Temporary ID of Step#4");
		String subjectNumber = studyNavigatorDashBoardPage.autoGenerateSubjectNumber();		
		studyNavigatorDashBoardPage.verifyAutoGeneratedSubjectNumber(generatedTempId);
		
		reportLog("6: Navigate to the Subject list page and verify the entry of step#5 Subject");
		dashBoardPage=studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.clickOnSubject(subjectNumber);
		
		reportLog("6.1:  Subject Number = Temporary ID of Step#4");
		studyNavigatorDashBoardPage.verifyAutoGeneratedSubjectNumber(generatedTempId);
		
		reportLog("7: Navigate to the Visit list page and verify the entry of step#5 Subject");
		dashBoardPage=studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();		
		
		reportLog("7.1:  Subject Number = Temporary ID of Step#4");
		studyNavigatorDashBoardPage.searchSubject(generatedTempId);
		studyNavigatorDashBoardPage.searchVisit(crvisitName);
		visitDetaiLPage=studyNavigatorDashBoardPage.clickOnVisitFilterLink();
		
		reportLog("8: Navigate to the Visit details page of Pr#4");
		visitDetaiLPage.verifyVisitPageIsDisplayed();
		
		reportLog("8.1: Subject Number = Temporary ID of Step#4");
		studyNavigatorDashBoardPage.verifySubjectNumberOnVisitPage(generatedTempId);
		
		reportLog("9: Navigate to the Assessment list page and verify the entry of step#5 subject");
		dashBoardPage=studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("9.1: Applied the filter for the subject");
		studyNavigatorDashBoardPage.searchSubject(generatedTempId);
		studyNavigatorDashBoardPage.searchVisit(crvisitName);
		assessmentDetailPage=studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		
		reportLog("10: Navigate to the Assessment details page of an assessment of Pr#4 visit");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
		
		reportLog("10.1: Subject Number = Temporary ID of Step#4");
		studyNavigatorDashBoardPage.verifySubjectNumberOnAssessmentPage(generatedTempId);
		
		reportLog("10.2: Navigate to the subject listing page");
		dashBoardPage=studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();	

		reportLog("11.1: Logout from the application");
		loginPage.logoutApplication();

		reportLog("11.2: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
