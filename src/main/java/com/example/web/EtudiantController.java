package com.example.web;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.EtudiantRepository;
import com.example.dao.PhotoRepository;
import com.example.entities.Etudiant;
import com.example.entities.Photo;
import com.example.entities.Role;
import com.example.services.EtudiantRestService;



@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private EtudiantRestService etudiantRestService;

	@RequestMapping(value="/Index")
	public String Index(){
		return "redirect:/index.html";
	}
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formEtudiant(Model model){
		model.addAttribute("etudiant", new Etudiant());
		return "FormEtudiant";
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editEtudiant(Model model){
		Etudiant et=etudiantRestService.getEtudiantById();
		model.addAttribute("etudiant", et);
		return "editEtudiant";
	}
	@RequestMapping(value="/editEtudiant",method=RequestMethod.POST)
	public String edit(@Valid Etudiant et,
			BindingResult bindingResult) throws Exception {
			if(bindingResult.hasErrors()){
				return "editEtudiant";
			}
			Role r=new Role("USER","");
			et.setRole(r);
			etudiantRepository.save(et);

			 
		
		return "redirect:Index";
	}
	@RequestMapping(value="/saveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et,
			BindingResult bindingResult) throws Exception {
			if(bindingResult.hasErrors()){
				return "FormEtudiant";
			}
			Role r=new Role("USER","");
			et.setRole(r);
			etudiantRepository.save(et);

			 
		
		return "redirect:Index";
	}
	

}
