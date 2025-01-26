package OOP_Lab16.TodoList.Controller;


import OOP_Lab16.TodoList.Model.TodoItem;
import OOP_Lab16.TodoList.Repository.TodoRepository;
import OOP_Lab16.TodoList.dto.request.CreateTodoRequest;
import OOP_Lab16.TodoList.dto.request.UpdateTodoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class MyTodoController {
    private final TodoRepository todoRepository;

    @Autowired
    public MyTodoController(@Qualifier("MyTodoRepository") TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }



    @Operation(summary = "Show all todo items with optional filter by status",
    description = "If status is not provided, all todo items will be shown otherwise only todo items with the provided status will be shown")
    @GetMapping("/")
    public List<TodoItem> ShowAll( @RequestParam(required = false,name = "status") Boolean status){
        if(status != null){
            return this.todoRepository.findByStatus(status);
        }
        return this.todoRepository.findAll();
    }

    @Operation(summary = "Add a new todo item",
    description = "Add a new todo item with the provided title")
    @PostMapping("/")
    public TodoItem Add(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Title of the todo item",
            required = true,
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CreateTodoRequest.class),
            examples = @ExampleObject(value = "{\"title\": \"Buy milk\"}")
    )) @RequestBody CreateTodoRequest createTodoRequest){
       return this.todoRepository.add(createTodoRequest.getTitle());
    }

    @Operation(summary = "Delete a todo item by ID",
    description = "Delete a todo item with the provided ID")
    @DeleteMapping("/{id}")
    public void Delete(@Parameter(description = "ID of the todo item")@PathVariable(name = "id") int id){
        this.todoRepository.deleteById(id);
    }

    @Operation(summary = "Search todo items by title",
    description = "Search todo items with the provided title")
    @GetMapping("/search/")
    public List<TodoItem> SearchByTitle(@Parameter(description = "Title of the todo item")
                                            @RequestParam String title){
        return this.todoRepository.findByTitle(title);
    }

    @Operation(summary = "Update the status of a todo item",
    description = "Update the status of a todo item with the provided ID")
    @PutMapping("/{id}")
    public void Update(@Parameter(description = "ID of the todo item")@PathVariable(name = "id") int id,
                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                               description = "Completed of the todo item",
                               required = true,
                               content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = UpdateTodoRequest.class),
                                       examples = @ExampleObject(value = "{\n" +
                                               "  \"completed\": true\n" +
                                               "}")
                               ))
                       @RequestBody UpdateTodoRequest updateTodoRequest){
        this.todoRepository.update(id,updateTodoRequest.getCompleted());
    }


    @Operation(summary = "Upload a list of todo items",
    description = "Upload a list of todo items")
    @PostMapping("/upload/")
    public void AddList(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "List of the todo item",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TodoItem.class)),
                    examples = @ExampleObject(
                            name = "TodoItemListExample",
                            value = "[{\"id\":10, \"title\": \"Task 1\", \"completed\": false}, {\"id\":11,\"title\": \"Task 2\", \"completed\": true}]"
                    )
            ))
            @RequestBody List<TodoItem> todoList){
        this.todoRepository.addList(todoList);
    }
}
