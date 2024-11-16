package webTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    // Локатор для кнопки "Войти в аккаунт"
    @FindBy(xpath = "//button[contains(text(),'Войти в аккаунт')]")
    private WebElement loginAccountButton;

    // Локатор для кнопки регистрации
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[1]/a")
    private WebElement registerButton;

    // Локатор для поля ввода имени
    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;

    // Локатор для поля ввода email
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input") 
    private WebElement emailInput;

    // Локатор для поля ввода пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input")
    private WebElement passwordInput;

    // Локатор для кнопки Зарегистрироваться
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/button")
    private WebElement registerButtonClick;

    // Локатор для сообщения об ошибке
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p")
    private WebElement passwordError;


    // Конструктор
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForNameInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void waitForEmailInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void waitForPasswordInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void scrollToRegisterButton() {
        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
    }


    // Методы для взаимодействия с элементами
    public void clickLoginAccountButton() {

        loginAccountButton.click();
    }
    public void clickRegisterButton() {

        registerButton.click();
    }

    public void enterName(String name) {

        nameInput.sendKeys(name);
    }

    public void enterEmail(String email) {

        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {

        passwordInput.sendKeys(password);
    }

    public void clickRegisterButtonClick() {

        registerButton.click();
    }

    public boolean isPasswordErrorDisplayed() {

        return passwordError.isDisplayed();
    }

    public String getPasswordErrorText() {

        return passwordError.getText();
    }
}

