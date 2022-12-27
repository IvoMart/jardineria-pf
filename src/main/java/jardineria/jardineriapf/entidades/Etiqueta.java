package jardineria.jardineriapf.entidades;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "etiqueta")
public class Etiqueta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private int etiqueta;

    @Nullable
    private Long usuario;

}