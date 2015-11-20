package pl.java.scalatech.converters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import pl.java.scalatech.annotations.EnumFormatter;

@Component
public class EnumFormatterAnnotationFactory implements AnnotationFormatterFactory<EnumFormatter> {

    @Autowired
    private MessageSource messageSource;

    private final Set<Class<?>> fieldTypes;

    public EnumFormatterAnnotationFactory() {
        Set<Class<?>> rawFieldTypes = new HashSet<>();
        rawFieldTypes.add(Enum.class);
        this.fieldTypes = Collections.unmodifiableSet(rawFieldTypes);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Parser<?> getParser(EnumFormatter annotation, Class<?> fieldType) {
        return new DefaultEnumFormatter(fieldType, messageSource);
    }

    @Override
    public Printer<?> getPrinter(EnumFormatter annotation, Class<?> fieldType) {
        return new DefaultEnumFormatter(fieldType, messageSource);
    }
}