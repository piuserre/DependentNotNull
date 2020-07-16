package com.notnull.dependent.dependentnotnull;

import java.lang.reflect.Field;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class NotNullDependentValueValidator implements ConstraintValidator<EnableDependentNotNull, Object> {

	@Override
	public void initialize(final EnableDependentNotNull constraint) {
		// empty
	}

	@Override
	public boolean isValid(final Object o, final ConstraintValidatorContext context) {
		boolean result = true;
		try {
			String dependentFieldName, message;
			String dependentFieldValues[];

			final Class<?> clazz = o.getClass();
			final Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				if (field.isAnnotationPresent(NotNullDependentValue.class)) {

					// retrieve annotation parameters
					dependentFieldName = field.getAnnotation(NotNullDependentValue.class).fieldName();
					dependentFieldValues = field.getAnnotation(NotNullDependentValue.class).fieldValues();
					String actualDependentFieldValue = BeanUtils.getProperty(o, dependentFieldName);
					Object fieldValue = BeanUtils.getProperty(o, field.getName());

					message = field.getAnnotation(NotNullDependentValue.class).message();
					if (message.isEmpty())
						message = " " + field.getName() + " IS REQUIRED ";

					if ((Arrays.asList(dependentFieldValues).contains(actualDependentFieldValue))
							&& fieldValue == null) {
						if (result)
							result=false;

						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(message).addPropertyNode(field.getName())
								.addConstraintViolation();
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}