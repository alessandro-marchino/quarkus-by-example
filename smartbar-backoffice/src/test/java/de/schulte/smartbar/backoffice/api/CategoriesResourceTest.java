package de.schulte.smartbar.backoffice.api;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.schulte.smartbar.backoffice.entity.Category;
import de.schulte.smartbar.backoffice.service.CategoriesService;

import static io.restassured.RestAssured.given;

import java.util.List;

@QuarkusTest
class CategoriesResourceTest {

    @InjectMock
    CategoriesService categoriesServiceMock;

    @Test
    void testHelloEndpoint() {
        Category category = new Category();
        category.setName("Mock");
        Mockito.when(categoriesServiceMock.listAll()).thenReturn(List.of(category));

        final Response response = given()
          .when().get("/categories")
          .then()
             .statusCode(200)
             .extract().response();
        final JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals("Mock", jsonPath.getString("[0].name"));
    }

}