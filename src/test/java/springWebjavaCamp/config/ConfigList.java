package springWebjavaCamp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

@Configuration
public class ConfigList {



  @Bean(name="items")
  //@ClassPresentConditional(value="org.h2.Driver",negated=false)
  public List<Item> strings(){
     return Lists.newArrayList(new Item("slawek"),new Item("maciek"),new Item("koala"));
  }

}

