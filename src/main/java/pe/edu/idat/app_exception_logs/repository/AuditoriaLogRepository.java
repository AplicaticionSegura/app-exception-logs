package pe.edu.idat.app_exception_logs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.app_exception_logs.model.AuditoriaLog;

public interface AuditoriaLogRepository extends
        JpaRepository<AuditoriaLog, Long> {
}
