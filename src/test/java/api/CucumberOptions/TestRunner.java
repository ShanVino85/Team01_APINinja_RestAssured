package api.CucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="/Users/parikshit/git/Team 1 Rest assured/Team01_APINinja_RestAssured/src/test/resources/features1",glue= {"api.stepdefinitions"},
plugin = {"pretty"
		})
public class TestRunner {

}
