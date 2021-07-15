package com.casadocodigo.site.compartilhado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> domainClass;
    private String fieldName;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        domainClass = constraintAnnotation.domainClass();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        TypedQuery<Boolean> query = manager.createQuery(String.format("SELECT count(1) > 0 FROM %s WHERE %s =: value", domainClass.getName(), fieldName), Boolean.class)
                                  .setParameter("value", value);

        Boolean valueAlreadyExists = query.getSingleResult();
        return !valueAlreadyExists;
    }
}