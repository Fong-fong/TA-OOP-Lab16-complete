package OOP_Lab16.TodoList.Repository;

import OOP_Lab16.TodoList.Model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("MyTodoRepository")
public class MyTodoRepository implements TodoRepository {
    private List<TodoItem> todoItems = new ArrayList<>();

    public MyTodoRepository() {
    }

    @Override
    public List<TodoItem> findAll() {
        return todoItems;
    }

    @Override
    public List<TodoItem> findByStatus(Boolean completed) {
        return todoItems.stream()
                        .filter(todoItem -> todoItem.getCompleted().equals(completed))
                        .collect(Collectors.toList());
    }

    @Override
    public TodoItem add(String title) {
        TodoItem todoItem = new TodoItem(title);
        todoItems.add(todoItem);
        return todoItem;

    }

    @Override
    public void deleteById(int id) {
        todoItems.removeIf(todoItem -> todoItem.getId() == id);
    }

    @Override
    public void update(int id, Boolean completed) {
        todoItems.stream()
                 .filter(todoItem -> todoItem.getId() == id)
                 .forEach(todoItem -> todoItem.setCompleted(completed));
    }

    @Override
    public void addList(List<TodoItem> todoList) {
        todoItems.clear();
        todoItems.addAll(todoList);
    }

    @Override
    public List<TodoItem> findByTitle(String title) {
        String titleLowerCase = title.toLowerCase();
        return todoItems.stream()
                        .filter(todoItem -> todoItem.getTitle().toLowerCase().contains(titleLowerCase))
                        .collect(Collectors.toList());
    }


}
