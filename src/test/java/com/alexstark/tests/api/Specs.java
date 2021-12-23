package com.alexstark.tests.api;

import com.alexstark.helpers.AllureRestAssuredFilter;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Specs {

    // spec for demowebshop

    public static RequestSpecification requestSpec = with()
            .filter(AllureRestAssuredFilter.withCustomTemplates())
            .log().all()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .baseUri("http://demowebshop.tricentis.com");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
