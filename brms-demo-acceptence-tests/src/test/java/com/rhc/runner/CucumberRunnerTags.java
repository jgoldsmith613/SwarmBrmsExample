package com.rhc.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags="@wip", monochrome = true, glue = "com/rhc/steps/", strict = true, plugin = "json:target/cucumber.json")
public class CucumberRunnerTags {

}

