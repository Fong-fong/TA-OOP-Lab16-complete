package OOP_Lab16.TodoList.Repository;

import OOP_Lab16.TodoList.Model.TodoItem;

import java.util.List;

public interface TodoRepository {

    List<TodoItem> findAll();

    List<TodoItem> findByStatus(Boolean completed);

    TodoItem add(String title);

    void deleteById(int id);

    void update(int id, Boolean completed);

    void addList(List<TodoItem> todoList);

    List<TodoItem> findByTitle(String title);



}
