package by.burov.user.core.dto;

import by.burov.user.core.enums.UserRole;
import by.burov.user.core.enums.UserStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CreateUserDto {

    private String mail;
    private String nick;
    private String role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
