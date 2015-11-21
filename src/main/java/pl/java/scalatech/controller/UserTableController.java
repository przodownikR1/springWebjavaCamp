package pl.java.scalatech.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@Controller
@Slf4j
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

    @RequestMapping(value ="/redirectUser/{str}/str", method=RequestMethod.GET)
    @ResponseBody String redirectStringUser(@PathVariable String str,Model model){
           log.info("+ +++ model : {}",model);
           return "redirect : to : "+str ;
    }

    @RequestMapping(value ="/redirectUser/{id}", method=RequestMethod.GET)
    String redirectUser(@PathVariable Long id,Model model){
        User loaded = userRepository.findById(id);
        model.addAttribute("username", loaded);
        return "redirect:/redirectUser/"+loaded.getLogin()+"/str";
    }

}
