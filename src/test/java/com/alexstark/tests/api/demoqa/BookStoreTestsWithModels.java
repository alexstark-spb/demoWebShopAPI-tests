package com.alexstark.tests.api.demoqa;

import com.alexstark.models.BooksData;
import com.alexstark.tests.api.Specs;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@Story("Book Store tests")
@Feature("DemoQA tests")
public class BookStoreTestsWithModels {

    public static final JsonObject DATA = new JsonObject();
    public static final Faker faker = new Faker();

    @Test
    @Tag("demoqa")
    void getListOfBooksWithGroovy() {
        BooksData data =
                given()
                        .spec(Specs.requestDemoqa)
                        .get("/BookStore/v1/Books")
                        .then()
                        .log().body()
                        .body("books", hasSize(greaterThan(0)))
                        .body("books.findAll{it.author}.author.flatten()", hasItem("Eric Elliott"))
                        .extract().as(BooksData.class);

        for (int i = 0; i < data.getBooks().size(); i++) {
            get("https://demoqa.com/BookStore/v1/Book?ISBN=" + data.getBooks().get(i).getIsbn())
                    .then()
                    .body("isbn", notNullValue());
        }
    }

    @Test
    @Tag("demoqa")
    void createNewUser() {
        String fakerName = faker.name().firstName();
        String fakerPassword = "5$H%." + faker.internet().password();

        DATA.addProperty("userName", fakerName);
        DATA.addProperty("password", fakerPassword);

        BooksData data =
                given()
                        .spec(Specs.requestDemoqa)
                        .body(DATA.toString())
                        .when()
                        .post("/Account/v1/User")
                        .then()
                        .log().body()
                        .extract().as(BooksData.class);

        assertThat(data.getUsername()).isEqualTo(fakerName);
        assertThat(data.getBooks()).isEmpty();
    }
}
