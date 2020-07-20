package pl.agh.edu.master_diet.core.model.database;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double mets;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

}
