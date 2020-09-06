package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	@Query("select ph from Photo ph where ph.produit.id like :x")
	public List<Photo> getByAnnonce(@Param("x")Long id);
	 @Transactional
	  @Modifying
	@Query("delete from Photo ph where ph.produit.id like :x")
	public void deleteByAnnonce(@Param("x")Long id);

}
