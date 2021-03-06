package com.alexstark.tests.api.demoqa;

import com.alexstark.helpers.AllureRestAssuredFilter;
import com.alexstark.tests.api.Specs;
import com.google.gson.JsonObject;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Story("Book Store tests")
@Feature("DemoQA tests")
public class BookStoreTests {

    public static final JsonObject DATA = new JsonObject();

    @Test
    @Tag("demoqa")
    void noLogsTest() {
        given()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    @Tag("demoqa")
    void withAllLogsTest() {
        given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    @Tag("demoqa")
    void withSomeLogsTest() {
        given()
                .log().uri()
                .log().body()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    @Tag("demoqa")
    void generateTokenTest() {
        DATA.addProperty("userName", "alex");
        DATA.addProperty("password", "asdsad#frew_DFS2");

        given()
                .spec(Specs.requestDemoqa)
                .body(DATA.toString())
                .when()
                .log().uri()
                .log().body()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }

    @Test
    @Tag("demoqa")
    void generateTokenWithSchemaTest() {
        DATA.addProperty("userName", "alex");
        DATA.addProperty("password", "asdsad#frew_DFS2");

        given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .contentType("application/json")
                .accept("application/json")
                .body(DATA.toString())
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("schemas/GenerateTokenSchema.json"))
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }
}
