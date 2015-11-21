package pl.java.scalatech.config;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.fasterxml.jackson.databind.SerializationFeature;

import pl.java.scalatech.converters.AmountFormatAnnotationFormatterFactory;
import pl.java.scalatech.converters.AmountFormatter;
import pl.java.scalatech.converters.LongToUserConverter;
import pl.java.scalatech.converters.StringToUserConverter;
import pl.java.scalatech.domain.User;

@Configuration
@ComponentScan({ "pl.java.scalatech.controller", "pl.java.scalatech.initializer" })
@EnableWebMvc
@Import(value= {ThymeleafConfig.class,I18nConfig.class})
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private StringToUserConverter stringToUserConverter;
    @Autowired
    private LongToUserConverter longToUserConverter;
    @Autowired
    private AmountFormatter amountFormatter;
    @Autowired
    private AmountFormatAnnotationFormatterFactory amountFormatAnnotationFormatterFactory;
    @Autowired
    private  LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addConverter(stringToUserConverter);
        registry.addConverter(longToUserConverter);
        registry.addFormatter(amountFormatter);
        registry.addFormatterForFieldAnnotation(amountFormatAnnotationFormatterFactory);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/illegalEx").setViewName("illegalEx");
    }
    @Override
    public  void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
        Map<String, MediaType> mediaTypeMap = newHashMap();
        mediaTypeMap.put("html", MediaType.TEXT_HTML);
        mediaTypeMap.put("atom", MediaType.APPLICATION_ATOM_XML);
        mediaTypeMap.put("json", MediaType.APPLICATION_JSON);
        mediaTypeMap.put("xml", MediaType.APPLICATION_XML);

        configurer.favorPathExtension(true).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true).useJaf(false)
                .defaultContentType(MediaType.TEXT_HTML).mediaTypes(mediaTypeMap);


    }



    @Bean
    public ViewResolver viewResolver(ContentNegotiationManager manager, View jsonView, View xmlView, ThymeleafViewResolver thymeleafResolver) {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);
        viewResolver.setOrder(1);

        List<ViewResolver> viewResolverList = newArrayList();
        viewResolverList.add(thymeleafResolver);
        viewResolver.setOrder(1);
        viewResolver.setViewResolvers(viewResolverList);
        viewResolver.setDefaultViews(Arrays.asList(jsonView, xmlView));
        return viewResolver;
    }


@Bean
public View jsonView() {
    return new MappingJackson2JsonView();
}

@Bean
public Jaxb2Marshaller jaxb2Marshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setClassesToBeBound(User.class);
    return jaxb2Marshaller;
}



  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
      Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
      builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
  }
}
