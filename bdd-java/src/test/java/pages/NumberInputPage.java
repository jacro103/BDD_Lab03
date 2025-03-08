package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NumberInputPage {
    WebDriver driver;

    @FindBy(id = "numberField") // Reemplaza con el ID real del campo
    private WebElement numberInput;

    public NumberInputPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNumber(String number) {
        numberInput.clear();
        numberInput.sendKeys(number);
    }

    public String getEnteredNumber() {
        return numberInput.getAttribute("value");
    }
}
