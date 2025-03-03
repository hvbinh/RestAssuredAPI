package configreport;

import io.qameta.allure.Attachment;

public class AllureLogger {

    @Attachment(value = "Test Data", type = "text/plain")
    public static String logTestData(String testData) {
        return testData;
    }

    @Attachment(value = "Request Data", type = "text/plain")
    public static String logRequest(String requestData) {
        return requestData;
    }

    @Attachment(value = "Response Data", type = "text/plain")
    public static String logResponse(String responseData) {
        return responseData;
    }
}