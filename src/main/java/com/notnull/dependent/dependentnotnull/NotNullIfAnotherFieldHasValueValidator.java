package com.notnull.dependent.dependentnotnull;

import java.lang.reflect.Field;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class NotNullIfAnotherFieldHasValueValidator implements ConstraintValidator<EnableDependentNotNull, Object> {

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
				if (field.isAnnotationPresent(NotNullIfAnotherFieldHasValue.class)) {

					dependentFieldName = field.getAnnotation(NotNullIfAnotherFieldHasValue.class).fieldName();
					dependentFieldValues = field.getAnnotation(NotNullIfAnotherFieldHasValue.class).fieldValues();
					message = field.getAnnotation(NotNullIfAnotherFieldHasValue.class).message();

					String actualDependentFieldValue = BeanUtils.getProperty(o, dependentFieldName);
					String fieldValue = BeanUtils.getProperty(o, field.getName());

					if (message.isEmpty())
						message = " " + field.getName() + " IS REQUIRED ";

					// se dependentField assume un valore di dependentFieldValues, allora field
					// deve essere notnull
					if ((Arrays.asList(dependentFieldValues).contains(actualDependentFieldValue)) && fieldValue == null)
						result = false;

					if (!result) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(message).addPropertyNode(field.getName())
								.addConstraintViolation();
						break;
					}
				}
			}
		} catch (final Exception e) {
			// logger.info("error");
		}
		return result;
	}
}