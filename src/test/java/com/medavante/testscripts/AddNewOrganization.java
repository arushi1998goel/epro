package com.medavante.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.datamodel.OrganizationModel;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.OrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class AddNewOrganization extends BaseTest {

    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public AddNewOrganization(String browser) {
        super(browser);
    }

    private OrganizationPage organizationPage;
    private MedAvantePortalPage medAvantePortalPage;
    private OrganizationModel org;

    @BeforeMethod
    public void getTestData() throws Exception {
        Properties properties = Configuration.readTestData("Organization");
        org = new OrganizationModel.AddOrganizationModelBuilder(properties.getProperty("name"), properties.getProperty(
                "abbreviation"), properties.getProperty("type")).setOrgSubType(properties.getProperty("subtype"))
                        .setorgComments(properties.getProperty("comments")).build();
        System.setProperty("className", getClass().getSimpleName());
    }

    @Test
    public void test_AddNewOrganization() throws Exception {
        reportLog("Login in to application");
        medAvantePortalPage = loginPage.loginInApplication(userName, password);
        reportLog("Verify MedAvante portal");
        medAvantePortalPage = medAvantePortalPage.verifyMedavantePortalPage();
        reportLog("Select Administration option.");
        organizationPage = medAvantePortalPage.selectAdministrationPortal(OrganizationPage.class);
        reportLog("Add organization");
        organizationPage = organizationPage.addOrganization(OrganizationPage.class,org);
//        reportLog("Delete organization");
//        organizationPage = organizationPage.deleteOrganization(org);
        loginPage = organizationPage.logoutApplication();
        reportLog("Logout application");
       
    }

}
