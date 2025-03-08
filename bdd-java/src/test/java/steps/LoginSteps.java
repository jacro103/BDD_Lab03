package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import pages.*;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the secure area")
    public void the_user_should_be_redirected_to_the_secure_area() {
        assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    @Then("a success {string} should be displayed")
    public void a_success_message_should_be_displayed(String message) {
        assertTrue(driver.getPageSource().contains(message));
        driver.quit();
    }
}