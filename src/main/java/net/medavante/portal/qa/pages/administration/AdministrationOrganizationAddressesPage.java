package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationOrganizationAddressesPage extends BasePage {
 public AdministrationOrganizationAddressesPage(WebDriver driver) {
  super(driver);
 }

 @FindBy(xpath = "//div[@class='row grid-header']/span/span/label[contains(text(),'Add Address')]")
 private WebElement addressIconLabel;

 public void verifyAddressesPageIsOpen() {
  Assert.assertTrue(isElementPresent(addressIconLabel));
 }

}