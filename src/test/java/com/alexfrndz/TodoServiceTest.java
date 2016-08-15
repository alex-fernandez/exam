package com.alexfrndz;

import com.alexfrndz.pojo.exceptions.DataException;
import com.alexfrndz.pojo.exceptions.NotFoundException;
import com.alexfrndz.pojo.model.SuccessMessageResponse;
import com.alexfrndz.pojo.model.Todo;
import com.alexfrndz.services.TodoServiceImpl;
import com.google.common.collect.Maps;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {


    @InjectMocks
    @Spy
    private TodoServiceImpl todoService = new TodoServiceImpl();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void testCreateTodo() {

        Todo todo = new Todo();
        todo.setPriority(1L);
        todo.setName("Todo1");

        SuccessMessageResponse todoResponse = todoService.createTodo(todo);
        MatcherAssert.assertThat("Response is null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Id should be 1", todoResponse.getId().toString(), is("1"));
    }

    @Test(expected = DataException.class)
    public void testCreateTodoWithEmptyTodoName() {

        Todo todo = new Todo();
        todo.setPriority(1L);

        SuccessMessageResponse todoResponse = todoService.createTodo(todo);
        MatcherAssert.assertThat("Response is null", todoResponse, is(nullValue()));
    }

    @Test
    public void testUpdateTodoAndSucceed() {

        Todo oldTodo = new Todo();
        oldTodo.setId(1L);
        oldTodo.setPriority(1L);
        oldTodo.setName("Todo1");

        Todo newTodo = new Todo();
        newTodo.setId(1L);
        newTodo.setPriority(1L);
        newTodo.setName("Todo2");


        Map<Long, Todo> todoMap = Mockito.mock(Map.class);
        todoService.setTodoValues(todoMap);
        Mockito.when(todoMap.get(1L)).thenReturn(oldTodo);

        SuccessMessageResponse todoResponse = todoService.updateTodo(1L, newTodo);
        MatcherAssert.assertThat("Response is null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Id should be 1", todoResponse.getId().toString(), is("1"));
        Mockito.when(todoMap.get(1L)).thenReturn(newTodo);
        MatcherAssert.assertThat("Name should be should be Todo2", todoService.getTodoValues().get(1L).getName(), is("Todo2"));
    }

    @Test
    public void testGetTodoAndExpectTodoResponse() {

        Map<Long, Todo> todoMap = Maps.newHashMap();
        Todo oldTodo = new Todo();
        oldTodo.setId(1L);
        oldTodo.setPriority(1L);
        oldTodo.setName("Todo1");
        todoMap.put(1L, oldTodo);
        todoService.setTodoValues(todoMap);

        Todo todoResponse = todoService.getTodo(1L);
        MatcherAssert.assertThat("Response is null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Id should be 1", todoResponse.getId().toString(), is("1"));
        MatcherAssert.assertThat("Name should be Todo1", todoService.getTodoValues().get(1L).getName(), is("Todo1"));
    }

    @Test(expected = NotFoundException.class)
    public void testGetTodoAndExpectsNotFoundException() {

        Todo todoResponse = todoService.getTodo(1L);
        MatcherAssert.assertThat("Response is null", todoResponse, is(nullValue()));
    }

    @Test
    public void testGetTodosAndExpectsNonEmptyList() {
        Map<Long, Todo> todoMap = Maps.newHashMap();
        Todo oldTodo = new Todo();
        oldTodo.setId(1L);
        oldTodo.setPriority(1L);
        oldTodo.setName("Todo1");
        todoMap.put(1L, oldTodo);
        todoService.setTodoValues(todoMap);

        List<Todo> todoResponse = todoService.getTodos();
        MatcherAssert.assertThat("Response is null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Todo Size is empty", todoResponse.size(), is(1));
        MatcherAssert.assertThat("Id should be 1", todoResponse.get(0).getId().toString(), is("1"));
    }


    @Test
    public void testGetTodosAndExpectsEmptyList() {
        List<Todo> todoResponse = todoService.getTodos();
        MatcherAssert.assertThat("Response is null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Todo Size is not empty", todoResponse.size(), is(0));
    }


    @Test
    public void testDeleteAndExpectedToSucceed() {
        Map<Long, Todo> todoMap = Maps.newHashMap();
        Todo oldTodo = new Todo();
        oldTodo.setId(1L);
        oldTodo.setPriority(1L);
        oldTodo.setName("Todo1");
        todoMap.put(1L, oldTodo);
        todoService.setTodoValues(todoMap);
        SuccessMessageResponse todoResponse = todoService.deleteTodo(1L);
        MatcherAssert.assertThat("Response is not null", todoResponse, is(notNullValue()));
        MatcherAssert.assertThat("Id should be 1", todoResponse.getId().toString(), is("1"));
    }



    @Test(expected = NotFoundException.class)
    public void testDeleteNonExistingRecordAndExpectsNotFoundException() {
        SuccessMessageResponse todoResponse = todoService.deleteTodo(1L);
        MatcherAssert.assertThat("Response is not null", todoResponse, is(nullValue()));
    }


}