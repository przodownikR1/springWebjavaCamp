package pl.java.scalatech.converters;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@Component
public class StringToUserConverter implements Converter<String,User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String id) {
        checkNotNull(id);
        return userRepository.findById(Long.parseLong(id));
    }

}
