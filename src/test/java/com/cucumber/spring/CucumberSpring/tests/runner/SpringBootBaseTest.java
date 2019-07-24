package com.cucumber.spring.CucumberSpring.tests.runner;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cucumber.spring.CucumberSpring.CucumberSpringApplication;
import com.cucumber.spring.CucumberSpring.model.Bag;


@ContextConfiguration(classes = CucumberSpringApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class SpringBootBaseTest {
	 
    private final String SERVER_URL = "http://localhost";
    private final String THINGS_ENDPOINT = "/things";
 
    private RestTemplate restTemplate;
 
    @LocalServerPort
    protected int port = 8080;
 
    public SpringBootBaseTest() {
        restTemplate = new RestTemplate();
    }
 
    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }
 
    public int put(final String something) {
        return restTemplate.postForEntity(thingsEndpoint(), something, Void.class).getStatusCodeValue();
    }
 
    public Bag getContents() {
        return restTemplate.getForEntity(thingsEndpoint(), Bag.class).getBody();
    }

    public void clean() {
        restTemplate.delete(thingsEndpoint());
    }
	
}
