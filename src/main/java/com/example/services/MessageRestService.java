package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.MessageRepository;
import com.example.entities.Message;
import com.example.entities.Produit;

@RestController
public class MessageRestService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	EtudiantRestService etudiantRestService;
	//renvoie les messages d'un user
	@RequestMapping(value="/MessageUser",method=RequestMethod.GET)
	public Page<Message> getProduitsByUser(
			@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="8")int size){
		return messageRepository.getMessageUser(etudiantRestService.getIdEtudiant(),new PageRequest(page, size));
	}
	//supprimer un message
	@RequestMapping(value="deleteMessage/{id}",method=RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id) {
		messageRepository.deleteById(id);
	}

}
