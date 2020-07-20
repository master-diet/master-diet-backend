package pl.agh.edu.master_diet.core.model.rest.browser.activity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ActivitySearchResponse {

    private List<BaseActivityInfo> activities;
    private Integer maximumPageNumber;
}
