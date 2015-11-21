package pl.java.scalatech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping("/users")
    ResponseEntity<List<User>> getAllUser(){
        log.info("+++  {}",userRepository.getAll());
        return ResponseEntity.ok(userRepository.getAll());
    }

    @RequestMapping("/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id")User user){
         log.info("getUserById {}",user);
        return ResponseEntity.ok(user);

    }

    @RequestMapping("/users/{id}/long")
    ResponseEntity<Long> getUserByIdLong(@PathVariable("id")Long id){
         log.info("getUserById {}",id);
        return ResponseEntity.ok(id);

    }

    @RequestMapping("/users/{id}/conversion")
    ResponseEntity<User> getUserById(@PathVariable("id")Long id){
         log.info("getUserById {}",id);
         User user= conversionService.convert(id, User.class);
        return ResponseEntity.ok(user);

    }

    @RequestMapping("/amount/{id}")
    ResponseEntity<String> getAmount(@PathVariable("id")Long id){
         log.info("getUserById {} , format : {}",id);

        return ResponseEntity.ok(""+conversionService.convert(id, String.class));

    }




   /* @RequestMapping(value = "*", method = RequestMethod.GET)
    public void catchAll() {
        log.info("++++++ catch all");
    }*/

}
