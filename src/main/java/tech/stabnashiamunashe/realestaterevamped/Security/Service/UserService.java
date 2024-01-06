package tech.stabnashiamunashe.realestaterevamped.Security.Service;

import org.springframework.http.ResponseEntity;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;
import tech.stabnashiamunashe.realestaterevamped.Security.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            return "User Already Exists!";
        }
        userRepository.save(user);
        return "User Saved!";
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
