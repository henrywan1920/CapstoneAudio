package com.capstone.springboot.audio.service;

//public interface UserService extends UserDetailsServiceAutoConfiguration {
public interface UserService {
    public boolean findUserByName(String username);

    public void createNewUser(String username, String password, String role);
}
