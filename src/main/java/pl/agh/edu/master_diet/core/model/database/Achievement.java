package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "achievement")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer points;

    @Column(nullable = false)
    private Integer completeCondition;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photo;
}
