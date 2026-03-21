package pe.edu.idat.app_exception_logs.exception;

public class SaldoInsuficienteException extends
    RuntimeException{

    public SaldoInsuficienteException(String message){
        super(message);
    }
}
