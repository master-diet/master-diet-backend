package pl.agh.edu.master_diet.service.converter;

public interface GenericConverter<From, To> {

    To createFrom(From dto);
}
