/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice.service;

import com.meteergin.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meteergin.orderservice.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author mergin
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order makeOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
    
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
    
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
    
}
