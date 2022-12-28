package jardineria.jardineriapf.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @ManyToOne
    @JsonBackReference
    @NotNull
    private Usuario usuarioId;

    @ManyToMany
    @Nullable
    private List<Planta> plantas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaModificacion;

    @Nullable
    private String foto;

}