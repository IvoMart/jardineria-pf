package jardineria.jardineriapf.Validaciones;

import jardineria.jardineriapf.dto.*;
import jardineria.jardineriapf.repositorios.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, Object> {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void initialize(final EmailUnico constraintAnnotation) {
        //
    }

    @Override //aca va la logica de la validacion 
    public boolean isValid(final Object objeto, final ConstraintValidatorContext context) {
        final RegistroDto registro = (RegistroDto) objeto;
        boolean esValido = ! usuarioRepositorio.existsByEmail(registro.getEmail());

        if (! esValido) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode( "email" ).addConstraintViolation();
       }

       return esValido;
    }

}
