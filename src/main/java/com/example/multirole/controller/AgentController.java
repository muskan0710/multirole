package com.example.multirole.controller;

import com.example.multirole.entity.User;
import com.example.multirole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class AgentController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user = userRepository
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
        User user = userRepository
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
