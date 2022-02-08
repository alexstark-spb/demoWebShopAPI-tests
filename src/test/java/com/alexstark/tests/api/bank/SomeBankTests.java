package com.alexstark.tests.api.bank;

import com.alexstark.lombok.BankData;
import com.alexstark.tests.api.Specs;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Story("Accounts")
@Feature("Some Bank test")
public class SomeBankTests {

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "http://kn-ktapp.herokuapp.com";
    }

    @Test
    @Tag("bank")
    @DisplayName("Check that in requests to the list of accounts, account_id is not empty")
    void vtb() {
        BankData[] data =
                given()
                        .spec(Specs.requestBank)
                        .when()
                        .get("/apitest/accounts")
                        .then()
                        .log().body()
                        .extract().as(BankData[].class);

        for (BankData bankData : data) {
            get("/apitest/accounts/" + bankData.getAccountId())
                    .then()
                    .body("account_id", notNullValue())
                    .body("currency", is("RUR"));
            System.out.println(bankData.getAccountId());
        }

        assertThat(data[0].getTitle()).isEqualTo("Master1");
    }
}
