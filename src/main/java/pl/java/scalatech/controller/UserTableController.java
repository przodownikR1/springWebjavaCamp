package pl.java.scalatech.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@Controller
public class UserTableController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/usersTab")
    String getAllUser(Model model){
        model.addAttribute("users", userRepository.getAll());
        return "hello";
    }
    
    @RequestMapping("/user")
    String getUser(Model model){
        model.addAttribute("user", userRepository.findById(1l));
        return "user";
    }
    
    @RequestMapping(value ="/user", method=RequestMethod.POST)
    String getUser(@Valid User user, BindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return "user";
        }
        return "redirect:/results";
    }
    
}
