package pl.agh.edu.master_diet.core.model.rest.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserWeightRequest {

    @NotNull
    private Double weight;
}
