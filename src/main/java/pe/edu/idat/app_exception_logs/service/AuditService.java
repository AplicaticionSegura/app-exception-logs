package pe.edu.idat.app_exception_logs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.app_exception_logs.model.AuditoriaLog;
import pe.edu.idat.app_exception_logs.repository.AuditoriaLogRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuditService {
    private final AuditoriaLogRepository auditoriaLogRepository;

    public void registrar(String usuario,
                          String accion,
                          String detalle,
                          String nivel,
                          String ipOrigen){
        AuditoriaLog auditoriaLog = new AuditoriaLog();
        auditoriaLog.setFecha(LocalDateTime.now());
        auditoriaLog.setUsuario(usuario);
        auditoriaLog.setAccion(accion);
        auditoriaLog.setDetalle(detalle);
        auditoriaLog.setNivel(nivel);
        auditoriaLog.setIporigen(ipOrigen);
        auditoriaLogRepository.save(auditoriaLog);
    }

}
