package pl.agh.edu.master_diet.core.model.rest.browser.activity;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseActivityInfo {

    private Long id;
    private String name;
    private Integer burnedCalories;
    private Double mets;
}
