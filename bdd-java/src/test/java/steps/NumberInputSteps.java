package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.NumberInputPage;
import static org.junit.Assert.assertEquals;

public class NumberInputSteps {
    private WebDriver driver;
    private NumberInputPage numberInputPage;

    @Given("I navigate to the Number Input page")
    public void i_navigate_to_the_number_input_page() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://example.com/number-input"); // Reemplázalo con la URL real
        numberInputPage = new NumberInputPage(driver);
    }

    @When("I enter {string} in the number field")
    public void i_enter_in_the_number_field(String number) {
        numberInputPage.enterNumber(number);
    }

    @Then("The number field should contain {string}")
    public void the_number_field_should_contain(String expectedNumber) {
        assertEquals(expectedNumber, numberInputPage.getEnteredNumber());
        driver.quit();
    }
}
