package springWebjavaCamp.integration.rest;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.RestAccessConfig;
import pl.java.scalatech.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestAccessConfig.class)
@Slf4j
public class RestTemplateTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getTest() {
        ResponseEntity<User> loaded = restTemplate.getForEntity("http://localhost:8080/springWebjavaCamp/users/1", User.class);
        log.info("{}", loaded.getBody());
    }

    @Test
    public void postTest() {
        User user = new User(10l, "koala", "mis", 23l, null);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity("http://localhost:8080/springWebjavaCamp/api/users3/10", user, ResponseEntity.class);
        log.info("{}", responseEntity.getHeaders());
    }

    @Test
    public void putTest() {
        User user = new User(10l, "koala", "mis2", 23l, null);
        HttpEntity<User> httpEntityUser = new HttpEntity<>(user);
        log.info("{}", doUpdateUser(restTemplate, httpEntityUser, 10));
    }

    @Test
    public void getAllTest() {
        log.info("++++++++++");
        ResponseEntity<User[]> loaded = restTemplate.getForEntity("http://localhost:8080/springWebjavaCamp/users/", User[].class);
        log.info("{}", newArrayList(loaded.getBody()));
        log.info("++++++++++");
    }

    @Test
    public void getAllTest2() {
        log.info("++++++++++");
        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {
        };//typ ogolny odpowiedzi
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);


        ResponseEntity<List<User>> loaded = restTemplate.exchange("http://localhost:8080/springWebjavaCamp/users/",HttpMethod.GET,requestEntity, typeRef);
        log.info("{}", loaded);
        log.info("++++++++++");
    }

    static String baseUrl = "http://localhost:8080/springWebjavaCamp/api";

    private static ResponseEntity<String> doCreateUser(RestTemplate restTemplate, HttpEntity<User> entity) {
        return restTemplate.exchange(baseUrl + "/users3", HttpMethod.POST, entity, String.class);
    }

    private static ResponseEntity<String> doUpdateUser(RestTemplate restTemplate, HttpEntity<User> entity, long id) {
        return restTemplate.exchange(baseUrl + "/users3/" + id, HttpMethod.PUT, entity, String.class);
    }

    private static ResponseEntity<String> doDeleteUser(RestTemplate restTemplate, HttpEntity<User> entity, long id) {
        return restTemplate.exchange(baseUrl + "/api/users3/" + id, HttpMethod.DELETE, entity, String.class);
    }

}
