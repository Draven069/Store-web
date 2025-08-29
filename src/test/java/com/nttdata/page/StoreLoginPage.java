package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class StoreLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private By signInButton = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    private By emailField = By.xpath("//*[@id=\"field-email\"]");
    private By passwordField = By.xpath("//*[@id=\"field-password\"]");
    private By submitLoginButton = By.xpath("//*[@id=\"submit-login\"]");
    private By accountButton = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span");
    private By logoutButton = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[1]");
    private By errorMessage = By.xpath("//*[@id=\"content\"]/section/div");

    public StoreLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void typeEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickSubmitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton)).click();
    }

    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(accountButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage)).getText();
    }
}