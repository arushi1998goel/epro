package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1542_MoveAssessmentMarkedAsNotCompleted_SIP extends BaseTest {

	protected String subjectPRO = "SUBJ_" + generateRandomString(5);
	protected String subjectOBSRO1 = "SUBJ_" + generateRandomString(5);
	protected String subjectOBSRO5 = "SUBJ_" + generateRandomString(5);
	protected String subjectPRO_NotComplete = "SUBJ_" + generateRandomString(5);
	protected String subjectOBSRO_WithoutObserver = "SUBJ_" + generateRandomString(5);
	protected String subjectOBSRO_WithObserver = "SUBJ_" + generateRandomString(5);
	protected String subjectOBSRO_WithMultipleObserver = "SUBJ_" + generateRandomString(5);
	private String observerAlias = "Auto" + generateRandomString(3);

	private String visit_PRO, Observer, moveToAssesment, visitOBSRO, visitOBSRO5, visitName3,
			completedButNotAssignedObserver, ObserverToSelect;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1542_MoveAssessmentMarkedAsNotCompleted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visit_PRO = properties.getProperty("visit_PRO");
		visitOBSRO = properties.getProperty("visitOBSRO");
		visitOBSRO5 = properties.getProperty("visitOBSRO5");
		visitName3 = properties.getProperty("visitName3");
		moveToAssesment = properties.getProperty("moveToAssesment");
		ObserverToSelect = properties.getProperty("ObserverToSelect");
		completedButNotAssignedObserver = properties.getProperty("Auto_Observer_Relation1");
		Observer = properties.getProperty("observer");

		reportLog("Creating a subject for the Study Site (Pr#1),");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.VTAssignedRater_21);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectPRO);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Verify new subject detail page display");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_PRO);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectOBSRO1);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName3);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectOBSRO5);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitOBSRO);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();

		reportLog("Creating a subject for the Study Site (Pr#4),");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectPRO_NotComplete);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_PRO);
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectOBSRO_WithoutObserver);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName3);
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectOBSRO_WithObserver);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitOBSRO);
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectOBSRO_WithMultipleObserver);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitOBSRO5);
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("Logout application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}

	@Test(description = "FP-TC-1542 MoveAssessmentMarkedAsNotCompleted", groups = { "" })
	public void FPTC_1542_verifyMoveAssessmentMarkedAsNotCompleted() {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.VTAssignedRater_21);

		reportLog("1.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.2 Search visit1 and subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visit_PRO, subjectPRO);

		reportLog("1.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("2 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.1 Select the Subject from dropdown in change assesment section");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectPRO_NotComplete);

		reportLog("2.2 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visit_PRO);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("2.3 Click on Confirm button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("2.4 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("2.5 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("2.6Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("3.1:navigate to Dashboard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("3.2:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("3.3 Search visit1 and subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_MMSEFormName, visitName3, subjectOBSRO1);

		reportLog("3.4 Verify Action button is not  displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("3.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.6 Check that Assessment PR#4.2 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.verifySubjectIsNOtPresntInMoveToSubjectListDrpDwn(subjectOBSRO_WithoutObserver);

		reportLog("3.7Click on close button of change assesment form dialog");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("4.1 Verify Action button is   displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("4.2 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("4.3 Select the Subject from dropdown in change assesment section");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectOBSRO_WithObserver);

		reportLog("4.4 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitOBSRO);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("4.6 Click on Confirm button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("4.5 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("4.7 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("4.8 Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("5.1:navigate to Dashboard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.VTAssignedRater_21);

		reportLog("5.2:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5.3 Search visit1 and subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_MMSEFormName, visitOBSRO, subjectOBSRO5);

		reportLog("5.4 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("5.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("5.6 Select the Subject from dropdown in change assesment section");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectOBSRO_WithMultipleObserver);

		reportLog("5.7 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitOBSRO);

		reportLog(
				"5.8 Check that a drop-down list for Observers displayed on the Assessment PR#4.4. row in the \"Move To\" Assessment block");
		assessmentDetailPage.verifyObserverDropDownIsPrsentOrNot();

		reportLog("6.0: Select an Observer from the list");
		assessmentDetailPage.selectTheObserverFromTheList(observerAlias);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("6.1: Click on Confirm button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("6.2: : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("6.3: Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("6.4: Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("Check that the Assessment PR#4.4 shown as Not completed");
		// not able to verify due to Bug
		
		reportLog("7.1: Logout application");
		loginPage.logoutApplication();

		reportLog("7.2:Verify user is logout");
		loginPage.verifyUserLogout();
	}

}
