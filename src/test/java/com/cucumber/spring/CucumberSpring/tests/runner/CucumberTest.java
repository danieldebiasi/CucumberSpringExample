package com.cucumber.spring.CucumberSpring.tests.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features", 
		glue = "com.cucumber.spring.CucumberSpring.tests.stepdefs")
public class CucumberTest {
	
}
