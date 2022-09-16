package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll () {
		
		List<City> listOfCities = repository.findAll(Sort.by("name"));
		List<CityDTO> listOfCitiesDTO = listOfCities.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
		return listOfCitiesDTO;
		
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		
		City entityCity = new City();
		entityCity.setName(dto.getName());
		entityCity = repository.save(entityCity);
		return new CityDTO(entityCity);		
	}

}
