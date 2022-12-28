package jardineria.jardineriapf.validaciones;

import jardineria.jardineriapf.dto.*;
import jardineria.jardineriapf.repositorios.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailexisteValidator implements ConstraintValidator<Emailexiste, Object> {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void initialize(final Emailexiste constraintAnnotation) {
        //
    }

    @Override //aca va la logica de la validacion 
    public boolean isValid(final Object objeto, final ConstraintValidatorContext context) {
        final RecuperacionDto registro = (RecuperacionDto) objeto;
        boolean esValido = usuarioRepositorio.existsByEmail(registro.getEmail());

        if (esValido) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode( "email" ).addConstraintViolation();
       }

       return esValido;
    }

}
