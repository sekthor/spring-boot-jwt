package ch.sekthor.springbootjwt.resources;

import ch.sekthor.springbootjwt.models.User;
import ch.sekthor.springbootjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) {

        try {
            if (!userRepository.existsByUsername(user.getUsername())){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {

            // ResponseStatusExceptions will be passed on, others will be replaced by one
            if (e instanceof ResponseStatusException) throw e;
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.accepted().build();

    }
}
