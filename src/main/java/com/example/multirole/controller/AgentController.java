package com.example.multirole.controller;

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
        try {
            User _user = userDao.save(new User(user.))
            AgentBean agent = agentServices.getAgentDetails(authentication.getName());
            agentId = agent.getAgentId();
            UserType user = UserType.fromValue(data.get("userType"));
            adminService.createUser(data.get("olmId"), agent.getCircle().getCircleId(), data.get("storeId"), user,
                    data.get("name"), data.get("userId"), data.get("msisdn"), data.get("lob"),
                    data.get("postpaidSfoCode"), data.get("dslSfoCode"), data.get("dslChannelId"), data.get("lapuNumber"), data.get("managerId"));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



}
