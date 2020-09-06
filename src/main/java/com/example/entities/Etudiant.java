package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Etudiant implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String password;
	@NotEmpty
	private String tel;
	@NotEmpty
	@Email
	private String mail;
	private boolean actived;
	@NotEmpty
	private String prenom;
	
	@ManyToOne
	@JoinColumn(name="role_etudiant")
	private Role role;
	@JsonIgnore
	@OneToMany(mappedBy="etudiant",fetch=FetchType.LAZY)
	private Collection<Produit> produits;
	@JsonIgnore
	@OneToMany(mappedBy="etudiant_emetteur",fetch=FetchType.LAZY)
	private Collection<Message> messages_envoye;
	@JsonIgnore
	@OneToMany(mappedBy="etudiant_destinataire",fetch=FetchType.LAZY)
	private Collection<Message> messages_recu;
	

	public Etudiant(String prenom, String tel, String mail, String password,Role role) {
		super();
		this.prenom = prenom;
		this.tel = tel;
		this.mail = mail;
		this.password = password;
		this.role=role;
		
	}
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}

	public Collection<Message> getMessages_envoye() {
		return messages_envoye;
	}
	public void setMessages_envoye(Collection<Message> messages_envoye) {
		this.messages_envoye = messages_envoye;
	}
	public Collection<Message> getMessages_recu() {
		return messages_recu;
	}
	public void setMessages_recu(Collection<Message> messages_recu) {
		this.messages_recu = messages_recu;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
