package com.example.multirole.controller;

import com.example.multirole.entity.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class StoreController {
    @RequestMapping(value = "/createStore", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Store> createUser(@RequestBody Store store) {


}
}
