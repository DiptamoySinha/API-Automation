package stepDefinitions;

import io.cucumber.java.Before;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Hooks {

    @Before("@E2E")
    public void login() throws IOException, ParseException {
        StepDefinitions stepDefinitions = new StepDefinitions();
        if(StepDefinitions.token == null)
        {
            stepDefinitions.add_user_payload_for("login");
            stepDefinitions.user_call_with_request("logInAPI", "POST");
            stepDefinitions.in_response_should_be("success", "true");
            stepDefinitions.saveTheToken();
        }
    }
}
