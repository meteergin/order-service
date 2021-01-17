/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteergin.orderservice.entity.Order;
import com.meteergin.orderservice.model.Customer;
import com.meteergin.orderservice.service.OrderService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mergin
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/list")
    public ResponseEntity<?> list() {
        try {
            List<Order> o = orderService.findAll();
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(o), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            Optional<Order> o = orderService.findById(id);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(o), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> put(@RequestBody Order order) {
        try {
            Order updateOrder = orderService.updateOrder(order);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(updateOrder), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Order order) {
        try {
            Order makeOrder = orderService.makeOrder(order);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(makeOrder), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(path = "/testOrderPost")
    public ResponseEntity<?> testOrderPost(@RequestBody Order order) {
        try {
            Customer c = restTemplate.getForObject("http://localhost:9193/api/customer/getRandomCustomer", Customer.class);
            order.setCustomerId(c.getId());
            Order makeOrder = orderService.makeOrder(order);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(makeOrder), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
