package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SFB_TC_2195MoveAssessmentAutomaticallySubmitted_SIP extends BaseTest{
	private String VisitCompleted,SubjectAutomaticSubmission,VisitSecondForClinRo,Visit_NotCompleted_2195SFBTC;
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)	
	public SFB_TC_2195MoveAssessmentAutomaticallySubmitted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitCompleted = properties.getProperty("CDRVisitCompletedFirstVisit2187");
		SubjectAutomaticSubmission=properties.getProperty("SubjectAutomaticSubmission");
		VisitSecondForClinRo=properties.getProperty("VisitSecondForClinRo");
		Visit_NotCompleted_2195SFBTC=properties.getProperty("Visit_NotCompleted_2195SFBTC");
}
	/**
	 * ===================================================================================================================
	 * Test Case Id: SFB_TC_2195 Test Case Name: Move Assessment Automatically Submitted 
	 * 
	 * ====================================================================================================================
	 * 
	 */
	
	@Test(description = "SFB_TC_2195MoveAssessmentAutomaticallySubmitted_SIP", groups = { "" })
	public void SFB_TC_2195verfiyMoveAssessmentAutomaticallySubmitted_SIP() {
		
		reportLog("1.1:Log in to the Portal as a User from Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Open the Study Navigator-> Select a Study and a Site from PR#2 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.4: Navigate to the Assessment Listing screen and select the 1st Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitSecondForClinRo, SubjectAutomaticSubmission);
		
		reportLog("1.5:Visit Assessment screen from PR#3 is displayed  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.1:Action option is displayed for the Assessment ");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("2.2:Select - Action to Change Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();
		
		
		reportLog("2.3:Assessment from PR#4 is displayed in Change To Assessment drop down list  ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(SubjectAutomaticSubmission);
		
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(Visit_NotCompleted_2195SFBTC);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("2.4:Change Assessment PR#3 to Assessment PR#4  ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(Visit_NotCompleted_2195SFBTC);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		
		reportLog("2.5:Save changes ");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.6:The Assessment was changed ");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		
		reportLog("2.7Verify The PDF has the similar mention in the Audit History ");
		assessmentDetailPage.verifyNotesSectionIsAppeared();
		assessmentDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();
		
		reportLog("3.1Log in to the Portal with PR#5 ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("3.2:User from PR#5 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.3:Open the Study Navigator-> Select a Study and a Site from PR#5 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		
		reportLog("3.4: Navigate to the Assessment Listing screen and select the 1st Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, Visit_NotCompleted_2195SFBTC, SubjectAutomaticSubmission);
		
		reportLog("Verify The PDF has the similar mention in the Audit History ");
		assessmentDetailPage.verifyNotesSectionIsAppeared();
		loginPage.logoutApplication();
		
		reportLog("4Log in to the Portal with PR#6");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSponsorUserType3, AT_Password);
		
		reportLog("4.2:User from PR#5 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.3:Open the Study Navigator-> Select a Study and a Site from PR#5 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		
		reportLog("4.4: Navigate to the Assessment Listing screen and select the 1st Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, Visit_NotCompleted_2195SFBTC, SubjectAutomaticSubmission);
		
		reportLog("Verify The PDF has the similar mention in the Audit History ");
		assessmentDetailPage.verifyNotesSectionIsAppeared();
		loginPage.logoutApplication();
	}
	
	
	@AfterMethod
	public void updateVisitValueInPropertiesFile(ITestResult result) {
		if(ITestResult.SUCCESS==result.getStatus()) {
			Configuration.updatePropertyTestData("RegressionTestData", "VisitSecondForClinRo",Visit_NotCompleted_2195SFBTC);
			Configuration.updatePropertyTestData("RegressionTestData", "Visit_NotCompleted_2195SFBTC",VisitSecondForClinRo);
		}else {
			Log.error(screeningNum + " subject is not added for " + studyName+ " study.");
		}
	}

	
}