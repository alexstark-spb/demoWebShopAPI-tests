package com.alexstark.helpers;

import com.alexstark.data.DataLogin;
import com.alexstark.tests.api.Specs;

public class Authorization {

    public String getAuthorizationCookie() {
        return Specs.requestSpec
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