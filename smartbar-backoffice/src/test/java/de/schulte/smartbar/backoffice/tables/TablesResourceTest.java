package de.schulte.smartbar.backoffice.tables;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@QuarkusTest
public class TablesResourceTest {

    @InjectMock
    private TablesRepository tablesRepository;

    @Test
    void testHelloEndpoint() {
        Table table = new Table();
        table.setName("Mock table");
        Mockito.when(tablesRepository.listAll()).thenReturn(List.of(table));

        final Response response = given()
          .when().get("/tables")
          .then()
             .statusCode(200)
             .extract().response();
        final JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals("Mock table", jsonPath.getString("[0].name"));
    }
}
