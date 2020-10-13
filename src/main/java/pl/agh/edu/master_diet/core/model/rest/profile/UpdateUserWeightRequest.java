package pl.agh.edu.master_diet.core.model.rest.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserWeightRequest {

    private Double weight;
}
