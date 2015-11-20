package pl.java.scalatech.converters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

@SuppressWarnings("rawtypes")
public class DefaultEnumFormatter implements Formatter<Enum<?>> {

    private final Class type;
    private final MessageSource messageSource;

    public DefaultEnumFormatter(Class type, MessageSource messageSource) {
        this.type = type;
        this.messageSource = messageSource;
    }

    public Class<?> getTarget() {
        return type;
    }

    @Override
    public String print(Enum<?> value, Locale locale) {
        if (value == null) {
            return "";
        }
        return messageSource.getMessage(value.getClass().getSimpleName() + "_" + value.name(), null, locale);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enum<?> parse(String text, Locale locale) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return Enum.valueOf(type, text);
    }
}