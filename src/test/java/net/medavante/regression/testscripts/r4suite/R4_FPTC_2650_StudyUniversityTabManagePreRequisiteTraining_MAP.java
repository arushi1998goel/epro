/**
 *@author 
* @date 27/05/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2650 || Test Case Name:Study Virgil University tab - Manage Prerequisite Training- V28
*  *  pre-qualification : 1. At least 1 user with canManageStudies claim exists
                          2. At least one studies with enabled Virgil University product type exist for test:
                          2.1 Study with no activated Prerequisite Training.
                          2.2 Study with activated Prerequisite Training.
                          3. At least 4 trainings with type Prerequisite exist:
                          3.1 Active Prerequisite training associated with study Pr. #2.2 and assigned to at least one rater;
                          3.2 Active Prerequisite training not yet associated with studies;
                          3.3 Inactive Prerequisite training.
                          3.4 Active Prerequisite training associated with another study (not Pr. #2.2)	
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesVirgilUniversityPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_2650_StudyUniversityTabManagePreRequisiteTraining_MAP extends BaseTest {

	private String study,studyNew;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2650_StudyUniversityTabManagePreRequisiteTraining_MAP(String browser) {
		super(browser);
	}
	

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study2180");
		studyNew=properties.getProperty("Study2650");
	}
	
	@Test(description="FP-TC-2650--Study University tab - Manage Prerequisite Training- V28")
	public void R4_FPTC_2650_StudyUniversityTabManagePreRequisiteTraining()
	{
		
		reportLog("2.0: Log in to MA Portal as User from Pr. #1" );
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			
		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0:  Navigate to Configure -> Study Setup");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.0.1:  Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.0.2:  Search " + studyNew + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyNew);
		
		reportLog("3.0.3: Study configuration is displayed" );
		adminstrationStudiesPage.verifyStudyConfigurationElementsAreDisplayed();
		
		reportLog("3.0.3: Navigate to University tab");
		adminStudiesVirgilUniversityPage= adminstrationStudiesPage.navigateToSubTabs(Constants.virgilUniversitySubTab,AdministrationStudiesVirgilUniversityPage.class );
		
		reportLog("3.1: Study University tab is displayed");
		adminStudiesVirgilUniversityPage.verifyVirgilUniversityTabIsDisplayed();
		
		reportLog("3.2: Prerequisite Training section is displayed with No activated prerequisite training");
		adminStudiesVirgilUniversityPage.verifyPreRequisiteWithNoActivatedtrainingDisplayed();
		
		reportLog("4.0: Select to edit Prerequisite Training");
		adminStudiesVirgilUniversityPage.selectPreRequisiteEditControl();
		
		reportLog("4.1: Manage Prerequisite Training modal window is displayed with:");
		adminStudiesVirgilUniversityPage.verifyManagePreRequisitePopupDisplayed();
		
		reportLog("4.2: Search field is displayed");
		adminStudiesVirgilUniversityPage.verifySearchFieldIsDisplayed();
		
		reportLog("4.3: Information text");
		adminStudiesVirgilUniversityPage.verifyInformationTextIsdisplayed();
		
		reportLog("4.4: The list of trainings is displayed, grouped by the Use in Studies:");
		adminStudiesVirgilUniversityPage.verifyTrainingListGroupedByUseInStudyDisplayed();
		
		reportLog("4.5: Not in Use - training Pr.# 3.2 is displayed");
		adminStudiesVirgilUniversityPage.verifyNOtInUseTrainingIsDisplayed();
		
		reportLog("4.6:  In Use for Other Studies - trainings Pr.# 3.1 and 3.4 are displayed");
		adminStudiesVirgilUniversityPage.verifyTrainingIsDisplayed(Constants.PrerequisiteTraining_1);
		adminStudiesVirgilUniversityPage.verifyTrainingIsDisplayed(Constants.PrerequisiteTraining_2);
		
		reportLog("4.7: Save option, disabled");
		adminStudiesVirgilUniversityPage.verifySaveButtonIsDisplayed();
		adminStudiesVirgilUniversityPage.verifySaveButtonIsDisabled();
		
		reportLog("4.8: Cancel and close options");
		adminStudiesVirgilUniversityPage.verifyCancelBtnIsDisplayed();
		adminStudiesVirgilUniversityPage.verifyCloseControlIDisplayed();
		
		reportLog("5.0: Populate search field with title of training Pr# 3.3");
		adminStudiesVirgilUniversityPage.populateTrainngNameinSearchField(Constants.PrerequisiteTraining_3);
		
		reportLog("5.1: Training Pr.#3.3 is not displayed in the list");
		adminStudiesVirgilUniversityPage.verifyTrainingIsNotDisplayed(Constants.PrerequisiteTraining_3);
		
		reportLog("6.0: Populate search field with title of training Pr# 3.1");
		adminStudiesVirgilUniversityPage.populateTrainngNameinSearchField(Constants.PrerequisiteTraining_2);

		reportLog("6.1: Training Pr.#3.1 is displayed in the list.");
		adminStudiesVirgilUniversityPage.verifyTrainingIsDisplayed(Constants.PrerequisiteTraining_2);

		reportLog("6.2: Select training Pr# 3.1");
		adminStudiesVirgilUniversityPage.selectTraining(Constants.PrerequisiteTraining_2);
		
		reportLog("6.3:  Training Pr. #3.1 is selected");
		adminStudiesVirgilUniversityPage.verifyTrainingIsSelected(Constants.PrerequisiteTraining_2);
		
		reportLog("6.4: Hover over the details icon");
		reportLog("- Following training details are displayed in the tooltip:\r\n" + 
				"-- Name: Display name\r\n" + 
				"-- Training Type: Prerequisite Training\r\n" + 
				"-- Description: Display description (if exists)");
		adminStudiesVirgilUniversityPage.mouseHoverOnTrainingInfoIcon(Constants.PrerequisiteTraining_2);
		
		reportLog("6.5: Save option is available");
		adminStudiesVirgilUniversityPage.verifySaveButtonIsDisplayed();
		
		reportLog("7.0: Select Cancel option");
		adminStudiesVirgilUniversityPage.selectCancelControl();
		
		reportLog("7.1: Confirmation modal window is displayed with text "
				+ "'Are you sure you want to leave this page? The changes you made will be lost.' and options:");
		adminStudiesVirgilUniversityPage.verifyConfirmationMessageOnCancelPopUp();
		
		reportLog("7.2: Yes, close form");
		adminStudiesVirgilUniversityPage.verifyYesCloseFormBUttonDisplayed();
		
		reportLog("7.3: No, back to form");
		adminStudiesVirgilUniversityPage.verifyNoBackToFormButtonDisplayed();

		reportLog("8.0: Select No, back to form option");
		adminStudiesVirgilUniversityPage.selectNoBackToFormOption();
		
		reportLog("8.1: Manage Prerequisite Training modal window is displayed with the latest updates");
		adminStudiesVirgilUniversityPage.verifyManagePreRequisitePopupDisplayed();
		adminStudiesVirgilUniversityPage.verifyTrainingIsSelected(Constants.PrerequisiteTraining_2);
		
		reportLog("9.0: Select Cancel option");
		adminStudiesVirgilUniversityPage.selectCancelControl();

		reportLog("9.1: Select 'Yes, close form' option");
		adminStudiesVirgilUniversityPage.selectYesCloseformOption();
		
		reportLog("9.2: Manage Prerequisite Training modal window is closed");
		adminStudiesVirgilUniversityPage.verifyManagePreRequisitePopupNotDisplayed();
		
		reportLog("9.3: University tab is displayed without changes");
		adminStudiesVirgilUniversityPage.verifyPreRequisiteWithNoActivatedtrainingDisplayed();

		reportLog("10.0: Select to edit Prerequisite Training");
		adminStudiesVirgilUniversityPage.selectPreRequisiteEditControl();

		reportLog("10.1: Select training Pr# 3.1");
		adminStudiesVirgilUniversityPage.selectTraining(Constants.PrerequisiteTraining_1);

		reportLog("10.2: Select Save option");
		adminStudiesVirgilUniversityPage.selectSaveControl();
		
		reportLog("10.3: Manage Prerequisite Training modal window is closed.");
		adminStudiesVirgilUniversityPage.verifyManagePreRequisitePopupNotDisplayed();

		reportLog("10.4: University tab is displayed");
		adminStudiesVirgilUniversityPage.verifyVirgilUniversityTabIsDisplayed();
		
		reportLog("10.5: Training Pr. #3.1 is displayed in Activated section with the Activated date");
		adminStudiesVirgilUniversityPage.verifyActivatedTrainingIsDisplayedInPreRequisiteSection(Constants.PrerequisiteTraining_1);
		
		reportLog("10.6: Deactivated section with count is collapsed");
		adminStudiesVirgilUniversityPage.verifyDeactivatedELearningSectionTrainingCollapsedDisplayed();
		
		reportLog("11.0: Open Study Pr.#2.2 -> University tab");
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		adminStudiesVirgilUniversityPage= adminstrationStudiesPage.navigateToSubTabs(Constants.virgilUniversitySubTab,
				AdministrationStudiesVirgilUniversityPage.class );
		
		reportLog("11.1: Study University tab is displayed");
		adminStudiesVirgilUniversityPage.verifyVirgilUniversityTabIsDisplayed();

		reportLog("11.2: Prerequisite Training section is displayed with the chosen training");
		adminStudiesVirgilUniversityPage.verifyActivatedTrainingIsDisplayedInPreRequisiteSection(Constants.PrerequisiteTraining_1);

		reportLog("12.0: Select to edit Prerequisite Training");
		adminStudiesVirgilUniversityPage.selectPreRequisiteEditControl();
		
		reportLog("12.1: Manage Prerequisite Training modal window is displayed with:");
		adminStudiesVirgilUniversityPage.verifyManagePreRequisitePopupDisplayed();

		reportLog("12.2: Search field is displayed");
		adminStudiesVirgilUniversityPage.verifySearchFieldIsDisplayed();

		reportLog("12.3: The list of trainings is displayed, grouped by the Use in Studies:");
		adminStudiesVirgilUniversityPage.verifyTrainingListGroupedByUseInStudyDisplayed();

		reportLog("12.4: In Use - training Pr.# 3.1 is displayed");
		adminStudiesVirgilUniversityPage.verifyInUseTrainingIsDisplayed(Constants.PrerequisiteTraining_1);
		
		reportLog("12.5: Not in Use - training Pr.# 3.2 is displayed");
		adminStudiesVirgilUniversityPage.verifyNOtInUseTrainingIsDisplayed();

		reportLog("12.6: In Use for Other Studies - training Pr.# 3.4 is displayed");
		adminStudiesVirgilUniversityPage.verifyTrainingIsDisplayed(Constants.PrerequisiteTraining_4);

		reportLog("12.7: Save option, disabled");
		adminStudiesVirgilUniversityPage.verifySaveButtonIsDisplayed();
		adminStudiesVirgilUniversityPage.verifySaveButtonIsDisabled();
		
		reportLog("12.8: Cancel and close options");
		adminStudiesVirgilUniversityPage.verifyCancelBtnIsDisplayed();
		adminStudiesVirgilUniversityPage.verifyCloseControlIDisplayed();
		
		reportLog("Restoring Prequisite for Further Run");
		adminStudiesVirgilUniversityPage.selectCancelControl();
		adminstrationStudiesPage.searchAndClickOnStudy(studyNew);
		adminStudiesVirgilUniversityPage= adminstrationStudiesPage.navigateToSubTabs(Constants.virgilUniversitySubTab,
				AdministrationStudiesVirgilUniversityPage.class );
		adminStudiesVirgilUniversityPage.selectPreRequisiteEditControl();
		adminStudiesVirgilUniversityPage.removeSelectedTraining();
		adminStudiesVirgilUniversityPage.selectSaveControl();
		adminStudiesVirgilUniversityPage.verifyVirgilUniversityTabIsDisplayed();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout(); 


		
	}
}
