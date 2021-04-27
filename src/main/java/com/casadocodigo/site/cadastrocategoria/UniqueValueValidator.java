package com.casadocodigo.site.cadastrocategoria;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

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

        Query query = manager.createQuery(String.format("SELECT 1 FROM %s WHERE %s =: value", domainClass.getName(), fieldName)).setParameter("value", value);

        List<?> queryResultList = query.getResultList();

        Assert.state(queryResultList.size() <= 1, String.format("#BUG Mais de um %s com o atributo %s = %s", domainClass.getName(), fieldName, value));

        return queryResultList.isEmpty();
    }
}