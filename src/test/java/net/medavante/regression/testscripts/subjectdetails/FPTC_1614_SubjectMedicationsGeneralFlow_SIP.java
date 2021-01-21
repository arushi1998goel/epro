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

public class FPTC_1614_SubjectMedicationsGeneralFlow_SIP extends BaseTest {

	private String subjectWithMedications, medicationAddedName, medicationEditedName, medicationDeletedName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1614_SubjectMedicationsGeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectWithMedications = properties.getProperty("SubjectWithMedications");
		medicationAddedName = properties.getProperty("MedicationAdded");
		medicationEditedName = properties.getProperty("MedicationEdited");
		medicationDeletedName = properties.getProperty("MedicationDeleted");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1614 Test Case Name:Subject medications: General
	 * flow
	 * ====================================================================================================================
	 * 
	 * 
	 */
	@Test(description = "FP-TC-1614_Subject medications: General flow", groups = { "" })
	public void FPTC_1614_VerifySubjectMedicationsGeneralFlow() {

		reportLog("1.1: Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.2:Study Pr.#1 Subject Listing screen");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectWithMedications);

		reportLog("2.3:Select Subject Pr.#3");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectWithMedications);

		reportLog("2.4:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.1:	Select Medications from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Medication);

		reportLog("3.2:List of Current Medications Pr.#4.1 and Pr.# 4.2 is displayed");
		subjectDetailPage.verifyMedicationPresentInRow(medicationAddedName);
		subjectDetailPage.verifyMedicationPresentInRow(medicationEditedName);

		reportLog("3.3:Order by: Date of action, with latest on top");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("4.1:Select Medication Pr.#4.1");
		subjectDetailPage.clickOnMedicationVisitRow(medicationAddedName);

		reportLog("4.2:Medication Pr.#4.1 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("4.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationAddedName);

		reportLog("4.4:Date Time");
		subjectDetailPage.verifyMedicationDateTimePresent(Constants.MedicationDate);

		reportLog("4.5: Instructions");
		subjectDetailPage.verifyMedicationInstructions();

		reportLog("4.6: Flag");
		subjectDetailPage.verifySetFlagMedicationIsDisplayed();

		reportLog("4.7: Medication History");
		subjectDetailPage.verifyMedicationHistoryLastActionForAddedMedication();

		reportLog("4.8: Notes");
		subjectDetailPage.verifyMedicationNotesLabelIsDisplayed();

		reportLog("4.9: Comment");
		subjectDetailPage.verifyMedicationComment();

		reportLog("4.10 Status in the medication row");
		subjectDetailPage.verifyStatusInMedicationRow(medicationAddedName, Constants.Medication_Added_Status);

		reportLog("5.1:	Select Medication Pr.#4.2");
		subjectDetailPage.clickOnMedicationVisitRow(medicationEditedName);

		reportLog("5.2:Medication Pr.#4.2 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("5.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationEditedName);

		reportLog("5.4:Date Time");
		subjectDetailPage.verifyMedicationDateTimePresent(Constants.MedicationDate);

		reportLog("5.5: Instructions");
		subjectDetailPage.verifyMedicationInstructions();

		reportLog("5.6: Flag");
		subjectDetailPage.verifySetFlagMedicationIsDisplayed();

		reportLog("5.7: Medication History");
		subjectDetailPage.verifymedicationHistory();

		reportLog("5.8: Notes");
		subjectDetailPage.verifyMedicationNotesLabelIsDisplayed();

		reportLog("5.9: Comment");
		subjectDetailPage.verifyMedicationComment();

		reportLog("5.10 Status in the medication row");
		subjectDetailPage.verifyStatusInMedicationRow(medicationEditedName, Constants.Medication_Edited_Status);

		reportLog("6.1:	Switch from Current medications to Deleted");
		subjectDetailPage.selectMedicationFillterValue(Constants.Medication_Filter_Deleted);

		reportLog("6.1:List of Deleted Medications Pr.#4.3 is displayed");
		subjectDetailPage.verifyMedicationListDisplayed();

		reportLog("7.1:Select Medication Pr.#4.3");
		subjectDetailPage.clickOnMedicationVisitRow(medicationDeletedName);

		reportLog("7.2:Medication Pr.#4.3 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("7.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationDeletedName);

		reportLog("7.4:Date Time");
		subjectDetailPage.verifyMedicationDateTimePresent(Constants.MedicationDate);

		reportLog("7.5: Instructions");
		subjectDetailPage.verifyMedicationInstructions();

		reportLog("7.6: Flag");
		subjectDetailPage.verifySetFlagMedicationIsDisplayed();

		reportLog("7.7: Medication History");
		subjectDetailPage.verifymedicationHistory();

		reportLog("7.8: Notes");
		subjectDetailPage.verifyMedicationNotesLabelIsDisplayed();

		reportLog("7.9: Comment");
		subjectDetailPage.verifyMedicationComment();

		reportLog("7.10: Status in the medication row");
		subjectDetailPage.verifyStatusInMedicationRow(medicationDeletedName, Constants.Medication_Deleted_Status);

		reportLog("8.1: Select action to open Medication history pop-up");
		subjectDetailPage.clickOnMedicationHistory();

		reportLog("8.2:Medication history pop-up is displayed with options");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayed();

		reportLog("8.3:Refresh item");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithRefreshItemIcon();

		reportLog("8.4:Date & Time");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithDateTime();

		reportLog("8.5:Event details");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithEventsInfo();
		subjectDetailPage.closeMedicationHistoryPopUp();

		reportLog("8.6:Logout from the application");
		loginPage.logoutApplication();

		reportLog("8.7:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}