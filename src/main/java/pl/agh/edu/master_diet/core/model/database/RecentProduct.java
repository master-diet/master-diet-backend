package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleRecentProductInfo;
import pl.agh.edu.master_diet.core.model.shared.MealType;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "recent_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime mealTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Float portion;

    @Column(nullable = false)
    private Unit portionUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public SingleRecentProductInfo createSingleResponseForProduct() {
        if (!Objects.equals(product.getUnit(), getPortionUnit())) {
            throw new RuntimeException("Unit of product and recent product is not the same");
            // TODO to be handled in the future
        }

        final Float weightEaten = amount * portion;
        final Float coefficient = weightEaten / product.getDefaultValue();

        return SingleRecentProductInfo.builder()
                .mealUnit(portionUnit)
                .fatEaten(coefficient * product.getFat())
                .caloriesEaten(coefficient * product.getCalories())
                .proteinsEaten(coefficient * product.getProteins())
                .carbohydratesEaten(coefficient * product.getCarbohydrates())
                .mealTime(mealTime)
                .recentProductId(id)
                .portion(portion)
                .amount(amount)
                .mealType(mealType)
                .productName(product.getName())
                .build();
    }
}
