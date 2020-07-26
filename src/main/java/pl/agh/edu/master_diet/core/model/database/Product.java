package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "product",
        indexes = {
                @Index(columnList = "name", name = "name_index")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Browsed {

    @Pattern(regexp = "[\\d]+")
    private String barcode;

    @Column(nullable = false)
    private Float defaultValue;

    private String notes;

    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Long calories;

    @Column(nullable = false)
    private Float proteins;

    @Column(nullable = false)
    private Float fat;

    @Column(nullable = false)
    private Float carbohydrates;

}
