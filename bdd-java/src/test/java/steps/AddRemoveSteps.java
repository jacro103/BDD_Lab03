package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AddRemovePage;

public class AddRemoveSteps {
    WebDriver driver;
    AddRemovePage addRemovePage;

    @Given("I am on the Add\\/Remove Elements page")
    public void i_am_on_the_add_remove_elements_page() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        addRemovePage = new AddRemovePage(driver);
    }


    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        if (buttonName.equals("Add Element")) {
            addRemovePage.clickAddElement();
        } else if (buttonName.equals("Delete")) {
            addRemovePage.clickDeleteButton();
        }
    }

    @Then("A new element should be added")
    public void a_new_element_should_be_added() {
        Assert.assertTrue(addRemovePage.isDeleteButtonDisplayed());
    }

    @Then("The element should be removed")
    public void the_element_should_be_removed() {
        Assert.assertTrue(addRemovePage.isDeleteButtonRemoved());
        driver.quit();
    }
}
