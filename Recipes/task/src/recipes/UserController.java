package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {

    private final String emailFormat = "\\w+@\\w+\\.\\w+";

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    public HttpStatus userRegistration(@Valid @RequestBody User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            if (user.getEmail().matches(emailFormat)) {
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                return HttpStatus.OK;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
