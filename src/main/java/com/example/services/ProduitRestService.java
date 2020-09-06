package com.example.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.PhotoRepository;
import com.example.dao.ProduitRepository;
import com.example.entities.Photo;
import com.example.entities.Produit;
import com.example.web.ProduitController;

@RestController
public class ProduitRestService {
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	EtudiantRestService etudiantRestService;
	@Autowired
	PhotoRepository photoRepository;
    @Value("${upload.path}")
    private String path;
    //renvoie tous les annonces
	@RequestMapping(value="/produits",method=RequestMethod.GET)
	public Page<Produit> getProduits(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size){
		return produitRepository.findAll(new PageRequest(page, size));
	}
	//Renvoie des annonces par utilisateur connecte
	@RequestMapping(value="/produitsUser",method=RequestMethod.GET)
	public Page<Produit> getProduitsByUser(
			@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="5")int size){
		return produitRepository.chercherProduitsByUser(etudiantRestService.getIdEtudiant(),new PageRequest(page, size));
	}
	//Modifier une annonce par son id
	@RequestMapping(value="updateProduit/{id}",method=RequestMethod.POST)
	public Produit updateProduit(@RequestBody Produit p, @PathVariable("id") Long id){
		p.setId(id);
		return produitRepository.saveAndFlush(p);
	}
	//enregistrer une annoces
	@RequestMapping(value="/saveProduit",method=RequestMethod.POST)
	public Produit saveProduit(@RequestBody Produit p){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		p.setDate(date);
		return produitRepository.save(p);
	}
	//supprimer une annoces
	@RequestMapping(value="deleteProduit/{id}",method=RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id) throws FileNotFoundException, IOException {

		//liste des photo d'une annonces
		List<Photo> photos=photoRepository.getByAnnonce(id);
		//suppression des photo de l'annoce
		photoRepository.deleteByAnnonce(id);
		//suppresion de l'annonce
		produitRepository.deleteById(id);
		//supprestion des fichier photo de couverture
		File fich= new File(path+id);
		System.out.println(path+id);
		
		fich.delete();
		//suppression des fichier des photo
		for(Photo ph: photos){
			File fichier= new File(path+ph.getId());
			System.out.println(path+ph.getId());
			fichier.delete();
			
		}

		
	}
	//cherecher les annonces par motCles
	@RequestMapping(value="/produitsMc/{mc}",method=RequestMethod.GET)
	public Page<Produit> getProduitsMotCle(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("mc") String mc){
		return produitRepository.chercherProduitsMotCle("%"+mc+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par ville et type et motCles
	@RequestMapping(value="/produitsMcVT/{mc}/{ville}/{type}",method=RequestMethod.GET)
	public Page<Produit> getProduitsMotCleAndVilleAndType(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("mc") String mc,
			@PathVariable("ville") String ville,
			@PathVariable("type") String type){
		return produitRepository.chercherProduitsMotCleAndVilleAndType("%"+mc+"%","%"+ville+"%","%"+type+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par ville 
	@RequestMapping(value="/produitsV/{ville}",method=RequestMethod.GET)
	public Page<Produit> getProduitsVille(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("ville") String ville){
		return produitRepository.chercherProduitsVille("%"+ville+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par type 
	@RequestMapping(value="/produitsT/{type}",method=RequestMethod.GET)
	public Page<Produit> getProduitsType(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("type") String type){
		return produitRepository.chercherProduitsType("%"+type+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par ville et  motCles
	@RequestMapping(value="/produitsMcV/{mc}/{ville}",method=RequestMethod.GET)
	public Page<Produit> getProduitsMotCleAndVille(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("mc") String mc,
			@PathVariable("ville") String ville){
		return produitRepository.chercherProduitsMotCleAndVille("%"+mc+"%","%"+ville+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par type et motCles
	@RequestMapping(value="/produitsMcT/{mc}/{type}",method=RequestMethod.GET)
	public Page<Produit> getProduitsMotCleAndType(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("mc") String mc,
			@PathVariable("type") String type){
		return produitRepository.chercherProduitsMotCleAndType("%"+mc+"%","%"+type+"%", new PageRequest(page, size));
	}
	//cherecher les annonces par ville et type 
	@RequestMapping(value="/produitsVT/{ville}/{type}",method=RequestMethod.GET)
	public Page<Produit> getProduitsVilleAndType(@RequestParam(name="page", defaultValue="0")int page ,
			@RequestParam(name="size", defaultValue="10")int size,
			@PathVariable("ville") String ville,
			@PathVariable("type") String type){
		return produitRepository.chercherProduitsVilleAndType("%"+ville+"%","%"+type+"%", new PageRequest(page, size));
	}
	

}
