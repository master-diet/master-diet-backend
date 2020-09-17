package pl.agh.edu.master_diet.core.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private final String tokenType = "Bearer";
    private String accessToken;
}
