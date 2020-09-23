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

    private boolean success;
    private String message;
}
