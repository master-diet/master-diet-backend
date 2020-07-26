package pl.agh.edu.master_diet.core.model.shared;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult<T> {
    private List<T> result;
    private Integer maximumPage;
}
