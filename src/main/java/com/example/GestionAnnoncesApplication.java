package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.management.relation.RoleResult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.dao.EtudiantRepository;
import com.example.dao.MessageRepository;
import com.example.dao.ProduitRepository;
import com.example.dao.RoleRepository;
import com.example.dao.TypeRepository;
import com.example.entities.Etudiant;
import com.example.entities.Message;
import com.example.entities.Role;
import com.example.entities.Type;

@SpringBootApplication
public class GestionAnnoncesApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(GestionAnnoncesApplication.class, args);
		EtudiantRepository etudiantRepository=ctx.getBean(EtudiantRepository.class);
/*		ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);
		
		typeRepository.save(new Type("GHTA"));
		
		etudiantRepository.save(new Etudiant("zakaria", "0769117954"
				, "najizakaria96@gmail.com", "1234", r));*/
		TypeRepository typeRepository=ctx.getBean(TypeRepository.class);
		typeRepository.save(new Type("voiture"));
		typeRepository.save(new Type("Immobillier"));
		typeRepository.save(new Type("Telephone"));
		typeRepository.save(new Type("Livres"));
		RoleRepository roleRepository=ctx.getBean(RoleRepository.class);
		Role r=new Role("ADMIN", "");
		roleRepository.save(r);
		roleRepository.save(new Role("USER", ""));
		MessageRepository messageRepository=ctx.getBean(MessageRepository.class);
		
	}
	

}

