package springWebjavaCamp.config;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigList.class)
@Slf4j
public class ListsTEst {

    @Resource
   //@Qualifier("items")
   private List<Item> items;
   @Autowired
   private ApplicationContext app;

    @Test
    public void shouldAutowiredLists() {
        log.info("app :  {} ",Lists.newArrayList(app.getBeanDefinitionNames()));
        List<Item> obj = app.getBean("items",List.class);
        log.info("{}  ->  {}",items, obj);
    }

}
