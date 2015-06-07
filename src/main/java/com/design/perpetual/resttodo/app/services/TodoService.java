/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.services;

import com.design.perpetual.resttodo.app.pojos.TodoDTO;
import com.design.perpetual.resttodo.app.entities.Todo;
import com.design.perpetual.resttodo.app.repositories.TodoRepo;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class TodoService {

    @Autowired
    private TodoRepo repo;

    public TodoDTO getTodos() {
        List<Todo> todos = repo.findAll();
        TodoDTO dto = new TodoDTO();
        dto.setTodos(todos);
        return dto;
    }
    
    public Todo getTodo(int id){
        return repo.find(id);
    }

    public void editTodo(Todo todo) {
        if (Objects.nonNull(todo)) {
            repo.edit(todo);
        }
    }
    
    public void deleteTodo(int id){
        Todo t = repo.find(id);
        if(Objects.nonNull(t)){
            repo.remove(t);
        }        
    }
    
    public void addTodo(Todo todo){
        if(Objects.nonNull(todo)){
            repo.create(todo);
        }
    }
    
    public void addTodoFlush(Todo todo){
        if(Objects.nonNull(todo)){
            repo.createFlush(todo);
        }
    }
}
