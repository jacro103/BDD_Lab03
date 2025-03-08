package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ABTestingPage;

import java.util.concurrent.TimeUnit;

public class ABTestingSteps {
    private WebDriver driver;
    private ABTestingPage abTestingPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("I navigate to the A/B Testing page")
    public void i_navigate_to_ab_testing_page() {
        driver.get("https://the-internet.herokuapp.com/abtest");
        abTestingPage = new ABTestingPage(driver);
    }

    @Then("I should see the A/B test text")
    public void i_should_see_ab_test_text() {
        Assert.assertTrue(abTestingPage.getPageTitle().contains("A/B Test"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
