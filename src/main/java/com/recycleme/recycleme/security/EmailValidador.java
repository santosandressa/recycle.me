package com.recycleme.recycleme.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidador implements ConstraintValidator<EmailValido, String> {
    
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$"; 
    @Override
    public void initialize(EmailValido constraintAnnotation) {       
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){   
        return (validateEmail(email));
    } 
    private boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}