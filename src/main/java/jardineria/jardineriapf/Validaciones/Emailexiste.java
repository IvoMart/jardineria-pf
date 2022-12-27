package jardineria.jardineriapf.Validaciones;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;
import javax.validation.*;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailexisteValidator.class)
@Documented
public @interface Emailexiste {
    String message() default "No Existe un Usuario Con Este Email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}