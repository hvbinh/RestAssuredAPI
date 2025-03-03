

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigManager;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class GetAuthConfig {
    @Test
    public void verify_get_auth_config() {
        Allure.description("verify_get_auth_config");
        given()
                .header("content-type", "application/json")
                .body("{}")
                .when()
                .baseUri(ConfigManager.get("base_url"))
                .basePath("/gw/login/get_auth_config")
                .then()
                .statusCode(200)
                .body("code",equalTo(0))
                .body("msg",equalTo("ok"));
    }
}
