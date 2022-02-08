package com.alexstark.tests.api.demoqa;

import com.alexstark.lombok.BooksData;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Story("Book Store tests")
@Feature("DemoQA tests")
public class BookStoreTestsWithLombok {

    @Test
    @Tag("demoqa")
    void checkISBNTest() {
        BooksData data = given()
                .log().uri()
                .log().body()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .body("books", hasSize(greaterThan(0)))
                .extract().as(BooksData.class);

        for (int i = 0; i < data.getBooks().size(); i++) {
            get("https://demoqa.com/BookStore/v1/Book?ISBN=" + data.getBooks().get(i).getIsbn())
                    .then()
                    .body("author", notNullValue());
        }
    }
}
