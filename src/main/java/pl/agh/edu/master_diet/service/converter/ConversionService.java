package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.rest.caloricDemand.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;

public interface ConversionService {

    UserParameters convert(UserParametersRequest request);
}