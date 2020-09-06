package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query("select m from Message m where m.etudiant_destinataire.id like :x")
	public Page<Message> getMessageUser(@Param("x")Long mc,Pageable pageable);

}
