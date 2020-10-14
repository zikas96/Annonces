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
		TypeRepository typeRepository=ctx.getBean(TypeRepository.class);
		typeRepository.save(new Type("voiture"));
		typeRepository.save(new Type("Immobillier"));
		typeRepository.save(new Type("Telephone"));
		typeRepository.save(new Type("Livres"));
		typeRepository.save(new Type("accessoire"));
		typeRepository.save(new Type("vetement"));

		RoleRepository roleRepository=ctx.getBean(RoleRepository.class);
		Role r=new Role("ADMIN", "");
		roleRepository.save(r);
		Role roleUser = new Role("USER", "");
		roleRepository.save(roleUser);

		etudiantRepository.save(new Etudiant("mohammed", "+212671617999", "dars930@gmail.com", "1234", roleUser));
		etudiantRepository.save(new Etudiant("zakaria", "+212646256126", "najizakaria96@gmail.com", "123455", roleUser));


	}


}
