package com.cookbook.cookbookdatabase.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long unitId;
	
	@Column(nullable = true)
	private String unit;
	
	@OneToMany(mappedBy = "unit")
	@JsonIgnore
	private List<Amount> amounts;
	
	public Unit() {}

	public Unit(String unit) {
		super();
		this.unit = unit;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(List<Amount> amounts) {
		this.amounts = amounts;
	}
	
}
