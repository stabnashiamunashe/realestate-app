package tech.stabnashiamunashe.realestaterevamped.Security.Service;

import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;
import tech.stabnashiamunashe.realestaterevamped.Security.Repositories.UserRepository;
import org.springframework.stereotype.Service;

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
}
