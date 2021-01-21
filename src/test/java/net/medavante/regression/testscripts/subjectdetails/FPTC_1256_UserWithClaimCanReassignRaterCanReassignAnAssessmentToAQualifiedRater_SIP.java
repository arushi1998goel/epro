package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1256_UserWithClaimCanReassignRaterCanReassignAnAssessmentToAQualifiedRater_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5),	subjectName2 = "SUBJ_2" + generateRandomString(5), subjectName3 = "SUBJ_3" + generateRandomString(5),
			subjectName4 = "SUBJ_4" + generateRandomString(5), subjectName5 = "SUBJ_5" + generateRandomString(5);
	
	 
	protected String hourForAppointment = "08", minuteForAppointment = "00", timeMarker = "AM",
			time = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	private String visit1, visit2, visit3, studyName1, studyName2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1256_UserWithClaimCanReassignRaterCanReassignAnAssessmentToAQualifiedRater_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName1 = properties.getProperty("Test741Study");
		studyName2 = properties.getProperty("AutomationStudyName");
		visit1 = properties.getProperty("NonAssignedRaterVisit");
		visit2 = properties.getProperty("VisitCompleted");
		visit3 = properties.getProperty("Auto_CR_Visit1");

		reportLog("At least one Subject 1 exists in Study/Site in PR#1 where:");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName1, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("Visit in PR#2(ClinRO form) is in editing status");
		subjectDetailPage.clickOnVisitRow(visit1);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("Visit was never assigned to a Rater");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);

		reportLog("Another Subject 2 exists in Study/Site in PR#1");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName1, Constants.ATAssignedRater_10,subjectName2);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		

		reportLog("Visit in PR#2(ClinRO form) is in editing status");
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("Visit is already assigned to an activated Rater of PR#3.2 by the Rater himself");
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		reportLog("Another Subject 3 exists in Study/Site in PR#1 where:");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName1, Constants.ATAssignedRater_10,subjectName3);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Visit in PR#2(ClinRO form) is in editing status");
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog(
				"Visit is already assigned to the Rater of PR#3.4 by the Rater himself (One Rater is deactivated from study/site in PR#1)");
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		reportLog("Another Subject 4 exists in Study/Site in PR#1 where:");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName1, Constants.ATAssignedRater_10,subjectName4);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Visit in PR#2(ClinRO form) is in editing status");
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog(
				"Visit is already assigned to the Rater of PR#3.4 by the Rater himself (One Rater is deactivated from study/site in PR#1)");
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		loginPage.logoutApplication();

		reportLog("Submit the assessment by rater selected for these visits");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName1,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName4);
		studyNavigatorDashBoardPage.searchVisit(visit2);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.navigateOnHomePage();
		loginPage.logoutApplication();
        loginPage.verifyUserLogout();

		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName1,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName4);
		studyNavigatorDashBoardPage.searchVisit(visit2);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
