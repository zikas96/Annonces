package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.TypeDef;
import org.hibernate.type.TextType;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Produit implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String title;
	@NotEmpty
	@Column(length = 65535, columnDefinition = "text")
	private String designation;
	private double prix;
	private Date date;
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@NotEmpty
	private String ville;
	@ManyToOne
	@JoinColumn(name="CODE_TYPE")
	private Type type;
	@ManyToOne
	@JoinColumn(name="CODE_ETD")
	private Etudiant etudiant;
	@JsonIgnore
	@OneToMany(mappedBy="produit",fetch=FetchType.LAZY)
	private Collection<Photo> photos;
	
	private String photoCouverture;

	public Produit(String designation, double prix, String ville, Etudiant etudiant,Type type,String title,String photoCouverture) {
		super();
		this.title=title;
		this.designation = designation;
		this.prix = prix;
		this.ville = ville;
		this.etudiant = etudiant;
		this.type=type;
		this.photoCouverture = photoCouverture;
	}
	

	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Collection<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Collection<Photo> photos) {
		this.photos = photos;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPhotoCouverture() {
		return photoCouverture;
	}


	public void setPhotoCouverture(String photoCouverture) {
		this.photoCouverture = photoCouverture;
	}
	
	

}
