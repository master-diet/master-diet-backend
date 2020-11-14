package pl.agh.edu.master_diet.core.model.rest.user_plan;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWeightResponse {

    @NonNull
    private Double weight;

    @NonNull
    private LocalDateTime creationDate;
}
