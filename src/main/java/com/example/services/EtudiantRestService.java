package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.EtudiantRepository;
import com.example.entities.Etudiant;
import com.example.entities.Produit;

@RestController
public class EtudiantRestService {

	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private HttpServletRequest httpServletRequest;
	private String mail="username";
	//Renvoie id de l'utilisateur
	@RequestMapping(value="/id",method=RequestMethod.GET)
	public Long getIdEtudiant(){
		String email=(String) getLogedUser().get(mail);
		return etudiantRepository.getIdEtudiant(email);
	}
	//Enregistrement de l'utilisateur en appelant la methode save de jpa repository
	@RequestMapping(value="/saveEtudiant",method=RequestMethod.POST)
	public Etudiant saveEtudiant(@RequestBody Etudiant e){
		return etudiantRepository.save(e);
	}
	//Modification l'utilisateur en appelant la methode saveAnd flush de jpa repository
	@RequestMapping(value="updateEtudiant/{id}",method=RequestMethod.PUT)
	public Etudiant updateEtudiant(@RequestBody Etudiant e, @PathVariable("id") Long id){
		e.setId(id);
		return etudiantRepository.saveAndFlush(e);
	}
	//renvoie de tous les utilisateurs
	@RequestMapping(value="/etudiants",method=RequestMethod.GET)
	public Page<Etudiant> listEtudiants(int page,int size){
		return etudiantRepository.findAll(new PageRequest(page, size));
	}
	//Renvoie l'utilisateur logger
	@RequestMapping(value="etudiant",method=RequestMethod.GET)
	public Etudiant getEtudiantById(){
		return etudiantRepository.getEtudiantById(this.getIdEtudiant());
	}
	//Renvoie l'utilisateur(email et role) 
	@RequestMapping(value="/getLogedUser")
	public Map<String, Object> getLogedUser( ){
		HttpSession httpsession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) httpsession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<String> roles=new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			roles.add(ga.getAuthority());
		}
		Map<String,Object> params=new HashMap<>();
		params.put(mail, username);
		params.put("roles", roles);
		return params;
	}
}
