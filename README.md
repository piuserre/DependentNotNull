# DependentNotNull
Demonstrate a simple @NotNull extension for Dto validation

Example:

  
```
@NotNull	
private boolean isPremium;

@NotNullIfAnotherFieldHasValue(fieldName = "isPremium", fieldValues = { "true" })
private BigDecimal amount;
```

