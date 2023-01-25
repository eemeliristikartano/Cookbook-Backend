package com.cookbook.cookbookdatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long ingredientId;
	
	@Column(nullable = false)
	private String ingredientName;
	
	@ManyToOne
	@JoinColumn(name = "recipeId")
	@JsonIgnoreProperties(value = "ingredients")
	private Recipe recipe;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amountId")
	@JsonIgnoreProperties(value = "ingredients")
	private Amount amount;
	
	public Ingredient() {}

	public Ingredient(String ingredientName, Amount amount, Recipe recipe) {
		super();
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.recipe = recipe;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String name) {
		this.ingredientName = name;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
}
