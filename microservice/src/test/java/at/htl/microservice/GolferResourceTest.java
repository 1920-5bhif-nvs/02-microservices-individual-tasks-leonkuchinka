package at.htl.microservice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GolferResourceTest {

    @Test
    public void testGolferCount() {
        given()
                .when()
                .get("/api/golfer/count")
                .then()
                .statusCode(200)
                .body(is("6"));
    }

    @Test
    public void testAverageHcp() {
        given()
                .when()
                .get("/api/golfer/avg/hcp")
                .then()
                .statusCode(200)
                .body(is("-9.733333333333333"));
    }

    @Test
    public void testAverageAge() {
        given()
                .when()
                .get("/api/golfer/avg/age")
                .then()
                .statusCode(200)
                .body(is("20.0"));
    }


}