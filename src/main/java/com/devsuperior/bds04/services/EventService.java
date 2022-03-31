package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.dto.EventInsertDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            CityRepository cityRepository){
        this.eventRepository = eventRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPageable(Pageable pageable){
        return eventRepository.findAll(pageable).map(event -> new EventDTO(event));
    }

    @Transactional
    public EventDTO insert(EventInsertDTO dto){
        var event = new Event();

        event.setDate(dto.getDate());
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());

        var city = cityRepository.getOne(dto.getCityId());
        event.setCity(city);

        event =  eventRepository.save(event);
        return new EventDTO(event);
    }
}
