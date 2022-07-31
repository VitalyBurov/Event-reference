package by.burov.user.controller;

import by.burov.user.core.api.PageConverter;
import by.burov.user.core.api.PageResponse;
import by.burov.user.core.dto.CreateUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.service.api.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder encoder;

    public AdminController(AdminService adminService,
                           PasswordEncoder encoder) {
        this.adminService = adminService;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<ReadUserDto> createUser(@RequestBody CreateUserDto user) {
        return new ResponseEntity<>(adminService.save(user), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<PageResponse<ReadUserDto>> getAllUsers(@RequestParam(defaultValue = "1") int pageNo,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        PageResponse<ReadUserDto> response = new PageConverter<ReadUserDto>().convert(adminService.readAll(pageNo - 1, pageSize));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ReadUserDto> getUserByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(adminService.getUserByUuid(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    // change to Read concert
    public ResponseEntity<ReadUserDto> updateUser(@PathVariable("uuid") UUID uuid,
                                                  @PathVariable("dt_update") Long dt,
                                                  @RequestBody CreateUserDto user) {
        //Should refactor
        LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt), ZoneId.systemDefault());

        return new ResponseEntity<>(adminService.update(uuid, dtUpdate, user), HttpStatus.CREATED);
    }
}