//		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		// deactivate the rater
		reportLog("Navigate to home page");
		subjectDetailPage.navigateToHomePage();

		reportLog("Study is selected");
		 adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.verifyAdminstrationStudiesPageIsOpen();

		reportLog("Search Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName1);

		reportLog("From Sites tab deactivate the Rater#1 of step#4 from the study site");
		AdministrationStudiesSitesPage sitePage = adminstrationStudiesPage.navigateToStudySitesTab();
		sitePage.verifyAdministrationStudiesSitesPage();
		administrationStudiesSitePage.deactivateTheRater(Constants.clinician14Name,Constants.clinician14Name);

		reportLog(
				"At least one Central Rating Visit is configured for the Study of PR#9 with at least one ClinRO form");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName2);
		studyNavigatorDashBoardPage.selectSite(Constants.ChooseSite_ATTester10);

		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName5);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("At least two qualified Clinicians exist for the Study/Site of PR#9 where");
		subjectDetailPage.clickOnVisitRow(visit3);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();

		reportLog("At least one Subject exists in the Study/Site of PR#9 wher Visit in PR#10 is in editing status");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Notification date-time is set to be in future");
		centralRatingAppointmentPage.scheduleNotificationAndAppointmentDateForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("Schedule date-time is set to be in future");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("Pick Clinician with time slot");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.waitSpinnerToBecomeInvisible();
		loginPage.logoutApplication();

		reportLog("Submit the assessment by rater selected for these visits");
		loginPage.loginInApplication(clinician14User, Password);
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName2);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName5);
		studyNavigatorDashBoardPage.searchVisit(visit3);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(clinician14User, Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		loginPage.logoutApplication();

		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName2);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName5);
		studyNavigatorDashBoardPage.searchVisit(visit3);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();

	}

	/**
	 *  ====================================================================================================================
	 * Test Case Id: FP-TC-1256 || Test Case Name:In Portal - User with claim canReassignRater can reassign an assessment to a qualified rater (Old version of Subject Detail screen) 
	 * ====================================================================================================================
	 **/
	
	
	@Test(description = "FP-TC-1256_VerifyUserWithClaimCanReassignRaterCanReassignAnAssessmentToAQualifiedRater", groups = {
			"" })
	public void FPTC_1256_UserWithClaimCanReassignRaterCanReassignAnAssessmentToAQualifiedRater() {

		reportLog(
				"1: Login to the Portal as user of PR#4.(At least one User with claim canReassignRater and without claim canAssignToMe and CanAssignToOthers exists for Study/Site of PR#1)");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName1);

		reportLog("2: Navigate to subject details page of PR#5");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName1);

		reportLog("2.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.2: Visit in PR#5.1 is displayed in the visits list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visit1);

		reportLog("3: Select the visit in PR#5.1 from the visit list Check the Assigned to field value");
		subjectDetailPage.clickOnVisitRow(visit1);

		reportLog("3.1: Field value is Not Assigned");
		subjectDetailPage.verifyRaterNameForNonAssignedVisit(Constants.notAssignedText);

		reportLog("3.2: Field is not editable");
		subjectDetailPage.verifyAssigneToFieldIsNonEditable();

		reportLog("4: Navigate to subject details page of PR#6");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName2);

		reportLog("4.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4.2: Visit in PR#6.1 is displayed in the visits list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visit2);

		reportLog("5: Select the visit in PR#6.1 from the visit list Check the Assigned to field value");
		subjectDetailPage.clickOnVisitRow(visit2);

		reportLog("5.2: Field value is [PR#3.2 Rater First Name] + [PR#3.2 Rater Last Name]");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.ATAssignedRater_10);

		reportLog("5.3: Field is editable and drop down list is enabled");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("6: Select the option to expand the assignee dropdown");
		subjectDetailPage.verifyRaterDropDownIsDisplayed();

		reportLog("6.1: List does not contain rater in PR#3.4");

		reportLog("6.2: List contains raters in PR#3.2 and PR#3.3");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.ATAssignedRater_10);

		reportLog("6.3: List contains the option Not Assigned");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.notAssignedText);

		reportLog(
				"7: Select the option to choose Not Assigned from the drop down Select the option to choose rater in PR#3.3");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("7.1: Assigned to field value changes to Not Assigned");
		subjectDetailPage.selectRaterFromDropDown(Constants.notAssignedText);

		reportLog("7.2: Assigned to field value changed to [PR#3.3 Rater First Name] + [PR#3.3 Rater Last Name]");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);

		reportLog("8: Navigate to subject details page of PR#7");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.clickOnSubject(subjectName3);

		reportLog("8.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("8.2:  Visit in PR#7.1 is displayed in the visits list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visit2);

		reportLog("9: Select the visit in PR#7.1 from the visit list Check the Assigned to field value");
		subjectDetailPage.clickOnVisitRow(visit2);

		reportLog("9.1: Field value is 'Not Assigned'");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);

		reportLog("9.2: Field is editable and drop down list is enabled");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("10: Select the option to expand the assignee dropdown");
		subjectDetailPage.verifyRaterDropDownIsDisplayed();

		reportLog("10.1: List does not contain rater in PR#3.4");

		reportLog("10.2: List contains raters in PR#3.2 and PR#3.3");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.ATAssignedRater_10);

		reportLog("10.3: List contains the option 'Not Assigned'");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.notAssignedText);

		reportLog("11: Select the option to choose rater in PR#3.3");
		subjectDetailPage.clickOnAssignRaterDropDown();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);

		reportLog("11.1: Assigned to field value changed to [PR#3.3 Rater First Name] + [PR#3.3 Rater Last Name]");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.ATAssignedRater_10);

		reportLog("12: Navigate to subject details page of PR#8");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName4);

		reportLog("12.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("12.2: Visit in PR#8.1 is displayed in the visits list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visit2);

		reportLog(
				"13: Select the visit in PR#8.1 from the visit list Check the 'Submitted By' and 'Assigned to' field values");
		subjectDetailPage.clickOnVisitRow(visit2);
		subjectDetailPage.verifyEditVisitIconIsDisplayed();
		subjectDetailPage.clickOnEditVisitIcon();
		subjectDetailPage.verifyVisitStatus(visit2, Constants.Editing_Status);

		reportLog("13.1: Submitted By field value is: [PR#3.4 Rater First Name] + [PR#3.4 Rater Last Name]");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);

		reportLog("13.2: Assigned to field is editable and drop down list is enabled");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("14: Select the option to expand the assignee dropdown");
		subjectDetailPage.verifyRaterDropDownIsDisplayed();

		reportLog("14.1: List does not contain rater in PR#3.4");

		reportLog("14.2: List contains raters in PR#3.2 and PR#3.3");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.ATAssignedRater_10);
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.ATAssignedRater_10);

		reportLog("14.3: List contains the option 'Not Assigned'");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.notAssignedText);

		reportLog("15: Select the option to choose rater in PR#3.3");
		subjectDetailPage.clickOnAssignRaterDropDown();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);

		reportLog("15.1: Assigned to field value changed to [PR#3.3 Rater First Name] + [PR#3.3 Rater Last Name]");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.ATAssignedRater_10);

		reportLog("16: Login to the Portal as user of PR#11.");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.selectStudy(studyName2);
		studyNavigatorDashBoardPage.selectSite(Constants.ChooseSite_ATTester10);

		reportLog("17: Navigate to subject details page of PR#13");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName5);

		reportLog("17.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("17.2: Visit in PR#13.1 is displayed in the visits list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visit3);

		reportLog(
				"18: Select the visit in PR#13.1 from the visit list and Check the Submitted By and Assigned to field values");
		subjectDetailPage.clickOnVisitRow(visit3);
		subjectDetailPage.verifyEditVisitIconIsDisplayed();
		subjectDetailPage.clickOnEditVisitIcon();
		subjectDetailPage.verifyVisitStatus(visit3, Constants.Editing_Status);

		reportLog("18.1: 'Submitted By' field value is: [PR#12.1 Rater First Name] + [PR#12.1 Rater Last Name]");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);

		reportLog("18.2: 'Assigned To' field value is: 'Not Assigned'");

		reportLog("18.3: Assigned to field is editable and drop down list is enabled");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("19: Select the option to expand the assignee dropdown");
		subjectDetailPage.verifyRaterDropDownIsDisplayed();

		reportLog("19.1:  List does not contain rater in PR#12.2");

		reportLog("19.2: List contains rater in PR#12.1");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.clinician14Name);
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.clinician15Name);

		reportLog("19.3: List contains the option 'Not Assigned'");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Constants.notAssignedText);

		reportLog("20: Select the option to choose rater in PR#12.1");
		subjectDetailPage.clickOnAssignRaterDropDown();
		subjectDetailPage.selectRaterFromDropDown(Constants.clinician15Name);

		reportLog("20.1: Assigned to field value changed to [PR#12.1 Rater First Name] + [PR#12.1 Rater Last Name]");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.clinician15Name);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
