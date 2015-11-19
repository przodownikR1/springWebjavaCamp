package pl.java.scalatech.repository;

import pl.java.scalatech.domain.User;

public interface UserRepository {
    User findById(Long id);
}
