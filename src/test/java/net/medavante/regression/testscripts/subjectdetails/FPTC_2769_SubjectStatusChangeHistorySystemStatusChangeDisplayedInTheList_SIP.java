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

public class FPTC_2769_SubjectStatusChangeHistorySystemStatusChangeDisplayedInTheList_SIP extends BaseTest {

	protected String subjectName__New_2103 = "SUBJ_New_" + generateRandomString(5), 
			subjectName__Screened_2103 = "SUBJ_Screened_" + generateRandomString(5),
	subjectName__Enrolled_2103 ="SUBJ_Enrolled_" + generateRandomString(5),
	subjectName__Completed_2103 = "SUBJ_Completed_" + generateRandomString(5), 
	subjectName_CompVisit = "SUBJ_CompVisit_" + generateRandomString(5),
	subjectName = "Sub_" + generateRandomString(5);

	private String visit_screened, visit_enrolled, visit_completed;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2769_SubjectStatusChangeHistorySystemStatusChangeDisplayedInTheList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visit_screened = properties.getProperty("visit_screened");
		visit_enrolled = properties.getProperty("visit_enrolled");
		visit_completed = properties.getProperty("visit_completed");
		visitName = properties.getProperty("visitName");
	
		reportLog("#pr6:Creating1 Subject with status NEW ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName__New_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("pr#3:Creating 1 Subject with completed Visits from Pr#3, exists i.e 1 Visit is configured which change Subject status from NEW to Screened "
				);
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName__Screened_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_screened);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackToDashBoard();

		reportLog("pr#4:Creating 1 Subject with completed Visits from Pr#3 and Pr#4--Screened to Enrolled");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName__Enrolled_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_screened);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.searchSubject(subjectName__Enrolled_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__Enrolled_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_enrolled);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		
		reportLog("#pr5: At least 1 Visit is configured which change Subject status from Enrolled to Completed");
		
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName__Completed_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_enrolled);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
				
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.searchSubject(subjectName__Completed_2103);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__Completed_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_completed);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
			
		reportLog("pr#7:At least 1 Subject with completed Visits from Pr#3, exists");
		
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName_CompVisit);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("pr#8:At least 1 Subject with completed Visits from Pr#3,pr#5 exists");
		
		reportLog("pr#9:Creating Subject with completed Visits from Pr#3, Pr#4, Pr#5, exists");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_screened);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnSubjectLink();
		assessmentDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.Status_Screened);
		subjectDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();

}
	
	/**
	 * ==========================================================================
	 * Test Case Id: FP-TC-2769  Test Case Name: Portal: Subject Status Change History - System status change displayed in the list 

	 * ===========================================================================
	 * 
	 */

	@Test(description = "FP-TC-2769_Subject Status Change History - System status change displayed in the list", groups = { "" })
		public void FPTC_2769_SystemStatusChangeDisplayedInHistoryListWithTheCorrespondingInformation() {

		reportLog("1.1 Log in to Portal as a User from PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.2 Navigate to the Subject details page from Pr#6 by the user from Pr#2 ");
		studyNavigatorDashBoardPage.searchSubject(subjectName__New_2103);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__New_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.3verify  control to open Subject history list is available on the page. ");
		subjectDetailPage.verifyShowHistoryIconDisplayed();

		reportLog("2.1Select the control to open Subject history modal window");
		subjectDetailPage.clickOnShowHistory();

		reportLog("2.2Status Subject History modal window is opened. ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("2.3verify  The record that the status is NEW and it was changed by System, is available");
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_New, Constants.Status_System);

		reportLog("2.4Close History Modal window Popup");
		subjectDetailPage.clickOnCloseButtonInHistoryModalPopUp();

		subjectDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("3.1. Navigate to the Subject details page from Pr#6 by the user from Pr#2 ");
		studyNavigatorDashBoardPage.searchSubject(subjectName__Screened_2103);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__Screened_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.2verify  control to open Subject history list is available on the page. ");
		subjectDetailPage.verifyShowHistoryIconDisplayed();

		reportLog("3.3Select the control to open Subject history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("3.4Status Subject History modal window is opened. ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("4.1 Records are sorted by date, the most recent on top. ");
		subjectDetailPage.verifyDateAtTop();

		reportLog(
				"4.2The record that the status is Screened and it was changed by System, is available, record that the status is NEW and it was changed by System, is available");
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_Screened, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_New, Constants.Status_System);

		reportLog("4.3Close History Modal window Popup");
		subjectDetailPage.clickOnCloseButtonInHistoryModalPopUp();

		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("5.1.Navigate to the Subject details page from Pr#8 by the user from Pr#2 ");
		studyNavigatorDashBoardPage.searchSubject(subjectName__Enrolled_2103);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__Enrolled_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("5.2verify  control to open Subject history list is available on the page. ");
		subjectDetailPage.verifyShowHistoryIconDisplayed();

		reportLog("6.1Select the control to open Subject history modal window");
		subjectDetailPage.clickOnShowHistory();

		reportLog("6.2Status Subject History modal window is opened. ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("6.3Records are sorted by date, the most recent on top. ");
		subjectDetailPage.verifyDateAtTop();

		reportLog("6.4 The record that the status is Enrolled and it was changed by System, is available"
				+ "The record that the status is Screened and it was changed by System, is available"
				+ "The record that the status is NEW and it was changed by System, is available. ");
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_Enrolled, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_Screened, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_New, Constants.Status_System);

		reportLog("6.5: Close History Modal window Popup");
		subjectDetailPage.clickOnCloseButtonInHistoryModalPopUp();
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("7.1 Navigate to the Subject details page from Pr#9 by the user from Pr#2 ");
		studyNavigatorDashBoardPage.searchSubject(subjectName__Completed_2103);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName__Completed_2103);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("7.2 The control to open Subject history list is available on the page");
		subjectDetailPage.verifyShowHistoryIconDisplayed();

		reportLog("8.1Select the control to open Subject history modal window");
		subjectDetailPage.clickOnShowHistory();

		reportLog("8.2 Status Subject History modal window is opened.");
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("8.3 Records are sorted by date, the most recent on top.");
		subjectDetailPage.verifyDateAtTop();

		reportLog("8.9The record that the status is Completed and it was changed by System, is available. "
				+ "- The record that the status is Enrolled and it was changed by System, is available. "
				+ "- The record that the status is Screened and it was changed by System, is available."
				+ "- The record that the status is NEW and it was changed by System, is available ");
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Complete_Status, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_Enrolled, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_Screened, Constants.Status_System);
		subjectDetailPage.verifyHistoryModalWindowStatus(Constants.Status_New, Constants.Status_System);
		
		reportLog("8.10: Close History Modal window Popup");
		subjectDetailPage.clickOnCloseButtonInHistoryModalPopUp();

		reportLog("9: Logout application");
		loginPage.logoutApplication();

		reportLog("9.1: Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
