package pl.agh.edu.master_diet.util;

import pl.agh.edu.master_diet.core.model.shared.Unit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UnitConverter implements AttributeConverter<Unit, String> {

    @Override
    public String convertToDatabaseColumn(Unit unit) {
        return unit.getSymbol();
    }

    @Override
    public Unit convertToEntityAttribute(String unitSymbol) {
        return Unit.fromSymbol(unitSymbol);
    }
}
