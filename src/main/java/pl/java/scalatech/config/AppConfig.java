package pl.java.scalatech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"pl.java.scalatech.service","pl.java.scalatech.repository","pl.java.scalatech.converters"})
public class AppConfig {

}
