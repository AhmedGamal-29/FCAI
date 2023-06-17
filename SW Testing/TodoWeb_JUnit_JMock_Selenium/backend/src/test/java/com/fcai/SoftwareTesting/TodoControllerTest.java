package com.fcai.SoftwareTesting;

import com.fcai.SoftwareTesting.todo.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TodoControllerTest
{

    private TodoController todoController;



    @Mock
    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        todoController = new TodoController(todoService);
    }

    @Test
    public void testCreate_Successful() {
        // Mock request body
        TodoCreateRequest requestBody = new TodoCreateRequest("title","desc");

        // Mock created Todo
        Todo createdTodo = new Todo();
        createdTodo.setId("1");
        createdTodo.setTitle("title");
        createdTodo.setDescription("desc");

        // Mock the todoService.create() method to return the createdTodo
        when(todoService.create(requestBody)).thenReturn(createdTodo);

        // Call the create method on the todoController
        ResponseEntity<Todo> response = todoController.create(requestBody);

        // Verify the todoService.create() method was called with the correct request body
        verify(todoService, times(1)).create(requestBody);

        // Verify the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify the response body is the same as the createdTodo
        assertEquals(createdTodo, response.getBody());
    }

    @Test
    public void testCreate_InvalidRequest() {

        // Mock request body with empty title
        TodoCreateRequest requestBody = new TodoCreateRequest("","desc");


        when(todoService.create(requestBody)).thenThrow(IllegalArgumentException.class);

        // Verify the todoService.create() method was called with the correct requestBody
        verify(todoService, never()).create(requestBody);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            // Call the create method on the todoController
            ResponseEntity<Todo> response = todoController.create(requestBody);

        });

    }

    @Test
    public void testRead_Successful() {
        // Mock the Todo object
        Todo todo = new Todo();
        todo.setId("1");
        todo.setTitle("Title");
        todo.setDescription("Description");

        // Mock the todoService.read() method
        when(todoService.read("1")).thenReturn(todo);


        ResponseEntity<Todo> response = todoController.read("1");

        // Verify the todoService.read() method was called with the correct ID
        verify(todoService, times(1)).read("1");

        // Verify the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(todo, response.getBody());
    }

    @Test
    public void testRead_InvalidRequest() {

        String invalidId = "-10";

        // Mock the todoService.read() method to throw an exception
        when(todoService.read(invalidId)).thenThrow(IllegalArgumentException.class);

        // Call the read method on the todoController with the invalid ID
        ResponseEntity<Todo> response = todoController.read(invalidId);

        // Verify the todoService.read() method was called with the correct ID
        verify(todoService, times(1)).read(invalidId);

        // Verify the response status code is 400 (Bad Request)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdate_Success()
    {
        // Mock request parameters
        String id = "1";
        boolean completed = true;

        // Mock the todoService.update() method
        Todo updatedTodo = new Todo(id, "Updated Todo", "desc", completed);
        when(todoService.update(id, completed)).thenReturn(updatedTodo);

        // Call the update method on the todoController
        ResponseEntity<Todo> response = todoController.update(id, completed);

        // Verify the todoService.update() method was called with the correct parameters
        verify(todoService, times(1)).update(id, completed);

        // Verify the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify the response body contains the updated todo
        assertEquals(updatedTodo, response.getBody());
    }


    @Test
    public void testUpdate_InvalidRequest() {

        String id = "mock-id";
        boolean completed = true;

        // Mock the todoService.update() method to throw an exception
        when(todoService.update(id, completed)).thenThrow(IllegalArgumentException.class);


        ResponseEntity<Todo> response = todoController.update(id, completed);

        verify(todoService, times(1)).update(id, completed);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void testDelete_Success() {

        // Mock request parameter
        String id = "todo-id";

        // Call the delete method on the todoController
        ResponseEntity<?> response = todoController.delete(id);

        // Verify the todoService.delete() method was called with the correct parameter
        verify(todoService, times(1)).delete(id);

        // Verify the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testDelete_InvalidRequest() {

        String id = "mock";

        // Mock throw an exception
        doThrow(IllegalArgumentException.class).when(todoService).delete(id);

        ResponseEntity<?> response = todoController.delete(id);
        verify(todoService, times(1)).delete(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testList_Success() {

        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("1", "Todo 1", "Description 1", false));
        todos.add(new Todo("2", "Todo 2", "Description 2", true));

        when(todoService.list()).thenReturn(todos);

        // Call the list method on the todoController
        ResponseEntity<List<Todo>> response = todoController.list();

        // Verify the todoService.list() method was called
        verify(todoService, times(1)).list();

        // Verify the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify the response body contains the expected todos
        assertEquals(todos, response.getBody());
    }


    @Test
    public void testList_InvalidRequest() {

        when(todoService.list()).thenThrow(IllegalArgumentException.class);
        ResponseEntity<List<Todo>> response = todoController.list();
        verify(todoService, times(1)).list();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testListCompleted_Success() {

        // Mock the list of completed todos
        List<Todo> completedTodos = new ArrayList<>();
        completedTodos.add(new Todo("1", "Todo 1", "Description 1", true));
        completedTodos.add(new Todo("2", "Todo 2", "Description 2", true));

        when(todoService.listCompleted()).thenReturn(completedTodos);

        ResponseEntity<List<Todo>> response = todoController.listCompleted();
        verify(todoService, times(1)).listCompleted();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(completedTodos, response.getBody());
    }

    @Test
    public void testListCompleted_InvalidRequest() {
        
        when(todoService.listCompleted()).thenThrow(IllegalArgumentException.class);

        ResponseEntity<List<Todo>> response = todoController.listCompleted();

        verify(todoService, times(1)).listCompleted();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}


