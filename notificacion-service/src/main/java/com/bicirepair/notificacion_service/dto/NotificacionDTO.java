package com.bicirepair.notificacion_service.dto;

import com.bicirepair.notificacion_service.model.Notificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionDTO {

    private Long idNotificacion;
    private Long idReparacion;
    private String mensaje;
    private String canal;
    private boolean enviada;
    private Date fechaEnvio;

    public Notificacion toModel() {
        return new Notificacion(this.idNotificacion, this.idReparacion, this.mensaje, this.canal, this.enviada, this.fechaEnvio);
    }

    public static NotificacionDTO fromModel(Notificacion n) {
        if (n == null) return null;
        return new NotificacionDTO(n.getIdNotificacion(), n.getIdReparacion(), n.getMensaje(),
                                   n.getCanal(), n.isEnviada(), n.getFechaEnvio());
    }
}