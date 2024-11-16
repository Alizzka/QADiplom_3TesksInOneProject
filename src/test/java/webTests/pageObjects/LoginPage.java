package webTests.pageObjects;

//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    /*private By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By personalCabinetButton = By.xpath("//button[text()='Личный кабинет']");
    private By registerLoginButton = By.xpath("//a[text()='Войти']");
    private By forgotPasswordLoginButton = By.xpath("//a[text()='Войти']");
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By submitLoginButton = By.xpath("//button[text()='Войти']");*/

    // Локатор для кнопки "Войти в аккаунт"
    @FindBy(xpath = "//button[contains(text(),'Войти в аккаунт')]")
    private WebElement loginAccountButton;

    // Локатор для кнопки регистрации
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[1]/a")
    private WebElement registerButton;

    // Локатор для кнопки "Личный Кабинет"
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/a/p")
    private WebElement personalCabinetButton;

    // Локатор для кнопки "Войти" в форме регитрации
    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement registerLoginButton;

    // Локатор для поля ввода email
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private WebElement emailInput;

    // Локатор для поля ввода пароля
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    // Локатор для поля ввода пароля в форме восстановления пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset/div/div/input")
    private WebElement passwordRestoreInput;

    // Локатор для книпоки "Восстановить пароль"
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[2]/a")
    private WebElement restorePasswordButton;

    // Локатор для кнопки "Войти" в: входе на главной странице, в форме регистрации, через кнопку 'Личный кабинет'
    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement submitLoginButton;

    // Локатор для кнопки "Войти" в форме восстановления пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p/a")
    private WebElement submitRestoreLoginButton;

    // Локатор для кнопки "Конструктор"
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p")
    private WebElement constructorButton;

    // Локатор для кнопки "Выйти"
    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement exitButton;

    // Локатор для логотипа Stellar Burgers
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/div")
    private WebElement stellarBurgersLogo;



    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    // Методы для взаимодействия с элементами
    public void submitLogin() {

        submitLoginButton.click();
    }

    public void clickPersonalCabinetButton() {

        personalCabinetButton.click();
    }

    public void clickRegisterLoginButton() {

        registerLoginButton.click();
    }

    public void clickRestorePasswordButton() {

        restorePasswordButton.click();
    }

    public void enterEmail(String email) {

        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {

        passwordInput.sendKeys(password);
    }

    public void enterRestorePassword(String password) {

        passwordRestoreInput.sendKeys(password);
    }

    public void submitRestoreLogin() {

        submitRestoreLoginButton.click();
    }

    public void clickConstructor() {

        constructorButton.click();
    }

    public void clickExit() {

        exitButton.click();
    }

    public void clickStellarBurgersLogo() {

        stellarBurgersLogo.click();
    }
}

    /*public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

    public void clickRegisterLoginButton() {
        driver.findElement(registerLoginButton).click();
    }

    public void clickForgotPasswordLoginButton() {
        driver.findElement(forgotPasswordLoginButton).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submitLogin() {
        driver.findElement(submitLoginButton).click();
    }*/


