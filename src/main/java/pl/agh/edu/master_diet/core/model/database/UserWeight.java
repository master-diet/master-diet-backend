package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_weight")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float weight;

    @Column(name = "creation_date",updatable = false)
    @CreationTimestamp
    private Date creation_date;

}
