package com.cucumber.spring.CucumberSpring.tests.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.cucumber.spring.CucumberSpring.tests.runner.SpringBootBaseTest;

import cucumber.api.java.en.*;

public class BagStepdefs extends SpringBootBaseTest {

	private final Logger log = LoggerFactory.getLogger(BagStepdefs.class);
	
	@Given("^A sacola encontra-se vazia$")
	public void a_sacola_encontra_se_vazia() throws Throwable {
		clean();
        assertThat(getContents().isEmpty()).isTrue();
	}

	@When("^Inserir (\\d+) \"(.*?)\" na sacola$")
	public void inserir_na_sacola(int quantidade, String item) throws Throwable {
		IntStream.range(0, quantidade)
        .peek(n -> log.info("Inserindo {} na sacola, quantidade {}", item, quantidade))
        .map(ignore -> put(item))
        .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
	}

	@Then("^A sacola deve conter (\\d+) \"(.*?)\"$")
	public void a_sacola_deve_conter(int quantidade, String item) throws Throwable {
		assertNumberOfTimes(quantidade, item, false);
	}
	
	private void assertNumberOfTimes(final int quantity, final String something, final boolean onlyThat) {
        final List<String> things = getContents().getThings();
        log.info("Espera-se {} de {}. A sacola contem {}", quantity, something, things);
        final int timesInList = Collections.frequency(things, something);
        assertThat(timesInList).isEqualTo(quantity);
        if (onlyThat) {
            assertThat(timesInList).isEqualTo(things.size());
        }
    }
	
}
