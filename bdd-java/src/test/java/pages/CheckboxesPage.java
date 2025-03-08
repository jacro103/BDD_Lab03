package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxesPage {
    WebDriver driver;

    // Localizadores con PageFactory
    @FindBy(xpath = "//input[1]")
    private WebElement firstCheckbox;

    @FindBy(xpath = "//input[2]")
    private WebElement secondCheckbox;

    // Constructor
    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Métodos para interactuar con la página
    public void checkFirstCheckbox() {
        if (!firstCheckbox.isSelected()) {
            firstCheckbox.click();
        }
    }

    public void uncheckSecondCheckbox() {
        if (secondCheckbox.isSelected()) {
            secondCheckbox.click();
        }
    }

    public boolean isFirstCheckboxChecked() {
        return firstCheckbox.isSelected();
    }

    public boolean isSecondCheckboxChecked() {
        return secondCheckbox.isSelected();
    }
}
