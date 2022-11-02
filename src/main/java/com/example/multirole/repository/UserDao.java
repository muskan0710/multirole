package com.example.multirole.repository;

import org.springframework.security.core.userdetails.User;

public interface UserDao {
    User getUserByUsername(String username);
}
