package com.example.multirole.controller;

import com.example.multirole.entity.Store;
import com.example.multirole.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class StoreController {
    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(value = "/createStore", method = RequestMethod.POST)
    public @ResponseBody Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @RequestMapping(value = "/deleteStore/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteStore(@PathVariable(value = "id") Long storeId) throws Exception {
        Store store = storeRepository
                .findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found on :: " + storeId));
        storeRepository.delete(store);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @RequestMapping(value = "/modifyStore/{id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Store> modifyStore(@PathVariable(value = "id") Long storeId, @RequestBody Store storeDetails)
            throws ResourceNotFoundException {
        Store store = storeRepository
                .findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found on :: " + storeId));
        store.setStorename(storeDetails.getStorename());
        final Store updatedStore = storeRepository.save(store);
        return ResponseEntity.ok(updatedStore);
    }

    @RequestMapping(value = "/getStores", method = RequestMethod.GET)
    public @ResponseBody List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}
