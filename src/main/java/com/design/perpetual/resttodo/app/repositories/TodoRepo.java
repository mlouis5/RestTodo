/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.repositories;

import com.design.perpetual.resttodo.app.entities.Todo;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class TodoRepo extends AbstractRepository<Todo> {

    public TodoRepo() {
        super(Todo.class);
    }
    
}
