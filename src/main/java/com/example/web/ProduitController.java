package com.example.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.EtudiantRepository;
import com.example.dao.PhotoRepository;
import com.example.dao.ProduitRepository;
import com.example.entities.Etudiant;
import com.example.entities.Message;
import com.example.entities.Photo;
import com.example.entities.Produit;

import com.example.entities.Type;
import com.example.services.EtudiantRestService;
import com.example.services.TypeRestService;



@Controller
@RequestMapping(value="/Annonces")

public class ProduitController {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private TypeRestService typeRestService;
	@Autowired
	EtudiantRestService etudiantRestService;
	@Autowired
	EtudiantRepository etudiantRepository;
    @Value("${upload.path}")
    private String path;
    @RequestMapping(value="/form",method=RequestMethod.GET)
	public String formProduit(Model model){
    	List<Type> types= typeRestService.listTypes();
    	model.addAttribute("types", types);
		model.addAttribute("produit", new Produit());
		model.addAttribute("photo", new Photo());

		return "FormProduit";
	}
    @RequestMapping(value="/formU",method=RequestMethod.GET)
	public String updateProduit(Model model,Long id){
    	List<Type> types= typeRestService.listTypes();
    	model.addAttribute("types", types);
    	Produit p=produitRepository.getOne(id);
    	List<Photo> photos=photoRepository.getByAnnonce(id);
    	model.addAttribute("photos",photos);
		model.addAttribute("produit", p);
		model.addAttribute("photo", new Photo());

		return "UpdateAnnonce";
	}
    @RequestMapping(value="/Product",method=RequestMethod.GET)
    public String product(Model model,Long id){
    	Produit p=produitRepository.getOne(id);
    	List<Photo> photos=photoRepository.getByAnnonce(id);
    	model.addAttribute("photos",photos);
    	model.addAttribute("produit",p);
    	return "product";
    }
    @RequestMapping(value="/Index")
	public String Index(){
		return "redirect:/index.html";
	}
	@RequestMapping(value="/saveProduit",method=RequestMethod.POST)
	public String save(@Valid Produit p,
			BindingResult bindingResult,
			String t,
			@RequestParam(name="picture")MultipartFile[] files) throws Exception {
			if(bindingResult.hasErrors()){
				return "FormProduit";
			}
			Etudiant et =etudiantRestService.getEtudiantById();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date date = new Date();
			p.setDate(date);
				produitRepository.save(p);
						
			p.setEtudiant(et);
			produitRepository.save(p);
			int i=0;
			 for (MultipartFile file : files){
		    	   if(!file.isEmpty()){
		    		   if(i==0){
		    			   
  
		    					
		    					file.transferTo(new File(path+p.getId()));
		    				
				            p.setPhotoCouverture(file.getOriginalFilename());
				            produitRepository.saveAndFlush(p);
				            i++;
		    		   }else{
		            
		            Photo ph1=photoRepository.save(new Photo(file.getOriginalFilename(), p));
		            file.transferTo(new File(path+ph1.getId()));

		    	   i++;}
		    	   }
		       }

		return "redirect:Index";
	}
	@RequestMapping(value="/updatProduit",method=RequestMethod.POST)
	public String update(@Valid Produit p,
			BindingResult bindingResult,
			String t,
			@RequestParam(name="picture")MultipartFile[] files) throws Exception {
			if(bindingResult.hasErrors()){
				return "FormProduit";
			}
			Etudiant et =etudiantRestService.getEtudiantById();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date date = new Date();
			p.setDate(date);
				produitRepository.save(p);
						
			p.setEtudiant(et);
			produitRepository.save(p);
			int i=0;
			 for (MultipartFile file : files){
		    	   if(!file.isEmpty()){
		    		   if(i==0){
		    			    					
		    					file.transferTo(new File(path+p.getId()));
		    				
				            p.setPhotoCouverture(file.getOriginalFilename());
				            produitRepository.saveAndFlush(p);
				            i++;
		    		   }else{
		            
		            Photo ph1=photoRepository.save(new Photo(file.getOriginalFilename(), p));
		            file.transferTo(new File(path+ph1.getId()));

		    	   i++;}
		    	   }
		    	   else
		    		   System.out.println("khawi");
		       }

		return "redirect:Index";	}
	@RequestMapping(value="/getPhoto",
			produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	
	public byte[] getPhoto(Long id) throws Exception{
		
		File f = new File(path+id);
		InputStream is = new FileInputStream(f);
		byte[] result = IOUtils.toByteArray(is);
		is.close();
		return result;
		
	}
	
	
	

}
