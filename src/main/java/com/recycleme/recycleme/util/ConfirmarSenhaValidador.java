package com.recycleme.recycleme.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.recycleme.recycleme.model.Usuario;

public class ConfirmarSenhaValidador implements ConstraintValidator<ConfirmarSenha, Object> { 
    
    @Override
    public void initialize(ConfirmarSenha constraintAnnotation) {       
    }
    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        Usuario usuario = (Usuario) obj;
        return usuario.getSenha().equals(usuario.getConfirmarSenha());    
    }     
}