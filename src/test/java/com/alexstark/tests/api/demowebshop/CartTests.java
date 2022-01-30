package com.alexstark.tests.api.demowebshop;

import com.alexstark.data.DataCart;
import com.alexstark.tests.api.Specs;
import com.alexstark.tests.api.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.alexstark.tests.api.Specs.responseSpec;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Cart tests")
@Feature("DemoWebShop tests")
public class CartTests extends TestBase {

    private static Cookie cookieWeb;
    private static Response responseBody;

    @Test
    @Tag("demowebshop")
    @DisplayName("Add goods to cart (UI + API)")
    void cartTest() {
        step("Open product page", () ->
                open("/build-your-cheap-own-computer"));

        step("Enter value = 3 in the 'Qty' field", () ->
                $("#addtocart_72_EnteredQuantity").setValue("3"));

        step("Click on the 'Add to cart' button", () ->
                $("#add-to-cart-button-72").click());

        step("Get cookie from browser", () ->
                cookieWeb = getWebDriver().manage().getCookieNamed("Nop.customer"));

        step("Set cookie by API and to add goods 'Qty = 1' to cart", () -> {

            responseBody =
                    Specs.requestSpec
                            .cookie(cookieWeb.toString())
                            .formParams(new DataCart().getDataForCart())
                            .when()
                            .post("/addproducttocart/details/72/1")
                            .then()
                            .spec(responseSpec)
                            .log().body()
                            .extract().response();
        });

        step("Check the 'Qty = 4' in the cart", () -> {
            assertThat((responseBody.path("success")).toString()).isEqualTo("true");
            assertThat((responseBody.path("updatetopcartsectionhtml")).toString()).isEqualTo("(4)");
        });
    }
}
