package org.iesbelen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class RangoCategoria implements ConstraintValidator<RangoCategoriaValidation, Integer>{
    @Override
    public boolean isValid(Integer categoria, ConstraintValidatorContext C) {
        List<Integer> categoriaValidas = List.of(100, 200, 300, 400, 500, 600, 700, 800, 1000);

        return categoria != null && categoriaValidas.contains(categoria);
    }
}
