package com.alexfrndz.services;

import com.alexfrndz.pojo.model.SuccessMessageResponse;
import com.alexfrndz.pojo.model.Todo;

import java.util.List;

/**
 * Created by alexfernandezwhiteskylabs on 15/08/2016.
 */
public interface TodoService {

    SuccessMessageResponse createTodo(Todo to);
    Todo getTodo(Long todoItemId);
    SuccessMessageResponse updateTodo(Long todoItemId, Todo todo);
    SuccessMessageResponse deleteTodo(Long todoItemId);
    List<Todo> getTodos();
}
