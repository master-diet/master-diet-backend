package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBmiRequest;
import pl.agh.edu.master_diet.core.model.rest.calculator.bmi.CalculateBmiResponse;
import pl.agh.edu.master_diet.core.model.shared.BmiParameters;
import pl.agh.edu.master_diet.service.CalculatorService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculator")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final ConversionService conversionService;

    @PostMapping("/bmi")
    public ResponseEntity<CalculateBmiResponse> calculateBmi(@Valid @RequestBody final CalculateBmiRequest request) {
        final BmiParameters bmiParameters = conversionService.convert(request);
        return ResponseEntity.ok()
                .body(calculatorService.calculateBmi(bmiParameters));
    }
}
