package pe.edu.idat.app_exception_logs.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.edu.idat.app_exception_logs.exception.ClienteNoEncontradoException;
import pe.edu.idat.app_exception_logs.exception.SaldoInsuficienteException;
import pe.edu.idat.app_exception_logs.model.Cliente;
import pe.edu.idat.app_exception_logs.repository.ClienteRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class RetiroService {

    private final ClienteRepository clienteRepository;
    private final AuditService auditService;

    private static final Logger log = LoggerFactory.getLogger(RetiroService.class);

    public void realizarRetiro(Long clienteId,
                               BigDecimal monto,
                               String usuario,
                               String ipOrigen){
        log.info("Inicio de retiro. cliente={}, monto={}, usuario={}",
                clienteId, monto, usuario);
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> {
                    log.warn("Cliente no encontrado. Cliente={}", clienteId);
                    auditService.registrar(usuario, "CLIENTE_NO_ENCONTRADO",
                            "No existe el cliente con ID="+clienteId,
                            "WARN", ipOrigen);
                    return new ClienteNoEncontradoException("El cliente no existe");
                });
        if(cliente.getSaldo().compareTo(monto) < 0){
            log.warn("Saldo insuficiente. ClienteId={}, saldoActual={}, montoSolicitado={}",
                    clienteId, cliente.getSaldo(), monto);
            auditService.registrar(usuario, "RETIRO_RECHAZADO_SALDO",
                    "Saldo actual: "+cliente.getSaldo()+", monto solicitado="+monto,
                    "WARN", ipOrigen);
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro");
        }
        cliente.setSaldo(cliente.getSaldo().subtract(monto));
        clienteRepository.save(cliente);
        log.info("Retiro exitoso. ClienteId={}, nuevoSaldo={}", clienteId, cliente.getSaldo());
        auditService.registrar(usuario, "RETIRO_EXITOSO",
                "Nuevo saldo del cliente: "+clienteId+": "+cliente.getSaldo(),
                "INFO", ipOrigen);
    }


}
