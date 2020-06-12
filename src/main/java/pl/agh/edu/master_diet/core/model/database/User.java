package pl.agh.edu.master_diet.core.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.agh.edu.master_diet.core.model.common.AuthProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "firebase_token")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String firstName;

    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    private LocalDateTime lastLoginDate;

    private Integer height;

    private LocalDate birthDate;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPlan userPlan;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserGamificationDetail userGamificationDetail;

    private String providerId;

    @Column(name = "firebase_token")
    private String firebaseToken;
}
