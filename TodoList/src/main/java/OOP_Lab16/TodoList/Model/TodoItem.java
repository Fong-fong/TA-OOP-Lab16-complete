package OOP_Lab16.TodoList.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoItem {
    private  static  int counter=0;
    private  static  int nextCounter(){
        return counter++;
    }

    private int id =nextCounter();

    private final String title;

    private Boolean completed = false;


    public TodoItem(String title) {

        this.title = title;
    }
}
