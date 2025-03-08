package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DragAndDropPage;

public class DragAndDropSteps {
    WebDriver driver;
    DragAndDropPage dragAndDropPage;

    @Given("I am on the drag and drop page")
    public void iAmOnTheDragAndDropPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        dragAndDropPage = new DragAndDropPage(driver);
    }

    @When("I drag box A to box B")
    public void iDragBoxAToBoxB() {
        dragAndDropPage.dragAtoB();
    }

    @Then("Box A should be in the position of box B")
    public void boxAShouldBeInThePositionOfBoxB() {
        Assertions.assertEquals("B", dragAndDropPage.getBoxAText());
        driver.quit();
    }
}
