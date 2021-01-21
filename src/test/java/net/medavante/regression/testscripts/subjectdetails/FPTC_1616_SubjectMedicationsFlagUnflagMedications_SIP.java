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

public class FPTC_1616_SubjectMedicationsFlagUnflagMedications_SIP extends BaseTest {
	
	
	private String subjectWithMedications,MedicationWithoutFlag;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1616_SubjectMedicationsFlagUnflagMedications_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectWithMedications = properties.getProperty("Subject2153");
		MedicationWithoutFlag=properties.getProperty("MedicationUnflagged");
	}
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1616  Test Case Name:Subject medications: Flag/Unflag medications
	 * 
	 * ====================================================================================================================
	 */
	
	
	@Test(description = "FP-TC-1616_Subject medications: Flag/Unflag medications", groups = { "" })

	public void FPTC_1616_verifySubjectMedicationsFlagUnflagMedications() {

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
		subjectDetailPage.clickOnMedicationVisitRow(MedicationWithoutFlag);
		
		reportLog("4.2:	Medication Pr.#4 detail is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
			
		reportLog("4.3:	Flag is not set");
		subjectDetailPage.verifyFlagIsNotDisplayedForMedicationInMedicationRow(MedicationWithoutFlag);
	
		reportLog("4.4:	Option to set flag is enable");
		subjectDetailPage.verifySetFlagMedicationIsEnabled();
		
		reportLog("5.1:Set Flag for Medication Pr.#4");	
		subjectDetailPage.clickOnSetFlag();
		
		reportLog("5.2:Flag is set");
		subjectDetailPage.verifyFlagIsSetForMedicationInMedicationRow(MedicationWithoutFlag);
		
		reportLog("5.3:Option to clear flag is enable");
		subjectDetailPage.verifyClearFlagIconDisplayed();
		
		reportLog("6.1:	Clear Flag for Medication Pr.#4");
		subjectDetailPage.clickOnClearFlag();
		
		reportLog("6.2: Flag is not set");
		subjectDetailPage.verifyFlagIsNotDisplayedForMedicationInMedicationRow(MedicationWithoutFlag);
		
		reportLog("6.3:Option to set flag is enable");
		subjectDetailPage.verifySetFlagMedicationIsEnabled();
		
		reportLog("6.4: Logout application");
		loginPage.logoutApplication();

		reportLog("6.5: Verify User is Logout from the application");
		loginPage.verifyUserLogout();
		
	}
  
}
