
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

public class FPTC_1619_SubjectMedicationsMedicationHistory_SIP extends BaseTest {
	
private String subjectWithMedications,medicationAddedName,medicationEditedName,medicationDeletedName;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1619_SubjectMedicationsMedicationHistory_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1619  Test Case Name: Subject medications: Medication History

	 * 
	 * ====================================================================================================================
	 */
	
	
	@Test(description = "FP-TC-1619_Subject medications: Medication History", groups = { "" })
	public void FPTC_1619_verifySubjectMedicationsMedicationHistory() {

		reportLog("1.1: Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("2.1:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

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
		
		reportLog("3.2:	List of Medications is displayed");
		subjectDetailPage.verifyMedicationListDisplayed();
		
		//------------------Steps For Edited Medication------------------//
		
		reportLog("4.1:Select Medication Pr.#4");
		subjectDetailPage.clickOnMedicationVisitRow(medicationEditedName);

		reportLog("4.2:Medication Pr.#4.1 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("4.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationEditedName);

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
		subjectDetailPage.verifyStatusInMedicationRow(medicationAddedName, Constants.Medication_Edited_Status);
		
		reportLog("5.1: Select action to open Medication history pop-up");
		subjectDetailPage.clickOnMedicationHistory();

		reportLog("5.2:Medication history pop-up is displayed with options");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayed();

		reportLog("5.3:Refresh control");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithRefreshItemIcon();

		reportLog("5.4:Date & Time");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithDateTime();

		reportLog("5.5:Event details");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithEventsInfo();
		subjectDetailPage.closeMedicationHistoryPopUp();
				
		
		//------------------------Steps For Deleted Medication------------------///
			
		reportLog("6.1:Select Medication Pr.#5");
		subjectDetailPage.selectMedicationFillterValue(Constants.Medication_Filter_Deleted);
		subjectDetailPage.clickOnMedicationVisitRow(medicationDeletedName);

		reportLog("6.2:Medication Pr.#4.1 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("6.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationDeletedName);

		reportLog("6.4:Date Time");
		subjectDetailPage.verifyMedicationDateTimePresent(Constants.MedicationDate);

		reportLog("6.5: Instructions");
		subjectDetailPage.verifyMedicationInstructions();

		reportLog("6.6: Flag");
		subjectDetailPage.verifySetFlagMedicationIsDisplayed();

		reportLog("6.7: Medication History");
		subjectDetailPage.verifyMedicationHistoryLastActionForAddedMedication();

		reportLog("6.8: Notes");
		subjectDetailPage.verifyMedicationNotesLabelIsDisplayed();

		reportLog("6.9: Comment");
		subjectDetailPage.verifyMedicationComment();

		reportLog("6.10 Status in the medication row");
		subjectDetailPage.verifyStatusInMedicationRow(medicationAddedName, Constants.Medication_Deleted_Status);
		
		reportLog("7.1: Select action to open Medication history pop-up");
		subjectDetailPage.clickOnMedicationHistory();

		reportLog("7.2:Medication history pop-up is displayed with options");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayed();

		reportLog("7.3:Refresh control");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithRefreshItemIcon();

		reportLog("7.4:Date & Time");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithDateTime();

		reportLog("7.5:Event details");
		subjectDetailPage.verifyMedicationHistoryPopUpDisplayedWithEventsInfo();
		subjectDetailPage.closeMedicationHistoryPopUp();
		
		//------------------------Steps For Added Medication------------------///
		
		reportLog("8.1:Select Medication Pr.#6");
		subjectDetailPage.selectMedicationFillterValue(Constants.Medication_Filter_Current);
		subjectDetailPage.clickOnMedicationVisitRow(medicationAddedName);

		reportLog("8.2:Medication Pr.#4.1 corresponding details is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("8.3:Medication Name");
		subjectDetailPage.verifyMedicationName(medicationAddedName);

		reportLog("8.4:Date Time");
		subjectDetailPage.verifyMedicationDateTimePresent(Constants.MedicationDate);

		reportLog("8.5: Instructions");
		subjectDetailPage.verifyMedicationInstructions();

		reportLog("8.6: Flag");
		subjectDetailPage.verifySetFlagMedicationIsDisplayed();

		reportLog("8.7: Notes");
		subjectDetailPage.verifyMedicationNotesLabelIsDisplayed();

		reportLog("8.8: Comment");
		subjectDetailPage.verifyMedicationComment();

		reportLog("8.9: Status in the medication row");
		subjectDetailPage.verifyStatusInMedicationRow(medicationAddedName, Constants.Medication_Edited_Status);
		
		reportLog("8.10:Medication History control isn't displayed for 'Added' medications");
		subjectDetailPage.verifyMedicationHistoryControlIsNotDisplayed();
		
		reportLog("8.11: Logout application");
		loginPage.logoutApplication();

		reportLog("8.12: Verify User is Logout from the application");
		loginPage.verifyUserLogout();
		
		
		
	}
}