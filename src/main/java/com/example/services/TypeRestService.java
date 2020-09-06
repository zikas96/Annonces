package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.TypeRepository;

import com.example.entities.Type;

@RestController
public class TypeRestService {
	@Autowired
	TypeRepository typeRepository;
	//renvoie tous les types existent
	@RequestMapping(value="/types",method=RequestMethod.GET)
	public List<Type> listTypes(){
		return typeRepository.findAll();
	}

}
