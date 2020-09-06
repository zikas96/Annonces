package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
@Entity
public class Message implements Serializable{
	@Id@GeneratedValue
	private Long id;
	@NotEmpty
	@Column(length = 65535, columnDefinition = "text")
	private String msg;
	@ManyToOne
	@JoinColumn(name="CODE_EMTTEUR")
	private Etudiant etudiant_emetteur;
	@ManyToOne
	@JoinColumn(name="CODE_DESTINATAIREs")
	private Etudiant etudiant_destinataire;


	public Message(@NotEmpty String msg, Etudiant etudiant_emetteur, Etudiant etudiant_destinataire) {
		super();
		this.msg = msg;
		this.etudiant_emetteur = etudiant_emetteur;
		this.etudiant_destinataire = etudiant_destinataire;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Etudiant getEtudiant_emetteur() {
		return etudiant_emetteur;
	}
	public void setEtudiant_emetteur(Etudiant etudiant_emetteur) {
		this.etudiant_emetteur = etudiant_emetteur;
	}
	public Etudiant getEtudiant_destinataire() {
		return etudiant_destinataire;
	}
	public void setEtudiant_destinataire(Etudiant etudiant_destinataire) {
		this.etudiant_destinataire = etudiant_destinataire;
	}

	
	

}
