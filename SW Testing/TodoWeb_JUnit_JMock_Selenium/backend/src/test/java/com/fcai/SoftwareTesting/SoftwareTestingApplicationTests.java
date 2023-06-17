package com.fcai.SoftwareTesting;

import com.fcai.SoftwareTesting.todo.Todo;
import com.fcai.SoftwareTesting.todo.TodoController;
import com.fcai.SoftwareTesting.todo.TodoCreateRequest;
import com.fcai.SoftwareTesting.todo.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class SoftwareTestingApplicationTests {
	TodoServiceImpl todoService = new TodoServiceImpl();


	///////// TodoServiceImpl Methods Unit tests /////////
	@Test
	public void testGetterAndSetter() {
		Todo todo = new Todo();

		// Use setter methods to set values
		todo.setId("1");
		todo.setTitle("Test Todo");
		todo.setDescription("Test Description");
		todo.setCompleted(true);

		// Use getter methods to get the values and assert their correctness
		Assertions.assertEquals("1", todo.getId());
		Assertions.assertEquals("Test Todo", todo.getTitle());
		Assertions.assertEquals("Test Description", todo.getDescription());
		Assertions.assertTrue(todo.isCompleted());
	}

	@Test
	public void testTodoConstructor() {
		// Create an object using the constructor
		Todo todo = new Todo("1", "Test Todo", "Test Description", true);


		// Use getter methods to get the values and assert their correctness
		Assertions.assertEquals("1", todo.getId());
		Assertions.assertEquals("Test Todo", todo.getTitle());
		Assertions.assertEquals("Test Description", todo.getDescription());
		Assertions.assertTrue(todo.isCompleted());

		//TodoCreateRequest Constructor
		TodoCreateRequest todo_request = new TodoCreateRequest("testReq", "testReqDescription");


		Assertions.assertEquals("testReq", todo_request.getTitle());
		Assertions.assertEquals("testReqDescription", todo_request.getDescription());
	}

	@Test
	public void testCreateNull()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Todo todo = todoService.create(null);
		});
	}

	@Test
	public void testCreateEmptyDescription()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Todo todo = todoService.create(new TodoCreateRequest("title",""));
		});

	}

	@Test
	public void testCreateEmptyTitle()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Todo todo = todoService.create(new TodoCreateRequest("","description"));
		});

	}

	@Test
	public void testCreate()
	{
		Todo todo = todoService.create(new TodoCreateRequest("title","description"));

		Assertions.assertFalse(todo.isCompleted());
		Assertions.assertEquals(todo.getId(), "1");
		Assertions.assertEquals(todo.getTitle(), "title");
		Assertions.assertEquals(todo.getDescription(), "description");

	}

	@Test
	public void testRead()
	{
		if(todoService.list().isEmpty())
		{
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				Todo todo = todoService.read("1");
			});
		}
		else
		{
			Todo todo = todoService.read("1");
			Assertions.assertEquals(todo.getId(), "1");
		}

	}

	@Test
	public void testRead2()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.read(null);
		});
	}

	@Test
	public void testRead3()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.read("");
		});
	}

	@Test
	public void testRead4()
	{
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("title", "desc");
		todoService.create(todoCreateRequest);
		Todo todo = todoService.read("1");

		Assertions.assertEquals("1", todo.getId());

	}

	@Test
	public void testRead5()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.read("1");
		});
	}

	@Test
	public void testRead6()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.create(new TodoCreateRequest("title","description"));
			todoService.create(new TodoCreateRequest("title2","description2"));
			todoService.read("3");
		});
	}

	@Test
	public void testRead7()
	{
		todoService.create(new TodoCreateRequest("title","description"));
		todoService.create(new TodoCreateRequest("title2","description2"));
		Todo todo = todoService.read("1");
		Assertions.assertEquals("1", todo.getId());
	}

	@Test
	public void testRead8()
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.create(new TodoCreateRequest("title","description"));
			todoService.read("2");
		});
	}

	@Test
	public void testUpdate()
	{
		//create task with id 1
		todoService.create(new TodoCreateRequest("title", "Desc"));
		Todo todo = todoService.read("1");

		todoService.update("1", true);

		Assertions.assertTrue(todo.isCompleted());
	}


	@Test
	public void testDelete()
	{
		//create tasks
		todoService.create(new TodoCreateRequest("title1", "Desc1"));
		todoService.create(new TodoCreateRequest("title2", "Desc2"));
		todoService.create(new TodoCreateRequest("title3", "Desc3"));

		todoService.delete("2");

		//expect this task to be removed
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.read("2");
		});



	}


	@Test
	public void testList()
	{
		todoService.setTodos(null);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.list();
		});
	}

	@Test
	public void testList1()
	{
		todoService.create(new TodoCreateRequest("title1", "desc1"));
		todoService.create(new TodoCreateRequest("title2", "desc2"));

		List<Todo> todos = todoService.list();

		Assertions.assertEquals(2, todos.size());
	}

	@Test
	public void testListCompleted()
	{

		todoService.setTodos(null);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			todoService.listCompleted();
		});

	}

	@Test
	public void testListCompleted1()
	{
		Assertions.assertTrue(todoService.listCompleted().isEmpty());
	}

	@Test
	public void testListCompleted2()
	{
		todoService.create(new TodoCreateRequest("title1","desc1"));
		todoService.update("1",true);

		Assertions.assertEquals(1, todoService.listCompleted().size() );
	}

	@Test
	public void testListCompleted3()
	{
		todoService.create(new TodoCreateRequest("title1","desc1"));
		todoService.create(new TodoCreateRequest("title2","desc2"));

		Assertions.assertTrue(todoService.listCompleted().isEmpty());
	}

	@Test
	public void testListCompleted4()
	{
		todoService.create(new TodoCreateRequest("title1","desc1"));
		todoService.create(new TodoCreateRequest("title2","desc2"));

		todoService.update("2",true);

		Assertions.assertEquals(1,todoService.listCompleted().size());
	}


}
