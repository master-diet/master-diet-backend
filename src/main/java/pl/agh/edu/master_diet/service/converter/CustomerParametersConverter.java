package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.CustomerParameters;
import pl.agh.edu.master_diet.core.model.rest.caloricDemand.CustomerParametersRequest;

@Component
public class CustomerParametersConverter implements GenericConverter<CustomerParametersRequest, CustomerParameters> {
}
