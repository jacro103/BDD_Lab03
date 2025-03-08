package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRemovePage {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Add Element']")
    WebElement addElementButton;

    @FindBy(xpath = "//button[text()='Delete']")
    List<WebElement> deleteButtons;

    public AddRemovePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddElement() {
        addElementButton.click();
    }

    public boolean isDeleteButtonDisplayed() {
        return !deleteButtons.isEmpty();
    }

    public void clickDeleteButton() {
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
        }
    }

    public boolean isDeleteButtonRemoved() {
        return deleteButtons.isEmpty();
    }
}
