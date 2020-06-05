package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.caloricDemand.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.service.CaloricDemandService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/caloricDemand")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CaloricDemandController {

    private final ConversionService conversionService;
    private final CaloricDemandService caloricDemandService;

    @PostMapping
    public int calculateAndGetCaloricDemand(@Valid @RequestBody UserParametersRequest request){
        UserParameters userParameters = conversionService.convert(request);
        return caloricDemandService.calculateAndSaveUsersCaloricDemand(userParameters);
    }

}
