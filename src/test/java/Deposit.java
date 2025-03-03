
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

public class Deposit {
    @Test()
    public void verify_deposit() {
        String user_id, token;
        System.out.println("===== m5pre verify_deposit");
        String deposit_url = ConfigManager.get("deposit_url");
        String deposit_body = ConfigManager.get("deposit_body");
        user_id = CommonFunction.userId;
        token = CommonFunction.token;
        deposit_body = deposit_body.replace("user_id_value", user_id);
        deposit_body = deposit_body.replace("token_value", token);

        Response response =
                given()
                        .header("content-type", "application/json")
                        .body(deposit_body)
                        .when()
                        .post(deposit_url)
                        .then()
                        .statusCode(200)
                        .body("code", equalTo(200))
                        .contentType(ContentType.JSON).
                        extract().response();

        Allure.addAttachment("Request Body", "application/json", deposit_body);
        Allure.addAttachment("Response Body", "application/json", response.asPrettyString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("code: " + jsonPathEvaluator.get("code"));
        System.out.println("order_id: " + jsonPathEvaluator.get("data.order_id"));
        System.out.println("pay_order_id: " + jsonPathEvaluator.get("data.pay_order_id"));


    }
}
