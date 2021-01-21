package net.medavante.regression.testscripts.studynavigator;

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

public class FPTC_1459_ChangeVisitOptionWhenObserverForDestinationSubExistOrNot_SIP
		extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5),
			subjectName2 = "SUBJ_2" + generateRandomString(5), subjectName3 = "SUBJ_3" + generateRandomString(5),
			subjectName4 = "SUBJ_4" + generateRandomString(5),completedVisit, notCompletedVisit, observerAlias = "Auto" + generateRandomString(3),
					observerRelation1, observerRelation2, observerNameInDropDown;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1459_ChangeVisitOptionWhenObserverForDestinationSubExistOrNot_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		completedVisit = properties.getProperty("Visit2146CompletedVisit");
		notCompletedVisit = properties.getProperty("Visit2146NotCompletedVisit");
		observerRelation1 = properties.getProperty("Auto_Observer_Relation1");
		observerRelation2 = properties.getProperty("Auto_Observer_Relation2");

		reportLog("1. At least one Visit has one ClinRO, one PRO, one ObsRO completed assessments ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("Creating Subject for Prerequest");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		// Add observer to this subject
		reportLog("Add observer to this subject");
		subjectDetailPage.verifyReportedOutComeButtonIsDisplayed();
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("Enter required Observer data and click on 'Save' control");
		subjectDetailPage.inputObserverRelationName(observerRelation1);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		observerNameInDropDown = observerRelation1 + " " + "(" + observerAlias + ")";

		reportLog("New Observer record is created");
		subjectDetailPage.verifyObserverInformation(observerRelation1, Constants.observerDeactiveText, "No");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("Configure study for ClinRo");
		subjectDetailPage.clickOnVisitRow(completedVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,Constants.ATAssignedRater_10);

		reportLog("Configure study for ObsRo Assessment which marked as Not Completed exists");
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("Configure study for PRO Assessment which marked as Not Completed exists");
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("2. At least one Visit has one ClinRO, one PRO, one ObsRO not assigned assessments. An observer does not exist for the destination Subject");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName2);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		subjectDetailPage.clickOnVisitRow(notCompletedVisit);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("Configure study for ClinRo,ObsRo Pro and not assign it ");
		subjectDetailPage.clickOnVisitRow(completedVisit);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog(
				"3. At least one Visit has one ClinRO, one PRO, one ObsRO not assigned assessments. Only one Observer exist for the destination Subject.");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName3);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		// Add observer to this subject
		reportLog("Add observer to this subject");
		subjectDetailPage.verifyReportedOutComeButtonIsDisplayed();
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("Enter required Observer data and click on 'Save' control");
		subjectDetailPage.inputObserverRelationName(observerRelation1);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		observerNameInDropDown = observerRelation1 + " " + "(" + observerAlias + ")";
		reportLog("New Observer record is created");
		subjectDetailPage.verifyObserverInformation(observerRelation1, Constants.observerDeactiveText, "No");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		subjectDetailPage.clickOnVisitRow(notCompletedVisit);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("4. At least one Visit has one ClinRO, one PRO, one ObsRO not assigned assessments. More than one Observer exist for the destination Subject.");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName4);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		// Add observer to this subject
		subjectDetailPage.verifyReportedOutComeButtonIsDisplayed();
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("Enter required Observer data and click on 'Save' control");
		subjectDetailPage.inputObserverRelationName(observerRelation1);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		observerNameInDropDown = observerRelation1 + " " + "(" + observerAlias + ")";

		reportLog("New Observer record is created");
		subjectDetailPage.verifyObserverInformation(observerRelation1, Constants.observerDeactiveText, "No");

		reportLog("Add second observer");
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("Enter required Observer data and click on 'Save' control");
		subjectDetailPage.inputObserverRelationName(observerRelation2);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		observerNameInDropDown = observerRelation2 + " " + "(" + observerAlias + ")";

		reportLog("New Observer record is created");
		subjectDetailPage.verifyObserverInformation(observerRelation2, Constants.observerDeactiveText, "No");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		subjectDetailPage.clickOnVisitRow(notCompletedVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, completedVisit, subjectName1);

		reportLog("Click on Assessment link");
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1459 Test Case Name: Change Visit - option to change
	 * Visit in case when Observer for the destination Subject exist or doesn't
	 * exist
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1459_Change Visit - option to change Visit in case when Observer for the destination Subject exist or doesn't exist ", groups = {})
	public void FPTC_1459_VerifyOptionToChangeVisitInCaseWhenObserverForTheDestinationSubjectExistOrDoesNotExist()
			throws InterruptedException {

		reportLog("1: Login to portal as a User from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("2: Navigate to Visit listing screen and select Visit from Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedVisit, subjectName1);

		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3: Select action to change visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("4: Check if Visit Pr.#3 isn't displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.verifySubjectIsNotPresentInChangeToSubjectDropDown(subjectName2);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();

		reportLog("5: Navigate to Visit listing screen and select Visit from Pr.#2");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedVisit, subjectName1);

		reportLog("6.1: Select action to change visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName3);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("6.2: Check if Visit Pr.#4 is displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(completedVisit);

		reportLog("7: Select Visit from Pr.#4");
		visitDetaiLPage.selectChangeToVisit(completedVisit);

		reportLog("8.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("8.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("8.3: E-signature window appears after the change has been confirmed");
		visitDetaiLPage.verifyReasonofChangePopUpDisplayed();

		reportLog("8.4: Select control to apply the Reasons to Change");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);

		reportLog("9: Select control to sign the Reason for Change ('Ok')");
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("9.1: Visit is changed successfully and Visit detail screen is displayed");
		visitDetaiLPage.verifySuccessMessage("The visit has been changed successfully");
		visitDetaiLPage.closeSuccessMessage();

		reportLog("9.2: Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status, Constants.Complete_Status);

		reportLog("10: Check if Observer exists for the destination Subject is pre-selected");
		reportLog("10.1: Observer exists for the destination Subject is pre-selected");

		reportLog("11: Open 'Source Subject Status' for Subject Pr#2");
		visitDetaiLPage.navigateToHome();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName1);

		reportLog(
				"11.1: Source Visit becomes blank after a successful change. And all assessments are not assigned to any rater");
		subjectDetailPage.verifyVisitStatus(completedVisit, Constants.blank_Status);

		reportLog("12: Open 'Subject Status History' Pr#4");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName3);

		reportLog("12: Open 'Subject Status History' Pr#3");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog(
				"12.1: 'Subject Status History' is updated and has the latest status in order of visit completion by system change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog(
				"13: Open corresponding assessments and Destination Visit has received all corresponding data from the source Assessment:");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedVisit, subjectName3);

		reportLog("13.1: Assessments detail screen;");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("13.2: Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("13.5: close the pdf");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("14: Navigate to Visit listing screen and select Visit from Pr.#4");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedVisit, subjectName3);

		reportLog("14.1: Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("15: 'Action' option is displayed");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("15.1: Select action to change visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		reportLog("15.2: Visit from Pr.#5 is displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName4);

		reportLog("16: Change Visit Pr.#3 to Visit Pr.#5 for the different Subject");
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(completedVisit);

		reportLog("17: Select Visit from Pr.#5");
		visitDetaiLPage.selectChangeToVisit(completedVisit);
		visitDetaiLPage.selectObserver(observerAlias);

		reportLog("18.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("18.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("18.3: E-signature window appears after the change has been confirmed");
		visitDetaiLPage.verifyReasonofChangePopUpDisplayed();

		reportLog("18.4: Select control to apply the Reasons to Change");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);

		reportLog("19: Select control to sign the Reason for Change ('Ok')");
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("19.1: Visit is changed successfully and Visit detail screen is displayed");
		visitDetaiLPage.verifySuccessMessage("The visit has been changed successfully");
		visitDetaiLPage.closeSuccessMessage();

		reportLog("20: Check that Observer exists for the destination Subject is selected");
		reportLog("20.1: Observer exists for the destination Subject is selected");

		reportLog("21: Open 'Source Subject Status' for Subject Pr#3");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName3);

		reportLog(
				"21.1: Source Visit becomes blank after a successful change. And all assessments are not assigned to any rater");
		subjectDetailPage.verifyVisitStatus(completedVisit, Constants.blank_Status);

		reportLog("22: Open 'Subject Status History' Pr#5");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName4);

		reportLog("22.1: Open 'Subject Status History' Pr#5");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog(
				"22.2: 'Subject Status History' is updated and has the latest status in order of visit completion by system change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog(
				"23: Open corresponding assessments and Destination Visit has received all corresponding data from the source Assessment:");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedVisit, subjectName4);

		reportLog("23.1: Assessments detail screen;");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("23.2: Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("23.3: close the pdf");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("24: Logout application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

}
