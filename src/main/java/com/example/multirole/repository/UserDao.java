package com.example.multirole.repository;

import com.example.multirole.entity.Permission;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {

    List<Permission> fetchPermissions(Long userId);

    User getUserByUsername(String username);

}
