package com.example.multirole.repository.impl;

import com.example.multirole.entity.UserPermissionMapping;
import com.example.multirole.repository.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.multirole.entity.Permission;
import org.springframework.security.core.userdetails.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Permission> fetchPermissions(Long userId) {
        List<Permission> permissionList = null;
        Session session = sessionFactory.getCurrentSession();
        permissionList = session.createCriteria(UserPermissionMapping.class)
                .add(Restrictions.eq("userid", userId))
                .list();
        return permissionList;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class)
                .add(Restrictions.eq("username", username)).list();
        if (users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }


}
