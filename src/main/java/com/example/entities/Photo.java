package com.example.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Photo implements Serializable{
	@Id @GeneratedValue
	private Long id;
	private String link;
	@ManyToOne
	@JoinColumn(name="CODE_PRD")
	private Produit produit;
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Photo(String link, Produit produit) {
		super();
		this.link = link;
		this.produit = produit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	

}
