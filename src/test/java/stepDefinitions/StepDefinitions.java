package stepDefinitions;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import pojo.GetNote;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;
import org.junit.Assert;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends Utils {

    RequestSpecification req;
    Response res;
    JsonPath jsonPath;
    static String token;
    static String noteID = "";

    @Given("add user payload for {string}")
    public void add_user_payload_for(String scenario) throws IOException, ParseException {
        if(token != null)
        {
            req = given().spec(getReqSpecification().addHeader("x-auth-token", token).build())
                    .body(TestDataBuild.getUserDetails(scenario));
        }
        else {
            req = given().spec(getReqSpecification().build())
                    .body(TestDataBuild.getUserDetails(scenario));
        }
    }

    @Given("add note payload {string} {string} {string}")
    public void addNotePayload(String arg0, String arg1, String arg2) throws IOException {
        if(token != null)
        {
            req = given().spec(getReqSpecification().addHeader("x-auth-token", token).build())
                    .body(TestDataBuild.getNoteDetails(arg0, arg1, arg2));
        }
    }

    @When("user call {string} with {string} request")
    public void user_call_with_request(String apiResource, String httpRequest) {
        ApiResources apiResources = ApiResources.valueOf(apiResource);
        String url = apiResources.getURl();
        if(apiResource.equals("noteAPI"))
        {
            url += "/" + noteID;
        }
        switch (httpRequest)
        {
            case "GET" -> res = req.get(url);
            case "POST" -> res = req.post(url);
            case "PATCH" -> res = req.patch(url);
            case "DELETE" -> res = req.delete(url);
        }
    }
    @Then("APi call get success with {string} code")
    public void a_pi_call_get_success_with_code(String statusCode) {
        Assert.assertEquals(res.getStatusCode(), Integer.parseInt(statusCode));
    }
    @Then("{string} in response should be {string}")
    public void in_response_should_be(String field, String value) {
        String body = res.asString();
        jsonPath = new JsonPath(body);
        Assert.assertEquals(jsonPath.get(field).toString(), value);
    }

    @Then("save the token")
    public void saveTheToken() {
        System.out.println((String) jsonPath.get("data.token"));
        token = jsonPath.get("data.token");
    }

    @Given("add token in header")
    public void addTokenInHeader() throws IOException {
        req = given().spec(getReqSpecification().addHeader("x-auth-token", token).build());
    }

    @And("verify the details {string} {string}")
    public void verifyTheDetails(String arg0, String arg1) throws IOException, ParseException {
        Map<String, String> map= TestDataBuild.getUser();
        Assert.assertEquals(jsonPath.get("data."+ arg0), map.get(arg0));
        Assert.assertEquals(jsonPath.get("data." + arg1), map.get(arg1));
    }


    @And("verify the note details {string} {string} {string}")
    public void verifyTheNoteDetails(String title, String description, String category) {
        GetNote getNote = res.as(GetNote.class);
//        Assert.assertEquals(getNote.getData().size()
        Assert.assertEquals(getNote.getData().getTitle(), title);
        Assert.assertEquals(getNote.getData().getDescription(), description);
        Assert.assertEquals(getNote.getData().getCategory(), category);
    }

    @And("save note id")
    public void saveNoteId() {
        noteID = jsonPath.get("data.id");
    }
}
