package by.burov.event.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserHolder {


    public UserDetails getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals("anonymousUser", principal)) {
            return (UserDetails) principal;
        } else {
            return null;
        }

    }

}
