package pl.java.scalatech.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//@RestController
@Slf4j
public class ExampleController {
    @RequestMapping("/{date}")
    public String date(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws Exception {
        log.info("{}",date);
        return date.toString();

    }

}
