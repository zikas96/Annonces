package com.example.web;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.example.dao.EtudiantRepository;
import com.example.dao.MessageRepository;
import com.example.entities.Etudiant;
import com.example.entities.Message;
import com.example.services.EtudiantRestService;


@Controller
public class WebMVCConfig{
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	EtudiantRestService etudiantRestService;

	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/Mdp",method=RequestMethod.GET)
	public String renitialiserMdp(Model model){
		Etudiant et=new Etudiant();
		model.addAttribute("etudiant",et);
		return "NewPass";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String sendMessage(Model model,Long id) {
		Etudiant et_destinataire= etudiantRepository.getOne(id);
		Etudiant et_emetteure=etudiantRestService.getEtudiantById();
		model.addAttribute("destinataire",et_destinataire);
		model.addAttribute("emetteure",et_emetteure);
		Message m=new Message();
		m.setEtudiant_destinataire(et_destinataire);
		m.setEtudiant_emetteur(et_emetteure);
		model.addAttribute("message",m);
		return "contact";
	}
	
	@RequestMapping(value="/saveMessage",method=RequestMethod.POST)
	public String saveMessage(@Valid Message message,
			Etudiant destinataire,
			Etudiant emetteure,
			BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()){
			return "test";
		}
		message.setEtudiant_emetteur(etudiantRestService.getEtudiantById());
		message.setEtudiant_destinataire(etudiantRepository.getEtudiantById(destinataire.getId()));
		messageRepository.save(message);
		
		return "redirect:index.html";
	}
	

}
