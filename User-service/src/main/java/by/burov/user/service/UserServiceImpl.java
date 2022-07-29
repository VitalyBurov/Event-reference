package by.burov.user.service;

import by.burov.user.core.dto.LoginUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.dto.RegistrationUserDto;
import by.burov.user.core.enums.UserRole;
import by.burov.user.core.enums.UserStatus;
import by.burov.user.repository.AuthorityRepository;
import by.burov.user.repository.UserRepository;
import by.burov.user.repository.entity.Authority;
import by.burov.user.repository.entity.User;
import by.burov.user.service.api.UserMapper;
import by.burov.user.service.api.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    @Override
    public ReadUserDto register(RegistrationUserDto dto) {
        User user = userMapper.toRegistrationEntity(dto);
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singleton(new Authority(1L, String.valueOf(UserRole.USER))));
        User savedUser = userRepository.save(user);
        ReadUserDto readUserDto = userMapper.toReadDto(savedUser);
        Set<String> roles = user.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
        readUserDto.setRoles(roles);
        return readUserDto;
    }

    @Transactional
    @Override
    public User login(LoginUserDto loginUserDto) {
        User user = this.loadUserByUsername(loginUserDto.getMail());
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }
        return user;
    }

    @Transactional
    @Override
    public User loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Transactional
    @Override
    public ReadUserDto getUserInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReadUserDto readUserDto = userMapper.toReadDto(user);
        Set<String> roles = user.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
        readUserDto.setRoles(roles);
        return readUserDto;
    }





}



