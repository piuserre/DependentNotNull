# DependentNotNull
Demonstrate a simple @NotNull extension for pojo validation

* @NotNullIfAnotherFieldHasValue on the interested field
* @EnableDependentNotNull on the class

Example:
  
```
@NotNull	
private boolean isPremium;

@NotNullIfAnotherFieldHasValue(fieldName = "isPremium", fieldValues = { "true" })
private BigDecimal amount;
```

Validation fails when amount is null and isPremium assume one of the values from fieldValues list

Another example:
```
@NotNull	
private String nationality;

@NotNullIfAnotherFieldHasValue(fieldName = "nationality", fieldValues = { "Italian", "USA", "French" })
private String favouriteDish;
```
