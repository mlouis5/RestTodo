/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.pojos;

import com.design.perpetual.resttodo.app.entities.Todo;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TodoDTO {

    private List<Todo> todos;
    
    public TodoDTO(){}
    
    public TodoDTO(Todo todo){
        todos = new ArrayList(1);
        todos.add(todo);
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        if(Objects.nonNull(todos)){            
            this.todos = Lists.newArrayList(todos.stream().filter(t -> !t.getIsRemoved()).iterator());
        }
    }

    public void addTodo(Todo todo) {
        if (Objects.nonNull(todos) && Objects.nonNull(todo)) {
            this.todos.add(todo);            
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.todos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TodoDTO other = (TodoDTO) obj;
        return Objects.equals(todos, other.todos);
    }
    
    public boolean isSingle(){
        return todos.size() == 1;
    }

}
