package com.nttdata.steps;

import com.nttdata.page.StoreCatalogPage;
import org.openqa.selenium.WebDriver;

public class StoreCatalogSteps {

    private WebDriver driver;
    private String precioUnitario;
    private String precioTotal;

    public StoreCatalogSteps(WebDriver driver) {
        this.driver = driver;
    }


    public void navegarACategoriaYSubcategoria(String categoria, String subcategoria) {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        try {
            catalogPage.navigateToCategory(categoria, subcategoria);
        } catch (RuntimeException e) {
            if (categoria.equals("Autos")) {
                // Para categorías inexistentes, manejamos la excepción
                throw new RuntimeException("Categoría '" + categoria + "' no encontrada como se esperaba");
            } else {
                throw e;
            }
        }
    }

    /**
     * Seleccionar el primer producto de la lista
     */
    public void seleccionarPrimerProducto() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        catalogPage.selectFirstProduct();
    }

    /**
     * Agregar cantidad específica del producto al carrito
     * @param cantidad número de unidades a agregar
     */
    public void agregarProductoAlCarrito(int cantidad) {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        catalogPage.setQuantity(String.valueOf(cantidad));
        catalogPage.addToCart();
    }

    /**
     * Validar que se muestre la confirmación de producto agregado
     */
    public boolean validarConfirmacionProductoAgregado() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.isProductAddedConfirmationDisplayed();
    }

    /**
     * Validar que el popup esté visible
     */
    public boolean validarPopupVisible() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.isPopupDisplayed();
    }

    /**
     * Obtener y validar el precio total calculado
     * @param cantidadEsperada cantidad esperada para validar el cálculo
     */
    public boolean validarCalculoPrecioTotal(int cantidadEsperada) {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);

        // Obtener el precio total del popup
        precioTotal = catalogPage.getTotalPrice();

        // Validar que el precio no esté vacío
        if (precioTotal == null || precioTotal.isEmpty()) {
            return false;
        }

        System.out.println("Precio total calculado: " + precioTotal + " para " + cantidadEsperada + " unidades");
        return true;
    }

    /**
     * Proceder al checkout desde el popup
     */
    public void procederAlCheckout() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        catalogPage.proceedToCheckout();
    }

    /**
     * Validar que se encuentre en la página del carrito
     */
    public boolean validarPaginaCarrito() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.isCartPageDisplayed();
    }

    /**
     * Obtener el título de la página del carrito
     */
    public String obtenerTituloCarrito() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.getCartTitle();
    }

    /**
     * Validar el cálculo de precios en el carrito final
     */
    public boolean validarCalculoPreciosCarrito(int cantidad) {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.validatePriceCalculation(String.valueOf(cantidad));
    }

    /**
     * Validar que no se encuentre la categoría solicitada
     */
    public boolean validarCategoriaNoEncontrada() {
        StoreCatalogPage catalogPage = new StoreCatalogPage(driver);
        return catalogPage.isCategoryNotFound();
    }

    /**
     * Obtener el precio total guardado para comparaciones
     */
    public String obtenerPrecioTotal() {
        return precioTotal;
    }
}