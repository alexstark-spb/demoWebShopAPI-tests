package com.alexstark.tests;

import com.alexstark.config.App;
import com.alexstark.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Story("Login tests")
public class LoginTests extends TestBase {

    private static final Map<String,String> DATA = new HashMap<>();

    @Test
    @Tag("demowebshop")
    @DisplayName("Successful authorization (UI)")
    void loginTest() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form", () -> {
            $("#Email").setValue(App.config.userLogin());
            $("#Password").setValue(App.config.userPassword()).pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(App.config.userLogin())));
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Successful authorization (API + UI)")
    void loginWithCookieTest() {
        step("Get cookie by api and set it to browser", () -> {

            DATA.put("Email", App.config.userLogin());
            DATA.put("Password", App.config.userPassword());

            String authorizationCookie =
                    given()
                            .filter(AllureRestAssuredFilter.withCustomTemplates())
                            .log().all()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParams(DATA)
                            .when()
                            .post("http://demowebshop.tricentis.com/login")
                            .then()
                            .log().status()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(App.config.userLogin())));
    }
}
