package pl.agh.edu.master_diet.core.model.rest.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.agh.edu.master_diet.core.model.database.User;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

    @NotNull
    private User user;

    @NotNull
    private Double weight;
}
