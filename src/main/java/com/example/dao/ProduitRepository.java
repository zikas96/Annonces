package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	@Query("select p from Produit p")
	public Page<Produit> chercherProduits( Pageable pageable);
	
	@Query("select p from Produit p where p.etudiant.id like :x")
	public Page<Produit> chercherProduitsByUser(@Param("x")Long mc, Pageable pageable);
	
	@Query("select p from Produit p where p.id like :x")
	public Produit chercherById(@Param("x")Long mc);
	
	@Query("select p from Produit p where p.designation like :x or p.title like :x")
	public Page<Produit> chercherProduitsMotCle(@Param("x")String mc, Pageable pageable);
	
	@Query("select p from Produit p where p.ville like :v ")
	public Page<Produit> chercherProduitsVille(@Param("v")String ville, Pageable pageable);
	
	@Query("select p from Produit p where p.type.nom_type like :t ")
	public Page<Produit> chercherProduitsType(@Param("t")String type, Pageable pageable);
	
	@Query("select p from Produit p where p.ville like :v and ( p.designation like :x or p.title like :x )")
	public Page<Produit> chercherProduitsMotCleAndVille(@Param("x")String mc,@Param("v")String ville, Pageable pageable);
	
	@Query("select p from Produit p where p.ville like :v and p.type.nom_type like :t and ( p.designation like :x or p.title like :x )")
	public Page<Produit> chercherProduitsMotCleAndVilleAndType(@Param("x")String mc,@Param("v")String ville,@Param("t")String type, Pageable pageable);
	
	
	@Query("select p from Produit p where p.ville like :v and p.type.nom_type like :t ")
	public Page<Produit> chercherProduitsVilleAndType(@Param("v")String ville,@Param("t")String type, Pageable pageable);
	
	@Query("select p from Produit p where p.type.nom_type like :t and ( p.designation like :x or p.title like :x )")
	public Page<Produit> chercherProduitsMotCleAndType(@Param("x")String mc,@Param("t")String type, Pageable pageable);
	

	
}
