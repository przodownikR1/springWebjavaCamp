package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pl.java.scalatech.converters.StringToUserConverter;
@Configuration
@ComponentScan({ "pl.java.scalatech.controller", "pl.java.scalatech.initializer" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

@Autowired
private StringToUserConverter stringToUserConverter;

@Override
public void addFormatters(FormatterRegistry registry) {
    super.addFormatters(registry);
    registry.addConverter(stringToUserConverter);
}

}
