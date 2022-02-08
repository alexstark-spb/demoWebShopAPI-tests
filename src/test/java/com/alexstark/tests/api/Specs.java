package com.alexstark.tests.api;

import com.alexstark.helpers.AllureRestAssuredFilter;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Specs {

    // spec for demowebshop

    public static RequestSpecification requestDemowebshop = with()
            .filter(AllureRestAssuredFilter.withCustomTemplates())
            .log().all()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .baseUri("http://demowebshop.tricentis.com");

    public static ResponseSpecification responseDemowebshop = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static RequestSpecification requestDemoqa = with()
            .filter(AllureRestAssuredFilter.withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType("application/json")
            .baseUri("https://demoqa.com");

    public static RequestSpecification requestBank = with()
            .filter(AllureRestAssuredFilter.withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType("application/json")
            .baseUri("http://kn-ktapp.herokuapp.com");
}
