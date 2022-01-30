package com.alexstark.helpers;

import com.alexstark.tests.api.demowebshop.Authorization;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        String authorizationCookie =
                new Authorization().getAuthorizationCookie();

        step("Open minimal content, because cookie can be set when site is opened", () ->
                open("/Themes/DefaultClean/Content/images/logo.png"));

        step("Set cookie to browser", () ->
                getWebDriver().manage().addCookie(
                        new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
    }
}
