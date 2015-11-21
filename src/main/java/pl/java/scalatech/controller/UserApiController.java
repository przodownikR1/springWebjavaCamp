package pl.java.scalatech.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private  UserRepository userRepository;

    @RequestMapping("/users1/{id}")
    HttpEntity<User> getUser(@PathVariable Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("some-header", "123z");
        return new HttpEntity<>(userRepository.findById(id),responseHeaders);

    }
    @SneakyThrows
    @RequestMapping("/users2/{id}")
   void getUser(@PathVariable Long id,HttpServletResponse response){
        response.setHeader("someH", "321");
        response.setStatus(200);
        ObjectMapper jsonMapper = new ObjectMapper();
        String ret= jsonMapper.writeValueAsString(userRepository.findById(id));
        response.getWriter().write(ret);

    }
    @RequestMapping("/users3/{id}")
    ResponseEntity<User> getUser2(@PathVariable Long id){
        return ResponseEntity.ok(userRepository.findById(id));

    }
}
