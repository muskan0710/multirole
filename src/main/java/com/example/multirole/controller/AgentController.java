package com.example.multirole.controller;

import com.example.multirole.entity.User;
import org.hibernate.usertype.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;
import java.util.Map;

public class AgentController {

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
        ResponseBean response = new ResponseBean(null,null);
        try {
            User _user = userDao.save(new User(user.))
            AgentBean agent = agentServices.getAgentDetails(authentication.getName());
            agentId = agent.getAgentId();
            UserType user = UserType.fromValue(user.get("userType"));
            adminService.createUser(user.get("olmId"), agent.getCircle().getCircleId(), user.get("storeId"), user,
                    user.get("name"), user.get("userId"), user.get("msisdn"), user.get("lob"),
                    user.get("postpaidSfoCode"), user.get("dslSfoCode"), user.get("dslChannelId"), user.get("lapuNumber"), user.get("managerId"));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



}
