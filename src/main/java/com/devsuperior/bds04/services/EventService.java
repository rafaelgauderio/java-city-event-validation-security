package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll (Pageable pageable) {
		
		Page<Event> page = repository.findAll(pageable);
		return page.map(x -> new EventDTO(x));		
	}
	
	@Transactional
	public EventDTO insert(EventDTO dto) {		
		Event entityEvent = new Event();
		entityEvent.setName(dto.getName());
		entityEvent.setDate(dto.getDate());
		entityEvent.setUrl(dto.getUrl());
		entityEvent.setCity(cityRepository.getOne(dto.getCityId()));
		entityEvent = repository.save(entityEvent);
		return new EventDTO(entityEvent);		
	}
	
	

}
