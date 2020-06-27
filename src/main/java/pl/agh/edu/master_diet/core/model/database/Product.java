package pl.agh.edu.master_diet.core.model.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[\\d]+")
    private String barcode;

    @Column(nullable = false)
    private Float defaultValue;

    @Column(nullable = false)
    private String name;

    private String notes;

    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Float calories;

    @Column(nullable = false)
    private Float proteins;

    @Column(nullable = false)
    private Float fat;

    @Column(nullable = false)
    private Float carbohydrates;

    @Column(nullable = false)
    private Integer approvals;
}
