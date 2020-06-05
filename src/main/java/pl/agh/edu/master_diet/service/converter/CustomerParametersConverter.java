package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.CustomerParameters;
import pl.agh.edu.master_diet.core.model.rest.caloricDemand.CustomerParametersRequest;

@Component
public final class CustomerParametersConverter implements GenericConverter<CustomerParametersRequest, CustomerParameters> {
    @Override
    public CustomerParameters createFrom(CustomerParametersRequest dto) {
        return Ci.builder()
                .userType(dto.getUserType())
                .additionTime(LocalDateTime.now())
                .emailAddress(dto.getEmailAddress())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .build();

    }
}
