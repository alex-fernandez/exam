package com.alexfrndz.services;

import com.alexfrndz.pojo.exceptions.DataException;
import com.alexfrndz.pojo.exceptions.NotFoundException;
import com.alexfrndz.pojo.model.SuccessMessageResponse;
import com.alexfrndz.pojo.model.Todo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by alexfernandezwhiteskylabs on 15/08/2016.
 */
@Service
@Data
@Slf4j
public class TodoServiceImpl implements TodoService {


    private Map<Long, Todo> todoValues = Maps.newLinkedHashMap();
    private Long currentId = 0L;

    @Override
    public SuccessMessageResponse createTodo(Todo todo) {
        validateTodo(todo);
        sanitizeData(todo);
        if (findTodoByName(todoValues, todo.getName()) == null) {
            currentId = currentId + 1;
            todo.setId(currentId);
            todoValues.put(currentId, todo);
        } else {
            todoValues.put(todo.getId(), todo);
        }
        log.info("Todo Id: {}, Name: {} Created. ", todo.getId(), todo.getName());
        return new SuccessMessageResponse<>(currentId, "OK", "Todo successfully created");
    }

    @Override
    public Todo getTodo(Long todoItemId) {

        if (todoValues.get(todoItemId) == null) {
            throw new NotFoundException(404, "Todo Item not found");
        }
        log.info("Get Todo Id: {}", todoItemId);
        return todoValues.get(todoItemId);
    }

    @Override
    public SuccessMessageResponse updateTodo(Long todoItemId, Todo todo) {
        validateTodo(todo);
        sanitizeData(todo);
        if (findTodoByName(todoValues, todo.getName()) == null) {
            currentId = currentId + 1;
            todoValues.put(currentId, todo);
        } else {
            todo.setId(todoItemId);
            todoValues.put(todo.getId(), todo);
        }

        log.info("Update Todo Id: {}", todoItemId);
        return new SuccessMessageResponse<>(todo.getId(), "OK", "Todo successfully updated");
    }

    @Override
    public SuccessMessageResponse deleteTodo(Long todoItemId) {
        if (todoValues.get(todoItemId) == null) {
            throw new NotFoundException(404, "Todo Item not found");
        }
        log.info("Delete Todo: {}", todoItemId);
        return new SuccessMessageResponse<>(todoItemId, "OK", "Todo successfully deleted");
    }

    @Override
    public List<Todo> getTodos() {
        log.info("Get Todos");
        return Lists.newArrayList(todoValues.values());
    }

    private void validateTodo(Todo todo) {
        if (todo == null) {
            throw new DataException(400, "'Todo' values is required.");
        }
        if (StringUtils.isEmpty(todo.getName())) {
            throw new DataException(400, "A name is required for any item");
        }
    }

    private void sanitizeData(Todo todo) {
        todo.setPriority(todo.getPriority() == null ? 1 : todo.getPriority());
    }

    private Todo findTodoByName(Map<Long, Todo> todoValues, String name) {
        for (Map.Entry<Long, Todo> entry : todoValues.entrySet()) {
            if (StringUtils.equalsIgnoreCase(name, entry.getValue().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
