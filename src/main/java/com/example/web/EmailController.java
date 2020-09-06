package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.EtudiantRepository;
import com.example.entities.Etudiant;
import com.example.services.EtudiantRestService;

@Controller
public class EmailController {
 
    @Autowired
    public JavaMailSender emailSender;
    @Autowired 
    EtudiantRestService etudiantRestService;
    @Autowired
    EtudiantRepository etudiantRepository;
    @RequestMapping(value="/sendSimpleEmail")
    public String sendSimpleEmail(Etudiant et) {
    	
    	
    	Etudiant etudiant=etudiantRepository.getEtudiant(et.getMail());
    	System.out.println(etudiant.getMail());
    	System.out.println("le password est :" + etudiant.getPassword());
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(etudiant.getMail());
        message.setSubject("Renitialisation de votre mot de passe");
        message.setText("Bonjour \n Votre mot de pass est : "+etudiant.getPassword());
 
        // Send Message!
        this.emailSender.send(message);
 
        return "redirect:/login";
    }
 
}