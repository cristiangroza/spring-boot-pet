package io.cristiangroza.poc.users;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UsersRepository {
    private final ConcurrentMap<Long, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    void post() {
        users.put(1L, new User(1L, "Cristian", "Groza", "cg@yopmail.com"));
        users.put(2L, new User(2L, "John", "Doe", "john.doe@yopmail.com"));
        users.put(3L, new User(3L, "Jane", "Doe", "jane.doe@yopmail.com"));
    }
    public User findById(Long id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        if(user.id() == null || !users.containsKey(user.id())) {
            Long newId = users.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
            User newUser = new User(newId, user.firstName(), user.lastName(), user.email());
            users.put(newId, newUser);
            return newUser;
        }
        users.put(user.id(), user);
        return user;
    }
}
