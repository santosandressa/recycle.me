package com.recycleme.recycleme.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmarSenhaValidador.class)
public @interface ConfirmarSenha {
	String message() default "Senha diferente";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
