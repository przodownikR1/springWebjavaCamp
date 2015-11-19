package pl.java.scalatech.converters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class AmountFormatter implements Formatter<Long>{

    @Override
    public String print(Long object, Locale locale) {
        String ret = object.toString() + " USD";
        if(locale.getLanguage().equals(new Locale("pl").getLanguage())) {
        ret = object.toString() + " PLN";
        }
        log.info("+++  {}",ret);
        return ret;
    }

    @Override
    public Long parse(String text, Locale locale) throws ParseException {
        String str[] = text.split(" ");
        Long ret = Long.parseLong(str[0]);
        log.info("parse : {}",ret);
        return ret;
    }

}
