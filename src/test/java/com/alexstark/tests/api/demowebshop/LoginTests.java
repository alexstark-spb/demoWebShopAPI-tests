package com.alexstark.tests.api.demowebshop;

import com.alexstark.config.App;
import com.alexstark.helpers.WithLogin;
import com.alexstark.tests.api.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Login tests")
@Feature("DemoWebShop tests")
public class LoginTests extends TestBase {

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
    @WithLogin
    @Tag("demowebshop")
    @DisplayName("Successful authorization (API + UI) using annotation @WithLogin")
    void loginWithCookieTest() {

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(App.config.userLogin())));
    }
}
