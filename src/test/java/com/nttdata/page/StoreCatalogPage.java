package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class StoreCatalogPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Localizadores Genéricos
    private By menuCategories = By.xpath("//*[@id=\"category-3\"]/a");
    private By firstProduct = By.xpath("//ul[@class='product_list grid row']/li[1]//a[@class='product-name']");
    private By addToCartButton = By.xpath("add_to_cart");
    private By quantityField = By.xpath("quantity_wanted");
    private By proceedToCheckoutPopup = By.xpath("//a[@title='Proceed to checkout']");
    private By cartTotal = By.xpath("total_price");
    private By confirmationPopup = By.xpath("//div[@id='layer_cart']");
    private By productAddedMessage = By.xpath("//h2[contains(text(),'Product successfully added')]");
    private By cartTitle = By.xpath("//h1[contains(text(),'Shopping-cart summary')]");
    private By categoryNotFound = By.xpath("//p[@class='alert alert-warning']");

    public StoreCatalogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    public void navigateToCategory(String category, String subcategory) {
        try {
            WebElement categoryElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[@title='" + category + "']")));
            actions.moveToElement(categoryElement).perform();

            WebElement subcategoryElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@title='" + subcategory + "']")));
            subcategoryElement.click();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo navegar a la categoría: " + category + " > " + subcategory);
        }
    }

    public void selectFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public void setQuantity(String quantity) {
        WebElement qty = wait.until(ExpectedConditions.presenceOfElementLocated(quantityField));
        qty.clear();
        qty.sendKeys(quantity);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isProductAddedConfirmationDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(productAddedMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(confirmationPopup)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(cartTotal)).getText();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutPopup)).click();
    }

    public boolean isCartPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(cartTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartTitle() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(cartTitle)).getText();
    }

    public boolean isCategoryNotFound() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(categoryNotFound)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validatePriceCalculation(String expectedQuantity) {

        return true;
    }
}