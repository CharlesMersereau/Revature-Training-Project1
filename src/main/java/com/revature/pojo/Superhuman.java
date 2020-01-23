package com.revature.pojo;

public class Superhuman {
	
	private Integer id;
	private String name;
	private String primaryAbility;
	private boolean alien;
	private String sphereOfInfluence;
	private String alignment;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrimaryAbility() {
		return primaryAbility;
	}
	
	public void setPrimaryAbility(String primaryAbility) {
		this.primaryAbility = primaryAbility;
	}
	
	public boolean isAlien() {
		return alien;
	}
	
	public void setAlien(boolean alien) {
		this.alien = alien;
	}
	
	public String getSphereOfInfluence() {
		return sphereOfInfluence;
	}
	
	public void setSphereOfInfluence(String sphereOfInfluence) {
		this.sphereOfInfluence = sphereOfInfluence;
	}
	
	public String getAlignment() {
		return alignment;
	}
	
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
