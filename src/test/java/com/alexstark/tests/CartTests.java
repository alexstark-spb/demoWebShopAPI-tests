package com.alexstark.tests;

import com.alexstark.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Cart tests")
public class CartTests extends TestBase {

    private static Cookie cookieWeb;
    private static Response responseBody;

    @Test
    @Tag("demowebshop")
    @DisplayName("Add goods to cart (UI + API)")
    void cartTest() {
        step("Open product page", () ->
                open("/build-your-cheap-own-computer"));

        step("Enter value = 3 in the 'Qty' field", () -> {
            $("#addtocart_72_EnteredQuantity").setValue("3");
        });

        step("Click on the 'Add to cart' button", () ->
                $("#add-to-cart-button-72").click());

        step("Get cookie from browser", () ->
                cookieWeb = getWebDriver().manage().getCookieNamed("Nop.customer"));

        step("Set cookie by API and to add goods 'Qty = 1' to cart", () -> {
            responseBody = given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&" +
                            "product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                    .cookie(cookieWeb.toString())
                    .when()
                    .post("/addproducttocart/details/72/1")
                    .then()
                    .statusCode(200)
                    .extract().response();
        });

        step("Check the 'Qty = 4' in the cart", () -> {
            assertThat((responseBody.path("success")).toString()).isEqualTo("true");
            assertThat((responseBody.path("updatetopcartsectionhtml")).toString()).isEqualTo("(4)");
        });
    }
}
