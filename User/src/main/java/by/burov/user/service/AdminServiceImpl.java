package by.burov.user.service;

import by.burov.user.core.dto.CreateUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.enums.UserRole;
import by.burov.user.core.enums.UserStatus;
import by.burov.user.repository.AuthorityRepository;
import by.burov.user.repository.UserRepository;
import by.burov.user.repository.entity.Authority;
import by.burov.user.repository.entity.User;
import by.burov.user.service.api.AdminService;
import by.burov.user.service.api.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }


    @Override
    @Transactional
    public ReadUserDto save(CreateUserDto dto) {
        User user = userMapper.toCreateEntity(dto);
        LocalDateTime now = LocalDateTime.now();
        user.setDtCreate(now);
        user.setDtUpdate(now.minusNanos(now.getNano() % 1_000_000));

        Authority authority = getAuthority(dto);

        user.setAuthorities(Collections.singleton(authority));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user = userRepository.save(user);
        ReadUserDto readUserDto = userMapper.toReadDto(user);
        Set<String> roles = user.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
        readUserDto.setRoles(roles);
        return readUserDto;
    }


    @Override
    @Transactional
    public Page<ReadUserDto> readAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<User> usersFromDB = userRepository.findAll(paging);

        Page<ReadUserDto> pagedResult = usersFromDB.map(entity -> {
            ReadUserDto readUserDto = userMapper.toReadDto(entity);
            Set<String> roles = entity.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
            readUserDto.setRoles(roles);
            return readUserDto;
        });
        return pagedResult;
    }

    @Override
    @Transactional
    public ReadUserDto getUserByUuid(UUID uuid) {
        User user = userRepository.findById(uuid).get();
        ReadUserDto readUserDto = userMapper.toReadDto(user);
        Set<String> roles = user.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
        readUserDto.setRoles(roles);
        return readUserDto;
    }


    @Transactional
    @Override
    public ReadUserDto update(UUID uuid, LocalDateTime dtUpdate, CreateUserDto dto) {
        if (uuid == null) {
            throw new IllegalArgumentException("No such user");
        }
        User user = userRepository.findById(uuid).get();

        if (!user.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("The User was updated before you!!!");
        }

        //Should refactor
        populateNewUserData(dto, user);
        user = userRepository.save(user);
        ReadUserDto readUserDto = userMapper.toReadDto(user);
        Set<String> roles = user.getRoles().stream().map(Authority::getRoleName).collect(Collectors.toSet());
        readUserDto.setRoles(roles);
        return readUserDto;

    }

    private void populateNewUserData(CreateUserDto dto, User user) {
        Authority authority = getAuthority(dto);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setStatus(dto.getStatus());
        user.setStatus(dto.getStatus());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

    }

    private Authority getAuthority(CreateUserDto dto) {
        Authority authority = null;
        switch (dto.getRole()) {
            case "USER":
                authority = new Authority(1L, String.valueOf(UserRole.USER));
                break;
            case "ADMIN":
                authority = new Authority(2L, String.valueOf(UserRole.ADMIN));
                break;
        }
        return authority;
    }

}



