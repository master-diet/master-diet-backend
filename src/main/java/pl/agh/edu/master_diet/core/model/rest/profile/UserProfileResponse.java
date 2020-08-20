package pl.agh.edu.master_diet.core.model.rest.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.agh.edu.master_diet.core.model.database.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

    @NonNull
    private User user;

    @NonNull
    private Double weight;
}
