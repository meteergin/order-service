/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice.model;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mergin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private String email;
    private UUID id;
    private String name;

}
