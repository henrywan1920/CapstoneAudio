package com.capstone.springboot.audio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Timestamp addDays(Timestamp oldTimestamp, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldTimestamp);
        calendar.add(Calendar.DATE, days);
        return new Timestamp(calendar.getTime().getTime());
    }

    private Timestamp addYears(Timestamp oldTimestamp, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldTimestamp);
        calendar.add(Calendar.YEAR, years);
        return new Timestamp(calendar.getTime().getTime());
    }

    @Override
    public boolean findUserByName(String username) {
        return userDetailsManager.userExists(username);
    }

    @Override
    public void createNewUser(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String authority = switch (role) {
            case "vip" -> "ROLE_VIP";
            case "svip" -> "ROLE_SVIP";
            case "admin" -> "ROLE_ADMIN";
            default -> "ROLE_TEMP";
        };
        authorities.add(new SimpleGrantedAuthority(authority));

        // password must be encoded with Bcrypt
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, authorities);
        userDetailsManager.createUser(user);
    }

    /*@Override
    public void createNewUser(String username, String email, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String authority = switch (role) {
            case "vip" -> "ROLE_VIP";
            case "svip" -> "ROLE_SVIP";
            case "admin" -> "ROLE_ADMIN";
            default -> "ROLE_TEMP";
        };
        authorities.add(new SimpleGrantedAuthority(authority));
        User user = new User(username, )
    }*/

    /*@Override
    public void createNewUser(String username, String email, String password, String role) {
        Timestamp start = new Timestamp(System.currentTimeMillis());
        int roleId = switch (role) {
            case "temp" -> 1;
            case "vip" -> 2;
            case "svip" -> 3;
            default -> 4;
        };
        Timestamp end = switch (role) {
            case "temp" -> addDays(start, 7);
            case "vip" -> addDays(start, 30);
            case "svip" -> addYears(start, 1);
            default -> null;
        };
        // password must be encoded with Bcrypt algorithm;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = "{bcrypt}" + encoder.encode(password);
        User user = new User(username, email, encodedPassword);
        userDao.save(user);
        int userId = findUserByEmail(username).getId();

        UserRole userRole = new UserRole(userId, roleId, start, end);
        userRoleRepository.save(userRole);
    }*/

}
