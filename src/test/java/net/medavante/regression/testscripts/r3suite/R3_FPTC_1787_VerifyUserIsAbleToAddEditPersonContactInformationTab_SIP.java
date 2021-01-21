/**
 *@author Siddharth
* @date 13/09/2019
* =========================================================================
* Test Case Id: FP-TC-1787 
* Test Case Name:  Person Contact Information Tab - Timezone Look-up - General flow
* ========================================================================= 
*/

package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationPeopleContactInformationPage;
import net.medavante.portal.pages.administration.AdministrationPeoplePage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1787_VerifyUserIsAbleToAddEditPersonContactInformationTab_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1787_VerifyUserIsAbleToAddEditPersonContactInformationTab_SIP(String Browser) {
		super(Browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
	@Test(description = " Person Contact Information Tab - Timezone Look-up - General flow" , groups = {"R3"} )
	public void R3_FPTC_1787_VerifyUserIsAbleToAddEditPersonContactInformationTab() {
		try {

			reportLog("1.1: Login to the application");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("2.1: Navigate to study setup");
			adminstrationOrganizationPage = dashBoardPage.clickOnDropdown(Constants.ConfigureNavText)
					.navigateToPagePresentOnUpperTab(Constants.StudySetupText, AdministrationOrganizationPage.class);

			reportLog("2.2: Navigate to people tab ");
			adminstrationPeoplePage = adminstrationOrganizationPage.navigateToTab(Constants.Peopletext,
					AdministrationPeoplePage.class);

			reportLog("2.3: Navigate to Contact information tab");
			adminstrationPeopleContactInformationPage = adminstrationPeoplePage.NavigateToPeopleSubTab(
					Constants.ContactInformation, AdministrationPeopleContactInformationPage.class);

			reportLog("2.4: Contact information tab is displayed");
			adminstrationPeopleContactInformationPage.verifyContactInformationPageIsOpen();

			reportLog("3.1: Select to add new address");
			adminstrationPeopleContactInformationPage.clickOnAddAddressButton();

			reportLog("3.2: Modal to add new address is displayed with active field and inactive timezone look up");
			adminstrationPeopleContactInformationPage.addNewAddressModalIsDisplayed().verifyFieldsAreEmpty()
					.verifyTimeZoneLookUpControl(Constants.isDisabled);

			reportLog("4.1: Fill in valid address and country");
			adminstrationPeopleContactInformationPage.enterAddress(Constants.Address).enterCountry(Constants.Country);

			reportLog("4.2: Filled in value displayed");
			adminstrationPeopleContactInformationPage.verifyAddressIsDisplayed(Constants.Address)
					.verifyCountryNameIsDisplayed(Constants.Country);

			reportLog("4.3: Timezone lookup control becomes active");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isEnabled);

			reportLog("5.1: Clear address field ");
			adminstrationPeopleContactInformationPage.clearAddressField();

			reportLog("5.2: Timezone lookup becomes inactive");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isDisabled);

			reportLog("6.1: Fill in address field");
			adminstrationPeopleContactInformationPage.enterAddress(Constants.Address1);

			reportLog("6.2: Time zone becomes active");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isEnabled);

			reportLog("7.1: Select time zone lookup control ");
			adminstrationPeopleContactInformationPage.selectTimeZoneLookupControl();

			reportLog("7.2: time zone lookup control becomes inactive");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isDisabled);

			reportLog("7.3: Time zone fields become inactive with valid value displayed");
			adminstrationPeopleContactInformationPage.verifyTimeZoneIsActiveOrInactive(Constants.isDisabled)
					.verifyValueIsDisplayedInTimeZone();

			reportLog("7.4: Save control is active");
			adminstrationPeopleContactInformationPage.verifySaveControlIsActiveOrInactive(Constants.isEnabled);

			reportLog("8.1: Save a new Address ");
			adminstrationPeopleContactInformationPage.clickOnSaveButton();

			reportLog("8.2: New Address is displayed in the address list");
			adminstrationPeopleContactInformationPage.verifyAddressIsDisplayed(Constants.Address1);

			reportLog("9.1: Select edit newly created address ");
			adminstrationPeopleContactInformationPage.editAddress(Constants.Address1);

			reportLog("9.2: Timezone lookup control becomes active");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isEnabled);

			reportLog("9.3: Update address and country");
			adminstrationPeopleContactInformationPage.enterAddress(Constants.Address).enterCountry(Constants.Country1);

			reportLog("9.4: Update timezone with timezone lookup");
			adminstrationPeopleContactInformationPage.selectTimeZoneLookupControl();

			reportLog("9.5: Save changes");
			adminstrationPeopleContactInformationPage.clickOnSaveButton();

			reportLog("9.6: Updated address displayed");
			adminstrationPeopleContactInformationPage.verifyAddressIsDisplayed(Constants.Address);

			reportLog("10.1: Select edit newly created address");
			adminstrationPeopleContactInformationPage.editAddress(Constants.Address);

			reportLog("10.2: Update Country");
			adminstrationPeopleContactInformationPage.enterCountry(Constants.Country);

			reportLog("10.3: Cancel changes");
			adminstrationPeopleContactInformationPage.clickOnCancelButton();

			reportLog("10.4: newly created address displayed without changes");
			adminstrationPeopleContactInformationPage.verifyAddressIsDisplayed(Constants.Address);

			reportLog("11.1: select to edit newly created address");
			adminstrationPeopleContactInformationPage.editAddress(Constants.Address);

			reportLog("11.2: Timezone Lookup control becomes active");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isEnabled);

			reportLog("11.3: Update country");
			adminstrationPeopleContactInformationPage.enterCountry(Constants.Country2);

			reportLog("11.4: Timezone field is empty and required");
			adminstrationPeopleContactInformationPage.verifyTimeZoneFieldIsRequired();

			reportLog("12.1: Select timezone lookup control");
			adminstrationPeopleContactInformationPage.selectTimeZoneLookupControl();

			reportLog("12.2: Timezone Lookup control becomes inactive");
			adminstrationPeopleContactInformationPage.verifyTimeZoneLookUpControl(Constants.isDisabled);

			reportLog(
					"12.3: Message displayed saying that can't find match.Please verify address or select timezone manually");
			adminstrationPeopleContactInformationPage.timeZoneErrorMessage(Constants.errorMessage);

			reportLog("12.4: Timezone field is empty and required");
			adminstrationPeopleContactInformationPage.verifyTimeZoneFieldIsRequired();

			reportLog("12.5: Save control is inactive");
			adminstrationPeopleContactInformationPage.verifySaveControlIsActiveOrInactive(Constants.isDisabled);			

			reportLog("13.1: Select Timezone value from dropdown menu (if displayed ) and save changed");
			adminstrationPeopleContactInformationPage.selectTimeZoneFieldFromDropDown();

			reportLog("13.2: Timezone value is displayed");
			adminstrationPeopleContactInformationPage.verifyValueIsDisplayedInTimeZone();

			reportLog("13.3: save control is active");
			adminstrationPeopleContactInformationPage.verifySaveControlIsActiveOrInactive(Constants.isEnabled);

			reportLog("13.4: Updated address is displayed");
			adminstrationPeopleContactInformationPage.verifyAddressIsDisplayed(Constants.Address);
			
			reportLog("13.5 Delete the address");
			adminstrationPeopleContactInformationPage.deleteAddress();
			
			reportLog("14.1:  logout from the application ");
			loginPage = loginPage.logoutApplication();

			reportLog("14.2: verify user is logout");
			loginPage.verifyUserLogout();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
