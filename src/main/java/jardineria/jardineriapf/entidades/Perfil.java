package jardineria.jardineriapf.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Builder
@Table(name = "perfil")
public class Perfil extends Persona implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull(message = "Campo obligatorio")
    private Usuario userId;

    public Perfil(String nombre, String apellido, String tipo_Documento, int documento_identidad, String email_alternativo, int telefono, Date fechaNacimiento, String domicilio, String domicilio_2) {
        super(nombre, apellido, tipo_Documento, documento_identidad, email_alternativo, telefono, fechaNacimiento, domicilio, domicilio_2);
    }

    public Perfil(PersonaBuilder<?, ?> b) {
        super(b);
    }

    public Perfil(Long id, Usuario userId, String nombre, String apellido, String tipo_Documento, int documento_identidad, String email_alternativo, int telefono, Date fechaNacimiento, String domicilio, String domicilio_2) {
        super(nombre, apellido, tipo_Documento, documento_identidad, email_alternativo, telefono, fechaNacimiento, domicilio, domicilio_2);
        this.id = id;
        this.userId = userId;
    }

    public Perfil(Long id, Usuario userId, PersonaBuilder<?, ?> b) {
        super(b);
        this.id = id;
        this.userId = userId;
    }

}