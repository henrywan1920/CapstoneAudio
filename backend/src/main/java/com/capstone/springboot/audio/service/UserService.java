package com.capstone.springboot.audio.service;

import org.springframework.security.core.userdetails.User;

//public interface UserService extends UserDetailsServiceAutoConfiguration {
public interface UserService {
    public boolean checkUserExistsBy(String username);
    public void createNewUser(String username, String password, String role);

    public boolean checkUsernamePassword(String username, String password);
}
