package OOP_Lab16.TodoList.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoRequest {
    @NonNull
    @JsonProperty("completed")
    private Boolean completed;
}
