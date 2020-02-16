package ch.sekthor.springbootjwt.services;

import ch.sekthor.springbootjwt.models.AppUserDetails;
import ch.sekthor.springbootjwt.models.User;
import ch.sekthor.springbootjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();

        return new AppUserDetails(user);
    }
}
