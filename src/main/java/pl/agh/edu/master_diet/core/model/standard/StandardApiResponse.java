package pl.agh.edu.master_diet.core.model.standard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardApiResponse {

    public StandardApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private boolean success;
    private String message;
    private ErrorResponse error;
}
