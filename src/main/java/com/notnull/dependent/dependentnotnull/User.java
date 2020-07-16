package com.notnull.dependent.dependentnotnull;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

@EnableDependentNotNull
public class User {

	@NotNull
	private long userId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private boolean isPremium;

	@NotNullDependentValue(fieldName = "isPremium", fieldValues = { "true" })
	private BigDecimal amount;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
