package by.burov.user.core.dto;

import javax.validation.constraints.NotBlank;

public class LoginUserDto {

    @NotBlank
    private String mail;
    @NotBlank
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
