package OOP_Lab16.TodoList.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UploadTodolistRequest {
    @NonNull
    @JsonProperty("todoList")
    private List<OOP_Lab16.TodoList.Model.TodoItem> todoList;

}
