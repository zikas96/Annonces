package com.example.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Type implements Serializable{
	@Id
	@GeneratedValue
	Long id;
	private String nom_type;
	@OneToMany(mappedBy="type",fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<Produit> produits;
	public Type(String nom_type) {
		super();
		this.nom_type = nom_type;
	}
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom_type() {
		return nom_type;
	}
	public void setNom_type(String nom_type) {
		this.nom_type = nom_type;
	}
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
