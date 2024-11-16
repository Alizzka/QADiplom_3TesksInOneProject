package webTests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConstructorPage {
    private WebDriver driver;

    // Локатор для раздела "Булки"
    @FindBy(xpath = "//span[text()='Булки']")
    private WebElement bunsSection;

    // Локатор для раздела "Соусы"
    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement saucesSection;

    // Локатор для раздела "Начинки"
    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement fillingsSection;

    // Локатор активного раздела Соусы
    @FindBy(xpath = "//*[@id='root']/div/main/section[1]/div[2]/h2[2]")
    private WebElement saucesHeaderSauces;

    // Локатор активного раздела Начинки
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]")
    private WebElement saucesHeaderFillings;

    // Локатор активного раздела Начинки
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]")
    private WebElement saucesHeaderBuns;



    // Конструктор
    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    // Методы для взаимодействия с элементами
    public void clickSaucesSection() {
        saucesSection.click();
    }

    public String getSaucesHeaderText() {
        return saucesHeaderSauces.getText();
    }

    public void clickFillingsSection() {
        fillingsSection.click();
    }

    public String getFillingsHeaderText() {
        return saucesHeaderFillings.getText();
    }

    public void clickBunsSection() {
        bunsSection.click();
    }

    public String getBunsHeaderText() {
        return saucesHeaderBuns.getText();
    }
}

