package pl.agh.edu.master_diet.core.model.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.agh.edu.master_diet.core.model.shared.MealType;
import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
