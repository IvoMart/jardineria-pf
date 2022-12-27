package jardineria.jardineriapf.entidades;

import com.fasterxml.jackson.annotation.*;

import jardineria.jardineriapf.dto.RegistroDto;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor //me evita escribir constructores
@AllArgsConstructor
@Data
@Builder //realiza los getters y los setters
@Table(name="recuperaciones")
public class Recuperacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TIPO DE GENERACION - AUTOINCREMENTAL 
    private Long id;

    private String mensaje;

    @OneToOne
    @JsonBackReference
    @NotNull
    private Usuario usuario;

}
