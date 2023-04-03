package StepDef;

import Logik.Logic;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;

    Random randomNumb = new Random();

    String randomAbc = Logic.generate();


    @Given("I have chosen a {string}")
    public void iHaveChosenA(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:/Selenium/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        //driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
    }

    @When("I have entered a valid {string}, {string} and {string}")
    public void iEnterAValidAnd(String emailAdress, String userName, String PassWord) {
        sendKEys(emailAdress, userName, PassWord);


    }

    private void sendKEys(String emailAdress, String userName, String PassWord) {
        int randomNumber = randomNumb.nextInt(1000) + 1;
        String email = "@hejhej.com";
        String emailLong = "asdacacacacKLMNOPQRSTUVWXYZabcdefghijklmnoasacascascascacascascacacacacaacascsacascascsacac";


        if (emailAdress == null || emailAdress.isEmpty()) {

            driver.findElement(By.id("new_username")).sendKeys(userName + randomNumber);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
            driver.findElement(By.id("new_password")).sendKeys(PassWord + randomNumber);
        } else if (userName.equalsIgnoreCase("LongUserName")) {

            driver.findElement(By.id("email")).sendKeys(emailAdress + email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_username")));
            driver.findElement(By.id("new_username")).click();
            driver.findElement(By.id("new_username")).clear();

            driver.findElement(By.id("new_username")).sendKeys(userName + emailLong);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
            driver.findElement(By.id("new_password")).sendKeys(PassWord + randomNumber);

        } else if (emailAdress.equalsIgnoreCase("havenAbove")) {

            driver.findElement(By.id("email")).sendKeys(emailAdress + email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
            driver.findElement(By.id("new_password")).sendKeys(PassWord);

        } else if (PassWord == null || PassWord.isEmpty()) {
            driver.findElement(By.id("email")).sendKeys(emailAdress + email);

        } else {
            driver.findElement(By.id("email")).sendKeys(emailAdress + randomNumber + email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
            driver.findElement(By.id("new_password")).sendKeys(PassWord + randomNumber);

        }


    }

    @And("I press SignUp button")
    public void iPressSignUpButton() {
        Actions action = new Actions(driver);
        driver.findElement(By.id("marketing_newsletter")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create-account-enabled")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("marketing_newsletter")));
        WebElement button = driver.findElement(By.id("create-account-enabled"));
        action.moveToElement(button).perform();
        button.click();
    }

    @Then("I should be {string} or not registered")
    public void iShouldBeOrNotRegistered(String Verify) {

        if (Verify.equalsIgnoreCase("yes")) {
            //*[@id="signup-content"]/div/div[1]/section/div/h1 gammal xpath frö förra veckan
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-success\"]/div/div[1]/section/div/h1")));
            assertEquals("Check your email", driver.findElement(By.xpath("//*[@id=\"signup-success\"]/div/div[1]/section/div/h1")).getText());

        } else if (Verify.equalsIgnoreCase("missingE")) {
            System.out.println("Email-adress not provided!");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span")));
            assertEquals("An email address must contain a single @.", driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span")).getText());
        } else if (Verify.equalsIgnoreCase("MissingPass")) {
            System.out.println("Missing password");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"passwordHint\"]/div/div/ul/li[5]/span")));
            assertEquals("8 characters minimum", driver.findElement(By.xpath("//*[@id=\"passwordHint\"]/div/div/ul/li[5]/span")).getText());
        } else if (Verify.equalsIgnoreCase("LongUn")) {
            System.out.println("Long user 100 characters");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")));
            assertEquals("Enter a value less than 100 characters long", driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")).getText());
        } else if (Verify.equalsIgnoreCase("Occupied")) {
            System.out.println("User already occupied");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")));
            assertEquals("Great minds think alike - someone already has this username." + " If it's you, log in.", driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")).getText());
        } else {
            System.out.println("Work in progress...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/h1")));
            assertEquals("We've run into an issue", driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
        }
    }
    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
