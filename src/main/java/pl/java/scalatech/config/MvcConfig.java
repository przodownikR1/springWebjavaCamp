package pl.java.scalatech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@ComponentScan({ "pl.java.scalatech.controller", "pl.java.scalatech.initializer" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

}
