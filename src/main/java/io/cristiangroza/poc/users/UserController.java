package io.cristiangroza.poc.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(path =  "/{id}")
    public User getUserById(@PathVariable Long id) {
        return usersRepository.findById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @PutMapping
    public User createUser(@RequestBody User user) {
        return usersRepository.save(user);
    }
}
