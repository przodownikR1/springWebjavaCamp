package springWebjavaCamp.integration.rest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.RestAccessConfig;
import pl.java.scalatech.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RestAccessConfig.class)
@Slf4j
public class RestTemplateTest {
@Autowired
private RestTemplate restTemplate;

@Test
public void getTest() {
  ResponseEntity<User> loaded = restTemplate.getForEntity("http://localhost:8080/springWebjavaCamp/users/1", User.class);
  log.info("{}",loaded.getBody());
}

@Test
public void postTest() {
  ResponseEntity<User> loaded = restTemplate.getForEntity("http://localhost:8080/springWebjavaCamp/users/1", User.class);
  log.info("{}",loaded.getBody());
}

}
