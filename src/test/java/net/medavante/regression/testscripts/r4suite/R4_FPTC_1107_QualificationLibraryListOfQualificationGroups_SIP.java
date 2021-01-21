/**
 *@author 
* @date 08/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-1107   Test Case Name: Qualification Library - List of Qualification Groups- V22
*  pre-qualification : 1. At least one Study and multiple Form Groups (Scales) exist for test
                       2. Multiple Qualification Groups are available in the Qualification Library
                       3. At least one Qualification Group is Live (currently being used on a study)
                       4. At least one Qualification Group is not Live
                       5. At least one Qualification Group is Active
                       6. At least one Qualification Group is not completed
                       7. User with the claims to access and manage Qualification Library exists
                       8. User with claim to access Qualification Library and without the claim to manage Qualification Library exists
                       9. User without claim to access Qualification Library exists

* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.qualificationlibrary.QualificationLibraryPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_1107_QualificationLibraryListOfQualificationGroups_SIP extends BaseTest {

	
	private String AllFilter="All",ActiveFilter="Active",AttentionFilter="Attention",LiveFilter="Live",LibraryName="QualificationGroup",
			groupNotLive="GroupNotLive",activeGroup="ActiveGroup",InActiveGroup="NotComplete";
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_1107_QualificationLibraryListOfQualificationGroups_SIP(String browser) {
		super(browser);
	}
	
    @BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
    @Test(description="FP-TC-1107--Qualification Library - List of Qualification Groups- V22")
    public void R4_FPTC_1107_QualificationLibraryListOfQualificationGroups()
    {
    	
    	reportLog("2.0: Log in to the Portal as User Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, AT_Password);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to the Qualification Library");
		qualificationlibrary = dashBoardPage.selectHorizontalUpperNavMenuItem(QualificationLibraryPage.class,
				Constants.ConfigureNavText, Constants.QualificationLibraryText);
		
		reportLog("3.1: Qualification Library screen is displayed with Qualification Grid with the list of "
				+ "Qualification Groups sorted in alphabetical order by Name by default");
		qualificationlibrary.verifyQualificationLibraryPage();

		reportLog("3.2: Qualification Library Grid is displayed with following columns by default:");
		reportLog("Details accordion");
		qualificationlibrary.verifyDetailsAccordinColumnDisplayed();
		
		reportLog("Multiple selection");
		qualificationlibrary.verifyMultipleSelectionColumnDisplayed();
		
		reportLog("Library Name");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_libraryName);
		
		reportLog("Display Name");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_DisplayName);

		reportLog("Phase");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_Phase);

		reportLog("Status");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_Status);

		reportLog("Created");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_Created);

		reportLog("Modified");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_Modified);

		reportLog("3.3: Options to be filtered are available (only one selection is available):");
		qualificationlibrary.verifyOptionsToBeFilteredIsDisplayed();
		
		reportLog("All (selected by default)");
		qualificationlibrary.verifyRespectiveFilterOptionDisplayed(AllFilter);
		qualificationlibrary.verifyFilterOptionIsSelected(AllFilter);
		
		reportLog("Active");
		qualificationlibrary.verifyRespectiveFilterOptionDisplayed(ActiveFilter);

		reportLog("Attention");
		qualificationlibrary.verifyRespectiveFilterOptionDisplayed(AttentionFilter);

		reportLog("Live");
		qualificationlibrary.verifyRespectiveFilterOptionDisplayed(LiveFilter);

		reportLog("3.4: Grid Columns configuration is available with (multiple selection is available)");
		qualificationlibrary.verifyGridColumnConfigurationOptionIsdisplayed();
		
		reportLog("3.5: Next options are available:");  
		reportLog("Add Group"); 
		qualificationlibrary.verifyAddGroupButtonIsDisplayed();
		
		reportLog("Actions (when at least one group selected)");
		qualificationlibrary.verifyActionOptionIsNOtDisplayed();
		
		reportLog("Refresh"); 
		qualificationlibrary.verifyRefreshOptionIsDisplayed();
		
		reportLog("Configure displaying quantity of groups per page"); 
		qualificationlibrary.verifyDisplayQuantityIsDisplayed();
		
		reportLog("Page navigation");
		qualificationlibrary.verifyPageNavigationOptionsIsDisplayed();
		
		reportLog("4.0: Verify sorting of items within each column by clicking once (ascending) and second (descending)");
		reportLog("4.1: User is able to sort grid according to column");
		reportLog("Ascending & Descending Functionality Working Accordingly with Phase,Status,Created& Modified column");
		qualificationlibrary.applySortingAccordinglyColumn(Constants.column_Phase);
		qualificationlibrary.applySortingAccordinglyColumn(Constants.column_Status);
		qualificationlibrary.applySortingAccordinglyColumn(Constants.column_Created);
		qualificationlibrary.applySortingAccordinglyColumn(Constants.column_Modified);

		
		reportLog("5.0: Select Qualification Group Pr.#3 details accordion");
		qualificationlibrary.applyFilterUnderLibraryName(LibraryName);
		qualificationlibrary.showQualificationGroupAdditionalDetails(LibraryName);
		
		reportLog("5.1: Additional details are displayed for selected Qualification Group with:");
		qualificationlibrary.verifyAdditionalDetailsIsDisplayedForSelectedGroup();
		
		reportLog("Forms Group(s)");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.Formgroups);
		
		reportLog("Study(ies)");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.Studyies);

		reportLog("Description");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.description);
		
		reportLog("6.0: Select Qualification Group Pr.#4 details accordion");
		qualificationlibrary.applyFilterUnderLibraryName(groupNotLive);
		qualificationlibrary.showQualificationGroupAdditionalDetails(groupNotLive);
		
		reportLog("6.1: Additional details are displayed for selected Qualification Group with:");
		qualificationlibrary.verifyAdditionalDetailsIsDisplayedForSelectedGroup();

		reportLog("Forms Group(s)");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.Formgroups);

		reportLog("Study ('-')");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.Studyies);

		reportLog("Description");
		qualificationlibrary.verifyRespectiveFieldInAdditionalDetails(Constants.description);

        reportLog("7.0: Select option to filter Qualification Groups list to show only Active Groups");
        qualificationlibrary.selectRespectiveFilterOption(ActiveFilter);
        
        reportLog("7.1: Qualification Groups list is refreshed accordingly and displayed with the list of Active Groups only (Pr.#5)");
		qualificationlibrary.verifyQualificationLibraryPage();
		qualificationlibrary.applyFilterUnderLibraryName(activeGroup);
        qualificationlibrary.verifyQualificationGroupIsDisplayed(activeGroup);
		
        reportLog("8.0: Select option to filter Qualification Groups list to show only Live Groups");
        qualificationlibrary.selectRespectiveFilterOption(LiveFilter);

        reportLog("8.1: Qualification Groups list is refreshed accordingly and displayed with the list of Live Groups only (Pr.#3)");
		qualificationlibrary.verifyQualificationLibraryPage();
		qualificationlibrary.applyFilterUnderLibraryName(LibraryName);
        qualificationlibrary.verifyQualificationGroupIsDisplayed(LibraryName);

        reportLog("9.0: Select option to filter Qualification Groups list to show only Incomplete Groups with Attention mark");
        qualificationlibrary.selectRespectiveFilterOption(AttentionFilter);

        reportLog("9.1: Qualification Groups list is refreshed accordingly and displayed with the list of Incomplete Groups "
        		+ "with Attention mark only (Pr.#6)");
        qualificationlibrary.verifyQualificationLibraryPage();
		qualificationlibrary.applyFilterUnderLibraryName(InActiveGroup);
        qualificationlibrary.verifyQualificationGroupIsDisplayed(InActiveGroup);

        reportLog("10.0: Select Grid Columns configuration");
        qualificationlibrary.selectGridColumnConfiguration();

        reportLog("Library name (selected by default, view only)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_libraryName);
        qualificationlibrary.verifySelectedOptionInViewOnlyMode(Constants.column_libraryName);
        
        reportLog("Display name (selected by default, view only)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_DisplayName);
        qualificationlibrary.verifySelectedOptionInViewOnlyMode(Constants.column_DisplayName);

        reportLog("Phase (selected by default)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_Phase);

        reportLog("Status (selected by default)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_Status);

        reportLog("Created (selected by default)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_Created);

        reportLog("Modified (selected by default)");
        qualificationlibrary.verifyRespectiveOptionIsDisplayedAndSelectedByDefault(Constants.column_Modified);

        reportLog("10.1: Close option Displayed");
		qualificationlibrary.verifyCloseOptionIsDisplayed();
		
        reportLog("11.0: Configure Grid Columns:"); 
		reportLog("Deselect Phase (selected by default)");
		qualificationlibrary.DeSelectRespectiveFromColumnsconfiguration(Constants.column_Phase);
		
		reportLog("Deselect Status (selected by default)");
		qualificationlibrary.DeSelectRespectiveFromColumnsconfiguration(Constants.column_Status);

		reportLog("Deselect Created (selected by default)");
		qualificationlibrary.DeSelectRespectiveFromColumnsconfiguration(Constants.column_Created);
		
		reportLog("11.1: Qualification Groups list Grid is refreshed accordingly and displayed with following columns:");
		reportLog("Details accordion");
		qualificationlibrary.verifyDetailsAccordinColumnDisplayed();
		
		reportLog("Multiple selection");
		qualificationlibrary.verifyMultipleSelectionColumnDisplayed();

		reportLog("Library Name");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_libraryName);

		reportLog("Display Name");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_DisplayName);

		reportLog("Modified");
		qualificationlibrary.verifyRequiredColumnIsDisplayed(Constants.column_Modified);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("Log in to the Portal as User Pr.#8");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);

		reportLog("12.1: User is successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("13.0: Navigate to the Qualification Library");
		qualificationlibrary = dashBoardPage.selectHorizontalUpperNavMenuItem(QualificationLibraryPage.class,
				Constants.ConfigureNavText, Constants.QualificationLibraryText);
		
		reportLog("13.1: Qualification Library screen is displayed with Qualification Grid with the list of "
				+ "Qualification Groups sorted in alphabetical order by Name by default");
		qualificationlibrary.verifyQualificationLibraryPage();

		reportLog("13.2:  Add Group option isn't available");
		qualificationlibrary.verifyAddGroupButtonIsNOTDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("14.0: Log in to the Portal as User Pr.#9");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("14.1: User is successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("15.0: Try to navigate to the Qualification Library");
		qualificationlibrary=dashBoardPage.navigateToQualificationLibrary();
		
		reportLog("15.2: User is not allowed to navigate to the Qualification Library");
		qualificationlibrary.verifyQualificationLibraryOptionNotDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
    }
	

}
