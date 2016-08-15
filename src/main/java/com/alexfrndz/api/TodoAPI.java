package com.alexfrndz.api;

import com.alexfrndz.pojo.model.SuccessMessageResponse;
import com.alexfrndz.pojo.model.Todo;
import com.alexfrndz.services.TodoServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by alexfernandezwhiteskylabs on 15/08/2016.
 */
@Api(value = "/todolist", description = "the agent api")
@RestController("todolistAPI")
@RequestMapping("todolist")
public class TodoAPI extends BaseController {

    @Autowired
    private TodoServiceImpl todoService;

    @RequestMapping(value = "items", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "", notes = "Create a todo item", response = SuccessMessageResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Status 500 Error")
    })
    public SuccessMessageResponse createTodoItem(@ApiParam(value = "Creates a new todo", required = true) @RequestBody @Valid Todo todo) {
        return todoService.createTodo(todo);
    }

    @RequestMapping(value = "items/{todoItemId}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "", notes = "Get todo item", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Status 500 Error")
    })
    public Todo getTodo(@ApiParam(value = "Todo Id", required = true) @PathVariable Long todoItemId) {
        return todoService.getTodo(todoItemId);
    }


    @RequestMapping(value = "items/{todoItemId}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "", notes = "Create a todo item", response = SuccessMessageResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Status 500 Error")
    })
    public SuccessMessageResponse updateTodoItem(
            @ApiParam(value = "Todo Id", required = true) @PathVariable Long todoItemId,
            @ApiParam(value = "Creates a new todo", required = true) @RequestBody @Valid Todo todo) {
        return todoService.updateTodo(todoItemId, todo);
    }

    @RequestMapping(value = "items/{todoItemId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "", notes = "Get todo item", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Status 500 Error")
    })
    public SuccessMessageResponse deleteTodo(@ApiParam(value = "Customer Id", required = true) @PathVariable Long todoItemId) {
        return todoService.deleteTodo(todoItemId);
    }

    @RequestMapping(value = "items", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "", notes = "Get todo items", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Status 500 Error")
    })
    public Collection<Todo> getTodos() {
        return todoService.getTodos();
    }

}
