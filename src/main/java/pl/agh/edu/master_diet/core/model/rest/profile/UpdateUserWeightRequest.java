package pl.agh.edu.master_diet.core.model.rest.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserWeightRequest {

    @NonNull
    private Double weight;
}
