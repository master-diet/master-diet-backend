package pl.agh.edu.master_diet.core.model.database;

import lombok.*;
import pl.agh.edu.master_diet.core.model.shared.MealType;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
