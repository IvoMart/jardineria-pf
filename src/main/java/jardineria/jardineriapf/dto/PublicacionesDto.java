package jardineria.jardineriapf.dto;

import jardineria.jardineriapf.entidades.*;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.*;

@Data
public class PublicacionesDto {

    private String contenido;

    private String _usuario;

    private List<Planta> plantas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;
    
    @Nullable
    private String foto;
    
}
