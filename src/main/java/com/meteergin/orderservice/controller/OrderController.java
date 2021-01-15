/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.meteergin.orderservice.entity.Order;
import com.meteergin.orderservice.service.OrderService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mergin
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
    public ResponseEntity<?> get(@PathVariable int id) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
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
