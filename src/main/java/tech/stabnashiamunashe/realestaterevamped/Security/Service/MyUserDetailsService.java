package tech.stabnashiamunashe.realestaterevamped.Security.Service;

import org.springframework.transaction.annotation.Transactional;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Security.Repositories.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username " + email + " not found!"));
    }

}
