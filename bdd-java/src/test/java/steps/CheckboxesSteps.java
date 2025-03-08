package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CheckboxesPage;

public class CheckboxesSteps {
    WebDriver driver;
    CheckboxesPage checkboxesPage;

    @Given("I am on the checkboxes page")
    public void iAmOnTheCheckboxesPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage = new CheckboxesPage(driver);
    }

    @When("I check the first checkbox")
    public void iCheckTheFirstCheckbox() {
        checkboxesPage.checkFirstCheckbox();
    }

    @Then("The first checkbox should be checked")
    public void theFirstCheckboxShouldBeChecked() {
        Assertions.assertTrue(checkboxesPage.isFirstCheckboxChecked());
        driver.quit();
    }

    @When("I uncheck the second checkbox")
    public void iUncheckTheSecondCheckbox() {
        checkboxesPage.uncheckSecondCheckbox();
    }

    @Then("The second checkbox should not be checked")
    public void theSecondCheckboxShouldNotBeChecked() {
        Assertions.assertFalse(checkboxesPage.isSecondCheckboxChecked());
        driver.quit();
    }
}
