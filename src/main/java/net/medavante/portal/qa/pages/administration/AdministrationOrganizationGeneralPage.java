package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationOrganizationGeneralPage extends BasePage {

 public AdministrationOrganizationGeneralPage(WebDriver driver) {
  super(driver);
 }

 @FindBy(xpath = "//div[@data-value='model.name']//div[2]//label")
 private WebElement selectedOrgName;

 public void verifyGeneralPageIsOpen(String orgName) {
  Assert.assertEquals(orgName, selectedOrgName.getText());
 }

}