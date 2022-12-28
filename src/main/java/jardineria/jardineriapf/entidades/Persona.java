package jardineria.jardineriapf.entidades;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Component
@MappedSuperclass
@Data
@SuperBuilder
public abstract class Persona {

    @NotBlank(message = "Campo obligatorio")
    @Size(max = 250, message = "Nombre demasiado largo")
    protected String nombre;

    @NotBlank(message = "Campo obligatorio")
    @Size(max = 250, message = "Nombre demasiado largo")
    protected String apellido;

    @NotBlank(message = "Campo obligatorio")
    protected String tipo_Documento;

    @NotBlank(message = "Campo obligatorio")
    protected int documento_identidad;

    protected String email_alternativo;

    protected int telefono;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Campo obligatorio")
    @Past(message = "La fecha de nacimiento no puede ser futura")
    protected Date fechaNacimiento;

    @NotNull(message = "Campo obligatorio")
    protected String domicilio;

    @NotNull(message = "Campo obligatorio")
    protected String domicilio_2;

    protected Persona() {
    }

    protected Persona(String nombre, String apellido, String tipo_Documento, int documento_identidad, String email_alternativo, int telefono, Date fechaNacimiento, String domicilio, String domicilio_2) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_Documento = tipo_Documento;
        this.documento_identidad = documento_identidad;
        this.email_alternativo = email_alternativo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.domicilio_2 = domicilio_2;
    }
    
    

}
