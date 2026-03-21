package pe.edu.idat.app_exception_logs.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.edu.idat.app_exception_logs.service.AuditService;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final AuditService auditService;

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public String manejarClienteNoEncontrado(ClienteNoEncontradoException ex,
                                             Model model,
                                             HttpServletRequest request){
        log.warn("Excepción controlada: {}", ex.getMessage());
        auditService.registrar("operador1",
                "EXCEPCION_CLIENTE_NO_ENCONTRADO",
                ex.getMessage(),
                "WARN",
                request.getRemoteAddr());
        model.addAttribute("error", ex.getMessage());
        return "error-negocio";
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public String manejarSaldoInsuficiente(SaldoInsuficienteException ex,
                                             Model model,
                                             HttpServletRequest request){
        log.warn("Excepción controlada: {}", ex.getMessage());
        auditService.registrar("operador1",
                "EXCEPCION_SALDO_INSUFICIENTE",
                ex.getMessage(),
                "WARN",
                request.getRemoteAddr());
        model.addAttribute("error", ex.getMessage());
        return "error-negocio";
    }

    @ExceptionHandler(Exception.class)
    public String manejarErrorGeneral(Exception ex,
                                           Model model,
                                           HttpServletRequest request){
        log.error("Error inesperado en la aplicación", ex);
        auditService.registrar("operador1",
                "ERROR_GENERAL",
                ex.getMessage(),
                "ERROR",
                request.getRemoteAddr());
        model.addAttribute("error", "Ocurrió un error inesperado. Contactar con el admin.");
        return "error-negocio";
    }

}
