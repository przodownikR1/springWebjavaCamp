package pl.java.scalatech.repository.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
@Repository
public class UserRepositoryImpl implements UserRepository{

 private ConcurrentHashMap<Long,User> users = new ConcurrentHashMap<>();

 @PostConstruct
 public void init() {
     users.put(1l, new User(1l,"slawek","przodownik"));
     users.put(2l, new User(1l,"mike","tyson"));
 }


 @Override
public User findById(Long id) {
     return  users.entrySet().stream().filter(t->t.getKey() == id).map(t -> t.getValue()).findFirst().orElseThrow(()->new IllegalArgumentException("user not exists"));


 }


@Override
public List<User> getAll() {
    return users.entrySet().stream().map(t -> t.getValue()).collect(toList());
}

}
