package net.medavante.regression.testscripts.r2suite;

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

public class R2_FPTC_1243AssignFormAbleToChangeOtherUsersFormAssignmentInPortal_SIP extends BaseTest {

	private String studyName,visitName,screeningNum="Subject1126"+generateRandomAlphanumericString(3) ;
		
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public  R2_FPTC_1243AssignFormAbleToChangeOtherUsersFormAssignmentInPortal_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void testSetUp() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Configuration.updatePropertyTestData("RegressionTestData", "AutomationSubjectName","Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("Auto_NonCr_Visit1");
		studyLanguage=properties.getProperty("studyLanguage");
		
		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();
		
	}
	
	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1243
	 * Test Case Name: Assign Form - able to change other user's form assignment in Portal 
	 * =========================================================================
	 * 
	 */
	
	@Test(description = "FPTC_1243_Verification of Assign Form - able to change other user's form assignment in Portal ", groups = { "R2" })
	public void FPTC_1243_VerificationOfAssignForm(){
				
		reportLog("1.1 :Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3 : Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.4 : select "+studyName +" study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		
		reportLog("1.5: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("1.6: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed(); 
		
		reportLog("1.7: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("1.8: Click on add visit icon"); 
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("1.9: Verify Visit is added"); 
		subjectDetailPage.verifyVisitStatus(visitName,Constants.Pending_Status);

		reportLog("1.10: Verify Forms for assessments are listed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label); 

		reportLog("1.11: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_11);
		dashBoardPage=subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("2.1: Logout application");
		loginPage.logoutApplication();
		
		reportLog("2.2: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

		reportLog("2.3 :Login to Site Portal");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.4 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.5 : Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2.6 : select "+studyName +" study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.7: Select "+ screeningNum+ " from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum); 

		reportLog("2.8: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.9: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed(); 

		reportLog("2.10: Click on visit row");
		subjectDetailPage.clickOnVisitRow(visitName);
		
		reportLog("2.11: Verify Forms for assessments are listed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label); 

		reportLog("3.1: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_12);
		
		reportLog("3.2: Logout application");
		loginPage.logoutApplication();
		
		reportLog("3.3: Verify User is Logout from the application");
		loginPage.verifyUserLogout();
		
		
	}

}
