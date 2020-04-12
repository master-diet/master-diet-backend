package pl.agh.edu.master_diet.core.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PingResponse {

    private String message;
    private ZonedDateTime pingTime;
}
