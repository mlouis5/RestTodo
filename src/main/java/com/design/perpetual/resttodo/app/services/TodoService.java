/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.services;

import com.design.perpetual.resttodo.app.entities.HouseholdMember;
import com.design.perpetual.resttodo.app.pojos.TodoDTO;
import com.design.perpetual.resttodo.app.entities.Todo;
import com.design.perpetual.resttodo.app.pojos.HouseholdMemberDTO;
import com.design.perpetual.resttodo.app.repositories.TodoRepo;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.ObjectFactory;
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
    @Autowired
    private HouseholdMemberService hms;
    @Autowired
    private ObjectFactory<TodoDTO> todoDtos;

    public TodoDTO getTodos() {
//        List<Todo> todos = repo.findAll();
//        TodoDTO dto = new TodoDTO();
//        dto.setTodos(todos);
        HouseholdMemberDTO hmd = hms.getHouseholdMembers();
        TodoDTO dto = todoDtos.getObject();
        dto.setTodos(hmd.getAllTodos());
        return dto;
    }

    public Todo getTodo(int id) {
        return repo.find(id);
    }

    public void editTodo(Todo todo) {
        if (Objects.nonNull(todo)) {
            repo.edit(todo);
        }
    }

    public void deleteTodo(int id) {
        Todo t = repo.find(id);
        if (Objects.nonNull(t)) {
            repo.remove(t);
        }
    }

    public void addTodo(Todo todo) {
        if (Objects.nonNull(todo)) {
            
            repo.create(todo);
        }
    }

    public void addTodoFlush(Todo todo) {
        if (Objects.nonNull(todo)) {
            repo.merge(todo);
        }
    }

    public Todo getLatestTodoForMember(HouseholdMember hm) {
        if(Objects.isNull(hm)){
            return null;
        }
        CriteriaBuilder cb = repo.getCriteriaBuilder();
        CriteriaQuery<Todo> cq = repo.getCriteriaQuery();
        Root<Todo> root = repo.getRoot();
        cq.select(root)
                .where(cb.equal(root.get("createdBy")
                                .get("email"), hm.getEmail()))
                .orderBy(cb.desc(root.get("id")));
        List<Todo> list = repo.getCriteriaList(cq);
        return Objects.nonNull(list) && !list.isEmpty() ? list.get(0) : null;
    }
}
