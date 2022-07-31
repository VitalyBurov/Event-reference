package by.burov.user.core.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationUserDto {

    @NotBlank
    private String mail;
    @NotBlank
    private String nick;
    @NotBlank
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
