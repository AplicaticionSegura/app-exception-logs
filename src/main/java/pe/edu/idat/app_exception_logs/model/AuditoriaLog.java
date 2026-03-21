package pe.edu.idat.app_exception_logs.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auditoria_log")
public class AuditoriaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @Column(length = 100)
    private String usuario;
    @Column(length = 100, nullable = false)
    private String accion;
    @Column(length = 500)
    private String detalle;
    @Column(length = 20, nullable = false)
    private String nivel;
    @Column(length = 50)
    private String iporigen;
}
