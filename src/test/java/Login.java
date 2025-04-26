
import configreport.AllureLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import utils.CommonFunction;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Login {
    @Test()
    @Description("verify_login")
    public void verify_login() {

        Allure.description("verify_login");
        String login_url = ConfigManager.get("login_url");
        String login_body = ConfigManager.get("login_body");
        Response response =
                given()
                        .header("content-type", "application/json")
                        .body(login_body)
                        .when()
                        .post(login_url)
                        .then()
                        .statusCode(200)
                        .body("code", equalTo(0))
                        .body("msg", equalTo("ok"))
                        .body("trace_id", notNullValue())
                        .body("data.token", notNullValue())
                        .body("data.user_id", notNullValue())
                        .body("data.is_register", notNullValue())
                        .contentType(ContentType.JSON).
                        extract().response();

        Allure.addAttachment("Request Body", "application/json", login_body);
        Allure.addAttachment("Response Body", "application/json", response.asPrettyString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("code: " + jsonPathEvaluator.get("code"));
        System.out.println("token: " + jsonPathEvaluator.get("data.token"));
        System.out.println("user_id: " + jsonPathEvaluator.get("data.user_id"));
        System.out.println("is_register: " + jsonPathEvaluator.get("data.is_register"));
        CommonFunction.userId = jsonPathEvaluator.get("data.user_id").toString();
        CommonFunction.token = jsonPathEvaluator.get("data.token").toString();
        //test main branch
        System.out.print("master branch");
        
    }
}
