
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import utils.CommonFunction;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterByPhone {
    @Test
    public void verify_register_by_phone() {
        String register_url = ConfigManager.get("register_url");
        String register_body = ConfigManager.get("register_phone_body");
        String phone = "1"+CommonFunction.RandomPhoneNumber();
        register_body = register_body.replace("phone","+52|"+phone);

        Response response =
                given()
                        .header("content-type", "application/json")
                        .body(register_body)
                        .when()
                        .post(register_url)
                        .then()
                        .statusCode(200)
                        .body("code", equalTo(0))
                        .body("msg", equalTo("ok"))
                        .body("trace_id", notNullValue())
                        .body("data.token", notNullValue())
                        .body("data.user_id", notNullValue())
                        .contentType(ContentType.JSON).
                        extract().response();

        Allure.addAttachment("Request Body", "application/json", register_body);
        Allure.addAttachment("Response Body", "application/json", response.asPrettyString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("code: " + jsonPathEvaluator.get("code"));
        System.out.println("token: " + jsonPathEvaluator.get("data.token"));
        System.out.println("user_id: " + jsonPathEvaluator.get("data.user_id"));
        System.out.println("phone: " + phone);


    }
}
