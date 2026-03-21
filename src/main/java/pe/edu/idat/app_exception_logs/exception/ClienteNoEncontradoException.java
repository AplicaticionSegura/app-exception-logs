package pe.edu.idat.app_exception_logs.exception;

public class ClienteNoEncontradoException
    extends RuntimeException{

    public ClienteNoEncontradoException(String message){
        super(message);
    }

}
