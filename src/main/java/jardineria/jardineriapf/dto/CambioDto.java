package jardineria.jardineriapf.dto;

//import jardineria.jardineriapf.validaciones.*;
    
import javax.validation.constraints.*;
import lombok.*;

@Data
public class CambioDto {
        @NotNull
        @NotEmpty(message = "Ingrese una contraseña")
        private String password;
}
