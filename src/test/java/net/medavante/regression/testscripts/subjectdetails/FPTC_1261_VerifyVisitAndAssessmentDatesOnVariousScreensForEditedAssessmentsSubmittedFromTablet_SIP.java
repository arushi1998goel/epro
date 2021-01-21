package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1261_VerifyVisitAndAssessmentDatesOnVariousScreensForEditedAssessmentsSubmittedFromTablet_SIP
		extends BaseTest {
	
	protected String subjectName = "SUBJ_" + generateRandomString(5);
	private String visit1,visit2,visit3;
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1261_VerifyVisitAndAssessmentDatesOnVariousScreensForEditedAssessmentsSubmittedFromTablet_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		visit1=properties.getProperty("Auto_ClinRo_VMico1");
		visit2=properties.getProperty("ElectronicClinRoVisit2");
		visit3=properties.getProperty("Auto_ClinRo_VMico2");
		
		reportLog("Creating a subject for the Study Site (Pr#1),");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Study(Pr#1) is configured with at least 2 visit");
		subjectDetailPage.clickOnVisitRow(visit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);	
		
		subjectDetailPage.clickOnVisitRow(visit3);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		loginPage.logoutApplication();		
		
		reportLog("Submit the assessment by rater selected for these visits");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormNameSecondType,visit1,subjectName);
		
		reportLog("Click on Assessment link");

		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
	
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormNameSecondType,visit1,subjectName);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Search visit2 and subject in assessment page");
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(visit2);
		
		reportLog("Click on Assessment link");

		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("Search visit3 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(visit3);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_ClinRO_VForm);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(visit3);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinator, AT_Password);
		loginPage.logoutApplication();
		
	}

	/**
	 * =======================================================================================================================================
	 * Test Case Id: FP-TC-1261 Test Case Name: Visit and Assessment dates on various screens for edited assessments submitted from tablet 
	 * 
	 * ========================================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1261_VerifyVisitAndAssessmentDatesOnVariousScreensForEditedAssessmentsSubmittedFromTablet", groups = {""})
	public void FPTC_1261_VerifyVisitAndAssessmentDatesOnVariousScreensForEditedAssessmentsSubmittedFromTablet() {
	
		reportLog("1: Log in to the portal as user of PR#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2: Navigate to the subject details page of PR#8");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("2.1: Subject details page is visible");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("2.2: The section where visits are listed shows label 'Visit Start Date'");
		subjectDetailPage.verifyLabelsForVisitHeader(Constants.SubjectDeatil_VisitLabel);
		
		reportLog("3: Select visit in Pr#5");
		subjectDetailPage.clickOnVisitRow(visit1);
		
		reportLog("3.1: Assessment (Pr#5.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("4: Select visit in Pr#6");
		subjectDetailPage.clickOnVisitRow(visit2);
		
		reportLog("4.1: Assessment (Pr#6.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("5: Select visit in Pr#7");
		subjectDetailPage.clickOnVisitRow(visit3);
		
		reportLog("5.1: Assessment (Pr#7.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("5.2: The section where visits are listed shows label 'Visit Start Date'");
		subjectDetailPage.verifyVisitStartDate(visit3, currentDate());
		
		reportLog("6: Navigate to the visit details page of step #5");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);	
		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit3,subjectName);
		
		reportLog("Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("6.1: 'Visit Start date' field shows start date of V1 Assessment");
		visitDetaiLPage.verifyVisitDetailsUnderSubjectSection("Visit Start Date", currentDate());
		
		reportLog("6.2: 'Visit Complete date' field shows UTC date of V1 Assessment submission");
		visitDetaiLPage.verifyVisitDetails("Visit Complete Date (UTC)", currentDate());		
		visitDetaiLPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);
		visitDetaiLPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("7: Navigate to the Visit list page");		
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		
		reportLog("7.1: 'Visit Start date' field shows start date of V1 Assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visit3);
		
		reportLog("8: Navigate to the details page Assessment of step #6");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visit3,subjectName);
		
		reportLog("Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("8.1: Under Versions, label is “Submitted Date (UTC)” and shows date in UTC when e-signature was captured.");
		//assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", currentDate());
		
		reportLog("9: Navigate to the Assessment list page Find Step#7 Assessment row");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("9.1: “Initial Version Start Date” column shows the start date of V1 assessment");	
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visit3);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
	}

}
