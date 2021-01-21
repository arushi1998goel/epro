package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1265VerifyDuplicateScreeningFieldInSubjectDetail_SIP extends BaseTest {
	

	private String studyName,screeningNum="Auto_"+generateRandomString(5),screening2="Auto_"+ generateRandomString(5);
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1265VerifyDuplicateScreeningFieldInSubjectDetail_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
 
		
		reportLog("1.1: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.2: Verify User Login successfully");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("1.3: Navigate to study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.4: Creating subject1 for the pre-requisite");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("1.5: Navigate back to dashboard page");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
	
		reportLog("1.6: Navigate to study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.7: Select "+studyName+" study from the list of studies");
		//studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.8: Click on add subject button --To configure the test preRequiste for subject2");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		
		reportLog("1.9: Enter "+screening2+" in screening field to configure the test preRequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screening2);
		
		reportLog("1.10: Select "+studyLanguage+" Subject language to configure the test preRequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		reportLog("1.11: Click on save button to save the subject to configure the test preRequiste and verify subject is created");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screening2);

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1265 Test Case Name:Site Portal: Validation of duplicate
	 * 'Screening#' field in subject detail should occur before e-signature
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1265_Verification of duplicate 'Screening#' field in subject detail should occur before e-signature", groups = {
			"R1" })
	public void FPTC_1265_verifyDuplicateScreeningFieldInSubjectDetail() {	
		
		reportLog("2.1: click on a control to Edit");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("2.2: Verify 'Subjects' page is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed(); 
		
		reportLog("3.1: Set a "+screeningNum+" for 'Subject 1' = "+screeningNum+" for 'Subject 2'");
		subjectDetailPage.inputScreeningNumber(screeningNum);
		
		reportLog("3.2: click a control to 'Save' a 'Subject'");
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("3.3: Error message 'Failed to update subject.Subject with the same screening number already exists.' is presented in the top of the page.");
		subjectDetailPage.verifyDuplicateScreeningErrorPoUpIsDisplayed(SubjectsModuleConstants.editDuplicateScreeningErrorMessage); 
		
		reportLog("3.4: close error validation message container");
		subjectDetailPage.closeErrorMessage(); 
		
		reportLog("3.5: Verify 'Subjects' page is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("3.6: Click on cancel button to close the edit screening popup");
		subjectDetailPage.clickOnCancelBtn();
	
		reportLog("3.7: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("3.8: Verify User Logout from the system");
		loginPage.verifyUserLogout();
	
	}

}
