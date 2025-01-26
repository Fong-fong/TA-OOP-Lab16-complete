package OOP_Lab16.TodoList.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateTodoRequest {
    @NonNull
    @JsonProperty("title")
    @NotBlank(message = "Title cannot be blank")
    private String title;

}
