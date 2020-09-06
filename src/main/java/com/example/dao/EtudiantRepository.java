package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Etudiant;


public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	@Query("select e from Etudiant e where e.id like :x")
	public Etudiant getEtudiantById(@Param("x")Long mc);
	@Query("select e.id from Etudiant e where e.mail like :x")
	public Long getIdEtudiant(@Param("x")String mc);
	@Query("select e from Etudiant e where e.mail like :x")
	public Etudiant getEtudiant(@Param("x")String mc);


}
