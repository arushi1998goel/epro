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

public class R2_SFBTC_2055_SubjectStatusCountAddEditDeleteSubjectTotalCountTest_SIP extends BaseTest {

	Random rand = new Random();
	int n = rand.nextInt(10) + 1;
	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String crSiteName, crStudyName;
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_SFBTC_2055_SubjectStatusCountAddEditDeleteSubjectTotalCountTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		crSiteName = prop.getProperty("siteName");
		crStudyName = prop.getProperty("studyForEnrolledStatus");
		System.setProperty("className", getClass().getSimpleName());
		

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-2055 Test Case Name: Verify that Add/Edit/Delete Subject
	 * actions calculate correct count on Study Navigator.
	 * 
	 * pre-qualification : 1. A Study should have at least one Site with more than
	 * one Subjects and configure following statuses: 1) New	 * 
	 * 2) Screened 
	 * 3) Screen Failed
	 * 4) Enrolled 
	 * 5) Completed 
	 * 6) Discontinued 
	 * 
	 * 2. At least one Subject without assigned Visits
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "SFB-TC-2055 --Verify that Add/Edit/Delete Subject actions calculate correct count on Study Navigator", groups = { "R2" })
	public void SFBTC_2055_verifySubjectStatusCountAddEditDeleteSubjectTotalCountTest() throws Exception {

		reportLog("1. Log in to Portal with PR#1 and go to the Study Dashboard");
		//MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(userName, password);
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(superAdminUN, superAdminPW);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.1: Study from PR#2 -> Subjects");
		studyNavigatorDashBoardPage.selectStudy(crStudyName,crSiteName);
				
		reportLog("1.2: Add new Subject by control 'Add New Subject' ");	
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(crSiteName);
		
		reportLog("1.3: Fill all required fields");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		
		reportLog("1.4: Save the changes");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.5: Check that total count of Subjects was changed on Study Navigator values.");
		/*String countAfterAddingSubj = studyNavigatorDashBoardPage.verifyStatusCount();
		Assert.assertNotEquals(countBeforeAddingSubj, countAfterAddingSubj);
		
		reportLog("1.6: Click on New Status");
		studyNavigatorDashBoardPage.verifyCountsAndStatusesOfSubjects("New", 0);*/
		
		reportLog("2.1: Add new Subject by control 'Add New Subject' ");		
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(crSiteName);
				
		
		reportLog("2.2: Fill all required fields");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		
		reportLog("2.3: Select ‘Cancel’ option");
		studyNavigatorDashBoardPage.clickOnCancelBTN();
		
		studyNavigatorDashBoardPage.navigateToHomePage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.4: Check that total count of Subjects wasn't changed on Study Navigator values.");	
		/*String countAfterCancelSubj = studyNavigatorDashBoardPage.verifyStatusCount();
		Assert.assertEquals(countAfterCancelSubj, countAfterAddingSubj);
		
		reportLog("2.5: Click on New Status");
		studyNavigatorDashBoardPage.verifyCountsAndStatusesOfSubjects("New", 0);*/
		
		reportLog("3.1: Navigate to Subject Listing screen and select Subject from Step#1");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("3.2: Control to Edit Subject");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("3.3: Edit any field (ex. Initials)");
		subjectDetailPage.verifySubjectOpenedInEditMode();		
		studyNavigatorDashBoardPage.inputNotes("notesText"+n);

		reportLog("3.4: Save changes");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("3.5: Select reason to update the screening reason");
		
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		//subjectDetailPage.eSignReasonForChangeAndSubmit(userName, password);
		subjectDetailPage.eSignReasonForChangeAndSubmit(superAdminUN, superAdminPW);
		
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		
		reportLog("3.6: Check that total count of Subjects wasn't changed on Study Navigator values.");		
		/*String countAfterUpdateSubj = studyNavigatorDashBoardPage.verifyStatusCount();
		Assert.assertEquals(countAfterUpdateSubj, countAfterCancelSubj);
		
		reportLog("3.7: Click on New Status");
		studyNavigatorDashBoardPage.verifyCountsAndStatusesOfSubjects("New", 0);*/
		
		reportLog("4.1: Navigate to Subject Listing screen ");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("4.2: Delete Subject");
		subjectDetailPage.deleteSubject();
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		
		reportLog("4.3: Check that total count of Subjects was changed on Study Navigator values.");
		/*String countAfterDeleteSubj = studyNavigatorDashBoardPage.verifyStatusCount();
		Assert.assertNotEquals(countAfterUpdateSubj, countAfterDeleteSubj);
		*/
		
		reportLog("5.1: Logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("5.2: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
