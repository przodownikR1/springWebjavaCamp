package pl.java.scalatech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
