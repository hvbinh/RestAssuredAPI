import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.CommonFunction;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Withdraw {
    @Test()
    public void verify_withdraw() {
        String user_id, token;
        System.out.println("===== m5pre verify_withdraw");
        String withdraw_url = ConfigManager.get("withdraw_url");
        String withdraw_body = ConfigManager.get("withdraw_body");
        user_id = CommonFunction.userId;
        token = CommonFunction.token;
        withdraw_body = withdraw_body.replace("user_id_value", user_id);
        withdraw_body = withdraw_body.replace("token_value", token);

        Response response =
                given()
                        .header("content-type", "application/json")
                        .body(withdraw_body)
                        .when()
                        .post(withdraw_url)
                        .then()
                        .statusCode(200)
                        .body("code", equalTo(200))
                        .contentType(ContentType.JSON).
                        extract().response();

        Allure.addAttachment("Request Body", "application/json", withdraw_body);
        Allure.addAttachment("Response Body", "application/json", response.asPrettyString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("code: " + jsonPathEvaluator.get("code"));
        System.out.println("order_id: " + jsonPathEvaluator.get("data.order_id"));
        System.out.println("pay_order_id: " + jsonPathEvaluator.get("data.pay_order_id"));


    }
}
