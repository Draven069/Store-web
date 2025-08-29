package com.nttdata.steps;

import com.nttdata.page.StoreLoginPage;
import org.openqa.selenium.WebDriver;

public class StoreLoginSteps {

    private WebDriver driver;

    public StoreLoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navegar a la página principal de la tienda
     */
    public void navegarATienda() {
        driver.get("https://qalab.bensg.com/store");
    }

    /**
     * Realizar login con credenciales válidas
     * @param usuario email del usuario
     * @param clave contraseña del usuario
     */
    public void iniciarSesionConCredencialesValidas(String usuario, String clave) {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        loginPage.clickSignIn();
        loginPage.typeEmail(usuario);
        loginPage.typePassword(clave);
        loginPage.clickSubmitLogin();
    }

    /**
     * Realizar login con credenciales inválidas
     * @param usuario email inválido
     * @param clave contraseña inválida
     */
    public void iniciarSesionConCredencialesInvalidas(String usuario, String clave) {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        loginPage.clickSignIn();
        loginPage.typeEmail(usuario);
        loginPage.typePassword(clave);
        loginPage.clickSubmitLogin();
    }

    /**
     * Validar que el usuario esté logueado correctamente
     */
    public boolean validarUsuarioLogueado() {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        return loginPage.isUserLoggedIn();
    }

    /**
     * Validar que se muestre mensaje de error
     */
    public boolean validarMensajeError() {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        return loginPage.isErrorMessageDisplayed();
    }

    /**
     * Obtener el mensaje de error mostrado
     */
    public String obtenerMensajeError() {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        return loginPage.getErrorMessage();
    }
}
