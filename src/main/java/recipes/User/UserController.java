package recipes.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerNewUser(@Valid @RequestBody UserEntity newUser) {
        Optional<UserEntity> response = userService.findUserEntityByUsername(newUser.getEmail());
        if (response.isEmpty()) {
            userService.addUserEntity(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}