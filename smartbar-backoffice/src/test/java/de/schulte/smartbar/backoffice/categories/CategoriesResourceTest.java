package de.schulte.smartbar.backoffice.categories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CategoriesResourceTest {

    @Test
    @TestSecurity(authorizationEnabled = true, user = "bob", roles = { "admin" })
    void getListOfCategories() {
        final Response response = given()
            .when()
                .get("/categories")
            .then()
                .statusCode(200)
            .extract().response();
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Coffee", jsonPath.getString("[0].name"));
    }

    @Test
    @TestSecurity(authorizationEnabled = true, user = "alice", roles = { "user" })
    void getListOfCategoriesNonAdminUser() {
        given()
            .when()
                .get("/categories")
            .then()
                .statusCode(403);
    }

    @Test
    void getListOfCategoriesNonLoggedUser() {
        given()
            .when()
                .get("/categories")
            .then()
                .statusCode(401);
    }
}
