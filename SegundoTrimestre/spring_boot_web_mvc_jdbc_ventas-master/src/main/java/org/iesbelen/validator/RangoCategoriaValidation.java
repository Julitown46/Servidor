package org.iesbelen.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RangoCategoria.class})
public @interface RangoCategoriaValidation {
    public String message() default "La categoría debe ser 100,200,300,400,500,600,700,800 o 1000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
