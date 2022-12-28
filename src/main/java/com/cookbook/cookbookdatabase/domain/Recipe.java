package com.cookbook.cookbookdatabase.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long recipeId;
	
	@Column(nullable = true)
	private String recipeName;
	
	@Column(nullable = true, length = 2000)
	private String instructions;
	
	@Column(nullable = false)
	private LocalDateTime dateCreated;
	
	@Column(nullable = true)
	private LocalDateTime dateEdited;
	
	@Column(nullable = true)
	private String source;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties(value = "recipes")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnoreProperties(value = "recipes")
	private Category category;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "recipe")
	private List<Ingredient> ingredients;
	
	public Recipe() {}

	public Recipe(String recipeName, String instructions, LocalDateTime dateCreated, LocalDateTime dateEdited, String source,
			User user, Category category) {
		super();
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.dateCreated = dateCreated;
		this.dateEdited = dateEdited;
		this.source = source;
		this.user = user;
		this.category = category;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String name) {
		this.recipeName = name;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateEdited() {
		return dateEdited;
	}

	public void setDateEdited(LocalDateTime dateEdited) {
		this.dateEdited = dateEdited;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
