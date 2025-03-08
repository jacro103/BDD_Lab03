package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {
    WebDriver driver;

    // Localizadores con PageFactory
    @FindBy(id = "column-a")
    private WebElement boxA;

    @FindBy(id = "column-b")
    private WebElement boxB;

    // Constructor
    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para arrastrar A sobre B
    public void dragAtoB() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(boxA, boxB).perform();
    }

    // Método para verificar la posición
    public String getBoxAText() {
        return boxA.getText();
    }
}
