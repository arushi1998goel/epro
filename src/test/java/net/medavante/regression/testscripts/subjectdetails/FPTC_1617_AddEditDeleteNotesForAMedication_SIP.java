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

public class FPTC_1617_AddEditDeleteNotesForAMedication_SIP extends BaseTest {

	private String subjectWithMedications, MedicationWithNotes, noteText = generateRandomString(4),
			noteText2 = generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1617_AddEditDeleteNotesForAMedication_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectWithMedications = properties.getProperty("Subject2153");
		MedicationWithNotes = properties.getProperty("MedicationNotes");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1617 Test Case Name:Subject medications:
	 * Add/Edit/Delete notes for a medication
	 * 
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = " FP-TC-1617_Subject medications: AddEditDelete notes for a medication", groups = { "" })
	public void FPTC_1617_verifyAddEditDeleteNotesForAMedication() {

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

		reportLog("4.1:Select Medication Pr.#4");
		subjectDetailPage.clickOnMedicationVisitRow(MedicationWithNotes);

		reportLog("4.2:	Medication Pr.#4 detail is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("5.1:Add Notes text");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("5.2:	Created Note text is displayed for corresponding Medication Pr.#4");
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText);

		reportLog("6.1:Edit Notes text from Step#5");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText2);
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("6.2:Edited Note text is displayed for corresponding Medication Pr.#4");
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText2);

		reportLog("7.1:	Delete Notes text from Step#6");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("7.2:Deleted Note text isn't displayed for corresponding Medication Pr.#4");
		subjectDetailPage.verifyNoteTextOnNotesSection(null);

		reportLog("7.3: Logout application");
		loginPage.logoutApplication();

		reportLog("7.4: Verify User is Logout from the application");
		loginPage.verifyUserLogout();
	}

}
