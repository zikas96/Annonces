package com.example.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.PhotoRepository;
import com.example.entities.Photo;

@RestController
public class PhotoRestService {
	@Autowired
	PhotoRepository photoRepository;
	//enregistrement d'une photo dans la base de donnes
	@RequestMapping(value="/photo")
	public Photo savePhoto(Photo p){
		return photoRepository.save(p);
	}
	//renvoie les photo d'une annonces par id
	@RequestMapping(value="/photos/{id}")
	public List<Photo> getPhotos(@PathVariable("id") Long id){
		return photoRepository.getByAnnonce(id);
	}
	//supprime tout les photo d'une annoce
	@RequestMapping(value="/pho/{id}")
	public void deletePhotos(@PathVariable("id") Long id){
		 photoRepository.deleteByAnnonce(id);
	}
	
	}


