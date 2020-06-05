package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer_parameters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float weight;

    @Column(name = "creation_date",updatable = false)
    @CreationTimestamp
    private Date creation_date;

}
