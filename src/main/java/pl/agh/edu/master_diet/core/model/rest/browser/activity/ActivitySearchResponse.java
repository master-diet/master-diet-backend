package pl.agh.edu.master_diet.core.model.rest.browser.activity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySearchResponse {

    private List<BaseActivityInfo> activities;
    private Integer maximumPageNumber;
}
