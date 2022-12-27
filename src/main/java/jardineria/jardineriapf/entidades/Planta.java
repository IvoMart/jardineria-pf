package jardineria.jardineriapf.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "plantas")
public class Planta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre_vulgar;

    private String nombre_cientifico;

    private String descripcion;

    @Nullable
    private String foto;

    @Nullable
    @ElementCollection
    @CollectionTable(name = "propiedades_lista", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "propiedades") 
    private List<String> propiedades;

}