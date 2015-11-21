package pl.java.scalatech.repository;

import java.util.List;

import pl.java.scalatech.domain.User;

public interface UserRepository {
    User findById(Long id);

    List<User> getAll();

    User save(User user);

    void delete(long id);

}
