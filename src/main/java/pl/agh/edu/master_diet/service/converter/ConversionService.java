package pl.agh.edu.master_diet.service.converter;

import pl.agh.edu.master_diet.core.model.rest.caloricDemand.CustomerParametersRequest;
import pl.agh.edu.master_diet.core.model.CustomerParameters;

public interface ConversionService {

    CustomerParameters convert(CustomerParametersRequest request);
}
