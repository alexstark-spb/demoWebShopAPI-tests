package com.alexstark.helpers;

import com.alexstark.data.DataLogin;

import static com.alexstark.tests.api.Specs.requestDemowebshop;
import static io.restassured.RestAssured.given;

public class AuthorizationAPI {

    public String getAuthorizationCookie() {
        return given()
                .spec(requestDemowebshop)
                .formParams(new DataLogin().getDataForLogin())
                .when()
                .post("/login")
                .then()
                .log().status()
                .statusCode(302)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");
    }
}