package jardineria.jardineriapf.dto;

import jardineria.jardineriapf.Validaciones.*;
import jardineria.jardineriapf.entidades.*;

import javax.validation.constraints.*;
import lombok.*;

@Data
@Emailexiste //validaciones manuales 
public class RecuperacionDto {
    
    @NotNull
    @NotEmpty(message = "Ingrese un mensaje")
    private String mensaje;

    @NotNull
    @NotEmpty(message = "Ingrese una dirección de correo electrónico")
    @Email(message = "Ingrese una dirección de correo electrónico válida")
    private String email;

    private Usuario usuario;

    //private String confirmar;

    //private String recaptcha;
}
