package pl.agh.edu.master_diet.core.model.rest.browser.activity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentActivityResponse {

    @NonNull
    private List<BaseActivityInfo> activities;

    @NonNull
    private Integer maximumPageNumber;
}
