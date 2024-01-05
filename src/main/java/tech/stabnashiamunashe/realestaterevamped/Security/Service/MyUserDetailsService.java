package tech.stabnashiamunashe.realestaterevamped.Security.Service;

import tech.stabnashiamunashe.realestaterevamped.Security.Models.SecurityUser;
import tech.stabnashiamunashe.realestaterevamped.Security.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository
                .findByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username " + email + " not found!"));
    }

}
