package com.example.multirole.controller;

import com.example.multirole.entity.User;
import com.example.multirole.repository.UserDao;
import com.example.multirole.repository.UserRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/test")
public class AgentController {
    @Autowired
    private UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        // ResponseBean response = new ResponseBean(null,null);
//        try {
//            User _user = userDao.save(new User(user.))
//            AgentBean agent = agentServices.getAgentDetails(authentication.getName());
//            agentId = agent.getAgentId();
//            UserType user = UserType.fromValue(user.get("userType"));
//            adminService.createUser(user.get("olmId"), agent.getCircle().getCircleId(), user.get("storeId"), user,
//                    user.get("name"), user.get("userId"), user.get("msisdn"), user.get("lob"),
//                    user.get("postpaidSfoCode"), user.get("dslSfoCode"), user.get("dslChannelId"), user.get("lapuNumber"), user.get("managerId"));
//            return new ResponseEntity<>(_user, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
        System.out.println("in create user");
        return userRepository.save(user);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {


        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @RequestMapping(value = "/modifyUser/{id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<User> modifyUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails)
            throws ResourceNotFoundException {


        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));


        user.setUsername(userDetails.getUsername());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {

        return userRepository.findAll();
    }


}
