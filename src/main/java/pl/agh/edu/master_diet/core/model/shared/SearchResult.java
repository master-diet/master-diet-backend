package pl.agh.edu.master_diet.core.model.shared;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchResult<T> {
    private List<T> result;
    private Integer maximumPage;
}
