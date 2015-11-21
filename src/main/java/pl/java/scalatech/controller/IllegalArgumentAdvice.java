package pl.java.scalatech.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class IllegalArgumentAdvice {

    /*@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Wrong parameters")
    public  String  handleIllegalArgument(Model model,Exception exception) {
        model.addAttribute("test", ""+exception.getMessage());
        return "illegal";

    }*/

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ModelAndView  handleRuntimeException(Exception e, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ex", e.getMessage());
        log.error("RuntimeException in Request: " + request.toString());
        log.error(e.toString(), e);
        modelAndView.setViewName("illegalEx");
        return modelAndView;
    }
}