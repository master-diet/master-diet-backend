package pl.agh.edu.master_diet.core.model.database;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity extends Browsable {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double mets;

    @Column(nullable = false)
    private String category;

}
