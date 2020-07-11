package pl.agh.edu.master_diet.exception;

public class UnitSymbolNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Cannot found unit enum for symbol: ";

    public UnitSymbolNotFoundException(String unitSymbol) {
        super(EXCEPTION_MESSAGE + unitSymbol);
    }
}
