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

public class FPTC_1258_VerifyVisitAndAssessmentDatesOnVariousScreensForCompletedAssessmentsSubmittedFromTablet_SIP
		extends BaseTest {
	
	protected String subjectName1 = "SUBJ_1" + generateRandomString(5), subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String visit1,visit2,visit3;
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1258_VerifyVisitAndAssessmentDatesOnVariousScreensForCompletedAssessmentsSubmittedFromTablet_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		visit1=properties.getProperty("Auto_ClinRo_VMico1");
		visit2=properties.getProperty("PaperClinRoVisit2");
		visit3=properties.getProperty("Auto_ClinRo_VMico2");
		
		reportLog("Creating a subject for the Study Site (Pr#1),");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);	
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("(Pr#1) Study is configured with at least 3 visits");
		subjectDetailPage.clickOnVisitRow(visit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);	
		
		subjectDetailPage.clickOnVisitRow(visit3);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
				
		reportLog("Creating subject 2");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);		
		
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Pr#5) Assessment is submitted as Not administered by rater of (Pr#3)");
		subjectDetailPage.clickOnVisitRow(visit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.RaterName_23);
		
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);	
		
		subjectDetailPage.clickOnVisitRow(visit3);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);	
		
		loginPage.logoutApplication();		
		
		reportLog("Submit the assessment by rater selected for these visits");
		loginPage.loginInApplication(PRODSiteUser, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);	
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATSiteAssignedRater_10);
				studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(visit1);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List1FormName);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);	
		
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(visit1);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Search visit2 and subject in assessment page");
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(visit2);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		
		reportLog("Search visit3 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(visit3);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(visit3);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("For 2nd subject, (Pr#5) Assessment is submitted as Not administered by rater of (Pr#3)");
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.searchSubject(subjectName2);
		studyNavigatorDashBoardPage.searchVisit(visit1);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_ClinRO_VForm);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.searchSubject(subjectName2);
		studyNavigatorDashBoardPage.searchVisit(visit1);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(PRODSiteUser, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		loginPage.logoutApplication();
		
	}

	/**
	 * =======================================================================================================================================
	 * Test Case Id: FP-TC-1258 Test Case Name: Visit and Assessment dates on various screens for completed assessments submitted from tablet 
	 * 
	 * ========================================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1258_VerifyVisitAndAssessmentDatesOnVariousScreensForEditedAssessmentsSubmittedFromTablet", groups = {""})
	public void FPTC_1258_VerifyVisitAndAssessmentDatesOnVariousScreensForCompletedAssessmentsSubmittedFromTablet() {
	
		reportLog("1: Log in to the portal as user of PR#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ChooseSite_VPTester21);
		//studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2: Navigate to the subject details page of PR#8.1");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName1);
		
		reportLog("2.1: Subject details page is visible");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("2.2: The section where visits are listed shows label 'Visit Start Date'");
		subjectDetailPage.verifyLabelsForVisitHeader(Constants.SubjectDeatil_VisitLabel);
		
		reportLog("3: Select visit in Pr#5");
		subjectDetailPage.clickOnVisitRow(visit1);
		
		reportLog("3.1: Assessment (Pr#5.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.RaterName_23);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("4: Select visit in Pr#6");
		subjectDetailPage.clickOnVisitRow(visit2);
		
		reportLog("4.1: Assessment (Pr#6.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.RaterName_23);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("5: Select visit in Pr#7");
		subjectDetailPage.clickOnVisitRow(visit3);
		
		reportLog("5.1: Assessment (Pr#7.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.RaterName_23);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("5.2: The section where visits are listed shows label 'Visit Start Date'");
		subjectDetailPage.verifyVisitStartDate(visit3, currentDate());
		
		reportLog("6: Navigate to the visit details page of step #5");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();		
		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit3,subjectName1);
		
		reportLog("Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("6.1: 'Visit Start date' field shows start date of V1 Assessment");
		visitDetaiLPage.verifyVisitDetailsUnderSubjectSection("Visit Start Date", currentDate());
		
		reportLog("6.2: 'Visit Complete date' field shows UTC date of V1 Assessment submission");
		visitDetaiLPage.verifyVisitDetails("Visit Complete Date (UTC)", currentDate());		
		visitDetaiLPage.verifySubmittedVisitRaterName(Constants.RaterName_23);
		visitDetaiLPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("7: Navigate to the Visit list page");		
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		
		reportLog("7.1: Find Step#6 Visit row and “Visit Start Date” column shows the start date of first started assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName1);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visit3);
		
		reportLog("8: Navigate to the details page Assessment of step #6");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator(); 
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visit3,subjectName1);
		
		reportLog("Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("8.1: Under Versions, label is “Submitted Date (UTC)” and shows date in UTC when e-signature was captured.");		
		assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", currentDate());
		
		reportLog("9: Navigate to the Assessment list page Find Step#7 Assessment row");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("9.1: “Initial Version Start Date” column shows the start date of V1 assessment");	
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName1);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Visit, visit3);
		
		reportLog("10: Navigate to the subject details page of PR#8.2");		
		studyNavigatorDashBoardPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName2);
		
		reportLog("10.1: Subject details page is visible");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("10.2: The section where visits are listed shows label “Visit Start Date”");
		subjectDetailPage.verifyLabelsForVisitHeader(Constants.SubjectDeatil_VisitLabel);
		
		reportLog("11: Select visit in Pr#5");
		subjectDetailPage.clickOnVisitRow(visit1);
		
		reportLog("11.1: Assessment (Pr#5.1) thumbnail shows data on fields: Submitted by, Submitted date in UTC");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.RaterName_23);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
	}

}
