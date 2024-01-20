package Cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"F:\\Note_API_Auomation\\src\\test\\java\\features\\user.feature", "F:\\Note_API_Auomation\\src\\test\\java\\features\\note.feature"},
        glue = {"stepDefinitions"},
        tags = "@E2E")
public class TestRunner {
}
