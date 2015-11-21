package pl.java.scalatech.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users1/{id}")
    HttpEntity<User> getUser(@PathVariable Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("some-header", "123z");
        return new HttpEntity<>(userRepository.findById(id), responseHeaders);

    }

    @SneakyThrows
    @RequestMapping("/users2/{id}")
    void getUser(@PathVariable Long id, HttpServletResponse response) {
        response.setHeader("someH", "321");
        response.setStatus(200);
        ObjectMapper jsonMapper = new ObjectMapper();
        String ret = jsonMapper.writeValueAsString(userRepository.findById(id));
        response.getWriter().write(ret);

    }

    @RequestMapping(value = "/users3/{id}", method = RequestMethod.GET)
    ResponseEntity<User> getUser2(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id));

    }

    @RequestMapping("/path/{id}")
    String path(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
        UriComponents uriComponents = uriBuilder.path("/api/users3/{id}").buildAndExpand(id);
        return uriComponents.toUri().toString();
    }

    @RequestMapping(value = "/users3/{id}", method = RequestMethod.POST)
    HttpEntity<Void> post(@PathVariable Long id, @RequestBody User user, HttpServletRequest request) {
        User loaded = userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        id = loaded.getId();
        final UriComponentsBuilder builder = ServletUriComponentsBuilder.fromPath("/api/users3/" + id).scheme(request.getScheme()).host(request.getServerName())
                .port(request.getServerPort());
        headers.setLocation(builder.build().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users3/{id:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userRepository.delete(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users3/{id:\\d+}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateResource(@PathVariable Long id, @RequestBody @Valid final User resource) {
        User loaded = userRepository.save(resource);
        return new ResponseEntity<>(loaded, HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/users3/{id:\\d+}/ex/{ex:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<String> updateResource(@PathVariable Long id,@PathVariable Integer ex) {
        if(ex == 1) {
            throw new IllegalArgumentException("ex ==1");
        }
        if(ex == 2) {
            throw new IllegalStateException("ex == 2");
        }

        return  ResponseEntity.ok().body("normal");

    }

   /* @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleUnsupportedOperation1(UnsupportedOperationException uoe) {
        return new ResponseEntity<>(uoe.getMessage(), HttpStatus.BAD_REQUEST);
    }*/


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleUnsupportedOperation2(UnsupportedOperationException uoe) {
        return new ResponseEntity<>(uoe.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleUnsupportedOperation(UnsupportedOperationException uoe) {
        return new ResponseEntity<>(uoe.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
