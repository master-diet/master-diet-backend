package pl.agh.edu.master_diet.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private final String tokenType = "Bearer";

    private String accessToken;
}
