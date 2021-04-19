package co.uk.gel.runner;

import co.uk.gel.jira.util.Debugger;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"},
        glue = {"co/uk/gel/jira/steps"},
        features = {"src/test/features"},
        tags = {"@JiraWorkflow1,@JiraWorkflow2"}
)
public class RunnerTest {
    @BeforeClass
    public static void setup() {
        Debugger.println("\n******* RUN STARTS " + new java.util.Date() + " *******************************");
    }
    @AfterClass
    public static void teardown() {
        Debugger.println("\n***** Run COMPLETED " + new java.util.Date() + " *****");
    }
}//end