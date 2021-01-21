package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1432_StudyNavigatorMedications_SIP extends BaseTest {

	private String medicationName,subjectMedicationName,medicationDateTime="19-JUL-2018 2:02AM";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1432_StudyNavigatorMedications_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		medicationName = properties.getProperty("MedicationName2593");
		subjectMedicationName=properties.getProperty("SubjectWithMedications");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1432 Test Case Name:StudyNavigator Medications
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1432_StudyNavigator Medications", groups = { "" })
	public void FPTC_1432_verifyStudyNavigatorMedication() {

		reportLog("1.1:	Log in to the Portal as User from PR#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User from PR#2 is logged in");
		dashBoardPage.verifyMedavantePortalPage();   

		reportLog("2.1:Navigate to Study Navigator -> Study PR#1");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);

		reportLog("2.2:	Study Navigator for Study PR#1 is displayed with expanded Left side panel");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayedWithMenuIconAndTiles();
		
		reportLog("3.1:Select Medications menu item");
		studyNavigatorDashBoardPage.navigateToMedicationListing();

		reportLog("3.2:	Selected menu item displayed with its filter and list blocks");
		studyNavigatorDashBoardPage.verifySelectedMenuItemDisplayedWithFilter();

		reportLog("4.1:	Check the 'Filter by' block");
		studyNavigatorDashBoardPage.verifyFilterByBlockDisplayed();

		reportLog("4.2:'Filter by' block is displayed with the following items:");
		reportLog("4.3:Item title");
		studyNavigatorDashBoardPage.verifyItemTitle(Constants.Dates_FilterBy);
		studyNavigatorDashBoardPage.verifyItemTitle(Constants.Other_FilterBy);

		reportLog("4.4:All option with its value (selected by default) ");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_All);
	
		reportLog("4.5:Today option with its value");
		studyNavigatorDashBoardPage.verifyMenuItemFiltersDisplayedWithValues(Constants.CategoryFilter_Today,"0");

		reportLog("4.6:Past Week option with its value");
		studyNavigatorDashBoardPage.verifyMenuItemFiltersDisplayedWithValues(Constants.CategoryFilter_PastWeek,"0");

		reportLog("5.1:Select Today option");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_Today);

		reportLog("5.2:Today option is selected");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_Today);

		reportLog("5.3:List block displayed according to the selected filter");
		studyNavigatorDashBoardPage.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_Today);

		reportLog("5.4:	Select Past Week option");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_PastWeek);

		reportLog("6.2:Past Week option is selected");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("6.3: List block displayed according to the selected filter");
		studyNavigatorDashBoardPage.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("7.1:	Refresh the page");
		studyNavigatorDashBoardPage.refreshPage();
		
		reportLog("7.2:	The page is refreshed");
		studyNavigatorDashBoardPage.verifySelectedMenuItemDisplayedWithFilter();
	
		reportLog("7.3:Medications menu item is selected");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("8.1:Check the lists block");
		studyNavigatorDashBoardPage.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_PastWeek);


		reportLog(
				"8.2: Medication (with Name of the medication that subject updated and includes newly added, changed an existing one or deleted an existing one)");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.verifyMedicationIsDisplayedInMedicationList(medicationName);
		
		reportLog("8.3: Dosage (includes the quantity of dosage and time frequency)");
		studyNavigatorDashBoardPage.verifyMedicationValuesDisplayedCorrectly(medicationName,Constants.Medication_Dosage,"1");
		
		reportLog("8.4:Subject (clickable link)");
		studyNavigatorDashBoardPage.verifyMedicationSubjectLinkIsClickable(medicationName,subjectMedicationName);

		reportLog("8.5:Event (with values: Added/Edited/Deleted)");
		studyNavigatorDashBoardPage.verifyMedicationValuesDisplayedCorrectly(medicationName,Constants.Medication_Event_Status,"3");

		reportLog("8.6:Date/Time");
		studyNavigatorDashBoardPage.verifyMedicationValuesDisplayedCorrectly(medicationName,medicationDateTime,"4");

		reportLog("8.7: Flag (empty or with 'Yes' icon)");
		studyNavigatorDashBoardPage.verifyMedicationValuesDisplayedCorrectly(medicationName,"","5");

		reportLog("8.8:Site");
		studyNavigatorDashBoardPage.verifyMedicationValuesDisplayedCorrectly(medicationName,Constants.Site_forFilters,"6");

		reportLog("9.1:Click to select any Medication name");
		studyNavigatorDashBoardPage.clickOnMedicationByMedicationAndSubjectName(medicationName,subjectMedicationName);

		reportLog("9.2:Medications details screen is displayed");
		studyNavigatorDashBoardPage.verifyMedicationPopUpDisplayed();
		studyNavigatorDashBoardPage.clickOnMedicationCloseButton();

		reportLog("10.1:Click to select any Subject");
		studyNavigatorDashBoardPage.clickOnMedicationSubjectLink(medicationName,subjectMedicationName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("10.2:Select Medications from drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Medication);
		
		reportLog("10.3:Medications list is displayed on Subject details screen for the selected Subject");
		subjectDetailPage.verifyMedicationListDisplayed();

		reportLog("10.4:Navigate back to the Study Navigator for Study PR#1");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		reportLog("11.2:Medications menu item is selected and displayed");
		studyNavigatorDashBoardPage.verifyMenuItemSelectedDisplayed(Constants.StudyDashBoard_columnName_Medication);
		
		reportLog("11.3: Logout application");
		loginPage.logoutApplication();

		reportLog("11.4: Verify user is at logout Page");
		loginPage.verifyUserLogout();


	}

}
