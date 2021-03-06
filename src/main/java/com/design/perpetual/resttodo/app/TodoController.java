/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app;

import com.design.perpetual.resttodo.app.pojos.TodoDTO;
import com.design.perpetual.resttodo.app.entities.Todo;
import com.design.perpetual.resttodo.app.services.MailService;
import com.design.perpetual.resttodo.app.services.TodoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MacDerson
 */
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public TodoDTO getTodos() {
        TodoDTO dto = todoService.getTodos();
        System.out.println("returning: " + dto.getTodos().size() + " todos");
        return dto;//todoService.getTodos();
    }

    @RequestMapping(value = "/todo/{id:\\d+}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Todo getTodo(@PathVariable(value = "id") Integer id) {
        if (Objects.nonNull(id)) {
            return todoService.getTodo(id);
        }
        return new Todo();
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Todo addTodo(@RequestBody Todo todo) {
        if (Objects.isNull(todo)) {
            return todo;
        }
        todoService.addTodoFlush(todo);

        Todo newTodo = todoService.getLatestTodoForMember(todo.getCreatedBy());
        return Objects.nonNull(newTodo) ? newTodo : todo;
    }

    @RequestMapping(value = "/todo/edit", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Todo editTodo(@RequestBody Todo todo) {
        System.out.println("Id: " + todo.getId());
        System.out.println("isRemoved: " + todo.getIsRemoved());
        Todo managedTodo = getTodo(todo.getId());
        if (Objects.nonNull(managedTodo)) {
            boolean isMerged = managedTodo.merge(todo);
            if (isMerged) {
                todoService.addTodoFlush(managedTodo);
            }
        }
        return managedTodo;//todoService.getTodo(todo.getId());
    }

    @RequestMapping(value = "/todo/delete/{id:\\d+}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable(value = "id") Integer id) {
        if (Objects.nonNull(id)) {

            todoService.deleteTodo(id);
        }
    }

    @RequestMapping(value = "/todo/purge/completed", method = RequestMethod.DELETE)
    public TodoDTO purgeCompleted() {
        TodoDTO dto = getTodos();
        if (Objects.nonNull(dto)) {
            List<Todo> todos = Optional.ofNullable(dto.getTodos()).orElse(new ArrayList());
            todos.stream().filter(t -> Objects.nonNull(t.getIsComplete())
                    && t.getIsComplete()).forEach(t -> {
                        deleteTodo(t.getId());
                    });
        }
        return getTodos();
    }

    @RequestMapping(value = "/todo/email", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Todo emailTodo(@RequestBody Todo todo) {
        String sendTo = todo.getSendTo();
        boolean sent = false;
        if (Objects.nonNull(sendTo) && !sendTo.isEmpty()) {
            if (Objects.nonNull(todo)) {
                sent = mailService.sendMail(todo);
            }
        }
        return sent ? todo : null;
    }
}
