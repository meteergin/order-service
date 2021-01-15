/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meteergin.orderservice.entity.Order;

/**
 *
 * @author mergin
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
