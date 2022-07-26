package by.burov.user.controller;

import by.burov.user.controller.utills.token.JwtTokenUtil;
import by.burov.user.core.dto.LoginUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.dto.RegistrationUserDto;
import by.burov.user.repository.entity.User;
import by.burov.user.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> registrationUser(@RequestBody RegistrationUserDto user) {
        userService.register(user);
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    @RequestMapping("/login")
    public String login(@RequestBody LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto);
        return JwtTokenUtil.generateAccessToken(user);
    }

    @GetMapping("/me")
    public ResponseEntity<ReadUserDto> getDetails() {
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }


}
