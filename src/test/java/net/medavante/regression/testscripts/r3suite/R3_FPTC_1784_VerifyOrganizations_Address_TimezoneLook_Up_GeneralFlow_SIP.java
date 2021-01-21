package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.datamodel.OrganizationModel;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.OrganizationPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

/**
 * ======================================================================================================
 * @author Mrinal
 * @date 17/09/2019
 * ======================================================================================================
 * Test Case Id: FP-TC-1784
 * Test Case Name: Organizations - Address - Timezone Look-up - General flow
 * ======================================================================================================
 *
 */
public class R3_FPTC_1784_VerifyOrganizations_Address_TimezoneLook_Up_GeneralFlow_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1784_VerifyOrganizations_Address_TimezoneLook_Up_GeneralFlow_SIP(String browser) {
		super(browser);
	}
	
	private OrganizationPage organizationPage;
    private MedAvantePortalPage medAvantePortalPage;
    private OrganizationModel org;
	
	@BeforeMethod
	public void addNewOrganization() throws Exception {
		Properties properties = Configuration.readTestData("Organization");
        org = new OrganizationModel.AddOrganizationModelBuilder(properties.getProperty("name"), properties.getProperty(
                "abbreviation"), properties.getProperty("type")).setOrgSubType(properties.getProperty("subtype"))
                        .setorgComments(properties.getProperty("comments")).build();
        System.setProperty("className", getClass().getSimpleName());
        
	}
	
	@Test(description = "FPTC_1784", groups = { "R3" })
	public void R3_FPTC_1784_VerifyOrganizations_Address_TimezoneLook_Up_GeneralFlow() throws Exception{
		
	    reportLog("1. Login in to application.");
        medAvantePortalPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
        
        reportLog("2. Verify MedAvante portal.");
        medAvantePortalPage = medAvantePortalPage.verifyMedavantePortalPage();
        
        reportLog("2.1. Select Administration option.");
        organizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
        		OrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
        //organizationPage = medAvantePortalPage.selectAdministrationPortal(OrganizationPage.class);
        
        reportLog("2.2. Add organization.");
        organizationPage.deleteOrganization(org);
        adminstrationOrganizationPage=organizationPage.addOrganization(AdministrationOrganizationPage.class, org);
                
		reportLog("General,Address,People sub tabs are displayed.");
		adminstrationOrganizationPage.verifyAllTabsPresent();
		
		reportLog("3. Click On Address Tab.");
		adminstrationOrganizationAddressPage = adminstrationOrganizationPage.navigateToOrganizationAddressesTab();

		reportLog("4. Verify Address Page Open.");
		adminstrationOrganizationAddressPage.verifyAddressesPageIsOpen();
		
		reportLog("Click on Add address icon.");
		adminstrationOrganizationAddressPage.clickOnAddAddress();
		
		reportLog("4.1. Verify all fields and drop down empty and Timezone look up button disabled.");
		adminstrationOrganizationAddressPage.assertAllFieldsEmptyAndTimeZoneLookUpButtonDisabled();
		
		reportLog("5. Enter City, State and select country.");
		adminstrationOrganizationAddressPage.enterState(Constants.State);
		adminstrationOrganizationAddressPage.enterCityAndCountry(Constants.City, Constants.Country);
		
		reportLog("5.1. Verify Timezone look-up button is disabled.");
		adminstrationOrganizationAddressPage.assertTimezoneLookUpButtonDisabled();
		
		reportLog("6. Fill the address and verify Time look-up button becomes active.");
		adminstrationOrganizationAddressPage.enterAddress(Constants.Address);
		adminstrationOrganizationAddressPage.assertTimeZoneLookUpButtonBecomeActive();
		
		reportLog("7. Reset City field and verify Time look-up button becomes inactive.");
		adminstrationOrganizationAddressPage.resetCityField();
		adminstrationOrganizationAddressPage.assertTimezoneLookUpButtonDisabled();
		
		reportLog("8. Fill city field and verify Time look-up button becomes active. ");
		adminstrationOrganizationAddressPage.enterCity(Constants.City);
		adminstrationOrganizationAddressPage.assertTimeZoneLookUpButtonBecomeActive();
		
		reportLog("9. Select Timezone look up control.");
		adminstrationOrganizationAddressPage.clickOnTimeZoneLookUpButton();
		
		reportLog("9.1. Verify Time look up button becomes inactive.");
		adminstrationOrganizationAddressPage.assertTimezoneLookUpButtonDisabled();
		
		reportLog("9.2. Verify Time zone field fill with valid value.");
		adminstrationOrganizationAddressPage.assertTimezoneFieldFillWithValue(Constants.Timezone);
		
		reportLog("9.3. Verify save icon is active.");
		adminstrationOrganizationAddressPage.assertSaveIconActive();
		
		reportLog("Click on Save icon.");
		adminstrationOrganizationAddressPage.clickOnSaveIcon();
		
		reportLog("10. Verify New Added address is displayed in Address List.");
		adminstrationOrganizationAddressPage.assertAddedAddressInTheAddressList(Constants.Address);
		
		reportLog("11. Click on Newly added Address from address list.");
		adminstrationOrganizationAddressPage.clickOnNewlyAddedAddress(Constants.Address);
		
		reportLog("11.1: Reset Address, City and State fields.");
		adminstrationOrganizationAddressPage.resetAddressCityAndStateFields();
		
		reportLog("11.2. Enter Address, City and State value.");
		adminstrationOrganizationAddressPage.enterAddress(Constants.Address1);
		adminstrationOrganizationAddressPage.enterCity(Constants.City1);
		adminstrationOrganizationAddressPage.enterState(Constants.State1);
		
		reportLog("11.3. Update Timezone with Timezone look up button.");
		adminstrationOrganizationAddressPage.clickOnTimeZoneLookUpButton();
		
		reportLog("11.4. Save the updated details.");
		adminstrationOrganizationAddressPage.clickOnSaveIcon();
		
		reportLog("11.5. Verify address is updated in Address List.");
		adminstrationOrganizationAddressPage.assertAddedAddressInTheAddressList(Constants.Address1);
		
		reportLog("12. Click on Newly added Address from address list.");
		adminstrationOrganizationAddressPage.clickOnNewlyAddedAddress(Constants.Address1);
		
		reportLog("12.1. Update country value");
		adminstrationOrganizationAddressPage.selectCountry(Constants.Country1);
		
		reportLog("12.2. Click on cancel icon.");
		adminstrationOrganizationAddressPage.clickOnCancelIcon();
		
		reportLog("12.3. Verify created Address displayed without changes");
		adminstrationOrganizationAddressPage.assertAddedAddressInTheAddressList(Constants.Country);
		
		reportLog("13. Click on Newly added address from address list.");
		adminstrationOrganizationAddressPage.clickOnNewlyAddedAddress(Constants.Address1);
		
		reportLog("13.1. Update country in address.");
		adminstrationOrganizationAddressPage.selectCountry(Constants.Country1);
		
		reportLog("13.2. Verify Timezone look up button is active and Timezone field empty.");
		adminstrationOrganizationAddressPage.assertTimeZoneLookUpButtonBecomeActive();
		adminstrationOrganizationAddressPage.assertTimezoneDropDownEmpty();
		
		reportLog("14. Select Timezone look up button.");
		adminstrationOrganizationAddressPage.clickOnTimeZoneLookUpButton();
		
		reportLog("14.1. Assert Timezone look up button becomes inactive.");
		adminstrationOrganizationAddressPage.assertTimezoneLookUpButtonDisabled();
		
		reportLog("14.2. Assert Timezone message.");
		adminstrationOrganizationAddressPage.assertTimezoneMessage(Constants.TimezoneMessage);
		
		reportLog("14.3. Assert save icon is displayed disabled.");
		adminstrationOrganizationAddressPage.assertSaveIconIsDisabled();
		
		reportLog("15. Select Timezone value from drop down.");
		adminstrationOrganizationAddressPage.clickOnTimezoneDropDownAndSelectValue(Constants.Timezone1);
		
		reportLog("15.1. Assert save icon active and click on save icon.");
		adminstrationOrganizationAddressPage.assertSaveIconActive();
		adminstrationOrganizationAddressPage.clickOnSaveIcon();
		
		reportLog("15.2. Assert address is updated.");
		adminstrationOrganizationAddressPage.assertAddedAddressInTheAddressList(Constants.Address1);
		
		organizationPage = organizationPage.deleteOrganization(org);
		
		reportLog("16.1: Logout application");
		loginPage.logoutApplication();

		reportLog("16.2: Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
	}	
}
